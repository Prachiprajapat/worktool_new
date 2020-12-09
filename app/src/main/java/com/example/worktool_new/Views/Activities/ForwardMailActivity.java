package com.example.worktool_new.Views.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.worktool_new.Adapters.Adapter_ForwardMail;
import com.example.worktool_new.Models.ForwardMailModel;
import com.example.worktool_new.R;
import com.example.worktool_new.Retrofit.Apis;
import com.example.worktool_new.Util.SharedPreference.App;
import com.example.worktool_new.Util.SharedPreference.AppConstants;
import com.example.worktool_new.demo.Item;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ForwardMailActivity extends AppCompatActivity
        implements Adapter_ForwardMail.sendData {

    public ImageView backButton;
    public RecyclerView recyclerList;
    public Adapter_ForwardMail adapter;
    List<String> checkItemList = new ArrayList<>();
    private String message, subject, image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forward_mail);

        backButton = findViewById(R.id.forwardMailBackButton);
        recyclerList = findViewById(R.id.forwardMailRecycleList);
        Button sentMail = findViewById(R.id.sentMailButton);
        message = getIntent().getStringExtra("message");
        subject = getIntent().getStringExtra("subject");
        image = getIntent().getStringExtra("image");
        getData();

        sentMail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i=0; i<checkItemList.size(); i++){
//                    Log.i("mailimg", AppConstants.IMAGEURL + image);
//                    Log.i("mailid", checkItemList.toString());
//                    Log.i("mailmsg", ""+message);
//                    Log.i("mailsub", ""+subject);
                    RequestBody id = RequestBody.create(MediaType.parse("multipart/form-data"),
                            App.getAppPreference().getString("LoginId"));
                    RequestBody reqMessage = RequestBody.create(MediaType.parse("multipart/form-data"),
                            message);
                    RequestBody reqSubject = RequestBody.create(MediaType.parse("multipart/form-data"),
                            subject);
                    RequestBody reqImg = RequestBody.create(MediaType.parse("image/jpeg"),AppConstants.IMAGEURL+image);
                    RequestBody reqDestId = RequestBody.create(MediaType.parse("multipart/form-data"),
                            checkItemList.get(i));
                    File file2 = new File(Uri.parse(image).getPath());
                    MultipartBody.Part reqImage = MultipartBody.Part.createFormData("eventphoto",
                            file2.getName(), RequestBody.create(MediaType.parse("image/jpeg"), file2));
                    Retrofit retrofit = new Retrofit.Builder().baseUrl("http://devworktools.fr/contenu/conseiller/")
                            .addConverterFactory(GsonConverterFactory.create()).build();
                    ((Apis) retrofit.create(Apis.class)).postSentMail(id, reqDestId, reqSubject, reqMessage, reqImg).
                            enqueue(new Callback() {
                        public void onResponse(Call call, Response response) {
                            Toast.makeText(ForwardMailActivity.this, "Successfully", Toast.LENGTH_SHORT).show();
                            Log.i("mail", "Mail Forwarded Successfully!");
                        }

                        public void onFailure(Call call, Throwable t) {
                            Log.i("mailThrowable",""+t);
                        }
                    });
                }
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
        }

    private void getData() {
        ((Apis) new Retrofit.Builder().baseUrl("http://devworktools.fr/contenu/conseiller/").addConverterFactory(GsonConverterFactory.create()).build().create(Apis.class)).
                get_Destinationdata(App.getAppPreference().getString("LoginId")).enqueue(new Callback<ForwardMailModel>() {
            public void onResponse(Call<ForwardMailModel> call, Response<ForwardMailModel> response) {
                if (response.body() != null && response.body().getData().size() > 0) {
                    Log.i("data", response.body().getData().toString());
                    ForwardMailActivity.this.adapter = new Adapter_ForwardMail(ForwardMailActivity.this, response.body().getData(), ForwardMailActivity.this);
                    ForwardMailActivity.this.recyclerList.setLayoutManager(new LinearLayoutManager(ForwardMailActivity.this,1, false));
                    ForwardMailActivity.this.recyclerList.setAdapter(ForwardMailActivity.this.adapter);

//                    Log.i("data", "response aaya hai");
                } else Log.i("data", "null");
            }

            public void onFailure(Call<ForwardMailModel> call, Throwable t) {
                Log.i("throwable", t.getMessage());
            }
        });
    }

    public void senddata(ArrayList<String> id) {
        checkItemList = id;
    }
}