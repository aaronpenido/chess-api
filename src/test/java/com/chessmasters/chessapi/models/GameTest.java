package com.chessmasters.chessapi.models;

import com.chessmasters.chessapi.entities.*;
import com.chessmasters.chessapi.enums.Color;
import com.chessmasters.chessapi.enums.ErrorMessage;
import com.chessmasters.chessapi.enums.GameStatus;
import com.chessmasters.chessapi.enums.Letter;
import com.chessmasters.chessapi.exceptions.InvalidMoveException;
import org.junit.Test;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Arrays;
import java.util.Collections;

import static com.chessmasters.chessapi.enums.Color.BLACK;
import static com.chessmasters.chessapi.enums.Color.WHITE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

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

    @Test
    public void throwInvalidMoveExceptionWhenFirstMoveIsNotFromWhitePlayer() {
        final GameEntity gameEntity = createGameEntity();
        Game game = new Game(gameEntity);
        Player player = createPlayer(BLACK);
        Move move = createMove(gameEntity);

        assertThatThrownBy(() -> game.move(player, move))
                .isInstanceOf(InvalidMoveException.class)
                .hasMessage(String.valueOf(ErrorMessage.INVALID_MOVE_ITS_OPPONENTS_TURN));
    }

    @Test
    public void throwInvalidMoveExceptionWhenNextMoveIsFromRepeatedColor() {
        final GameEntity gameEntity = createGameEntity();
        SquareEntity destination = new SquareEntity();
        PieceEntity pieceEntity = new PieceEntity(gameEntity, WHITE, destination, "Pawn");
        MoveEntity previousMove = new MoveEntity(gameEntity, pieceEntity, destination);
        ReflectionTestUtils.setField(gameEntity, "moves", Collections.singletonList(previousMove));
        Game game = new Game(gameEntity);
        Player player = createPlayer(WHITE);
        MoveEntity moveEntity = new MoveEntity(gameEntity, pieceEntity, destination);
        Move move = new Move(moveEntity);

        assertThatThrownBy(() -> game.move(player, move))
                .isInstanceOf(InvalidMoveException.class)
                .hasMessage(String.valueOf(ErrorMessage.INVALID_MOVE_ITS_OPPONENTS_TURN));
    }

    @Test
    public void throwInvalidMoveExceptionWhenPlayerTriesToMoveOpponentsPiece() {
        final GameEntity gameEntity = createGameEntity();
        Game game = new Game(gameEntity);
        Player player = createPlayer(WHITE);
        Move move = createMove(gameEntity);

        assertThatThrownBy(() -> game.move(player, move))
                .isInstanceOf(InvalidMoveException.class)
                .hasMessage(String.valueOf(ErrorMessage.INVALID_MOVE_ITS_OPPONENTS_PIECE));
    }

    private GameEntity createGameEntity() {
        final int squareNumber = 1;
        PlayerEntity player = new PlayerEntity();
        PlayerEntity player2 = new PlayerEntity();
        GameEntity gameEntity = new GameEntity(player, GameStatus.CREATED);
        gameEntity.setPlayer2(player2);
        SquareEntity squareEntity = new SquareEntity(squareNumber, Letter.A);
        PieceEntity piece = new PieceEntity(gameEntity, Color.WHITE, squareEntity, "Pawn");
        gameEntity.getPieces().addAll(Arrays.asList(piece));

        return gameEntity;
    }

    private Player createPlayer(Color color) {
        PlayerEntity playerEntity = new PlayerEntity();
        playerEntity.setColor(color);
        return new Player(playerEntity);
    }

    private Move createMove(GameEntity gameEntity) {
        SquareEntity destination = new SquareEntity();
        PieceEntity pieceEntity = new PieceEntity(gameEntity, BLACK, destination, "Pawn");
        MoveEntity moveEntity = new MoveEntity(gameEntity, pieceEntity, destination);
        return new Move(moveEntity);
    }
}