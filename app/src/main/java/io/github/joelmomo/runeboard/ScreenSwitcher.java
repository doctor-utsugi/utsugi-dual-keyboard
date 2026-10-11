package io.github.joelmomo.runeboard;

import android.content.Context;
import android.provider.Settings;
import android.util.Log;
import java.util.concurrent.TimeUnit;

/**
 * Moves the keyboard between the Thor's screens through AYN's IME settings: ime_fixed=1 pins it,
 * ime_show_on_second chooses the lower (1) or the main (0) screen. Android refuses these private
 * keys to ordinary apps, so they are written as root when the device allows it.
 */
final class ScreenSwitcher {

  private static final String TAG = "RuneBoard";
  private static final String KEY_FIXED = "ime_fixed";
  private static final String KEY_ON_SECOND = "ime_show_on_second";

  private ScreenSwitcher() {}

  /** Switches to the other screen; false when the settings could not be changed. Blocks. */
  static boolean toggle(Context context) {
    int onSecond =
        Settings.System.getInt(context.getContentResolver(), KEY_ON_SECOND, 1) == 1 ? 0 : 1;
    try {
      Settings.System.putInt(context.getContentResolver(), KEY_FIXED, 1);
      Settings.System.putInt(context.getContentResolver(), KEY_ON_SECOND, onSecond);
      return true;
    } catch (RuntimeException error) {
      Log.i(TAG, "Screen settings refused to the app, trying root: " + error.getMessage());
    }
    String command =
        "settings put system " + KEY_FIXED + " 1 && settings put system " + KEY_ON_SECOND + " "
            + onSecond;
    try {
      Process su = new ProcessBuilder("su", "-c", command).redirectErrorStream(true).start();
      if (!su.waitFor(10, TimeUnit.SECONDS)) {
        su.destroy();
        Log.w(TAG, "su timed out changing the keyboard screen");
        return false;
      }
      return su.exitValue() == 0;
    } catch (Exception error) {
      Log.w(TAG, "Cannot change the keyboard screen", error);
      return false;
    }
  }
}
