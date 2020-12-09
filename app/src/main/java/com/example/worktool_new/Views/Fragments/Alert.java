package com.example.worktool_new.Views.Fragments;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.worktool_new.Adapters.Adapter_alert;
import com.example.worktool_new.Models.MyAlertsModel;
import com.example.worktool_new.R;
import com.example.worktool_new.Retrofit.Apis;
import com.example.worktool_new.Util.SharedPreference.App;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Alert extends Fragment implements FragmentManager.OnBackStackChangedListener {
    /* access modifiers changed from: private */
    public Adapter_alert mAdapter;
    private ProgressDialog progress;
    /* access modifiers changed from: private */
    public RecyclerView recycler_alert;
    /* access modifiers changed from: private */
    public TextView tvNoDataFound;
    private View view;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (this.view == null) {
            View inflate = inflater.inflate(R.layout.fragment_alert, container, false);
            this.view = inflate;
            this.recycler_alert = (RecyclerView) inflate.findViewById(R.id.recycler_alert);
            this.tvNoDataFound = (TextView) this.view.findViewById(R.id.tvNoDataFound);
            AlertPosts();
        }
        return this.view;
    }

    public void onBackStackChanged() {
    }

    private void AlertPosts() {
        if (isAdded()) {
            showLoadingDialog();
        }
        ((Apis) new Retrofit.Builder().baseUrl("http://devworktools.fr/contenu/conseiller/").addConverterFactory(GsonConverterFactory.create()).build().create(Apis.class)).myAlerts(App.getAppPreference().getString("LoginId")).enqueue(new Callback<MyAlertsModel>() {
            public void onResponse(Call<MyAlertsModel> call, Response<MyAlertsModel> response) {
                if (!response.isSuccessful()) {
                    Alert.this.dismissLoadingDialog();
                    Alert.this.recycler_alert.setVisibility(8);
                    Alert.this.tvNoDataFound.setVisibility(0);
                    Toast.makeText(Alert.this.getContext(), "Data Not Found", 0).show();
                } else if (response.body() == null || response.body().getStatusCode() == null || response.body().getStatusCode().intValue() != 200) {
                    Alert.this.dismissLoadingDialog();
                    Alert.this.recycler_alert.setVisibility(8);
                    Alert.this.tvNoDataFound.setVisibility(0);
                    Toast.makeText(Alert.this.getContext(), "Data Not Found", 0).show();
                } else if (response.body().getData() == null) {
                    Alert.this.dismissLoadingDialog();
                    Alert.this.recycler_alert.setVisibility(8);
                    Alert.this.tvNoDataFound.setVisibility(0);
                    Toast.makeText(Alert.this.getContext(), "Data Not Found", 0).show();
                } else if (response.body() == null || response.body().getData().size() <= 0) {
                    Alert.this.dismissLoadingDialog();
                    Alert.this.recycler_alert.setVisibility(8);
                    Alert.this.tvNoDataFound.setVisibility(0);
                    Toast.makeText(Alert.this.getContext(), "Data Not Found", 0).show();
                } else {
                    Alert.this.dismissLoadingDialog();
                    Alert.this.recycler_alert.setVisibility(0);
                    Alert.this.tvNoDataFound.setVisibility(8);
                    Adapter_alert unused = Alert.this.mAdapter = new Adapter_alert(Alert.this.getActivity(), response.body().getData());
                    Alert.this.recycler_alert.setAdapter(Alert.this.mAdapter);
                }
            }

            public void onFailure(Call<MyAlertsModel> call, Throwable t) {
                Alert.this.dismissLoadingDialog();
                Alert.this.recycler_alert.setVisibility(8);
                Alert.this.tvNoDataFound.setVisibility(0);
                Toast.makeText(Alert.this.getContext(), t.getLocalizedMessage(), 1).show();
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
