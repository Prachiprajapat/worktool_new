package com.example.worktool_new.Views.Activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.worktool_new.R;
import java.io.PrintStream;

public class WebViewActivity extends AppCompatActivity {
    Intent intent;
    String link;
    private ProgressDialog progress;
    WebView webView;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) R.layout.activity_web_view);
        this.webView = (WebView) findViewById(R.id.webview);
        this.intent = getIntent();
        init();
    }

    private void init() {
        this.link = this.intent.getStringExtra("link");
        PrintStream printStream = System.out;
        printStream.println("lonk" + this.link);
        this.webView.getSettings().setLoadsImagesAutomatically(true);
        this.webView.getSettings().setJavaScriptEnabled(true);
        this.webView.loadUrl(this.link);
    }

    public void showLoadingDialog() {
        if (this.progress == null) {
            ProgressDialog progressDialog = new ProgressDialog(this);
            this.progress = progressDialog;
            progressDialog.setTitle(getString(R.string.loading_title));
            this.progress.setMessage(getString(R.string.loading_message));
            this.progress.setCancelable(false);
        }
        this.progress.show();
    }

    public void dismissLoadingDialog() {
        ProgressDialog progressDialog = this.progress;
        if (progressDialog != null && progressDialog.isShowing()) {
            this.progress.dismiss();
        }
    }
}
