package com.chessmasters.chessapi.repositories;

import com.chessmasters.chessapi.entities.Move;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MoveRepository extends JpaRepository<Move, Long>  {
    List<Move> findByGameId(Long gameId);
}
