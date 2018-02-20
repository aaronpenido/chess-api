package com.chessmasters.chessapi;

public abstract class PawnMove {

    private Square square;
    private int initialNumber;
    private int promotionNumber;
    private int nextNumber;
    private int nextTwoNumber;

    public PawnMove(Square square, int initialNumber, int promotionNumber, int nextNumber, int nextTwoNumber) {
        this.square = square;
        this.initialNumber = initialNumber;
        this.promotionNumber = promotionNumber;
        this.nextNumber = nextNumber;
        this.nextTwoNumber = nextTwoNumber;
    }

    public int getInitialNumber() {
        return initialNumber;
    }

    public int getPromotionNumber() {
        return promotionNumber;
    }

    public int getNextNumber() {
        return nextNumber;
    }

    public int getNextTwoNumber() {
        return nextTwoNumber;
    }
}
