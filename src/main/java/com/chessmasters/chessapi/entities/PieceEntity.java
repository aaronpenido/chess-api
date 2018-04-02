package com.chessmasters.chessapi.entities;

import com.chessmasters.chessapi.enums.Color;
import com.chessmasters.chessapi.enums.PieceType;

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
    @Enumerated(EnumType.STRING)
    private PieceType type;
    @ManyToOne
    private GameEntity game;

    public PieceEntity() {
    }

    public PieceEntity(GameEntity game, Color color, SquareEntity square, PieceType type) {
        this.color = color;
        this.square = square;
        this.type = type;
        this.game = game;
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

    public void setSquare(SquareEntity square) {
        this.square = square;
    }

    public PieceType getType() {
        return type;
    }

    public GameEntity getGame() {
        return game;
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
                Objects.equals(game, that.game);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, color, square, type, game);
    }
}
