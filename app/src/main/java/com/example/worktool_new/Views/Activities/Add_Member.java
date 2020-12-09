package com.example.worktool_new.Views.Activities;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.worktool_new.Models.AddMemberModel;
import com.example.worktool_new.R;
import com.example.worktool_new.Retrofit.Apis;
import com.example.worktool_new.Util.SharedPreference.App;
import com.example.worktool_new.Views.Fragments.Members;

import java.util.ArrayList;
import java.util.Calendar;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Add_Member extends AppCompatActivity {
    Button btnNext;
    Button btnSaveMember;
    ArrayAdapter<String> dataAdapter;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    EditText etDateOfBirth;
    EditText etEmail;
    EditText etFirstName;
    EditText etLastName;
    EditText etMobile;
    EditText etPassword;
    EditText etPhone;
    EditText etTypeMember;
    ImageView ivBack;
    LinearLayout llContactInfo;
    LinearLayout llContactInfoTitle;
    LinearLayout llPersonalInfo;
    LinearLayout llPersonalInfoTitle;
    /* access modifiers changed from: private */
    public int mDay;
    private int mHour;
    private int mMinute;
    /* access modifiers changed from: private */
    public int mMonth;
    /* access modifiers changed from: private */
    public int mYear;
    private ProgressDialog progress;
    Spinner spinnerSurName;
    Spinner spinner_type;
    TextView tvContactInfoTitle;
    TextView tvPersonalInfoTitle;
    TextView tvSave;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) R.layout.activity_add__member);
        this.llContactInfo = (LinearLayout) findViewById(R.id.llContactInfo);
        this.llPersonalInfo = (LinearLayout) findViewById(R.id.llPersonalInfo);
        this.btnNext = (Button) findViewById(R.id.btnNext);
        this.btnSaveMember = (Button) findViewById(R.id.btnSaveMember);
        this.spinner_type = (Spinner) findViewById(R.id.sp_membertype);
        ArrayList<String> membertype = new ArrayList<>();
        membertype.add("Parrain");
        membertype.add("Beneficiaire");
        membertype.add("Professionnel");
        ArrayList<String> membertypeParrain = new ArrayList<>();
        membertypeParrain.add("Parrain");
        membertypeParrain.add("Professionnel");
        if (App.getAppPreference().getString("type").equalsIgnoreCase("referent")) {
            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, 17367048, membertypeParrain);
            this.dataAdapter = arrayAdapter;
            arrayAdapter.setDropDownViewResource(17367049);
            this.spinner_type.setAdapter(this.dataAdapter);
        } else {
            ArrayAdapter<String> arrayAdapter2 = new ArrayAdapter<>(this, 17367048, membertype);
            this.dataAdapter = arrayAdapter2;
            arrayAdapter2.setDropDownViewResource(17367049);
            this.spinner_type.setAdapter(this.dataAdapter);
        }
        this.llPersonalInfoTitle = (LinearLayout) findViewById(R.id.llPersonalInfoTitle);
        this.llContactInfoTitle = (LinearLayout) findViewById(R.id.llContactInfoTitle);
        this.tvPersonalInfoTitle = (TextView) findViewById(R.id.tvPersonalInfoTitle);
        this.tvContactInfoTitle = (TextView) findViewById(R.id.tvContactInfoTitle);
        this.tvSave = (TextView) findViewById(R.id.tvSave);
        this.ivBack = (ImageView) findViewById(R.id.ivBack);
        this.spinnerSurName = (Spinner) findViewById(R.id.spinnerSurName);
        ArrayList<String> civilitylist = new ArrayList<>();
        civilitylist.add("Mr.");
        civilitylist.add("Mrs.");
        civilitylist.add("Miss.");
        ArrayAdapter arrayAdapter3 = new ArrayAdapter(this, 17367048, civilitylist);
        this.dataAdapter.setDropDownViewResource(17367049);
        this.spinnerSurName.setAdapter(arrayAdapter3);
        this.etFirstName = (EditText) findViewById(R.id.etFirstName);
        this.etLastName = (EditText) findViewById(R.id.etLastName);
        this.etDateOfBirth = (EditText) findViewById(R.id.etDateOfBirth);
        this.etPhone = (EditText) findViewById(R.id.etPhone);
        this.etMobile = (EditText) findViewById(R.id.etMobile);
        this.etEmail = (EditText) findViewById(R.id.etEmail);
        this.etPassword = (EditText) findViewById(R.id.etPassword);
        this.etEmail.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
                if (!Add_Member.this.etEmail.getText().toString().trim().matches(Add_Member.this.emailPattern) || s.length() <= 0) {
                    Toast.makeText(Add_Member.this.getApplicationContext(), "Invalid email address", 0).show();
                } else {
                    Toast.makeText(Add_Member.this.getApplicationContext(), "valid email address", 0).show();
                }
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
        });
        this.etDateOfBirth.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Calendar c = Calendar.getInstance();
                int unused = Add_Member.this.mYear = c.get(1);
                int unused2 = Add_Member.this.mMonth = c.get(2);
                int unused3 = Add_Member.this.mDay = c.get(5);
                DatePickerDialog datePickerDialog = new DatePickerDialog(Add_Member.this, new DatePickerDialog.OnDateSetListener() {
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        EditText editText = Add_Member.this.etDateOfBirth;
                        editText.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                    }
                }, Add_Member.this.mYear, Add_Member.this.mMonth, Add_Member.this.mDay);
                datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
                datePickerDialog.show();
            }
        });
        this.llPersonalInfoTitle.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Add_Member.this.llContactInfo.setVisibility(8);
                Add_Member.this.llPersonalInfo.setVisibility(0);
                Add_Member.this.btnNext.setVisibility(0);
                Add_Member.this.btnSaveMember.setVisibility(8);
                Add_Member.this.llPersonalInfoTitle.setBackground(Add_Member.this.getResources().getDrawable(R.drawable.button_style2));
                Add_Member.this.llContactInfoTitle.setBackground(Add_Member.this.getResources().getDrawable(R.drawable.button_style3));
                Add_Member.this.tvPersonalInfoTitle.setTextColor(Add_Member.this.getResources().getColor(R.color.colorWhite));
                Add_Member.this.tvContactInfoTitle.setTextColor(Add_Member.this.getResources().getColor(R.color.textGrey));
            }
        });
        this.llContactInfoTitle.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Add_Member.this.llContactInfo.setVisibility(0);
                Add_Member.this.llPersonalInfo.setVisibility(8);
                Add_Member.this.btnNext.setVisibility(8);
                Add_Member.this.btnSaveMember.setVisibility(0);
                Add_Member.this.llPersonalInfoTitle.setBackground(Add_Member.this.getResources().getDrawable(R.drawable.button_style3));
                Add_Member.this.llContactInfoTitle.setBackground(Add_Member.this.getResources().getDrawable(R.drawable.button_style2));
                Add_Member.this.tvPersonalInfoTitle.setTextColor(Add_Member.this.getResources().getColor(R.color.textGrey));
                Add_Member.this.tvContactInfoTitle.setTextColor(Add_Member.this.getResources().getColor(R.color.colorWhite));
            }
        });
        this.btnNext.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Add_Member.this.llContactInfo.setVisibility(0);
                Add_Member.this.llPersonalInfo.setVisibility(8);
                Add_Member.this.btnNext.setVisibility(8);
                Add_Member.this.btnSaveMember.setVisibility(0);
                Add_Member.this.llPersonalInfoTitle.setBackground(Add_Member.this.getResources().getDrawable(R.drawable.button_style3));
                Add_Member.this.llContactInfoTitle.setBackground(Add_Member.this.getResources().getDrawable(R.drawable.button_style2));
                Add_Member.this.tvPersonalInfoTitle.setTextColor(Add_Member.this.getResources().getColor(R.color.textGrey));
                Add_Member.this.tvContactInfoTitle.setTextColor(Add_Member.this.getResources().getColor(R.color.colorWhite));
            }
        });
        this.btnSaveMember.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String type = String.valueOf(Add_Member.this.spinner_type.getSelectedItem());
                String lastName = Add_Member.this.etLastName.getText().toString().trim();
                String firstName = Add_Member.this.etFirstName.getText().toString().trim();
                String surName = Add_Member.this.spinnerSurName.getSelectedItem().toString().trim();
                String email = Add_Member.this.etEmail.getText().toString().trim();
                String telephone = Add_Member.this.etPhone.getText().toString().trim();
                String mobile = Add_Member.this.etMobile.getText().toString().trim();
                String password = Add_Member.this.etPassword.getText().toString().trim();
                String dob = Add_Member.this.etDateOfBirth.getText().toString().trim();
                int[] ids = {R.id.etLastName, R.id.etFirstName, R.id.etEmail, R.id.etPhone, R.id.etMobile, R.id.etPassword, R.id.etDateOfBirth};
                if (!Add_Member.this.validateEditText(ids)) {
                    int[] iArr = ids;
                    Add_Member.this.addMemberpost(type, lastName, firstName, surName, email, telephone, mobile, password, dob);
                    return;
                }
                Toast.makeText(Add_Member.this, "Please fill all the mandatory fields", 0).show();
            }
        });
        this.tvSave.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String type = String.valueOf(Add_Member.this.spinner_type.getSelectedItem());
                String lastName = Add_Member.this.etLastName.getText().toString().trim();
                String firstName = Add_Member.this.etFirstName.getText().toString().trim();
                String surName = Add_Member.this.spinnerSurName.getSelectedItem().toString().trim();
                String email = Add_Member.this.etEmail.getText().toString().trim();
                String telephone = Add_Member.this.etPhone.getText().toString().trim();
                String mobile = Add_Member.this.etMobile.getText().toString().trim();
                String password = Add_Member.this.etPassword.getText().toString().trim();
                String dob = Add_Member.this.etDateOfBirth.getText().toString().trim();
                int[] ids = {R.id.sp_membertype, R.id.etLastName, R.id.etFirstName, R.id.etEmail, R.id.etPhone, R.id.etMobile, R.id.etPassword, R.id.etDateOfBirth};
                if (!Add_Member.this.validateEditText(ids)) {
                    int[] iArr = ids;
                    Add_Member.this.addMemberpost(type, lastName, firstName, surName, email, telephone, mobile, password, dob);
                    return;
                }
                Toast.makeText(Add_Member.this, "Please fill the mandatory fields", 0).show();
            }
        });
        this.ivBack.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Add_Member.this.onBackPressed();
            }
        });
    }

    public boolean validateEditText(int[] ids) {
        boolean isEmpty = false;
        for (int id : ids) {
            EditText et = (EditText) findViewById(id);
            if (TextUtils.isEmpty(et.getText().toString())) {
                et.setError("Must enter Value");
                isEmpty = true;
            }
        }
        return isEmpty;
    }

    /* access modifiers changed from: private */
    public void addMemberpost(String type, String lastName, String firstName, String surName, String email, String telephone, String mobile, String password, String dob) {
        showLoadingDialog();
        ((Apis) new Retrofit.Builder().baseUrl("http://devworktools.fr/contenu/conseiller/").addConverterFactory(GsonConverterFactory.create()).build().create(Apis.class)).addMember(App.getAppPreference().getString("LoginId"), type, lastName, firstName, surName, email, telephone, mobile, password, dob).enqueue(new Callback<AddMemberModel>() {
            public void onResponse(Call<AddMemberModel> call, Response<AddMemberModel> response) {
                if (!response.isSuccessful()) {
                    Add_Member.this.dismissLoadingDialog();
                    if (response.body() != null) {
                        Toast.makeText(Add_Member.this, response.body().getMessage(), 0).show();

                    }
                } else if ((response.body() == null || !response.body().getMessage().equals("New Record Inserted Successfully")) && response.body().getStatusCode().intValue() != 200) {
                    Add_Member.this.dismissLoadingDialog();
                    Toast.makeText(Add_Member.this, response.body().getMessage(), 0).show();
                } else {
                    Add_Member.this.dismissLoadingDialog();
                    Toast.makeText(Add_Member.this, response.body().getMessage(), 0).show();
                   /* FragmentManager fm = getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fm.beginTransaction();
                    fragmentTransaction.replace(R.id.container,members);
                    fragmentTransaction.commit();*/
                    Intent intent=new Intent(Add_Member.this, MainActivity.class);
                    intent.putExtra("member","added");
                    startActivity(intent);
                    finish();
                }
            }

            public void onFailure(Call<AddMemberModel> call, Throwable t) {
                Add_Member.this.dismissLoadingDialog();
                Toast.makeText(Add_Member.this, t.getLocalizedMessage(), 1).show();
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
