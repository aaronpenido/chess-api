package com.chessmasters.chessapi.entity.piece;

import com.chessmasters.chessapi.model.Board;
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
public class KnightTest {

    @Mock
    Game game;

    @Test
    public void knightHasValidMoves() {
        Knight knight = new Knight(WHITE, new Square(Letter.A, 1));
        when(game.getPieces()).thenReturn(Collections.singletonList(knight));
        Board board = new Board(game);

        assertThat(knight.moves(board)).isNotEmpty();
    }
}