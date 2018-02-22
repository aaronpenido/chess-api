package com.chessmasters.chessapi.piece;

import com.chessmasters.chessapi.Letter;
import com.chessmasters.chessapi.Square;
import com.chessmasters.chessapi.piece.Rook;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.chessmasters.chessapi.Color.*;
import static org.assertj.core.api.Java6Assertions.assertThat;

public class RookTest {

    @Test
    public void rookHasValidMoves() {
        Rook rook = new Rook(WHITE, new Square(Letter.A, 1));

        assertThat(rook.moves()).isNotNull();
        assertThat(rook.moves()).isNotEmpty();
    }
}