package com.example.worktool_new.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import androidx.recyclerview.widget.RecyclerView;
import com.example.worktool_new.Models.GetEngagementModel;
import com.example.worktool_new.R;
import java.util.ArrayList;

public class Adapter_Engagements extends RecyclerView.Adapter<Adapter_Engagements.ViewHolder> {
    private Context context;
    ArrayList<GetEngagementModel.Datum> dataModelArrayList;

    public Adapter_Engagements(Context context2, ArrayList<GetEngagementModel.Datum> dataModelArrayList2) {
        this.context = context2;
        this.dataModelArrayList = dataModelArrayList2;
    }

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(this.context).inflate(R.layout.item_engagements, parent, false));
    }

    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.etBeneficaireDate.setText(this.dataModelArrayList.get(position).getBenefEch());
        holder.etBeneficaire.setText(this.dataModelArrayList.get(position).getBenefEngagement());
        holder.etReferentDate.setText(this.dataModelArrayList.get(position).getRefEch());
        holder.etReferent.setText(this.dataModelArrayList.get(position).getRefEngagement());
    }

    public int getItemCount() {
        ArrayList<GetEngagementModel.Datum> arrayList = this.dataModelArrayList;
        if (arrayList == null) {
            return 0;
        }
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        EditText etBeneficaire;
        EditText etBeneficaireDate;
        EditText etReferent;
        EditText etReferentDate;

        public ViewHolder(View itemView) {
            super(itemView);
            this.etBeneficaireDate = (EditText) itemView.findViewById(R.id.etBeneficaireDate);
            this.etBeneficaire = (EditText) itemView.findViewById(R.id.etBeneficaire);
            this.etReferentDate = (EditText) itemView.findViewById(R.id.etReferentDate);
            this.etReferent = (EditText) itemView.findViewById(R.id.etReferent);
        }
    }
}
