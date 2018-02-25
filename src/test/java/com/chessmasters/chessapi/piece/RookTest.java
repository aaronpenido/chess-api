package com.chessmasters.chessapi.piece;

import com.chessmasters.chessapi.Board;
import com.chessmasters.chessapi.Letter;
import com.chessmasters.chessapi.Square;
import org.junit.Test;

import java.util.Collections;
import java.util.List;

import static com.chessmasters.chessapi.Color.*;
import static org.assertj.core.api.Assertions.assertThat;

public class RookTest {

    @Test
    public void rookHasValidMoves() {
        Rook rook = new Rook(WHITE, new Square(Letter.A, 1));
        Board board = new Board(Collections.singletonList(rook));
        List<Square> path = rook.moves(board);

        assertThat(path).isNotNull();
        assertThat(path).isNotEmpty();
    }
}