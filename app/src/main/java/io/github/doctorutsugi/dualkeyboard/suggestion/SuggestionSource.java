package io.github.doctorutsugi.dualkeyboard.suggestion;

public interface SuggestionSource extends AutoCloseable {

    interface Callback {
        void onSuggestions(SuggestionResult result);
    }

    boolean isAvailable();

    void request(String word, Callback callback);

    @Override
    void close();
}
