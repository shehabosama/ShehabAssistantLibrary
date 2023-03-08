package com.android.lib_assistant.builder_pattern;

import com.android.lib_assistant.common.model.PatternQuestionAnswer;

import java.util.List;

public interface IShehabAssistantBuilder {
    public ShehabAssistantBuilder addFloatActionButton();
    public ShehabAssistantBuilder addListOfQuestionAnswerModel(List<PatternQuestionAnswer> model);
    public ShehabAssistantBuilder checkTTSIsExists();
    public ShehabAssistantBuilder checkIfLanguageSupportInCurrentDevice();
    public ShehabAssistantBuilder build();
   public void speakOut(String textRekognation);
}
