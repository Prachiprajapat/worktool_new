package com.example.worktool_new.Views.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import com.example.worktool_new.Adapters.AdapterSponsors;
import com.example.worktool_new.R;

public class Godfather extends AppCompatActivity {
    ImageView backButton;
    private AdapterSponsors mAdapter;
    private RecyclerView recyclerSponsors;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) R.layout.activity_godfather);
        this.recyclerSponsors = (RecyclerView) findViewById(R.id.recyclerSponsors);
        this.backButton = (ImageView) findViewById(R.id.backButton);
        AdapterSponsors adapterSponsors = new AdapterSponsors(getApplicationContext());
        this.mAdapter = adapterSponsors;
        this.recyclerSponsors.setAdapter(adapterSponsors);
        this.backButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Godfather.this.startActivity(new Intent(Godfather.this, MainActivity.class));
                Godfather.this.finish();
            }
        });
    }
}
