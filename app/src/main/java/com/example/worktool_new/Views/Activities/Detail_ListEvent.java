package com.example.worktool_new.Views.Activities;

import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.worktool_new.Models.ListEventsModel;
import com.example.worktool_new.Models.MyEventModel;
import com.example.worktool_new.R;
import com.example.worktool_new.Util.SharedPreference.AppConstants;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class Detail_ListEvent extends AppCompatActivity {
    private TextView date, author, type, summary, description, title,download;
    private ImageView eventImage;
    private int position;
    private ArrayList<ListEventsModel.Datum> eventlist1 = new ArrayList<>();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.event_details);

        date = findViewById(R.id.detailDate);
        author = findViewById(R.id.detailAuthor);
        type = findViewById(R.id.detailType);
        summary = findViewById(R.id.detailSummary);
        description = findViewById(R.id.detailDescription);
        title = findViewById(R.id.detailTitle);
        eventImage = findViewById(R.id.detailImageName);
        download = findViewById(R.id.detailAttachment);

        checkIntent();
    }
    public void eventDetailBackButton(View view){
        this.finish();
    }

    private void checkIntent() {
        Intent intent2 = getIntent();
        String getInt = intent2.getStringExtra("activity");
        if (getInt.equals("detail")) {
            this.position = intent2.getIntExtra("position", 0);
            this.eventlist1 = (ArrayList) intent2.getSerializableExtra("eventlist");
            Gson gson = new Gson();
            PrintStream printStream = System.out;
            //printStream.println("eventlits  " + gson.toJson((Object) this.eventlist));
            this.title.setText(this.eventlist1.get(this.position).getTitre());
            this.type.setText(this.eventlist1.get(this.position).getEventType());
            this.description.setText(this.eventlist1.get(this.position).getDescription());
            this.summary.setText(this.eventlist1.get(this.position).getResume());
            this.download.setText(this.eventlist1.get(this.position).getAttachedfile());
            this.download.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    downloadAttachment(eventlist1.get(position).getAttachedfile());
                }
            });
            Picasso picasso = Picasso.get();
            picasso.load(AppConstants.IMAGEURL + this.eventlist1.get(this.position).getFile()).placeholder((int) R.drawable.profileplaceholder).error((int) R.drawable.profileplaceholder).into(this.eventImage);
            String[] dateTime = this.eventlist1.get(position).getDate().split(" ");
            if (dateTime.length == 2 ) {
                this.date.setText("on " + dateTime[0]);
            }
        }
    }

    private void downloadAttachment(String attachedfile) {
        try {
            URL url = new URL(AppConstants.IMAGEURL + attachedfile);
            HttpURLConnection c = (HttpURLConnection) url.openConnection();
            c.setRequestMethod("GET");
            c.setDoOutput(true);
            c.connect();

            String PATH = Environment.getExternalStorageDirectory() + "/download";
            File file = new File(PATH);
            if (!file.exists())
                file.mkdirs();

            File outputFile = new File(file, attachedfile);
            FileOutputStream fos = new FileOutputStream(outputFile);
            InputStream is = c.getInputStream();
            byte[] buffer = new byte[1024];
            int len1 = 0;
            while ((len1 = is.read(buffer)) != -1)
                fos.write(buffer, 0, len1);

            fos.flush();
            fos.close();
            is.close();
            Log.i("sucess","successful");
        } catch (Exception e){
            Log.i("downloadException", e.getMessage());
        }
    }

    /*private void downloadAttachment(final String file) {
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

    }*/
}