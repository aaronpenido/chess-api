package com.chessmasters.chessapi;

import com.chessmasters.chessapi.exception.InvalidMoveExcpetion;
import com.chessmasters.chessapi.piece.Pawn;
import com.chessmasters.chessapi.piece.Piece;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;

import static com.chessmasters.chessapi.Color.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class BoardTest {

    private List<Piece> pieces;
    private Board board;

    @Test
    public void throwInvalidMoveExceptionWhenPawnMovesToDiagonalSquareWithoutPieceToCapture() {
        Square from = new Square(Letter.A, 2);
        Square to = new Square(Letter.B, 3);
        pieces = new ArrayList<>();
        pieces.add(new Pawn(WHITE, from));
        board = new Board(pieces);

        assertThatThrownBy(() -> board.movePiece(from, to))
                .isInstanceOf(InvalidMoveExcpetion.class);
    }

    @Test
    public void pieceMovesLeavingPreviousSquareEmpty() {
        Square from = new Square(Letter.A, 2);
        Square to = new Square(Letter.A, 3);
        pieces = new ArrayList<>();
        pieces.add(new Pawn(WHITE, from));
        board = new Board(pieces);

        board.movePiece(from, to);

        Piece piece = getPieceFromSquare(from);

        assertThat(piece).isNull();
    }

    private Piece getPieceFromSquare(Square square) {
        return board.getPieces()
                    .stream()
                    .filter(p -> p != null)
                    .filter(p -> p.getSquare().equals(square))
                    .findFirst()
                    .orElse(null);
    }
}