package com.chessmasters.chessapi;

import com.chessmasters.chessapi.enums.Letter;
import com.chessmasters.chessapi.piece.*;
import org.junit.Before;
import org.junit.Test;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.chessmasters.chessapi.enums.Color.*;
import static org.assertj.core.api.Assertions.assertThat;

public class GameTest {

    private Game game;

    @Before
    public void setUp() {
        game = new Game(new Player());
        game.start();
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
    public void rooksAreOnA1H1A8AndH8Squares() {
        assertThat(game.getPieces()).contains(new Rook(WHITE, new Square(Letter.A, 1)));
        assertThat(game.getPieces()).contains(new Rook(WHITE, new Square(Letter.H, 1)));
        assertThat(game.getPieces()).contains(new Rook(BLACK, new Square(Letter.A, 8)));
        assertThat(game.getPieces()).contains(new Rook(BLACK, new Square(Letter.H, 8)));
    }

    @Test
    public void knightsAreOnB1G1B8AndG8Squares() {
        assertThat(game.getPieces()).contains(new Knight(WHITE, new Square(Letter.B, 1)));
        assertThat(game.getPieces()).contains(new Knight(WHITE, new Square(Letter.G, 1)));
        assertThat(game.getPieces()).contains(new Knight(BLACK, new Square(Letter.B, 8)));
        assertThat(game.getPieces()).contains(new Knight(BLACK, new Square(Letter.G, 8)));
    }

    @Test
    public void bishopsAreOnC1F1C8AndF8Squares() {
        assertThat(game.getPieces()).contains(new Bishop(WHITE, new Square(Letter.C, 1)));
        assertThat(game.getPieces()).contains(new Bishop(WHITE, new Square(Letter.F, 1)));
        assertThat(game.getPieces()).contains(new Bishop(BLACK, new Square(Letter.C, 8)));
        assertThat(game.getPieces()).contains(new Bishop(BLACK, new Square(Letter.F, 8)));
    }

    @Test
    public void queensAreOnD1AndD8Squares() {
        assertThat(game.getPieces()).contains(new Queen(WHITE, new Square(Letter.D, 1)));
        assertThat(game.getPieces()).contains(new Queen(BLACK, new Square(Letter.D, 8)));
    }

    @Test
    public void kingsAreOnE1AndE8Squares() {
        assertThat(game.getPieces()).contains(new King(WHITE, new Square(Letter.E, 8)));
        assertThat(game.getPieces()).contains(new King(BLACK, new Square(Letter.E, 8)));
    }

    @Test
    public void gameHasWhitePawnsInAllNumberTwoSquares() {
        List<Pawn> pawns = Arrays.stream(Letter.values())
                .map(letter -> new Square(letter, 2))
                .map(square -> new Pawn(WHITE, square))
                .collect(Collectors.toList());

        assertThat(game.getPieces()).contains(pawns.toArray(new Pawn[pawns.size()]));
    }

    @Test
    public void gameHasBlackPawnsInAllNumberSevenSquares() {
        List<Pawn> pawns = Arrays.stream(Letter.values())
                .map(letter -> new Square(letter, 7))
                .map(square -> new Pawn(BLACK, square))
                .collect(Collectors.toList());

        assertThat(game.getPieces()).contains(pawns.toArray(new Pawn[pawns.size()]));
    }

    @Test
    public void movePiece() {
        Square from = new Square(Letter.E, 2);
        Square destination = new Square(Letter.E, 4);

        game.movePiece(from, destination);

        assertThat(game.getPieces()).contains(new Pawn(WHITE, destination));
    }
}