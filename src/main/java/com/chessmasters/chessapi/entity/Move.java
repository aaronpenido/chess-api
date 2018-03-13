package com.chessmasters.chessapi.entity;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

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
}
