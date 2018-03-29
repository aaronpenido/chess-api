package com.chessmasters.chessapi.repositories;

import com.chessmasters.chessapi.entities.PieceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PieceRepository extends JpaRepository<PieceEntity, Long>  {
}
