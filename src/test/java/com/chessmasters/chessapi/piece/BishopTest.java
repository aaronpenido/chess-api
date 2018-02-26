package com.chessmasters.chessapi.piece;

import com.chessmasters.chessapi.Board;
import com.chessmasters.chessapi.Letter;
import com.chessmasters.chessapi.Square;
import org.junit.Test;
import java.util.Collections;

import static com.chessmasters.chessapi.Color.*;
import static org.assertj.core.api.Assertions.assertThat;

public class BishopTest {

    @Test
    public void bishopHasValidMoves() {
        Bishop bishop = new Bishop(WHITE, new Square(Letter.D, 5));
        Board board = new Board(Collections.singletonList(bishop));

        assertThat(bishop.moves(board)).isNotEmpty();
    }
}