package com.android.lib_assistant.common.model;

public class PatternQuestionAnswer {
    private int index;
    private String question;
    private String answer;
    private int actionKey;

    public PatternQuestionAnswer(int index, String question, String answer,int actionKey) {
        this.index = index;
        this.question = question;
        this.answer = answer;
        this.actionKey = actionKey;
    }

    public int getActionKey() {
        return actionKey;
    }

    public void setActionKey(int actionKey) {
        this.actionKey = actionKey;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
