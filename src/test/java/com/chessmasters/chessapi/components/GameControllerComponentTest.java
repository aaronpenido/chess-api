package com.chessmasters.chessapi.components;

import com.chessmasters.chessapi.entities.Game;
import com.chessmasters.chessapi.enums.GameStatus;
import com.chessmasters.chessapi.repositories.GameRepository;
import com.chessmasters.chessapi.request.GameRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static org.hamcrest.CoreMatchers.is;

@RunWith(SpringRunner.class)
public class GameControllerComponentTest extends BaseComponentTest {

    @Autowired
    private GameRepository gameRepository;

    @Test
    public void createGame() {
        final int expectedId = 1;
        final GameStatus expectedStatus = GameStatus.CREATED;
        final Long playerId = 1L;

        GameRequest gameRequest = new GameRequest(playerId);

        given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(gameRequest)
        .post("/games").then()
                .statusCode(HttpStatus.CREATED.value())
                .body("id", is(expectedId))
                .body("status", is(String.valueOf(expectedStatus)));

    }

    @Test
    public void getGames() {
        final GameStatus expectedStatus = GameStatus.CREATED;
        Game game = new Game(expectedStatus);
        gameRepository.save(game);

        given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
        .get("/games").then()
                .statusCode(HttpStatus.OK.value());

    }
}
