package com.example.worktool_new.Views.Activities;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.worktool_new.Adapters.Adapter_Participants;
import com.example.worktool_new.Models.ParticipationListModel;
import com.example.worktool_new.R;
import com.example.worktool_new.Retrofit.Apis;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ParticipentsList extends AppCompatActivity {
    /* access modifiers changed from: private */
    public Adapter_Participants adapter_participants;
    private ProgressDialog progress;
    /* access modifiers changed from: private */
    public RecyclerView rvParticipantList;
    ImageView backbutton;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) R.layout.activity_participents_list);
        this.rvParticipantList = (RecyclerView) findViewById(R.id.rvParticipantList);
        backbutton = (ImageView) findViewById(R.id.ivBackParticiapnt);
        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        getUsersData();
    }

    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    private void getUsersData() {
        showLoadingDialog();
        ((Apis) new Retrofit.Builder().baseUrl("http://devworktools.fr/contenu/conseiller/").addConverterFactory(GsonConverterFactory.create()).build().create(Apis.class)).getParticipationList(Integer.parseInt(getIntent().getStringExtra("idEvent"))).enqueue(new Callback<ParticipationListModel>() {
            public void onResponse(Call<ParticipationListModel> call, Response<ParticipationListModel> response) {
                if (!response.isSuccessful()) {
                    ParticipentsList.this.dismissLoadingDialog();
                    Toast.makeText(ParticipentsList.this, "Data Not Found", 0).show();
                } else if (response.body() != null && response.body().getData().size() > 0) {
                    ParticipentsList.this.dismissLoadingDialog();
                    Adapter_Participants unused = ParticipentsList.this.adapter_participants = new Adapter_Participants(ParticipentsList.this, response.body().getData());
                    ParticipentsList.this.rvParticipantList.setLayoutManager(new LinearLayoutManager(ParticipentsList.this, 1, false));
                    ParticipentsList.this.rvParticipantList.setAdapter(ParticipentsList.this.adapter_participants);
                }
            }

            public void onFailure(Call<ParticipationListModel> call, Throwable t) {
                ParticipentsList.this.dismissLoadingDialog();
                Toast.makeText(ParticipentsList.this, t.getLocalizedMessage(), 1).show();
            }
        });
    }

    public void showLoadingDialog() {
        if (this.progress == null) {
            ProgressDialog progressDialog = new ProgressDialog(this);
            this.progress = progressDialog;
            progressDialog.setTitle(getString(R.string.loading_title));
            this.progress.setMessage(getString(R.string.loading_message));
            this.progress.setCancelable(false);
        }
        this.progress.show();
    }

    public void dismissLoadingDialog() {
        ProgressDialog progressDialog = this.progress;
        if (progressDialog != null && progressDialog.isShowing()) {
            this.progress.dismiss();
        }
    }
}
