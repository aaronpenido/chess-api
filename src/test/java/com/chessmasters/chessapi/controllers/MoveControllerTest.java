package com.chessmasters.chessapi.controllers;

import com.chessmasters.chessapi.entities.Game;
import com.chessmasters.chessapi.entities.Move;
import com.chessmasters.chessapi.models.MoveModel;
import com.chessmasters.chessapi.response.MoveResponse;
import com.chessmasters.chessapi.services.MoveService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

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
        final Game game = new Game();

        when(moveService.createMove(gameId)).thenReturn(new MoveModel(new Move(game, moveOrder)));

        MoveResponse response = controller.createMove(gameId);

        assertThat(response).isNotNull();
    }

    @Test
    public void listMoves() {
        final Long gameId = 1L;

        when(moveService.getMoves(gameId)).thenReturn(new ArrayList<>());

        List<MoveResponse> responseList = controller.listMoves(gameId);

        assertThat(responseList).isNotNull();
    }
}