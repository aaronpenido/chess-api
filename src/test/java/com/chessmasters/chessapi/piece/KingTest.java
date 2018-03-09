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
public class KingTest {

    @Mock
    Game game;

    @Test
    public void kingHasValidMoves() {
        King king = new King(WHITE, new Square(Letter.E, 1));
        when(game.getPieces()).thenReturn(Collections.singletonList(king));
        Board board = new Board(game);

        assertThat(king.moves(board)).isNotEmpty();
    }
}