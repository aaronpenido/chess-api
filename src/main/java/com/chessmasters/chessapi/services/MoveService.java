package com.chessmasters.chessapi.services;

import com.chessmasters.chessapi.entities.*;
import com.chessmasters.chessapi.enums.GameStatus;
import com.chessmasters.chessapi.exceptions.GameNotStartedException;
import com.chessmasters.chessapi.exceptions.PieceNotFoundException;
import com.chessmasters.chessapi.models.Game;
import com.chessmasters.chessapi.models.Move;
import com.chessmasters.chessapi.models.Player;
import com.chessmasters.chessapi.repositories.MoveRepository;
import com.chessmasters.chessapi.repositories.PieceRepository;
import com.chessmasters.chessapi.request.MoveRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MoveService {

    private MoveRepository moveRepository;
    private PieceRepository pieceRepository;
    private GameService gameService;
    private PlayerService playerService;

    public MoveService(MoveRepository moveRepository, PieceRepository pieceRepository,
                       GameService gameService, PlayerService playerService) {
        this.moveRepository = moveRepository;
        this.pieceRepository = pieceRepository;
        this.gameService = gameService;
        this.playerService = playerService;
    }

    public Move createMove(Long gameId, MoveRequest request) {
        GameEntity gameEntity = getGameEntity(gameId);
        PlayerEntity playerEntity = playerService.getById(request.getPlayerId());
        PieceEntity pieceEntity = getPieceEntity(request);
        MoveEntity moveEntity = createMoveEntity(request, gameEntity, pieceEntity);
        Move move = new Move(moveEntity);
        Game game = new Game(gameEntity);
        Player player = new Player(playerEntity);

        move = game.move(player, move);
        moveEntity.setMoveOrder(move.getOrder());
        moveEntity = moveRepository.save(moveEntity);

        gameService.addMoveToGame(gameEntity, moveEntity);

        return new Move(moveEntity);
    }

    public List<Move> getMoves(Long gameId) {
        List<MoveEntity> moves = moveRepository.findByGameId(gameId);

        return moves
                .stream()
                .map(Move::new)
                .collect(Collectors.toList());
    }

    private GameEntity getGameEntity(Long gameId) {
        GameEntity gameEntity = gameService.getById(gameId);

        if(!gameEntity.getStatus().equals(GameStatus.STARTED)) {
            throw new GameNotStartedException(gameId);
        }
        return gameEntity;
    }

    private PieceEntity getPieceEntity(MoveRequest request) {
        PieceEntity pieceEntity = pieceRepository.findOne(request.getPieceId());

        if(pieceEntity == null) {
            throw new PieceNotFoundException(request.getPieceId());
        }
        return pieceEntity;
    }

    private MoveEntity createMoveEntity(MoveRequest request, GameEntity gameEntity, PieceEntity pieceEntity) {
        final SquareEntity destination = new SquareEntity(
                request.getDestination().getNumber(),
                request.getDestination().getLetter());

        return new MoveEntity(gameEntity, pieceEntity, destination);
    }
}
