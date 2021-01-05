package com.example.worktool_new.Views.Activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.chinalwb.are.AREditor;
import com.example.worktool_new.Models.DeleteEventModel;
import com.example.worktool_new.R;
import com.example.worktool_new.Retrofit.Apis;
import com.example.worktool_new.Util.SharedPreference.App;
import com.example.worktool_new.Util.Validations;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.theartofdev.edmodo.cropper.CropImage;
import de.hdodenhof.circleimageview.CircleImageView;
import java.io.File;
import java.io.PrintStream;
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

public class WallPost extends AppCompatActivity {
    private static final int FILE_REQUEST_CODE = 101;
    private String[] PERMISSIONSLoc = {"android.permission.READ_EXTERNAL_STORAGE", "android.permission.WRITE_EXTERNAL_STORAGE", "android.permission.CAMERA"};
    private int PERMISSION_Location = 2;
    CircleImageView circleImageView;
    ImageView cross;
    EditText ed_email;
    //EditText ed_messsage;
    EditText ed_title;
    String imageUri;
    private Boolean ispermission = false;
    ImageView iv_eventphoto;
    RelativeLayout ll_uploadphoto;
    private ProgressDialog progress;
    TextView publish;
    String upload_file;
    Validations validations;
    AREditor ed_messsage;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) R.layout.activity_wall_post);
        init();
    }

    private void init() {
        this.publish = (TextView) findViewById(R.id.tv_publish);
        this.validations = new Validations();
        this.ed_title = (EditText) findViewById(R.id.tv_walltitle);
        //this.ed_messsage = (EditText) findViewById(R.id.tv_wallmessga);
        this.ed_email = (EditText) findViewById(R.id.tv_emailwall);
        this.ll_uploadphoto = (RelativeLayout) findViewById(R.id.ll_uploadphoto);
        this.iv_eventphoto = (ImageView) findViewById(R.id.iv_eventphoto);
        ImageView imageView = (ImageView) findViewById(R.id.cross);
        this.cross = imageView;
        ed_messsage = findViewById(R.id.tv_wallmessga);
        ed_messsage.setExpandMode(AREditor.ExpandMode.FULL);
        ed_messsage.setHideToolbar(false);
        ed_messsage.setToolbarAlignment(AREditor.ToolbarAlignment.BOTTOM);


        imageView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                WallPost.this.finish();
            }
        });
        this.ll_uploadphoto.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                WallPost.this.getLocation();
            }
        });
        this.publish.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (App.getAppPreference().getString("postwall").equals("pro")) {
                    WallPost.this.postWallpro();
                } else {
                    WallPost.this.postWall();
                }

            }
        });
        if (App.getAppPreference().getString("postwall").equals("pro")) {
            this.ed_title.setVisibility(8);
            this.ed_email.setVisibility(8);
        }
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
            String valueOf = String.valueOf(result.getUri());
            this.imageUri = valueOf;
            this.iv_eventphoto.setImageURI(Uri.parse(valueOf));
        }
    }

    /* access modifiers changed from: private */
    public void postWall() {
        //if (this.validations.ValidateWallPostData(this.imageUri, this.ed_messsage.getText().toString().trim())) {
        if (this.validations.ValidateWallPostData(this.imageUri, this.ed_messsage.getHtml())) {
            MultipartBody.Part ImagePic = null;
            PrintStream printStream = System.out;
            printStream.println("asKAJ" + this.imageUri);
            if (this.imageUri != null) {
                File file = new File(Uri.parse(this.imageUri).getPath());
                ImagePic = MultipartBody.Part.createFormData("file", file.getName(), RequestBody.create(MediaType.parse("image/jpeg"), file));
            }
            RequestBody id = RequestBody.create(MediaType.parse("text/plain"), App.getAppPreference().getString("LoginId"));
            RequestBody title = RequestBody.create(MediaType.parse("text/plain"), this.ed_title.getText().toString().trim());
            // RequestBody message = RequestBody.create(MediaType.parse("text/plain"), this.ed_messsage.getText().toString().trim());
            RequestBody message = RequestBody.create(MediaType.parse("text/plain"), this.ed_messsage.getHtml().trim());

            Log.e("WORK",""+this.ed_messsage.getHtml().trim());
            RequestBody email = RequestBody.create(MediaType.parse("text/plain"), this.ed_email.getText().toString().trim());
            showLoadingDialog();
            Gson gson = new GsonBuilder()
                    .setLenient()
                    .create();
            ((Apis) new Retrofit.Builder().baseUrl("http://devworktools.fr/contenu/conseiller/").addConverterFactory(GsonConverterFactory.create(gson)).client(new OkHttpClient.Builder().addInterceptor(provideHttpLoggingInterceptor()).readTimeout(120, TimeUnit.SECONDS).writeTimeout(120, TimeUnit.SECONDS).build()).build().create(Apis.class)).postwall(id, message, email,title, ImagePic).enqueue(new Callback<DeleteEventModel>() {
                public void onResponse(Call<DeleteEventModel> call, Response<DeleteEventModel> response) {
                    if (!response.isSuccessful() || response.body().getStatusCode().intValue() != 200) {
                        WallPost.this.dismissLoadingDialog();
                        Toast.makeText(WallPost.this, response.body().getStatusMessage(), 0).show();
                        return;
                    }
                    WallPost.this.dismissLoadingDialog();
                    Toast.makeText(WallPost.this, "Data uploaded", 0).show();
                    WallPost.this.finish();
                }

                public void onFailure(Call<DeleteEventModel> call, Throwable t) {
                    WallPost.this.dismissLoadingDialog();
                    Log.i("wallpostthrowable",""+t);
                    //Toast.makeText(WallPost.this, t.getMessage().toString(), 0).show();
                }
            });
            return;
        }
        dismissLoadingDialog();
        Toast.makeText(this, Validations.error_message, 0).show();
    }

    private static HttpLoggingInterceptor provideHttpLoggingInterceptor() {
        return new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            public void log(String message) {
                Log.d("Injector", message);
            }
        });
    }

    /* access modifiers changed from: private */
    public void postWallpro() {
        //if (this.validations.ValidateWallPostDataPro(this.imageUri, this.ed_messsage.getText().toString().trim())) {
        if (this.validations.ValidateWallPostDataPro(this.imageUri, this.ed_messsage.getHtml().trim())) {
            MultipartBody.Part ImagePic = null;
            if (this.imageUri != null) {
                File file = new File(Uri.parse(this.imageUri).getPath());
                ImagePic = MultipartBody.Part.createFormData("file", file.getName(), RequestBody.create(MediaType.parse("image/jpeg"), file));
            }
            RequestBody id = RequestBody.create(MediaType.parse("text/plain"), App.getAppPreference().getString("LoginId"));
            RequestBody message = RequestBody.create(MediaType.parse("text/plain"), this.ed_messsage.getHtml().trim());
            showLoadingDialog();
            Gson gson = new GsonBuilder()
                    .setLenient()
                    .create();
            ((Apis) new Retrofit.Builder().baseUrl("http://devworktools.fr/contenu/conseiller/").addConverterFactory(GsonConverterFactory.create(gson)).build().create(Apis.class)).postwallpro(id, message, ImagePic).enqueue(new Callback<DeleteEventModel>() {
                public void onResponse(Call<DeleteEventModel> call, Response<DeleteEventModel> response) {
                    if (!response.isSuccessful() || response.body().getStatusCode().intValue() != 200) {
                        WallPost.this.dismissLoadingDialog();
                        Toast.makeText(WallPost.this, response.body().getStatusMessage(), 0).show();
                        return;
                    }
                    WallPost.this.dismissLoadingDialog();
                    Toast.makeText(WallPost.this, "Data uploaded", 0).show();
                    WallPost.this.finish();
                }

                public void onFailure(Call<DeleteEventModel> call, Throwable t) {
                    WallPost.this.dismissLoadingDialog();
                    Toast.makeText(WallPost.this, t.getMessage().toString(), 0).show();
                }
            });
            return;
        }
        dismissLoadingDialog();
        Toast.makeText(this, Validations.error_message, 0).show();
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
