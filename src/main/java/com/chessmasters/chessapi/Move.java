package com.chessmasters.chessapi;

import com.chessmasters.chessapi.piece.Piece;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;

@Entity
public class Move {

    @Id
    @GeneratedValue
    private Long id;
    @MapsId
    @OneToOne
    private Piece piece;
    @Embedded
    private Square destination;
    private int moveOrder;

    public Move() {
    }

    public Move(Piece piece, Square destination) {
        this.piece = piece;
        this.destination = destination;
    }

    public Piece getPiece() {
        return piece;
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
}
