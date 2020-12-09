package com.example.worktool_new.Views.Activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.worktool_new.Models.MemberChangePasswordModel;
import com.example.worktool_new.R;
import com.example.worktool_new.Retrofit.Apis;
import com.example.worktool_new.Util.SharedPreference.App;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MemberChangePassword extends AppCompatActivity {
    private Button btnReset;
    /* access modifiers changed from: private */
    public EditText etNewPassword;
    private String memberId;
    private ImageView passwodBack;
    private ProgressDialog progress;
    private TextView tvMemberName;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) R.layout.activity_member_change_password);
        this.tvMemberName = (TextView) findViewById(R.id.tvMemberName);
        this.passwodBack = (ImageView) findViewById(R.id.passwodBack);
        this.etNewPassword = (EditText) findViewById(R.id.etNewPassword);
        this.btnReset = (Button) findViewById(R.id.btnReset);
        String MemberName = getIntent().getStringExtra("memberName");
        this.memberId = getIntent().getStringExtra("memberId");
        this.tvMemberName.setText(MemberName);
        this.passwodBack.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                MemberChangePassword.this.onBackPressed();
            }
        });
        this.btnReset.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (MemberChangePassword.this.etNewPassword.getText().toString().isEmpty() || MemberChangePassword.this.etNewPassword.getText().toString() == null) {
                    Toast.makeText(MemberChangePassword.this, "Please Enter New Password first", 0).show();
                } else {
                    MemberChangePassword.this.resetApi();
                }
            }
        });
    }

    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    /* access modifiers changed from: private */
    public void resetApi() {
        showLoadingDialog();
        ((Apis) new Retrofit.Builder().baseUrl("http://devworktools.fr/contenu/conseiller/").addConverterFactory(GsonConverterFactory.create()).build().create(Apis.class)).resetPassword(Integer.parseInt(App.getAppPreference().getString("LoginId")), this.etNewPassword.getText().toString(), this.memberId).enqueue(new Callback<MemberChangePasswordModel>() {
            public void onResponse(Call<MemberChangePasswordModel> call, Response<MemberChangePasswordModel> response) {
                if (!response.isSuccessful()) {
                    MemberChangePassword.this.dismissLoadingDialog();
                    Toast.makeText(MemberChangePassword.this, "Data Not Found", 0).show();
                } else if (response.body() == null || response.body().getStatus_code() != 200) {
                    MemberChangePassword.this.dismissLoadingDialog();
                    Toast.makeText(MemberChangePassword.this, "Data Not Found", 0).show();
                } else {
                    MemberChangePassword.this.dismissLoadingDialog();
                    Toast.makeText(MemberChangePassword.this, response.body().getMessage(), 0).show();
                    MemberChangePassword.this.sendBroadcast(new Intent("updateData"));
                    if (MemberChangePassword.this.getFragmentManager() != null) {
                        MemberChangePassword.this.getFragmentManager().popBackStack();
                    }
                }
            }

            public void onFailure(Call<MemberChangePasswordModel> call, Throwable t) {
                MemberChangePassword.this.dismissLoadingDialog();
                Toast.makeText(MemberChangePassword.this, t.getLocalizedMessage(), 1).show();
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
