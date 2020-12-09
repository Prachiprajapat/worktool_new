package com.example.worktool_new.Views.Activities;

import android.os.Bundle;
import android.widget.Spinner;
import androidx.appcompat.app.AppCompatActivity;
import com.example.worktool_new.R;

public class AddArticle extends AppCompatActivity {
    Spinner statusSpinner;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) R.layout.activity_add_article);
    }
}
