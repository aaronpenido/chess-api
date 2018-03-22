package com.chessmasters.chessapi.components;

import com.chessmasters.chessapi.entities.Game;
import com.chessmasters.chessapi.entities.Piece;
import com.chessmasters.chessapi.entities.Player;
import com.chessmasters.chessapi.entities.Square;
import com.chessmasters.chessapi.enums.GameStatus;
import com.chessmasters.chessapi.enums.Letter;
import com.chessmasters.chessapi.models.Pawn;
import com.chessmasters.chessapi.models.PieceModel;
import com.chessmasters.chessapi.models.SquareModel;
import com.chessmasters.chessapi.repositories.GameRepository;
import com.chessmasters.chessapi.repositories.PlayerRepository;
import com.chessmasters.chessapi.request.MoveRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static org.hamcrest.CoreMatchers.is;

@RunWith(SpringRunner.class)
public class MoveControllerComponentTest extends BaseComponentTest {

    @Autowired
    private PlayerRepository playerRepository;
    @Autowired
    private GameRepository gameRepository;

    @Test
    public void createMove() {
        final Square destination = new Square(1, Letter.A);
        final SquareModel expectedDestination = new SquareModel(destination);
        final Piece piece = new Piece("White", destination, "Pawn");
        PieceModel pawn = new Pawn(piece);
        final MoveRequest moveRequest = new MoveRequest(pawn, expectedDestination);
        final int expectedOrder = 1;
        final String playerName = "Player name";
        final Player player = playerRepository.save(new Player(playerName));
        final Game game = gameRepository.save(new Game(player, GameStatus.STARTED));
        final int gameId = game.getId().intValue();
        final String path = String.format("/games/%s/moves", gameId);

        given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(moveRequest)
        .post(path).then()
                .statusCode(HttpStatus.CREATED.value())
                .body("gameId", is(gameId))
                .body("order", is(expectedOrder));

    }

    @Test
    public void listMoves() {
        final String name = "Player name";
        final Player player = playerRepository.save(new Player(name));
        Game game = gameRepository.save(new Game(player, GameStatus.CREATED));
        final String path = String.format("/games/%s/moves", game.getId());

        given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
        .get(path).then()
                .statusCode(HttpStatus.OK.value());
    }
}
