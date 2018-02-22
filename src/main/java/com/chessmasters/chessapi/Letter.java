package com.chessmasters.chessapi;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public enum Letter {
    A, B, C, D, E, F, G, H;

    public static Letter previousLetter(Letter letter) {
        final int previousIndex = letter.ordinal() - 1;

        return Letter.values()[previousIndex];
    }

    public static Letter nextLetter(Letter letter) {
        final int nextIndex = letter.ordinal() + 1;

        return Letter.values()[nextIndex];
    }

    public static List<Letter> nextLetters(Letter letter) {
        final int ordinal = letter.ordinal();

        return Arrays.stream(Letter.values())
                .filter(i -> i.ordinal() > ordinal)
                .collect(Collectors.toList());
    }

    public static List<Letter> previousLetters(Letter letter) {
        final int ordinal = letter.ordinal();

        return Arrays.stream(Letter.values())
                .sorted(Comparator.reverseOrder())
                .filter(i -> i.ordinal() < ordinal)
                .collect(Collectors.toList());
    }
}
