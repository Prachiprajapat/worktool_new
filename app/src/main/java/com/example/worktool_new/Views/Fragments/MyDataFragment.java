package com.example.worktool_new.Views.Fragments;

import android.app.Dialog;
import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.downloader.OnDownloadListener;
import com.downloader.OnProgressListener;
import com.downloader.OnStartOrResumeListener;
import com.downloader.PRDownloader;
import com.downloader.Progress;
import com.downloader.Status;
import com.example.worktool_new.Adapters.Adapter_MyData;
import com.example.worktool_new.Models.MyDataModel;
import com.example.worktool_new.Models.MyDeleteDataModel;
import com.example.worktool_new.R;
import com.example.worktool_new.Retrofit.Apis;
import com.example.worktool_new.Util.SharedPreference.App;
import com.example.worktool_new.Util.SharedPreference.AppConstants;
import com.example.worktool_new.Views.Activities.RecyclerTouchListener;
import com.tonyodev.fetch2.Error;
import com.tonyodev.fetch2.Fetch;
import com.tonyodev.fetch2.FetchConfiguration;
import com.tonyodev.fetch2.NetworkType;
import com.tonyodev.fetch2.Priority;
import com.tonyodev.fetch2.Request;
import com.tonyodev.fetch2core.Func;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MyDataFragment extends Fragment implements Adapter_MyData.deleteData, Adapter_MyData.editData, Adapter_MyData.downloadData {
    /* access modifiers changed from: private */
    public Adapter_MyData mAdapter;
    private ProgressDialog progress;
    BroadcastReceiver receiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            MyDataFragment.this.getMyData();
        }
    };
    /* access modifiers changed from: private */
    public RecyclerView rv_data;
    private RecyclerTouchListener touchListener;
    /* access modifiers changed from: private */
    public TextView tvNoDataFound;
    private View view;
    Dialog dialog;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        PRDownloader.initialize(getActivity());
        if (this.view == null) {
            this.view = inflater.inflate(R.layout.fragment_my__data, container, false);
        }
        return this.view;
    }

    public void onViewCreated(View view2, Bundle savedInstanceState) {
        super.onViewCreated(view2, savedInstanceState);
        init();
    }

    public void init() {
        this.rv_data = (RecyclerView) this.view.findViewById(R.id.rv_data);
        this.tvNoDataFound = (TextView) this.view.findViewById(R.id.tvNoDataFound);
        getMyData();
        RecyclerTouchListener recyclerTouchListener = new RecyclerTouchListener(getActivity(), this.rv_data);
        this.touchListener = recyclerTouchListener;
        recyclerTouchListener.setClickable((RecyclerTouchListener.OnRowClickListener) new RecyclerTouchListener.OnRowClickListener() {
            public void onRowClicked(int position) {
            }

            public void onIndependentViewClicked(int independentViewID, int position) {
            }
        }).setSwipeOptionViews(Integer.valueOf(R.id.delete_task), Integer.valueOf(R.id.edit_task)).setSwipeable(R.id.rowFG, R.id.rowBG, new RecyclerTouchListener.OnSwipeOptionsClickListener() {
            public void onSwipeOptionClicked(int viewID, int position) {
            }
        });
    }

    public void onResume() {
        super.onResume();
        getActivity().registerReceiver(this.receiver, new IntentFilter("updateData"));
        this.rv_data.addOnItemTouchListener(this.touchListener);
    }

    public void onDestroy() {
        super.onDestroy();
        if (this.receiver != null) {
            getActivity().unregisterReceiver(this.receiver);
        }
    }

    private void deleteData(final ArrayList<MyDataModel.Datum> datamodelArraylists, final int position) {
        showLoadingDialog();
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://devworktools.fr/contenu/conseiller/").addConverterFactory(GsonConverterFactory.create()).build();
        ((Apis) retrofit.create(Apis.class)).deleteMyData(datamodelArraylists.get(position).getIdFichier(), datamodelArraylists.get(position).getTable()).enqueue(new Callback<MyDeleteDataModel>() {
            public void onResponse(Call<MyDeleteDataModel> call, Response<MyDeleteDataModel> response) {
                if (!response.isSuccessful()) {
                    MyDataFragment.this.dismissLoadingDialog();
                    dialog.dismiss();
                    Toast.makeText(MyDataFragment.this.getContext(), "Data Not Found", 0).show();
                } else if (response.body() != null && response.body().getStatusCode() != null && response.body().getStatusCode().intValue() == 200) {
                    MyDataFragment.this.dismissLoadingDialog();
                    Toast.makeText(MyDataFragment.this.getActivity(), response.body().getMessage(), 0).show();
                    datamodelArraylists.remove(position);
                    MyDataFragment.this.mAdapter.notifyItemRemoved(position);
                    MyDataFragment.this.mAdapter.notifyItemRangeChanged(position, datamodelArraylists.size());
                    dialog.dismiss();
                }
            }

            public void onFailure(Call<MyDeleteDataModel> call, Throwable t) {
                MyDataFragment.this.dismissLoadingDialog();
                dialog.dismiss();
                Toast.makeText(MyDataFragment.this.getContext(), t.getLocalizedMessage(), 1).show();
            }
        });
    }

    /* access modifiers changed from: private */
    public void getMyData() {
        showLoadingDialog();
        ((Apis) new Retrofit.Builder().baseUrl("http://devworktools.fr/contenu/conseiller/").addConverterFactory(GsonConverterFactory.create()).build().create(Apis.class)).getMyData(App.getAppPreference().getString("LoginId")).enqueue(new Callback<MyDataModel>() {
            public void onResponse(Call<MyDataModel> call, Response<MyDataModel> response) {
                if (!response.isSuccessful()) {
                    MyDataFragment.this.dismissLoadingDialog();
                    MyDataFragment.this.rv_data.setVisibility(8);
                    MyDataFragment.this.tvNoDataFound.setVisibility(0);
                    Toast.makeText(MyDataFragment.this.getContext(), "Data Not Found", 0).show();
                } else if (response.body() == null || response.body().getStatusCode() == null || response.body().getStatusCode().intValue() != 200) {
                    MyDataFragment.this.dismissLoadingDialog();
                    MyDataFragment.this.rv_data.setVisibility(8);
                    MyDataFragment.this.tvNoDataFound.setVisibility(0);
                    Toast.makeText(MyDataFragment.this.getContext(), "Data Not Found", 0).show();
                } else if (response.body() == null || response.body().getData().size() <= 0) {
                    MyDataFragment.this.dismissLoadingDialog();
                    MyDataFragment.this.rv_data.setVisibility(8);
                    MyDataFragment.this.tvNoDataFound.setVisibility(0);
                    Toast.makeText(MyDataFragment.this.getContext(), "Data Not Found", 0).show();
                } else {
                    MyDataFragment.this.dismissLoadingDialog();
                    MyDataFragment.this.rv_data.setVisibility(0);
                    MyDataFragment.this.tvNoDataFound.setVisibility(8);
                    MyDataFragment myDataFragment = MyDataFragment.this;
                    FragmentActivity activity = MyDataFragment.this.getActivity();
                    ArrayList<MyDataModel.Datum> data = response.body().getData();
                    MyDataFragment myDataFragment2 = MyDataFragment.this;
                    Adapter_MyData unused = myDataFragment.mAdapter = new Adapter_MyData(activity, data, myDataFragment2, myDataFragment2, myDataFragment2);
                    MyDataFragment.this.rv_data.setAdapter(MyDataFragment.this.mAdapter);
                }
            }

            public void onFailure(Call<MyDataModel> call, Throwable t) {
                MyDataFragment.this.dismissLoadingDialog();
                MyDataFragment.this.rv_data.setVisibility(8);
                MyDataFragment.this.tvNoDataFound.setVisibility(0);
                Toast.makeText(MyDataFragment.this.getContext(), t.getLocalizedMessage(), 1).show();
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

    public void deleteMyData(final ArrayList<MyDataModel.Datum> datamodelArraylists, final int position) {

        Dialog dialog2 = new Dialog(getContext());
        this.dialog = dialog2;
        dialog2.requestWindowFeature(1);
        this.dialog.setContentView(R.layout.customdialogremove);
        this.dialog.setCancelable(false);
        this.dialog.show();
        ((Button) this.dialog.findViewById(R.id.btnCommentDialog)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        ((TextView) this.dialog.findViewById(R.id.tvCommentDeleteYes)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                deleteData(datamodelArraylists, position);
            }
        });
        ((TextView) this.dialog.findViewById(R.id.tvCommentDeleteNo)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        //(datamodelArraylists, position);
    }

    public void editMyData(String dataId, String table, String name) {
        Fragment fr = new EditData();
        Bundle bundle = new Bundle();
        bundle.putString("dataId", dataId);
        bundle.putString("table", table);
        bundle.putString("dataName", name);
        fr.setArguments(bundle);
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.add((int) R.id.container, fr);
        fragmentTransaction.addToBackStack((String) null);
        fragmentTransaction.commit();
    }

    @Override
    public void downloadMyData(final int position, final String arrayList, ArrayList<MyDataModel.Datum> datamodellist) {
        String DownloadUrl = AppConstants.IMAGEURL + arrayList;
        String fileName = DownloadUrl.substring(DownloadUrl.lastIndexOf('/') + 1);
        String filename = fileName;
        String downloadUrlOfImage = AppConstants.IMAGEURL + arrayList;
        File direct =
                new File(Environment
                        .getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)
                        .getAbsolutePath() + "/" + "gautam" + "/");

        if (!direct.exists()) {
            direct.mkdir();
            Log.i("", "dir created for first time");
        }

        DownloadManager dm = (DownloadManager) getActivity().getSystemService(Context.DOWNLOAD_SERVICE);
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
        Intent browserIntent = new Intent("android.intent.action.VIEW", Uri.parse(AppConstants.IMAGEURL + arrayList));
        startActivity(browserIntent);
    }

}
