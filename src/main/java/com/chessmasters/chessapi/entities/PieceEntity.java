package com.chessmasters.chessapi.entities;

import com.chessmasters.chessapi.enums.Color;

import javax.persistence.*;
import java.util.Objects;

@Entity(name = "Piece")
public class PieceEntity {

    @Id
    @GeneratedValue
    private Long id;
    @Enumerated(EnumType.STRING)
    private Color color;
    @Embedded
    private SquareEntity square;
    private String type;
    @ManyToOne
    private GameEntity gameEntity;

    public PieceEntity() {
    }

    public PieceEntity(GameEntity gameEntity, Color color, SquareEntity square, String type) {
        this.color = color;
        this.square = square;
        this.type = type;
        this.gameEntity = gameEntity;
    }

    public Long getId() {
        return id;
    }

    public Color getColor() {
        return color;
    }

    public SquareEntity getSquare() {
        return square;
    }

    public String getType() {
        return type;
    }

    public GameEntity getGameEntity() {
        return gameEntity;
    }

    @Override
    public String toString() {
        return String.format("%s - %s %s", square, color, type);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        PieceEntity that = (PieceEntity) o;
        return Objects.equals(id, that.id) &&
                color == that.color &&
                Objects.equals(square, that.square) &&
                Objects.equals(type, that.type) &&
                Objects.equals(gameEntity, that.gameEntity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, color, square, type, gameEntity);
    }
}
