package com.chessmasters.chessapi.piece;

import com.chessmasters.chessapi.Color;
import com.chessmasters.chessapi.Square;
import com.chessmasters.chessapi.piece.Piece;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Rook extends Piece {

    public Rook(Color color, Square square) {
        super(color, square);
    }

    @Override
    public List<Square> moves() {
        List<Square> moves = new ArrayList<>();

        for (int letter = 65; letter <= 72; letter++) {
            moves.add(new Square((char)letter, square.getNumber()));
        }

        for (int i = 1; i <= 8; i++) {
            moves.add(new Square(square.getLetter(), i));
        }
        moves.removeAll(Arrays.asList(square));

        return moves;
    }
}