package io.github.joelmomo.runeboard.keyboard;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public final class KeyboardSizingTest {

    @Test
    public void fullKeyboardFillsTheLowerScreenAndTakesSixtyPercentOfTheMainOne() {
        assertEquals(1025, KeyboardSizing.maxHeight(1025, 1080, false, false));
        assertEquals(648, KeyboardSizing.maxHeight(1025, 1080, true, false));
    }

    @Test
    public void compactKeyboardTakesAtMostHalfOfEitherScreen() {
        assertEquals(540, KeyboardSizing.maxHeight(1025, 1080, false, true));
        assertEquals(540, KeyboardSizing.maxHeight(1025, 1080, true, true));
    }

    @Test
    public void compactKeyboardNeverGrowsPastTheSpaceTheSystemOffers() {
        assertEquals(300, KeyboardSizing.maxHeight(300, 1080, false, true));
        assertEquals(300, KeyboardSizing.maxHeight(300, 1080, true, false));
    }

    @Test
    public void compactLetteringShrinksAndFullLetteringDoesNot() {
        assertEquals(1f, KeyboardSizing.textScale(false), 0f);
        assertTrue(KeyboardSizing.textScale(true) < 1f);
    }
}
