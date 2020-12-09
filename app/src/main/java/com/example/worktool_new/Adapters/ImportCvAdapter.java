package com.example.worktool_new.Adapters;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import androidx.recyclerview.widget.RecyclerView;
import com.example.worktool_new.Models.JsonModel;
import com.example.worktool_new.Models.getskills.CustomSkillModel;
import com.example.worktool_new.Models.getskills.SkillBodyItem;
import com.example.worktool_new.R;
import com.example.worktool_new.Util.SharedPreference.App;
import com.google.gson.Gson;
import java.io.PrintStream;
import java.util.ArrayList;
import okhttp3.internal.cache.DiskLruCache;

public class ImportCvAdapter extends RecyclerView.Adapter<ImportCvAdapter.Viewholder> {
    /* access modifiers changed from: private */
    public int adapterpoistion;
    /* access modifiers changed from: private */
    public ArrayList<JsonModel> competence_data = new ArrayList<>();
    private Context context;
    private CustomSkillAdapter customSkillAdapter;
    /* access modifiers changed from: private */
    public ArrayList<CustomSkillModel> datamodelArraylist;
    /* access modifiers changed from: private */
    public Boolean isActive = true;
    /* access modifiers changed from: private */
    public onClickListener onClickListener;
    private ArrayList<SkillBodyItem> skillModelArrayList;

    public interface onClickListener {
        void ontextChanged(int i, ArrayList<CustomSkillModel> arrayList, String str);
    }

    public ImportCvAdapter(Context context2, ArrayList<CustomSkillModel> datamodelArraylist2, onClickListener onClickListener2) {
        this.datamodelArraylist = datamodelArraylist2;
        this.context = context2;
        this.onClickListener = onClickListener2;
    }

    public Viewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new Viewholder(LayoutInflater.from(this.context).inflate(R.layout.edit_skill_recycler_item, parent, false), this.onClickListener);
    }

    public void onBindViewHolder(final Viewholder holder, final int position) {
        PrintStream printStream = System.out;
        printStream.println("snjkankjaska 0" + new Gson().toJson((Object) this.datamodelArraylist));
        this.adapterpoistion = position;
        PrintStream printStream2 = System.out;
        printStream2.println("prefence value " + App.getAppPreference().getString("value"));
        if (App.getAppPreference().getString("value").equals("no")) {
            this.isActive = false;
        } else {
            this.isActive = true;
        }
        if (this.datamodelArraylist.get(position).getSkillBodyItemArrayList() != null) {
            this.customSkillAdapter = new CustomSkillAdapter(this.context, this.datamodelArraylist.get(position).getSkillBodyItemArrayList());
            holder.skills.setAdapter(this.customSkillAdapter);
            holder.skills.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    SkillBodyItem selectedItem = (SkillBodyItem) parent.getSelectedItem();
                    holder.ed_searchskills.setText(selectedItem.getLabel());
                    if (((CustomSkillModel) ImportCvAdapter.this.datamodelArraylist.get(ImportCvAdapter.this.adapterpoistion)).getSkillBodyItemArrayList().size() > 0 && !((CustomSkillModel) ImportCvAdapter.this.datamodelArraylist.get(ImportCvAdapter.this.adapterpoistion)).getSkillBodyItemArrayList().get(position).getId().equals("0")) {
                        final JsonModel jsonModel = new JsonModel();
                        jsonModel.setCompetence_id(selectedItem.getId());
                        jsonModel.setCompetence(selectedItem.getLabel());
                        jsonModel.setCompetence_type(selectedItem.getType());
                        holder.rvRatingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
                            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                                jsonModel.setLevel(String.valueOf(rating));
                            }
                        });
                        if (App.getAppPreference().getString("timing").equals("first")) {
                            ImportCvAdapter.this.competence_data.add(jsonModel);
                            App.getAppPreference().saveCustomSkilllist(ImportCvAdapter.this.competence_data);
                        } else {
                            ArrayList unused = ImportCvAdapter.this.competence_data = App.getAppPreference().getcustomSkilllist();
                            ImportCvAdapter.this.competence_data.add(jsonModel);
                            App.getAppPreference().saveCustomSkilllist(ImportCvAdapter.this.competence_data);
                        }
                        PrintStream printStream = System.out;
                        printStream.println("competence_data  " + new Gson().toJson((Object) ImportCvAdapter.this.competence_data));
                    }
                }

                public void onNothingSelected(AdapterView<?> adapterView) {
                }
            });
        }
        holder.ivDelete.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            }
        });
        holder.ed_searchskills.addTextChangedListener(new TextWatcher() {
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            public void afterTextChanged(Editable s) {
                if (s.length() >= 2 && !((CustomSkillModel) ImportCvAdapter.this.datamodelArraylist.get(position)).getIsadded().booleanValue()) {
                    System.out.println("api called");
                    ImportCvAdapter.this.onClickListener.ontextChanged(position, ImportCvAdapter.this.datamodelArraylist, s.toString());
                    Boolean unused = ImportCvAdapter.this.isActive = false;
                    ((CustomSkillModel) ImportCvAdapter.this.datamodelArraylist.get(position)).setIsadded(false);
                }
            }
        });
    }

    public void updateList() {
        System.out.println("list updated");
        if (App.getAppPreference().getString("timing").equals("first")) {
            App.getAppPreference().saveCustomSkilllist(this.competence_data);
        } else {
            this.competence_data = App.getAppPreference().getcustomSkilllist();
        }
        if (this.competence_data.size() != this.datamodelArraylist.size()) {
            Toast.makeText(this.context, "Please select a value", 0).show();
            return;
        }
        this.skillModelArrayList = new ArrayList<>();
        this.datamodelArraylist.add(new CustomSkillModel(DiskLruCache.VERSION_1, this.skillModelArrayList, false));
        App.getAppPreference().saveString("value", "yes");
        App.getAppPreference().saveString("timing", "second");
        notifyItemInserted(this.datamodelArraylist.size() + 1);
    }

    public void setSpinner() {
    }

    public int getItemCount() {
        return this.datamodelArraylist.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder implements View.OnClickListener {
        /* access modifiers changed from: private */
        public EditText ed_searchskills;
        /* access modifiers changed from: private */
        public LinearLayout ivDelete;
        private onClickListener onClickListener;
        /* access modifiers changed from: private */
        public RatingBar rvRatingBar;
        Spinner skills;
        private TextView tvSkill;

        public Viewholder(View itemView, onClickListener onClickListener2) {
            super(itemView);
            this.onClickListener = onClickListener2;
            this.ivDelete = (LinearLayout) itemView.findViewById(R.id.llDelete);
            this.ed_searchskills = (EditText) itemView.findViewById(R.id.ed_searchskills);
            this.tvSkill = (TextView) itemView.findViewById(R.id.tv_skill);
            this.skills = (Spinner) itemView.findViewById(R.id.sp_skills);
            this.rvRatingBar = (RatingBar) itemView.findViewById(R.id.rv_ratingBar);
            itemView.setOnClickListener(this);
        }

        public void onClick(View v) {
        }
    }
}
