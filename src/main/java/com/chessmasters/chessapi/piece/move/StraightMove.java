package com.chessmasters.chessapi.piece.move;

import com.chessmasters.chessapi.Board;
import com.chessmasters.chessapi.Letter;
import com.chessmasters.chessapi.Square;
import com.chessmasters.chessapi.piece.Piece;
import java.util.*;

public class StraightMove extends Move {

    public StraightMove(Board board, Square square) {
        super(board, square);
    }

    @Override
    public List<Square> path() {
        List<Square> path = new ArrayList<>();

        path.addAll(leftPath());
        path.addAll(rightPath());
        path.addAll(aheadPath());
        path.addAll(behindPath());

        return path;
    }

    private List<Square> leftPath() {
        return horizontalSquaresPath(Letter.previousLetters(square.getLetter()));
    }

    private List<Square> rightPath() {
        return horizontalSquaresPath(Letter.nextLetters(square.getLetter()));
    }

    private List<Square> aheadPath() {
        List<Square> path = new ArrayList<>();
        Piece piece = board.getPieceFromSquare(square);

        for (int number = square.getNumber() + 1; number <= 8; number++) {
            Square squareAhead = new Square(square.getLetter(), number);
            Piece pieceAhead = board.getPieceFromSquare(squareAhead);

            if(pieceAhead == null) {
                path.add(squareAhead);
            } else {
                if(!pieceAhead.getColor().equals(piece.getColor())) {
                    path.add(squareAhead);
                }
                break;
            }
        }

        return path;
    }

    private List<Square> behindPath() {
        List<Square> path = new ArrayList<>();
        Piece piece = board.getPieceFromSquare(square);

        for (int number = square.getNumber() - 1; number >= 1; number--) {
            Square squareBehind = new Square(square.getLetter(), number);
            Piece pieceBehind = board.getPieceFromSquare(squareBehind);

            if(pieceBehind == null) {
                path.add(squareBehind);
            } else {
                if(!pieceBehind.getColor().equals(piece.getColor())) {
                    path.add(squareBehind);
                }
                break;
            }
        }

        return path;
    }

    private List<Square> horizontalSquaresPath(List<Letter> letters) {
        List<Square> path = new ArrayList<>();
        Piece piece = board.getPieceFromSquare(square);

        for (Letter letter : letters) {
            Square actualSquare = new Square(letter, square.getNumber());
            Piece actualPiece = board.getPieceFromSquare(actualSquare);

            if(actualPiece == null) {
                path.add(actualSquare);
            } else {
                if(!actualPiece.getColor().equals(piece.getColor())) {
                    path.add(actualSquare);
                }
                break;
            }
        }

        return path;
    }
}
