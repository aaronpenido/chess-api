package com.chessmasters.chessapi.components;

import com.chessmasters.chessapi.entities.PlayerEntity;
import com.chessmasters.chessapi.repositories.PlayerRepository;
import com.chessmasters.chessapi.request.GameRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;

@RunWith(SpringRunner.class)
public class PlayerControllerComponentTest extends BaseComponentTest {

    @Autowired
    private PlayerRepository playerRepository;

    @Test
    public void createPlayer() {
        final Long playerId = 1L;
        GameRequest gameRequest = new GameRequest(playerId);

        given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(gameRequest)
         .post("/players").then()
                .statusCode(HttpStatus.CREATED.value());

    }

    @Test
    public void getPlayers() {
        final String name = "Player name";
        playerRepository.save(new PlayerEntity(name));

        given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .get("/players").then()
                .statusCode(HttpStatus.OK.value());

    }
}
