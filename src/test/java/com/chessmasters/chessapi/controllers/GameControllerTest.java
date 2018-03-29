package com.chessmasters.chessapi.controllers;

import com.chessmasters.chessapi.entities.GameEntity;
import com.chessmasters.chessapi.entities.PlayerEntity;
import com.chessmasters.chessapi.enums.GameStatus;
import com.chessmasters.chessapi.models.Game;
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
        Game gameModel = createGame(GameStatus.CREATED);

        when(gameService.createGame(request)).thenReturn(gameModel);

        GameResponse response = controller.createGame(request);

        assertThat(response).isNotNull();
        assertThat(response.getId()).isEqualTo(gameModel.getId());
        assertThat(response.getStatus()).isEqualTo(gameModel.getStatus());
        assertThat(response.getPlayerId()).isEqualTo(playerId);
    }

    @Test
    public void listGames() {
        Game gameModel = createGame(GameStatus.CREATED);
        when(gameService.getGames()).thenReturn(Collections.singletonList(gameModel));

        List<GameResponse> responseList = controller.listGames();

        assertThat(responseList).isNotEmpty();

        final GameResponse response = responseList.get(0);
        assertThat(response.getId()).isEqualTo(gameModel.getId());
        assertThat(response.getPlayerId()).isEqualTo(gameModel.getPlayer().getId());
        assertThat(response.getStatus()).isEqualTo(gameModel.getStatus());
    }

    @Test
    public void startGame() {
        final Long gameId = 1L;
        final Long playerId = 1L;
        GameRequest request = new GameRequest(playerId);
        Game game = createGame(GameStatus.STARTED);
        when(gameService.startGame(gameId, request)).thenReturn(game);

        GameResponse response = controller.startGame(gameId, request);

        assertThat(response).isNotNull();
        assertThat(response.getId()).isEqualTo(game.getId());
        assertThat(response.getStatus()).isEqualTo(game.getStatus());
        assertThat(response.getPlayerId()).isEqualTo(game.getPlayer().getId());
        assertThat(response.getPlayer2Id()).isEqualTo(game.getPlayer2().getId());
    }

    private Game createGame(GameStatus gameStatus) {
        final Long gameId = 1L;
        final Long playerId = 1L;
        final Long player2Id = 2L;

        PlayerEntity player = new PlayerEntity();
        PlayerEntity player2 = new PlayerEntity();

        GameEntity game = new GameEntity(player, gameStatus);
        game.setPlayer2(player2);

        ReflectionTestUtils.setField(player, "id", playerId);
        ReflectionTestUtils.setField(player2, "id", player2Id);
        ReflectionTestUtils.setField(game, "id", gameId);

        return new Game(game);
    }
}