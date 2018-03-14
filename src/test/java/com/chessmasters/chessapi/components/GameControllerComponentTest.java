package com.chessmasters.chessapi.components;

import com.chessmasters.chessapi.enums.GameStatus;
import com.chessmasters.chessapi.request.GameRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static org.hamcrest.CoreMatchers.is;

@RunWith(SpringRunner.class)
public class GameControllerComponentTest extends BaseComponentTest {

    @Test
    public void registerGame() {
        final Long playerId = 1L;
        final String expectedStatus = String.valueOf(GameStatus.CREATED);

        GameRequest gameRequest = new GameRequest(playerId);

        given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(gameRequest)
        .post("/games").then()
                .statusCode(HttpStatus.CREATED.value())
                .body("status", is(expectedStatus));

    }
}
