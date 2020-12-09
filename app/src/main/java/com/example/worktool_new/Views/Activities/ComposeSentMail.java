package com.example.worktool_new.Views.Activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import com.example.worktool_new.Models.ComposeMailModel;
import com.example.worktool_new.Models.ToModel;
import com.example.worktool_new.R;
import com.example.worktool_new.Retrofit.Apis;
import com.example.worktool_new.Util.SharedPreference.App;
import com.example.worktool_new.Util.Validations;
import com.theartofdev.edmodo.cropper.CropImage;
import java.io.File;
import java.io.PrintStream;
import java.util.ArrayList;
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

public class ComposeSentMail extends AppCompatActivity {
    private String[] PERMISSIONSLoc = {"android.permission.READ_EXTERNAL_STORAGE", "android.permission.WRITE_EXTERNAL_STORAGE", "android.permission.CAMERA"};
    private int PERMISSION_Location = 2;
    EditText etMessage;
    EditText etSubject;
    TextView etTo;
    String idNames = "";
    String idss = "";
    String imageUri;
    private Boolean ispermission = false;
    ImageView ivAttachment;
    private ProgressDialog progress;
    TextView tvSendMail;
    Validations validations;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) R.layout.activity_compose_sent_mail);
        this.etSubject = (EditText) findViewById(R.id.etSubject);
        this.etMessage = (EditText) findViewById(R.id.etMessage);
        this.tvSendMail = (TextView) findViewById(R.id.tvSendMail);
        this.ivAttachment = (ImageView) findViewById(R.id.ivAttachment);
        this.etTo = (TextView) findViewById(R.id.etTo);
        ArrayList<ToModel.Datum> destIds = (ArrayList) getIntent().getSerializableExtra("destinationIds");
        if (destIds.size() > 0) {
            ArrayList<String> arrayListIds = new ArrayList<>();
            for (int i = 0; i < destIds.size(); i++) {
                arrayListIds.add(destIds.get(i).getPrenom() + " " + destIds.get(i).getNom());
            }
            String join = TextUtils.join(",", arrayListIds);
            this.idNames = join;
            this.etTo.setText(join);
        }
        this.tvSendMail.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                ComposeSentMail.this.postMail();
            }
        });
        this.ivAttachment.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                ComposeSentMail.this.getLocation();
            }
        });
    }

    /* access modifiers changed from: private */
    public void getLocation() {
        if (!hasPermissions(this, this.PERMISSIONSLoc)) {
            ActivityCompat.requestPermissions(this, this.PERMISSIONSLoc, this.PERMISSION_Location);
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
        }
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
            RequestBody id = RequestBody.create(MediaType.parse("text/plain"), App.getAppPreference().getString("LoginId"));
            ArrayList<ToModel.Datum> destIds = (ArrayList) getIntent().getSerializableExtra("destinationIds");
            if (destIds.size() > 0) {
                ArrayList<Integer> arrayListIds = new ArrayList<>();
                for (int i = 0; i < destIds.size(); i++) {
                    arrayListIds.add(Integer.valueOf(Integer.parseInt(destIds.get(i).getIdCompte())));
                }
                this.idss = TextUtils.join(",", arrayListIds);
            }
            ((Apis) new Retrofit.Builder().baseUrl("http://devworktools.fr/contenu/conseiller/").addConverterFactory(GsonConverterFactory.create()).client(new OkHttpClient.Builder().addInterceptor(provideHttpLoggingInterceptor()).readTimeout(120, TimeUnit.SECONDS).writeTimeout(120, TimeUnit.SECONDS).build()).build().create(Apis.class)).postMail(id, RequestBody.create(MediaType.parse("text/plain"), String.valueOf(this.idss)), RequestBody.create(MediaType.parse("text/plain"), this.etSubject.getText().toString().trim()), RequestBody.create(MediaType.parse("text/plain"), this.etMessage.getText().toString().trim()), ImagePic).enqueue(new Callback<ComposeMailModel>() {
                public void onResponse(Call<ComposeMailModel> call, Response<ComposeMailModel> response) {
                    if (!response.isSuccessful()) {
                        ComposeSentMail.this.dismissLoadingDialog();
                        Toast.makeText(ComposeSentMail.this, "Data Not Found", 0).show();
                    } else if (response.body() != null && response.body().getStatus().intValue() == 200) {
                        ComposeSentMail.this.dismissLoadingDialog();
                        ComposeSentMail.this.finish();
                    }
                }

                public void onFailure(Call<ComposeMailModel> call, Throwable t) {
                    ComposeSentMail.this.dismissLoadingDialog();
                    Toast.makeText(ComposeSentMail.this, t.getLocalizedMessage(), 1).show();
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
