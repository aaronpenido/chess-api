package com.chessmasters.chessapi.entities;

import javax.persistence.*;

@Entity(name = "Move")
public class MoveEntity {

    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne
    @JoinColumn(name="game_id")
    private GameEntity game;
    @OneToOne
    private PieceEntity piece;
    @AttributeOverrides( {
            @AttributeOverride(name="letter", column = @Column(name="destinationletter") ),
            @AttributeOverride(name="number", column = @Column(name="destinationnumber") )
    })
    private SquareEntity destination;
    private int moveOrder;

    public MoveEntity() {
    }

    public MoveEntity(GameEntity game, PieceEntity piece, SquareEntity destination, int moveOrder) {
        this.game = game;
        this.piece = piece;
        this.destination = destination;
        this.moveOrder = moveOrder;
    }

    public Long getId() {
        return id;
    }

    public GameEntity getGame() {
        return game;
    }

    public PieceEntity getPiece() {
        return piece;
    }

    public SquareEntity getDestination() {
        return destination;
    }

    public int getMoveOrder() {
        return moveOrder;
    }
}
