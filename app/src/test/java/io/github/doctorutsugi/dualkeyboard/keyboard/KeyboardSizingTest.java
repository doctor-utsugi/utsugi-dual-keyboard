package io.github.doctorutsugi.dualkeyboard.keyboard;

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

    @Test
    public void compactRowsAreEvenedOutAndHintsShrink() {
        assertEquals(0.9f, KeyboardSizing.compactRowWeight(0.72f), 0f);
        assertEquals(1f, KeyboardSizing.compactRowWeight(1f), 0f);
        assertEquals(1f, KeyboardSizing.hintScale(false), 0f);
        assertTrue(KeyboardSizing.hintScale(true) < 1f);
    }

    @Test
    public void compactKeyboardTightensMarginsGapsAndCornersAndFullDoesNot() {
        assertEquals(10f, KeyboardSizing.outerMarginDp(10f, false), 0f);
        assertEquals(6f, KeyboardSizing.keyGapDp(6f, false), 0f);
        assertEquals(8f, KeyboardSizing.keyRadiusDp(8f, false), 0f);
        assertTrue(KeyboardSizing.outerMarginDp(10f, true) < 10f);
        assertTrue(KeyboardSizing.keyGapDp(6f, true) < 6f);
        assertTrue(KeyboardSizing.keyRadiusDp(8f, true) < 8f);
        assertEquals(2f, KeyboardSizing.keyGapDp(2f, true), 0f);
    }

    @Test
    public void touchesInsideAKeyAreOnItAndTouchesInAGapMeasureToItsEdge() {
        assertEquals(0f, KeyboardSizing.distanceToKey(10f, 10f, 50f, 30f, 20f, 20f), 0f);
        assertEquals(0f, KeyboardSizing.distanceToKey(10f, 10f, 50f, 30f, 50f, 30f), 0f);
        assertEquals(4f, KeyboardSizing.distanceToKey(10f, 10f, 50f, 30f, 30f, 6f), 0.001f);
        assertEquals(3f, KeyboardSizing.distanceToKey(10f, 10f, 50f, 30f, 53f, 20f), 0.001f);
        assertEquals(5f, KeyboardSizing.distanceToKey(10f, 10f, 50f, 30f, 53f, 34f), 0.001f);
    }
}
