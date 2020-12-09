package com.example.worktool_new.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.example.worktool_new.Models.ToModel;
import com.example.worktool_new.R;
import java.util.ArrayList;
import java.util.Iterator;

public class AdapterUsersData extends RecyclerView.Adapter<AdapterUsersData.ViewHolder> {
    private Context context;
    /* access modifiers changed from: private */
    public ArrayList<ToModel.Datum> datamodelArraylist;
    private Boolean isSelected = false;

    public AdapterUsersData(Context context2, ArrayList<ToModel.Datum> datamodelArraylist2) {
        this.context = context2;
        this.datamodelArraylist = datamodelArraylist2;
    }

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(this.context).inflate(R.layout.item_select_user, parent, false));
    }

    public void onBindViewHolder(ViewHolder holder, final int position) {
        TextView textView = holder.tvSelectUserName;
        textView.setText(this.datamodelArraylist.get(position).getPrenom() + " " + this.datamodelArraylist.get(position).getNom());
        TextView textView2 = holder.tvSelectUserType;
        textView2.setText("(" + this.datamodelArraylist.get(position).getType() + ")");
        if (!this.isSelected.booleanValue()) {
            holder.checkb.setChecked(false);
        } else {
            holder.checkb.setChecked(true);
        }
        holder.checkb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    ((ToModel.Datum) AdapterUsersData.this.datamodelArraylist.get(position)).setSelected(true);
                } else {
                    ((ToModel.Datum) AdapterUsersData.this.datamodelArraylist.get(position)).setSelected(false);
                }
            }
        });
    }

    public ArrayList<ToModel.Datum> getSelectedItems() {
        ArrayList<ToModel.Datum> selectedItems = new ArrayList<>();
        Iterator<ToModel.Datum> it = this.datamodelArraylist.iterator();
        while (it.hasNext()) {
            ToModel.Datum item = it.next();
            if (item.isSelected()) {
                selectedItems.add(item);
            }
        }
        return selectedItems;
    }

    public int getItemCount() {
        ArrayList<ToModel.Datum> arrayList = this.datamodelArraylist;
        if (arrayList == null) {
            return 0;
        }
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CheckBox checkb;
        TextView tvSelectUserName;
        TextView tvSelectUserType;

        public ViewHolder(View itemView) {
            super(itemView);
            this.tvSelectUserName = (TextView) itemView.findViewById(R.id.tvSelectUserName);
            this.tvSelectUserType = (TextView) itemView.findViewById(R.id.tvSelectUserType);
            this.checkb = (CheckBox) itemView.findViewById(R.id.checkb);
        }
    }
}
