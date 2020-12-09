package com.example.worktool_new.Views.Activities.addCer;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.worktool_new.Adapters.Adapter_Engagements;
import com.example.worktool_new.Models.GetEngagementModel;
import com.example.worktool_new.R;
import com.example.worktool_new.Util.SharedPreference.App;
import java.util.ArrayList;

public class Engagemnets_Activity extends AppCompatActivity {
    Adapter_Engagements adapter_engagements;
    ArrayList<GetEngagementModel.Datum> arrayList;
    ImageView back;
    RecyclerView rvEngagements;
    TextView tv_next;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) R.layout.engagements_layout);
        init();
    }

    private void init() {
        this.back = (ImageView) findViewById(R.id.iv_backAddCER);
        this.tv_next = (TextView) findViewById(R.id.tv_next);
        this.rvEngagements = (RecyclerView) findViewById(R.id.rvEngagements);
        this.back.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Engagemnets_Activity.this.finish();
            }
        });
        this.arrayList = new ArrayList<>();
        this.arrayList = (ArrayList) getIntent().getSerializableExtra("engagementList");
        final String memberId = getIntent().getStringExtra("memberId");
        this.adapter_engagements = new Adapter_Engagements(this, this.arrayList);
        this.rvEngagements.setLayoutManager(new LinearLayoutManager(this, 1, false));
        this.rvEngagements.setAdapter(this.adapter_engagements);
        this.tv_next.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                App.getAppPreference().saveEngagementArrayList(Engagemnets_Activity.this.arrayList);
                Intent intent = new Intent(Engagemnets_Activity.this, Prochains_Activity.class);
                intent.putExtra("memberId", memberId);
                Engagemnets_Activity.this.startActivity(intent);
            }
        });
    }
}
