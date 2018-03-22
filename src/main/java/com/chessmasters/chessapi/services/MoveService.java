package com.chessmasters.chessapi.services;

import com.chessmasters.chessapi.entities.GameEntity;
import com.chessmasters.chessapi.entities.MoveEntity;
import com.chessmasters.chessapi.entities.PieceEntity;
import com.chessmasters.chessapi.entities.SquareEntity;
import com.chessmasters.chessapi.enums.GameStatus;
import com.chessmasters.chessapi.exceptions.GameNotStartedException;
import com.chessmasters.chessapi.models.MoveModel;
import com.chessmasters.chessapi.repositories.MoveRepository;
import com.chessmasters.chessapi.request.MoveRequest;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MoveService {

    private MoveRepository moveRepository;
    private GameService gameService;

    public MoveService(MoveRepository moveRepository, GameService gameService) {
        this.moveRepository = moveRepository;
        this.gameService = gameService;
    }

    public MoveModel createMove(Long gameId, MoveRequest request) {
        GameEntity game = gameService.getById(gameId);

        if(!game.getStatus().equals(GameStatus.STARTED)) {
            throw new GameNotStartedException(gameId);
        }

        final SquareEntity destination = new SquareEntity(
                request.getDestination().getNumber(),
                request.getDestination().getLetter());

        PieceEntity piece = new PieceEntity(request.getPieceModel().getColor(), destination, request.getPieceModel().getType());
        MoveEntity move = new MoveEntity(game, piece, destination, generateMoveOrder(gameId));

        return new MoveModel(moveRepository.save(move));
    }

    public List<MoveModel> getMoves(Long gameId) {
        List<MoveEntity> moves = moveRepository.findByGameId(gameId);

        return moves
                .stream()
                .map(MoveModel::new)
                .collect(Collectors.toList());
    }

    private int generateMoveOrder(Long gameId) {
        int order = 1;

        Optional<MoveModel> moveModel = getMoves(gameId)
                .stream()
                .max(Comparator.comparingInt(m -> m.getOrder()));

        if(moveModel.isPresent()) {
            order = moveModel.get().getOrder() + 1;
        }

        return order;
    }
}
