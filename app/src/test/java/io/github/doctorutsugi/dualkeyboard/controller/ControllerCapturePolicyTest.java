package io.github.doctorutsugi.dualkeyboard.controller;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public final class ControllerCapturePolicyTest {

  @Test
  public void externalEditorUsesGlobalControllerCapture() {
    assertTrue(ControllerCapturePolicy.shouldCaptureGlobally(false));
  }

  @Test
  public void internalPreviewNeverUsesGlobalControllerCapture() {
    assertFalse(ControllerCapturePolicy.shouldCaptureGlobally(true));
  }
}