package com.example.worktool_new.Views.Activities;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.example.worktool_new.Models.MyEventModel;
import com.example.worktool_new.Models.PostProfileModel;
import com.example.worktool_new.R;
import com.example.worktool_new.Retrofit.Apis;
import com.example.worktool_new.Util.SharedPreference.App;
import com.example.worktool_new.Util.SharedPreference.AppConstants;
import com.example.worktool_new.Util.Validations;
import com.google.gson.Gson;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.DexterError;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.PermissionRequestErrorListener;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;
import com.squareup.picasso.Picasso;
import com.theartofdev.edmodo.cropper.CropImage;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Add_Event extends AppCompatActivity implements View.OnClickListener {
    private static final int GALLERY = 101;
    private static String IMAGE_DIRECTORY = "/Take next";
    private String BASE64_IMAGE = "";
    private int CAMERA_REQUEST = 422;
    private int MY_CAMERA_PERMISSION_CODE = 421;
    private String[] PERMISSIONSLoc = {"android.permission.READ_EXTERNAL_STORAGE", "android.permission.WRITE_EXTERNAL_STORAGE", "android.permission.CAMERA"};
    private int PERMISSION_Location = 2;
    TextView add_event;
    String attach_file;
    LinearLayout attachfile,imageLayout,pdfLayout,attachFilePdf;
    EditText date;
    EditText description;
    boolean docType = true;
    ArrayList<MyEventModel.Datum> eventlist = new ArrayList<>();
    ImageView eventphoto;
    private String fromCamera = "";
    String id,fileType=null;
    File imageFile;
    String imageUri;
    Intent intent;
    boolean isForProfile = true;
    Boolean isattach = false;
    Boolean ismageEdited = false;
    /* access modifiers changed from: private */
    public Boolean ispermission = false;
    private int mDay;
    private int mHour;
    private int mMinute;
    private int mMonth;
    private int mYear;
    int position;
    private ProgressDialog progress;
    private String selectedFileCertification = "";
    private String selectedfileIdProof = "";
    String selectfile;
    Spinner sp_status;
    String status;
    ArrayList<String> statusList;
    EditText summary;
    EditText time;
    Spinner typeFile;
    EditText title;
    TextView toptexxt;
    TextView tv_attachfile,tv_attachfilepdf;
    EditText type;
    String upload_photo;
    String typeOfFile;
    String intentImage = null;
    String img = null;
    Uri uri;
    RelativeLayout uploadphoto;
    /* access modifiers changed from: private */
    public String userChoosenTask = "";
    Validations validations;
    final int DATE_PICKER_ID = 1111;
    String[] str = {"PDF OR XLSX","IMAGE"};
    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) R.layout.activity_add__event);
        init();
        checkIntent();


    }

    private void checkIntent() {
        Intent intent2 = getIntent();
        this.intent = intent2;
        if (intent2.hasExtra("eventlist")) {
            this.toptexxt.setText("Edit Event");
            this.position = this.intent.getIntExtra("position", 0);
            this.eventlist = (ArrayList) this.intent.getSerializableExtra("eventlist");
            Gson gson = new Gson();
            PrintStream printStream = System.out;
            printStream.println("eventlits  " + gson.toJson((Object) this.eventlist));
            Log.i("idid",id+" "+this.eventlist.get(position).getIdEvent());
            this.title.setText(this.eventlist.get(this.position).getTitre());
            this.type.setText(this.eventlist.get(this.position).getEventType());
            this.description.setText(this.eventlist.get(this.position).getDescription());
            this.summary.setText(this.eventlist.get(this.position).getResume());
            String[] dateTime = this.eventlist.get(position).getDate().split(" ");
            if (dateTime.length == 2 ) {
                this.date.setText(dateTime[0]);
                this.time.setText(dateTime[1]);
            }
            String[] split = this.eventlist.get(this.position).getDate().split(" ");
            Picasso picasso = Picasso.get();
            intentImage = this.eventlist.get(this.position).getIdFichier();
            Log.i("intentFile",""+intentImage);
            picasso.load(AppConstants.IMAGEURL + this.eventlist.get(this.position).getAttachedfile()).placeholder((int) R.drawable.profileplaceholder).error((int) R.drawable.profileplaceholder).into(this.eventphoto);
        }
    }

    private void init() {
        this.toptexxt = (TextView) findViewById(R.id.tv_topevent);
        this.date = (EditText) findViewById(R.id.ed_dateOfEvent);
        this.time = (EditText) findViewById(R.id.ed_timeOfEvent);
        this.title = (EditText) findViewById(R.id.ed_titleofEvent);
        this.type = (EditText) findViewById(R.id.ed_typeOfEvent);
        this.summary = (EditText) findViewById(R.id.ed_summartOfEvent);
        this.typeFile = (Spinner) findViewById(R.id.typeFile);
        this.description = (EditText) findViewById(R.id.ed_descOFEvent);
        TextView textView = (TextView) findViewById(R.id.tv_saveEventData);
        this.add_event = textView;
        textView.setOnClickListener(this);
        this.date.setOnClickListener(this);
        this.time.setOnClickListener(this);
        this.eventphoto = (ImageView) findViewById(R.id.iv_eventphoto);
        this.sp_status = (Spinner) findViewById(R.id.sp_status);
        this.uploadphoto = (RelativeLayout) findViewById(R.id.ll_uploadphoto);
        this.attachfile = (LinearLayout) findViewById(R.id.ll_attachFile);
        this.attachFilePdf = (LinearLayout) findViewById(R.id.ll_attachFilePdf);
        this.imageLayout = (LinearLayout) findViewById(R.id.fileButtonImage);
        this.pdfLayout = (LinearLayout) findViewById(R.id.fileButtonPdf);
        this.tv_attachfile = (TextView) findViewById(R.id.tv_attachfile);
        this.tv_attachfilepdf = (TextView) findViewById(R.id.tv_attachfilePdf);
        this.uploadphoto.setOnClickListener(this);
        this.attachfile.setOnClickListener(this);
        this.attachFilePdf.setOnClickListener(this);
        this.attachfile = (LinearLayout) findViewById(R.id.ll_attachFile);
        this.validations = new Validations();
        ArrayList<String> arrayList = new ArrayList<>();
        this.statusList = arrayList;
        arrayList.add("Public");
        this.statusList.add("Private");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(this, 17367048, this.statusList);
        dataAdapter.setDropDownViewResource(17367049);
        this.sp_status.setAdapter(dataAdapter);
        this.sp_status.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
            }

            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
        ArrayAdapter<String> adp = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,str);
        adp.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        typeFile.setAdapter(adp);
        //typeOfFile = typeFile.getSelectedItem().toString();
        this.typeFile.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                if(typeFile.getSelectedItem().toString().equals("IMAGE")){
                    imageLayout.setVisibility(View.VISIBLE);
                    pdfLayout.setVisibility(View.GONE);
                } else if (typeFile.getSelectedItem().toString().equals("PDF OR XLSX")) {
                    pdfLayout.setVisibility(View.VISIBLE);
                    imageLayout.setVisibility(View.GONE);
                }
            }
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ed_dateOfEvent:
                showDatePicker();
                return;
            case R.id.ed_timeOfEvent:
                showTimePicker();
                return;
            case R.id.ll_attachFile:
                this.isattach = true;
                if (this.ispermission) {
                    CropImage.activity().start(this);
                } else {
                    getLocation();
                }
                return;
            case R.id.ll_uploadphoto:
                getLocation();
                return;
            case R.id.ll_attachFilePdf:
                pdfIntent();
                return;
            case R.id.tv_saveEventData:
                if (!App.getAppPreference().getString("eventscreen").equals("edit")) {
                    uploadEventdata();
                    Log.i("save button clicked","save");
                } else {
                    EditEventdata();
                }
                return;
            default:
                return;
        }
    }

    private void getLocation() {
        if (!hasPermissions(this, this.PERMISSIONSLoc)) {
            ActivityCompat.requestPermissions(this, this.PERMISSIONSLoc, this.PERMISSION_Location);
            return;
        }
        this.ispermission = true;
        CropImage.activity().start(this);
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

    private void uploadEventdata() {
        if (this.validations.validateAddEventdata( this.type.getText().toString().trim(), this.date.getText().toString().trim(), this.time.getText().toString().trim(), this.description.getText().toString().trim(), this.summary.getText().toString().trim())) {
            showLoadingDialog();
            MultipartBody.Part uploadedphoto = null;
            MultipartBody.Part attachedfile = null;
            MultipartBody.Part reqImage = null;
            if (this.attach_file != null) {
//                File file2 = new File(Uri.parse(this.attach_file).getPath());
                // String extension = file2.getName().substring(file2.getName().lastIndexOf("."));
                if (fileType.equals("png") || fileType.equals("jpeg")) {
                    File file2 = new File(Uri.parse(this.attach_file).getPath());
                    attachedfile = MultipartBody.Part.createFormData("fichier", file2.getName(), RequestBody.create(MediaType.parse("image/jpeg"), file2));
                } else if (fileType.equals("pdf")) {
                    File file2 = new File(AppConstants.IMAGEURL + this.attach_file);
                    Log.i("pdffile",""+file2);
                    Log.i("Pdfname",""+file2.getName());
                    Log.i("Pdfpath",""+file2.getPath());
                    attachedfile = MultipartBody.Part.createFormData("fichier", file2.getName(), RequestBody.create(MediaType.parse("application/pdf"), file2.getPath()));
                    Log.i("gautam",attachedfile.toString());
                } else if (fileType.equals("xlsx")) {
                    //attachedfile = MultipartBody.Part.createFormData("fichier", file2.getName(), RequestBody.create(MediaType.parse("application/xlsx"), file2));
                }
            }
            RequestBody id2 = RequestBody.create(MediaType.parse("multipart/form-data"), App.getAppPreference().getString("LoginId"));
            RequestBody reqtitle = RequestBody.create(MediaType.parse("multipart/form-data"), this.title.getText().toString().trim());
            RequestBody reqstatus = RequestBody.create(MediaType.parse("multipart/form-data"), this.sp_status.getSelectedItem().toString());
            RequestBody reqdate = RequestBody.create(MediaType.parse("multipart/form-data"), this.date.getText().toString().trim());
            RequestBody reqtime = RequestBody.create(MediaType.parse("multipart/form-data"), this.time.getText().toString().trim());
            if(this.upload_photo != null){
                File file = new File(Uri.parse(this.upload_photo).getPath());
                Log.i("path",Uri.parse(this.upload_photo).getPath());
                Log.i("path2",file.getName());
                reqImage = MultipartBody.Part.createFormData("eventphoto", file.getName(), RequestBody.create(MediaType.parse("image/jpeg"), file));
            }
            else{
                reqImage = MultipartBody.Part.createFormData("eventphoto","defaultImage",RequestBody.create(MediaType.parse("multipart/form-data"), String.valueOf(R.drawable.profileplaceholder)));
            }
            RequestBody reqdesc = RequestBody.create(MediaType.parse("multipart/form-data"), this.description.getText().toString().trim());
            RequestBody reqsummary = RequestBody.create(MediaType.parse("multipart/form-data"), this.summary.getText().toString().trim());
            RequestBody reqtype = RequestBody.create(MediaType.parse("multipart/form-data"), this.type.getText().toString().trim());
            Retrofit retrofit = new Retrofit.Builder().baseUrl("http://devworktools.fr/contenu/conseiller/").addConverterFactory(GsonConverterFactory.create()).build();
            Retrofit retrofit3 = retrofit;
            Log.i("gautam","hhh");
            Log.i("gaunam", date.getText().toString());
            ((Apis) retrofit.create(Apis.class)).postEvent(id2, reqtitle, reqstatus, reqdate, reqtime, reqtype, reqsummary, reqdesc, attachedfile, reqImage).enqueue(new Callback<PostProfileModel>() {
                public void onResponse(Call<PostProfileModel> call, Response<PostProfileModel> response) {
                    if (!response.isSuccessful() || response.body().getStatuscode().intValue() != 200) {
                        Add_Event.this.dismissLoadingDialog();
                        Toast.makeText(Add_Event.this, response.body().getMessage(), 0).show();
                        Log.i("gautam",response.body().getMessage());
                        Intent intent=new Intent(Add_Event.this,MainActivity.class);
                        intent.putExtra("event","added");
                        startActivity(intent);
                        finish();
                    }
                    Add_Event.this.dismissLoadingDialog();
                    Toast.makeText(Add_Event.this, response.body().getMessage(), 0).show();
                    Intent intent=new Intent(Add_Event.this,MainActivity.class);
                    intent.putExtra("event","added");
                    startActivity(intent);
                    finish();
                }

                public void onFailure(Call<PostProfileModel> call, Throwable t) {
                    Add_Event.this.dismissLoadingDialog();
                    Toast.makeText(Add_Event.this, t.getMessage(), 0).show();
                    Log.i("throwable1",t+"");
                }
            });
            dismissLoadingDialog();
            Add_Event.this.finish();

        }
        dismissLoadingDialog();
        Toast.makeText(this, Validations.error_message, 0).show();
        Log.i("gautam",Validations.error_message);
    }

    private void EditEventdata() {
        Toast.makeText(this, "Edit", 0).show();
        showLoadingDialog();
        MultipartBody.Part uploadedphoto = null;
        RequestBody filesavedid = null;
        if (this.attach_file != null) {
            File file2 = new File(Uri.parse(this.attach_file).getPath());
            uploadedphoto = MultipartBody.Part.createFormData("fichier", file2.getName(), RequestBody.create(MediaType.parse("image/jpeg"), file2));
        }
        else{
            //Log.i("intentImage",img);
            filesavedid = RequestBody.create(MediaType.parse("text/plain"),this.eventlist.get(this.position).getIdFichier());
            Log.i("intentImage2",img);
        }
        RequestBody eventid = RequestBody.create(MediaType.parse("text/plain"), this.eventlist.get(this.position).getIdEvent());
        RequestBody id2 = RequestBody.create(MediaType.parse("text/plain"), App.getAppPreference().getString("LoginId"));
        RequestBody reqtitle = RequestBody.create(MediaType.parse("text/plain"), this.title.getText().toString().trim());
        RequestBody reqstatus = RequestBody.create(MediaType.parse("text/plain"), this.sp_status.getSelectedItem().toString());
        MediaType parse = MediaType.parse("text/plain");
        RequestBody reqdate = RequestBody.create(parse, this.date.getText().toString().trim() + " " + this.time.getText().toString().trim());
        //RequestBody reqtime = RequestBody.create(MediaType.parse("text/plain"), "");
        RequestBody reqdesc = RequestBody.create(MediaType.parse("text/plain"), this.description.getText().toString().trim());
        RequestBody reqsummary = RequestBody.create(MediaType.parse("text/plain"), this.summary.getText().toString().trim());
        RequestBody reqtype = RequestBody.create(MediaType.parse("text/plain"), this.type.getText().toString().trim());
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://devworktools.fr/contenu/conseiller/").addConverterFactory(GsonConverterFactory.create()).build();
        Retrofit retrofit3 = retrofit;
        ((Apis) retrofit.create(Apis.class)).EditEvent(eventid, id2, reqstatus, reqdate, reqsummary, reqtitle, reqtype, filesavedid, reqdesc, uploadedphoto).enqueue(new Callback<ResponseBody>() {
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    Add_Event.this.dismissLoadingDialog();
                    Toast.makeText(Add_Event.this, "sucesss", 0).show();
                    Intent intent=new Intent(Add_Event.this,MainActivity.class);
                    intent.putExtra("event","added");
                    startActivity(intent);
                    finish();
                }
                Add_Event.this.dismissLoadingDialog();
                //Toast.makeText(Add_Event.this, "failed", 0).show();
                Intent intent=new Intent(Add_Event.this,MainActivity.class);
                intent.putExtra("event","added");
                startActivity(intent);
                finish();
            }

            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Add_Event.this.dismissLoadingDialog();
                Toast.makeText(Add_Event.this, t.getMessage(), 0).show();
                Log.i("throw",t.getMessage());
            }
        });
        dismissLoadingDialog();
        Add_Event.this.finish();
    }

    private void EditEventWithoutImage() {
        Toast.makeText(this, " Without Edit", 0).show();
        showLoadingDialog();
        RequestBody eventid = RequestBody.create(MediaType.parse("multipart/form-data"), this.eventlist.get(this.position).getIdEvent());
        RequestBody id2 = RequestBody.create(MediaType.parse("multipart/form-data"), App.getAppPreference().getString("LoginId"));
        RequestBody reqtitle = RequestBody.create(MediaType.parse("multipart/form-data"), this.title.getText().toString().trim());
        RequestBody reqstatus = RequestBody.create(MediaType.parse("multipart/form-data"), this.sp_status.getSelectedItem().toString());
        MediaType parse = MediaType.parse("multipart/form-data");
        RequestBody reqdate = RequestBody.create(parse, this.date.getText().toString().trim() + " " + this.time.getText().toString().trim());
        RequestBody reqtime = RequestBody.create(MediaType.parse("multipart/form-data"), "");
        RequestBody reqdesc = RequestBody.create(MediaType.parse("multipart/form-data"), this.description.getText().toString().trim());
        RequestBody reqsummary = RequestBody.create(MediaType.parse("multipart/form-data"), this.summary.getText().toString().trim());
        RequestBody reqtype = RequestBody.create(MediaType.parse("multipart/form-data"), this.type.getText().toString().trim());
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://devworktools.fr/contenu/conseiller/").addConverterFactory(GsonConverterFactory.create()).build();
        Retrofit retrofit3 = retrofit;
        ((Apis) retrofit.create(Apis.class)).EditEventWithoutImage(eventid, id2, reqstatus, reqdate, reqsummary, reqtitle, reqtype, reqtime, reqdesc).enqueue(new Callback<ResponseBody>() {
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    Add_Event.this.dismissLoadingDialog();
                    Toast.makeText(Add_Event.this, "sucesss", 0).show();
                }
                Add_Event.this.dismissLoadingDialog();
                Toast.makeText(Add_Event.this, "failed", 0).show();
                Intent intent=new Intent(Add_Event.this,MainActivity.class);
                intent.putExtra("event","added");
                startActivity(intent);
                finish();

            }

            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Add_Event.this.dismissLoadingDialog();
                Toast.makeText(Add_Event.this, t.getMessage(), 0).show();
            }
        });
        dismissLoadingDialog();
        Add_Event.this.finish();
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

    private void requestStoragePermission() {
        Dexter.withActivity(this).withPermissions("android.permission.READ_EXTERNAL_STORAGE", "android.permission.WRITE_EXTERNAL_STORAGE", "android.permission.CAMERA").withListener(new MultiplePermissionsListener() {
            public void onPermissionsChecked(MultiplePermissionsReport report) {
                if (report.areAllPermissionsGranted()) {
                    Add_Event.this.showPictureDialog();
                    Boolean unused = Add_Event.this.ispermission = true;
                }
                if (report.isAnyPermissionPermanentlyDenied()) {
                    Add_Event.this.showSettingsDialog();
                }
            }

            public void onPermissionRationaleShouldBeShown(List<PermissionRequest> list, PermissionToken token) {
                token.continuePermissionRequest();
            }
        }).withErrorListener(new PermissionRequestErrorListener() {
            public void onError(DexterError error) {
                Toast.makeText(Add_Event.this, "Error occurred! ", 0).show();
            }
        }).onSameThread().check();
    }

    public void showPictureDialog() {
        final CharSequence[] items = {"Take Photo", "Choose image from Library",
                "Choose pdf from Library", "Cancel"};
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Add Photo!");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int item) {
                if (items[item].equals("Take Photo")) {
                    String unused = Add_Event.this.userChoosenTask = "Take Photo";
                    Add_Event.this.cameraIntent();
                } else if (items[item].equals("Choose image from Library")) {
                    String unused2 = Add_Event.this.userChoosenTask = "Choose image from Library";
                    Add_Event.this.galleryIntent();
                }else if (items[item].equals("Choose pdf from Library")) {
                    String unused2 = Add_Event.this.userChoosenTask = "Choose pdf from Library";
                    Add_Event.this.pdfIntent();
                } else if (items[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }

    public void pdfIntent(){
        Intent intent = new Intent();
        intent.setType("application/pdf");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select PDF"), 11);
    }

    public void galleryIntent() {
        Intent intent2 = new Intent();
        intent2.setType("image/*");
        intent2.setAction("android.intent.action.GET_CONTENT");
        startActivityForResult(Intent.createChooser(intent2, "Select File"), 101);
    }

    /* access modifiers changed from: private */
    public void cameraIntent() {
        startActivityForResult(new Intent("android.media.action.IMAGE_CAPTURE"), 1);
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        CropImage.ActivityResult result = CropImage.getActivityResult(data);
        if (resultCode == -1) {
            if (requestCode != 11) {
                String valueOf = String.valueOf(result.getUri());
                this.imageUri = valueOf;
                if (valueOf != null) {
                    if (this.isattach.booleanValue()) {
                        this.tv_attachfile.setText(this.imageUri);
                        fileType = "png";
                        Log.i("datafile",imageUri);
                        this.isattach = false;
                        this.attach_file = this.imageUri;
                    } else {
                        this.eventphoto.setImageURI(Uri.parse(this.imageUri));
                        this.upload_photo = this.imageUri;
                        this.ismageEdited = true;
                    }
                    PrintStream printStream = System.out;
                    printStream.println("dkadskad" + this.imageUri);
                }
            } else if (requestCode == 11){
                this.tv_attachfilepdf.setText(data.getDataString());
                fileType = "pdf";
                Log.i("datafile", data.getData().toString());
                uri = data.getData();
                this.attach_file = data.getData().toString();
            }
        }
    }

    private Uri getImageUri(Context applicationContext, Bitmap photo) {
        photo.compress(Bitmap.CompressFormat.JPEG, 100, new ByteArrayOutputStream());
        return Uri.parse(MediaStore.Images.Media.insertImage(getContentResolver(), photo, "Title", (String) null));
    }

    public String getRealPathFromURI(Uri uri) {
        Cursor cursor = getContentResolver().query(uri, (String[]) null, (String) null, (String[]) null, (String) null);
        cursor.moveToFirst();
        return cursor.getString(cursor.getColumnIndex("_data"));
    }

    /* access modifiers changed from: private */
    public void showSettingsDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Need Permissions");
        builder.setMessage("This app needs permission to use this feature. You can grant them in app settings.");
        builder.setPositiveButton("GOTO SETTINGS", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
                Add_Event.this.openSettings();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.show();
    }

    /* access modifiers changed from: private */
    public void openSettings() {
        Intent intent2 = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS");
        intent2.setData(Uri.fromParts("package", getPackageName(), (String) null));
        startActivityForResult(intent2, 101);
    }

    private String saveImage(Bitmap bitmap) {
        bitmap.compress(Bitmap.CompressFormat.JPEG, 60, new ByteArrayOutputStream());
        File wallpaperDirectory = new File(Environment.getExternalStorageDirectory().toString() + IMAGE_DIRECTORY);
        if (!wallpaperDirectory.exists()) {
            wallpaperDirectory.mkdirs();
        }
        try {
            File file = new File(wallpaperDirectory, Calendar.getInstance().getTimeInMillis() + ".jpg");
            this.imageFile = file;
            file.createNewFile();
            new File(String.valueOf(this.imageFile));
            MediaScannerConnection.scanFile(this, new String[]{this.imageFile.getPath()}, new String[]{"image/jpeg"}, (MediaScannerConnection.OnScanCompletedListener) null);
            return this.imageFile.getAbsolutePath();
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }

    private void showDatePicker() {
        final Calendar c = Calendar.getInstance();
        this.mYear = c.get(Calendar.YEAR);
        this.mMonth = c.get(Calendar.MONTH);
        this.mDay = c.get(Calendar.DAY_OF_MONTH);
        showDialog(DATE_PICKER_ID);
        /*new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                view.setMinDate(0);
                EditText editText = Add_Event.this.date;
                editText.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
            }
        }, this.mYear, this.mMonth, this.mDay).show();*/
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id){
            case DATE_PICKER_ID:
                DatePickerDialog datePickerDialog = new DatePickerDialog(this, datePickerListener, mYear, mMonth, mDay);
                Calendar calendar = Calendar.getInstance();
                calendar.add(Calendar.DATE, 0);
                Date newDate = calendar.getTime();
                datePickerDialog.getDatePicker().setMinDate(newDate.getTime()-(newDate.getTime()%(24*60*60*1000)));
                return datePickerDialog;
        }
        return null;
    }
    private DatePickerDialog.OnDateSetListener datePickerListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            EditText editText = Add_Event.this.date;
            editText.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
        }
    };

    private void showTimePicker() {
        Calendar c = Calendar.getInstance();
        this.mHour = c.get(11);
        this.mMinute = c.get(12);
        new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                EditText editText = Add_Event.this.time;
                editText.setText(hourOfDay + ":" + minute + ":00");
            }
        }, this.mHour, this.mMinute, false).show();
    }

}