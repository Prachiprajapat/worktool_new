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

public class Situation_Activity extends AppCompatActivity {
    ImageView back;
    EditText etSituationActuelle;
    String etSituationActuelles;
    TextView tvNextMotifs;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) R.layout.situation_actuelle_layout);
        init();
    }

    private void init() {
        this.back = (ImageView) findViewById(R.id.iv_backAddCER);
        this.tvNextMotifs = (TextView) findViewById(R.id.tvNextMotifs);
        this.etSituationActuelle = (EditText) findViewById(R.id.etSituationActuelle);
        this.back.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Situation_Activity.this.finish();
            }
        });
        this.etSituationActuelles = getIntent().getStringExtra("etSituationActuelles");
        final String memberId = getIntent().getStringExtra("memberId");
        Log.i("memid5",getIntent().getStringExtra("memberId")+"");
        this.etSituationActuelle.setText(this.etSituationActuelles);
        this.tvNextMotifs.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Situation_Activity situation_Activity = Situation_Activity.this;
                AllSharedPref.save((Context) situation_Activity, "etSituationActuelle", situation_Activity.etSituationActuelle.getText().toString());
                Intent intent = new Intent(Situation_Activity.this, Besoin_Activity.class);
                intent.putExtra("memberId", memberId);
                Situation_Activity.this.startActivity(intent);
            }
        });
    }
}
