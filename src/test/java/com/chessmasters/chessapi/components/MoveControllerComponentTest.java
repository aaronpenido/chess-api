package com.chessmasters.chessapi.components;

import com.chessmasters.chessapi.entities.GameEntity;
import com.chessmasters.chessapi.entities.PieceEntity;
import com.chessmasters.chessapi.entities.PlayerEntity;
import com.chessmasters.chessapi.entities.SquareEntity;
import com.chessmasters.chessapi.enums.GameStatus;
import com.chessmasters.chessapi.enums.Letter;
import com.chessmasters.chessapi.models.Pawn;
import com.chessmasters.chessapi.models.Piece;
import com.chessmasters.chessapi.models.Square;
import com.chessmasters.chessapi.repositories.GameRepository;
import com.chessmasters.chessapi.repositories.PlayerRepository;
import com.chessmasters.chessapi.request.MoveRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;

import static com.chessmasters.chessapi.enums.Color.WHITE;
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
        final SquareEntity destination = new SquareEntity(1, Letter.A);
        final Square expectedDestination = new Square(destination);
        final PieceEntity piece = new PieceEntity(WHITE, destination, "Pawn");
        Piece pawn = new Pawn(piece);
        final MoveRequest moveRequest = new MoveRequest(pawn, expectedDestination);
        final int expectedOrder = 1;
        final String playerName = "Player name";
        final PlayerEntity player = playerRepository.save(new PlayerEntity(playerName));
        final GameEntity game = gameRepository.save(new GameEntity(player, GameStatus.STARTED));
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
        final PlayerEntity player = playerRepository.save(new PlayerEntity(name));
        GameEntity game = gameRepository.save(new GameEntity(player, GameStatus.CREATED));
        final String path = String.format("/games/%s/moves", game.getId());

        given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
        .get(path).then()
                .statusCode(HttpStatus.OK.value());
    }
}
