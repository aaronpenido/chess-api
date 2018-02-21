package com.chessmasters.chessapi.piece.move;

import com.chessmasters.chessapi.Square;

import java.util.ArrayList;
import java.util.List;

public class DiagonalMove {

    private Square square;

    public DiagonalMove(Square square) {
        this.square = square;
    }

    public List<Square> moves() {
        List<Square> moves = new ArrayList<>();

        moves.addAll(leftDiagonal());
        moves.addAll(rightDiagonal());

        return moves;
    }

    private List<Square> rightDiagonal() {
        List<Square> diagonals = new ArrayList<>();

        if(square.getLetter() != 'H') {
            int numberAhead = square.getNumber() + 1;
            int numberBehind = square.getNumber() - 1;

            for (int letter = ((int)square.getLetter() + 1); letter <= 72; letter++) {
                if(square.getNumber() < 8 && numberAhead <= 8) {
                    diagonals.add(new Square((char)letter, numberAhead));
                    numberAhead++;
                }

                if(square.getNumber() > 1 && numberBehind >= 1) {
                    diagonals.add(new Square((char)letter, numberBehind));
                    numberBehind--;
                }
            }
        }

        return diagonals;
    }

    private List<Square> leftDiagonal() {
        List<Square> diagonals = new ArrayList<>();

        if(square.getLetter() != 'A') {
            int numberAhead = square.getNumber() + 1;
            int numberBehind = square.getNumber() - 1;

            for (int letter = ((int)square.getLetter() - 1); letter >= 65; letter--) {
                if(square.getNumber() < 8 && numberAhead <= 8) {
                    diagonals.add(new Square((char)letter, numberAhead));
                    numberAhead++;
                }

                if(square.getNumber() > 1 && numberBehind >= 1) {
                    diagonals.add(new Square((char)letter, numberBehind));
                    numberBehind--;
                }
            }
        }

        return diagonals;
    }
}
