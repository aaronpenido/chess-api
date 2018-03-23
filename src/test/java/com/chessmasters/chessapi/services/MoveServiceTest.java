package com.chessmasters.chessapi.services;

import com.chessmasters.chessapi.entities.*;
import com.chessmasters.chessapi.enums.ErrorMessage;
import com.chessmasters.chessapi.enums.GameStatus;
import com.chessmasters.chessapi.exceptions.GameNotStartedException;
import com.chessmasters.chessapi.exceptions.InvalidMoveException;
import com.chessmasters.chessapi.models.Move;
import com.chessmasters.chessapi.models.Pawn;
import com.chessmasters.chessapi.models.Piece;
import com.chessmasters.chessapi.models.Square;
import com.chessmasters.chessapi.repositories.MoveRepository;
import com.chessmasters.chessapi.request.MoveRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.util.ReflectionTestUtils;

import static com.chessmasters.chessapi.enums.Color.BLACK;
import static com.chessmasters.chessapi.enums.Color.WHITE;
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
    private GameService gameService;
    @Mock
    private PlayerService playerService;

    @Test
    public void createMove() {
        final Long gameId = 1L;
        final int expectedOrder = 1;
        MoveRequest request = createMoveRequest(gameId, expectedOrder);
        final PlayerEntity player = new PlayerEntity();
        player.setColor(WHITE);

        when(playerService.getById(anyLong())).thenReturn(player);
        when(moveRepository.findTopByGameOrderByMoveOrderDesc(any(GameEntity.class))).thenReturn(null);

        Move moveModel = service.createMove(gameId, request);

        assertThat(moveModel).isNotNull();
        assertThat(moveModel.getGameId()).isEqualTo(gameId);
        assertThat(moveModel.getOrder()).isEqualTo(expectedOrder);
        assertThat(moveModel.getDestination()).isEqualTo(request.getDestination());
    }

    @Test
    public void createMoveHasOrderIncreasedByOne() {
        final Long gameId = 1L;
        final int increasedMoveOrder = 2;
        final PlayerEntity player = new PlayerEntity();
        player.setColor(WHITE);

        when(playerService.getById(anyLong())).thenReturn(player);
        when(moveRepository.findTopByGameOrderByMoveOrderDesc(any(GameEntity.class))).thenReturn(null);

        MoveRequest request = createMoveRequest(gameId, increasedMoveOrder);
        Move moveModel = service.createMove(gameId, request);

        assertThat(moveModel).isNotNull();
        assertThat(moveModel.getOrder()).isEqualTo(increasedMoveOrder);
    }

    @Test
    public void throwGameNotStartedExceptionWhenTryingToMovePieceOnNonStartedGame() {
        final Long playerId = 1L;
        final Long gameId = 1L;
        GameEntity game = new GameEntity(new PlayerEntity(), GameStatus.CREATED);
        SquareEntity destination = new SquareEntity();
        PieceEntity piece = new PieceEntity(WHITE, new SquareEntity(), "Pawn");
        Piece pawn = new Pawn(piece);
        MoveRequest request = new MoveRequest(playerId, pawn, new Square(destination));

        when(gameService.getById(gameId)).thenReturn(game);

        assertThatThrownBy(() -> service.createMove(gameId, request))
                .isInstanceOf(GameNotStartedException.class);
    }

    @Test
    public void saveMoveOnDatabase() {
        final Long playerId = 1L;
        final Long gameId = 1L;
        GameEntity game = new GameEntity(new PlayerEntity(), GameStatus.STARTED);
        final SquareEntity square = new SquareEntity();
        PieceEntity piece = new PieceEntity(WHITE, square, "Pawn");
        Piece pawn = new Pawn(piece);
        final MoveRequest request = new MoveRequest(playerId, pawn, new Square(square));
        final PlayerEntity player = new PlayerEntity();
        player.setColor(WHITE);

        when(playerService.getById(anyLong())).thenReturn(player);
        when(moveRepository.findTopByGameOrderByMoveOrderDesc(game)).thenReturn(null);
        when(gameService.getById(gameId)).thenReturn(game);
        when(moveRepository.save(any(MoveEntity.class))).thenReturn(new MoveEntity());

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
        final Long playerId = 1L;
        final Long gameId = 1L;
        SquareEntity destination = new SquareEntity();
        PieceEntity piece = new PieceEntity(BLACK, destination, "Pawn");
        Piece pawn = new Pawn(piece);
        MoveRequest request = new MoveRequest(playerId, pawn, new Square(destination));
        PlayerEntity player = new PlayerEntity();
        player.setColor(BLACK);
        GameEntity game = new GameEntity(player, GameStatus.STARTED);

        when(gameService.getById(gameId)).thenReturn(game);
        when(playerService.getById(playerId)).thenReturn(player);
        when(moveRepository.findTopByGameOrderByMoveOrderDesc(game)).thenReturn(null);
        when(moveRepository.save(any(MoveEntity.class))).thenReturn(new MoveEntity());

        assertThatThrownBy(() -> service.createMove(gameId, request))
                .isInstanceOf(InvalidMoveException.class)
                .hasMessage(String.valueOf(ErrorMessage.INVALID_MOVE_ITS_OPPONENTS_TURN));
    }

    private MoveRequest createMoveRequest(final Long gameId, final int order) {
        final Long playerId = 1L;
        GameEntity game = new GameEntity(new PlayerEntity(), GameStatus.STARTED);
        ReflectionTestUtils.setField(game, "id", gameId);
        SquareEntity destination = new SquareEntity();
        Square expectedDestination = new Square(destination);
         PieceEntity piece = new PieceEntity(WHITE, destination, "Pawn");
        Piece pawn = new Pawn(piece);
        final MoveEntity move = new MoveEntity(game, null, destination, order);

        when(gameService.getById(gameId)).thenReturn(game);
        when(moveRepository.save(any(MoveEntity.class))).thenReturn(move);

        return new MoveRequest(playerId, pawn, expectedDestination);
    }
}