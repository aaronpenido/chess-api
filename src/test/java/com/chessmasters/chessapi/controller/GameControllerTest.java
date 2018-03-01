package com.chessmasters.chessapi.controller;

import com.chessmasters.chessapi.Board;
import com.chessmasters.chessapi.Game;
import com.chessmasters.chessapi.Player;
import com.chessmasters.chessapi.request.GameRequest;
import com.chessmasters.chessapi.response.GameResponse;
import com.chessmasters.chessapi.service.GameService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class GameControllerTest {

    @InjectMocks
    private GameController controller;
    @Mock
    private GameService service;
    @Mock
    Board board;

    @Test
    public void registerGame() {
        final Long playerId = 1L;
        GameRequest request = new GameRequest(playerId);
        Player player = new Player(playerId, null);
        Game game = new Game(player, board);
        when(service.registerGame(request)).thenReturn(game);

        GameResponse response = controller.registerGame(request);

        assertThat(response).isNotNull();
    }

    @Test
    public void startGame() {
        final Long gameId = 1L;
        final Long playerId = 2L;
        Player player = new Player(playerId, null);
        Game game = new Game(player, board);
        GameRequest request = new GameRequest(playerId);

        when(service.gameById(gameId)).thenReturn(game);
        when(service.startGame(gameId, request)).thenReturn(game);

        GameResponse response = controller.startGame(gameId, request);

        assertThat(response).isNotNull();
    }
}