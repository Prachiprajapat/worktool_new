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

public class Freins_Activity extends AppCompatActivity {
    ImageView back;
    EditText etEnvironmentSocial;
    EditText etLogement;
    EditText etMobLite;
    EditText etSante;
    EditText etSituation;
    TextView tv_next;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) R.layout.friens_layout);
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
        String etSantesFreins = getIntent().getStringExtra("etSanteFreins");
        String etMobLitesFreins = getIntent().getStringExtra("etMobLiteFreins");
        String etLogementsFreins = getIntent().getStringExtra("etLogementFreins");
        String etEnvironmentSocialsFreins = getIntent().getStringExtra("etEnvironmentSocialFreins");
        String etSituationsFreins = getIntent().getStringExtra("etSituationFreins");
        final String memberId = getIntent().getStringExtra("memberId");
        this.etSante.setText(etSantesFreins);
        this.etMobLite.setText(etMobLitesFreins);
        this.etLogement.setText(etLogementsFreins);
        this.etEnvironmentSocial.setText(etEnvironmentSocialsFreins);
        this.etSituation.setText(etSituationsFreins);
        this.tv_next.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Freins_Activity freins_Activity = Freins_Activity.this;
                AllSharedPref.save((Context) freins_Activity, "etSanteFreins", freins_Activity.etSante.getText().toString());
                Freins_Activity freins_Activity2 = Freins_Activity.this;
                AllSharedPref.save((Context) freins_Activity2, "etMobLiteFreins", freins_Activity2.etMobLite.getText().toString());
                Freins_Activity freins_Activity3 = Freins_Activity.this;
                AllSharedPref.save((Context) freins_Activity3, "etLogementFreins", freins_Activity3.etLogement.getText().toString());
                Freins_Activity freins_Activity4 = Freins_Activity.this;
                AllSharedPref.save((Context) freins_Activity4, "etEnvironmentSocialFreins", freins_Activity4.etEnvironmentSocial.getText().toString());
                Freins_Activity freins_Activity5 = Freins_Activity.this;
                AllSharedPref.save((Context) freins_Activity5, "etSituationFreins", freins_Activity5.etSituation.getText().toString());
                Intent intent = new Intent(Freins_Activity.this, Freins_Activity.class);
                intent.putExtra("memberId", memberId);
                Freins_Activity.this.startActivity(intent);
            }
        });
        this.back.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Freins_Activity.this.finish();
            }
        });
    }
}
