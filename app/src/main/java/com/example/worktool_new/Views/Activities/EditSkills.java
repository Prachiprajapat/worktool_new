package com.example.worktool_new.Views.Activities;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import com.example.worktool_new.Adapters.EditSkillAdapter;
import com.example.worktool_new.Models.getskills.SkillModel;
import com.example.worktool_new.R;
import com.example.worktool_new.Retrofit.Apis;
import com.example.worktool_new.Util.SharedPreference.App;
import java.util.ArrayList;
import okhttp3.internal.cache.DiskLruCache;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class EditSkills extends AppCompatActivity implements EditSkillAdapter.onaddClick, EditSkillAdapter.startDialog {
    private static ProgressDialog progress;
    ArrayList<String> arrayList = new ArrayList<>();
    private EditSkillAdapter editSkillAdapter;
    LinearLayout ll_add;
    RecyclerView recyclerView;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) R.layout.activity_edit_skills);
        this.recyclerView = (RecyclerView) findViewById(R.id.rv_editskill);
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.ll_add);
        this.ll_add = linearLayout;
        linearLayout.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                App.getAppPreference().saveString("random", "second");
                EditSkills.this.arrayList.add(DiskLruCache.VERSION_1);
                EditSkills editSkills = EditSkills.this;
                editSkills.setAdapter(editSkills.arrayList);
            }
        });
        this.arrayList.add(DiskLruCache.VERSION_1);
        setAdapter(this.arrayList);
        App.getAppPreference().saveString("random", "first");
    }

    /* access modifiers changed from: private */
    public void setAdapter(ArrayList<String> arrayList2) {
        EditSkillAdapter editSkillAdapter2 = new EditSkillAdapter(this, arrayList2, this, App.getAppPreference().getcustomSkilllist(), this);
        this.editSkillAdapter = editSkillAdapter2;
        this.recyclerView.setAdapter(editSkillAdapter2);
        this.editSkillAdapter.notifyDataSetChanged();
    }

    public void onclicklistener(int pos, ArrayList<String> arrayList2) {
    }

    public void ontextchange(int pos, ArrayList<String> arrayList2, String term) {
        ((Apis) new Retrofit.Builder().baseUrl("http://devworktools.fr/ajax/").addConverterFactory(GsonConverterFactory.create()).build().create(Apis.class)).getskills(App.getAppPreference().getString("LoginId"), term).enqueue(new Callback<SkillModel>() {
            public void onResponse(Call<SkillModel> call, Response<SkillModel> response) {
                if (response.isSuccessful()) {
                    EditSkills.dismissLoadingDialog();
                } else {
                    EditSkills.dismissLoadingDialog();
                }
            }

            public void onFailure(Call<SkillModel> call, Throwable t) {
                EditSkills.dismissLoadingDialog();
                Toast.makeText(EditSkills.this, t.getMessage().toString(), 0).show();
            }
        });
    }

    public void showLoadingDialog() {
        if (progress == null) {
            ProgressDialog progressDialog = new ProgressDialog(this);
            progress = progressDialog;
            progressDialog.setTitle(getString(R.string.loading_title));
            progress.setMessage(getString(R.string.loading_message));
            progress.setCancelable(false);
        }
        progress.show();
    }

    public static void dismissLoadingDialog() {
        ProgressDialog progressDialog = progress;
        if (progressDialog != null && progressDialog.isShowing()) {
            progress.dismiss();
        }
    }

    public void start() {
    }

    public void dismiss() {
    }
}
