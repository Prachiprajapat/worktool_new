package com.example.worktool_new.Adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.worktool_new.Models.AddSkillModel;
import com.example.worktool_new.Models.JsonModel;
import com.example.worktool_new.Models.getskills.CustomSkillModel;
import com.example.worktool_new.Models.getskills.SkillBodyItem;
import com.example.worktool_new.R;

import java.util.ArrayList;

public class AddSkillAdapter extends RecyclerView.Adapter<AddSkillAdapter.Viewholder> {
    /* access modifiers changed from: private */
    public int adapterpoistion;
    /* access modifiers changed from: private */
    public ArrayList<JsonModel> competence_data = new ArrayList<>();
    private Context context;
    private CustomSkillAdapter customSkillAdapter;
    /* access modifiers changed from: private */
    public ArrayList<CustomSkillModel> datamodelArraylist;
    /* access modifiers changed from: private */
    ArrayList<AddSkillModel> skillList;
    private ArrayList<AddSkillModel> sendSkillList = new ArrayList<>();
    sendSkillData sendSkillData;
    AddSkillModel skillModel;

    public interface sendSkillData{
        void sendSkill(ArrayList<AddSkillModel> sendSkillList);
    }

    public AddSkillAdapter(Context context2, ArrayList<AddSkillModel> arrayList, sendSkillData sendSkillData) {
        this.skillList = arrayList;
        this.context = context2;
        this.sendSkillData = sendSkillData;
    }

    public Viewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new Viewholder(LayoutInflater.from(this.context).inflate(R.layout.edit_skill_recycler_item, parent, false));
    }

    public void onBindViewHolder(final Viewholder holder, final int position) {
        holder.tvSkill.setText(skillList.get(position).getSkillName());
        holder.rvRatingBar.setRating(skillList.get(position).getSkillRating());

        skillModel = new AddSkillModel(skillList.get(position).getSkillName(), skillList.get(position).getSkillRating());
        sendSkillList.add(skillModel);
        Log.i("cvAdapter", sendSkillList.toString());
        sendSkillData.sendSkill(sendSkillList);

        /*PrintStream printStream = System.out;
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
        }*/
        holder.ivDelete.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            }
        });
    }

/*
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
*/

    public int getItemCount() {
        return this.skillList.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {

        public LinearLayout ivDelete;
        public RatingBar rvRatingBar;
        private TextView tvSkill;

        public Viewholder(View itemView) {
            super(itemView);
            this.ivDelete = (LinearLayout) itemView.findViewById(R.id.llDelete);
            this.tvSkill = (TextView) itemView.findViewById(R.id.tv_skill);
            this.rvRatingBar = (RatingBar) itemView.findViewById(R.id.ratingBar);
        }

    }

}