package com.example.worktool_new.Util.SharedPreference;

import android.app.Application;
import android.content.Context;

public class App extends Application {
    private static AppPreferences appPreference;
    private Context context;

    public void onCreate() {
        super.onCreate();
        this.context = this;
        appPreference = AppPreferences.init(this);
    }

    public static AppPreferences getAppPreference() {
        return appPreference;
    }
}
