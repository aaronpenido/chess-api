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

public class MoveTest {

    @Test
    public void throwInvalidMoveExceptionWhenFirstMoveIsNotFromWhitePlayer() {
        Color color = BLACK;
        GameEntity gameEntity = createGameEntity();
        PlayerEntity playerEntity = new PlayerEntity();
        playerEntity.setColor(color);
        Move move = createMove(gameEntity, color);

        assertThatThrownBy(() -> move.movePiece(playerEntity))
                .isInstanceOf(InvalidMoveException.class)
                .hasMessage(String.valueOf(ErrorMessage.INVALID_MOVE_ITS_OPPONENTS_TURN));
    }

    @Test
    public void throwInvalidMoveExceptionWhenNextMoveIsFromRepeatedColor() {
        Color color = WHITE;
        GameEntity gameEntity = createGameEntity();
        SquareEntity destination = new SquareEntity();
        PieceEntity pieceEntity = new PieceEntity(gameEntity, color, destination, PAWN);
        MoveEntity previousMove = new MoveEntity(gameEntity, pieceEntity, destination);
        ReflectionTestUtils.setField(gameEntity, "moves", Collections.singletonList(previousMove));
        PlayerEntity playerEntity = new PlayerEntity();
        playerEntity.setColor(color);
        MoveEntity moveEntity = new MoveEntity(gameEntity, pieceEntity, destination);
        Move move = new Move(moveEntity);

        assertThatThrownBy(() -> move.movePiece(playerEntity))
                .isInstanceOf(InvalidMoveException.class)
                .hasMessage(String.valueOf(ErrorMessage.INVALID_MOVE_ITS_OPPONENTS_TURN));
    }

    @Test
    public void throwInvalidMoveExceptionWhenPlayerTriesToMoveOpponentsPiece() {
        final GameEntity gameEntity = createGameEntity();
        Color color = BLACK;
        PlayerEntity playerEntity = new PlayerEntity();
        playerEntity.setColor(color.opposite());
        Move move = createMove(gameEntity, color);

        assertThatThrownBy(() -> move.movePiece(playerEntity))
                .isInstanceOf(InvalidMoveException.class)
                .hasMessage(String.valueOf(ErrorMessage.INVALID_MOVE_ITS_OPPONENTS_PIECE));
    }

    @Test
    public void moveOrderIsGeneratedWhenMovingPiece() {
        final int expectedMoveOrder = 2;
        GameEntity gameEntity = createGameEntity();
        List<MoveEntity> previousMoves = createPreviousMove(gameEntity);
        ReflectionTestUtils.setField(gameEntity, "moves", previousMoves);
        Color color = BLACK;
        PlayerEntity playerEntity = new PlayerEntity();
        playerEntity.setColor(color);
        Move move = createMove(gameEntity, color);

        move.movePiece(playerEntity);

        assertThat(move.getOrder()).isEqualTo(expectedMoveOrder);
    }

    @Test
    public void pieceSquareIsUpdatedWhenMovingPiece() {
        GameEntity gameEntity = createGameEntity();
        Color color = WHITE;
        PlayerEntity playerEntity = new PlayerEntity();
        playerEntity.setColor(color);
        Move move = createMove(gameEntity, color);

        move.movePiece(playerEntity);

        assertThat(move.getPiece()).isNotNull();
        assertThat(move.getPiece().getSquare()).isEqualTo(move.getDestination());
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