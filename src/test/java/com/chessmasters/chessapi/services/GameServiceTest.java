package com.chessmasters.chessapi.services;

import com.chessmasters.chessapi.entities.Game;
import com.chessmasters.chessapi.entities.Player;
import com.chessmasters.chessapi.enums.GameStatus;
import com.chessmasters.chessapi.exceptions.GameNotFoundException;
import com.chessmasters.chessapi.exceptions.GameStartedException;
import com.chessmasters.chessapi.models.GameModel;
import com.chessmasters.chessapi.repositories.GameRepository;
import com.chessmasters.chessapi.request.GameRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.util.ReflectionTestUtils;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
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
        final Long gameId = 1L;
        final Long playerId = 1L;
        Player player = new Player();
        ReflectionTestUtils.setField(player, "id", playerId);
        final Game game = new Game(player, GameStatus.CREATED);
        ReflectionTestUtils.setField(game, "id", gameId);
        GameRequest gameRequest = new GameRequest(playerId);

        when(playerService.getById(playerId)).thenReturn(player);
        when(gameRepository.save(any(Game.class))).thenReturn(game);

        GameModel gameModel = service.createGame(gameRequest);

        assertThat(gameModel).isNotNull();
        assertThat(gameModel.getId()).isEqualTo(game.getId());
        assertThat(gameModel.getPlayerId()).isEqualTo(game.getPlayer().getId());
        assertThat(gameModel.getStatus()).isEqualTo(game.getStatus());
    }

    @Test
    public void saveGameOnDatabase() {
        final Long playerId = 1L;
        GameRequest request = new GameRequest(playerId);
        Player player = new Player();

        when(playerService.getById(playerId)).thenReturn(player);
        when(gameRepository.save(any(Game.class))).thenReturn(new Game(player, GameStatus.CREATED));

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
        Player player = new Player();
        GameRequest gameRequest = new GameRequest(playerId);
        Game game = new Game(player, GameStatus.CREATED);

        when(playerService.getById(playerId)).thenReturn(player);
        when(gameRepository.findOne(any(Long.class))).thenReturn(game);
        when(gameRepository.save(any(Game.class))).thenReturn(game);

        GameModel gameModel = service.startGame(gameId, gameRequest);

        assertThat(gameModel).isNotNull();
        assertThat(gameModel.getStatus()).isEqualTo(GameStatus.STARTED);
    }

    @Test
    public void throwRuntimeExceptionWhenErrorOccurredOnSaveGame() {
        GameRequest request = new GameRequest();

        when(gameRepository.save(any(Game.class))).thenReturn(null);

        assertThatThrownBy(() -> service.createGame(request))
                .isInstanceOf(RuntimeException.class);
    }

    @Test
    public void throwGameNotFoundExceptionWhenTryingToStartNonExistentGame() {
        final Long gameId = 1L;
        GameRequest request = new GameRequest();

        when(gameRepository.findOne(any(Long.class))).thenReturn(null);

        assertThatThrownBy(() -> service.startGame(gameId, request))
                .isInstanceOf(GameNotFoundException.class);
    }

    @Test
    public void throwGameIsStartedExceptionWhenTryingToStartAgain() {
        final Long gameId = 1L;
        GameRequest request = new GameRequest();
        Game game = new Game(new Player(), GameStatus.STARTED);
        ReflectionTestUtils.setField(game, "id", gameId);
        when(playerService.getById(any(Long.class))).thenReturn(new Player());
        when(gameRepository.findOne(any(Long.class))).thenReturn(game);

        assertThatThrownBy(() -> service.startGame(gameId, request))
                .isInstanceOf(GameStartedException.class);
    }
}