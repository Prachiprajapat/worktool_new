package com.example.worktool_new.Adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.worktool_new.Models.ParticipationListModel;
import com.example.worktool_new.R;
import com.example.worktool_new.Util.SharedPreference.AppConstants;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

import java.util.ArrayList;

public class Adapter_Participants extends RecyclerView.Adapter<Adapter_Participants.ViewHolder> {
    private Context context;
    private ArrayList<ParticipationListModel.Datum> datamodelArraylist;

    public Adapter_Participants(Context context2, ArrayList<ParticipationListModel.Datum> datamodelArraylist2) {
        this.context = context2;
        this.datamodelArraylist = datamodelArraylist2;
    }

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(this.context).inflate(R.layout.item_participants, parent, false));
    }

    public void onBindViewHolder(ViewHolder holder, int position) {
        Log.i("leng","length");
        if (this.datamodelArraylist.get(position).getImage() != null) {
            Picasso.get().load(AppConstants.IMAGEURL + this.datamodelArraylist.get(position).getImage()).placeholder((int) R.drawable.placeholder).error((int) R.drawable.default_participant).into((ImageView) holder.civParticipant);
        }
        holder.tvParticipantName.setText(this.datamodelArraylist.get(position).getName());
        Log.i("length",this.datamodelArraylist.get(position).getName());
        holder.civParticipant.setBackgroundResource(R.drawable.placeholder);
    }

    public int getItemCount() {
        ArrayList<ParticipationListModel.Datum> arrayList = this.datamodelArraylist;
        if (arrayList == null) {
            return 0;
        }
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CircleImageView civParticipant;
        TextView tvParticipantName;

        public ViewHolder(View itemView) {
            super(itemView);
            this.tvParticipantName = (TextView) itemView.findViewById(R.id.tvParticipantName);
            this.civParticipant = (CircleImageView) itemView.findViewById(R.id.civParticipant);
        }
    }
}
