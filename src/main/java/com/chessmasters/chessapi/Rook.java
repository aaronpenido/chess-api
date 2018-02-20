package com.chessmasters.chessapi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Rook {

    private Color color;
    private Square square;

    public Rook(Color color, Square square) {
        this.color = color;
        this.square = square;
    }

    public Color getColor() {
        return color;
    }

    public Square getSquare() {
        return square;
    }

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
