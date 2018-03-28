package com.chessmasters.chessapi.models;

import com.chessmasters.chessapi.entities.GameEntity;
import com.chessmasters.chessapi.entities.PieceEntity;
import com.chessmasters.chessapi.entities.SquareEntity;
import com.chessmasters.chessapi.enums.Color;
import com.chessmasters.chessapi.enums.Letter;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import static com.chessmasters.chessapi.enums.Color.BLACK;
import static com.chessmasters.chessapi.enums.Color.WHITE;
import static com.chessmasters.chessapi.enums.Letter.*;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class BoardInitializerTest {

    private BoardInitializer initializer;
    private List<PieceEntity> pieces;
    private final static int WHITE_INITIAL_NUMBER = 1;
    private final static int BLACK_INITIAL_NUMBER = 8;
    @Mock
    private GameEntity gameEntity;

    @Before
    public void setUp() {
        initializer = new BoardInitializer(gameEntity);
        pieces = initializer.getPieces();
    }

    @Test
    public void initializedBoardHasThirtyTwoPieces() {
        assertThat(pieces).isNotNull();
        assertThat(pieces.size()).isEqualTo(32);
    }

    @Test
    public void kingsAreInE1AndE8Squares() {
        final String pieceType = "King";
        final Letter initialLetter = E;

        PieceEntity whitePiece = createPieceEntity(WHITE, pieceType, initialLetter, WHITE_INITIAL_NUMBER);
        PieceEntity blackPiece = createPieceEntity(BLACK, pieceType, initialLetter, BLACK_INITIAL_NUMBER);

        List<PieceEntity> kings = getPiecesFromType(pieceType);

        assertThat(kings).containsExactlyInAnyOrder(whitePiece, blackPiece);
    }
    
    @Test
    public void queensAreInD1AndD8Squares() {
        final String pieceType = "Queen";
        final Letter initialLetter = D;

        PieceEntity whitePiece = createPieceEntity(WHITE, pieceType, initialLetter, WHITE_INITIAL_NUMBER);
        PieceEntity blackPiece = createPieceEntity(BLACK, pieceType, initialLetter, BLACK_INITIAL_NUMBER);

        List<PieceEntity> queens = getPiecesFromType(pieceType);

        assertThat(queens).containsExactlyInAnyOrder(whitePiece, blackPiece);
    }

    @Test
    public void bishopsAreInC1AndF1AndC8AndF8Squares() {
        final String pieceType = "Bishop";
        final Letter firstInitialLetter = C;
        final Letter secondInitialLetter = F;

        PieceEntity firstWhitePiece = createPieceEntity(WHITE, pieceType, firstInitialLetter, WHITE_INITIAL_NUMBER);
        PieceEntity secondWhitePiece = createPieceEntity(WHITE, pieceType, secondInitialLetter, WHITE_INITIAL_NUMBER);

        PieceEntity firstBlackPiece = createPieceEntity(BLACK, pieceType, firstInitialLetter, BLACK_INITIAL_NUMBER);
        PieceEntity secondBlackPiece = createPieceEntity(BLACK, pieceType, secondInitialLetter, BLACK_INITIAL_NUMBER);

        List<PieceEntity> bishops = getPiecesFromType(pieceType);

        assertThat(bishops).containsExactlyInAnyOrder(firstWhitePiece, secondWhitePiece, firstBlackPiece, secondBlackPiece);
    }

    @Test
    public void knightsAreInB1AndG1AndB8AndG8Squares() {
        final String pieceType = "Knight";
        final Letter firstInitialLetter = B;
        final Letter secondInitialLetter = G;

        PieceEntity firstWhitePiece = createPieceEntity(WHITE, pieceType, firstInitialLetter, WHITE_INITIAL_NUMBER);
        PieceEntity secondWhitePiece = createPieceEntity(WHITE, pieceType, secondInitialLetter, WHITE_INITIAL_NUMBER);

        PieceEntity firstBlackPiece = createPieceEntity(BLACK, pieceType, firstInitialLetter, BLACK_INITIAL_NUMBER);
        PieceEntity secondBlackPiece = createPieceEntity(BLACK, pieceType, secondInitialLetter, BLACK_INITIAL_NUMBER);

        List<PieceEntity> knights = getPiecesFromType(pieceType);

        assertThat(knights).containsExactlyInAnyOrder(firstWhitePiece, secondWhitePiece, firstBlackPiece, secondBlackPiece);
    }

    @Test
    public void rooksAreInA1AndH1AndA8AndH8Squares() {
        final String pieceType = "Rook";
        final Letter firstInitialLetter = A;
        final Letter secondInitialLetter = H;

        PieceEntity firstWhitePiece = createPieceEntity(WHITE, pieceType, firstInitialLetter, WHITE_INITIAL_NUMBER);
        PieceEntity secondWhitePiece = createPieceEntity(WHITE, pieceType, secondInitialLetter, WHITE_INITIAL_NUMBER);

        PieceEntity firstBlackPiece = createPieceEntity(BLACK, pieceType, firstInitialLetter, BLACK_INITIAL_NUMBER);
        PieceEntity secondBlackPiece = createPieceEntity(BLACK, pieceType, secondInitialLetter, BLACK_INITIAL_NUMBER);

        List<PieceEntity> rooks = getPiecesFromType(pieceType);

        assertThat(rooks).containsExactlyInAnyOrder(firstWhitePiece, secondWhitePiece, firstBlackPiece, secondBlackPiece);
    }

    @Test
    public void pawnsAreInSecondAndSeventhRows() {
        final String pieceType = "Pawn";
        final int whiteInitialNumber = 2;
        final int blackInitialNumber = 7;
        final int randomLetterIndex = new Random().nextInt(Letter.values().length);
        final Letter randomLetter = Letter.values()[randomLetterIndex];

        PieceEntity whitePiece = createPieceEntity(WHITE, pieceType, randomLetter, whiteInitialNumber);
        PieceEntity blackPiece = createPieceEntity(BLACK, pieceType, randomLetter, blackInitialNumber);

        List<PieceEntity> pawns = getPiecesFromType(pieceType);

        assertThat(pawns).contains(whitePiece, blackPiece);
    }

    private PieceEntity createPieceEntity(final Color color, final String pieceType,
                                          final Letter initialLetter, final int initialNumber) {

        SquareEntity whiteSquare = new SquareEntity(initialNumber, initialLetter);

        return new PieceEntity(gameEntity, color, whiteSquare, pieceType);
    }

    private List<PieceEntity> getPiecesFromType(final String type) {
        return pieces
                .stream()
                .filter(p -> p.getType().equals(type))
                .collect(Collectors.toList());
    }
}