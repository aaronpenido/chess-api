package com.chessmasters.chessapi.services;

import com.chessmasters.chessapi.entities.GameEntity;
import com.chessmasters.chessapi.entities.PlayerEntity;
import com.chessmasters.chessapi.enums.GameStatus;
import com.chessmasters.chessapi.exceptions.GameNotFoundException;
import com.chessmasters.chessapi.exceptions.GameStartedException;
import com.chessmasters.chessapi.models.Game;
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
        PlayerEntity player = new PlayerEntity();
        ReflectionTestUtils.setField(player, "id", playerId);
        final GameEntity game = new GameEntity(player, GameStatus.CREATED);
        ReflectionTestUtils.setField(game, "id", gameId);
        GameRequest gameRequest = new GameRequest(playerId);

        when(playerService.getById(playerId)).thenReturn(player);
        when(gameRepository.save(any(GameEntity.class))).thenReturn(game);

        Game gameModel = service.createGame(gameRequest);

        assertThat(gameModel).isNotNull();
        assertThat(gameModel.getId()).isEqualTo(game.getId());
        assertThat(gameModel.getPlayerId()).isEqualTo(game.getPlayer().getId());
        assertThat(gameModel.getStatus()).isEqualTo(game.getStatus());
    }

    @Test
    public void saveGameOnDatabase() {
        final Long playerId = 1L;
        GameRequest request = new GameRequest(playerId);
        PlayerEntity player = new PlayerEntity();

        when(playerService.getById(playerId)).thenReturn(player);
        when(gameRepository.save(any(GameEntity.class))).thenReturn(new GameEntity(player, GameStatus.CREATED));

        service.createGame(request);

        verify(gameRepository).save(any(GameEntity.class));
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
        PlayerEntity player = new PlayerEntity();
        GameRequest gameRequest = new GameRequest(playerId);
        GameEntity game = new GameEntity(player, GameStatus.CREATED);

        when(playerService.getById(playerId)).thenReturn(player);
        when(gameRepository.findOne(any(Long.class))).thenReturn(game);
        when(gameRepository.save(any(GameEntity.class))).thenReturn(game);

        Game gameModel = service.startGame(gameId, gameRequest);

        assertThat(gameModel).isNotNull();
        assertThat(gameModel.getStatus()).isEqualTo(GameStatus.STARTED);
    }

    @Test
    public void throwRuntimeExceptionWhenErrorOccurredOnSaveGame() {
        Long playerId = 1L;
        GameRequest request = new GameRequest(playerId);

        when(gameRepository.save(any(GameEntity.class))).thenReturn(null);

        assertThatThrownBy(() -> service.createGame(request))
                .isInstanceOf(RuntimeException.class);
    }

    @Test
    public void throwGameNotFoundExceptionWhenTryingToStartNonExistentGame() {
        final Long gameId = 1L;
        Long playerId = 1L;
        GameRequest request = new GameRequest(playerId);

        when(gameRepository.findOne(any(Long.class))).thenReturn(null);

        assertThatThrownBy(() -> service.startGame(gameId, request))
                .isInstanceOf(GameNotFoundException.class);
    }

    @Test
    public void throwGameIsStartedExceptionWhenTryingToStartAgain() {
        final Long gameId = 1L;
        Long playerId = 1L;
        GameRequest request = new GameRequest(playerId);
        GameEntity game = new GameEntity(new PlayerEntity(), GameStatus.STARTED);
        ReflectionTestUtils.setField(game, "id", gameId);
        when(playerService.getById(any(Long.class))).thenReturn(new PlayerEntity());
        when(gameRepository.findOne(any(Long.class))).thenReturn(game);

        assertThatThrownBy(() -> service.startGame(gameId, request))
                .isInstanceOf(GameStartedException.class);
    }
}