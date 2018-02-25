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

    public StraightMove(Board board, Square square, boolean isOneSquarePerMove) {
        super(board, square, isOneSquarePerMove);
    }

    private List<Square> oneSquarePerMove(final Square square) {
        List<Square> moves = new ArrayList<>();

        moves.addAll(oneSquareAhead(square));
        moves.addAll(oneSquareBehind(square));
        moves.addAll(oneSquareLeft(square));
        moves.addAll(oneSquareRight(square));

        return moves;
    }

    private List<Square> oneSquareAhead(final Square square) {
        final int topBorderNumber = 8;

        if(square.getNumber() != topBorderNumber) {
            final int nextNumber = square.getNumber() + 1;
            return Collections.singletonList(new Square(square.getLetter(), nextNumber));
        }

        return Collections.emptyList();
    }

    private List<Square> oneSquareBehind(final Square square) {
        final int downBorderNumber = 1;

        if(square.getNumber() != downBorderNumber) {
            final int previousNumber = square.getNumber() - 1;
            return Collections.singletonList(new Square(square.getLetter(), previousNumber));
        }

        return Collections.emptyList();
    }

    private List<Square> oneSquareLeft(final Square square) {
        final Letter leftBorderLetter = Letter.A;

        if(!square.getLetter().equals(leftBorderLetter)) {
            final Letter previousLetter = Letter.previousLetter(square.getLetter());
            return Collections.singletonList(new Square(previousLetter, square.getNumber()));
        }

        return Collections.emptyList();
    }

    private List<Square> oneSquareRight(final Square square) {
        final Letter rightBorderLetter = Letter.H;

        if(!square.getLetter().equals(rightBorderLetter)) {
            final Letter nextLetter = Letter.nextLetter(square.getLetter());
            return Collections.singletonList(new Square(nextLetter, square.getNumber()));
        }

        return Collections.emptyList();
    }

    @Override
    public List<Square> path() {
        List<Square> path = new ArrayList<>();

        if(isOneSquarePerMove) {
            return oneSquarePerMove(square);
        }

        path.addAll(leftPath());
        path.addAll(rightPath());
        path.addAll(aheadPath());
        path.addAll(behindPath());

        return path;
    }

    private List<Square> leftPath() {
        List<Square> path = new ArrayList<>();
        Piece piece = board.getPieceFromSquare(square);

        for (Letter letter : Letter.previousLetters(square.getLetter())) {
            Square leftSquare = new Square(letter, square.getNumber());
            Piece leftPiece = board.getPieceFromSquare(leftSquare);

            if(leftPiece == null) {
                path.add(leftSquare);
            } else {
                if(!leftPiece.getColor().equals(piece.getColor())) {
                    path.add(leftSquare);
                }
                break;
            }
        }

        return path;
    }

    private List<Square> rightPath() {
        List<Square> path = new ArrayList<>();
        Piece piece = board.getPieceFromSquare(square);

        for (Letter letter : Letter.nextLetters(square.getLetter())) {
            Square rightSquare = new Square(letter, square.getNumber());
            Piece rightPiece = board.getPieceFromSquare(rightSquare);

            if(rightPiece == null) {
                path.add(rightSquare);
            } else {
                if(!rightPiece.getColor().equals(piece.getColor())) {
                    path.add(rightSquare);
                }
                break;
            }
        }

        return path;
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
}
