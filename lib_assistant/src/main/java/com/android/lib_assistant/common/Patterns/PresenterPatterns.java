package com.android.lib_assistant.common.Patterns;

import android.content.Context;
import android.util.Log;


import com.android.lib_assistant.common.HelperStuffs.Constants;
import com.android.lib_assistant.common.SqlHelper.myDbAdapter;

import java.security.SecureRandom;
import java.util.Date;
import java.util.List;

import static android.content.ContentValues.TAG;


public class PresenterPatterns implements PatternsContract.Presenter{

    private PatternsContract.View mView;
    private myDbAdapter myDbAdapter;
    public String[] pat_en ={
            /*open favorites fragment*/"favorites","favorite","go to favorite","go to favorites","move to favorite","move to favorites","favourites","go to favourites",
            /*open timeline fragment*/"timeline","go to th timeline","view products","go to the posts","posts","go to timeline",
            /*open publish fragment*/"publish posts","add posts","add product","add land","add flats","publish something","add post","publish",
            /*open review fragment*/"reviews","go to reviews","get my rates","am i a bad gay","my rates","rates",
            /*open profile fragment*/"profile","my profile","go to my profile","settings","setting","go to the settings","go to the settings",
            /*open MyOrders Fragment*/"go to my orders","go to my requests","requests","request","order","orders",
            /*open broker fragment*/"broker","go to broker","brokers","want to brokers","proker","prokers","brother","protur",
            /*chat fragment*/"chat","room","rooms","chat rooms","rooms,group",
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


    public PresenterPatterns(PatternsContract.View mView) {
        this.mView = mView;
    }


    @Override
    public String PatternsFunc(List<String> patterns, int i, Context context){
        myDbAdapter = new myDbAdapter(context);
        Log.e(TAG, "PatternsFunc: "+patterns.get(i).toLowerCase() );
        String[] answer_about_competitor = {"i don't care about these guys. I believe that I will be special in the future.",
                "don't mention this name in this application because these my competitor . deal ?", "lol. don't say this boolshit",
                "i thank you are made me angry",
                "you can ask them. okay. i'm not a babysitter for them",
                "i hate this question","not funny question"};
        if(patterns.get(i).contains("go to timeline")||
                patterns.get(i).contains("timeline")||
                patterns.get(i).contains("go to the timeline")||
                patterns.get(i).contains("view products")||
                patterns.get(i).contains("posts")){
           // mView.onListenerToFragment(String.valueOf(Constants.BottomNavConstants.MENU_TIME_LINE_SCREEN));
            patterns.clear();
            return "opening time line";

        }else if(patterns.get(i).contains("favorites")||
                patterns.get(i).contains("favorite")||
                patterns.get(i).contains("go to favorite")
        ){

          //  mView.onListenerToFragment(String.valueOf(Constants.BottomNavConstants.ACTION_FAVORITES));
            patterns.clear();
            return "opening favorites";
        }else if(patterns.get(i).contains("publish")||
                patterns.get(i).contains("add posts")||
                patterns.get(i).contains("add land")||
                patterns.get(i).contains("add flats")||
                patterns.get(i).contains("add post")||
                patterns.get(i).contains("publish posts")){
           // mView.onListenerToFragment(String.valueOf(Constants.BottomNavConstants.MENU_MY_PUBLISHING_SCREEN));

            patterns.clear();
            return "opening publish products";
        }else if(patterns.get(i).contains("reviews")||
                patterns.get(i).contains("go to reviews")||
                patterns.get(i).contains("rates")||
                patterns.get(i).contains("my rates")||
                patterns.get(i).contains("get my rates")){
           // mView.onListenerToFragment(String.valueOf(Constants.BottomNavConstants.ACTION_REVIEWS));

            patterns.clear();
            return "opening you reviews";
        }else if(patterns.get(i).contains("profile")||
                patterns.get(i).contains("setting")||
                patterns.get(i).contains("settings")||
                patterns.get(i).contains("my profile")||
                patterns.get(i).contains("go to my profile")){
         //   mView.onListenerToFragment(String.valueOf(Constants.BottomNavConstants.ACTION_PROFILE));

            patterns.clear();
            return"opening your profile";
        }else if(patterns.get(i).contains("orders")||
                patterns.get(i).contains("order")||
                patterns.get(i).contains("request")||
                patterns.get(i).contains("my requests")||
                patterns.get(i).contains("requests")){
          //  mView.onListenerToFragment(String.valueOf(Constants.BottomNavConstants.ACTION_REQUESTS_PRODUCTS));

            patterns.clear();
            return "opening you requests";
        }else if(patterns.get(i).contains("broker")||
                patterns.get(i).contains("brokers")||
                patterns.get(i).contains("jobbers")||
                patterns.get(i).contains("want to brokers")){
           // mView.onListenerToFragment(String.valueOf(Constants.BottomNavConstants.ACTION_BROKERS));

            patterns.clear();
            return "opening brokers";
        }else if(patterns.get(i).contains("chat")||
                patterns.get(i).contains("room")||
                patterns.get(i).contains("rooms")||
                patterns.get(i).contains("group")){
           // mView.onListenerToFragment(String.valueOf(Constants.BottomNavConstants.ACTION_CHAT_ROOMS));

            patterns.clear();
            return "opening chat rooms";
        }else if(patterns.get(i).contains("hello")||
                patterns.get(i).contains("hay")||
                patterns.get(i).contains("hay you")){
            patterns.clear();
            return"hello my friend. how i can help you";
        }else if(patterns.get(i).contains("whats my name")||
                patterns.get(i).contains("my name is")||
                patterns.get(i).contains("my name")
                ){

            patterns.clear();
            return "your name is "+myDbAdapter.getEmployeeName("name");
        }else if(patterns.get(i).contains("you know siri")||
                patterns.get(i).contains("siri")||
                patterns.get(i).contains("cortana")||
                patterns.get(i).contains("do you know cortana")||
                patterns.get(i).contains("do you google assistant")||
                patterns.get(i).contains("google assistant")||
                patterns.get(i).contains("google assistance")||
                patterns.get(i).contains("do you google assistance")||
                patterns.get(i).contains("Siri")
                ){
            patterns.clear();
            return answer_about_competitor[generateRandomInteger(0,answer_about_competitor.length-1)];

        }else if (patterns.get(i).contains("who made you")||
                patterns.get(i).contains("made you")
                ){
            patterns.clear();
            return"Shehab Osama , he is a java programmer , and he created me to help you";
        }else if (patterns.get(i).contains("you have a name")||
                patterns.get(i).contains("your name")||
                patterns.get(i).contains("whats your name")||
                patterns.get(i).contains("who are you")

        ){
            patterns.clear();
            return"my name is shehab assistant can i get you anything ?";
        }else if (patterns.get(i).contains("how are you")||
                patterns.get(i).contains("whats up")
        ){
            patterns.clear();
            return"i'm fine";
        }else{
            patterns.clear();
            return  "i don't understand you";
        }

    }
    public static int generateRandomInteger(int min, int max) {
        SecureRandom rand = new SecureRandom();
        rand.setSeed(new Date().getTime());
        int randomNum = rand.nextInt((max - min) + 1) + min;
        return randomNum;
    }
}
