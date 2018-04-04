package com.chessmasters.chessapi.models;

import com.chessmasters.chessapi.entities.GameEntity;
import com.chessmasters.chessapi.entities.PieceEntity;
import com.chessmasters.chessapi.entities.PlayerEntity;
import com.chessmasters.chessapi.entities.SquareEntity;
import com.chessmasters.chessapi.enums.Color;
import com.chessmasters.chessapi.enums.GameStatus;
import org.junit.Test;

import java.util.Arrays;

import static com.chessmasters.chessapi.enums.Letter.A;
import static com.chessmasters.chessapi.enums.PieceType.PAWN;
import static org.assertj.core.api.Assertions.assertThat;

public class GameTest {

    @Test
    public void gameHasAllGameEntityValues() {
        GameEntity gameEntity = createGameEntity();

        Game game = new Game(gameEntity);

        assertThat(game.getId()).isEqualTo(gameEntity.getId());
        assertThat(game.getPlayer()).isNotNull();
        assertThat(game.getPlayer().getId()).isEqualTo(gameEntity.getPlayer().getId());
        assertThat(game.getPlayer().getName()).isEqualTo(gameEntity.getPlayer().getName());
        assertThat(game.getPlayer2()).isNotNull();
        assertThat(game.getPlayer2().getId()).isEqualTo(gameEntity.getPlayer2().getId());
        assertThat(game.getPlayer2().getName()).isEqualTo(gameEntity.getPlayer2().getName());
        assertThat(game.getStatus()).isEqualTo(gameEntity.getStatus());
        assertThat(game.getPieces()).isNotEmpty();
        assertThat(game.getPieces().get(0).getColor()).isEqualTo(gameEntity.getPieces().get(0).getColor());
        assertThat(game.getPieces().get(0).getType()).isEqualTo(gameEntity.getPieces().get(0).getType());
        assertThat(game.getPieces().get(0).getSquare().getNumber()).isEqualTo(gameEntity.getPieces().get(0).getSquare().getNumber());
        assertThat(game.getPieces().get(0).getSquare().getLetter()).isEqualTo(gameEntity.getPieces().get(0).getSquare().getLetter());
    }

    @Test
    public void startedGameHasStatusStarted() {
        GameEntity gameEntity = createGameEntity();
        Game game = new Game(gameEntity);

        game.start(gameEntity.getPlayer2());

        assertThat(game.getStatus()).isEqualTo(GameStatus.STARTED);
    }

    @Test
    public void startedGameHasNonNullPlayer2() {
        GameEntity gameEntity = createGameEntity();
        Game game = new Game(gameEntity);

        game.start(gameEntity.getPlayer2());

        assertThat(game.getPlayer2()).isNotNull();
    }

    @Test
    public void startedGameHasDifferentPlayersColors() {
        GameEntity gameEntity = createGameEntity();
        Game game = new Game(gameEntity);

        game.start(gameEntity.getPlayer2());

        assertThat(game.getPlayer().getColor()).isNotNull();
        assertThat(game.getPlayer2().getColor()).isNotNull();
        assertThat(game.getPlayer().getColor()).isNotEqualTo(game.getPlayer2().getColor());
    }

    @Test
    public void startGameInitializesPieces() {
        GameEntity gameEntity = createGameEntity();
        Game game = new Game(gameEntity);

        game.start(gameEntity.getPlayer2());

        assertThat(game.getPieces()).isNotEmpty();
    }

    private GameEntity createGameEntity() {
        final int squareNumber = 1;
        PlayerEntity player = new PlayerEntity();
        PlayerEntity player2 = new PlayerEntity();
        GameEntity gameEntity = new GameEntity(player, GameStatus.CREATED);
        gameEntity.setPlayer2(player2);
        SquareEntity squareEntity = new SquareEntity(squareNumber, A);
        PieceEntity piece = new PieceEntity(gameEntity, Color.WHITE, squareEntity, PAWN);
        gameEntity.getPieces().addAll(Arrays.asList(piece));

        return gameEntity;
    }
}