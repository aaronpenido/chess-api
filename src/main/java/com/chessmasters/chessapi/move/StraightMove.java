package com.chessmasters.chessapi.move;

import com.chessmasters.chessapi.Board;
import com.chessmasters.chessapi.Square;
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
        return pathBySquareFunction(Square::previousLetter);
    }

    private List<Square> rightPath() {
        return pathBySquareFunction(Square::nextLetter);
    }

    private List<Square> aheadPath() {
        return pathBySquareFunction(Square::nextNumber);
    }

    private List<Square> behindPath() {
        return pathBySquareFunction(Square::previousNumber);
    }
}
