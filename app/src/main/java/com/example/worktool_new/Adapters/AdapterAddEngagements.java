package com.example.worktool_new.Adapters;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.worktool_new.Models.AddEngagementModel;
import com.example.worktool_new.R;

import java.util.ArrayList;

public class AdapterAddEngagements extends RecyclerView.Adapter<AdapterAddEngagements.Viewholder> {
    private Context context;
    public ArrayList<AddEngagementModel> dataModelArrayList;

    public AdapterAddEngagements(Context context, ArrayList<AddEngagementModel> dataModelArrayList) {
        this.context = context;
        this.dataModelArrayList = dataModelArrayList;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new AdapterAddEngagements.Viewholder(LayoutInflater.from(this.context).inflate(R.layout.item_addengagement, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
        holder.bet.setText(dataModelArrayList.get(position).getBeneficiary());
        holder.ret.setText(dataModelArrayList.get(position).getReferant());
        holder.db.setText(dataModelArrayList.get(position).getbDate());
        holder.rd.setText(dataModelArrayList.get(position).getrDate());
    }
    @Override
    public int getItemCount() {
        return dataModelArrayList.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {
        public EditText bet,ret,db,rd;

        public Viewholder(View itemView) {
            super(itemView);
            bet = itemView.findViewById(R.id.beneficiaryEngagement);
            ret = itemView.findViewById(R.id.referantEngagement);
            db = itemView.findViewById(R.id.dateOfBeneficiary);
            rd = itemView.findViewById(R.id.dateOfReferent);
        }

    }
}
