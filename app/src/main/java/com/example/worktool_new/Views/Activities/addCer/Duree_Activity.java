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

public class Duree_Activity extends AppCompatActivity {
    ImageView back;
    /* access modifiers changed from: private */
    public EditText etDureeValidationDuContrat;
    private TextView tvNext;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) R.layout.duree_layout);
        init();
    }

    private void init() {
        this.back = (ImageView) findViewById(R.id.iv_backAddCER);
        this.etDureeValidationDuContrat = (EditText) findViewById(R.id.etDureeValidationDuContrat);
        this.tvNext = (TextView) findViewById(R.id.tvNext);
        this.back.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Duree_Activity.this.finish();
            }
        });
        String etDureeValidationDuContrats = getIntent().getStringExtra("etDureeValidationDuContrat");
        final String memberId = getIntent().getStringExtra("memberId");
        this.etDureeValidationDuContrat.setText(etDureeValidationDuContrats);
        this.tvNext.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Duree_Activity duree_Activity = Duree_Activity.this;
                AllSharedPref.save((Context) duree_Activity, "etDureeValidationDuContrat", duree_Activity.etDureeValidationDuContrat.getText().toString());
                Intent intent = new Intent(Duree_Activity.this, Signature_Activity.class);
                intent.putExtra("memberId", memberId);
                Duree_Activity.this.startActivity(intent);
                Duree_Activity.this.finish();
            }
        });
    }
}
