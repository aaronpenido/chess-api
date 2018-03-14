package com.chessmasters.chessapi.services;

import com.chessmasters.chessapi.entities.Game;
import com.chessmasters.chessapi.entities.Player;
import com.chessmasters.chessapi.enums.GameStatus;
import com.chessmasters.chessapi.models.GameModel;
import com.chessmasters.chessapi.repositories.GameRepository;
import com.chessmasters.chessapi.request.GameRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class GameServiceTest {

    @InjectMocks
    private GameService service;
    @Mock
    private GameRepository gameRepository;
    @Mock
    private PlayerService playerService;

    @Test
    public void createGame() {
        final Long playerId = 1L;
        GameRequest gameRequest = new GameRequest(playerId);

        GameModel game = service.createGame(gameRequest);

        assertThat(game).isNotNull();
    }

    @Test
    public void saveGameOnDatabase() {
        final Long playerId = 1L;
        GameRequest request = new GameRequest(playerId);

        service.createGame(request);
        verify(gameRepository).save(any(Game.class));
    }

    @Test
    public void getAllGamesFromDatabase() {
        service.getGames();
        verify(gameRepository).findAll();
    }

    @Test
    public void startGameUpdatesGameStatusToStarted() {
        final Long gameId = 1L;
        final Long playerId = 1L;
        GameRequest gameRequest = new GameRequest(playerId);
        Game game = new Game(new Player(), GameStatus.CREATED);
        when(gameRepository.findOne(any(Long.class))).thenReturn(game);
        when(gameRepository.save(any(Game.class))).thenReturn(game);

        GameModel gameModel = service.startGame(gameId, gameRequest);

        assertThat(gameModel).isNotNull();
        assertThat(gameModel.getStatus()).isEqualTo(GameStatus.STARTED);
    }
}