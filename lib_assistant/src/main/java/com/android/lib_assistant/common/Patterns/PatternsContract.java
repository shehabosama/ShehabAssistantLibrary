package com.android.lib_assistant.common.Patterns;

import android.content.Context;

import java.util.List;

public interface PatternsContract {

    interface View{
        void getActionKey(String key);
        void doNormaOperation();
        void spockFunc(String value);
    }
    interface Presenter{
        String PatternsFunc(List<String> patterns, List<Integer> i, Context context);
        void reservedWords(String reservedWord);



    }
}
