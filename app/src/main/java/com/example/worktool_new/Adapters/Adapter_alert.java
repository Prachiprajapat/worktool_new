package com.example.worktool_new.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.example.worktool_new.Models.MyAlertsModel;
import com.example.worktool_new.R;
import java.util.ArrayList;

public class Adapter_alert extends RecyclerView.Adapter<Adapter_alert.ViewHolder> {
    private Context context;
    private ArrayList<MyAlertsModel.Datum> datamodelArraylist;

    public Adapter_alert(Context context2, ArrayList<MyAlertsModel.Datum> datamodelArraylist2) {
        this.context = context2;
        this.datamodelArraylist = datamodelArraylist2;
    }

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(this.context).inflate(R.layout.item_alert, parent, false));
    }

    public void onBindViewHolder(ViewHolder holder, int position) {
        if (this.datamodelArraylist.get(position).getExp() != null || !this.datamodelArraylist.isEmpty()) {
            holder.tvAlertSenderName.setText(this.datamodelArraylist.get(position).getExp());
        }
        if (this.datamodelArraylist.get(position).getSujet() != null) {
            holder.tvAlertTitle.setText(this.datamodelArraylist.get(position).getSujet());
        }
    }

    public int getItemCount() {
        ArrayList<MyAlertsModel.Datum> arrayList = this.datamodelArraylist;
        if (arrayList == null) {
            return 0;
        }
        return arrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvAlertSenderName;
        TextView tvAlertTitle;

        public ViewHolder(View itemView) {
            super(itemView);
            this.tvAlertSenderName = (TextView) itemView.findViewById(R.id.tvAlertSenderName);
            this.tvAlertTitle = (TextView) itemView.findViewById(R.id.tvAlertTitle);
        }
    }
}
