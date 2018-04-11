package com.chessmasters.chessapi.services;

import com.chessmasters.chessapi.entities.*;
import com.chessmasters.chessapi.enums.Color;
import com.chessmasters.chessapi.enums.GameStatus;
import com.chessmasters.chessapi.exceptions.GameNotStartedException;
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

import static com.chessmasters.chessapi.enums.Letter.E;
import static com.chessmasters.chessapi.enums.PieceType.PAWN;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Matchers.any;
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
        mockObjects();
        MoveRequest request = createMoveRequest();

        Move move = service.createMove(gameId, request);

        assertThat(move).isNotNull();
        assertThat(move.getOrder()).isEqualTo(expectedOrder);
    }

    @Test
    public void saveMoveOnDatabase() {
        final Long gameId = 1L;
        mockObjects();
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
    public void throwGameNotStartedExceptionWhenTryingToMovePieceOnNonStartedGame() {
        final Long gameId = 1L;
        final Long playerId = 1L;
        final Long pieceId = 1L;
        Square destination = new Square(E, 1);
        MoveRequest request = new MoveRequest(playerId, pieceId, destination);
        GameEntity gameEntity = new GameEntity(new PlayerEntity(), GameStatus.CREATED);

        when(gameService.getById(gameId)).thenReturn(gameEntity);

        assertThatThrownBy(() -> service.createMove(gameId, request))
                .isInstanceOf(GameNotStartedException.class);
    }

    @Test
    public void throwPieceNotFoundExceptionWhenTryingToMoveNonExistentPiece() {
        final Long gameId = 1L;
        final Long playerId = 1L;
        final Long pieceId = 1L;
        Square destination = new Square(E, 1);
        GameEntity gameEntity = new GameEntity(new PlayerEntity(), GameStatus.STARTED);
        MoveRequest request = new MoveRequest(playerId, pieceId, destination);

        when(gameService.getById(gameId)).thenReturn(gameEntity);
        when(pieceRepository.findOne(any(Long.class))).thenReturn(null);

        assertThatThrownBy(() -> service.createMove(gameId, request))
                .isInstanceOf(PieceNotFoundException.class);
    }

    private MoveRequest createMoveRequest() {
        final Long playerId = 1L;
        final Long pieceId = 1L;
        Square destination = new Square(E, 1);
        PlayerEntity playerEntity = new PlayerEntity();
        playerEntity.setColor(Color.WHITE);

        return new MoveRequest(playerId, pieceId, destination);
    }

    private void mockObjects() {
        SquareEntity squareEntity = new SquareEntity();
        PlayerEntity playerEntity = new PlayerEntity();
        playerEntity.setColor(Color.WHITE);
        GameEntity gameEntity = new GameEntity(playerEntity, GameStatus.STARTED);
        PieceEntity pieceEntity = new PieceEntity(gameEntity, Color.WHITE, squareEntity, PAWN);
        MoveEntity moveEntity = new MoveEntity(gameEntity, pieceEntity, squareEntity);
        moveEntity.setMoveOrder(1);

        when(playerService.getById(any(Long.class))).thenReturn(playerEntity);
        when(gameService.getById(any(Long.class))).thenReturn(gameEntity);
        when(pieceRepository.findOne(any(Long.class))).thenReturn(pieceEntity);
        when(moveRepository.save(any(MoveEntity.class))).thenReturn(moveEntity);
    }
}