package com.chessmasters.chessapi.piece;

import com.chessmasters.chessapi.Board;
import com.chessmasters.chessapi.enums.Color;
import com.chessmasters.chessapi.Square;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "piece_type")
public abstract class Piece {

    @Id
    @GeneratedValue
    private Long id;
    Color color;
    @AttributeOverrides( {
            @AttributeOverride(name="letter", column = @Column(name="letter") ),
            @AttributeOverride(name="number", column = @Column(name="number") )
    })
    Square square;

    public Piece() {
    }

    public Piece(Color color, Square square) {
        this.color = color;
        this.square = square;
    }

    public Color getColor() {
        return color;
    }

    public Square getSquare() {
        return square;
    }

    public void setSquare(Square square) {
        this.square = square;
    }

    public abstract List<Square> moves(Board board);

    public Long getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Piece piece = (Piece) o;
        return Objects.equals(square, piece.square);
    }

    @Override
    public int hashCode() {
        return Objects.hash(square);
    }
}
