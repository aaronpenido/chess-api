package com.chessmasters.chessapi.repositories;

import com.chessmasters.chessapi.entities.MoveEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MoveRepository extends JpaRepository<MoveEntity, Long>  {
    List<MoveEntity> findByGameId(Long gameId);
}
