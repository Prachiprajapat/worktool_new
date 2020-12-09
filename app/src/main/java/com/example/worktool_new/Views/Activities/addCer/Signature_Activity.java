package com.example.worktool_new.Views.Activities.addCer;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import com.example.worktool_new.Models.AddCerModel;
import com.example.worktool_new.Models.GetEngagementModel;
import com.example.worktool_new.Models.SignatureUploadModel;
import com.example.worktool_new.R;
import com.example.worktool_new.Retrofit.Apis;
import com.example.worktool_new.Util.AllSharedPref;
import com.example.worktool_new.Util.SharedPreference.App;
import com.example.worktool_new.Util.SharedPreference.AppConstants;
import com.example.worktool_new.Util.Validations;
import com.github.gcacace.signaturepad.views.SignaturePad;
import com.squareup.picasso.Picasso;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
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

public class Signature_Activity extends AppCompatActivity {
    private static String[] PERMISSIONS_STORAGE = {"android.permission.WRITE_EXTERNAL_STORAGE"};
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    ArrayList<AddCerModel.Engagement> arrayList;
    ImageView back;
    Button btnResetBeneficiareSignature;
    Button btnResetConseillerSignature;
    Button btnSaveBeneficiareSignature;
    Button btnSaveConseillerSignature;
    EditText etNumeroDuContrat;
    ImageView ivSignature_pad_benefecire;
    ImageView ivSignature_pad_consellor;
    String memberId;
    private ProgressDialog progress;
    SignaturePad signature_pad_benefecire;
    SignaturePad signature_pad_consellor;
    TextView tv_register;
    File upload_photo;
    Validations validations;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        verifyStoragePermissions(this);
        setContentView((int) R.layout.signature_layout);
        init();
    }

    private void init() {
        this.back = (ImageView) findViewById(R.id.iv_backAddCER);
        this.signature_pad_consellor = (SignaturePad) findViewById(R.id.signature_pad_consellor);
        this.signature_pad_benefecire = (SignaturePad) findViewById(R.id.signature_pad_benefecire);
        this.etNumeroDuContrat = (EditText) findViewById(R.id.etNumeroDuContrat);
        this.btnSaveConseillerSignature = (Button) findViewById(R.id.btnSaveConseillerSignature);
        this.btnResetConseillerSignature = (Button) findViewById(R.id.btnResetConseillerSignature);
        this.btnSaveBeneficiareSignature = (Button) findViewById(R.id.btnSaveBeneficiareSignature);
        this.btnResetBeneficiareSignature = (Button) findViewById(R.id.btnResetBeneficiareSignature);
        this.tv_register = (TextView) findViewById(R.id.tv_register);
        this.ivSignature_pad_consellor = (ImageView) findViewById(R.id.ivSignature_pad_consellor);
        this.ivSignature_pad_benefecire = (ImageView) findViewById(R.id.ivSignature_pad_benefecire);
        this.validations = new Validations();
        this.back.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Signature_Activity.this.finish();
            }
        });
        String etNumeroDuContrats = getIntent().getStringExtra("etNumeroDuContrat");
        String signature_pad_consellors = getIntent().getStringExtra("signature_pad_consellor");
        String signature_pad_benefecires = getIntent().getStringExtra("signature_pad_benefecire");
        this.memberId = getIntent().getStringExtra("memberId");
        if (etNumeroDuContrats != null && !etNumeroDuContrats.isEmpty()) {
            this.etNumeroDuContrat.setText(etNumeroDuContrats);
        }
        if (signature_pad_consellors == null || signature_pad_consellors.isEmpty()) {
            this.ivSignature_pad_consellor.setVisibility(8);
            this.signature_pad_consellor.setVisibility(0);
            this.btnSaveConseillerSignature.setVisibility(0);
            this.btnResetConseillerSignature.setVisibility(0);
        } else {
            this.ivSignature_pad_consellor.setVisibility(0);
            this.signature_pad_consellor.setVisibility(8);
            this.btnSaveConseillerSignature.setVisibility(8);
            this.btnResetConseillerSignature.setVisibility(8);
            Picasso picasso = Picasso.get();
            picasso.load(AppConstants.IMAGEURL + signature_pad_consellors).into(this.ivSignature_pad_consellor);
        }
        if (signature_pad_benefecires == null || signature_pad_benefecires.isEmpty()) {
            this.ivSignature_pad_benefecire.setVisibility(8);
            this.signature_pad_benefecire.setVisibility(0);
            this.btnSaveBeneficiareSignature.setVisibility(0);
            this.btnResetBeneficiareSignature.setVisibility(0);
        } else {
            this.ivSignature_pad_benefecire.setVisibility(0);
            this.signature_pad_benefecire.setVisibility(8);
            this.btnSaveBeneficiareSignature.setVisibility(8);
            this.btnResetBeneficiareSignature.setVisibility(8);
            Picasso picasso2 = Picasso.get();
            picasso2.load(AppConstants.IMAGEURL + signature_pad_benefecires).into(this.ivSignature_pad_benefecire);
        }
        this.signature_pad_consellor.setOnSignedListener(new SignaturePad.OnSignedListener() {
            public void onStartSigning() {
                Toast.makeText(Signature_Activity.this, "Signing Start", 0).show();
            }

            public void onSigned() {
                Signature_Activity.this.btnSaveConseillerSignature.setEnabled(true);
                Signature_Activity.this.btnResetConseillerSignature.setEnabled(true);
            }

            public void onClear() {
                Signature_Activity.this.btnSaveConseillerSignature.setEnabled(false);
                Signature_Activity.this.btnResetConseillerSignature.setEnabled(false);
            }
        });
        this.signature_pad_benefecire.setOnSignedListener(new SignaturePad.OnSignedListener() {
            public void onStartSigning() {
                Toast.makeText(Signature_Activity.this, "Signing Start", 0).show();
            }

            public void onSigned() {
                Signature_Activity.this.btnSaveBeneficiareSignature.setEnabled(true);
                Signature_Activity.this.btnResetBeneficiareSignature.setEnabled(true);
            }

            public void onClear() {
                Signature_Activity.this.btnSaveBeneficiareSignature.setEnabled(false);
                Signature_Activity.this.btnResetBeneficiareSignature.setEnabled(false);
            }
        });
        this.tv_register.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Signature_Activity.this.addCERPost();
            }
        });
        this.btnResetConseillerSignature.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Signature_Activity.this.signature_pad_consellor.clear();
            }
        });
        this.btnResetBeneficiareSignature.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Signature_Activity.this.signature_pad_benefecire.clear();
            }
        });
        this.btnSaveConseillerSignature.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (Signature_Activity.this.addJpgSignatureToGallery(Signature_Activity.this.signature_pad_consellor.getSignatureBitmap())) {
                    Toast.makeText(Signature_Activity.this, "Signature saved into the Gallery", 0).show();
                    Signature_Activity.this.postSignature("conseiller");
                    return;
                }
                Toast.makeText(Signature_Activity.this, "Unable to store the signature", 0).show();
            }
        });
        this.btnSaveBeneficiareSignature.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (Signature_Activity.this.addJpgSignatureToGallery(Signature_Activity.this.signature_pad_benefecire.getSignatureBitmap())) {
                    Toast.makeText(Signature_Activity.this, "Signature saved into the Gallery", 0).show();
                    Signature_Activity.this.postSignature("beneficiere");
                    return;
                }
                Toast.makeText(Signature_Activity.this, "Unable to store the signature", 0).show();
            }
        });
    }

    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == 1) {
            if (grantResults.length <= 0 || grantResults[0] != 0) {
                Toast.makeText(this, "Cannot write images to external storage", 0).show();
            }
        }
    }

    public File getAlbumStorageDir(String albumName) {
        File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), albumName);
        file.mkdirs();
        return file;
    }

    public void saveBitmapToJPG(Bitmap bitmap, File photo) throws IOException {
        Bitmap newBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(newBitmap);
        canvas.drawColor(-1);
        canvas.drawBitmap(bitmap, 0.0f, 0.0f, (Paint) null);
        OutputStream stream = new FileOutputStream(photo);
        newBitmap.compress(Bitmap.CompressFormat.JPEG, 80, stream);
        stream.close();
    }

    public boolean addJpgSignatureToGallery(Bitmap signature) {
        boolean result = false;
        try {
            File photo = new File(getAlbumStorageDir("WorkTool"), String.format("Worktool_%d.jpg", new Object[]{Long.valueOf(System.currentTimeMillis())}));
            saveBitmapToJPG(signature, photo);
            scanMediaFile(photo);
            result = true;
            this.upload_photo = photo;
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return result;
        }
    }

    private void scanMediaFile(File photo) {
        Intent mediaScanIntent = new Intent("android.intent.action.MEDIA_SCANNER_SCAN_FILE");
        mediaScanIntent.setData(Uri.fromFile(photo));
        sendBroadcast(mediaScanIntent);
    }

    public static void verifyStoragePermissions(Activity activity) {
        if (ActivityCompat.checkSelfPermission(activity, "android.permission.WRITE_EXTERNAL_STORAGE") != 0) {
            ActivityCompat.requestPermissions(activity, PERMISSIONS_STORAGE, 1);
        }
    }

    /* access modifiers changed from: private */
    public void postSignature(String type) {
        if (this.validations.validateSignatureData(this.upload_photo.toString())) {
            MultipartBody.Part ImagePic = null;
            PrintStream printStream = System.out;
            printStream.println("asKAJ" + this.upload_photo);
            if (this.upload_photo != null) {
                ImagePic = MultipartBody.Part.createFormData("signature", this.upload_photo.getName(), RequestBody.create(MediaType.parse("image/jpeg"), this.upload_photo));
            }
            RequestBody id = RequestBody.create(MediaType.parse("text/plain"), this.memberId);
            RequestBody mType = RequestBody.create(MediaType.parse("text/plain"), type);
            showLoadingDialog();
            ((Apis) new Retrofit.Builder().baseUrl("http://devworktools.fr/contenu/conseiller/").addConverterFactory(GsonConverterFactory.create()).client(new OkHttpClient.Builder().addInterceptor(provideHttpLoggingInterceptor()).readTimeout(120, TimeUnit.SECONDS).writeTimeout(120, TimeUnit.SECONDS).build()).build().create(Apis.class)).postSignature(id, mType, ImagePic).enqueue(new Callback<SignatureUploadModel>() {
                public void onResponse(Call<SignatureUploadModel> call, Response<SignatureUploadModel> response) {
                    if (!response.isSuccessful() || response.body().getStatus_code().intValue() != 200) {
                        Signature_Activity.this.dismissLoadingDialog();
                        Toast.makeText(Signature_Activity.this, response.body().getMessage(), 0).show();
                        return;
                    }
                    Signature_Activity.this.dismissLoadingDialog();
                    Toast.makeText(Signature_Activity.this, "Signature uploaded successfully", 0).show();
                }

                public void onFailure(Call<SignatureUploadModel> call, Throwable t) {
                    Signature_Activity.this.dismissLoadingDialog();
                    Toast.makeText(Signature_Activity.this, t.getMessage().toString(), 0).show();
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

    public void addCERPost() {
        String etmds = AllSharedPref.restoreString(this, "etmds");
        String etccas = AllSharedPref.restoreString(this, "etccas");
        String etAssociation = AllSharedPref.restoreString(this, "etAssociation");
        String etmotifs = AllSharedPref.restoreString(this, "etmotifs");
        String etSante = AllSharedPref.restoreString(this, "etSante");
        String etMobLite = AllSharedPref.restoreString(this, "etMobLite");
        String etLogement = AllSharedPref.restoreString(this, "etLogement");
        String etEnvironmentSocial = AllSharedPref.restoreString(this, "etEnvironmentSocial");
        String etSituation = AllSharedPref.restoreString(this, "etSituation");
        String etSanteFreins = AllSharedPref.restoreString(this, "etSanteFreins");
        String etMobLiteFreins = AllSharedPref.restoreString(this, "etMobLiteFreins");
        String etLogementFreins = AllSharedPref.restoreString(this, "etLogementFreins");
        String etEnvironmentSocialFreins = AllSharedPref.restoreString(this, "etEnvironmentSocialFreins");
        String etSituationFreins = AllSharedPref.restoreString(this, "etSituationFreins");
        String etSituationActuelle = AllSharedPref.restoreString(this, "etSituationActuelle");
        String etBesoin = AllSharedPref.restoreString(this, "etBesoin");
        String etprochainBeneficiary = AllSharedPref.restoreString(this, "etprochainBeneficiary");
        String etContratValue = AllSharedPref.restoreString(this, "etContratValue");
        String etContratAjournePour = AllSharedPref.restoreString(this, "etContratAjournePour");
        String etPrenocisations = AllSharedPref.restoreString(this, "etPrenocisations");
        String etDureeValidationDuContrat = AllSharedPref.restoreString(this, "etDureeValidationDuContrat");
        AddCerModel addCerModel = new AddCerModel();
        addCerModel.setIdMember(Integer.valueOf(this.memberId));
        addCerModel.setMds(etmds);
        addCerModel.setCcas(etccas);
        addCerModel.setAssociation(etAssociation);
        addCerModel.setMotif(etmotifs);
        addCerModel.setAtout_sante(etSante);
        addCerModel.setAtout_mobilite(etMobLite);
        addCerModel.setAtout_logement(etLogement);
        addCerModel.setAtout_env_social(etEnvironmentSocial);
        addCerModel.setAtout_situation_perso(etSituation);
        addCerModel.setFrein_sante(etSanteFreins);
        addCerModel.setFrein_mobilite(etMobLiteFreins);
        addCerModel.setFrein_logement(etLogementFreins);
        addCerModel.setFrein_env_social(etEnvironmentSocialFreins);
        addCerModel.setFrein_situation_perso(etSituationFreins);
        addCerModel.setSituation_actuel(etSituationActuelle);
        String str = etmds;
        addCerModel.setBesoin(etBesoin);
        addCerModel.setNext_rdv(etprochainBeneficiary);
        addCerModel.setValidite(etContratValue);
        addCerModel.setAjourne(etContratAjournePour);
        addCerModel.setPreconisation(etPrenocisations);
        addCerModel.setDure_validation(etDureeValidationDuContrat);
        String str2 = etccas;
        addCerModel.setContrat(this.etNumeroDuContrat.getText().toString().trim());
        this.arrayList = new ArrayList<>();
        new ArrayList();
        ArrayList<GetEngagementModel.Datum> datumArrayList = App.getAppPreference().getEngagementArrayList();
        String str3 = etAssociation;
        int i = 0;
        while (true) {
            String etmotifs2 = etmotifs;
            if (i < datumArrayList.size()) {
                AddCerModel.Engagement addCerModel1Engagement = new AddCerModel.Engagement();
                addCerModel1Engagement.setEcheance_benef(datumArrayList.get(i).getBenefEch());
                addCerModel1Engagement.setEngagement_benef(datumArrayList.get(i).getBenefEngagement());
                addCerModel1Engagement.setEcheance_ref(datumArrayList.get(i).getRefEch());
                addCerModel1Engagement.setEngagement_ref(datumArrayList.get(i).getRefEngagement());
                this.arrayList.add(addCerModel1Engagement);
                i++;
                etmotifs = etmotifs2;
                etSante = etSante;
            } else {
                addCerModel.setEngdata(this.arrayList);
                String str4 = etMobLite;
                AddCerModel addCerModel2 = addCerModel;
                ((Apis) new Retrofit.Builder().baseUrl("http://devworktools.fr/contenu/conseiller/").addConverterFactory(GsonConverterFactory.create()).client(new OkHttpClient.Builder().addInterceptor(provideHttpLoggingInterceptor()).readTimeout(120, TimeUnit.SECONDS).writeTimeout(120, TimeUnit.SECONDS).build()).build().create(Apis.class)).addCerPost(addCerModel).enqueue(new Callback<AddCerModel>() {
                    public void onResponse(Call<AddCerModel> call, Response<AddCerModel> response) {
                        if (!response.isSuccessful() || response.body() == null) {
                            Signature_Activity.this.dismissLoadingDialog();
                            Toast.makeText(Signature_Activity.this, response.body().getMessage(), 0).show();
                        } else if (response.body().getStatus_code().intValue() == 200) {
                            Signature_Activity.this.dismissLoadingDialog();
                            Toast.makeText(Signature_Activity.this, "Cer data added Successfully", 0).show();
                            Signature_Activity.this.finish();
                        } else {
                            Signature_Activity.this.dismissLoadingDialog();
                            Toast.makeText(Signature_Activity.this, response.body().getMessage(), 0).show();
                        }
                    }

                    public void onFailure(Call<AddCerModel> call, Throwable t) {
                        Signature_Activity.this.dismissLoadingDialog();
                        Toast.makeText(Signature_Activity.this, t.getMessage().toString(), 0).show();
                    }
                });
                return;
            }
        }
    }
}
