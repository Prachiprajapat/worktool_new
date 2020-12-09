package com.example.worktool_new.Views.Activities;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.worktool_new.R;

public class AdvanvceCvSearchActivity extends AppCompatActivity {
    ImageView cross;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) R.layout.advance_cv_activity);
        ImageView imageView = (ImageView) findViewById(R.id.iv_cross);
        this.cross = imageView;
        imageView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                AdvanvceCvSearchActivity.this.finish();
            }
        });
    }
}
