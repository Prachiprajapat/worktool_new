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
import com.example.worktool_new.Adapters.Adapter_wallpro;
import com.example.worktool_new.Models.WallProModel;
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

public class WallPro extends Fragment implements Adapter_wallpro.deleteApi {
    /* access modifiers changed from: private */
    public ArrayList<WallProModel.Datum> WallProModelArraylist = new ArrayList<>();
    /* access modifiers changed from: private */
    public ArrayList<WallProModel.Datum> WallProModelArraylist1 = new ArrayList<>();
    /* access modifiers changed from: private */
    public Dialog dialog;
    private EditText etSearchWallPro;
    private LinearLayout llWriteOnWall;
    /* access modifiers changed from: private */
    public Adapter_wallpro mAdapter;
    private ProgressDialog progress;
    /* access modifiers changed from: private */
    public RecyclerView rv_wall;
    /* access modifiers changed from: private */
    public TextView tvNoDataFound;
    private View view;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (this.view == null) {
            View inflate = inflater.inflate(R.layout.fragment_wallpro, container, false);
            this.view = inflate;
            this.rv_wall = (RecyclerView) inflate.findViewById(R.id.rv_wall);
            this.etSearchWallPro = (EditText) this.view.findViewById(R.id.etSearchWallPro);
            this.tvNoDataFound = (TextView) this.view.findViewById(R.id.tvNoDataFound);
            LinearLayout linearLayout = (LinearLayout) this.view.findViewById(R.id.llWriteOnWall);
            this.llWriteOnWall = linearLayout;
            linearLayout.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    App.getAppPreference().saveString("postwall", "pro");
                    WallPro.this.startActivity(new Intent(WallPro.this.getActivity(), WallPost.class));
                }
            });
            wallProPosts();
            this.etSearchWallPro.addTextChangedListener(new TextWatcher() {
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                }

                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    if (s.toString().length() > 0) {
                        WallPro.this.WallProModelArraylist1.clear();
                        for (int i = 0; i < WallPro.this.WallProModelArraylist.size(); i++) {
                            if ((((WallProModel.Datum) WallPro.this.WallProModelArraylist.get(i)).getPrenom() != null && ((WallProModel.Datum) WallPro.this.WallProModelArraylist.get(i)).getPrenom().startsWith(s.toString())) || ((((WallProModel.Datum) WallPro.this.WallProModelArraylist.get(i)).getCivilite() != null && ((WallProModel.Datum) WallPro.this.WallProModelArraylist.get(i)).getCivilite().startsWith(s.toString())) || ((((WallProModel.Datum) WallPro.this.WallProModelArraylist.get(i)).getTexte() != null && ((WallProModel.Datum) WallPro.this.WallProModelArraylist.get(i)).getTexte().startsWith(s.toString())) || ((((WallProModel.Datum) WallPro.this.WallProModelArraylist.get(i)).getEmail() != null && ((WallProModel.Datum) WallPro.this.WallProModelArraylist.get(i)).getEmail().startsWith(s.toString())) || ((((WallProModel.Datum) WallPro.this.WallProModelArraylist.get(i)).getDateheure() != null && ((WallProModel.Datum) WallPro.this.WallProModelArraylist.get(i)).getDateheure().startsWith(s.toString())) || ((((WallProModel.Datum) WallPro.this.WallProModelArraylist.get(i)).getComment() != null && ((WallProModel.Datum) WallPro.this.WallProModelArraylist.get(i)).getComment().startsWith(s.toString())) || ((((WallProModel.Datum) WallPro.this.WallProModelArraylist.get(i)).getDescription() != null && ((WallProModel.Datum) WallPro.this.WallProModelArraylist.get(i)).getDescription().startsWith(s.toString())) || (((WallProModel.Datum) WallPro.this.WallProModelArraylist.get(i)).getNom() != null && ((WallProModel.Datum) WallPro.this.WallProModelArraylist.get(i)).getNom().startsWith(s.toString()))))))))) {
                                WallPro.this.WallProModelArraylist1.add(WallPro.this.WallProModelArraylist.get(i));
                            }
                        }
                        WallPro.this.mAdapter.updateList(WallPro.this.WallProModelArraylist1);
                        return;
                    }
                    if (WallPro.this.WallProModelArraylist1.size() > 0) {
                        WallPro.this.WallProModelArraylist1.clear();
                    }
                    WallPro.this.mAdapter.updateList(WallPro.this.WallProModelArraylist);
                }

                public void afterTextChanged(Editable s) {
                }
            });
        }
        return this.view;
    }

    private void wallProPosts() {
        if (isAdded()) {
            showLoadingDialog();
        }
        ((Apis) new Retrofit.Builder().baseUrl("http://devworktools.fr/contenu/conseiller/").addConverterFactory(GsonConverterFactory.create()).build().create(Apis.class)).wallPro(App.getAppPreference().getString("LoginId")).enqueue(new Callback<WallProModel>() {
            public void onResponse(Call<WallProModel> call, Response<WallProModel> response) {
                if (!WallPro.this.isAdded()) {
                    return;
                }
                if (!response.isSuccessful()) {
                    WallPro.this.dismissLoadingDialog();
                    WallPro.this.rv_wall.setVisibility(8);
                    WallPro.this.tvNoDataFound.setVisibility(0);
                    Toast.makeText(WallPro.this.getContext(), "Data Not Found", 0).show();
                } else if (response.body() == null || response.body().getStatusCode() == null || response.body().getStatusCode().intValue() != 200) {
                    WallPro.this.dismissLoadingDialog();
                    WallPro.this.rv_wall.setVisibility(8);
                    WallPro.this.tvNoDataFound.setVisibility(0);
                    Toast.makeText(WallPro.this.getContext(), "Data Not Found", 0).show();
                } else if (response.body() == null || response.body().getData().size() <= 0) {
                    WallPro.this.dismissLoadingDialog();
                    WallPro.this.rv_wall.setVisibility(8);
                    WallPro.this.tvNoDataFound.setVisibility(0);
                    Toast.makeText(WallPro.this.getContext(), "Data Not Found", 0).show();
                } else {
                    WallPro.this.dismissLoadingDialog();
                    WallPro.this.rv_wall.setVisibility(0);
                    WallPro.this.tvNoDataFound.setVisibility(8);
                    ArrayList unused = WallPro.this.WallProModelArraylist = response.body().getData();
                    Adapter_wallpro unused2 = WallPro.this.mAdapter = new Adapter_wallpro(WallPro.this.getActivity(), WallPro.this.WallProModelArraylist, WallPro.this);
                    WallPro.this.rv_wall.setAdapter(WallPro.this.mAdapter);
                }
            }

            public void onFailure(Call<WallProModel> call, Throwable t) {
                if (WallPro.this.isAdded()) {
                    WallPro.this.dismissLoadingDialog();
                }
                WallPro.this.rv_wall.setVisibility(8);
                WallPro.this.tvNoDataFound.setVisibility(0);
                Toast.makeText(WallPro.this.getContext(), t.getLocalizedMessage(), 1).show();
            }
        });
    }

    public void deleteApi(int wallId, final int position) {
        if (isAdded()) {
            showLoadingDialog();
        }
        ((Apis) new Retrofit.Builder().baseUrl("http://devworktools.fr/contenu/conseiller/").addConverterFactory(GsonConverterFactory.create()).client(new OkHttpClient.Builder().addInterceptor(provideHttpLoggingInterceptor()).readTimeout(120, TimeUnit.SECONDS).writeTimeout(120, TimeUnit.SECONDS).build()).build().create(Apis.class)).deleteWallProPost(wallId).enqueue(new Callback<WallRemovePostModel>() {
            public void onResponse(Call<WallRemovePostModel> call, Response<WallRemovePostModel> response) {
                if (!WallPro.this.isAdded()) {
                    return;
                }
                if (!response.isSuccessful()) {
                    WallPro.this.dismissLoadingDialog();
                    Toast.makeText(WallPro.this.getContext(), "Data Not Found", 0).show();
                } else if (response.body() == null || response.body().getStatusCode() == null || response.body().getStatusCode().intValue() != 200) {
                    WallPro.this.dismissLoadingDialog();
                    Toast.makeText(WallPro.this.getContext(), "Data Not Found", 0).show();
                } else {
                    WallPro.this.dismissLoadingDialog();
                    Toast.makeText(WallPro.this.getContext(), response.body().getMessage(), 0).show();
                    WallPro.this.mAdapter.removeItem(position);
                    WallPro.this.dialog.dismiss();
                }
            }

            public void onFailure(Call<WallRemovePostModel> call, Throwable t) {
                if (WallPro.this.isAdded()) {
                    WallPro.this.dismissLoadingDialog();
                }
                WallPro.this.rv_wall.setVisibility(8);
                WallPro.this.tvNoDataFound.setVisibility(0);
                Toast.makeText(WallPro.this.getContext(), t.getLocalizedMessage(), 1).show();
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
        wallProPosts();
        showLoadingDialog();
    }

    private static HttpLoggingInterceptor provideHttpLoggingInterceptor() {
        return new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            public void log(String message) {
                Log.d("Injector", message);
            }
        });
    }

    public void deletePost(ArrayList<WallProModel.Datum> arrayList, final int pos, final int wallId) {
        Dialog dialog2 = new Dialog(getContext());
        this.dialog = dialog2;
        dialog2.requestWindowFeature(1);
        this.dialog.setContentView(R.layout.customdialogremove);
        this.dialog.setCancelable(false);
        this.dialog.show();
        ((Button) this.dialog.findViewById(R.id.btnCommentDialog)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                WallPro.this.dialog.dismiss();
            }
        });
        ((TextView) this.dialog.findViewById(R.id.tvCommentDeleteYes)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                WallPro.this.deleteApi(wallId, pos);
            }
        });
        ((TextView) this.dialog.findViewById(R.id.tvCommentDeleteNo)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                WallPro.this.dialog.dismiss();
            }
        });
    }
}
