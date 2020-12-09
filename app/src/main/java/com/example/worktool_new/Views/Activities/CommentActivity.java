package com.example.worktool_new.Views.Activities;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.chinalwb.are.AREditText;
import com.chinalwb.are.AREditor;
import com.example.worktool_new.Adapters.Comment_Adapter;
import com.example.worktool_new.Models.PostWallCommentModel;
import com.example.worktool_new.Models.WallCommentModel;
import com.example.worktool_new.R;
import com.example.worktool_new.Retrofit.Apis;
import com.example.worktool_new.Util.Common;
import com.example.worktool_new.Util.SharedPreference.App;
import com.example.worktool_new.Util.SharedPreference.AppConstants;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import de.hdodenhof.circleimageview.CircleImageView;
import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.TimeUnit;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CommentActivity extends AppCompatActivity {
    private CircleImageView civWallProfile;
    /* access modifiers changed from: private */
    public Comment_Adapter comment_adapter;
    /* access modifiers changed from: private */
    public AREditText edit_comment;
    public AREditor arEditor;
    private ImageView ivBackComment;
    private Button post_button;
    private ProgressDialog progress;
    /* access modifiers changed from: private */
    public ProgressBar progressBar;
    /* access modifiers changed from: private */
    public RecyclerView rvCommentList;
    /* access modifiers changed from: private */
    public TextView tvNoDataFound;
    private TextView tvWallDescription;
    private TextView tvWallEmail;
    private TextView tvWallPostDate;
    private TextView tvWallPostName;
    private TextView tvWallTitle;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) R.layout.activity_comment);
        this.rvCommentList = (RecyclerView) findViewById(R.id.rvCommentList);
        this.edit_comment = (AREditText) findViewById(R.id.edit_comment);
        this.post_button = (Button) findViewById(R.id.post_button);
        this.ivBackComment = (ImageView) findViewById(R.id.ivBackComment);
        this.civWallProfile = (CircleImageView) findViewById(R.id.civWallProfile);
        this.tvWallPostName = (TextView) findViewById(R.id.tvWallPostName);
        this.tvWallPostDate = (TextView) findViewById(R.id.tvWallPostDate);
        this.tvWallTitle = (TextView) findViewById(R.id.tvWallTitle);
        this.tvWallEmail = (TextView) findViewById(R.id.tvWallEmail);
        this.tvWallDescription = (TextView) findViewById(R.id.tvWallDescription);
        this.tvNoDataFound = (TextView) findViewById(R.id.tvNoDataFound);
        this.progressBar = (ProgressBar) findViewById(R.id.progressBar);
        arEditor = (AREditor) findViewById(R.id.areditor);
        arEditor.setExpandMode(AREditor.ExpandMode.FULL);
        arEditor.setHideToolbar(false);
        arEditor.setToolbarAlignment(AREditor.ToolbarAlignment.BOTTOM);

        String wallImage = getIntent().getStringExtra("wallImage");
        String wallName = getIntent().getStringExtra("wallName");
        String wallDateTime = getIntent().getStringExtra("wallDateTime");
        String wallTitle = getIntent().getStringExtra("wallTitle");
        String wallEmail = getIntent().getStringExtra("wallEmail");
        String wallDescription = getIntent().getStringExtra("wallDescription");
        this.progressBar.setVisibility(0);
        String CommentType = getIntent().getStringExtra("commentType");
        this.tvWallPostName.setText(wallName);
        this.tvWallPostDate.setText(wallDateTime);
        this.tvWallTitle.setText(wallTitle);
        this.tvWallEmail.setText(wallEmail);
        this.tvWallDescription.setText(wallDescription);
        if (CommentType != null) {
            if (CommentType.equals("wallpro")) {
                this.tvWallTitle.setVisibility(8);
                fetchWallProCommentList();
            } else {
                this.tvWallTitle.setVisibility(0);
                fetchWallCommentList();
            }
        }
        if (wallImage == null || wallImage.isEmpty()) {
            this.progressBar.setVisibility(8);
        } else {
            Picasso picasso = Picasso.get();
            picasso.load(AppConstants.IMAGEURL + wallImage).error((int) R.drawable.profileplaceholder).placeholder((int) R.drawable.profileplaceholder).into(this.civWallProfile, new Callback() {
                public void onSuccess() {
                    CommentActivity.this.progressBar.setVisibility(8);
                }

                public void onError(Exception e) {
                    CommentActivity.this.progressBar.setVisibility(8);
                }
            });
        }
        this.ivBackComment.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                CommentActivity.this.onBackPressed();
            }
        });
        this.post_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (CommentActivity.this.edit_comment.getText().toString().isEmpty()) {
                    Common.showToast(CommentActivity.this, "Please enter the comment");
                } else {
                    CommentActivity.this.postComment(view);
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public void fetchWallCommentList() {
        showLoadingDialog();
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://devworktools.fr/contenu/conseiller/").addConverterFactory(GsonConverterFactory.create()).build();
        ((Apis) retrofit.create(Apis.class)).getWallComments(Integer.parseInt(getIntent().getStringExtra("wallId"))).enqueue(new retrofit2.Callback<WallCommentModel>() {
            public void onResponse(Call<WallCommentModel> call, Response<WallCommentModel> response) {
                if (!Common.InternetCheck(CommentActivity.this)) {
                    CommentActivity.this.dismissLoadingDialog();
                    CommentActivity.this.rvCommentList.setVisibility(0);
                    CommentActivity.this.tvNoDataFound.setVisibility(8);
                    Common.showToast(CommentActivity.this, "Internet Connection Not Found,Check Your Connection");
                } else if (response.body() != null) {
                    CommentActivity.this.dismissLoadingDialog();
                    ArrayList<WallCommentModel.Datum> list = response.body().getData();
                    if (list != null) {
                        Collections.reverse(list);
                        CommentActivity.this.rvCommentList.setVisibility(0);
                        CommentActivity.this.tvNoDataFound.setVisibility(8);
                        Comment_Adapter unused = CommentActivity.this.comment_adapter = new Comment_Adapter(CommentActivity.this, list);
                        CommentActivity.this.rvCommentList.setAdapter(CommentActivity.this.comment_adapter);
                        CommentActivity.this.comment_adapter.notifyDataSetChanged();
                        CommentActivity.this.rvCommentList.setLayoutManager(new LinearLayoutManager(CommentActivity.this.getApplicationContext(), 1, false));
                        return;
                    }
                    CommentActivity.this.rvCommentList.setVisibility(0);
                    CommentActivity.this.tvNoDataFound.setVisibility(8);
                    Common.showToast(CommentActivity.this, "Comments Data Not Found");
                } else {
                    CommentActivity.this.dismissLoadingDialog();
                    CommentActivity.this.rvCommentList.setVisibility(0);
                    CommentActivity.this.tvNoDataFound.setVisibility(8);
                    Toast.makeText(CommentActivity.this, response.body().getMessage(), 0).show();
                }
            }

            public void onFailure(Call<WallCommentModel> call, Throwable t) {
                CommentActivity.this.dismissLoadingDialog();
                CommentActivity.this.rvCommentList.setVisibility(0);
                CommentActivity.this.tvNoDataFound.setVisibility(8);
                Toast.makeText(CommentActivity.this, t.getLocalizedMessage(), 1).show();
            }
        });
    }

    private void fetchWallProCommentList() {
        showLoadingDialog();
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://devworktools.fr/contenu/conseiller/").addConverterFactory(GsonConverterFactory.create()).build();
        ((Apis) retrofit.create(Apis.class)).getWallProComments(Integer.parseInt(getIntent().getStringExtra("wallId"))).enqueue(new retrofit2.Callback<WallCommentModel>() {
            public void onResponse(Call<WallCommentModel> call, Response<WallCommentModel> response) {
                if (!Common.InternetCheck(CommentActivity.this)) {
                    CommentActivity.this.dismissLoadingDialog();
                    CommentActivity.this.rvCommentList.setVisibility(0);
                    CommentActivity.this.tvNoDataFound.setVisibility(8);
                    Common.showToast(CommentActivity.this, "Internet Connection Not Found,Check Your Connection");
                } else if (response.body() != null) {
                    CommentActivity.this.dismissLoadingDialog();
                    ArrayList<WallCommentModel.Datum> list = response.body().getData();
                    if (list != null) {
                        Collections.reverse(list);
                        CommentActivity.this.rvCommentList.setVisibility(0);
                        CommentActivity.this.tvNoDataFound.setVisibility(8);
                        Comment_Adapter unused = CommentActivity.this.comment_adapter = new Comment_Adapter(CommentActivity.this, list);
                        CommentActivity.this.rvCommentList.setAdapter(CommentActivity.this.comment_adapter);
                        CommentActivity.this.comment_adapter.notifyDataSetChanged();
                        CommentActivity.this.rvCommentList.setLayoutManager(new LinearLayoutManager(CommentActivity.this.getApplicationContext(), 1, false));
                        return;
                    }
                    CommentActivity.this.rvCommentList.setVisibility(0);
                    CommentActivity.this.tvNoDataFound.setVisibility(8);
                    Common.showToast(CommentActivity.this, "Comments Data Not Found");
                } else {
                    CommentActivity.this.dismissLoadingDialog();
                    CommentActivity.this.rvCommentList.setVisibility(0);
                    CommentActivity.this.tvNoDataFound.setVisibility(8);
                    Toast.makeText(CommentActivity.this, response.body().getMessage(), 0).show();
                }
            }

            public void onFailure(Call<WallCommentModel> call, Throwable t) {
                CommentActivity.this.dismissLoadingDialog();
                CommentActivity.this.rvCommentList.setVisibility(0);
                CommentActivity.this.tvNoDataFound.setVisibility(8);
                Toast.makeText(CommentActivity.this, t.getLocalizedMessage(), 1).show();
            }
        });
    }

    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
    private void setHtml() {
        String html = "<p style=\"text-align: center;\"><strong>New Feature in 0.1.2</strong></p>\n" +
                "<p style=\"text-align: center;\">&nbsp;</p>\n" +
                "<p style=\"text-align: left;\"><span style=\"color: #3366ff;\">In this release, you have a new usage with ARE.</span></p>\n" +
                "<p style=\"text-align: left;\">&nbsp;</p>\n" +
                "<p style=\"text-align: left;\"><span style=\"color: #3366ff;\">AREditText + ARE_Toolbar, you are now able to control the position of the input area and where to put the toolbar at and, what ToolItems you'd like to have in the toolbar. </span></p>\n" +
                "<p style=\"text-align: left;\">&nbsp;</p>\n" +
                "<p style=\"text-align: left;\"><span style=\"color: #3366ff;\">You can not only define the Toolbar (and it's style), you can also add your own ARE_ToolItem with your style into ARE.</span></p>\n" +
                "<p style=\"text-align: left;\">&nbsp;</p>\n" +
                "<p style=\"text-align: left;\"><span style=\"color: #ff00ff;\"><em><strong>Why not give it a try now?</strong></em></span></p>";
        edit_comment.fromHtml(html);
    }

    /* access modifiers changed from: private */
    public void postComment(final View view) {
        String comment1 = this.edit_comment.getText().toString();
        ((Apis) new Retrofit.Builder().baseUrl("http://devworktools.fr/contenu/conseiller/").addConverterFactory(GsonConverterFactory.create()).client(new OkHttpClient.Builder().addInterceptor(provideHttpLoggingInterceptor()).readTimeout(120, TimeUnit.SECONDS).writeTimeout(120, TimeUnit.SECONDS).build()).build().create(Apis.class)).postComment(Integer.parseInt(App.getAppPreference().getString("LoginId")), Integer.parseInt(getIntent().getStringExtra("wallId")), comment1).enqueue(new retrofit2.Callback<PostWallCommentModel>() {
            public void onResponse(Call<PostWallCommentModel> call, Response<PostWallCommentModel> response) {
                if (response.isSuccessful()) {
                    CommentActivity.this.edit_comment.setText("");
                    InputMethodManager imm = (InputMethodManager) CommentActivity.this.getSystemService("input_method");
                    if (imm != null) {
                        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                        CommentActivity.this.fetchWallCommentList();
                        return;
                    }
                    return;
                }
                CommentActivity commentActivity = CommentActivity.this;
                Common.showToast(commentActivity, "" + response.body().getStatusMessage());
            }

            public void onFailure(Call<PostWallCommentModel> call, Throwable t) {
                Toast.makeText(CommentActivity.this, t.getLocalizedMessage(), 1).show();
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

    private static HttpLoggingInterceptor provideHttpLoggingInterceptor() {
        return new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            public void log(String message) {
                Log.d("Injector", message);
            }
        });
    }
}
