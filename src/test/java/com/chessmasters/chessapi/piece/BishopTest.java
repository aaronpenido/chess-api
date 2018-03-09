package com.chessmasters.chessapi.piece;

import com.chessmasters.chessapi.Board;
import com.chessmasters.chessapi.Game;
import com.chessmasters.chessapi.enums.Letter;
import com.chessmasters.chessapi.Square;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Collections;

import static com.chessmasters.chessapi.enums.Color.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BishopTest {

    @Mock
    Game game;

    @Test
    public void bishopHasValidMoves() {
        Bishop bishop = new Bishop(WHITE, new Square(Letter.D, 5));
        when(game.getPieces()).thenReturn(Collections.singletonList(bishop));
        Board board = new Board(game);

        assertThat(bishop.moves(board)).isNotEmpty();
    }
}