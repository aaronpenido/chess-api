package com.chessmasters.chessapi;

import com.chessmasters.chessapi.piece.Rook;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.chessmasters.chessapi.Color.*;
import static org.assertj.core.api.Java6Assertions.assertThat;

public class RookTest {

    private Rook rook;
    private char letter;
    private int number;

    @Before
    public void setUp() {
        letter = (char)(new Random().nextInt(5) + 66);
        number = new Random().nextInt(7) + 1;
        rook = new Rook(WHITE, new Square(letter, number));
    }

    @Test
    public void rookHasValidMoves() {
        assertThat(rook.moves()).isNotNull();
        assertThat(rook.moves()).isNotEmpty();
    }

    @Test
    public void rookMovesLeft() {
        List<Square> squares = new ArrayList<>();

        for (int i = 65; i < (int)letter; i++) {
            squares.add(new Square((char)i, number));
        }

        assertThat(rook.moves()).contains(squares.toArray(new Square[squares.size()]));
    }

    @Test
    public void rookMovesRight() {
        List<Square> squares = new ArrayList<>();

        for (int i = 72; i > (int)letter; i--) {
            squares.add(new Square((char)i, number));
        }

        assertThat(rook.moves()).contains(squares.toArray(new Square[squares.size()]));
    }

    @Test
    public void rookMovesAhead() {
        List<Square> squares = new ArrayList<>();

        for (int i = number; i <= 8; i++) {
            Square square = new Square(letter, i);

            if(!square.equals(rook.getSquare())) {
                squares.add(square);
            }
        }

        assertThat(rook.moves()).contains(squares.toArray(new Square[squares.size()]));
    }

    @Test
    public void rookMovesBehind() {
        List<Square> squares = new ArrayList<>();

        for (int i = number; i >= 1; i--) {
            Square square = new Square(letter, i);

            if(!square.equals(rook.getSquare())) {
                squares.add(square);
            }
        }

        assertThat(rook.moves()).contains(squares.toArray(new Square[squares.size()]));
    }

    @Test
    public void movesDoesNotContainActualSquare() {
        assertThat(rook.moves()).doesNotContain(rook.getSquare());
    }
}