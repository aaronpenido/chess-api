package com.chessmasters.chessapi.services;

import com.chessmasters.chessapi.entities.*;
import com.chessmasters.chessapi.enums.GameStatus;
import com.chessmasters.chessapi.exceptions.GameNotStartedException;
import com.chessmasters.chessapi.models.MoveModel;
import com.chessmasters.chessapi.models.Pawn;
import com.chessmasters.chessapi.models.PieceModel;
import com.chessmasters.chessapi.models.SquareModel;
import com.chessmasters.chessapi.repositories.MoveRepository;
import com.chessmasters.chessapi.request.MoveRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.util.ReflectionTestUtils;

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
    private GameService gameService;

    @Test
    public void createMove() {
        final Long gameId = 1L;
        final int expectedOrder = 1;
        MoveRequest request = createMoveRequest(gameId, expectedOrder);

        MoveModel moveModel = service.createMove(gameId, request);

        assertThat(moveModel).isNotNull();
        assertThat(moveModel.getGameId()).isEqualTo(gameId);
        assertThat(moveModel.getOrder()).isEqualTo(expectedOrder);
        assertThat(moveModel.getDestination()).isEqualTo(request.getDestination());
    }

    @Test
    public void createMoveHasOrderIncreasedByOne() {
        final Long gameId = 1L;
        final int increasedMoveOrder = 2;

        MoveRequest request = createMoveRequest(gameId, increasedMoveOrder);
        MoveModel moveModel = service.createMove(gameId, request);

        assertThat(moveModel).isNotNull();
        assertThat(moveModel.getOrder()).isEqualTo(increasedMoveOrder);
    }

    @Test
    public void throwGameNotStartedExceptionWhenTryingToMovePieceOnNonStartedGame() {
        final Long gameId = 1L;
        Game game = new Game(new Player(), GameStatus.CREATED);
        Square destination = new Square();
        Piece piece = new Piece("White", new Square(), "Pawn");
        PieceModel pawn = new Pawn(piece);
        MoveRequest request = new MoveRequest(pawn, new SquareModel(destination));

        when(gameService.getById(gameId)).thenReturn(game);

        assertThatThrownBy(() -> service.createMove(gameId, request))
                .isInstanceOf(GameNotStartedException.class);
    }

    @Test
    public void saveMoveOnDatabase() {
        final Long gameId = 1L;
        Game game = new Game(new Player(), GameStatus.STARTED);
        final Square square = new Square();
        Piece piece = new Piece("White", square, "Pawn");
        PieceModel pawn = new Pawn(piece);
        final MoveRequest request = new MoveRequest(pawn, new SquareModel(square));

        when(gameService.getById(gameId)).thenReturn(game);
        when(moveRepository.save(any(Move.class))).thenReturn(new Move());

        service.createMove(gameId, request);

        verify(moveRepository).save(any(Move.class));
    }

    @Test
    public void listGameMovesFromDatabase() {
        final Long gameId = 1L;

        service.getMoves(gameId);

        verify(moveRepository).findByGameId(gameId);
    }

    private MoveRequest createMoveRequest(final Long gameId, final int order) {
        Game game = new Game(new Player(), GameStatus.STARTED);
        ReflectionTestUtils.setField(game, "id", gameId);
        Square destination = new Square();
        SquareModel expectedDestination = new SquareModel(destination);
         Piece piece = new Piece("White", destination, "Pawn");
        PieceModel pawn = new Pawn(piece);
        final Move move = new Move(game, null, destination, order);

        when(gameService.getById(gameId)).thenReturn(game);
        when(moveRepository.save(any(Move.class))).thenReturn(move);

        return new MoveRequest(pawn, expectedDestination);
    }
}