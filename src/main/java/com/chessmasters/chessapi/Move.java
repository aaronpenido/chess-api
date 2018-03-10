package com.chessmasters.chessapi;

import javax.persistence.*;

@Entity
public class Move {

    @Id
    @GeneratedValue
    private Long id;
    @Embedded
    private final Square origin;
    @AttributeOverrides( {
            @AttributeOverride(name="letter", column = @Column(name="destinationletter") ),
            @AttributeOverride(name="number", column = @Column(name="destinationnumber") )
    })
    private final Square destination;

    public Move() {
        this.origin = null;
        this.destination = null;
    }

    public Move(Square origin, Square destination) {
        this.origin = origin;
        this.destination = destination;
    }

    public Square getOrigin() {
        return origin;
    }

    public Square getDestination() {
        return destination;
    }
}
