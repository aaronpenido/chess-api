package com.chessmasters.chessapi.piece.move;

import com.chessmasters.chessapi.Square;

import java.util.List;

public abstract class Move {

    public abstract List<Square> moves();
    public abstract List<Square> path(Square destination);
}
