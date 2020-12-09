package com.example.worktool_new.Views.Activities;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import com.example.worktool_new.R;
import com.example.worktool_new.Util.SharedPreference.AppConstants;
import com.squareup.picasso.Picasso;
import de.hdodenhof.circleimageview.CircleImageView;

public class MemberProfile extends AppCompatActivity {
    CircleImageView ivMemberProfileImage;
    ImageView ivMemberProfileStatus;
    TextView tvMemberProfileAddress;
    TextView tvMemberProfileDOB;
    TextView tvMemberProfileEmail;
    TextView tvMemberProfileMobileNumber;
    TextView tvMemberProfileName;
    TextView tvMemberProfilePostalCode;
    TextView tvMemberProfileStatus;
    TextView tvMemberProfileTelephoneNumber;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) R.layout.activity_member_profile);
        this.tvMemberProfileEmail = (TextView) findViewById(R.id.tvMemberProfileEmail);
        this.tvMemberProfileDOB = (TextView) findViewById(R.id.tvMemberProfileDOB);
        this.tvMemberProfileTelephoneNumber = (TextView) findViewById(R.id.tvMemberProfileTelephoneNumber);
        this.tvMemberProfileMobileNumber = (TextView) findViewById(R.id.tvMemberProfileMobileNumber);
        this.tvMemberProfilePostalCode = (TextView) findViewById(R.id.tvMemberProfilePostalCode);
        this.tvMemberProfileAddress = (TextView) findViewById(R.id.tvMemberProfileAddress);
        this.tvMemberProfileName = (TextView) findViewById(R.id.tvMemberProfileName);
        this.tvMemberProfileStatus = (TextView) findViewById(R.id.tvMemberProfileStatus);
        this.ivMemberProfileImage = (CircleImageView) findViewById(R.id.ivMemberProfileImage);
        this.ivMemberProfileStatus = (ImageView) findViewById(R.id.ivMemberProfileStatus);
        String memberProfileImage = getIntent().getStringExtra("memberProfileImage").toString().trim();
        String memberName = getIntent().getStringExtra("memberName").toString().trim();
        String status = getIntent().getStringExtra(NotificationCompat.CATEGORY_STATUS).toString().trim();
        String memberEmail = getIntent().getStringExtra("memberEmail").toString().trim();
        String memberDOB = getIntent().getStringExtra("memberDOB").toString().trim();
        String memebrTelephoneNumber = getIntent().getStringExtra("memebrTelephoneNumber").toString().trim();
        String memberMobileNumber = getIntent().getStringExtra("memberMobileNumber").toString().trim();
        String memberPostalcode = getIntent().getStringExtra("memberPostalcode").toString().trim();
        String memeberAddress = getIntent().getStringExtra("memeberAddress").toString().trim();
        this.tvMemberProfileEmail.setText(memberEmail);
        this.tvMemberProfileDOB.setText(memberDOB);
        this.tvMemberProfileTelephoneNumber.setText(memebrTelephoneNumber);
        this.tvMemberProfileMobileNumber.setText(memberMobileNumber);
        this.tvMemberProfilePostalCode.setText(memberPostalcode);
        this.tvMemberProfileAddress.setText(memeberAddress);
        this.tvMemberProfileName.setText(memberName);
        this.tvMemberProfileStatus.setText(status);
        String ProfileImage = AppConstants.IMAGEURL + memberProfileImage;
        if (memberProfileImage != null) {
            Picasso.get().load(ProfileImage).placeholder((int) R.drawable.profileplaceholder).error((int) R.drawable.profileplaceholder).into((ImageView) this.ivMemberProfileImage);
        } else {
            this.ivMemberProfileImage.setImageResource(R.drawable.profileplaceholder);
        }
    }
}
