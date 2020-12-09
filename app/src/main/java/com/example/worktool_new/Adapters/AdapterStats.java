package com.example.worktool_new.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.example.worktool_new.Models.StatsModel;
import com.example.worktool_new.R;
import java.util.ArrayList;

public class AdapterStats extends RecyclerView.Adapter<AdapterStats.ViewHolder> {
    private Context context;
    private ArrayList<StatsModel.Datum> datamodelArraylist;

    public AdapterStats(Context context2, ArrayList<StatsModel.Datum> datamodelArraylist2) {
        this.context = context2;
        this.datamodelArraylist = datamodelArraylist2;
    }

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(this.context).inflate(R.layout.item_stats, parent, false));
    }

    public void onBindViewHolder(ViewHolder holder, int position) {
        TextView textView = holder.tvStatsTitleName;
        textView.setText(this.datamodelArraylist.get(position).getPrenom() + " " + this.datamodelArraylist.get(position).getNom());
        holder.tvStatsDate.setText(this.datamodelArraylist.get(position).getDate());
        holder.tvStatsEmail.setText(this.datamodelArraylist.get(position).getEmail());
        holder.tvStatsConnection.setText(this.datamodelArraylist.get(position).getConnection());
        holder.tvStatsPostuler.setText(this.datamodelArraylist.get(position).getPostuler());
        holder.tvStatsSaviour.setText(this.datamodelArraylist.get(position).getSavoir());
        holder.tvStatsMatch.setText(this.datamodelArraylist.get(position).getMatch());
        holder.tvStatsCER.setText(this.datamodelArraylist.get(position).getContract());
    }

    public int getItemCount() {
        ArrayList<StatsModel.Datum> arrayList = this.datamodelArraylist;
        if (arrayList == null) {
            return 0;
        }
        return arrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvStatsCER;
        TextView tvStatsConnection;
        TextView tvStatsDate;
        TextView tvStatsEmail;
        TextView tvStatsMatch;
        TextView tvStatsPostuler;
        TextView tvStatsSaviour;
        TextView tvStatsTitleName;

        public ViewHolder(View itemView) {
            super(itemView);
            this.tvStatsTitleName = (TextView) itemView.findViewById(R.id.tvStatsTitleName);
            this.tvStatsDate = (TextView) itemView.findViewById(R.id.tvStatsDate);
            this.tvStatsEmail = (TextView) itemView.findViewById(R.id.tvStatsEmail);
            this.tvStatsConnection = (TextView) itemView.findViewById(R.id.tvStatsConnection);
            this.tvStatsPostuler = (TextView) itemView.findViewById(R.id.tvStatsPostuler);
            this.tvStatsSaviour = (TextView) itemView.findViewById(R.id.tvStatsSaviour);
            this.tvStatsMatch = (TextView) itemView.findViewById(R.id.tvStatsMatch);
            this.tvStatsCER = (TextView) itemView.findViewById(R.id.tvStatsCER);
        }
    }
}
