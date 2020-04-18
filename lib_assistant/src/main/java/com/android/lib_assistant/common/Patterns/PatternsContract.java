package com.android.lib_assistant.common.Patterns;

import android.content.Context;

import java.util.List;

public interface PatternsContract {

    interface View{
        void onListenerToFragment(String key);
    }
    interface Presenter{
        String PatternsFunc(List<String> patterns, int i, Context context);

    }
}
