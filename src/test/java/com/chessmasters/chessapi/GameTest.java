package com.chessmasters.chessapi;

import com.chessmasters.chessapi.piece.Pawn;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static com.chessmasters.chessapi.Color.*;
import static org.assertj.core.api.Java6Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class GameTest {

    @Mock
    private Board board;
    private Game game;

    @Before
    public void setUp() {
        game = new Game(board);
    }

    @Test
    public void gameHasABoard() {
        assertThat(game.getBoard()).isNotNull();
    }

    @Test
    public void gameHasThirtyTwoPieces() {
        assertThat(game.getPieces()).isNotNull();
        assertThat(game.getPieces().size()).isEqualTo(32);
    }

    @Test
    public void gameHasPieceInA1Square() {
        assertThat(game.getPieces()).contains(new Pawn(WHITE, new Square('A', 1)));
    }

    @Test
    public void gameHasWhitePawnInA2Square() {
        assertThat(game.getPieces()).contains(new Pawn(WHITE, new Square('A', 2)));
    }

    @Test
    public void gameHasBlackPawnInF7Square() {
        assertThat(game.getPieces()).contains(new Pawn(BLACK, new Square('F', 7)));
    }

    @Test
    public void gameHasPieceInG8Square() {
        assertThat(game.getPieces()).contains(new Pawn(WHITE, new Square('G', 8)));
    }

    @Test
    public void gameDoesNotContainPieceInE5Square() {
        assertThat(game.getPieces()).doesNotContain(new Pawn(WHITE, new Square('E', 5)));
    }
}