package com.chessmasters.chessapi.piece.move;

import com.chessmasters.chessapi.Letter;
import com.chessmasters.chessapi.Square;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.IntStream;

public class StraightMove {

    private Square square;
    private boolean isOneSquarePerMove;

    public StraightMove(Square square) {
        this.square = square;
    }

    public StraightMove(Square square, boolean isOneSquarePerMove) {
        this.square = square;
        this.isOneSquarePerMove = isOneSquarePerMove;
    }

    public List<Square> moves() {
        if(isOneSquarePerMove) {
            return oneSquarePerMove(square.getLetter2(), square.getNumber());
        }

        List<Square> moves = new ArrayList<>();

        moves.addAll(horizontalMoves(moves));
        moves.addAll(verticalMoves(moves));
        moves.removeAll(Arrays.asList(square));

        return moves;
    }

    private List<Square> horizontalMoves(List<Square> moves) {
        List<Square> squares = new ArrayList<>();

        Arrays.stream(Letter.values())
                .forEachOrdered(letter -> squares.addAll(
                        oneSquarePerMove(letter, square.getNumber()))
                );

        return squares;
    }

    private List<Square> verticalMoves(List<Square> moves) {
        List<Square> squares = new ArrayList<>();

        IntStream.range(1, 8)
                .forEach(number -> squares.addAll(
                        oneSquarePerMove(square.getLetter2(), number))
                );

        return squares;
    }

    private List<Square> oneSquarePerMove(final Letter letter, final int number) {
        List<Square> moves = new ArrayList<>();

        moves.addAll(oneSquareAhead(letter, number));
        moves.addAll(oneSquareBehind(letter, number));
        moves.addAll(oneSquareLeft(letter, number));
        moves.addAll(oneSquareRight(letter, number));

        return moves;
    }

    private List<Square> oneSquareAhead(final Letter letter, final int number) {
        List<Square> moves = new ArrayList<>();
        final int topBorderNumber = 8;

        if(number != topBorderNumber) {
            final int nextNumber = number + 1;
            Square ahead = new Square(letter, nextNumber);
            moves.add(ahead);
        }

        return moves;
    }

    private List<Square> oneSquareBehind(final Letter letter, final int number) {
        List<Square> moves = new ArrayList<>();
        final int downBorderNumber = 1;

        if(number != downBorderNumber) {
            final int previousNumber = number - 1;
            Square behind = new Square(letter, previousNumber);
            moves.add(behind);
        }

        return moves;
    }

    private List<Square> oneSquareLeft(final Letter letter, final int number) {
        List<Square> moves = new ArrayList<>();
        final Letter leftBorderLetter = Letter.A;

        if(!letter.equals(leftBorderLetter)) {
            Square left = new Square(Letter.previousLetter(letter), number);
            moves.add(left);
        }

        return moves;
    }

    private List<Square> oneSquareRight(final Letter letter, final int number) {
        List<Square> moves = new ArrayList<>();
        final Letter rightBorderLetter = Letter.H;

        if(!letter.equals(rightBorderLetter)) {
            Square right = new Square(Letter.nextLetter(letter), number);
            moves.add(right);
        }

        return moves;
    }
}
