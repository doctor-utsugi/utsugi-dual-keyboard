package io.github.doctorutsugi.dualkeyboard.suggestion;

import io.github.doctorutsugi.dualkeyboard.editor.EditorInputPolicy;

public final class SuggestionPolicy {

    private SuggestionPolicy() {
    }

    public static boolean supportsInputType(int inputType) {
        return EditorInputPolicy.supportsSuggestions(inputType);
    }
}
