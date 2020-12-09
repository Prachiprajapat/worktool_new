package com.example.worktool_new.Views.Activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import com.example.worktool_new.Adapters.CustomToDataAdapter;
import com.example.worktool_new.Models.ComposeMailModel;
import com.example.worktool_new.Models.ToModel;
import com.example.worktool_new.R;
import com.example.worktool_new.Retrofit.Apis;
import com.example.worktool_new.Util.SharedPreference.App;
import com.example.worktool_new.Util.Validations;
import com.theartofdev.edmodo.cropper.CropImage;

import org.w3c.dom.Text;

import java.io.File;
import java.io.PrintStream;
import java.net.URI;
import java.util.concurrent.TimeUnit;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ComposeMail extends AppCompatActivity {
    private String[] PERMISSIONSLoc = {"android.permission.READ_EXTERNAL_STORAGE", "android.permission.WRITE_EXTERNAL_STORAGE", "android.permission.CAMERA"};
    private int PERMISSION_Location = 2;
    CustomToDataAdapter customToDataAdapter;
    EditText etMessage;
    EditText etSubject;
    String imageUri;
    private Boolean ispermission = false;
    ImageView ivAttachment;
    private ProgressDialog progress;
    Spinner spinnerTo;
    int spinnerposition;
    TextView tvSendMail,tvAttachment;
    Validations validations;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) R.layout.activity_compose_mail);
        this.spinnerTo = (Spinner) findViewById(R.id.spinnerTo);
        this.etSubject = (EditText) findViewById(R.id.etSubject);
        this.etMessage = (EditText) findViewById(R.id.etMessage);
        this.tvSendMail = (TextView) findViewById(R.id.tvSendMail);
        this.ivAttachment = (ImageView) findViewById(R.id.ivAttachment);
        tvAttachment=(TextView)findViewById(R.id.tv_attachement);
        this.tvSendMail.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                ComposeMail.this.postMail();
            }
        });
        getSpinnerData();
        this.ivAttachment.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                getLocation();
            }
        });
    }

    /* access modifiers changed from: private */
    public void getLocation() {
        if (!hasPermissions(this, this.PERMISSIONSLoc)) {
            ActivityCompat.requestPermissions(this, this.PERMISSIONSLoc, PERMISSION_Location);
            return;
        }
        CropImage.activity().start(this);
        this.ispermission = true;
    }

    public boolean hasPermissions(Context context, String... permissions) {
        if (context == null || permissions == null) {
            return true;
        }
        for (String permission : permissions) {
            if (ActivityCompat.checkSelfPermission(context, permission) != 0) {
                return false;
            }
        }
        return true;
    }

    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == this.PERMISSION_Location && grantResults[0] == 0) {
            this.ispermission = true;
            CropImage.activity().start(this);
            return;
        }
        Toast.makeText(this, "Please grant permisions", 0).show();
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        CropImage.ActivityResult result = CropImage.getActivityResult(data);
        if (resultCode == -1) {
            this.imageUri = String.valueOf(result.getUri());
            Uri uri = result.getUri();
            tvAttachment.setText(uri.getLastPathSegment());
            tvAttachment.setVisibility(View.VISIBLE);
        }
    }

    public void getSpinnerData() {
        showLoadingDialog();
        ((Apis) new Retrofit.Builder().baseUrl("http://devworktools.fr/contenu/conseiller/").addConverterFactory(GsonConverterFactory.create()).build().create(Apis.class)).getTo(Integer.parseInt(App.getAppPreference().getString("LoginId"))).enqueue(new Callback<ToModel>() {
            public void onResponse(Call<ToModel> call, Response<ToModel> response) {
                if (!response.isSuccessful()) {
                    ComposeMail.this.dismissLoadingDialog();
                    Toast.makeText(ComposeMail.this, "Data Not Found", 0).show();
                } else if (response.body() != null && response.body().getData().size() > 0) {
                    ComposeMail.this.dismissLoadingDialog();
                    ComposeMail.this.customToDataAdapter = new CustomToDataAdapter(ComposeMail.this, response.body().getData());
                    ComposeMail.this.spinnerTo.setAdapter(ComposeMail.this.customToDataAdapter);
                    ComposeMail.this.spinnerTo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                            ComposeMail.this.spinnerposition = position;
                            Log.e("hjsdhf", String.valueOf(position));
                        }

                        public void onNothingSelected(AdapterView<?> adapterView) {
                        }
                    });
                }
            }

            public void onFailure(Call<ToModel> call, Throwable t) {
                ComposeMail.this.dismissLoadingDialog();
                Toast.makeText(ComposeMail.this, t.getLocalizedMessage(), 1).show();
            }
        });
    }

    public void postMail() {
        showLoadingDialog();
        Validations validations2 = new Validations();
        this.validations = validations2;
        if (validations2.ValidateComposeMailPostData(this.etSubject.getText().toString().trim(), this.etMessage.getText().toString().trim())) {
            MultipartBody.Part ImagePic = null;
            PrintStream printStream = System.out;
            printStream.println("asKAJ" + this.imageUri);
            if (this.imageUri != null) {
                File file = new File(Uri.parse(this.imageUri).getPath());
                ImagePic = MultipartBody.Part.createFormData("fichier1", file.getName(), RequestBody.create(MediaType.parse("image/jpeg"), file));
            }
            ((Apis) new Retrofit.Builder().baseUrl("http://devworktools.fr/contenu/conseiller/").addConverterFactory(GsonConverterFactory.create()).client(new OkHttpClient.Builder().addInterceptor(provideHttpLoggingInterceptor()).readTimeout(120, TimeUnit.SECONDS).writeTimeout(120, TimeUnit.SECONDS).build()).build().create(Apis.class)).postMail(RequestBody.create(MediaType.parse("text/plain"), App.getAppPreference().getString("LoginId")), RequestBody.create(MediaType.parse("text/plain"), String.valueOf(Integer.parseInt(String.valueOf(this.spinnerTo.getSelectedItemId())))), RequestBody.create(MediaType.parse("text/plain"), this.etSubject.getText().toString().trim()), RequestBody.create(MediaType.parse("text/plain"), this.etMessage.getText().toString().trim()), ImagePic).enqueue(new Callback<ComposeMailModel>() {
                public void onResponse(Call<ComposeMailModel> call, Response<ComposeMailModel> response) {
                    if (!response.isSuccessful()) {
                        ComposeMail.this.dismissLoadingDialog();
                        Toast.makeText(ComposeMail.this, "Data Not Found", 0).show();
                    } else if (response.body() != null && response.body().getStatus().intValue() == 200) {
                        ComposeMail.this.dismissLoadingDialog();
                        ComposeMail.this.finish();
                    }
                }

                public void onFailure(Call<ComposeMailModel> call, Throwable t) {
                    ComposeMail.this.dismissLoadingDialog();
                    Toast.makeText(ComposeMail.this, t.getLocalizedMessage(), 1).show();
                }
            });
        }
    }

    private static HttpLoggingInterceptor provideHttpLoggingInterceptor() {
        return new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            public void log(String message) {
                Log.d("Injector", message);
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
