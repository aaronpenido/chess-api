package com.chessmasters.chessapi.entities;

import com.chessmasters.chessapi.enums.Letter;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Embeddable
public class Square {

    private int number;
    @Enumerated(EnumType.STRING)
    private Letter letter;

    public Square() {
    }

    public Square(int number, Letter letter) {
        this.number = number;
        this.letter = letter;
    }

    public int getNumber() {
        return number;
    }

    public Letter getLetter() {
        return letter;
    }
}
