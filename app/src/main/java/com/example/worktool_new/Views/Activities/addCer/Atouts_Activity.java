package com.example.worktool_new.Views.Activities.addCer;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.worktool_new.R;
import com.example.worktool_new.Util.AllSharedPref;

public class Atouts_Activity extends AppCompatActivity {
    ImageView back;
    EditText etEnvironmentSocial;
    EditText etLogement;
    EditText etMobLite;
    EditText etSante;
    EditText etSituation;
    TextView tv_next;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) R.layout.atouts_layout);
        init();
    }

    private void init() {
        this.back = (ImageView) findViewById(R.id.iv_backAddCER);
        this.etSante = (EditText) findViewById(R.id.etSante);
        this.etMobLite = (EditText) findViewById(R.id.etMobLite);
        this.etLogement = (EditText) findViewById(R.id.etLogement);
        this.etEnvironmentSocial = (EditText) findViewById(R.id.etEnvironmentSocial);
        this.etSituation = (EditText) findViewById(R.id.etSituation);
        this.tv_next = (TextView) findViewById(R.id.tv_next);
        this.back.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Atouts_Activity.this.finish();
            }
        });
        String etSantes = getIntent().getStringExtra("etSante");
        String etMobLites = getIntent().getStringExtra("etMobLite");
        String etLogements = getIntent().getStringExtra("etLogement");
        String etEnvironmentSocials = getIntent().getStringExtra("etEnvironmentSocial");
        String etSituations = getIntent().getStringExtra("etSituation");
        final String memberId = getIntent().getStringExtra("memberId");
        Log.i("memid3",getIntent().getStringExtra("memberId")+"");
        this.etSante.setText(etSantes);
        this.etMobLite.setText(etMobLites);
        this.etLogement.setText(etLogements);
        this.etEnvironmentSocial.setText(etEnvironmentSocials);
        this.etSituation.setText(etSituations);
        this.tv_next.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Atouts_Activity atouts_Activity = Atouts_Activity.this;
                AllSharedPref.save((Context) atouts_Activity, "etSante", atouts_Activity.etSante.getText().toString());
                Atouts_Activity atouts_Activity2 = Atouts_Activity.this;
                AllSharedPref.save((Context) atouts_Activity2, "etMobLite", atouts_Activity2.etMobLite.getText().toString());
                Atouts_Activity atouts_Activity3 = Atouts_Activity.this;
                AllSharedPref.save((Context) atouts_Activity3, "etLogement", atouts_Activity3.etLogement.getText().toString());
                Atouts_Activity atouts_Activity4 = Atouts_Activity.this;
                AllSharedPref.save((Context) atouts_Activity4, "etEnvironmentSocial", atouts_Activity4.etEnvironmentSocial.getText().toString());
                Atouts_Activity atouts_Activity5 = Atouts_Activity.this;
                AllSharedPref.save((Context) atouts_Activity5, "etSituation", atouts_Activity5.etSituation.getText().toString());
                Intent intent = new Intent(Atouts_Activity.this, Freins_Activity.class);
                intent.putExtra("memberId", memberId);
                Atouts_Activity.this.startActivity(intent);
            }
        });
    }
}
