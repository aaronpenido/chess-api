package com.chessmasters.chessapi.controllers;

import com.chessmasters.chessapi.enums.GameStatus;
import com.chessmasters.chessapi.request.GameRequest;
import com.chessmasters.chessapi.response.GameResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class GameControllerTest {

    @InjectMocks
    private GameController controller;

    @Test
    public void newGameHasStatusCreated() {
        final Long playerId = 1L;
        GameRequest request = new GameRequest(playerId);

        GameResponse response = controller.createGame(request);

        assertThat(response).isNotNull();
        assertThat(response.getStatus()).isEqualTo(GameStatus.CREATED);
    }

    @Test
    public void listGames() {
        final Long gameId = 1L;
        GameStatus gameStatus = GameStatus.CREATED;

        List<GameResponse> responseList = controller.listGames();

        assertThat(responseList).isNotEmpty();
        assertThat(responseList.get(0).getId()).isEqualTo(gameId);
        assertThat(responseList.get(0).getStatus()).isEqualTo(gameStatus);
    }
}