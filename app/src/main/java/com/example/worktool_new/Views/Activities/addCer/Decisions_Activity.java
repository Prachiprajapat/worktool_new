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

public class Decisions_Activity extends AppCompatActivity {
    ImageView back;
    /* access modifiers changed from: private */
    public EditText etContratAjournePour;
    /* access modifiers changed from: private */
    public EditText etContratValue;
    /* access modifiers changed from: private */
    public EditText etPrenocisations;
    private TextView tvNext;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) R.layout.decisions_layout);
        init();
    }

    private void init() {
        this.back = (ImageView) findViewById(R.id.iv_backAddCER);
        this.etContratValue = (EditText) findViewById(R.id.etContratValue);
        this.etContratAjournePour = (EditText) findViewById(R.id.etContratAjournePour);
        this.etPrenocisations = (EditText) findViewById(R.id.etPrenocisations);
        this.tvNext = (TextView) findViewById(R.id.tvNext);
        String etContratValues = getIntent().getStringExtra("etContratValue");
        String etContratAjournePours = getIntent().getStringExtra("etContratAjournePour");
        String etPrenocisationss = getIntent().getStringExtra("etPrenocisations");
        final String memberId = getIntent().getStringExtra("memberId");
        Log.i("memid8",getIntent().getStringExtra("memberId")+"");
        this.etContratValue.setText(etContratValues);
        this.etContratAjournePour.setText(etContratAjournePours);
        this.etPrenocisations.setText(etPrenocisationss);
        this.tvNext.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Decisions_Activity decisions_Activity = Decisions_Activity.this;
                AllSharedPref.save((Context) decisions_Activity, "etContratValue", decisions_Activity.etContratValue.getText().toString());
                Decisions_Activity decisions_Activity2 = Decisions_Activity.this;
                AllSharedPref.save((Context) decisions_Activity2, "etContratAjournePour", decisions_Activity2.etContratAjournePour.getText().toString());
                Decisions_Activity decisions_Activity3 = Decisions_Activity.this;
                AllSharedPref.save((Context) decisions_Activity3, "etPrenocisations", decisions_Activity3.etPrenocisations.getText().toString());
                Intent intent = new Intent(Decisions_Activity.this, Duree_Activity.class);
                intent.putExtra("memberId", memberId);
                Decisions_Activity.this.startActivity(intent);
            }
        });
        this.back.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Decisions_Activity.this.finish();
            }
        });
    }
}
