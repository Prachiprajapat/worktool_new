package com.example.worktool_new.Views.Fragments;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import com.example.worktool_new.Adapters.Adapter_wall;
import com.example.worktool_new.Models.WallModel;
import com.example.worktool_new.Models.WallRemovePostModel;
import com.example.worktool_new.R;
import com.example.worktool_new.Retrofit.Apis;
import com.example.worktool_new.Util.SharedPreference.App;
import com.example.worktool_new.Views.Activities.WallPost;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Wall extends Fragment implements Adapter_wall.deleteApi {
    /* access modifiers changed from: private */
    public ArrayList<WallModel.Datum> WallModelArraylist = new ArrayList<>();
    /* access modifiers changed from: private */
    public ArrayList<WallModel.Datum> WallModelArraylist1 = new ArrayList<>();
    /* access modifiers changed from: private */
    public Dialog dialog;
    private EditText etSearchWall;
    private LinearLayout llWriteOnWall;
    /* access modifiers changed from: private */
    public Adapter_wall mAdapter;
    private ProgressDialog progress;
    /* access modifiers changed from: private */
    public RecyclerView rv_wall;
    /* access modifiers changed from: private */
    public TextView tvNoDataFound;
    private View view;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (this.view == null) {
            View inflate = inflater.inflate(R.layout.fragment_wall, container, false);
            this.view = inflate;
            this.rv_wall = (RecyclerView) inflate.findViewById(R.id.rv_wall);
            this.etSearchWall = (EditText) this.view.findViewById(R.id.etSearchWall);
            this.llWriteOnWall = (LinearLayout) this.view.findViewById(R.id.llWriteOnWall);
            this.tvNoDataFound = (TextView) this.view.findViewById(R.id.tvNoDataFound);
            this.llWriteOnWall.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    App.getAppPreference().saveString("postwall", "nopro");
                    Wall.this.startActivity(new Intent(Wall.this.getActivity(), WallPost.class));
                }
            });
            wallPosts();
            this.etSearchWall.addTextChangedListener(new TextWatcher() {
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                }

                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    if (s.toString().length() > 0) {
                        Wall.this.WallModelArraylist1.clear();
                        for (int i = 0; i < Wall.this.WallModelArraylist.size(); i++) {
                            if ((((WallModel.Datum) Wall.this.WallModelArraylist.get(i)).getPrenom() != null && ((WallModel.Datum) Wall.this.WallModelArraylist.get(i)).getPrenom().startsWith(s.toString())) || ((((WallModel.Datum) Wall.this.WallModelArraylist.get(i)).getCivilite() != null && ((WallModel.Datum) Wall.this.WallModelArraylist.get(i)).getCivilite().startsWith(s.toString())) || ((((WallModel.Datum) Wall.this.WallModelArraylist.get(i)).getTitrePostuler() != null && ((WallModel.Datum) Wall.this.WallModelArraylist.get(i)).getTitrePostuler().startsWith(s.toString())) || ((((WallModel.Datum) Wall.this.WallModelArraylist.get(i)).getTexte() != null && ((WallModel.Datum) Wall.this.WallModelArraylist.get(i)).getTexte().startsWith(s.toString())) || ((((WallModel.Datum) Wall.this.WallModelArraylist.get(i)).getEmail() != null && ((WallModel.Datum) Wall.this.WallModelArraylist.get(i)).getEmail().startsWith(s.toString())) || ((((WallModel.Datum) Wall.this.WallModelArraylist.get(i)).getDateheure() != null && ((WallModel.Datum) Wall.this.WallModelArraylist.get(i)).getDateheure().startsWith(s.toString())) || ((((WallModel.Datum) Wall.this.WallModelArraylist.get(i)).getComment() != null && ((WallModel.Datum) Wall.this.WallModelArraylist.get(i)).getComment().startsWith(s.toString())) || ((((WallModel.Datum) Wall.this.WallModelArraylist.get(i)).getDescription() != null && ((WallModel.Datum) Wall.this.WallModelArraylist.get(i)).getDescription().startsWith(s.toString())) || (((WallModel.Datum) Wall.this.WallModelArraylist.get(i)).getNom() != null && ((WallModel.Datum) Wall.this.WallModelArraylist.get(i)).getNom().startsWith(s.toString())))))))))) {
                                Wall.this.WallModelArraylist1.add(Wall.this.WallModelArraylist.get(i));
                            }
                        }
                        Wall.this.mAdapter.updateList(Wall.this.WallModelArraylist1);
                        return;
                    }
                    if (Wall.this.WallModelArraylist1.size() > 0) {
                        Wall.this.WallModelArraylist1.clear();
                    }
                    Wall.this.mAdapter.updateList(Wall.this.WallModelArraylist);
                }

                public void afterTextChanged(Editable s) {
                }
            });
        }
        return this.view;
    }

    private void wallPosts() {
        if (isAdded()) {
            showLoadingDialog();
        }
        ((Apis) new Retrofit.Builder().baseUrl("http://devworktools.fr/contenu/conseiller/").addConverterFactory(GsonConverterFactory.create()).client(new OkHttpClient.Builder().addInterceptor(provideHttpLoggingInterceptor()).readTimeout(120, TimeUnit.SECONDS).writeTimeout(120, TimeUnit.SECONDS).build()).build().create(Apis.class)).wallPosts(App.getAppPreference().getString("LoginId")).enqueue(new Callback<WallModel>() {
            public void onResponse(Call<WallModel> call, Response<WallModel> response) {
                if (!Wall.this.isAdded()) {
                    return;
                }
                if (!response.isSuccessful()) {
                    Wall.this.dismissLoadingDialog();
                    Wall.this.rv_wall.setVisibility(8);
                    Wall.this.tvNoDataFound.setVisibility(0);
                    Toast.makeText(Wall.this.getContext(), "Data Not Found", 0).show();
                } else if (response.body() == null || response.body().getStatusCode() == null || response.body().getStatusCode().intValue() != 200) {
                    Wall.this.dismissLoadingDialog();
                    Wall.this.rv_wall.setVisibility(8);
                    Wall.this.tvNoDataFound.setVisibility(0);
                    Toast.makeText(Wall.this.getContext(), "Data Not Found", 0).show();
                } else if (response.body().getData().size() > 0) {
                    Wall.this.dismissLoadingDialog();
                    Wall.this.rv_wall.setVisibility(0);
                    Wall.this.tvNoDataFound.setVisibility(8);
                    ArrayList unused = Wall.this.WallModelArraylist = response.body().getData();
                    Adapter_wall unused2 = Wall.this.mAdapter = new Adapter_wall(Wall.this.getActivity(), response.body().getData(), Wall.this);
                    Wall.this.rv_wall.setAdapter(Wall.this.mAdapter);
                } else {
                    Wall.this.dismissLoadingDialog();
                    Wall.this.rv_wall.setVisibility(8);
                    Wall.this.tvNoDataFound.setVisibility(0);
                    Toast.makeText(Wall.this.getContext(), "Data Not Found", 0).show();
                }
            }

            public void onFailure(Call<WallModel> call, Throwable t) {
                if (Wall.this.isAdded()) {
                    Wall.this.dismissLoadingDialog();
                }
                Wall.this.rv_wall.setVisibility(8);
                Wall.this.tvNoDataFound.setVisibility(0);
                Toast.makeText(Wall.this.getContext(), t.getLocalizedMessage(), 1).show();
            }
        });
    }

    public void deleteApi(int wallId, final int position) {
        if (isAdded()) {
            showLoadingDialog();
        }
        ((Apis) new Retrofit.Builder().baseUrl("http://devworktools.fr/contenu/conseiller/").addConverterFactory(GsonConverterFactory.create()).client(new OkHttpClient.Builder().addInterceptor(provideHttpLoggingInterceptor()).readTimeout(120, TimeUnit.SECONDS).writeTimeout(120, TimeUnit.SECONDS).build()).build().create(Apis.class)).deleteWallPost(wallId).enqueue(new Callback<WallRemovePostModel>() {
            public void onResponse(Call<WallRemovePostModel> call, Response<WallRemovePostModel> response) {
                if (!Wall.this.isAdded()) {
                    return;
                }
                if (!response.isSuccessful()) {
                    Wall.this.dismissLoadingDialog();
                    Toast.makeText(Wall.this.getContext(), "Data Not Found", 0).show();
                } else if (response.body() == null || response.body().getStatusCode() == null || response.body().getStatusCode().intValue() != 200) {
                    Wall.this.dismissLoadingDialog();
                    Toast.makeText(Wall.this.getContext(), "Data Not Found", 0).show();
                } else {
                    Wall.this.dismissLoadingDialog();
                    Toast.makeText(Wall.this.getContext(), response.body().getMessage(), 0).show();
                    Wall.this.mAdapter.removeItem(position);
                    Wall.this.dialog.dismiss();
                }
            }

            public void onFailure(Call<WallRemovePostModel> call, Throwable t) {
                if (Wall.this.isAdded()) {
                    Wall.this.dismissLoadingDialog();
                }
                Wall.this.rv_wall.setVisibility(8);
                Wall.this.tvNoDataFound.setVisibility(0);
                Toast.makeText(Wall.this.getContext(), t.getLocalizedMessage(), 1).show();
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

    public void onResume() {
        super.onResume();
        wallPosts();
        showLoadingDialog();
    }

    private static HttpLoggingInterceptor provideHttpLoggingInterceptor() {
        return new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            public void log(String message) {
                Log.d("Injector", message);
            }
        });
    }

    public void deletePost(ArrayList<WallModel.Datum> arrayList, final int pos, final int wallId) {
        Dialog dialog2 = new Dialog(getContext());
        this.dialog = dialog2;
        dialog2.requestWindowFeature(1);
        this.dialog.setContentView(R.layout.customdialogremove);
        this.dialog.setCancelable(false);
        this.dialog.show();
        ((Button) this.dialog.findViewById(R.id.btnCommentDialog)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Wall.this.dialog.dismiss();
            }
        });
        ((TextView) this.dialog.findViewById(R.id.tvCommentDeleteYes)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Wall.this.deleteApi(wallId, pos);
            }
        });
        ((TextView) this.dialog.findViewById(R.id.tvCommentDeleteNo)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Wall.this.dialog.dismiss();
            }
        });
    }
}
