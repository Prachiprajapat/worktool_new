package com.example.worktool_new.Util;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.worktool_new.R;
import com.squareup.picasso.Picasso;

public class PreviewActivity extends AppCompatActivity {
    String imageUrl;
    TouchImageView imageView;
    ImageView imgBack;
    boolean isImageFitToScreen;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(1);
        setContentView((int) R.layout.activity_preview);
        this.imageView = (TouchImageView) findViewById(R.id.imageView);
        ImageView imageView2 = (ImageView) findViewById(R.id.imgBack);
        this.imgBack = imageView2;
        imageView2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                PreviewActivity.this.finish();
            }
        });
        String stringExtra = getIntent().getStringExtra("url");
        this.imageUrl = stringExtra;
        if (stringExtra != null && stringExtra.length() > 0) {
            Picasso.get().load(this.imageUrl).into((ImageView) this.imageView);
        }
    }
}
