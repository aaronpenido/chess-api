package com.chessmasters.chessapi.service;

import com.chessmasters.chessapi.Board;
import com.chessmasters.chessapi.Game;
import com.chessmasters.chessapi.Player;
import com.chessmasters.chessapi.repository.GameRepository;
import com.chessmasters.chessapi.repository.PlayerRepository;
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
    private GameRepository repository;
    @Mock
    private PlayerRepository playerRepository;
    @Mock
    private Board board;

    @Test
    public void registerGame() {
        final Long playerId = 1L;
        Player player = new Player(playerId, null);
        GameRequest gameRequest = new GameRequest(playerId);

        when(playerRepository.findOne(any(Long.class))).thenReturn(player);
        when(repository.save(any(Game.class))).thenReturn(new Game(player, board));

        Game game = service.registerGame(gameRequest);

        assertThat(game).isNotNull();
    }

    @Test
    public void saveGameOnDatabase() {
        final Long playerId = 1L;
        GameRequest request = new GameRequest(playerId);

        service.registerGame(request);
        verify(repository).save(any(Game.class));
    }

    @Test
    public void gameContainsPlayerWithGivenPlayerId() {
        final Long playerId = 1L;
        GameRequest gameRequest = new GameRequest(playerId);
        Player player = new Player(playerId, null);
        when(playerRepository.findOne(any(Long.class))).thenReturn(new Player(playerId, null));
        when(repository.save(any(Game.class))).thenReturn(new Game(player, board));

        Game game = service.registerGame(gameRequest);

        assertThat(game.getPlayer().getId()).isEqualTo(playerId);
    }
}