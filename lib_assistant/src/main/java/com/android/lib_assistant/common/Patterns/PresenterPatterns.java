package com.android.lib_assistant.common.Patterns;

import android.content.Context;
import android.util.Log;


import com.android.lib_assistant.common.SqlHelper.MyDbAdapter;
import com.android.lib_assistant.common.model.PatternQuestionAnswer;

import java.security.SecureRandom;
import java.util.Date;
import java.util.List;
import java.util.Random;

import static android.content.ContentValues.TAG;


public class PresenterPatterns implements PatternsContract.Presenter{

    private PatternsContract.View mView;
    private MyDbAdapter myDbAdapter;
    public List<PatternQuestionAnswer> presenterPatterns;
    public Random randomGenerator;

    public String[] pat_en ={
            /*ask about my information*/"whats my name","my name is","my name",
            /*ask about knowing things*/"birth date",
            /*say hi*/"hello","hello jobber","jobber",
            /*ask hir about hir information*/"whats your name","your name","you have a name","who made you","whats your birth day","your birth date","who are you","how are you",
            "are you dating before","you dating before","dating before","are you crazy","you crazy","tell my about you","tell me about yourself","are you stupid",
            "are you love anyone","love anyone","are you have a parents","whats your favorite meal","are you know every thing","who is the boy of your dreams",
            "the boy of you dreams","tell me about this app","i hate shehab","are you fill in love","fill in love",
            /*funny words*/"i love you","love you","do you love me","love me","do you love me","you love me","love me","song for me","song","fuck you",
            "mother fucker","i am hungry","i hungry","do you know siri","google assistant","google assistance","google","do you know cortana","Cortana",
            "you know siri","siri","you know cortana","do you sleep","so happy for talk to you","tell me a joke",
            "tell me joke","joke"
    };


    public PresenterPatterns(PatternsContract.View mView ,Context context) {
        this.mView = mView;
        randomGenerator = new Random();
        myDbAdapter = new MyDbAdapter(context);
        presenterPatterns = myDbAdapter.getData();
    }


    @Override
    public String PatternsFunc(List<String> patterns, List<Integer> i, Context context){

       // Log.e(TAG, "PatternsFunc: "+patterns.get(i).toLowerCase() );
        String[] answer_about_competitor = {"i don't care about these guys. I believe that I will be special in the future.",
                "don't mention this name in this application because these my competitor . deal ?", "lol. don't say this boolshit",
                "i thank you are made me angry",
                "you can ask them. okay. i'm not a babysitter for them",
                "i hate this question","not funny question"};

        int index = randomGenerator.nextInt(i.size());
        Integer item = i.get(index);

        mView.getActionKey(String.valueOf(myDbAdapter.getData().get(item).getActionKey()));
        return myDbAdapter.getData().get(item).getAnswer();
    }

    @Override
    public void reservedWords(String reservedWord) {
        if(reservedWord.contains("who made you")){
            mView.spockFunc("Shehab Osama , he is a java programmer , and he created me to help you");
        }else if(reservedWord.contains("What's your name")){
            mView.spockFunc("My name is shehab assistant. and i'm here to help you");
        }else if(reservedWord.contains("made you")){
            mView.spockFunc("Shehab Osama , he is a java programmer , and he created me to help you");
        } else if(reservedWord.contains("your name")){
            mView.spockFunc("My name is shehab assistant.");
        }else if(reservedWord.contains("who own you")){
            mView.spockFunc("Shehab Osama , he is a java programmer , and he created me to help you");
        }else if(reservedWord.contains("when is your birthday")){
            mView.spockFunc("i forgot my birth day now , but when i remember that i will tell you");
        }else if(reservedWord.contains("your birthday")){
            mView.spockFunc("i forgot my birth day now");
        }else if(reservedWord.contains("your birth date")){
           mView.spockFunc( "i forgot my birth day now , but when i remember that i will tell you");
        }else if(reservedWord.contains("birth date")){
            mView.spockFunc("i forgot my birth day now , but when i remember that i will tell you");
        }else{

            mView.doNormaOperation();
        }

    }

    public static int generateRandomInteger(int min, int max) {
        SecureRandom rand = new SecureRandom();
        rand.setSeed(new Date().getTime());
        int randomNum = rand.nextInt((max - min) + 1) + min;
        return randomNum;
    }
}
