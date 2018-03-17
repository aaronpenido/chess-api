package com.chessmasters.chessapi.services;

import com.chessmasters.chessapi.entities.Game;
import com.chessmasters.chessapi.entities.Move;
import com.chessmasters.chessapi.entities.Square;
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
        Game game = gameService.getById(gameId);

        if(!game.getStatus().equals(GameStatus.STARTED)) {
            throw new GameNotStartedException(gameId);
        }

        final Square destination = new Square(
                request.getDestination().getNumber(),
                request.getDestination().getLetter());

        Move move = new Move(game, destination, generateMoveOrder(gameId));

        return new MoveModel(moveRepository.save(move));
    }

    public List<MoveModel> getMoves(Long gameId) {
        List<Move> moves = moveRepository.findByGameId(gameId);

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
