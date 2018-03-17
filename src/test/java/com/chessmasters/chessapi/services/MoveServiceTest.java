package com.chessmasters.chessapi.services;

import com.chessmasters.chessapi.entities.Game;
import com.chessmasters.chessapi.entities.Move;
import com.chessmasters.chessapi.entities.Square;
import com.chessmasters.chessapi.models.MoveModel;
import com.chessmasters.chessapi.models.SquareModel;
import com.chessmasters.chessapi.repositories.MoveRepository;
import com.chessmasters.chessapi.request.MoveRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
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
        final int expectedOrder = 1;
        final Long gameId = 1L;
        Game game = new Game();
        ReflectionTestUtils.setField(game, "id", gameId);
        Square destination = new Square();
        SquareModel expectedDestination = new SquareModel(destination);
        MoveRequest request = new MoveRequest(expectedDestination);

        when(gameService.getById(gameId)).thenReturn(game);
        when(moveRepository.save(any(Move.class))).thenReturn(new Move(game, destination, expectedOrder));

        MoveModel moveModel = service.createMove(gameId, request);

        assertThat(moveModel).isNotNull();
        assertThat(moveModel.getGameId()).isEqualTo(gameId);
        assertThat(moveModel.getOrder()).isEqualTo(expectedOrder);
        assertThat(moveModel.getDestination()).isEqualTo(expectedDestination);
    }

    @Test
    public void saveMoveOnDatabase() {
        final Long gameId = 1L;
        Game game = new Game();
        MoveRequest request = new MoveRequest(new SquareModel());

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

    @Test
    public void createMoveHasOrderIncreasedByOne() {
        final Long gameId = 1L;
        final int moveOrder = 1;
        final int expectedMoveOrder = 2;
        Game game = new Game();
        Square destination = new Square();
        MoveRequest request = new MoveRequest(new SquareModel(destination));
        List<Move> moves = new ArrayList<>();
        moves.add(new Move(game, destination, moveOrder));

        when(gameService.getById(gameId)).thenReturn(game);
        when(moveRepository.findByGameId(gameId)).thenReturn(moves);
        when(moveRepository.save(any(Move.class))).thenReturn(new Move(game, destination, expectedMoveOrder));

        MoveModel moveModel = service.createMove(gameId, request);

        assertThat(moveModel).isNotNull();
        assertThat(moveModel.getOrder()).isEqualTo(expectedMoveOrder);
    }
}