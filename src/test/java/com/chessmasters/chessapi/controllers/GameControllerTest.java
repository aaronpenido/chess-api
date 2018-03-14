package com.chessmasters.chessapi.controllers;

import com.chessmasters.chessapi.enums.GameStatus;
import com.chessmasters.chessapi.request.GameRequest;
import com.chessmasters.chessapi.response.GameResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

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
}