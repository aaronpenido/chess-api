package com.chessmasters.chessapi.piece;

import com.chessmasters.chessapi.Letter;
import com.chessmasters.chessapi.Square;
import org.junit.Test;

import static com.chessmasters.chessapi.Color.*;
import static org.assertj.core.api.Java6Assertions.assertThat;

public class BishopTest {

    @Test
    public void bishopHasValidMoves() {
        Bishop bishop = new Bishop(WHITE, new Square(Letter.D, 5));

        assertThat(bishop.moves()).isNotNull();
        assertThat(bishop.moves()).isNotEmpty();
    }
}