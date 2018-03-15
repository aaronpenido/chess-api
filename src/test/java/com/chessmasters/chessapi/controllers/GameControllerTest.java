package com.chessmasters.chessapi.controllers;

import com.chessmasters.chessapi.entities.Game;
import com.chessmasters.chessapi.entities.Player;
import com.chessmasters.chessapi.enums.GameStatus;
import com.chessmasters.chessapi.models.GameModel;
import com.chessmasters.chessapi.request.GameRequest;
import com.chessmasters.chessapi.response.GameResponse;
import com.chessmasters.chessapi.services.GameService;
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
public class GameControllerTest {

    @InjectMocks
    private GameController controller;
    @Mock
    private GameService gameService;

    @Test
    public void createGame() {
        final Long playerId = 1L;
        GameRequest request = new GameRequest(playerId);
        GameModel gameModel = new GameModel(new Game(new Player(), GameStatus.CREATED));

        when(gameService.createGame(request)).thenReturn(gameModel);

        GameResponse response = controller.createGame(request);

        assertThat(response).isNotNull();
    }

    @Test
    public void listGames() {
        when(gameService.getGames()).thenReturn(new ArrayList<>());

        List<GameResponse> responseList = controller.listGames();

        assertThat(responseList).isNotNull();
    }

    @Test
    public void startGame() {
        final Long gameId = 1L;
        final Long playerId = 1L;
        GameRequest request = new GameRequest(playerId);
        GameModel gameModel = new GameModel(new Game(new Player(), GameStatus.CREATED));
        when(gameService.startGame(gameId, request)).thenReturn(gameModel);

        GameResponse response = controller.startGame(gameId, request);

        assertThat(response).isNotNull();
    }
}