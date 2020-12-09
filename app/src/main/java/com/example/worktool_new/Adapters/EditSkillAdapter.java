package com.example.worktool_new.Adapters;

import android.app.ProgressDialog;
import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.exifinterface.media.ExifInterface;
import androidx.recyclerview.widget.RecyclerView;
import com.example.worktool_new.Models.JsonModel;
import com.example.worktool_new.Models.getskills.SkillBodyItem;
import com.example.worktool_new.Models.getskills.SkillModel;
import com.example.worktool_new.R;
import com.example.worktool_new.Retrofit.Apis;
import com.example.worktool_new.Util.SharedPreference.App;
import com.google.gson.Gson;
import java.io.PrintStream;
import java.util.ArrayList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class EditSkillAdapter extends RecyclerView.Adapter<EditSkillAdapter.Viewholder> {
    /* access modifiers changed from: private */
    public Context context;
    private CustomSkillAdapter customSkillAdapter;
    /* access modifiers changed from: private */
    public ArrayList<JsonModel> customSkillModelArrayList;
    /* access modifiers changed from: private */
    public ArrayList<String> datamodelArraylist;
    /* access modifiers changed from: private */
    public Boolean isfirst = true;
    onaddClick onaddClick;
    private ProgressDialog progress;
    /* access modifiers changed from: private */
    public ArrayList<SkillBodyItem> skillModelArrayList = new ArrayList<>();
    private startDialog startDialog;

    public interface onaddClick {
        void onclicklistener(int i, ArrayList<String> arrayList);

        void ontextchange(int i, ArrayList<String> arrayList, String str);
    }

    public interface startDialog {
        void dismiss();

        void start();
    }

    public EditSkillAdapter(Context context2, ArrayList<String> datamodelArraylist2, onaddClick onaddClick2, ArrayList<JsonModel> customSkillModelArrayList2, startDialog startDialog2) {
        this.context = context2;
        this.onaddClick = onaddClick2;
        this.datamodelArraylist = datamodelArraylist2;
        this.customSkillModelArrayList = customSkillModelArrayList2;
    }

    public Viewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new Viewholder(LayoutInflater.from(this.context).inflate(R.layout.edit_skill_recycler_item, parent, false), this.onaddClick);
    }

    public void onBindViewHolder(final Viewholder holder, final int position) {
        holder.ll_add.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                EditSkillAdapter.this.onaddClick.onclicklistener(position, EditSkillAdapter.this.datamodelArraylist);
            }
        });
        if (App.getAppPreference().getString("random").equals("first")) {
            Toast.makeText(this.context, "first", 0).show();
        } else {
            Toast.makeText(this.context, "second", 0).show();
            if (this.customSkillModelArrayList.size() > 0) {
                for (int i = 0; i < this.customSkillModelArrayList.size(); i++) {
                    holder.search.setText(this.customSkillModelArrayList.get(i).getCompetence());
                }
            }
        }
        holder.search.addTextChangedListener(new TextWatcher() {
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() >= 2 && EditSkillAdapter.this.isfirst.booleanValue()) {
                    EditSkillAdapter editSkillAdapter = EditSkillAdapter.this;
                    editSkillAdapter.getskilllist(position, editSkillAdapter.datamodelArraylist, String.valueOf(s));
                    Boolean unused = EditSkillAdapter.this.isfirst = false;
                }
            }

            public void afterTextChanged(Editable s) {
            }
        });
        if (this.skillModelArrayList.size() > 0) {
            this.customSkillAdapter = new CustomSkillAdapter(this.context, this.skillModelArrayList);
            holder.skills.setAdapter(this.customSkillAdapter);
            holder.skills.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    SkillBodyItem selectedItem = (SkillBodyItem) parent.getSelectedItem();
                    PrintStream printStream = System.out;
                    printStream.println("sldkls" + selectedItem.getLabel());
                    holder.search.setText(selectedItem.getLabel());
                    JsonModel customSkillModel = new JsonModel();
                    customSkillModel.setCompetence_id(selectedItem.getId());
                    customSkillModel.setCompetence(selectedItem.getLabel());
                    EditSkillAdapter.this.customSkillModelArrayList.add(customSkillModel);
                    Gson gson = new Gson();
                    App.getAppPreference().saveCustomSkilllist(EditSkillAdapter.this.customSkillModelArrayList);
                    PrintStream printStream2 = System.out;
                    printStream2.println("dkjhsd" + gson.toJson((Object) App.getAppPreference().getArrayList()));
                }

                public void onNothingSelected(AdapterView<?> adapterView) {
                }
            });
        }
    }

    public void updateAdapter() {
        this.datamodelArraylist.add(ExifInterface.GPS_MEASUREMENT_2D);
        notifyItemInserted(this.datamodelArraylist.size() + 1);
    }

    public int getItemCount() {
        return this.datamodelArraylist.size();
    }

    public void updateSkillList(ArrayList<String> memberlist) {
        this.datamodelArraylist = memberlist;
    }

    public class Viewholder extends RecyclerView.ViewHolder implements View.OnClickListener {
        LinearLayout ll_add;
        onaddClick onaddClick;
        EditText search;
        Spinner skills;

        public Viewholder(View itemView, onaddClick onaddClick2) {
            super(itemView);
            this.onaddClick = onaddClick2;
            itemView.setOnClickListener(this);
            this.ll_add = (LinearLayout) itemView.findViewById(R.id.ll_add);
            this.search = (EditText) itemView.findViewById(R.id.ed_searchskills);
            this.skills = (Spinner) itemView.findViewById(R.id.sp_skills);
        }

        public void onClick(View v) {
        }
    }

    /* access modifiers changed from: private */
    public void getskilllist(int pos, ArrayList<String> arrayList, String term) {
        ((Apis) new Retrofit.Builder().baseUrl("http://devworktools.fr/").addConverterFactory(GsonConverterFactory.create()).build().create(Apis.class)).getskills(App.getAppPreference().getString("LoginId"), term).enqueue(new Callback<SkillModel>() {
            public void onResponse(Call<SkillModel> call, Response<SkillModel> response) {
                if (response.isSuccessful()) {
                    ArrayList unused = EditSkillAdapter.this.skillModelArrayList = new ArrayList();
                    ArrayList unused2 = EditSkillAdapter.this.skillModelArrayList = response.body().getData();
                    Gson gson = new Gson();
                    PrintStream printStream = System.out;
                    printStream.println("adjlsaka" + gson.toJson((Object) EditSkillAdapter.this.skillModelArrayList));
                    EditSkillAdapter.this.notifyDataSetChanged();
                }
            }

            public void onFailure(Call<SkillModel> call, Throwable t) {
                Toast.makeText(EditSkillAdapter.this.context, t.getMessage().toString(), 0).show();
            }
        });
    }
}
