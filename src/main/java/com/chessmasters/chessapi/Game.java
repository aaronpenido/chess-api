package com.chessmasters.chessapi;

import com.chessmasters.chessapi.enums.Letter;
import com.chessmasters.chessapi.exception.GameNotStartedException;
import com.chessmasters.chessapi.exception.PlayerNotFoundException;
import com.chessmasters.chessapi.piece.*;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.chessmasters.chessapi.enums.Color.*;

@Entity
public class Game {

    @Id
    @GeneratedValue
    private final Long id;
    @MapsId
    @ManyToOne
    private final Player player;
    //@MapsId
    //@ManyToOne
    @Transient
    private Player player2;
    @Transient
    private Board board;
    @MapsId
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Piece> pieces;

    public Game() {
        this(null);
    }

    public Game(Player player) {
        this.id = null;
        this.player = player;
        this.player2 = null;
        this.board = new Board(pieces);
    }

    public void movePiece(Player movePlayer, Square from, Square destination) {
        if(pieces == null) {
            throw new GameNotStartedException(id);
        }

        if(movePlayer == null) {
            throw new PlayerNotFoundException();
        }

        board = new Board(pieces);
        board.movePiece(movePlayer, from, destination);
    }

    public void start(Player player) {
        if(pieces == null) {
            pieces = new ArrayList<>();
        }

        initializeKings();
        initializeQueens();
        initializeRooks();
        initializeKnights();
        initializeBishops();
        initializePawns();

        board = new Board(pieces);
        player2 = player;
    }

    private void initializeKings() {
        pieces.add(new King(WHITE, new Square(Letter.E, 1)));
        pieces.add(new King(BLACK, new Square(Letter.E, 8)));
    }

    private void initializeQueens() {
        pieces.add(new Queen(WHITE, new Square(Letter.D, 1)));
        pieces.add(new Queen(BLACK, new Square(Letter.D, 8)));
    }

    private void initializeRooks() {
        pieces.add(new Rook(WHITE, new Square(Letter.A, 1)));
        pieces.add(new Rook(WHITE, new Square(Letter.H, 1)));
        pieces.add(new Rook(BLACK, new Square(Letter.A, 8)));
        pieces.add(new Rook(BLACK, new Square(Letter.H, 8)));
    }

    private void initializeKnights() {
        pieces.add(new Knight(WHITE, new Square(Letter.B, 1)));
        pieces.add(new Knight(WHITE, new Square(Letter.G, 1)));
        pieces.add(new Knight(BLACK, new Square(Letter.B, 8)));
        pieces.add(new Knight(BLACK, new Square(Letter.G, 8)));
    }

    private void initializeBishops() {
        pieces.add(new Bishop(WHITE, new Square(Letter.C, 1)));
        pieces.add(new Bishop(WHITE, new Square(Letter.F, 1)));
        pieces.add(new Bishop(BLACK, new Square(Letter.C, 8)));
        pieces.add(new Bishop(BLACK, new Square(Letter.F, 8)));
    }

    private void initializePawns() {
        Arrays.stream(Letter.values())
                .map(letter -> new Square(letter, 2))
                .map(square -> new Pawn(WHITE, square))
                .forEach(pieces::add);

        Arrays.stream(Letter.values())
                .map(letter -> new Square(letter, 7))
                .map(square -> new Pawn(BLACK, square))
                .forEach(pieces::add);
    }

    public Board getBoard() {
        return board;
    }

    public List<Piece> getPieces() {
        return pieces;
    }

    public Long getId() {
        return id;
    }

    public Player getPlayer() {
        return player;
    }

    public Player getPlayer2() {
        return player2;
    }

    public void setPlayer2(Player player2) {
        this.player2 = player2;
    }
}
