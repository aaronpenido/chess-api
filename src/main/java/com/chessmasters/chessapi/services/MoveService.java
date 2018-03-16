package com.chessmasters.chessapi.services;

import com.chessmasters.chessapi.entities.Move;
import com.chessmasters.chessapi.models.MoveModel;
import com.chessmasters.chessapi.repositories.MoveRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MoveService {

    private MoveRepository moveRepository;

    public MoveService(MoveRepository moveRepository) {
        this.moveRepository = moveRepository;
    }

    public List<MoveModel> getMoves(Long gameId) {
        List<Move> moves = moveRepository.findAll();

        return moves
                .stream()
                .map(MoveModel::new)
                .collect(Collectors.toList());
    }
}
