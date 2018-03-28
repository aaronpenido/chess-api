package com.chessmasters.chessapi.entities;

import com.chessmasters.chessapi.enums.Letter;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.Objects;

@Embeddable
public class SquareEntity {

    private int number;
    @Enumerated(EnumType.STRING)
    private Letter letter;

    public SquareEntity() {
    }

    public SquareEntity(int number, Letter letter) {
        this.number = number;
        this.letter = letter;
    }

    public int getNumber() {
        return number;
    }

    public Letter getLetter() {
        return letter;
    }

    @Override
    public String toString() {
        return String.format("%s%s", letter, number);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SquareEntity that = (SquareEntity) o;
        return number == that.number &&
                letter == that.letter;
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, letter);
    }
}
