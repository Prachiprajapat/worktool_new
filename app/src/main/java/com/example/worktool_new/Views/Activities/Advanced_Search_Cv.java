package com.example.worktool_new.Views.Activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.worktool_new.Models.AdvanceSearchCVModel;
import com.example.worktool_new.R;
import com.example.worktool_new.Retrofit.Apis;
import com.example.worktool_new.Util.SharedPreference.App;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Advanced_Search_Cv extends AppCompatActivity {
    ArrayList<AdvanceSearchCVModel.Body> arrayList;
    private Button btnAdvanceSearch;
    String dispo = "";
    private EditText etDesiredPosition;
    private EditText etExperience;
    private EditText etSkill;
    private EditText etTitleCv;
    private EditText etTraining;
    private ProgressDialog progress;
    private Spinner spinnerCity;
    private Spinner spinnerPermit;
    private Switch switchAvailableDevices;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) R.layout.activity_advanced__search__cv);
        this.btnAdvanceSearch = (Button) findViewById(R.id.btnAdvanceSearch);
        this.switchAvailableDevices = (Switch) findViewById(R.id.switchAvailableDevices);
        this.etTitleCv = (EditText) findViewById(R.id.etTitleCv);
        this.etDesiredPosition = (EditText) findViewById(R.id.etDesiredPosition);
        this.etExperience = (EditText) findViewById(R.id.etExperience);
        this.etTraining = (EditText) findViewById(R.id.etTraining);
        this.etSkill = (EditText) findViewById(R.id.etSkill);
        this.spinnerPermit = (Spinner) findViewById(R.id.spinnerPermit);
        this.spinnerCity = (Spinner) findViewById(R.id.spinnerCity);
        ArrayList<String> permitList = new ArrayList<>();
        permitList.add("sans interet");
        permitList.add("aucun");
        permitList.add("Permis A");
        permitList.add("Permis B");
        permitList.add("Permis poids lourd");
        ArrayAdapter<String> permitAdapter = new ArrayAdapter<>(this, 17367048, permitList);
        permitAdapter.setDropDownViewResource(17367049);
        this.spinnerPermit.setAdapter(permitAdapter);
        ArrayList<String> citylist = new ArrayList<>();
        citylist.add("Beauvaisis - Oise Picarde");
        citylist.add("Bray - Vexin - Sablons - Thelle");
        citylist.add("Creil - Clermont");
        citylist.add("Valois - Halatte");
        citylist.add("Noyon - Compiègne");
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, 17367048, citylist);
        arrayAdapter.setDropDownViewResource(17367049);
        this.spinnerCity.setAdapter(arrayAdapter);
        this.btnAdvanceSearch.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Advanced_Search_Cv.this.postAdvanceCvList();
            }
        });
    }

    /* access modifiers changed from: private */
    public void postAdvanceCvList() {
        showLoadingDialog();
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://devworktools.fr/contenu/conseiller/").addConverterFactory(GsonConverterFactory.create()).client(new OkHttpClient.Builder().addInterceptor(provideHttpLoggingInterceptor()).readTimeout(120, TimeUnit.SECONDS).writeTimeout(120, TimeUnit.SECONDS).build()).build();
        if (this.switchAvailableDevices.isChecked()) {
            this.dispo = "oui";
        } else {
            this.dispo = "";
        }
        int Id = Integer.valueOf(App.getAppPreference().getString("LoginId")).intValue();
        final Gson gson = new Gson();
        Call<AdvanceSearchCVModel> call = ((Apis) retrofit.create(Apis.class)).advanceSearch(Id, this.dispo, this.etTitleCv.getText().toString(), this.etDesiredPosition.getText().toString(), this.etSkill.getText().toString(), this.etExperience.getText().toString(), this.etTraining.getText().toString(), this.spinnerPermit.getSelectedItem().toString(), this.spinnerCity.getSelectedItem().toString());
        Log.e("advanceSearchSent", Id + this.dispo + this.etTitleCv.getText().toString() + this.etDesiredPosition.getText().toString() + this.etSkill.getText().toString() + this.etExperience.getText().toString() + this.etTraining.getText().toString() + this.spinnerPermit.getSelectedItem().toString() + this.spinnerCity.getSelectedItem().toString());
        call.enqueue(new Callback<AdvanceSearchCVModel>() {
            public void onResponse(Call<AdvanceSearchCVModel> call, Response<AdvanceSearchCVModel> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Log.e("advanceSearch", gson.toJson((Object) response.body()));
                    if (response.body().getStatus_code() == null || response.body().getStatus_code().intValue() != 200) {
                        Advanced_Search_Cv.this.dismissLoadingDialog();
                        Toast.makeText(Advanced_Search_Cv.this, response.body().getMessage(), 1).show();
                    } else if (response.body() != null && response.body().getData().size() > 0) {
                        Advanced_Search_Cv.this.arrayList = response.body().getData();
                        Advanced_Search_Cv.this.dismissLoadingDialog();
                        Toast.makeText(Advanced_Search_Cv.this, response.body().getMessage(), 0).show();
                        Intent intent = new Intent();
                        intent.putExtra("advanceSearchDataList", Advanced_Search_Cv.this.arrayList);
                        App.getAppPreference().saveString("time", "first");
                        Advanced_Search_Cv.this.setResult(-1, intent);
                        Advanced_Search_Cv.this.finish();
                    }
                }
            }

            public void onFailure(Call<AdvanceSearchCVModel> call, Throwable t) {
                Advanced_Search_Cv.this.dismissLoadingDialog();
                Toast.makeText(Advanced_Search_Cv.this, t.getLocalizedMessage(), 1).show();
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

    private static HttpLoggingInterceptor provideHttpLoggingInterceptor() {
        return new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            public void log(String message) {
                Log.d("Injector", message);
            }
        });
    }
}
