package com.chessmasters.chessapi;

import java.util.ArrayList;
import java.util.List;

import static com.chessmasters.chessapi.Color.*;

public class Game {

    private Board board;
    private List<Piece> pieces;

    public Game(Board board) {
        this.board = board;

        pieces = new ArrayList<>();

        for (int i = 65; i <= 72; i++) {
            char letter = (char)i;
            pieces.add(new Piece(WHITE, new Square(letter, 1)));
            pieces.add(new Pawn(WHITE, new Square(letter, 2)));
            pieces.add(new Pawn(BLACK, new Square(letter, 7)));
            pieces.add(new Piece(BLACK, new Square(letter, 8)));
        }
    }

    public Board getBoard() {
        return board;
    }

    public List<Piece> getPieces() {
        return pieces;
    }
}
