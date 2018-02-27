package com.chessmasters.chessapi.piece.move;

import com.chessmasters.chessapi.Board;
import com.chessmasters.chessapi.Square;

import java.util.ArrayList;
import java.util.List;

public class DiagonalMove extends Move {

    public DiagonalMove(Board board, Square square) {
        super(board, square);
    }

    @Override
    public List<Square> path() {
        List<Square> path = new ArrayList<>();

        path.addAll(leftAhead());
        path.addAll(rightAhead());
        path.addAll(leftBehind());
        path.addAll(rightBehind());

        return path;
    }

    private List<Square> leftAhead() {
        return pathBySquareFunction(s -> s.nextNumberAndPreviousLetter());
    }

    private List<Square> leftBehind() {
        return pathBySquareFunction(s -> s.previousNumberAndLetter());
    }

    private List<Square> rightAhead() {
        return pathBySquareFunction(s -> s.nextNumberAndLetter());
    }

    private List<Square> rightBehind() {
        return pathBySquareFunction(s -> s.previousNumberAndNextLetter());
    }
}
