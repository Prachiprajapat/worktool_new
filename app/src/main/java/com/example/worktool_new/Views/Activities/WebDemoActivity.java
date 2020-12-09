package com.example.worktool_new.Views.Activities;

import android.os.Bundle;
import android.webkit.WebView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.worktool_new.R;

public class WebDemoActivity extends AppCompatActivity {
    WebView wvDocReader;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) R.layout.activity_web_demo);
        this.wvDocReader = (WebView) findViewById(R.id.wvDocReader);
        String url = getIntent().getStringExtra("docUrl");
        this.wvDocReader.getSettings().setJavaScriptEnabled(true);
        this.wvDocReader.loadUrl(url);
    }
}
