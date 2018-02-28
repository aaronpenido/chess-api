package com.chessmasters.chessapi.piece;

import com.chessmasters.chessapi.Board;
import com.chessmasters.chessapi.Letter;
import com.chessmasters.chessapi.Square;
import org.junit.Test;
import java.util.Collections;

import static com.chessmasters.chessapi.Color.*;
import static org.assertj.core.api.Assertions.assertThat;

public class PawnTest {

    private Pawn pawn;
    private Board board;
    
    @Test
    public void pawnHasValidMoves() {
        pawn = new Pawn(WHITE, new Square(Letter.A, 1));
        board = new Board(Collections.singletonList(pawn));

        assertThat(pawn.moves(board)).isNotEmpty();
    }
}