package com.example.worktool_new.Util;

import android.app.Application;
import android.content.Context;

public class BaseApplication extends Application {
    public static final String TAG = BaseApplication.class.getSimpleName();
    static Context context;
    private static BaseApplication mInstance;

    public static synchronized BaseApplication getInstance() {
        BaseApplication baseApplication;
        synchronized (BaseApplication.class) {
            baseApplication = mInstance;
        }
        return baseApplication;
    }

    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        mInstance = this;
    }

    public static Context getContext() {
        return context;
    }
}
