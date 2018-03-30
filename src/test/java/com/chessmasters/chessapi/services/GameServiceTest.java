package com.chessmasters.chessapi.services;

import com.chessmasters.chessapi.entities.GameEntity;
import com.chessmasters.chessapi.entities.PlayerEntity;
import com.chessmasters.chessapi.enums.Color;
import com.chessmasters.chessapi.enums.ErrorMessage;
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

import java.util.Arrays;
import java.util.List;

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
        final GameEntity gameEntity = createGameEntity();
        mockObjects(gameEntity, gameEntity.getPlayer());

        Game game = service.createGame(gameRequest);

        assertThat(game).isNotNull();
        assertThat(game.getPlayer()).isNotNull();
    }

    @Test
    public void createGameSavesOnDatabase() {
        final Long playerId = 1L;
        GameRequest request = new GameRequest(playerId);
        PlayerEntity player = new PlayerEntity();

        when(playerService.getById(playerId)).thenReturn(player);
        when(gameRepository.save(any(GameEntity.class))).thenReturn(new GameEntity(player, GameStatus.CREATED));

        service.createGame(request);

        verify(gameRepository).save(any(GameEntity.class));
    }

    @Test
    public void throwRuntimeExceptionWhenErrorOccurredOnSaveGame() {
        Long playerId = 1L;
        GameRequest request = new GameRequest(playerId);

        when(gameRepository.save(any(GameEntity.class))).thenReturn(null);

        assertThatThrownBy(() -> service.createGame(request))
                .isInstanceOf(RuntimeException.class)
                .hasMessage(String.valueOf(ErrorMessage.CREATE_GAME_ERROR));
    }

    @Test
    public void throwGameIsStartedExceptionWhenTryingToStartAgain() {
        final Long gameId = 1L;
        Long playerId = 1L;
        GameRequest request = new GameRequest(playerId);
        GameEntity gameEntity = new GameEntity(new PlayerEntity(), GameStatus.STARTED);
        ReflectionTestUtils.setField(gameEntity, "id", gameId);

        when(playerService.getById(any(Long.class))).thenReturn(new PlayerEntity());
        when(gameRepository.findOne(any(Long.class))).thenReturn(gameEntity);

        assertThatThrownBy(() -> service.startGame(gameId, request))
                .isInstanceOf(GameStartedException.class);
    }

    @Test
    public void startGameSavesOnDatabase() {
        final Long gameId = 1L;
        final Long playerId = 1L;
        GameRequest request = new GameRequest(playerId);
        PlayerEntity playerEntity = new PlayerEntity();
        GameEntity gameEntity = createGameEntity();

        mockObjects(gameEntity, playerEntity);

        service.startGame(gameId, request);

        verify(gameRepository).save(any(GameEntity.class));
    }

    @Test
    public void getByIdReturnsGameEntity() {
        final Long gameId = 1L;
        GameEntity gameEntity = createGameEntity();

        when(gameRepository.findOne(any(Long.class))).thenReturn(gameEntity);

        GameEntity returnedGameEntity = service.getById(gameId);

        assertThat(returnedGameEntity).isEqualTo(gameEntity);
    }

    @Test
    public void throwGameNotFoundExceptionWhenTryingToFindNonExistentGame() {
        final Long gameId = 1L;

        when(gameRepository.findOne(any(Long.class))).thenReturn(null);

        assertThatThrownBy(() -> service.getById(gameId))
                .isInstanceOf(GameNotFoundException.class);
    }

    @Test
    public void getAllGamesReturnsValidGameList() {
        final GameEntity gameEntity = createGameEntity();
        List<GameEntity> gameEntities = Arrays.asList(gameEntity);

        when(gameRepository.findAll()).thenReturn(gameEntities);

        List<Game> games = service.getGames();

        assertThat(games).isNotNull();
        assertThat(games.size()).isEqualTo(gameEntities.size());
        assertThat(games.get(0)).isNotNull();
        assertThat(games.get(0).getId()).isEqualTo(gameEntities.get(0).getId());
    }

    @Test
    public void getAllGamesFromDatabase() {
        service.getGames();
        verify(gameRepository).findAll();
    }

    private GameEntity createGameEntity() {
        PlayerEntity player = new PlayerEntity();
        player.setColor(Color.WHITE);

        GameEntity gameEntity = new GameEntity(player, GameStatus.CREATED);
        PlayerEntity player2 = new PlayerEntity();
        player2.setColor(Color.BLACK);
        gameEntity.setPlayer2(player2);

        return gameEntity;
    }

    private void mockObjects(GameEntity gameEntity, PlayerEntity playerEntity) {
        when(playerService.getById(any(Long.class))).thenReturn(playerEntity);
        when(gameRepository.findOne(any(Long.class))).thenReturn(gameEntity);
        when(gameRepository.save(any(GameEntity.class))).thenReturn(gameEntity);
    }
}