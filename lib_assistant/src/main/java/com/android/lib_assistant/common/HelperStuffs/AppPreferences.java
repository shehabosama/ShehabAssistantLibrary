package com.android.lib_assistant.common.HelperStuffs;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by shehab on 28/11/2019.
 */


public class AppPreferences {

    private static final String APP_STATUS_KEY = "app_status_key";
    public static final String FIRST_RUN_KEY = "first_run_key";


    public static int getAppStatus(Context context, int defaultValue) {
        SharedPreferences appPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        return appPreferences.getInt(APP_STATUS_KEY, defaultValue);
    }

    public static void setAppStatus(int value, Context context) {
        SharedPreferences appPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        appPreferences.edit().putInt(APP_STATUS_KEY, value).apply();

    }

    public static String getString(String key, Context context, String defaultValue) {
        SharedPreferences appPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        return appPreferences.getString(key, defaultValue);
    }

    public static void setString(String key, String value, Context context) {
        SharedPreferences appPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        appPreferences.edit().putString(key, value).apply();

    }

    public static void setBoolean(String key, boolean value, Context context) {
        SharedPreferences appPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        appPreferences.edit().putBoolean(key, value).apply();

    }

    public static boolean getBoolean(String key, Context context, boolean defaultValue) {
        SharedPreferences appPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        return appPreferences.getBoolean(key, defaultValue);
    }

    public static void setLong(String key, long value, Context context) {
        SharedPreferences appPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        appPreferences.edit().putLong(key, value).apply();
    }

    public static long getLong(String key, Context context, long defaultValue) {
        SharedPreferences appPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        return appPreferences.getLong(key, defaultValue);
    }


    public static void clearKey(String key, Context context) {
        SharedPreferences appPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        appPreferences.edit().remove(key).apply();
    }

    public static void clear(Context context) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        sharedPreferences.edit().clear().apply();
    }

}
