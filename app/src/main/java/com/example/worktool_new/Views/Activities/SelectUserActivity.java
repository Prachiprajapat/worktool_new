package com.example.worktool_new.Views.Activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.worktool_new.Adapters.AdapterUsersData;
import com.example.worktool_new.Models.ToModel;
import com.example.worktool_new.R;
import com.example.worktool_new.Retrofit.Apis;
import com.example.worktool_new.Util.SharedPreference.App;
import java.util.ArrayList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SelectUserActivity extends AppCompatActivity {
    AdapterUsersData adapterUsersData;
    ImageView ivBack;
    ArrayList<ToModel.Datum> mgroupselectedParticiapnsList;
    private ProgressDialog progress;
    RecyclerView rvSelectUsers;
    TextView tvSave;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) R.layout.activity_select_user);
        this.rvSelectUsers = (RecyclerView) findViewById(R.id.rvSelectUsers);
        this.tvSave = (TextView) findViewById(R.id.tvSave);
        ImageView imageView = (ImageView) findViewById(R.id.ivBack);
        this.ivBack = imageView;
        imageView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                SelectUserActivity.this.onBackPressed();
            }
        });
        getUsersData();
        this.tvSave.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                SelectUserActivity.this.mgroupselectedParticiapnsList = new ArrayList<>();
                for (int i = 0; i < SelectUserActivity.this.adapterUsersData.getSelectedItems().size(); i++) {
                    ToModel.Datum toModel = new ToModel.Datum();
                    toModel.setIdCompte(SelectUserActivity.this.adapterUsersData.getSelectedItems().get(i).getIdCompte());
                    toModel.setNom(SelectUserActivity.this.adapterUsersData.getSelectedItems().get(i).getNom());
                    toModel.setPrenom(SelectUserActivity.this.adapterUsersData.getSelectedItems().get(i).getPrenom());
                    toModel.setType(SelectUserActivity.this.adapterUsersData.getSelectedItems().get(i).getType());
                    SelectUserActivity.this.mgroupselectedParticiapnsList.add(toModel);
                }
                Intent intent = new Intent(SelectUserActivity.this, ComposeSentMail.class);
                intent.putExtra("destinationIds", SelectUserActivity.this.mgroupselectedParticiapnsList);
                SelectUserActivity.this.startActivity(intent);
                SelectUserActivity.this.finish();
            }
        });
    }

    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    private void getUsersData() {
        showLoadingDialog();
        ((Apis) new Retrofit.Builder().baseUrl("http://devworktools.fr/contenu/conseiller/").addConverterFactory(GsonConverterFactory.create()).build().create(Apis.class)).getTo(Integer.parseInt(App.getAppPreference().getString("LoginId"))).enqueue(new Callback<ToModel>() {
            public void onResponse(Call<ToModel> call, Response<ToModel> response) {
                if (!response.isSuccessful()) {
                    SelectUserActivity.this.dismissLoadingDialog();
                    Toast.makeText(SelectUserActivity.this, "Data Not Found", 0).show();
                } else if (response.body() != null && response.body().getData().size() > 0) {
                    SelectUserActivity.this.dismissLoadingDialog();
                    SelectUserActivity.this.adapterUsersData = new AdapterUsersData(SelectUserActivity.this, response.body().getData());
                    SelectUserActivity.this.rvSelectUsers.setLayoutManager(new LinearLayoutManager(SelectUserActivity.this, 1, false));
                    SelectUserActivity.this.rvSelectUsers.setAdapter(SelectUserActivity.this.adapterUsersData);
                }
            }

            public void onFailure(Call<ToModel> call, Throwable t) {
                SelectUserActivity.this.dismissLoadingDialog();
                Toast.makeText(SelectUserActivity.this, t.getLocalizedMessage(), 1).show();
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
