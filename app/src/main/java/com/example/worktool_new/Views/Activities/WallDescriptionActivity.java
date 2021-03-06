package com.example.worktool_new.Views.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.widget.TextView;

import com.example.worktool_new.R;

public class WallDescriptionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wall_description);
        TextView desc = findViewById(R.id.tv_wall_desc);
        desc.setClickable(true);
        desc.setMovementMethod(LinkMovementMethod.getInstance());
        String s = getIntent().getStringExtra("wallDescription");
        Log.i("abcd",s);
        Log.i("abcd", String.valueOf(s.length()));
        desc.setText(Html.fromHtml(s));
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}