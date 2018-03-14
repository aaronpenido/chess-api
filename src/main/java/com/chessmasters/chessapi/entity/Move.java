package com.chessmasters.chessapi.entity;

import com.chessmasters.chessapi.entity.piece.Piece;

import javax.persistence.*;

@Entity
public class Move {

    @Id
    @GeneratedValue
    private Long id;
    @Embedded
    private Square origin;
    @AttributeOverrides( {
            @AttributeOverride(name="letter", column = @Column(name="destinationletter") ),
            @AttributeOverride(name="number", column = @Column(name="destinationnumber") )
    })
    private Square destination;
    private int moveOrder;
    @Transient
    private Piece piece;

    public Move() {
    }

    public Move(Square origin, Square destination) {
        this.origin = origin;
        this.destination = destination;
    }

    public Move(Square origin, Square destination, int moveOrder) {
        this.origin = origin;
        this.destination = destination;
        this.moveOrder = moveOrder;
    }

    public Long getId() {
        return id;
    }

    public Square getOrigin() {
        return origin;
    }

    public Square getDestination() {
        return destination;
    }

    public int getMoveOrder() {
        return moveOrder;
    }

    public void setMoveOrder(int moveOrder) {
        this.moveOrder = moveOrder;
    }

    public Piece getPiece() {
        return piece;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }
}
