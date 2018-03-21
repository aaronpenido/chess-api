package com.chessmasters.chessapi.controllers;

import com.chessmasters.chessapi.entities.Game;
import com.chessmasters.chessapi.entities.Move;
import com.chessmasters.chessapi.entities.Player;
import com.chessmasters.chessapi.entities.Square;
import com.chessmasters.chessapi.enums.GameStatus;
import com.chessmasters.chessapi.models.MoveModel;
import com.chessmasters.chessapi.models.Pawn;
import com.chessmasters.chessapi.models.PieceModel;
import com.chessmasters.chessapi.models.SquareModel;
import com.chessmasters.chessapi.request.MoveRequest;
import com.chessmasters.chessapi.response.MoveResponse;
import com.chessmasters.chessapi.services.MoveService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MoveControllerTest {

    @InjectMocks
    private MoveController controller;
    @Mock
    private MoveService moveService;

    @Test
    public void createMove() {
        final Long gameId = 1L;
        final int moveOrder = 1;
        MoveRequest request = createMoveRequest(gameId, moveOrder);

        MoveResponse response = controller.createMove(gameId, request);

        assertThat(response).isNotNull();
        assertThat(response.getGameId()).isEqualTo(gameId);
        assertThat(response.getDestination()).isEqualTo(request.getDestination());
        assertThat(response.getOrder()).isEqualTo(moveOrder);
    }

    @Test
    public void listMoves() {
        final Long gameId = 1L;

        when(moveService.getMoves(gameId)).thenReturn(new ArrayList<>());

        List<MoveResponse> responseList = controller.listMoves(gameId);

        assertThat(responseList).isNotNull();
    }

    private MoveRequest createMoveRequest(final Long gameId, final int moveOrder) {
        Game game = new Game(new Player(), GameStatus.STARTED);
        ReflectionTestUtils.setField(game, "id", gameId);
        final Square destination = new Square();
        final Move move = new Move(game, null, destination, moveOrder);
        final SquareModel expectedDestination = new SquareModel(destination);
        PieceModel pawn = new Pawn(expectedDestination, "White");
        MoveRequest request = new MoveRequest(pawn, expectedDestination);

        when(moveService.createMove(gameId, request)).thenReturn(new MoveModel(move));

        return request;
    }
}