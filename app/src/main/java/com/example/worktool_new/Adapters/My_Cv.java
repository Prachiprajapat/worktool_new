package com.example.worktool_new.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;
import com.example.worktool_new.Models.CustomModelCv;
import com.example.worktool_new.Models.NewCvModel;
import com.example.worktool_new.R;
import com.example.worktool_new.Views.Activities.WebDemoActivity;
import java.util.ArrayList;
import java.util.Iterator;
import okhttp3.internal.cache.DiskLruCache;

public class My_Cv extends RecyclerView.Adapter<My_Cv.ViewHolder> {
    /* access modifiers changed from: private */
    public Context context;
    private ArrayList<CustomModelCv> customModelCvArrayList;
    /* access modifiers changed from: private */
    public ArrayList<NewCvModel> datamodelArraylist;
    ondowmloadClick ondowmloadClick;
    /* access modifiers changed from: private */
    public ArrayList<NewCvModel> orig;

    public interface ondowmloadClick {
        void ondownloadClickListenr(int i, ArrayList<NewCvModel> arrayList, int i2);
    }

    public My_Cv(FragmentActivity context2, ArrayList<NewCvModel> datamodelArraylist2, ondowmloadClick ondowmloadClick2) {
        this.context = context2;
        this.datamodelArraylist = datamodelArraylist2;
        this.ondowmloadClick = ondowmloadClick2;
    }

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(this.context).inflate(R.layout.item_cv, parent, false), this.ondowmloadClick);
    }

    public void onBindViewHolder(ViewHolder holder, final int position) {
        if (this.datamodelArraylist.get(position).getStatus().equals(DiskLruCache.VERSION_1)) {
            holder.tv_download11.setText("Download Cv");
        } else {
            holder.tv_download11.setText("Open cv");
            holder.uv_download11.setVisibility(8);
        }
        if (this.datamodelArraylist.get(position).getTypecv().equals("WorkTools")) {
            holder.inactive.setVisibility(0);
            holder.avaliable.setVisibility(8);
        } else {
            holder.avaliable.setVisibility(0);
            holder.inactive.setVisibility(8);
        }
        TextView textView = holder.tvCvName;
        textView.setText(this.datamodelArraylist.get(position).getPrenom() + " " + this.datamodelArraylist.get(position).getNom());
        holder.open.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(My_Cv.this.context, WebDemoActivity.class);
                intent.putExtra("docUrl", ((NewCvModel) My_Cv.this.datamodelArraylist.get(position)).getFichier());
                My_Cv.this.context.startActivity(intent);
            }
        });
        holder.download.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (((NewCvModel) My_Cv.this.datamodelArraylist.get(position)).getStatus().equals(DiskLruCache.VERSION_1)) {
                    My_Cv.this.ondowmloadClick.ondownloadClickListenr(position, My_Cv.this.datamodelArraylist, 1);
                } else {
                    My_Cv.this.ondowmloadClick.ondownloadClickListenr(position, My_Cv.this.datamodelArraylist, 2);
                }
            }
        });
    }

    public Filter getFilter() {
        return new Filter() {
            /* access modifiers changed from: protected */
            public Filter.FilterResults performFiltering(CharSequence constraint) {
                Filter.FilterResults oReturn = new Filter.FilterResults();
                ArrayList<NewCvModel> results = new ArrayList<>();
                if (My_Cv.this.orig == null) {
                    My_Cv my_Cv = My_Cv.this;
                    ArrayList unused = my_Cv.orig = my_Cv.datamodelArraylist;
                }
                if (constraint != null) {
                    if (My_Cv.this.orig != null && My_Cv.this.orig.size() > 0) {
                        Iterator it = My_Cv.this.orig.iterator();
                        while (it.hasNext()) {
                            NewCvModel g = (NewCvModel) it.next();
                            if (g.getPrenom().toUpperCase().contains(constraint.toString().toUpperCase()) || g.getNom().toUpperCase().contains(constraint.toString().toUpperCase())) {
                                results.add(g);
                            }
                        }
                    }
                    oReturn.values = results;
                }
                return oReturn;
            }

            /* access modifiers changed from: protected */
            public void publishResults(CharSequence constraint, Filter.FilterResults results) {
                ArrayList unused = My_Cv.this.datamodelArraylist = (ArrayList) results.values;
                My_Cv.this.notifyDataSetChanged();
            }
        };
    }

    public int getItemCount() {
        ArrayList<NewCvModel> arrayList = this.datamodelArraylist;
        if (arrayList == null) {
            return 0;
        }
        return arrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView avaliable;
        LinearLayout download;
        TextView inactive;
        ondowmloadClick ondowmloadClick;
        LinearLayout open;
        RelativeLayout relEditCv;
        RelativeLayout relEditSkills;
        TextView tvCvName;
        TextView tv_download11;
        ImageView uv_download11;

        public ViewHolder(View itemView, ondowmloadClick ondowmloadClick2) {
            super(itemView);
            this.open = (LinearLayout) itemView.findViewById(R.id.open);
            this.download = (LinearLayout) itemView.findViewById(R.id.download);
            this.tvCvName = (TextView) itemView.findViewById(R.id.tvCvName);
            this.relEditCv = (RelativeLayout) itemView.findViewById(R.id.relEditCv);
            this.relEditSkills = (RelativeLayout) itemView.findViewById(R.id.relEditSkills);
            this.tv_download11 = (TextView) itemView.findViewById(R.id.tv_download11);
            this.uv_download11 = (ImageView) itemView.findViewById(R.id.uv_download11);
            this.ondowmloadClick = ondowmloadClick2;
            this.avaliable = (TextView) itemView.findViewById(R.id.tv_avaliable);
            this.inactive = (TextView) itemView.findViewById(R.id.tv_inactive);
            itemView.setOnClickListener(this);
        }

        public void onClick(View v) {
        }
    }
}
