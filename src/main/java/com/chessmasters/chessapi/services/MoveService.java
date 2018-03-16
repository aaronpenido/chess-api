package com.chessmasters.chessapi.services;

import com.chessmasters.chessapi.entities.Game;
import com.chessmasters.chessapi.entities.Move;
import com.chessmasters.chessapi.models.MoveModel;
import com.chessmasters.chessapi.repositories.MoveRepository;
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

    public MoveModel createMove(Long gameId) {
        Game game = gameService.getById(gameId);

        Move move = new Move(game, generateMoveOrder(gameId));

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
