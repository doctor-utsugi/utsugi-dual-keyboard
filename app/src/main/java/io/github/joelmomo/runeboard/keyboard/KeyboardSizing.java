package io.github.joelmomo.runeboard.keyboard;

/** How tall the keyboard may get on a screen, and how its keys and spacing shrink to fit */
public final class KeyboardSizing {

    /** Most of the main screen the full keyboard may take, leaving the rest to the app */
    public static final float MAIN_SCREEN_SHARE = 0.6f;
    /** Most of any screen the compact keyboard may take */
    public static final float COMPACT_SHARE = 0.5f;
    /** Lettering scale in the compact keyboard, whose keys are shorter */
    public static final float COMPACT_TEXT_SCALE = 0.8f;

    /** Controller hints in the corner of a key shrink further so they clear the key's label */
    public static final float COMPACT_HINT_SCALE = 0.7f;
    /** Shortest a row may be, relative to a typing row, in the compact keyboard */
    public static final float COMPACT_MIN_ROW_WEIGHT = 0.9f;
    /** Widest margin around the compact keyboard's keys, so the shorter keys keep their room */
    public static final float COMPACT_MAX_OUTER_MARGIN_DP = 4f;
    /** Widest gap between the compact keyboard's keys */
    public static final float COMPACT_MAX_KEY_GAP_DP = 3f;
    /** Corner rounding of the compact keyboard's keys, relative to the theme's */
    public static final float COMPACT_RADIUS_SCALE = 0.75f;

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

    public static float hintScale(boolean compact) {
        return compact ? COMPACT_HINT_SCALE : 1f;
    }

    public static float compactRowWeight(float weight) {
        return Math.max(weight, COMPACT_MIN_ROW_WEIGHT);
    }

    public static float outerMarginDp(float themeMarginDp, boolean compact) {
        return compact ? Math.min(themeMarginDp, COMPACT_MAX_OUTER_MARGIN_DP) : themeMarginDp;
    }

    public static float keyGapDp(float themeGapDp, boolean compact) {
        return compact ? Math.min(themeGapDp, COMPACT_MAX_KEY_GAP_DP) : themeGapDp;
    }

    public static float keyRadiusDp(float themeRadiusDp, boolean compact) {
        return compact ? themeRadiusDp * COMPACT_RADIUS_SCALE : themeRadiusDp;
    }

    /** How far a touch is from a key's bounds, 0 inside it */
    public static float distanceToKey(
            float left, float top, float right, float bottom, float x, float y) {
        float dx = Math.max(0f, Math.max(left - x, x - right));
        float dy = Math.max(0f, Math.max(top - y, y - bottom));
        return (float) Math.hypot(dx, dy);
    }
}
