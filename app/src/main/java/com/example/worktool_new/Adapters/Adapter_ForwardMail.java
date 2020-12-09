package com.example.worktool_new.Adapters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.worktool_new.Models.ForwardMailModel;
import com.example.worktool_new.R;
import com.example.worktool_new.Views.Activities.ForwardMailActivity;
import com.example.worktool_new.demo.Item;

import java.util.ArrayList;
import java.util.List;

public class Adapter_ForwardMail extends RecyclerView.Adapter<Adapter_ForwardMail.ForwardMailViewHolder> {

    private Context context;
    private ArrayList<ForwardMailModel.MailDatum> arrayList;
    public sendData sendData;
    private ArrayList<String> idList = new ArrayList<>();
    private final boolean[] checkedState;

    public interface sendData{
        void senddata(ArrayList<String> id);
    }

    public Adapter_ForwardMail(Context context, ArrayList<ForwardMailModel.MailDatum> arrayList,
                               sendData sendData) {
        this.context = context;
        this.arrayList = arrayList;
        this.sendData = sendData;
        checkedState = new boolean[arrayList.size()];
    }

    @NonNull
    @Override
    public ForwardMailViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ForwardMailViewHolder(LayoutInflater.from(this.context).inflate(R.layout.item_mail_forward, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final ForwardMailViewHolder holder, final int position) {
        holder.name.setText(this.arrayList.get(position).getPrenom() + " " +
                this.arrayList.get(position).getNom());
        holder.type.setText(this.arrayList.get(position).getType());
        holder.checkBox.setOnCheckedChangeListener(null);
        holder.checkBox.setChecked(false);
        if (checkedState[position]){
            holder.checkBox.setChecked(true);
        } else {
            holder.checkBox.setChecked(false);
        }
        holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (buttonView.isChecked()){
                    checkedState[position] = true;
                    idList.add(arrayList.get(position).getIdCompte());
                    holder.checkBox.setSelected(true);
                } else if (!buttonView.isChecked()){
                    checkedState[position] = false;
                    idList.remove(new String(arrayList.get(position).getIdCompte()));
                    holder.checkBox.setSelected(false);
                }
                sendData.senddata(idList);
                Log.i("checked",arrayList.get(position).getIdCompte());
            }
        });
    }
//mail->forward->checkbox array->loop
//event->pdf->download  ye krenge phle..    iska code kaha hai  ??
//delete->alert dialog->mail
    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ForwardMailViewHolder extends RecyclerView.ViewHolder{
        TextView name, type;
        CheckBox checkBox;

        public ForwardMailViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.forwardMailName);
            type = itemView.findViewById(R.id.forwardMailType);
            checkBox = itemView.findViewById(R.id.forwardMailCheckBox);
            checkBox.setClickable(true);
        }
    }
}
