package com.android.lib_assistant.common.HelperStuffs;

public interface TextToSpeechListener {
    void onStart(final String onStart);
    void onDone(final String onDone);
    void onError(final String onError);
}
