package com.chessmasters.chessapi.models;

import com.chessmasters.chessapi.entities.*;
import com.chessmasters.chessapi.enums.Color;
import com.chessmasters.chessapi.enums.ErrorMessage;
import com.chessmasters.chessapi.enums.GameStatus;
import com.chessmasters.chessapi.exceptions.InvalidMoveException;
import org.junit.Test;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static com.chessmasters.chessapi.enums.Color.BLACK;
import static com.chessmasters.chessapi.enums.Color.WHITE;
import static com.chessmasters.chessapi.enums.Letter.A;
import static com.chessmasters.chessapi.enums.PieceType.PAWN;
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
        Color pieceColor = BLACK;
        Move move = createMove(gameEntity, pieceColor);

        assertThatThrownBy(() -> game.move(player, move))
                .isInstanceOf(InvalidMoveException.class)
                .hasMessage(String.valueOf(ErrorMessage.INVALID_MOVE_ITS_OPPONENTS_TURN));
    }

    @Test
    public void throwInvalidMoveExceptionWhenNextMoveIsFromRepeatedColor() {
        final GameEntity gameEntity = createGameEntity();
        SquareEntity destination = new SquareEntity();
        PieceEntity pieceEntity = new PieceEntity(gameEntity, WHITE, destination, PAWN);
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
        Color pieceColor = BLACK;
        Move move = createMove(gameEntity, pieceColor);

        assertThatThrownBy(() -> game.move(player, move))
                .isInstanceOf(InvalidMoveException.class)
                .hasMessage(String.valueOf(ErrorMessage.INVALID_MOVE_ITS_OPPONENTS_PIECE));
    }

    @Test
    public void moveOrderIsGeneratedWhenMovingPiece() {
        final int expectedMoveOrder = 2;
        GameEntity gameEntity = createGameEntity();
        List<MoveEntity> previousMoves = createPreviousMove(gameEntity);
        ReflectionTestUtils.setField(gameEntity, "moves", previousMoves);
        Game game = new Game(gameEntity);
        Player player = createPlayer(BLACK);
        Color pieceColor = BLACK;
        Move move = createMove(gameEntity, pieceColor);

        Move returnedMove = game.move(player, move);

        assertThat(returnedMove.getOrder()).isEqualTo(expectedMoveOrder);
    }

    @Test
    public void pieceSquareIsUpdatedWhenMovingPiece() {
        GameEntity gameEntity = createGameEntity();
        Game game = new Game(gameEntity);
        Player player = createPlayer(WHITE);
        Color pieceColor = WHITE;
        Move move = createMove(gameEntity, pieceColor);

        Move returnedMove = game.move(player, move);

        assertThat(returnedMove.getPiece()).isNotNull();
        assertThat(returnedMove.getPiece().getSquare()).isEqualTo(returnedMove.getDestination());
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

    private Player createPlayer(Color color) {
        PlayerEntity playerEntity = new PlayerEntity();
        playerEntity.setColor(color);
        return new Player(playerEntity);
    }

    private Move createMove(GameEntity gameEntity, Color pieceColor) {
        SquareEntity origin = new SquareEntity(1, A);
        SquareEntity destination = new SquareEntity(2, A);
        PieceEntity pieceEntity = new PieceEntity(gameEntity, pieceColor, origin, PAWN);
        MoveEntity moveEntity = new MoveEntity(gameEntity, pieceEntity, destination);
        return new Move(moveEntity);
    }

    private List<MoveEntity> createPreviousMove(GameEntity gameEntity) {
        final int previousMoveOrder = 1;
        PieceEntity previousMovePiece = new PieceEntity(gameEntity, WHITE, new SquareEntity(), PAWN);
        MoveEntity previousMoveEntity = new MoveEntity(gameEntity, previousMovePiece, new SquareEntity());
        ReflectionTestUtils.setField(previousMoveEntity, "moveOrder", previousMoveOrder);

        return Arrays.asList(previousMoveEntity);
    }
}