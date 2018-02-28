package com.chessmasters.chessapi.piece;

import com.chessmasters.chessapi.Board;
import com.chessmasters.chessapi.Letter;
import com.chessmasters.chessapi.Square;
import org.junit.Test;
import java.util.Collections;

import static com.chessmasters.chessapi.Color.*;
import static org.assertj.core.api.Assertions.assertThat;

public class KnightTest {

    @Test
    public void knightHasValidMoves() {
        Knight knight = new Knight(WHITE, new Square(Letter.A, 1));
        Board board = new Board(Collections.singletonList(knight));

        assertThat(knight.moves(board)).isNotEmpty();
    }
}