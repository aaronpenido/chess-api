package com.chessmasters.chessapi.service;

import com.chessmasters.chessapi.entity.Game;
import com.chessmasters.chessapi.entity.Player;
import com.chessmasters.chessapi.entity.Square;
import com.chessmasters.chessapi.entity.piece.Pawn;
import com.chessmasters.chessapi.enums.Letter;
import com.chessmasters.chessapi.exception.GameNotFoundException;
import com.chessmasters.chessapi.exception.PlayerNotFoundException;
import com.chessmasters.chessapi.model.GameMove;
import com.chessmasters.chessapi.repository.GameRepository;
import com.chessmasters.chessapi.repository.PlayerRepository;
import com.chessmasters.chessapi.request.GameRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;

import static com.chessmasters.chessapi.enums.Color.WHITE;
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
    private GameRepository repository;
    @Mock
    private PlayerRepository playerRepository;
    @Mock
    private Game game;

    @Test
    public void registerGame() {
        final Long playerId = 1L;
        Player player = new Player(WHITE, playerId, null);
        GameRequest gameRequest = new GameRequest(playerId, null);

        when(playerRepository.findOne(any(Long.class))).thenReturn(player);
        when(repository.save(any(Game.class))).thenReturn(new Game(player));

        Game game = service.registerGame(gameRequest);

        assertThat(game).isNotNull();
    }

    @Test
    public void saveGameOnDatabase() {
        final Long playerId = 1L;
        GameRequest request = new GameRequest(playerId, null);

        when(playerRepository.findOne(any(Long.class))).thenReturn(new Player());
        service.registerGame(request);
        verify(repository).save(any(Game.class));
    }

    @Test
    public void gameContainsPlayerWithGivenPlayerId() {
        final Long playerId = 1L;
        GameRequest gameRequest = new GameRequest(playerId, null);
        Player player = new Player(WHITE, playerId, null);
        when(playerRepository.findOne(any(Long.class))).thenReturn(player);
        when(repository.save(any(Game.class))).thenReturn(new Game(player));

        Game registeredGame = service.registerGame(gameRequest);

        assertThat(registeredGame.getPlayer().getId()).isEqualTo(playerId);
    }

    @Test
    public void startGame() {
        final Long gameId = 1L;
        final Long player2Id = 2L;
        Player player2 = new Player(WHITE, player2Id, null);
        GameRequest gameRequest = new GameRequest(player2Id, null);

        when(playerRepository.findOne(any(Long.class))).thenReturn(player2);
        when(repository.findOne(any(Long.class))).thenReturn(game);
        when(repository.save(any(Game.class))).thenReturn(game);
        when(game.getPlayer2()).thenReturn(player2);

        Game startedGame = service.startGame(gameId, gameRequest);

        assertThat(startedGame).isNotNull();
        assertThat(startedGame.getPlayer2()).isNotNull();
        assertThat(startedGame.getPieces()).isNotNull();
    }

    @Test
    public void movePiece() {
        final Long playerId = 1L;
        Player player = new Player(WHITE, playerId, null);
        Square origin = new Square(Letter.E, 2);
        Square destination = new Square(Letter.E, 3);
        GameRequest gameRequest = new GameRequest(playerId, new GameMove(origin, destination));

        when(playerRepository.findOne(any(Long.class))).thenReturn(player);
        when(game.getPieces()).thenReturn(Arrays.asList(new Pawn(WHITE, origin)));
        when(repository.findOne(any(Long.class))).thenReturn(game);
        when(repository.save(any(Game.class))).thenReturn(game);

        game = service.movePiece(game.getId(), gameRequest);

        assertThat(game).isNotNull();
    }

    @Test
    public void throwPlayerNotFoundExceptionWhenTryingToRegisterGameWithNonExistentPlayer() {
        final Long playerId = 1L;
        GameRequest request = new GameRequest(playerId, null);

        when(playerRepository.findOne(any(Long.class))).thenReturn(null);

        assertThatThrownBy(() -> service.registerGame(request))
                .isInstanceOf(PlayerNotFoundException.class);
    }

    @Test
    public void throwPlayerNotFoundExceptionWhenTryingToStartGameWithNonExistentPlayer() {
        final Long playerId = 1L;
        final Long gameId = 1L;
        GameRequest request = new GameRequest(playerId, null);

        when(playerRepository.findOne(any(Long.class))).thenReturn(null);
        when(repository.findOne(any(Long.class))).thenReturn(game);

        assertThatThrownBy(() -> service.startGame(gameId, request))
                .isInstanceOf(PlayerNotFoundException.class);
    }

    @Test
    public void throwGameNotFoundExceptionWhenTryingToStartNonExistentGame() {
        final Long playerId = 1L;
        final Long gameId = 1L;
        GameRequest request = new GameRequest(playerId, null);

        when(playerRepository.findOne(any(Long.class))).thenReturn(new Player());
        when(repository.findOne(any(Long.class))).thenReturn(null);

        assertThatThrownBy(() -> service.startGame(gameId, request))
                .isInstanceOf(GameNotFoundException.class);
    }

    @Test
    public void throwGameNotFoundExceptionWhenTryingToMovePieceOnNonExistentGame() {
        final Long gameId = 1L;
        final Long playerId = 1L;
        Square origin = new Square(Letter.E, 2);
        Square destination = new Square(Letter.E, 3);
        GameRequest request = new GameRequest(playerId, new GameMove(origin, destination));

        assertThatThrownBy(() -> service.movePiece(gameId, request))
                .isInstanceOf(GameNotFoundException.class);
    }
}