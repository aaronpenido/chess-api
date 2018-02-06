package com.chessmasters.chessapi.model;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class GameTest {

    @Test
    public void boardIsNotNull() {
        Game game = createGame();
        assertThat(game.getBoard()).isNotNull();
    }

    @Test
    public void whitePlayerIsNotNull() {
        Game game = createGame();
        assertThat(game.getWhitePlayer()).isNotNull();
    }

    @Test
    public void blackPlayerIsNotNull() {
        Game game = createGame();
        assertThat(game.getBlackPlayer()).isNotNull();
    }

    private Game createGame() {
        Player white = new Player();
        Player black = new Player();

        return new Game(white, black);
    }
}