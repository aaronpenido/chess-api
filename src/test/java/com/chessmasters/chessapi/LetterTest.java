package com.chessmasters.chessapi;

import com.chessmasters.chessapi.enums.Letter;
import com.chessmasters.chessapi.exception.LetterNotFoundException;
import org.junit.Test;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class LetterTest {

    @Test
    public void throwExceptionWhenTryToGetPreviousLetterFromFirstOne() {
        assertThatThrownBy(() -> Letter.previousLetter(Letter.A))
                .isInstanceOf(LetterNotFoundException.class);
    }

    @Test
    public void throwExceptionWhenTryToGetNextLetterFromLastOne() {
        assertThatThrownBy(() -> Letter.nextLetter(Letter.H))
                .isInstanceOf(LetterNotFoundException.class);
    }

    @Test
    public void previousLetterFromEIsD() {
        assertThat(Letter.previousLetter(Letter.E)).isEqualTo(Letter.D);
    }

    @Test
    public void previousLetterFromEIsF() {
        assertThat(Letter.nextLetter(Letter.E)).isEqualTo(Letter.F);
    }

    @Test
    public void previousLettersFromDAreABC() {
        Letter letter = Letter.D;
        List<Letter> expected = Arrays.asList(
                Letter.A,
                Letter.B,
                Letter.C);

        assertThat(Letter.previousLetters(letter)).containsExactlyInAnyOrder(
                expected.toArray(new Letter[expected.size()]));
    }

    @Test
    public void nextLettersFromDAreEFGH() {
        Letter letter = Letter.D;
        List<Letter> expected = Arrays.asList(
                Letter.E,
                Letter.F,
                Letter.G,
                Letter.H);

        assertThat(Letter.nextLetters(letter)).containsExactlyInAnyOrder(
                expected.toArray(new Letter[expected.size()]));
    }
}