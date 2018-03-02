package com.chessmasters.chessapi.service;

import com.chessmasters.chessapi.Game;
import com.chessmasters.chessapi.Player;
import com.chessmasters.chessapi.Square;
import com.chessmasters.chessapi.enums.Letter;
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

    @Test
    public void registerGame() {
        final Long playerId = 1L;
        Player player = new Player(playerId, null);
        GameRequest gameRequest = new GameRequest(playerId);

        when(playerRepository.findOne(any(Long.class))).thenReturn(player);
        when(repository.save(any(Game.class))).thenReturn(new Game(player));

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
        when(repository.save(any(Game.class))).thenReturn(new Game(player));

        Game game = service.registerGame(gameRequest);

        assertThat(game.getPlayer().getId()).isEqualTo(playerId);
    }

    @Test
    public void startGame() {
        final Long playerId = 1L;
        final Long player2Id = 2L;
        Player player = new Player(playerId, null);
        Player player2 = new Player(player2Id, null);
        Game game = new Game(player);
        game.setPlayer2(player2);
        GameRequest gameRequest = new GameRequest(player2Id);

        when(playerRepository.findOne(any(Long.class))).thenReturn(player);
        when(repository.findOne(any(Long.class))).thenReturn(game);
        when(repository.save(any(Game.class))).thenReturn(game);

        Game startedGame = service.startGame(player2Id, gameRequest);

        assertThat(startedGame).isNotNull();
        assertThat(startedGame.getPlayer2()).isNotNull();
        assertThat(startedGame.getPieces()).isNotNull();
    }

    @Test
    public void movePiece() {
        final Long playerId = 1L;
        Player player = new Player(playerId, null);
        Game game = new Game(player);
        Square from = new Square(Letter.E, 2);
        Square destination = new Square(Letter.E, 3);
        GameRequest gameRequest = new GameRequest(from, destination);

        when(playerRepository.findOne(any(Long.class))).thenReturn(player);
        when(repository.findOne(any(Long.class))).thenReturn(game);
        when(repository.save(any(Game.class))).thenReturn(game);

        game = service.movePiece(game.getId(), gameRequest);

        assertThat(game).isNotNull();
    }
}