package com.example.worktool_new.Views.Fragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import com.example.worktool_new.Models.RenameDataModel;
import com.example.worktool_new.R;
import com.example.worktool_new.Retrofit.Apis;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class EditData extends Fragment {
    private Button btnRecord;
    Context context;
    String dataId;
    String dataName;
    /* access modifiers changed from: private */
    public EditText etRenameData;
    private ProgressDialog progress;
    String table;
    View view;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_edit_data, container, false);
        this.view = inflate;
        this.etRenameData = (EditText) inflate.findViewById(R.id.etRenameData);
        this.btnRecord = (Button) this.view.findViewById(R.id.btnRecord);
        return this.view;
    }

    public void onViewCreated(View view2, Bundle savedInstanceState) {
        String str;
        super.onViewCreated(view2, savedInstanceState);
        this.dataId = getArguments().getString("dataId");
        this.table = getArguments().getString("table");
        String string = getArguments().getString("dataName");
        this.dataName = string;
        if (!string.isEmpty() && (str = this.dataName) != null) {
            this.etRenameData.setText(str);
        }
        this.btnRecord.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (EditData.this.etRenameData.getText().toString().isEmpty() || EditData.this.etRenameData.getText().toString() == null) {
                    Toast.makeText(EditData.this.getContext(), "Please enter text first", 0).show();
                } else {
                    EditData.this.renameData();
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public void renameData() {
        showLoadingDialog();
        ((Apis) new Retrofit.Builder().baseUrl("http://devworktools.fr/contenu/conseiller/").addConverterFactory(GsonConverterFactory.create()).build().create(Apis.class)).renameMyData(Integer.parseInt(this.dataId), this.etRenameData.getText().toString(), this.table).enqueue(new Callback<RenameDataModel>() {
            public void onResponse(Call<RenameDataModel> call, Response<RenameDataModel> response) {
                if (!response.isSuccessful()) {
                    EditData.this.dismissLoadingDialog();
                    Toast.makeText(EditData.this.getContext(), "Data Not Found", 0).show();
                } else if (response.body() == null || response.body().getStatus_code() == null || !response.body().getStatus_code().equals("200")) {
                    EditData.this.dismissLoadingDialog();
                    Toast.makeText(EditData.this.getContext(), "Data Not Found", 0).show();
                } else {
                    EditData.this.dismissLoadingDialog();
                    Toast.makeText(EditData.this.getContext(), response.body().getMessage(), 0).show();
                    EditData.this.getContext().sendBroadcast(new Intent("updateData"));
                    if (EditData.this.getFragmentManager() != null) {
                        EditData.this.getFragmentManager().popBackStack();
                    }
                }
            }

            public void onFailure(Call<RenameDataModel> call, Throwable t) {
                EditData.this.dismissLoadingDialog();
                Toast.makeText(EditData.this.getContext(), t.getLocalizedMessage(), 1).show();
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
