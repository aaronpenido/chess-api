package com.chessmasters.chessapi.entities;

import javax.persistence.*;

@Entity
public class Move {

    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne
    @JoinColumn(name="game_id")
    private Game game;
    @Embedded
    private Piece piece;
    @AttributeOverrides( {
            @AttributeOverride(name="letter", column = @Column(name="destinationletter") ),
            @AttributeOverride(name="number", column = @Column(name="destinationnumber") )
    })
    private Square destination;
    private int moveOrder;

    public Move() {
    }

    public Move(Game game, Piece piece, Square destination, int moveOrder) {
        this.game = game;
        this.piece = piece;
        this.destination = destination;
        this.moveOrder = moveOrder;
    }

    public Long getId() {
        return id;
    }

    public Game getGame() {
        return game;
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
}
