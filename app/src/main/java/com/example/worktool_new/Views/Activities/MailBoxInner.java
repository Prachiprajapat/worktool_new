package com.example.worktool_new.Views.Activities;

import android.app.Dialog;
import android.app.DownloadManager;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.worktool_new.R;
import com.example.worktool_new.Retrofit.Apis;
import com.example.worktool_new.Util.SharedPreference.App;
import com.example.worktool_new.Util.SharedPreference.AppConstants;
import com.example.worktool_new.Views.Fragments.Mail;
import com.squareup.picasso.Picasso;

import java.io.File;

import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MailBoxInner extends AppCompatActivity {
    CircleImageView inboxPersonImage;
    ImageView ivForward;
    ImageView ivMailAttachment;
    TextView tvMailDate;
    TextView tvMailEmail;
    ImageView ivMailDelete;
    TextView tvMailName;
    TextView tvMailSubject;
    TextView tvMailTimme;
    TextView tvInboxMessage,dialogText;
    String split = null;
    String from;
//checkbox multiselection in rec view
//pdf upload
//import cv

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) R.layout.activity_mail_box_inner);
        this.ivForward = (ImageView) findViewById(R.id.ivForward);
        this.ivMailDelete = (ImageView) findViewById(R.id.mailDelete);
        this.inboxPersonImage = (CircleImageView) findViewById(R.id.inboxPersonImage);
        this.ivMailAttachment = (ImageView) findViewById(R.id.ivMailAttachment);
        this.tvMailName = (TextView) findViewById(R.id.tvMailName);
        this.tvMailDate = (TextView) findViewById(R.id.tvMailDate);
        this.tvMailTimme = (TextView) findViewById(R.id.tvMailTimme);
        this.tvMailEmail = (TextView) findViewById(R.id.tvMailEmail);
        this.tvMailSubject = (TextView) findViewById(R.id.tvMailSubject);
        this.tvInboxMessage = (TextView) findViewById(R.id.tvInboxMessage);
        final String image = getIntent().getStringExtra("image");
        from = getIntent().getExtras().getString("from");
        split = image.substring(1, image.length() - 1);
        if (from.equals("inboxAdapter")) {
            Log.i("split", "" + split);
            Glide.with(this).load(AppConstants.IMAGEURL + split)
                    .placeholder(R.drawable.profileplaceholder).into(ivMailAttachment);
            Glide.with(this).load(getIntent().getStringExtra("recieverimage"))
                    .placeholder(R.drawable.profileplaceholder).into(inboxPersonImage);
        } else if (from.equals("sentMailAdapter")) {
            Log.i("split", "" + split);
            Glide.with(this).load(AppConstants.IMAGEURL + split)
                    .placeholder(R.drawable.profileplaceholder).into(ivMailAttachment);
            Glide.with(this).load(getIntent().getStringExtra("recieverimage"))
                    .placeholder(R.drawable.profileplaceholder).into(inboxPersonImage);
        }
        String name = getIntent().getStringExtra("name");
        final String subject = getIntent().getStringExtra("subject");
        String date = getIntent().getStringExtra("date");
        String time = getIntent().getStringExtra("time");
        final String message = getIntent().getStringExtra("message");
        this.tvMailName.setText(name);
        this.tvMailDate.setText(date);
        this.tvMailTimme.setText(time);
        this.tvMailSubject.setText(subject);
        this.tvInboxMessage.setText(message);
        final int messageId = Integer.parseInt(getIntent().getStringExtra("mailid"));
        final int id = Integer.parseInt(App.getAppPreference().getString("LoginId"));
        ivForward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MailBoxInner.this, ForwardMailActivity.class);
                intent.putExtra("message", message);
                intent.putExtra("subject", subject);
                if (!image.equals(""))
                    intent.putExtra("image", split);
                startActivity(intent);

            }
        });
        ivMailDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog2 = new Dialog(MailBoxInner.this);
                dialog2.requestWindowFeature(1);
                dialog2.setContentView(R.layout.mail_delete_dalog);
                dialog2.setCancelable(false);
                dialog2.show();
                ((Button) dialog2.findViewById(R.id.btnCommentDialog)).setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        dialog2.dismiss();
                    }
                });
                ((TextView) dialog2.findViewById(R.id.tvCommentDeleteYes)).setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://devworktools.fr/contenu/conseiller/").addConverterFactory(GsonConverterFactory.create()).build();
                        ((Apis) retrofit.create(Apis.class)).deleteMessage(messageId, id).enqueue(new Callback() {
                            public void onResponse(Call call, Response response) {
                                if (response.isSuccessful()) {
                                    //startActivity(new Intent(MailBoxInner.this, Mail.class));
                                    finish();
                                    dialog2.dismiss();
                                    Toast.makeText(MailBoxInner.this, "Successfully Deleted!", Toast.LENGTH_SHORT).show();
                                }
                            }

                            public void onFailure(Call call, Throwable t) {
                                dialog2.dismiss();
                            }
                        });
                    }
                });
                ((TextView) dialog2.findViewById(R.id.tvCommentDeleteNo)).setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        dialog2.dismiss();
                    }
                });
            }
        });

        ivMailAttachment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                downloadAttachment(split);
            }
        });
    }

    private void downloadAttachment(final String file) {
        String DownloadUrl = AppConstants.IMAGEURL + file;
        String fileName = DownloadUrl.substring(DownloadUrl.lastIndexOf('/') + 1);
        String filename = fileName;
        String downloadUrlOfImage = AppConstants.IMAGEURL + file;
        File direct =
                new File(Environment
                        .getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)
                        .getAbsolutePath() + "/" + "gautam" + "/");

        if (!direct.exists()) {
            direct.mkdir();
            Log.i("", "dir created for first time");
        }

        DownloadManager dm = (DownloadManager) this.getSystemService(Context.DOWNLOAD_SERVICE);
        Uri downloadUri = Uri.parse(downloadUrlOfImage);
        DownloadManager.Request request = new DownloadManager.Request(downloadUri);
        request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI | DownloadManager.Request.NETWORK_MOBILE)
                .setAllowedOverRoaming(false)
                .setTitle(filename)
                .setMimeType("image/jpeg")
                .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
                .setDestinationInExternalPublicDir(Environment.DIRECTORY_PICTURES,
                        File.separator + "gautam" + File.separator + filename);

        dm.enqueue(request);
        Intent browserIntent = new Intent("android.intent.action.VIEW", Uri.parse(AppConstants.IMAGEURL + file));
        startActivity(browserIntent);

    }
}