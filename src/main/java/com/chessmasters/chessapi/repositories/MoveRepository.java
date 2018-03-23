package com.chessmasters.chessapi.repositories;

import com.chessmasters.chessapi.entities.GameEntity;
import com.chessmasters.chessapi.entities.MoveEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MoveRepository extends JpaRepository<MoveEntity, Long>  {

    List<MoveEntity> findByGameId(Long gameId);
    //@Query("select m from Move m WHERE m.game = :game ORDER BY m.moveOrder DESC")
    MoveEntity findTopByGameOrderByMoveOrderDesc(@Param("game") GameEntity gameEntity);
}
