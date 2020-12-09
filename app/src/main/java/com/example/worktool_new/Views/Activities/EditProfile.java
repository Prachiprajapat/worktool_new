package com.example.worktool_new.Views.Activities;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import com.example.worktool_new.Models.PostProfileModel;
import com.example.worktool_new.Models.ProfileModel;
import com.example.worktool_new.R;
import com.example.worktool_new.Retrofit.Apis;
import com.example.worktool_new.Util.SharedPreference.App;
import com.example.worktool_new.Util.SharedPreference.AppConstants;
import com.example.worktool_new.Util.SharedPreference.AppPreferences;
import com.google.android.material.tabs.TabLayout;
import com.squareup.picasso.Picasso;
import com.theartofdev.edmodo.cropper.CropImage;
import java.io.File;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class EditProfile extends AppCompatActivity {
    private static final int GALLERY = 101;
    private static String IMAGE_DIRECTORY = "/Take next";
    EditText address;
    Button bt_savedata;
    Button btn_nextdata;
    EditText city;
    EditText civility;
    ArrayList<String> civilitylist;
    LinearLayout contact_card;
    LinearLayout contact_info;
    DatePickerDialog.OnDateSetListener date;
    EditText date_of_birth;
    EditText desc;
    boolean docType = true;
    EditText email;
    File finalpath;
    EditText firstname;
    private String fromCamera = "";
    File imageFile;
    String imageUri;
    boolean isForProfile = true;
    ImageView ivBackEditProfile;
    EditText lastname;
    LinearLayout ll_contact_card;
    LinearLayout ll_cotact_info;
    LinearLayout ll_profile_info;
    final Calendar myCalendar = Calendar.getInstance();
    LinearLayout personal_info;
    EditText phone;
    EditText phoneport;
    EditText postal_code;
    private ProgressDialog progress;
    private String selectedFileCertification = "";
    private String selectedfileIdProof = "";
    String selectedtab = "personalInfo";
    String selectfile;
    Spinner spinner_civility;
    int spinnerposition;
    TabLayout tabLayout;
    ImageView top_image;
    EditText tv_confirm_password;
    EditText tv_new_password;
    private String userChoosenTask = "";
    ViewPager viewPager;
    View vw_contact_card;
    View vw_contact_info;
    View vw_profile_info;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) R.layout.activity_edit_profile);
        init();
        ImageView imageView = (ImageView) findViewById(R.id.ivBackEditProfile);
        this.ivBackEditProfile = imageView;
        imageView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                EditProfile.this.onBackPressed();
            }
        });
        this.vw_profile_info.setVisibility(4);
        this.vw_contact_info.setVisibility(4);
        this.vw_contact_card.setVisibility(4);
        this.contact_info.setVisibility(4);
        this.personal_info.setVisibility(0);
        this.contact_card.setVisibility(4);
        onclicks();
        getProfileData();
        setCivilityspinner();
    }

    public void init() {
        this.ll_profile_info = (LinearLayout) findViewById(R.id.ll_profile_info);
        this.ll_cotact_info = (LinearLayout) findViewById(R.id.ll_cotact_info);
        this.ll_contact_card = (LinearLayout) findViewById(R.id.ll_contact_card);
        this.personal_info = (LinearLayout) findViewById(R.id.personal_info);
        this.contact_info = (LinearLayout) findViewById(R.id.contact_info);
        this.contact_card = (LinearLayout) findViewById(R.id.contact_card);
        this.vw_profile_info = findViewById(R.id.vw_profile_info);
        this.vw_contact_info = findViewById(R.id.vw_contact_info);
        this.vw_contact_card = findViewById(R.id.vw_contact_card);
        this.top_image = (ImageView) findViewById(R.id.iv_avatar);
        this.spinner_civility = (Spinner) findViewById(R.id.spinner_civility);
        this.firstname = (EditText) findViewById(R.id.tv_usernmae);
        this.lastname = (EditText) findViewById(R.id.tv_lastname);
        this.date_of_birth = (EditText) findViewById(R.id.tv_dob);
        this.desc = (EditText) findViewById(R.id.tv_desc);
        this.address = (EditText) findViewById(R.id.ed_adresss);
        this.postal_code = (EditText) findViewById(R.id.tv_postalcode);
        this.city = (EditText) findViewById(R.id.tv_city);
        this.phone = (EditText) findViewById(R.id.ed_phone);
        this.phoneport = (EditText) findViewById(R.id.ed_phoneport);
        this.email = (EditText) findViewById(R.id.ed_email);
        this.btn_nextdata = (Button) findViewById(R.id.btn_nextdata);
        this.bt_savedata = (Button) findViewById(R.id.bt_savedata);
        this.tv_new_password = (EditText) findViewById(R.id.tv_new_password);
        this.tv_confirm_password = (EditText) findViewById(R.id.tv_confirm_password);
        if (this.selectedtab.equals("personalInfo")) {
            this.bt_savedata.setVisibility(8);
            this.btn_nextdata.setVisibility(0);
        } else if (this.selectedtab.equals("contactInfo")) {
            this.bt_savedata.setVisibility(8);
            this.btn_nextdata.setVisibility(0);
        } else {
            this.bt_savedata.setVisibility(0);
            this.btn_nextdata.setVisibility(8);
        }
    }

    private void setCivilityspinner() {
        ArrayList<String> arrayList = new ArrayList<>();
        this.civilitylist = arrayList;
        arrayList.add("Please select civility");
        this.civilitylist.add("Mr");
        this.civilitylist.add("Mrs");
        this.civilitylist.add("Miss");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(this, 17367048, this.civilitylist);
        dataAdapter.setDropDownViewResource(17367049);
        this.spinner_civility.setAdapter(dataAdapter);
        this.spinner_civility.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                EditProfile.this.spinnerposition = position;
                Log.e("hjsdhf", String.valueOf(position));
            }

            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
    }

    public void onclicks() {
        this.ll_profile_info.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                EditProfile.this.selectedtab = "personalInfo";
                EditProfile.this.vw_profile_info.setVisibility(0);
                EditProfile.this.vw_contact_info.setVisibility(4);
                EditProfile.this.vw_contact_card.setVisibility(4);
                EditProfile.this.personal_info.setVisibility(0);
                EditProfile.this.contact_info.setVisibility(4);
                EditProfile.this.contact_card.setVisibility(4);
                EditProfile.this.bt_savedata.setVisibility(8);
                EditProfile.this.btn_nextdata.setVisibility(0);
            }
        });
        this.ll_cotact_info.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                EditProfile.this.selectedtab = "contactInfo";
                EditProfile.this.vw_profile_info.setVisibility(4);
                EditProfile.this.vw_contact_info.setVisibility(0);
                EditProfile.this.vw_contact_card.setVisibility(4);
                EditProfile.this.contact_info.setVisibility(0);
                EditProfile.this.personal_info.setVisibility(4);
                EditProfile.this.contact_card.setVisibility(4);
                EditProfile.this.bt_savedata.setVisibility(8);
                EditProfile.this.btn_nextdata.setVisibility(0);
            }
        });
        this.ll_contact_card.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                EditProfile.this.selectedtab = "contactCard";
                EditProfile.this.vw_profile_info.setVisibility(4);
                EditProfile.this.vw_contact_info.setVisibility(4);
                EditProfile.this.vw_contact_card.setVisibility(0);
                EditProfile.this.contact_info.setVisibility(4);
                EditProfile.this.personal_info.setVisibility(4);
                EditProfile.this.contact_card.setVisibility(0);
                EditProfile.this.bt_savedata.setVisibility(0);
                EditProfile.this.btn_nextdata.setVisibility(8);
            }
        });
        this.btn_nextdata.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (EditProfile.this.selectedtab.equals("personalInfo")) {
                    EditProfile.this.selectedtab = "contactInfo";
                    EditProfile.this.bt_savedata.setVisibility(8);
                    EditProfile.this.btn_nextdata.setVisibility(0);
                    EditProfile.this.vw_profile_info.setVisibility(4);
                    EditProfile.this.vw_contact_info.setVisibility(0);
                    EditProfile.this.vw_contact_card.setVisibility(4);
                    EditProfile.this.contact_info.setVisibility(0);
                    EditProfile.this.personal_info.setVisibility(4);
                    EditProfile.this.contact_card.setVisibility(4);
                } else if (EditProfile.this.selectedtab.equals("contactInfo")) {
                    EditProfile.this.selectedtab = "contactCard";
                    EditProfile.this.vw_profile_info.setVisibility(4);
                    EditProfile.this.vw_contact_info.setVisibility(4);
                    EditProfile.this.vw_contact_card.setVisibility(0);
                    EditProfile.this.contact_info.setVisibility(4);
                    EditProfile.this.personal_info.setVisibility(4);
                    EditProfile.this.contact_card.setVisibility(0);
                    EditProfile.this.bt_savedata.setVisibility(0);
                    EditProfile.this.btn_nextdata.setVisibility(8);
                } else {
                    EditProfile.this.selectedtab = "persoalInfo";
                    EditProfile.this.bt_savedata.setVisibility(0);
                    EditProfile.this.btn_nextdata.setVisibility(8);
                    EditProfile.this.vw_profile_info.setVisibility(0);
                    EditProfile.this.vw_contact_info.setVisibility(4);
                    EditProfile.this.vw_contact_card.setVisibility(4);
                    EditProfile.this.personal_info.setVisibility(0);
                    EditProfile.this.contact_info.setVisibility(4);
                    EditProfile.this.contact_card.setVisibility(4);
                    EditProfile.this.bt_savedata.setVisibility(8);
                    EditProfile.this.btn_nextdata.setVisibility(0);
                }
            }
        });
        this.bt_savedata.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                EditProfile.this.postProfileData();
            }
        });
        this.top_image.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                CropImage.activity().start(EditProfile.this);
            }
        });
        this.date = new DatePickerDialog.OnDateSetListener() {
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                EditProfile.this.myCalendar.set(1, year);
                EditProfile.this.myCalendar.set(2, monthOfYear);
                EditProfile.this.myCalendar.set(5, dayOfMonth);
                EditProfile.this.updateLabel();
            }
        };
        this.date_of_birth.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                EditProfile editProfile = EditProfile.this;
                new DatePickerDialog(editProfile, editProfile.date, EditProfile.this.myCalendar.get(1), EditProfile.this.myCalendar.get(2), EditProfile.this.myCalendar.get(5)).show();
            }
        });
    }

    /* access modifiers changed from: private */
    public void updateLabel() {
        this.date_of_birth.setText(new SimpleDateFormat("dd/MM/YYYY", Locale.US).format(this.myCalendar.getTime()));
    }

    public static RequestBody toRequestBody(String value) {
        return RequestBody.create(MediaType.parse("text/plain"), value);
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        CropImage.ActivityResult result = CropImage.getActivityResult(data);
        if (resultCode == -1) {
            String valueOf = String.valueOf(result.getUri());
            this.imageUri = valueOf;
            this.top_image.setImageURI(Uri.parse(valueOf));
        }
    }

    public void postProfileData() {
        PrintStream printStream = System.out;
        printStream.println("jlj" + this.finalpath);
        MultipartBody.Part ImagePic = null;
        if (this.imageUri != null) {
            File file = new File(Uri.parse(this.imageUri).getPath());
            ImagePic = MultipartBody.Part.createFormData("image", file.getName(), RequestBody.create(MediaType.parse("image/jpeg"), file));
        }
        RequestBody id = RequestBody.create(MediaType.parse("text/plain"), App.getAppPreference().getString("LoginId"));
        RequestBody create = RequestBody.create(MediaType.parse("text/plain"), this.city.getText().toString());
        RequestBody civ = RequestBody.create(MediaType.parse("text/plain"), this.spinner_civility.getSelectedItem().toString());
        RequestBody requestBody = id;
        RequestBody pass = RequestBody.create(MediaType.parse("text/plain"), this.tv_new_password.getText().toString());
        RequestBody requestBody2 = pass;
        RequestBody requestBody3 = pass;
        RequestBody passconf = RequestBody.create(MediaType.parse("text/plain"), this.tv_new_password.getText().toString());
        RequestBody requestBody4 = passconf;
        RequestBody mail = RequestBody.create(MediaType.parse("text/plain"), this.email.getText().toString());
        RequestBody ville = RequestBody.create(MediaType.parse("text/plain"), this.city.getText().toString());
        showLoadingDialog();
        RequestBody requestBody5 = mail;
        Apis apis = (Apis) new Retrofit.Builder().baseUrl("http://devworktools.fr/contenu/conseiller/").addConverterFactory(GsonConverterFactory.create()).build().create(Apis.class);
        apis.postProfileData(id, RequestBody.create(MediaType.parse("text/plain"), this.desc.getText().toString()), ville, RequestBody.create(MediaType.parse("text/plain"), this.postal_code.getText().toString()), RequestBody.create(MediaType.parse("text/plain"), this.address.getText().toString()), RequestBody.create(MediaType.parse("text/plain"), this.phone.getText().toString()), RequestBody.create(MediaType.parse("text/plain"), this.phoneport.getText().toString()), RequestBody.create(MediaType.parse("text/plain"), this.date_of_birth.getText().toString()), civ, RequestBody.create(MediaType.parse("text/plain"), this.lastname.getText().toString()), RequestBody.create(MediaType.parse("text/plain"), this.firstname.getText().toString()), mail, requestBody2, passconf, RequestBody.create(MediaType.parse("text/plain"), ""), ImagePic).enqueue(new Callback<PostProfileModel>() {
            public void onResponse(Call<PostProfileModel> call, Response<PostProfileModel> response) {
                if (response.isSuccessful()) {
                    EditProfile.this.dismissLoadingDialog();
                    EditProfile.this.bt_savedata.setVisibility(8);
                    EditProfile.this.btn_nextdata.setVisibility(0);
                    AppPreferences appPreference = App.getAppPreference();
                    appPreference.saveString("Name", EditProfile.this.firstname.getText().toString() + " " + EditProfile.this.lastname.getText().toString());
                    EditProfile editProfile = EditProfile.this;
                    Toast.makeText(editProfile, "" + response.body().getMessage(), 0).show();
                    EditProfile.this.finish();
                    return;
                }
                EditProfile.this.dismissLoadingDialog();
                Toast.makeText(EditProfile.this, "Data Not Found", 0).show();
            }

            public void onFailure(Call<PostProfileModel> call, Throwable t) {
                EditProfile.this.dismissLoadingDialog();
                Toast.makeText(EditProfile.this, t.getLocalizedMessage(), 1).show();
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

    private void getProfileData() {
        showLoadingDialog();
        ((Apis) new Retrofit.Builder().baseUrl("http://devworktools.fr/contenu/conseiller/").addConverterFactory(GsonConverterFactory.create()).build().create(Apis.class)).getProfileData(App.getAppPreference().getString("LoginId")).enqueue(new Callback<ProfileModel>() {
            public void onResponse(Call<ProfileModel> call, Response<ProfileModel> response) {
                if (response.isSuccessful()) {
                    EditProfile.this.dismissLoadingDialog();
                    String ProfileImage = AppConstants.IMAGEURL + response.body().getData().getImage();
                    System.out.println("khkjk" + response.body().getData().getImage());
                    if (response.body().getData().getImage() != null) {
                        Picasso.get().load(ProfileImage).placeholder((int) R.drawable.profileplaceholder).error((int) R.drawable.profileplaceholder).into(EditProfile.this.top_image);
                        new File(String.valueOf(Uri.parse(response.body().getData().getImage())));
                        System.out.println("sjfksjfs" + EditProfile.this.finalpath + "    ");
                    } else {
                        EditProfile.this.top_image.setImageResource(R.drawable.profileplaceholder);
                    }
                    System.out.println("dhjwhdw" + response.body().getData().getCiv());
                    if (response.body().getData().getCiv().equals("Mr")) {
                        EditProfile.this.spinner_civility.setSelection(1);
                    } else if (response.body().getData().getCiv().equals("Mrs")) {
                        EditProfile.this.spinner_civility.setSelection(2);
                    } else if (response.body().getData().getCiv().equals("Miss")) {
                        EditProfile.this.spinner_civility.setSelection(3);
                    } else {
                        EditProfile.this.spinner_civility.setSelection(0);
                    }
                    EditProfile.this.firstname.setText(response.body().getData().getPrenom());
                    EditProfile.this.lastname.setText(response.body().getData().getNom());
                    EditProfile.this.desc.setText(response.body().getData().getDesc());
                    EditProfile.this.postal_code.setText(response.body().getData().getCp());
                    EditProfile.this.city.setText(response.body().getData().getVille());
                    EditProfile.this.phone.setText(response.body().getData().getTelport());
                    EditProfile.this.phoneport.setText(response.body().getData().getTelfixe());
                    EditProfile.this.email.setText(response.body().getData().getMail());
                    System.out.println("dnasndna" + response.body().getData().getAdresse());
                    if (response.body().getData().getAdresse() != null) {
                        EditProfile.this.address.setText(response.body().getData().getAdresse());
                    }
                    String DOB = response.body().getData().getDate();
                    if (DOB != null) {
                        String[] words = DOB.split("-");
                        EditProfile.this.getAge(Integer.parseInt(words[0]), Integer.parseInt(words[1]), Integer.parseInt(words[2]));
                        EditProfile.this.date_of_birth.setText(response.body().getData().getDate());
                        return;
                    }
                    return;
                }
                EditProfile.this.dismissLoadingDialog();
                Toast.makeText(EditProfile.this, "Data Not Found", 0).show();
            }

            public void onFailure(Call<ProfileModel> call, Throwable t) {
                EditProfile.this.dismissLoadingDialog();
                Toast.makeText(EditProfile.this, t.getLocalizedMessage(), 1).show();
            }
        });
    }

    public void getAge(int year, int month, int day) {
        Calendar dob = Calendar.getInstance();
        Calendar today = Calendar.getInstance();
        dob.set(year, month - 1, day);
        int age = today.get(1) - dob.get(1);
        if (today.get(6) < dob.get(6)) {
            age--;
        }
        String ageS = Integer.toString(age);
        this.date_of_birth.setText(ageS + " Years old");
    }
}
