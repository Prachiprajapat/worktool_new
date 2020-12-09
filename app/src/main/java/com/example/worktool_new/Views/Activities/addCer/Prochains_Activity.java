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

public class Prochains_Activity extends AppCompatActivity {
    ImageView back;
    EditText etprochainBeneficiary;
    TextView tvNext;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) R.layout.prochain_layout);
        init();
    }

    private void init() {
        this.back = (ImageView) findViewById(R.id.iv_backAddCER);
        this.etprochainBeneficiary = (EditText) findViewById(R.id.etprochainBeneficiary);
        this.tvNext = (TextView) findViewById(R.id.tvNext);
        this.back.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Prochains_Activity.this.finish();
            }
        });
        String etprochainBeneficiarys = getIntent().getStringExtra("etprochainBeneficiary");
        final String memberId = getIntent().getStringExtra("memberId");
        this.etprochainBeneficiary.setText(etprochainBeneficiarys);
        this.tvNext.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Prochains_Activity prochains_Activity = Prochains_Activity.this;
                AllSharedPref.save((Context) prochains_Activity, "etprochainBeneficiary", prochains_Activity.etprochainBeneficiary.getText().toString());
                Intent intent = new Intent(Prochains_Activity.this, Decisions_Activity.class);
                intent.putExtra("memberId", memberId);
                Prochains_Activity.this.startActivity(intent);
            }
        });
    }
}
