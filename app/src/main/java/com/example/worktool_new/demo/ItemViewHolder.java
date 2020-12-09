package com.example.worktool_new.demo;

import android.view.View;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.example.worktool_new.R;

/* compiled from: MyAdapter */
class ItemViewHolder extends RecyclerView.ViewHolder {
    public TextView length;
    public TextView name;

    public ItemViewHolder(View itemView) {
        super(itemView);
        this.name = (TextView) itemView.findViewById(R.id.textname);
        this.length = (TextView) itemView.findViewById(R.id.textlength);
    }
}
