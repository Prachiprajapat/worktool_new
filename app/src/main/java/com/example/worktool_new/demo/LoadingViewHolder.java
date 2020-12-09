package com.example.worktool_new.demo;

import android.view.View;
import android.widget.ProgressBar;
import androidx.recyclerview.widget.RecyclerView;
import com.example.worktool_new.R;

/* compiled from: MyAdapter */
class LoadingViewHolder extends RecyclerView.ViewHolder {
    public ProgressBar progressBar;

    public LoadingViewHolder(View itemView) {
        super(itemView);
        this.progressBar = (ProgressBar) itemView.findViewById(R.id.progressbar);
    }
}
