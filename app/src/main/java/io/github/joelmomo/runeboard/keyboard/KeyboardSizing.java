package io.github.joelmomo.runeboard.keyboard;

/** How tall the keyboard may get on a screen, and how much its lettering shrinks to fit */
public final class KeyboardSizing {

    /** Most of the main screen the full keyboard may take, leaving the rest to the app */
    public static final float MAIN_SCREEN_SHARE = 0.6f;
    /** Most of any screen the compact keyboard may take */
    public static final float COMPACT_SHARE = 0.5f;
    /** Lettering scale in the compact keyboard, whose keys are shorter */
    public static final float COMPACT_TEXT_SCALE = 0.8f;

    private KeyboardSizing() {}

    public static int maxHeight(
            int availableHeight, int screenHeight, boolean mainScreen, boolean compact) {
        float share = 1f;
        if (mainScreen) {
            share = MAIN_SCREEN_SHARE;
        }
        if (compact) {
            share = Math.min(share, COMPACT_SHARE);
        }
        if (share >= 1f) {
            return availableHeight;
        }
        return Math.min(availableHeight, Math.round(screenHeight * share));
    }

    public static float textScale(boolean compact) {
        return compact ? COMPACT_TEXT_SCALE : 1f;
    }
}
