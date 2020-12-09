package com.example.worktool_new.Util;

import android.content.Context;
import android.content.SharedPreferences;

public class AllSharedPref {
    static String KEY = "Survey54";

    public static boolean save(Context context, String key, String value) {
        SharedPreferences.Editor editor = context.getSharedPreferences(KEY, 0).edit();
        editor.putString(key, value);
        return editor.commit();
    }

    public static boolean save(Context context, String key, int value) {
        SharedPreferences.Editor editor = context.getSharedPreferences(KEY, 0).edit();
        editor.putInt(key, value);
        return editor.commit();
    }

    public static boolean save(Context context, String key, long value) {
        SharedPreferences.Editor editor = context.getSharedPreferences(KEY, 0).edit();
        editor.putLong(key, value);
        return editor.commit();
    }

    public static boolean save(Context context, String key, boolean value) {
        SharedPreferences.Editor editor = context.getSharedPreferences(KEY, 0).edit();
        editor.putBoolean(key, value);
        return editor.commit();
    }

    public static String restoreString(Context context, String key) {
        return context.getSharedPreferences(KEY, 0).getString(key, "");
    }

    public static int restoreInt(Context context, String key) {
        return context.getSharedPreferences(KEY, 0).getInt(key, -1);
    }

    public static long restoreLong(Context context, String key) {
        return context.getSharedPreferences(KEY, 0).getLong(key, -1);
    }

    public static boolean restoreBoolean(Context context, String key) {
        return context.getSharedPreferences(KEY, 0).getBoolean(key, false);
    }

    public static void clear(Context context) {
        SharedPreferences.Editor editor = context.getSharedPreferences(KEY, 0).edit();
        editor.clear();
        editor.commit();
    }
}
