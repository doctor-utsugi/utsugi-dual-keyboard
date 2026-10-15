package io.github.doctorutsugi.dualkeyboard.controller;

public final class ControllerCapturePolicy {

  public static final String TEST_PREVIEW_IME_OPTION = "dualkeyboard.test-preview";

  private ControllerCapturePolicy() {}

  public static boolean shouldCaptureGlobally(boolean testPreviewEditor) {
    return !testPreviewEditor;
  }
}