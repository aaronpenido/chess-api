package com.chessmasters.chessapi.controller;

import com.chessmasters.chessapi.entity.Game;
import com.chessmasters.chessapi.entity.Player;
import com.chessmasters.chessapi.request.GameRequest;
import com.chessmasters.chessapi.response.GameResponse;
import com.chessmasters.chessapi.service.GameService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static com.chessmasters.chessapi.enums.Color.WHITE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class GameControllerTest {

    @InjectMocks
    private GameController controller;
    @Mock
    private GameService service;

    @Test
    public void registerGame() {
        final Long playerId = 1L;
        GameRequest request = new GameRequest(playerId, null);
        Player player = new Player(WHITE, playerId, null);
        Game game = new Game(player);
        when(service.registerGame(request)).thenReturn(game);

        GameResponse response = controller.registerGame(request);

        assertThat(response).isNotNull();
    }

    @Test
    public void startGame() {
        final Long gameId = 1L;
        final Long playerId = 2L;
        Player player = new Player(WHITE, playerId, null);
        Game game = new Game(player);
        GameRequest request = new GameRequest(playerId, null);

        when(service.gameById(gameId)).thenReturn(game);
        when(service.startGame(gameId, request)).thenReturn(game);

        GameResponse response = controller.startGame(gameId, request);

        assertThat(response).isNotNull();
    }

    @Test
    public void movePiece() {
        final Long gameId = 1L;
        final Long playerId = 1L;
        Player player = new Player(WHITE, playerId, null);
        Game game = new Game(player);
        GameRequest request = new GameRequest(playerId, null);

        when(service.gameById(gameId)).thenReturn(game);
        when(service.movePiece(gameId, request)).thenReturn(game);
        when(service.save(game)).thenReturn(game);

        GameResponse response = controller.movePiece(gameId, request);

        assertThat(response).isNotNull();
    }
}