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
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Collections;
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
        GameModel gameModel = createGameModel(GameStatus.CREATED);

        when(gameService.createGame(request)).thenReturn(gameModel);

        GameResponse response = controller.createGame(request);

        assertThat(response).isNotNull();
        assertThat(response.getId()).isEqualTo(gameModel.getId());
        assertThat(response.getStatus()).isEqualTo(gameModel.getStatus());
        assertThat(response.getPlayerId()).isEqualTo(playerId);
        assertThat(response.getPlayer2Id()).isNull();
    }

    @Test
    public void listGames() {
        GameModel gameModel = createGameModel(GameStatus.CREATED);
        when(gameService.getGames()).thenReturn(Collections.singletonList(gameModel));

        List<GameResponse> responseList = controller.listGames();

        assertThat(responseList).isNotEmpty();

        final GameResponse response = responseList.get(0);
        assertThat(response.getId()).isEqualTo(gameModel.getId());
        assertThat(response.getPlayerId()).isEqualTo(gameModel.getPlayerId());
        assertThat(response.getStatus()).isEqualTo(gameModel.getStatus());
        assertThat(response.getPlayer2Id()).isNull();
    }

    @Test
    public void startGame() {
        final Long gameId = 1L;
        final Long playerId = 1L;
        GameRequest request = new GameRequest(playerId);
        GameModel gameModel = createGameModel(GameStatus.STARTED);
        ReflectionTestUtils.setField(gameModel, "player2Id", playerId);
        when(gameService.startGame(gameId, request)).thenReturn(gameModel);

        GameResponse response = controller.startGame(gameId, request);

        assertThat(response).isNotNull();
        assertThat(response.getId()).isEqualTo(gameModel.getId());
        assertThat(response.getStatus()).isEqualTo(gameModel.getStatus());
        assertThat(response.getPlayerId()).isEqualTo(playerId);
        assertThat(response.getPlayer2Id()).isEqualTo(gameModel.getPlayerId());
    }

    private GameModel createGameModel(GameStatus gameStatus) {
        final Long gameId = 1L;
        final Long playerId = 1L;

        final Player player = new Player();
        final Game game = new Game(player, gameStatus);
        ReflectionTestUtils.setField(player, "id", playerId);
        ReflectionTestUtils.setField(game, "id", gameId);
        return new GameModel(game);
    }
}