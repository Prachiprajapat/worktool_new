package com.example.worktool_new.Views.Fragments;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import com.example.worktool_new.Adapters.Adapter_Article;
import com.example.worktool_new.Models.ArticlesModel;
import com.example.worktool_new.R;
import com.example.worktool_new.Retrofit.Apis;
import com.example.worktool_new.Util.SharedPreference.App;
import com.example.worktool_new.Views.Activities.AddArticle;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Articles extends Fragment {
    private RelativeLayout llAddArticle;
    /* access modifiers changed from: private */
    public RecyclerView.Adapter mAdapter;
    private ProgressDialog progress;
    /* access modifiers changed from: private */
    public RecyclerView rv_articles;
    /* access modifiers changed from: private */
    public TextView tvNoDataFound;
    private View view;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (this.view == null) {
            View inflate = inflater.inflate(R.layout.fragment_articles, container, false);
            this.view = inflate;
            this.rv_articles = (RecyclerView) inflate.findViewById(R.id.rv_articles);
            this.llAddArticle = (RelativeLayout) this.view.findViewById(R.id.llAddArticle);
            this.tvNoDataFound = (TextView) this.view.findViewById(R.id.tvNoDataFound);
            this.llAddArticle.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Articles.this.startActivity(new Intent(Articles.this.getActivity(), AddArticle.class));
                }
            });
            ArticlePosts();
        }
        return this.view;
    }

    private void ArticlePosts() {
        showLoadingDialog();
        ((Apis) new Retrofit.Builder().baseUrl("http://devworktools.fr/contenu/conseiller/").addConverterFactory(GsonConverterFactory.create()).build().create(Apis.class)).articles(App.getAppPreference().getString("LoginId")).enqueue(new Callback<ArticlesModel>() {
            public void onResponse(Call<ArticlesModel> call, Response<ArticlesModel> response) {
                if (!response.isSuccessful()) {
                    Articles.this.dismissLoadingDialog();
                    Articles.this.rv_articles.setVisibility(8);
                    Articles.this.tvNoDataFound.setVisibility(0);
                    Toast.makeText(Articles.this.getContext(), "Data Not Found", 0).show();
                } else if (response.body() == null || response.body().getStatusCode() == null || response.body().getStatusCode().intValue() != 200) {
                    Articles.this.dismissLoadingDialog();
                    Articles.this.rv_articles.setVisibility(8);
                    Articles.this.tvNoDataFound.setVisibility(0);
                    Toast.makeText(Articles.this.getContext(), "Data Not Found", 0).show();
                } else if (response.body() == null || response.body().getData().size() <= 0) {
                    Articles.this.dismissLoadingDialog();
                    Articles.this.rv_articles.setVisibility(8);
                    Articles.this.tvNoDataFound.setVisibility(0);
                    Toast.makeText(Articles.this.getContext(), "Data Not Found", 0).show();
                } else {
                    Articles.this.dismissLoadingDialog();
                    Articles.this.rv_articles.setVisibility(0);
                    Articles.this.tvNoDataFound.setVisibility(8);
                    RecyclerView.Adapter unused = Articles.this.mAdapter = new Adapter_Article(Articles.this.getActivity(), response.body().getData());
                    Articles.this.rv_articles.setAdapter(Articles.this.mAdapter);
                }
            }

            public void onFailure(Call<ArticlesModel> call, Throwable t) {
                Articles.this.dismissLoadingDialog();
                Articles.this.rv_articles.setVisibility(8);
                Articles.this.tvNoDataFound.setVisibility(0);
                Toast.makeText(Articles.this.getContext(), t.getLocalizedMessage(), 1).show();
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
}
