package com.chessmasters.chessapi.piece;

import com.chessmasters.chessapi.Letter;
import com.chessmasters.chessapi.Square;
import org.junit.Test;

import static com.chessmasters.chessapi.Color.*;
import static org.assertj.core.api.Assertions.assertThat;

public class RookTest {

    @Test
    public void rookHasValidMoves() {
        Rook rook = new Rook(WHITE, new Square(Letter.A, 1));

        assertThat(rook.moves()).isNotNull();
        assertThat(rook.moves()).isNotEmpty();
    }
}