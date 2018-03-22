package com.chessmasters.chessapi.components;

import com.chessmasters.chessapi.entities.GameEntity;
import com.chessmasters.chessapi.entities.PlayerEntity;
import com.chessmasters.chessapi.enums.GameStatus;
import com.chessmasters.chessapi.repositories.GameRepository;
import com.chessmasters.chessapi.repositories.PlayerRepository;
import com.chessmasters.chessapi.request.GameRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.is;

@RunWith(SpringRunner.class)
public class GameControllerComponentTest extends BaseComponentTest {

    @Autowired
    private PlayerRepository playerRepository;
    @Autowired
    private GameRepository gameRepository;

    @Test
    public void createGame() {
        final GameStatus expectedStatus = GameStatus.CREATED;
        PlayerEntity player = new PlayerEntity("Player name");
        final Long playerId = playerRepository.save(player).getId();
        GameRequest gameRequest = new GameRequest(playerId);

        given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(gameRequest)
        .post("/games").then()
                .statusCode(HttpStatus.CREATED.value())
                .body("id", is(playerId.intValue()))
                .body("status", is(String.valueOf(expectedStatus)));

    }

    @Test
    public void getGames() {
        final GameStatus expectedStatus = GameStatus.CREATED;
        GameEntity game = createGameFromDatabase();
        int expectedId = game.getId().intValue();

        given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
        .get("/games").then()
                .statusCode(HttpStatus.OK.value())
                .body("status", hasItem(String.valueOf(expectedStatus)))
                .body("id", hasItem(expectedId));

    }

    @Test
    public void startGame() {
        final GameStatus expectedStatus = GameStatus.STARTED;
        final GameEntity game = createGameFromDatabase();
        final String path = String.format("/games/%s/start", game.getId());

        GameRequest gameRequest = new GameRequest(game.getPlayer().getId());

        given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(gameRequest)
        .put(path).then()
                .statusCode(HttpStatus.OK.value())
                .body("status", is(String.valueOf(expectedStatus)));
    }

    private GameEntity createGameFromDatabase() {
        final PlayerEntity player = playerRepository.save(new PlayerEntity("Player name"));
        return gameRepository.save(new GameEntity(player, GameStatus.CREATED));
    }
}
