package com.example.worktool_new.Views.Fragments;

import android.app.ProgressDialog;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.pdf.PdfDocument;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;
import androidx.core.internal.view.SupportMenu;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import com.example.worktool_new.Adapters.AdapterStats;
import com.example.worktool_new.Models.StatsModel;
import com.example.worktool_new.R;
import com.example.worktool_new.Retrofit.Apis;
import com.example.worktool_new.Util.SharedPreference.App;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Stats extends Fragment {
    /* access modifiers changed from: private */
    public AdapterStats mAdapter;
    private ProgressDialog progress;
    /* access modifiers changed from: private */
    public RecyclerView recyclerStats;
    RelativeLayout rlExportCsv;
    RelativeLayout rlExportPdf;
    View view;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (this.view == null) {
            this.view = inflater.inflate(R.layout.fragment_stats, container, false);
            Dexter.withActivity(getActivity()).withPermissions("android.permission.CAMERA", "android.permission.READ_CONTACTS", "android.permission.RECORD_AUDIO", "android.permission.READ_EXTERNAL_STORAGE").withListener(new MultiplePermissionsListener() {
                public void onPermissionsChecked(MultiplePermissionsReport report) {
                    if (report != null && !report.areAllPermissionsGranted() && report.isAnyPermissionPermanentlyDenied()) {
                        List<PermissionDeniedResponse> list = report.getDeniedPermissionResponses();
                        for (int i = 0; i < list.size(); i++) {
                            if (list.get(i).getPermissionName() == "android.permission.READ_EXTERNAL_STORAGE") {
                                Toast.makeText(Stats.this.getActivity(), "Permissions Needed", 0).show();
                            }
                        }
                    }
                }

                public void onPermissionRationaleShouldBeShown(List<PermissionRequest> list, PermissionToken token) {
                }
            }).check();
            this.recyclerStats = (RecyclerView) this.view.findViewById(R.id.recyclerStats);
            this.rlExportPdf = (RelativeLayout) this.view.findViewById(R.id.rlExportPdf);
            this.rlExportCsv = (RelativeLayout) this.view.findViewById(R.id.rlExportCsv);
            statsPosts();
            this.rlExportPdf.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Stats.this.createPdf("Example Text");
                }
            });
        }
        return this.view;
    }

    public void createPdf(String sometext) {
        PdfDocument document = new PdfDocument();
        PdfDocument.Page page = document.startPage(new PdfDocument.PageInfo.Builder(300, 600, 1).create());
        Canvas canvas = page.getCanvas();
        Paint paint = new Paint();
        paint.setColor(SupportMenu.CATEGORY_MASK);
        canvas.drawCircle(50.0f, 50.0f, 30.0f, paint);
        paint.setColor(ViewCompat.MEASURED_STATE_MASK);
        canvas.drawText(sometext, 80.0f, 50.0f, paint);
        document.finishPage(page);
        PdfDocument.Page page2 = document.startPage(new PdfDocument.PageInfo.Builder(300, 600, 2).create());
        Canvas canvas2 = page2.getCanvas();
        Paint paint2 = new Paint();
        paint2.setColor(-16776961);
        canvas2.drawCircle(100.0f, 100.0f, 100.0f, paint2);
        document.finishPage(page2);
        String directory_path = Environment.getExternalStorageDirectory().toString() + "/mypdf/";
        File file = new File(directory_path);
        if (!file.exists()) {
            file.mkdirs();
        }
        try {
            document.writeTo(new FileOutputStream(new File(directory_path + "test-2.pdf")));
            Toast.makeText(getActivity(), "Done", 1).show();
        } catch (IOException e) {
            Log.e("main", "error " + e.toString());
            Toast.makeText(getActivity(), "Something wrong: " + e.toString(), 1).show();
        }
        document.close();
    }

    private void statsPosts() {
        showLoadingDialog();
        ((Apis) new Retrofit.Builder().baseUrl("http://devworktools.fr/contenu/conseiller/").addConverterFactory(GsonConverterFactory.create()).build().create(Apis.class)).getStats(App.getAppPreference().getString("LoginId")).enqueue(new Callback<StatsModel>() {
            public void onResponse(Call<StatsModel> call, Response<StatsModel> response) {
                if (!response.isSuccessful()) {
                    Stats.this.dismissLoadingDialog();
                    Toast.makeText(Stats.this.getContext(), "Data Not Found", 0).show();
                } else if (response.body() != null && response.body().getData().size() > 0) {
                    Stats.this.dismissLoadingDialog();
                    AdapterStats unused = Stats.this.mAdapter = new AdapterStats(Stats.this.getActivity(), response.body().getData());
                    Stats.this.recyclerStats.setAdapter(Stats.this.mAdapter);
                }
            }

            public void onFailure(Call<StatsModel> call, Throwable t) {
                Stats.this.dismissLoadingDialog();
                Toast.makeText(Stats.this.getContext(), t.getLocalizedMessage(), 1).show();
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
