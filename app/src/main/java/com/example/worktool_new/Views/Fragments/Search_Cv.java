package com.example.worktool_new.Views.Fragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.exifinterface.media.ExifInterface;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import com.example.worktool_new.Adapters.My_Cv;
import com.example.worktool_new.Models.AdvanceSearchCVModel;
import com.example.worktool_new.Models.CustomModelCv;
import com.example.worktool_new.Models.MyCVModel;
import com.example.worktool_new.Models.NewCvModel;
import com.example.worktool_new.R;
import com.example.worktool_new.Retrofit.Apis;
import com.example.worktool_new.Util.SharedPreference.App;
import com.example.worktool_new.Util.SharedPreference.AppConstants;
import com.example.worktool_new.Views.Activities.Advanced_Search_Cv;
import com.example.worktool_new.Views.Activities.ImportCv;
import com.example.worktool_new.Views.Activities.UploadResume;
import com.google.gson.Gson;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import okhttp3.OkHttpClient;
import okhttp3.internal.cache.DiskLruCache;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Search_Cv extends Fragment implements My_Cv.ondowmloadClick {
    private static final int REQUEST_CODE = 101;
    /* access modifiers changed from: private */
    public ArrayList<NewCvModel> CvModelArrayList = new ArrayList<>();
    private RelativeLayout add_resume;
    private ArrayList<AdvanceSearchCVModel.Body> arrayList;
    private ArrayList<CustomModelCv> downloadCvlist;
    private ImageView iv_sort;
    /* access modifiers changed from: private */
    public My_Cv mAdapter;
    /* access modifiers changed from: private */
    public ArrayList<NewCvModel> newCvModelArrayList;
    private ProgressDialog progress;
    /* access modifiers changed from: private */
    public RecyclerView recycler_my_cv;
    private RelativeLayout rlImportCv;
    private RelativeLayout rlSearch;
    private EditText search;
    /* access modifiers changed from: private */
    public TextView tvNoDataFound;
    private View view;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (this.view == null) {
            View inflate = inflater.inflate(R.layout.fragment_search__cv, container, false);
            this.view = inflate;
            this.recycler_my_cv = (RecyclerView) inflate.findViewById(R.id.recycler_my_cv);
            this.tvNoDataFound = (TextView) this.view.findViewById(R.id.tvNoDataFound);
            this.add_resume = (RelativeLayout) this.view.findViewById(R.id.add_resume);
            this.iv_sort = (ImageView) this.view.findViewById(R.id.iv_sort);
            this.rlImportCv = (RelativeLayout) this.view.findViewById(R.id.rlImportCv);
            this.rlSearch = (RelativeLayout) this.view.findViewById(R.id.rlSearch);
            this.search = (EditText) this.view.findViewById(R.id.ed_searchCV);
            if (App.getAppPreference().getString("type").equals("benificiary")) {
                this.recycler_my_cv.setVisibility(4);
                this.tvNoDataFound.setVisibility(8);
                this.rlSearch.setVisibility(8);
            } else {
                this.recycler_my_cv.setVisibility(0);
                this.rlSearch.setVisibility(0);
                CvPosts();
            }
            this.add_resume.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Search_Cv.this.startActivity(new Intent(Search_Cv.this.getActivity(), UploadResume.class));
                }
            });
            this.rlImportCv.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Search_Cv.this.startActivity(new Intent(Search_Cv.this.getActivity(), ImportCv.class));
                }
            });
            this.iv_sort.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Search_Cv.this.startActivityForResult(new Intent(Search_Cv.this.getActivity(), Advanced_Search_Cv.class), 101);
                }
            });
            this.search.addTextChangedListener(new TextWatcher() {
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                }

                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    Search_Cv.this.mAdapter.getFilter().filter(s.toString());
                }

                public void afterTextChanged(Editable s) {
                }
            });
        }
        return this.view;
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == -1 && requestCode == 101 && data != null) {
            this.arrayList = (ArrayList) data.getSerializableExtra("advanceSearchDataList");
            this.newCvModelArrayList = new ArrayList<>();
            if (this.arrayList != null) {
                for (int i = 0; i < this.arrayList.size(); i++) {
                    NewCvModel newCvModel = new NewCvModel();
                    newCvModel.setDisponibilite(this.arrayList.get(i).getDisponibilite());
                    newCvModel.setFichier(this.arrayList.get(i).getFichier());
                    newCvModel.setNom(this.arrayList.get(i).getNom());
                    newCvModel.setIdCvliste(this.arrayList.get(i).getIdCvliste());
                    newCvModel.setTypecv(this.arrayList.get(i).getTypecv());
                    newCvModel.setPrenom(this.arrayList.get(i).getPrenom());
                    newCvModel.setTime(this.arrayList.get(i).getTime());
                    newCvModel.setTitre(this.arrayList.get(i).getTitre());
                    newCvModel.setStatus(DiskLruCache.VERSION_1);
                    this.newCvModelArrayList.add(newCvModel);
                }
            }
            Gson gson = new Gson();
            ArrayList<NewCvModel> arrayList2 = this.newCvModelArrayList;
            this.CvModelArrayList = arrayList2;
            Log.e("jijlj", gson.toJson((Object) arrayList2));
            if (App.getAppPreference().getString("time").equals("first")) {
                dismissLoadingDialog();
                this.mAdapter = new My_Cv(getActivity(), this.newCvModelArrayList, this);
            } else {
                dismissLoadingDialog();
                PrintStream printStream = System.out;
                printStream.println("prefernce list" + gson.toJson((Object) App.getAppPreference().getArrayList()));
                if (App.getAppPreference().getArrayList().size() > 0) {
                    for (int i2 = 0; i2 < App.getAppPreference().getArrayList().size(); i2++) {
                        for (int j = 0; j < this.newCvModelArrayList.size(); j++) {
                            if (App.getAppPreference().getArrayList().get(i2).getStatus().equals(ExifInterface.GPS_MEASUREMENT_2D)) {
                                this.newCvModelArrayList.get(i2).setStatus(ExifInterface.GPS_MEASUREMENT_2D);
                            }
                        }
                    }
                    this.mAdapter = new My_Cv(getActivity(), this.newCvModelArrayList, this);
                    PrintStream printStream2 = System.out;
                    printStream2.println("updaredliat" + gson.toJson((Object) this.newCvModelArrayList));
                }
            }
            this.recycler_my_cv.setAdapter(this.mAdapter);
        }
    }

    private void CvPosts() {
        if (isAdded()) {
            showLoadingDialog();
        }
        ((Apis) new Retrofit.Builder().baseUrl("http://devworktools.fr/contenu/conseiller/").addConverterFactory(GsonConverterFactory.create()).client(new OkHttpClient.Builder().addInterceptor(provideHttpLoggingInterceptor()).readTimeout(120, TimeUnit.SECONDS).writeTimeout(120, TimeUnit.SECONDS).build()).build().create(Apis.class)).getCvData(App.getAppPreference().getString("LoginId")).enqueue(new Callback<MyCVModel>() {
            public void onResponse(Call<MyCVModel> call, Response<MyCVModel> response) {
                if (!response.isSuccessful()) {
                    Search_Cv.this.dismissLoadingDialog();
                    Search_Cv.this.recycler_my_cv.setVisibility(8);
                    Search_Cv.this.tvNoDataFound.setVisibility(0);
                    Toast.makeText(Search_Cv.this.getContext(), "Data Not Found", 0).show();
                } else if (response.body() == null || response.body().getStatusCode() == null || response.body().getStatusCode().intValue() != 200) {
                    Search_Cv.this.dismissLoadingDialog();
                    Search_Cv.this.recycler_my_cv.setVisibility(8);
                    Search_Cv.this.tvNoDataFound.setVisibility(0);
                    Toast.makeText(Search_Cv.this.getContext(), "Data Not Found", 0).show();
                } else {
                    ArrayList unused = Search_Cv.this.newCvModelArrayList = new ArrayList();
                    if (response.body() == null || response.body().getData().size() <= 0) {
                        Search_Cv.this.dismissLoadingDialog();
                        Search_Cv.this.recycler_my_cv.setVisibility(8);
                        Search_Cv.this.tvNoDataFound.setVisibility(0);
                        Toast.makeText(Search_Cv.this.getContext(), "Data Not Found", 0).show();
                        return;
                    }
                    for (int i = 0; i < response.body().getData().size(); i++) {
                        NewCvModel newCvModel = new NewCvModel();
                        newCvModel.setDisponibilite(response.body().getData().get(i).getDisponibilite());
                        newCvModel.setFichier(response.body().getData().get(i).getFichier());
                        newCvModel.setNom(response.body().getData().get(i).getNom());
                        newCvModel.setIdCvliste(response.body().getData().get(i).getIdCvliste());
                        newCvModel.setTypecv(response.body().getData().get(i).getTypecv());
                        newCvModel.setPrenom(response.body().getData().get(i).getPrenom());
                        newCvModel.setTime(response.body().getData().get(i).getTime());
                        newCvModel.setTitre(response.body().getData().get(i).getTitre());
                        newCvModel.setStatus(DiskLruCache.VERSION_1);
                        Search_Cv.this.newCvModelArrayList.add(newCvModel);
                    }
                    Gson gson = new Gson();
                    Search_Cv search_Cv = Search_Cv.this;
                    ArrayList unused2 = search_Cv.CvModelArrayList = search_Cv.newCvModelArrayList;
                    Log.e("jijlj", gson.toJson((Object) Search_Cv.this.newCvModelArrayList));
                    if (App.getAppPreference().getString("time").equals("first")) {
                        Search_Cv.this.dismissLoadingDialog();
                        My_Cv unused3 = Search_Cv.this.mAdapter = new My_Cv(Search_Cv.this.getActivity(), Search_Cv.this.newCvModelArrayList, Search_Cv.this);
                    } else {
                        Search_Cv.this.dismissLoadingDialog();
                        PrintStream printStream = System.out;
                        printStream.println("prefernce list" + gson.toJson((Object) App.getAppPreference().getArrayList()));
                        if (App.getAppPreference().getArrayList().size() > 0) {
                            for (int i2 = 0; i2 < App.getAppPreference().getArrayList().size(); i2++) {
                                for (int j = 0; j < Search_Cv.this.newCvModelArrayList.size(); j++) {
                                    if (App.getAppPreference().getArrayList().get(i2).getStatus().equals(ExifInterface.GPS_MEASUREMENT_2D)) {
                                        ((NewCvModel) Search_Cv.this.newCvModelArrayList.get(i2)).setStatus(ExifInterface.GPS_MEASUREMENT_2D);
                                    }
                                }
                            }
                            My_Cv unused4 = Search_Cv.this.mAdapter = new My_Cv(Search_Cv.this.getActivity(), Search_Cv.this.newCvModelArrayList, Search_Cv.this);
                            PrintStream printStream2 = System.out;
                            printStream2.println("updaredliat" + gson.toJson((Object) Search_Cv.this.newCvModelArrayList));
                        }
                    }
                    Search_Cv.this.recycler_my_cv.setAdapter(Search_Cv.this.mAdapter);
                    Search_Cv.this.recycler_my_cv.setVisibility(0);
                    Search_Cv.this.tvNoDataFound.setVisibility(8);
                }
            }

            public void onFailure(Call<MyCVModel> call, Throwable t) {
                Search_Cv.this.dismissLoadingDialog();
                Search_Cv.this.recycler_my_cv.setVisibility(8);
                Search_Cv.this.tvNoDataFound.setVisibility(0);
                Toast.makeText(Search_Cv.this.getContext(), t.getLocalizedMessage(), 1).show();
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

    public void ondownloadClickListenr(int position, ArrayList<NewCvModel> cvlist, int i) {
        PrintStream printStream = System.out;
        printStream.println("adksad" + position);
        if (i == 1) {
            if (cvlist.get(position).getFichier().equals("")) {
                Toast.makeText(getContext(), AppConstants.NO_DATA_FOUND, 0).show();
            } else {
                cvlist.get(position).setStatus(ExifInterface.GPS_MEASUREMENT_2D);
                App.getAppPreference().saveString("time", "second");
                App.getAppPreference().saveArrayList(cvlist);
            }
            this.mAdapter.notifyDataSetChanged();
            return;
        }
        Intent browserIntent = new Intent("android.intent.action.VIEW", Uri.parse(AppConstants.IMAGEURL + cvlist.get(position).getFichier()));
        PrintStream printStream2 = System.out;
        printStream2.println("sadlakdlakdhttp://devworktools.fr/upload/" + cvlist.get(position).getFichier());
        startActivity(browserIntent);
    }

    public static class FileOpen {
        public static void openFile(Context context, File url) throws IOException {
            Uri uri = Uri.fromFile(url);
            Intent intent = new Intent("android.intent.action.VIEW");
            if (url.toString().contains(".doc") || url.toString().contains(".docx")) {
                intent.setDataAndType(uri, "application/msword");
            } else if (url.toString().contains(".pdf")) {
                intent.setDataAndType(uri, "application/pdf");
            } else if (url.toString().contains(".ppt") || url.toString().contains(".pptx")) {
                intent.setDataAndType(uri, "application/vnd.ms-powerpoint");
            } else if (url.toString().contains(".xls") || url.toString().contains(".xlsx")) {
                intent.setDataAndType(uri, "application/vnd.ms-excel");
            } else if (url.toString().contains(".zip") || url.toString().contains(".rar")) {
                intent.setDataAndType(uri, "application/x-wav");
            } else if (url.toString().contains(".rtf")) {
                intent.setDataAndType(uri, "application/rtf");
            } else if (url.toString().contains(".wav") || url.toString().contains(".mp3")) {
                intent.setDataAndType(uri, "audio/x-wav");
            } else if (url.toString().contains(".gif")) {
                intent.setDataAndType(uri, "image/gif");
            } else if (url.toString().contains(".jpg") || url.toString().contains(".jpeg") || url.toString().contains(".png")) {
                intent.setDataAndType(uri, "image/jpeg");
            } else if (url.toString().contains(".txt")) {
                intent.setDataAndType(uri, "text/plain");
            } else if (url.toString().contains(".3gp") || url.toString().contains(".mpg") || url.toString().contains(".mpeg") || url.toString().contains(".mpe") || url.toString().contains(".mp4") || url.toString().contains(".avi")) {
                intent.setDataAndType(uri, "video/*");
            } else {
                intent.setDataAndType(uri, "*/*");
            }
            intent.addFlags(268435456);
            context.startActivity(intent);
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
