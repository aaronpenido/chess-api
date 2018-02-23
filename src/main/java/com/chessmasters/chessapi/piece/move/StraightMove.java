package com.chessmasters.chessapi.piece.move;

import com.chessmasters.chessapi.Letter;
import com.chessmasters.chessapi.Square;

import java.util.*;
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
            return oneSquarePerMove(square);
        }

        List<Square> moves = new ArrayList<>();

        moves.addAll(horizontalMoves(moves));
        moves.addAll(verticalMoves(moves));
        moves.removeAll(Collections.singleton(square));

        return moves;
    }

    private List<Square> horizontalMoves(List<Square> moves) {
        List<Square> squares = new ArrayList<>();

        Arrays.stream(Letter.values())
                .forEachOrdered(letter -> squares.addAll(
                        oneSquarePerMove(new Square(letter, square.getNumber())))
                );

        return squares;
    }

    private List<Square> verticalMoves(List<Square> moves) {
        List<Square> squares = new ArrayList<>();

        IntStream.range(1, 8)
                .forEach(number -> squares.addAll(
                        oneSquarePerMove(new Square(square.getLetter2(), number)))
                );

        return squares;
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
            return Collections.singletonList(new Square(square.getLetter2(), nextNumber));
        }

        return Collections.emptyList();
    }

    private List<Square> oneSquareBehind(final Square square) {
        final int downBorderNumber = 1;

        if(square.getNumber() != downBorderNumber) {
            final int previousNumber = square.getNumber() - 1;
            return Collections.singletonList(new Square(square.getLetter2(), previousNumber));
        }

        return Collections.emptyList();
    }

    private List<Square> oneSquareLeft(final Square square) {
        final Letter leftBorderLetter = Letter.A;

        if(!square.getLetter2().equals(leftBorderLetter)) {
            final Letter previousLetter = Letter.previousLetter(square.getLetter2());
            return Collections.singletonList(new Square(previousLetter, square.getNumber()));
        }

        return Collections.emptyList();
    }

    private List<Square> oneSquareRight(final Square square) {
        final Letter rightBorderLetter = Letter.H;

        if(!square.getLetter2().equals(rightBorderLetter)) {
            final Letter nextLetter = Letter.nextLetter(square.getLetter2());
            return Collections.singletonList(new Square(nextLetter, square.getNumber()));
        }

        return Collections.emptyList();
    }
}
