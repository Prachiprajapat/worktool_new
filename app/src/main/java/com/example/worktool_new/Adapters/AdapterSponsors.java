package com.example.worktool_new.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.example.worktool_new.R;

public class AdapterSponsors extends RecyclerView.Adapter<AdapterSponsors.ViewHolder> {
    private Context context;

    public AdapterSponsors(Context context2) {
        this.context = context2;
    }

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(this.context).inflate(R.layout.item_sponsors, parent, false));
    }

    public void onBindViewHolder(ViewHolder holder, int position) {
    }

    public int getItemCount() {
        return 10;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
}
