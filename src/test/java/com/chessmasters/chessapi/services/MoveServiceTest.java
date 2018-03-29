package com.chessmasters.chessapi.services;

import com.chessmasters.chessapi.entities.*;
import com.chessmasters.chessapi.enums.Color;
import com.chessmasters.chessapi.enums.ErrorMessage;
import com.chessmasters.chessapi.enums.GameStatus;
import com.chessmasters.chessapi.exceptions.GameNotStartedException;
import com.chessmasters.chessapi.exceptions.InvalidMoveException;
import com.chessmasters.chessapi.exceptions.PieceNotFoundException;
import com.chessmasters.chessapi.models.Move;
import com.chessmasters.chessapi.models.Square;
import com.chessmasters.chessapi.repositories.MoveRepository;
import com.chessmasters.chessapi.repositories.PieceRepository;
import com.chessmasters.chessapi.request.MoveRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.util.ReflectionTestUtils;

import static com.chessmasters.chessapi.enums.Color.BLACK;
import static com.chessmasters.chessapi.enums.Color.WHITE;
import static com.chessmasters.chessapi.enums.GameStatus.CREATED;
import static com.chessmasters.chessapi.enums.GameStatus.STARTED;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MoveServiceTest {

    @InjectMocks
    private MoveService service;
    @Mock
    private MoveRepository moveRepository;
    @Mock
    private PieceRepository pieceRepository;
    @Mock
    private GameService gameService;
    @Mock
    private PlayerService playerService;

    @Test
    public void createMove() {
        final Long gameId = 1L;
        final int expectedOrder = 1;
        mockPlayerEntity();
        mockPieceEntity();
        GameEntity gameEntity = mockGameEntity(gameId, STARTED);
        mockMoveEntity(gameEntity, expectedOrder);
        MoveRequest request = createMoveRequest();

        Move move = service.createMove(gameId, request);

        assertThat(move).isNotNull();
        assertThat(move.getGameId()).isEqualTo(gameId);
        assertThat(move.getOrder()).isEqualTo(expectedOrder);
        assertThat(move.getDestination()).isEqualTo(request.getDestination());
    }

    @Test
    public void createMoveHasOrderIncreasedByOne() {
        final Long gameId = 1L;
        final int increasedMoveOrder = 2;
        mockPlayerEntity();
        mockPieceEntity();
        GameEntity gameEntity = mockGameEntity(gameId, STARTED);
        mockMoveEntity(gameEntity, increasedMoveOrder);
        MoveRequest request = createMoveRequest();

        Move move = service.createMove(gameId, request);

        assertThat(move).isNotNull();
        assertThat(move.getOrder()).isEqualTo(increasedMoveOrder);
    }

    @Test
    public void throwGameNotStartedExceptionWhenTryingToMovePieceOnNonStartedGame() {
        final Long gameId = 1L;
        final int order = 1;
        mockPlayerEntity();
        GameEntity gameEntity = mockGameEntity(gameId, CREATED);
        mockMoveEntity(gameEntity, order);
        MoveRequest request = createMoveRequest();

        assertThatThrownBy(() -> service.createMove(gameId, request))
                .isInstanceOf(GameNotStartedException.class);
    }

    @Test
    public void saveMoveOnDatabase() {
        final Long gameId = 1L;
        final int order = 1;
        mockPlayerEntity();
        mockPieceEntity();
        GameEntity gameEntity = mockGameEntity(gameId, STARTED);
        mockMoveEntity(gameEntity, order);
        MoveRequest request = createMoveRequest();

        service.createMove(gameId, request);

        verify(moveRepository).save(any(MoveEntity.class));
    }

    @Test
    public void listGameMovesFromDatabase() {
        final Long gameId = 1L;

        service.getMoves(gameId);

        verify(moveRepository).findByGameId(gameId);
    }

    @Test
    public void throwInvalidMoveExceptionWhenFirstMoveIsNotFromWhitePlayer() {
        final Long gameId = 1L;
        final int order = 1;
        final Color pieceColor = BLACK;
        mockPlayerEntity(pieceColor);
        mockPieceEntity(pieceColor);
        GameEntity gameEntity = mockGameEntity(gameId, STARTED);
        mockMoveEntity(gameEntity, order);
        MoveRequest request = createMoveRequest();

        assertThatThrownBy(() -> service.createMove(gameId, request))
                .isInstanceOf(InvalidMoveException.class)
                .hasMessage(String.valueOf(ErrorMessage.INVALID_MOVE_ITS_OPPONENTS_TURN));
    }

    @Test
    public void throwInvalidMoveExceptionWhenNextMoveIsFromRepeatedColor() {
        final Long gameId = 1L;
        final int moveOrder = 1;
        mockPlayerEntity();
        mockPieceEntity();
        GameEntity gameEntity = mockGameEntity(gameId, STARTED);
        MoveEntity lastMove = mockMoveEntity(gameEntity, moveOrder);
        MoveRequest request = createMoveRequest();

        when(moveRepository.findTopByGameOrderByMoveOrderDesc(any(GameEntity.class))).thenReturn(lastMove);

        assertThatThrownBy(() -> service.createMove(gameId, request))
                .isInstanceOf(InvalidMoveException.class)
                .hasMessage(String.valueOf(ErrorMessage.INVALID_MOVE_ITS_OPPONENTS_TURN));
    }

    @Test
    public void throwInvalidMoveExceptionWhenPlayerTriesToMoveOpponentsPiece() {
        final Long gameId = 1L;
        final Color playerColor = WHITE;
        final Color pieceColor = BLACK;
        mockPlayerEntity(playerColor);
        mockPieceEntity(pieceColor);
        mockGameEntity(gameId, STARTED);
        MoveRequest request = createMoveRequest();

        assertThatThrownBy(() -> service.createMove(gameId, request))
                .isInstanceOf(InvalidMoveException.class)
                .hasMessage(String.valueOf(ErrorMessage.INVALID_MOVE_ITS_OPPONENTS_PIECE));
    }

    @Test
    public void throwPieceNotFoundWhenTryingToMoveNonExistentPiece() {
        final Long gameId = 1L;
        mockPlayerEntity(WHITE);
        mockGameEntity(gameId, STARTED);
        MoveRequest request = createMoveRequest();

        when(pieceRepository.findOne(any(Long.class))).thenReturn(null);

        assertThatThrownBy(() -> service.createMove(gameId, request))
                .isInstanceOf(PieceNotFoundException.class);
    }

    private MoveRequest createMoveRequest() {
        final Long playerId = 1L;
        final Long pieceId = 1L;
        SquareEntity destination = new SquareEntity();
        Square expectedDestination = new Square(destination);

        return new MoveRequest(playerId, pieceId, expectedDestination);
    }

    private GameEntity mockGameEntity(final Long gameId, GameStatus gameStatus) {
        GameEntity gameEntity = new GameEntity(new PlayerEntity(), gameStatus);
        ReflectionTestUtils.setField(gameEntity, "id", gameId);
        when(gameService.getById(gameId)).thenReturn(gameEntity);

        return gameEntity;
    }

    private MoveEntity mockMoveEntity(GameEntity gameEntity, final int moveOrder) {
        SquareEntity destination = new SquareEntity();
        final PieceEntity pawn = createPieceEntity(WHITE);
        final MoveEntity moveEntity = new MoveEntity(gameEntity, pawn, destination, moveOrder);

        when(moveRepository.save(any(MoveEntity.class))).thenReturn(moveEntity);

        return moveEntity;
    }

    private void mockPlayerEntity() {
        mockPlayerEntity(WHITE);
    }

    private void mockPlayerEntity(Color playerColor) {
        final PlayerEntity player = new PlayerEntity();
        player.setColor(playerColor);

        when(playerService.getById(anyLong())).thenReturn(player);
    }

    private void mockPieceEntity() {
        mockPieceEntity(WHITE);
    }

    private void mockPieceEntity(Color pieceColor) {
        final PieceEntity pieceEntity = createPieceEntity(pieceColor);
        when(pieceRepository.findOne(any(Long.class))).thenReturn(pieceEntity);
    }

    private PieceEntity createPieceEntity(Color pieceColor) {
        return new PieceEntity(new GameEntity(), pieceColor, null, "Pawn");
    }
}