package com.chessmasters.chessapi.services;

import com.chessmasters.chessapi.entities.GameEntity;
import com.chessmasters.chessapi.entities.PlayerEntity;
import com.chessmasters.chessapi.enums.Color;
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
        final Long playerId = 1L;
        GameRequest gameRequest = new GameRequest(playerId);
        final GameEntity gameEntity = createGameEntity(playerId);

        Game gameModel = service.createGame(gameRequest);

        assertThat(gameModel).isNotNull();
        assertThat(gameModel.getId()).isEqualTo(gameEntity.getId());
        assertThat(gameModel.getPlayerId()).isEqualTo(gameEntity.getPlayer().getId());
        assertThat(gameModel.getStatus()).isEqualTo(gameEntity.getStatus());
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

    @Test
    public void playersHaveDifferentColorsOnStartGame() {
        final Long gameId = 1L;
        final Long playerId = 1L;
        GameEntity gameEntity = createGameEntity(playerId);
        GameRequest gameRequest = new GameRequest(playerId);

        service.startGame(gameId, gameRequest);

        assertThat(gameEntity.getPlayer2()).isNotNull();
        assertThat(gameEntity.getPlayer().getColor()).isNotNull();
        assertThat(gameEntity.getPlayer2().getColor()).isNotNull();
        assertThat(gameEntity.getPlayer().getColor()).isNotEqualTo(gameEntity.getPlayer2().getColor());
    }

    private GameEntity createGameEntity(Long playerId) {
        PlayerEntity player = new PlayerEntity();
        player.setColor(Color.WHITE);

        GameEntity gameEntity = new GameEntity(player, GameStatus.CREATED);
        PlayerEntity player2 = new PlayerEntity();
        player2.setColor(Color.BLACK);
        gameEntity.setPlayer2(player2);

        when(playerService.getById(playerId)).thenReturn(player2);
        when(gameRepository.findOne(any(Long.class))).thenReturn(gameEntity);
        when(gameRepository.save(any(GameEntity.class))).thenReturn(gameEntity);

        return gameEntity;
    }
}