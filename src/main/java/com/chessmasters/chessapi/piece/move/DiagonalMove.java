package com.chessmasters.chessapi.piece.move;

import com.chessmasters.chessapi.Board;
import com.chessmasters.chessapi.Letter;
import com.chessmasters.chessapi.Square;
import com.chessmasters.chessapi.piece.Piece;

import java.util.ArrayList;
import java.util.List;

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
        if(isOneSquarePerMove) {
            int nextNumber = square.getNumber() + 1;
            return oneSquareLeft(nextNumber);
        }

        return squaresAheadPath(Letter.previousLetters(square.getLetter()));
    }

    private List<Square> leftBehind() {
        if(isOneSquarePerMove) {
            int previousNumber = square.getNumber() - 1;
            return oneSquareLeft(previousNumber);
        }

        return squaresBehindPath(Letter.previousLetters(square.getLetter()));
    }

    private List<Square> rightAhead() {
        if(isOneSquarePerMove) {
            int nextNumber = square.getNumber() + 1;
            return oneSquareRight(nextNumber);
        }

        return squaresAheadPath(Letter.nextLetters(square.getLetter()));
    }

    private List<Square> rightBehind() {
        if(isOneSquarePerMove) {
            int previousNumber = square.getNumber() - 1;
            return oneSquareRight(previousNumber);
        }

        return squaresBehindPath(Letter.nextLetters(square.getLetter()));
    }

    private List<Square> squaresAheadPath(List<Letter> letters) {
        List<Square> path = new ArrayList<>();
        Piece piece = board.getPieceFromSquare(square);
        int nextNumber = square.getNumber() + 1;

        for (Letter letter : letters) {
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

    private List<Square> squaresBehindPath(final List<Letter> letters) {
        List<Square> path = new ArrayList<>();
        Piece piece = board.getPieceFromSquare(square);
        int previousNumber = square.getNumber() - 1;

        for (Letter letter : letters) {
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

    private List<Square> oneSquareLeft(final int number) {
        final Letter previousLetter = Letter.previousLetter(square.getLetter());
        return oneSquareIfIsAllowedToMove(number, previousLetter);
    }

    private List<Square> oneSquareRight(final int number) {
        final Letter nextLetter = Letter.nextLetter(square.getLetter());
        return oneSquareIfIsAllowedToMove(number, nextLetter);
    }

    private List<Square> oneSquareIfIsAllowedToMove(final int number, final Letter letter) {
        final List<Square> path = new ArrayList<>();
        final Piece piece = board.getPieceFromSquare(square);
        final Square actualSquare = new Square(letter, number);
        final Piece actualPiece = board.getPieceFromSquare(actualSquare);
        final boolean squareIsEmpty = actualPiece == null;
        final boolean actualPieceCanBeCaptured = (actualPiece != null &&
                !actualPiece.getColor().equals(piece.getColor()));

        if(squareIsEmpty || actualPieceCanBeCaptured) {
            path.add(actualSquare);
        }

        return path;
    }
}
