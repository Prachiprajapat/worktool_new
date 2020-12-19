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

public class Cardonnes_Activity extends AppCompatActivity {
    ImageView back;
    EditText etAssociation;
    EditText etccas;
    EditText etmds;
    TextView tvNext;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) R.layout.coordennees_layout);
        init();
    }

    private void init() {
        this.back = (ImageView) findViewById(R.id.iv_backAddCER);
        this.etmds = (EditText) findViewById(R.id.etmds);
        this.etccas = (EditText) findViewById(R.id.etccas);
        this.tvNext = (TextView) findViewById(R.id.tvNext);
        this.etAssociation = (EditText) findViewById(R.id.etAssociation);
        this.back.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Cardonnes_Activity.this.finish();
            }
        });
        String mds = getIntent().getStringExtra("mds");
        Log.i("mds",getIntent().getStringExtra("mds")+"");
        String ccas = getIntent().getStringExtra("ccas");
        String association = getIntent().getStringExtra("association");
        final String memberId = getIntent().getStringExtra("memberId");
        this.etmds.setText(mds);
        this.etccas.setText(ccas);
        this.etAssociation.setText(association);
        this.tvNext.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Cardonnes_Activity cardonnes_Activity = Cardonnes_Activity.this;
                AllSharedPref.save((Context) cardonnes_Activity, "etmds", cardonnes_Activity.etmds.getText().toString());
                Cardonnes_Activity cardonnes_Activity2 = Cardonnes_Activity.this;
                AllSharedPref.save((Context) cardonnes_Activity2, "etccas", cardonnes_Activity2.etccas.getText().toString());
                Cardonnes_Activity cardonnes_Activity3 = Cardonnes_Activity.this;
                AllSharedPref.save((Context) cardonnes_Activity3, "etAssociation", cardonnes_Activity3.etAssociation.getText().toString());
                Intent intent = new Intent(Cardonnes_Activity.this, Motifs_Activity.class);
                intent.putExtra("memberId", memberId);
                Cardonnes_Activity.this.startActivity(intent);
            }
        });
    }
}
