package com.chessmasters.chessapi.enums;

import com.chessmasters.chessapi.exception.LetterNotFoundException;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public enum Letter {
    A, B, C, D, E, F, G, H;

    public static Letter previousLetter(Letter letter) {
        final int previousIndex = letter.ordinal() - 1;

        if(previousIndex < 0) {
            throw new LetterNotFoundException("There is no previous letter from " + letter);
        }

        return Letter.values()[previousIndex];
    }

    public static Letter nextLetter(Letter letter) {
        final int nextIndex = letter.ordinal() + 1;

        if(nextIndex >= Letter.values().length) {
            throw new LetterNotFoundException("There is no next letter from " + letter);
        }

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

    public static boolean hasNextLetter(Letter letter) {
        return !letter.equals(H);
    }

    public static boolean hasPreviousLetter(Letter letter) {
        return !letter.equals(A);
    }
}
