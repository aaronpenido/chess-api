package com.chessmasters.chessapi.piece;

import com.chessmasters.chessapi.Board;
import com.chessmasters.chessapi.Letter;
import com.chessmasters.chessapi.Square;
import org.junit.Test;
import java.util.Collections;

import static com.chessmasters.chessapi.Color.*;
import static org.assertj.core.api.Assertions.assertThat;

public class QueenTest {

    @Test
    public void queenHasValidMoves() {
        Queen queen = new Queen(WHITE, new Square(Letter.D, 1));
        Board board = new Board(Collections.singletonList(queen));

        assertThat(queen.moves(board)).isNotEmpty();
    }
}