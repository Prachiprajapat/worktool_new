package com.example.worktool_new.Adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.worktool_new.Models.ConseillerModel;
import com.example.worktool_new.R;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class Adapter_conseiller extends RecyclerView.Adapter<Adapter_conseiller.ConseillerViewHolder> {
    private Context context;
    private ArrayList<ConseillerModel.Datum> datamodelArraylist;


    public Adapter_conseiller(Context context, ArrayList<ConseillerModel.Datum> datamodelArraylist) {
        this.context = context;
        this.datamodelArraylist = datamodelArraylist;
    }

    @NonNull
    @Override
    public ConseillerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ConseillerViewHolder(LayoutInflater.from(this.context).inflate(R.layout.item_conseiller, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ConseillerViewHolder holder, int position) {
        Log.i("idid", datamodelArraylist.get(position).getName());
        holder.name.setText(this.datamodelArraylist.get(position).getName());
        holder.address.setText(this.datamodelArraylist.get(position).getAddress());
        holder.phone.setText(this.datamodelArraylist.get(position).getPhone_no());
        holder.email.setText(this.datamodelArraylist.get(position).getEmail());
        Glide.with(context).load(this.datamodelArraylist.get(position).getImage())
                .placeholder(R.drawable.profileplaceholder).into(holder.image);
    }

    @Override
    public int getItemCount() {
        return datamodelArraylist.size();
    }

    public class ConseillerViewHolder extends RecyclerView.ViewHolder {
        CircleImageView image;
        TextView name, email, phone, address;

        public ConseillerViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.conseillerImage);
            name = itemView.findViewById(R.id.conseillerName);
            email = itemView.findViewById(R.id.conseillerEmail);
            phone = itemView.findViewById(R.id.conseillerNumber);
            address = itemView.findViewById(R.id.conseillerAddress);
        }
    }
}
