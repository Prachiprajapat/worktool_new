package com.example.worktool_new.Views.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;
import com.example.worktool_new.R;
import com.example.worktool_new.Util.SharedPreference.App;
import com.example.worktool_new.Util.SharedPreference.AppConstants;

public class Splash extends AppCompatActivity {
    Handler handler;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) R.layout.activity_splash);
        Handler handler2 = new Handler();
        this.handler = handler2;
        handler2.postDelayed(new Runnable() {
            public void run() {
                if (App.getAppPreference().getBoolean(AppConstants.IS_LOGIN)) {
                    Splash.this.startActivity(new Intent(Splash.this, MainActivity.class));
                    Splash.this.finish();
                    return;
                }
                Splash.this.startActivity(new Intent(Splash.this, Login.class));
                Splash.this.finish();
            }
        }, 5000);
    }
}
