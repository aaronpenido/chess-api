package com.chessmasters.chessapi.entities;

import com.chessmasters.chessapi.enums.GameStatus;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity(name = "Game")
public class GameEntity {

    @Id
    @GeneratedValue
    private Long id;
    @Enumerated(EnumType.STRING)
    private GameStatus status;
    @OneToOne
    @JoinColumn(name = "player_id", referencedColumnName = "id")
    private PlayerEntity player;
    @OneToOne
    @JoinColumn(name = "player2_id", referencedColumnName = "id")
    private PlayerEntity player2;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PieceEntity> pieces = new ArrayList<>();
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MoveEntity> moves = new ArrayList<>();

    public GameEntity() {
    }

    public GameEntity(PlayerEntity player, GameStatus status) {
        this.player = player;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public GameStatus getStatus() {
        return status;
    }

    public PlayerEntity getPlayer() {
        return player;
    }

    public PlayerEntity getPlayer2() {
        return player2;
    }

    public void setStatus(GameStatus status) {
        this.status = status;
    }

    public void setPlayer2(PlayerEntity player2) {
        this.player2 = player2;
    }

    public List<PieceEntity> getPieces() {
        return pieces;
    }

    public List<MoveEntity> getMoves() {
        return moves;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        GameEntity that = (GameEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
