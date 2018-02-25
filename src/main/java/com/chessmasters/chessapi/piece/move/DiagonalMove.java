package com.chessmasters.chessapi.piece.move;

import com.chessmasters.chessapi.Board;
import com.chessmasters.chessapi.Letter;
import com.chessmasters.chessapi.Square;
import com.chessmasters.chessapi.piece.Piece;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class DiagonalMove extends Move {

    public DiagonalMove(Board board, Square square) {
        super(board, square);
    }

    public DiagonalMove(Board board, Square square, boolean isOneSquarePerMove) {
        super(board, square, isOneSquarePerMove);
    }

    @Override
    public List<Square> path() {
        List<Square> path = new ArrayList<>();

        final Letter leftBorderLetter = Letter.A;
        final Letter rightBorderLetter = Letter.H;
        final int bottomBorderNumber = 1;
        final int topBorderNumber = 8;

        if(!square.getLetter().equals(leftBorderLetter)) {
            if(square.getNumber() != topBorderNumber) {
                path.addAll(leftAhead());
            }

            if(square.getNumber() != bottomBorderNumber) {
                path.addAll(leftBehind());
            }
        }

        if(!square.getLetter().equals(rightBorderLetter)) {
            if(square.getNumber() != topBorderNumber) {
                path.addAll(rightAhead());
            }

            if(square.getNumber() != bottomBorderNumber) {
                path.addAll(rightBehind());
            }
        }

        return path;
    }

    private List<Square> leftAhead() {
        List<Square> path = new ArrayList<>();
        Piece piece = board.getPieceFromSquare(square);
        int nextNumber = square.getNumber() + 1;

        if(isOneSquarePerMove) {
            Letter previousLetter = Letter.previousLetter(square.getLetter());
            Square previousSquare = new Square(previousLetter, nextNumber);
            Piece previousPiece = board.getPieceFromSquare(previousSquare);

            if(previousPiece == null ||
                    !previousPiece.getColor().equals(piece.getColor())) {

                path.add(previousSquare);
            }

            return path;
        }

        for (Letter letter : Letter.previousLetters(square.getLetter())) {
            Square actualSquare = new Square(letter, nextNumber);
            Piece actualPiece = board.getPieceFromSquare(actualSquare);
            nextNumber++;

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

    private List<Square> rightAhead() {
        List<Square> path = new ArrayList<>();
        Piece piece = board.getPieceFromSquare(square);
        int nextNumber = square.getNumber() + 1;

        if(isOneSquarePerMove) {
            Letter nextLetter = Letter.nextLetter(square.getLetter());
            Square nextSquare = new Square(nextLetter, nextNumber);
            Piece nextPiece = board.getPieceFromSquare(nextSquare);

            if(nextPiece == null ||
                    !nextPiece.getColor().equals(piece.getColor())) {

                path.add(nextSquare);
            }

            return path;
        }

        for (Letter letter : Letter.nextLetters(square.getLetter())) {
            Square actualSquare = new Square(letter, nextNumber);
            Piece actualPiece = board.getPieceFromSquare(actualSquare);
            nextNumber++;

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

    private List<Square> leftBehind() {
        List<Square> path = new ArrayList<>();
        Piece piece = board.getPieceFromSquare(square);
        int previousNumber = square.getNumber() - 1;

        if(isOneSquarePerMove) {
            Letter previousLetter = Letter.previousLetter(square.getLetter());
            Square previousSquare = new Square(previousLetter, previousNumber);
            Piece previousPiece = board.getPieceFromSquare(previousSquare);

            if(previousPiece == null ||
                    !previousPiece.getColor().equals(piece.getColor())) {

                path.add(previousSquare);
            }

            return path;
        }

        for (Letter letter : Letter.previousLetters(square.getLetter())) {
            if(previousNumber > 0) {
                Square actualSquare = new Square(letter, previousNumber);
                Piece actualPiece = board.getPieceFromSquare(actualSquare);
                previousNumber--;

                if(actualPiece == null) {
                    path.add(actualSquare);
                } else {
                    if(!actualPiece.getColor().equals(piece.getColor())) {
                        path.add(actualSquare);
                    }
                    break;
                }
            }
        }

        return path;
    }

    private List<Square> rightBehind() {
        List<Square> path = new ArrayList<>();
        Piece piece = board.getPieceFromSquare(square);
        int previousNumber = square.getNumber() - 1;

        if(isOneSquarePerMove) {
            Letter previousLetter = Letter.nextLetter(square.getLetter());
            Square previousSquare = new Square(previousLetter, previousNumber);
            Piece previousPiece = board.getPieceFromSquare(previousSquare);

            if(previousPiece == null ||
                    !previousPiece.getColor().equals(piece.getColor())) {

                path.add(previousSquare);
            }

            return path;
        }

        for (Letter letter : Letter.nextLetters(square.getLetter())) {
            if(previousNumber > 0) {
                Square actualSquare = new Square(letter, previousNumber);
                Piece actualPiece = board.getPieceFromSquare(actualSquare);
                previousNumber--;

                if(actualPiece == null) {
                    path.add(actualSquare);
                } else {
                    if(!actualPiece.getColor().equals(piece.getColor())) {
                        path.add(actualSquare);
                    }
                    break;
                }
            }
        }

        return path;
    }
}
