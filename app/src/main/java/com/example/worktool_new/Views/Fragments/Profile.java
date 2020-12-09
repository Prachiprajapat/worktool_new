package com.example.worktool_new.Views.Fragments;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import com.example.worktool_new.Models.ProfileModel;
import com.example.worktool_new.R;
import com.example.worktool_new.Retrofit.Apis;
import com.example.worktool_new.Util.SharedPreference.App;
import com.example.worktool_new.Util.SharedPreference.AppConstants;
import com.example.worktool_new.Views.Activities.EditProfile;
import com.example.worktool_new.Views.Activities.Login;
import com.squareup.picasso.Picasso;
import de.hdodenhof.circleimageview.CircleImageView;
import java.util.Calendar;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Profile extends Fragment {
    Button btnLogout;
    ImageView iv_edit;
    CircleImageView iv_profile;
    LinearLayout llAboutMe;
    LinearLayout llDescription;
    LinearLayout llPersonalInfo;
    LinearLayout llPersonalInformation;
    private ProgressDialog progress;
    TextView textAboutMe;
    TextView textPersonalIfo;
    TextView tvProfileAddress;
    TextView tvProfileAge;
    TextView tvProfileCity;
    TextView tvProfileDescription;
    TextView tvProfileDob;
    TextView tvProfileEmail;
    TextView tvProfileLandLine;
    TextView tvProfileMobile;
    TextView tvProfileName;
    TextView tvProfilePostal;
    View view;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (this.view == null) {
            View inflate = inflater.inflate(R.layout.fragment_profile, container, false);
            this.view = inflate;
            this.tvProfileName = (TextView) inflate.findViewById(R.id.tvProfileName);
            this.tvProfileAge = (TextView) this.view.findViewById(R.id.tvProfileAge);
            this.tvProfileCity = (TextView) this.view.findViewById(R.id.tvProfileCity);
            this.tvProfileDescription = (TextView) this.view.findViewById(R.id.tvProfileDescription);
            this.tvProfileEmail = (TextView) this.view.findViewById(R.id.tvProfileEmail);
            this.tvProfileDob = (TextView) this.view.findViewById(R.id.tvProfileDob);
            this.tvProfileLandLine = (TextView) this.view.findViewById(R.id.tvProfileLandLine);
            this.tvProfileMobile = (TextView) this.view.findViewById(R.id.tvProfileMobile);
            this.tvProfilePostal = (TextView) this.view.findViewById(R.id.tvProfilePostal);
            this.tvProfileAddress = (TextView) this.view.findViewById(R.id.tvProfileAddress);
            this.iv_profile = (CircleImageView) this.view.findViewById(R.id.iv_profile);
            this.llPersonalInformation = (LinearLayout) this.view.findViewById(R.id.llPersonalInformation);
            this.llAboutMe = (LinearLayout) this.view.findViewById(R.id.llAboutMe);
            this.llPersonalInfo = (LinearLayout) this.view.findViewById(R.id.llPersonalInfo);
            this.llDescription = (LinearLayout) this.view.findViewById(R.id.llDescription);
            this.textAboutMe = (TextView) this.view.findViewById(R.id.textAboutMe);
            this.textPersonalIfo = (TextView) this.view.findViewById(R.id.textPersonalIfo);
            this.iv_edit = (ImageView) this.view.findViewById(R.id.iv_edit);
            this.btnLogout = (Button) this.view.findViewById(R.id.btnLogout);
            getProfileData();
            this.btnLogout.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    App.getAppPreference().saveBoolean(AppConstants.IS_LOGIN, false);
                    Profile.this.startActivity(new Intent(Profile.this.getActivity(), Login.class));
                    Profile.this.getActivity().finish();
                }
            });
            this.llPersonalInformation.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Profile.this.llPersonalInformation.setBackgroundColor(Profile.this.getResources().getColor(R.color.colorPrimary));
                    Profile.this.textPersonalIfo.setTextColor(Profile.this.getResources().getColor(R.color.colorWhite));
                    Profile.this.llAboutMe.setBackgroundColor(Profile.this.getResources().getColor(R.color.colorWhite));
                    Profile.this.textAboutMe.setTextColor(Profile.this.getResources().getColor(R.color.textGrey));
                    Profile.this.llPersonalInfo.setVisibility(0);
                    Profile.this.llDescription.setVisibility(8);
                }
            });
            this.llAboutMe.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Profile.this.llAboutMe.setBackgroundColor(Profile.this.getResources().getColor(R.color.colorPrimary));
                    Profile.this.textAboutMe.setTextColor(Profile.this.getResources().getColor(R.color.colorWhite));
                    Profile.this.llPersonalInformation.setBackgroundColor(Profile.this.getResources().getColor(R.color.colorWhite));
                    Profile.this.textPersonalIfo.setTextColor(Profile.this.getResources().getColor(R.color.textGrey));
                    Profile.this.llPersonalInfo.setVisibility(8);
                    Profile.this.llDescription.setVisibility(0);
                }
            });
            this.iv_edit.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Profile.this.startActivity(new Intent(Profile.this.getActivity(), EditProfile.class));
                }
            });
        }
        return this.view;
    }

    private void getProfileData() {
        if (isAdded()) {
            System.out.println("iddd");
        }
        showLoadingDialog();
        ((Apis) new Retrofit.Builder().baseUrl("http://devworktools.fr/contenu/conseiller/").addConverterFactory(GsonConverterFactory.create()).build().create(Apis.class)).getProfileData(App.getAppPreference().getString("LoginId")).enqueue(new Callback<ProfileModel>() {
            public void onResponse(Call<ProfileModel> call, Response<ProfileModel> response) {
                if (response.isSuccessful()) {
                    Profile.this.dismissLoadingDialog();
                    System.out.println("image" + response.body().getData().getImage());
                    App.getAppPreference().saveString("profileImage", response.body().getData().getImage());
                    String ProfileImage = AppConstants.IMAGEURL + response.body().getData().getImage();
                    if (response.body().getData().getImage() != null) {
                        Picasso.get().load(ProfileImage).placeholder((int) R.drawable.profileplaceholder).error((int) R.drawable.profileplaceholder).into((ImageView) Profile.this.iv_profile);
                    } else {
                        Profile.this.iv_profile.setImageResource(R.drawable.profileplaceholder);
                    }
                    Profile.this.tvProfileName.setText(response.body().getData().getCiv() + " " + response.body().getData().getPrenom() + " " + response.body().getData().getNom());
                    Profile.this.tvProfileCity.setText(response.body().getData().getVille());
                    Profile.this.tvProfileDescription.setText(response.body().getData().getDesc());
                    Profile.this.tvProfileEmail.setText(response.body().getData().getMail());
                    Profile.this.tvProfileDob.setText(response.body().getData().getDate());
                    Profile.this.tvProfileLandLine.setText(response.body().getData().getTelfixe());
                    Profile.this.tvProfileMobile.setText(response.body().getData().getTelport());
                    Profile.this.tvProfilePostal.setText(response.body().getData().getCp());
                    Profile.this.tvProfileAddress.setText(response.body().getData().getAdresse());
                    String DOB = response.body().getData().getDate();
                    if (DOB != null) {
                        String[] words = DOB.split("-");
                        Profile.this.getAge(Integer.parseInt(words[0]), Integer.parseInt(words[1]), Integer.parseInt(words[2]));
                        return;
                    }
                    return;
                }
                Profile.this.dismissLoadingDialog();
                Toast.makeText(Profile.this.getContext(), "Data Not Found", 0).show();
            }

            public void onFailure(Call<ProfileModel> call, Throwable t) {
                Profile.this.dismissLoadingDialog();
                Toast.makeText(Profile.this.getContext(), t.getLocalizedMessage(), 1).show();
            }
        });
    }

    public void showLoadingDialog() {
        if (this.progress == null) {
            ProgressDialog progressDialog = new ProgressDialog(getContext());
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

    public void getAge(int year, int month, int day) {
        Calendar dob = Calendar.getInstance();
        Calendar today = Calendar.getInstance();
        dob.set(year, month - 1, day);
        int age = today.get(1) - dob.get(1);
        if (today.get(6) < dob.get(6)) {
            age--;
        }
        String ageS = Integer.toString(age);
        this.tvProfileAge.setText(ageS + " Years old");
    }

    public void onResume() {
        super.onResume();
        getProfileData();
    }
}
