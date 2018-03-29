package com.chessmasters.chessapi.services;

import com.chessmasters.chessapi.entities.*;
import com.chessmasters.chessapi.enums.Color;
import com.chessmasters.chessapi.enums.ErrorMessage;
import com.chessmasters.chessapi.enums.GameStatus;
import com.chessmasters.chessapi.exceptions.GameNotStartedException;
import com.chessmasters.chessapi.exceptions.InvalidMoveException;
import com.chessmasters.chessapi.exceptions.PieceNotFoundException;
import com.chessmasters.chessapi.models.Move;
import com.chessmasters.chessapi.repositories.MoveRepository;
import com.chessmasters.chessapi.repositories.PieceRepository;
import com.chessmasters.chessapi.request.MoveRequest;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
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
        GameEntity game = gameService.getById(gameId);

        if(!game.getStatus().equals(GameStatus.STARTED)) {
            throw new GameNotStartedException(gameId);
        }

        PlayerEntity player = playerService.getById(request.getPlayerId());

        PieceEntity pieceEntity = pieceRepository.findOne(request.getPieceId());

        if(pieceEntity == null) {
            throw new PieceNotFoundException(request.getPieceId());
        }

        throwExceptionIfPlayerTriesToMoveOpponentsPiece(player, pieceEntity);
        throwExceptionIfMoveIsDoneSequentiallyByThePlayer(game, player);

        final SquareEntity destination = new SquareEntity(
                request.getDestination().getNumber(),
                request.getDestination().getLetter());

        MoveEntity move = new MoveEntity(game, pieceEntity, destination, generateMoveOrder(gameId));

        return new Move(moveRepository.save(move));
    }

    private void throwExceptionIfPlayerTriesToMoveOpponentsPiece(PlayerEntity playerEntity, PieceEntity pieceEntity) {
        if(!pieceEntity.getColor().equals(playerEntity.getColor())) {
            throw new InvalidMoveException(String.valueOf(ErrorMessage.INVALID_MOVE_ITS_OPPONENTS_PIECE));
        }
    }

    private void throwExceptionIfMoveIsDoneSequentiallyByThePlayer(GameEntity game, PlayerEntity player) {
        MoveEntity moveEntity = moveRepository.findTopByGameOrderByMoveOrderDesc(game);

        if(moveEntity != null) {
            final boolean lastMoveColorIsEqualsToPlayerColor =
                    moveEntity.getPiece().getColor().equals(player.getColor());

            if(lastMoveColorIsEqualsToPlayerColor) {
                throw new InvalidMoveException(String.valueOf(ErrorMessage.INVALID_MOVE_ITS_OPPONENTS_TURN));
            }
        } else if (!player.getColor().equals(Color.WHITE)) {
            throw new InvalidMoveException(String.valueOf(ErrorMessage.INVALID_MOVE_ITS_OPPONENTS_TURN));
        }
    }

    public List<Move> getMoves(Long gameId) {
        List<MoveEntity> moves = moveRepository.findByGameId(gameId);

        return moves
                .stream()
                .map(Move::new)
                .collect(Collectors.toList());
    }

    private int generateMoveOrder(Long gameId) {
        int order = 1;

        Optional<Move> moveModel = getMoves(gameId)
                .stream()
                .max(Comparator.comparingInt(m -> m.getOrder()));

        if(moveModel.isPresent()) {
            order = moveModel.get().getOrder() + 1;
        }

        return order;
    }
}
