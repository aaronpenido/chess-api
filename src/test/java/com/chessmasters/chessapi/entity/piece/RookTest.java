package com.chessmasters.chessapi.entity.piece;

import com.chessmasters.chessapi.Board;
import com.chessmasters.chessapi.entity.Game;
import com.chessmasters.chessapi.enums.Letter;
import com.chessmasters.chessapi.entity.Square;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import java.util.Collections;

import static com.chessmasters.chessapi.enums.Color.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class RookTest {

    @Mock
    Game game;

    @Test
    public void rookHasValidMoves() {
        Rook rook = new Rook(WHITE, new Square(Letter.A, 1));
        when(game.getPieces()).thenReturn(Collections.singletonList(rook));
        Board board = new Board(game);

        assertThat(rook.moves(board)).isNotEmpty();
    }
}