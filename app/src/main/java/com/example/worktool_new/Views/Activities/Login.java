package com.example.worktool_new.Views.Activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.worktool_new.Models.LoginModel;
import com.example.worktool_new.R;
import com.example.worktool_new.Retrofit.Apis;
import com.example.worktool_new.Util.Common;
import com.example.worktool_new.Util.SharedPreference.App;
import com.example.worktool_new.Util.SharedPreference.AppConstants;
import com.example.worktool_new.Util.SharedPreference.AppPreferences;
import com.example.worktool_new.Util.Validations;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Login extends AppCompatActivity {
    Button bt_login;
    EditText editTextMail;
    EditText editTextPassword;
    private ProgressDialog progress;
    TextView resetPassword;
    Validations validations;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) R.layout.activity_login);
        this.validations = new Validations();
        this.bt_login = (Button) findViewById(R.id.bt_login);
        this.resetPassword = (TextView) findViewById(R.id.resetPassword);
        this.editTextMail = (EditText) findViewById(R.id.editTextMail);
        this.editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        this.bt_login.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String finalEmail = Login.this.editTextMail.getText().toString().trim();
                String finalPassword = Login.this.editTextPassword.getText().toString().trim();
                if (Login.this.validations.ValidateLoginData(finalEmail, finalPassword)) {
                    Login.this.showLoadingDialog();
                    Login.this.login(finalEmail, finalPassword);
                    return;
                }
                Login.this.dismissLoadingDialog();
                Toast.makeText(Login.this, Validations.error_message, 0).show();
            }
        });
        this.resetPassword.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Login.this.startActivity(new Intent(Login.this, MemberChangePassword.class));
            }
        });
    }

    /* access modifiers changed from: private */
    public void login(final String email, String password) {
        ((Apis) new Retrofit.Builder().baseUrl("http://devworktools.fr/").addConverterFactory(GsonConverterFactory.create()).build().create(Apis.class)).login(email, password).enqueue(new Callback<LoginModel>() {
            public void onResponse(Call<LoginModel> call, Response<LoginModel> response) {
                if (!Common.InternetCheck(Login.this)) {
                    Toast.makeText(Login.this, "Internet Not Available, Please Check Your Internet Connection", 0).show();
                } else if (!response.isSuccessful()) {
                    Toast.makeText(Login.this, "Data Not Found", 0).show();
                } else if (response.body() == null) {
                    Login.this.dismissLoadingDialog();
                    Login login = Login.this;
                    Toast.makeText(login, "" + response.body().getMessage(), 0).show();
                } else if (response.body().getStatusCode() == null || response.body().getStatusCode().intValue() != 200) {
                    Login.this.dismissLoadingDialog();
                    Login login2 = Login.this;
                    Toast.makeText(login2, "" + response.body().getMessage(), 0).show();
                } else {
                    App.getAppPreference().saveString("time", "first");
                    Login.this.dismissLoadingDialog();
                    if (email.isEmpty()) {
                        Toast.makeText(Login.this.getApplicationContext(), "Provide us an email according to your profile", 0).show();
                    } else if (response.body().getData().get(0).getType().equals("conseiller")) {
                        Intent intent = new Intent(Login.this, MainActivity.class);
                        App.getAppPreference().saveBoolean(AppConstants.IS_LOGIN, true);
                        App.getAppPreference().saveString("type", "counselor");
                        App.getAppPreference().saveString("profileImage", response.body().getData().get(0).getImage());
                        App.getAppPreference().saveString("LoginId", response.body().getData().get(0).getIdCompte());
                        App.getAppPreference().saveString("dob", response.body().getData().get(0).getDob());
                        AppPreferences appPreference = App.getAppPreference();
                        appPreference.saveString("Name", response.body().getData().get(0).getPrenom() + " " + response.body().getData().get(0).getNom());
                        Login.this.startActivity(intent);
                        Login.this.finish();
                    } else if (response.body().getData().get(0).getType().equals("jeune")) {
                        Intent intent2 = new Intent(Login.this, MainActivity.class);
                        App.getAppPreference().saveBoolean(AppConstants.IS_LOGIN, true);
                        App.getAppPreference().saveString("type", "benificiary");
                        App.getAppPreference().saveString("profileImage", response.body().getData().get(0).getImage());
                        App.getAppPreference().saveString("LoginId", response.body().getData().get(0).getIdCompte());
                        App.getAppPreference().saveString("dob", response.body().getData().get(0).getDob());
                        AppPreferences appPreference2 = App.getAppPreference();
                        appPreference2.saveString("Name", response.body().getData().get(0).getPrenom() + " " + response.body().getData().get(0).getNom());
                        Login.this.startActivity(intent2);
                        Login.this.finish();
                    } else if (response.body().getData().get(0).getType().equals("referent")) {
                        Intent intent3 = new Intent(Login.this, MainActivity.class);
                        App.getAppPreference().saveBoolean(AppConstants.IS_LOGIN, true);
                        App.getAppPreference().saveString("type", "referent");
                        App.getAppPreference().saveString("profileImage", response.body().getData().get(0).getImage());
                        App.getAppPreference().saveString("LoginId", response.body().getData().get(0).getIdCompte());
                        App.getAppPreference().saveString("dob", response.body().getData().get(0).getDob());
                        AppPreferences appPreference3 = App.getAppPreference();
                        appPreference3.saveString("Name", response.body().getData().get(0).getPrenom() + " " + response.body().getData().get(0).getNom());
                        Login.this.startActivity(intent3);
                        Login.this.finish();
                    } else if (response.body().getData().get(0).getType().equals("admin")) {
                        Intent intent4 = new Intent(Login.this, MainActivity.class);
                        App.getAppPreference().saveBoolean(AppConstants.IS_LOGIN, true);
                        App.getAppPreference().saveString("type", "godfather");
                        App.getAppPreference().saveString("profileImage", response.body().getData().get(0).getImage());
                        App.getAppPreference().saveString("LoginId", response.body().getData().get(0).getIdCompte());
                        App.getAppPreference().saveString("dob", response.body().getData().get(0).getDob());
                        AppPreferences appPreference4 = App.getAppPreference();
                        appPreference4.saveString("Name", response.body().getData().get(0).getPrenom() + " " + response.body().getData().get(0).getNom());
                        Login.this.startActivity(intent4);
                        Login.this.finish();
                    } else if (response.body().getData().get(0).getType().equals("parrain")) {
                        Intent intent5 = new Intent(Login.this, MainActivity.class);
                        App.getAppPreference().saveBoolean(AppConstants.IS_LOGIN, true);
                        App.getAppPreference().saveString("type", "godfather");
                        App.getAppPreference().saveString("profileImage", response.body().getData().get(0).getImage());
                        App.getAppPreference().saveString("LoginId", response.body().getData().get(0).getIdCompte());
                        App.getAppPreference().saveString("dob", response.body().getData().get(0).getDob());
                        AppPreferences appPreference5 = App.getAppPreference();
                        appPreference5.saveString("Name", response.body().getData().get(0).getPrenom() + " " + response.body().getData().get(0).getNom());
                        Login.this.startActivity(intent5);
                        Login.this.finish();
                    } else if (response.body().getData().get(0).getType().equals("professionnel")) {
                        Intent intent6 = new Intent(Login.this, MainActivity.class);
                        App.getAppPreference().saveBoolean(AppConstants.IS_LOGIN, true);
                        App.getAppPreference().saveString("type", "professionel");
                        App.getAppPreference().saveString("profileImage", response.body().getData().get(0).getImage());
                        App.getAppPreference().saveString("LoginId", response.body().getData().get(0).getIdCompte());
                        App.getAppPreference().saveString("dob", response.body().getData().get(0).getDob());
                        AppPreferences appPreference6 = App.getAppPreference();
                        appPreference6.saveString("Name", response.body().getData().get(0).getPrenom() + " " + response.body().getData().get(0).getNom());
                        Login.this.startActivity(intent6);
                        Login.this.finish();
                    } else {
                        Toast.makeText(Login.this, "Please Enter Valid Email and password", 0).show();
                    }
                }
            }

            public void onFailure(Call<LoginModel> call, Throwable t) {
                Login.this.dismissLoadingDialog();
                if (t instanceof UnknownHostException) {
                    Common.showToast(Login.this, AppConstants.INTERNET_CHECK);
                } else if (t instanceof SocketTimeoutException) {
                    Common.showToast(Login.this, "Server is not responding. Please try again");
                } else if (t instanceof ConnectException) {
                    Common.showToast(Login.this, "Failed to connect server");
                } else {
                    Common.showToast(Login.this, "something went wrong !! please try again");
                }
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
