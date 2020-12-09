package com.example.worktool_new.Views.Activities.addCer;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.worktool_new.R;
import com.example.worktool_new.Util.AllSharedPref;

public class Besoin_Activity extends AppCompatActivity {
    ImageView back;
    EditText etBesoin;
    TextView tvNextMotifs;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) R.layout.besoin_layout);
        init();
    }

    private void init() {
        this.back = (ImageView) findViewById(R.id.iv_backAddCER);
        this.etBesoin = (EditText) findViewById(R.id.etBesoin);
        this.tvNextMotifs = (TextView) findViewById(R.id.tvNextMotifs);
        this.back.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Besoin_Activity.this.finish();
            }
        });
        String etBesoins = getIntent().getStringExtra("etBesoins");
        final String memberId = getIntent().getStringExtra("memberId");
        this.etBesoin.setText(etBesoins);
        this.tvNextMotifs.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Besoin_Activity besoin_Activity = Besoin_Activity.this;
                AllSharedPref.save((Context) besoin_Activity, "etBesoin", besoin_Activity.etBesoin.getText().toString());
                Intent intent = new Intent(Besoin_Activity.this, Engagemnets_Activity.class);
                intent.putExtra("memberId", memberId);
                Besoin_Activity.this.startActivity(intent);
            }
        });
    }
}
