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

public class Motifs_Activity extends AppCompatActivity {
    ImageView back;
    EditText etmotifs;
    TextView tvNextMotifs;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) R.layout.motifs_layout);
        init();
    }

    private void init() {
        this.back = (ImageView) findViewById(R.id.iv_backAddCER);
        this.etmotifs = (EditText) findViewById(R.id.etmotifs);
        this.tvNextMotifs = (TextView) findViewById(R.id.tvNextMotifs);
        this.back.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Motifs_Activity.this.finish();
            }
        });
        String etmotifss = getIntent().getStringExtra("etmotifs");
        final String memberId = getIntent().getStringExtra("memberId");
        Log.i("memid2",getIntent().getStringExtra("memberId")+"");
        if (etmotifss != null) {
            this.etmotifs.setText(etmotifss);
        }
        this.tvNextMotifs.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Motifs_Activity motifs_Activity = Motifs_Activity.this;
                AllSharedPref.save((Context) motifs_Activity, "etmotifs", motifs_Activity.etmotifs.getText().toString());
                Intent intent = new Intent(Motifs_Activity.this, Atouts_Activity.class);
                intent.putExtra("memberId", memberId);
                Motifs_Activity.this.startActivity(intent);
            }
        });
    }
}
