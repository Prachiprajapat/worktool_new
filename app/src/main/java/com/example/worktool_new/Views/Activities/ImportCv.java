package com.example.worktool_new.Views.Activities;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.ThumbnailUtils;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.worktool_new.Adapters.AddSkillAdapter;
import com.example.worktool_new.Adapters.ImportCvAdapter;
import com.example.worktool_new.Models.AddSkillModel;
import com.example.worktool_new.Models.getskills.CustomSkillModel;
import com.example.worktool_new.Models.getskills.SkillBodyItem;
import com.example.worktool_new.Models.getskills.SkillModel;
import com.example.worktool_new.R;
import com.example.worktool_new.Retrofit.Apis;
import com.example.worktool_new.Util.ImagePickerUtil;
import com.example.worktool_new.Util.PermissionsUtil;
import com.example.worktool_new.Util.SharedPreference.App;
import com.example.worktool_new.Util.SharedPreference.AppConstants;
import com.google.gson.Gson;
import java.io.File;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ImportCv extends AppCompatActivity implements AddSkillAdapter.sendSkillData {
    private TextView addskill;
    /* access modifiers changed from: private */
    public File attachmentFile = null;
    /* access modifiers changed from: private */
    public String attachment_file_path = "";
    private Button btnUploadFile, saveButton;
    private ArrayList<CustomSkillModel> datamodelArrayList;
    /* access modifiers changed from: private */
    public ImportCvAdapter importCvAdapter;
    private ImageView ivBack;
    private ProgressDialog progress;
    private RatingBar ratingBar;
    private RecyclerView rvImportcv;
    /* access modifiers changed from: private */
    public ArrayList<SkillBodyItem> skillModelArrayList;
    private EditText inputTitle, inputSummary,inputLocation, inputSkill;
    public ArrayList<AddSkillModel> skillList = new ArrayList<>();
    public AddSkillAdapter cvAdapter;
    public ArrayList<AddSkillModel> getSkillList = new ArrayList<>();

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) R.layout.activity_import_cv);
        init();
    }

    private void init() {
        inputTitle = findViewById(R.id.cvTitle);
        inputSummary = findViewById(R.id.cvSummary);
        inputLocation = findViewById(R.id.cvLocation);
        inputSkill = findViewById(R.id.skillEditText);
        ratingBar = findViewById(R.id.ratingBar);
        saveButton = findViewById(R.id.saveCvButton);
        this.addskill = findViewById(R.id.ll_addskill);
        this.btnUploadFile = (Button) findViewById(R.id.btnUploadFile);
        this.ivBack = (ImageView) findViewById(R.id.ivBack);
        this.rvImportcv = (RecyclerView) findViewById(R.id.rvImportcv);
        this.ivBack.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                ImportCv.this.onBackPressed();
            }
        });
        this.btnUploadFile.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                PermissionsUtil.askPermissions(ImportCv.this, PermissionsUtil.CAMERA, PermissionsUtil.STORAGE, new PermissionsUtil.PermissionListener() {
                    public void onPermissionResult(boolean isGranted) {
                        ImportCv.pickFile(ImportCv.this, new ImagePickerUtil.ImagePickerListener() {
                            public void onImagePicked(File imageFile, String tag) {
                                if (imageFile != null) {
                                    try {
                                        File unused = ImportCv.this.attachmentFile = new File(ImportCv.this.attachment_file_path);
                                        if (ImportCv.this.attachmentFile != null && ImportCv.this.attachmentFile.exists()) {
                                            String[] filenameArray = ImportCv.this.attachment_file_path.split("\\.");
                                            String extension = filenameArray[filenameArray.length - 1];
                                            if ((extension != null && extension.equalsIgnoreCase("jpeg")) || extension.equalsIgnoreCase("jpg")) {
                                                return;
                                            }
                                            if (!extension.equalsIgnoreCase("png")) {
                                                if (extension != null && extension.equalsIgnoreCase("mp4")) {
                                                    Bitmap createVideoThumbnail = ThumbnailUtils.createVideoThumbnail(ImportCv.this.attachment_file_path, 2);
                                                }
                                            }
                                        }
                                    } catch (Exception ex) {
                                        ex.getLocalizedMessage();
                                    }
                                }
                            }
                        }, "");
                    }
                });
            }
        });
        this.addskill.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String skill = inputSkill.getText().toString();
                Float rating = ratingBar.getRating();
                addToView(skill, rating);
                inputSkill.setText("");
                ratingBar.setRating(0);
            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveCv();
            }
        });
    }

    private void saveCv() {
        if (inputTitle.getText().toString().equals(""))
            Toast.makeText(this, "Please enter the title", Toast.LENGTH_SHORT).show();
        else if (inputLocation.getText().toString().equals(""))
            Toast.makeText(this, "Please enter the location", Toast.LENGTH_SHORT).show();
        else if (inputSummary.getText().toString().equals(""))
            Toast.makeText(this, "Please enter the summary", Toast.LENGTH_SHORT).show();
        else {
            String title = inputTitle.getText().toString();
            String summary = inputSummary.getText().toString();
            String location = inputLocation.getText().toString();
            for (int i = 1; i < getSkillList.size(); i++){
                String skill = getSkillList.get(i).getSkillName();
                String rating = getSkillList.get(i).getSkillRating().toString();
                //  add to server
            }
        }
    }

    private void addToView(String skill, Float rating) {
        AddSkillModel model = new AddSkillModel(skill, rating);
        skillList.add(model);
        cvAdapter = new AddSkillAdapter(ImportCv.this, skillList, ImportCv.this);
        Log.i("ratibgList", "" + skillList.toString());
        rvImportcv.setLayoutManager(new LinearLayoutManager(ImportCv.this,1, false));
        rvImportcv.setAdapter(cvAdapter);
    }

    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PermissionsUtil.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    public static void pickFile(Activity context, ImagePickerUtil.ImagePickerListener imagePickerListener, String tag) {
        ImagePickerUtil.tag = tag;
        ImagePickerUtil.imagePickerListener = imagePickerListener;
        ImagePickerUtil.context = context;
        Intent intent = new Intent("android.intent.action.GET_CONTENT");
        intent.setType("/");
        context.startActivityForResult(Intent.createChooser(intent, "Select File"), 30000);
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

    public void ontextChanged(int postion, ArrayList<CustomSkillModel> dataModellist, String text) {
        getskilllist(postion, dataModellist, text);
    }

    private void getskilllist(final int pos, final ArrayList<CustomSkillModel> datamodelArraylist, String term) {
        ((Apis) new Retrofit.Builder().baseUrl("http://devworktools.fr/").addConverterFactory(GsonConverterFactory.create()).client(new OkHttpClient.Builder().addInterceptor(provideHttpLoggingInterceptor()).readTimeout(120, TimeUnit.SECONDS).writeTimeout(120, TimeUnit.SECONDS).build()).build().create(Apis.class)).getskills(App.getAppPreference().getString("LoginId"), term).enqueue(new Callback<SkillModel>() {
            public void onResponse(Call<SkillModel> call, Response<SkillModel> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(ImportCv.this, "something went wrong please try again", 0).show();
                } else if (response.body() == null) {
                } else {
                    if (response.body().getStatusCode() == null || response.body().getStatusCode().intValue() != 200) {
                        Toast.makeText(ImportCv.this, "something went wrong please try again", 0).show();
                        return;
                    }
                    ArrayList unused = ImportCv.this.skillModelArrayList = new ArrayList();
                    ArrayList unused2 = ImportCv.this.skillModelArrayList = response.body().getData();
                    Gson gson = new Gson();
                    if (response.body().getData().size() > 0) {
                        SkillBodyItem skillBodyItem = new SkillBodyItem();
                        skillBodyItem.setId("0");
                        skillBodyItem.setLabel("Please select a skill");
                        skillBodyItem.setType("no type");
                        response.body().getData().add(0, skillBodyItem);
                        ArrayList unused3 = ImportCv.this.skillModelArrayList = response.body().getData();
                        datamodelArraylist.set(pos, new CustomSkillModel(((CustomSkillModel) datamodelArraylist.get(pos)).getName(), ImportCv.this.skillModelArrayList, true));
                        PrintStream printStream = System.out;
                        printStream.println("adjlsaka" + gson.toJson((Object) ImportCv.this.skillModelArrayList));
                        App.getAppPreference().saveString("value", "no");
                        ImportCv.this.importCvAdapter.notifyItemChanged(pos);
                        return;
                    }
                    Toast.makeText(ImportCv.this, AppConstants.NO_DATA_FOUND, 0).show();
                }
            }

            public void onFailure(Call<SkillModel> call, Throwable t) {
                Toast.makeText(ImportCv.this, t.getMessage().toString(), 0).show();
            }
        });
    }

    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    public void sendSkill(ArrayList<AddSkillModel> sendSkillList) {
        this.getSkillList = sendSkillList;
    }
}

// we now have all data (148-153 line) and remaining is to upload on server , see line 151