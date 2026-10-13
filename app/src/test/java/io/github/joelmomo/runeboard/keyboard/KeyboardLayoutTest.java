package io.github.joelmomo.runeboard.keyboard;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import java.util.List;

public final class KeyboardLayoutTest {

    @Test
    public void qwertyUsesThorStaggeredRows() {
        KeyboardLayout layout = KeyboardLayouts.qwerty();

        assertEquals(6, layout.getRowCount());
        assertEquals(11, layout.getRow(0).size());
        assertEquals(10, layout.getRow(1).size());
        assertEquals(10, layout.getRow(2).size());
        assertEquals(9, layout.getRow(3).size());
        assertEquals(9, layout.getRow(4).size());
        assertEquals(5, layout.getRow(5).size());

        assertTrue(layout.getRow(3).getLeftInsetWeight() > 0f);
        assertEquals(KeyboardKey.Type.SHIFT,
                layout.getKey(4, 0).getType());
        assertEquals(KeyboardKey.Type.BACKSPACE,
                layout.getKey(4, 8).getType());
    }

    @Test
    public void quickEditRowExposesCommonEditingActions() {
        KeyboardLayout layout = KeyboardLayouts.qwerty();

        assertEquals(EditorCommand.CURSOR_LEFT,
                layout.getKey(0, 0).getCommand());
        assertEquals(EditorCommand.CURSOR_RIGHT,
                layout.getKey(0, 1).getCommand());
        assertEquals(EditorCommand.COPY,
                layout.getKey(0, 2).getCommand());
        assertEquals(EditorCommand.PASTE,
                layout.getKey(0, 3).getCommand());
        assertEquals(EditorCommand.UNDO,
                layout.getKey(0, 4).getCommand());
        assertEquals(EditorCommand.REDO,
                layout.getKey(0, 5).getCommand());
        assertEquals(KeyboardKey.Type.MINIMIZE,
                layout.getKey(0, 6).getType());
        assertEquals(KeyboardKey.Type.EDIT,
                layout.getKey(0, 7).getType());
        assertEquals(KeyboardKey.Type.SCREEN,
                layout.getKey(0, 8).getType());
        assertEquals(KeyboardKey.Type.HIDE,
                layout.getKey(0, 9).getType());
        assertEquals(KeyboardKey.Type.SIZE,
                layout.getKey(0, 10).getType());
    }

    @Test
    public void sizeKeyIsPresentInEveryLayoutAndModeWithAnActionRow() {
        for (KeyboardLayout layout : List.of(
                KeyboardLayouts.englishQwerty(),
                KeyboardLayouts.spanishQwerty(),
                KeyboardLayouts.frenchAzerty(),
                KeyboardLayouts.russianJcuken(),
                KeyboardLayouts.englishSymbols(),
                KeyboardLayouts.russianSymbols(),
                KeyboardLayouts.editorLayout())) {
            assertTrue(containsType(layout, KeyboardKey.Type.SIZE));
        }
    }

    private static boolean containsType(KeyboardLayout layout, KeyboardKey.Type type) {
        for (int row = 0; row < layout.getRowCount(); row++) {
            for (int col = 0; col < layout.getRow(row).size(); col++) {
                if (layout.getKey(row, col).getType() == type) {
                    return true;
                }
            }
        }
        return false;
    }

    @Test
    public void commandAndNumberRowsUseErgonomicHeights() {
        KeyboardLayout layout = KeyboardLayouts.qwerty();

        assertTrue(layout.getRow(0).getHeightWeight()
                < layout.getRow(2).getHeightWeight());
        assertEquals(layout.getRow(2).getHeightWeight(),
                layout.getRow(1).getHeightWeight(), 0f);
        assertTrue(layout.getRow(5).getHeightWeight()
                < layout.getRow(2).getHeightWeight());
    }

    @Test
    public void thumbRowPrioritizesSpaceAndNewline() {
        KeyboardLayout layout = KeyboardLayouts.qwerty();

        assertEquals(KeyboardKey.Type.MODE,
                layout.getKey(5, 0).getType());
        assertEquals(KeyboardKey.Type.SPACE,
                layout.getKey(5, 2).getType());
        assertEquals(KeyboardKey.Type.ENTER,
                layout.getKey(5, 4).getType());
    }
}
