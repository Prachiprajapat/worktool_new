package com.example.worktool_new.demo;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.worktool_new.R;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final int VIEW_TYPE_ITEM = 0;
    private final int VIEW_TYPE_LOADING = 1;
    Activity activity;
    boolean isloading;
    List<Item> items;
    int lastvisibleitem;
    Loadmore loadmore;
    int totalitemcount;
    int visiblethreshold = 5;

    public MyAdapter(RecyclerView recyclerView, Activity activity2, List<Item> items2) {
        this.activity = activity2;
        this.items = items2;
        final LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                MyAdapter.this.totalitemcount = linearLayoutManager.getItemCount();
                MyAdapter.this.lastvisibleitem = linearLayoutManager.findLastVisibleItemPosition();
                if (!MyAdapter.this.isloading && MyAdapter.this.totalitemcount <= MyAdapter.this.lastvisibleitem + MyAdapter.this.visiblethreshold) {
                    if (MyAdapter.this.loadmore != null) {
                        MyAdapter.this.loadmore.onloadmore();
                    }
                    MyAdapter.this.isloading = true;
                }
            }
        });
    }

    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == 0) {
            return new ItemViewHolder(LayoutInflater.from(this.activity).inflate(R.layout.item_layout, parent, false));
        }
        if (viewType == 1) {
            return new LoadingViewHolder(LayoutInflater.from(this.activity).inflate(R.layout.item_loading, parent, false));
        }
        return null;
    }

    public int getItemViewType(int position) {
        return this.items.get(position) == null ? 1 : 0;
    }

    public void setLoadmore(Loadmore loadmore2) {
        this.loadmore = loadmore2;
    }

    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ItemViewHolder) {
            Item item = this.items.get(position);
            ItemViewHolder viewHolder = (ItemViewHolder) holder;
            viewHolder.name.setText(this.items.get(position).getName());
            viewHolder.length.setText(String.valueOf(this.items.get(position).getLength()));
        } else if (holder instanceof LoadingViewHolder) {
            ((LoadingViewHolder) holder).progressBar.setIndeterminate(true);
        }
    }

    public int getItemCount() {
        return this.items.size();
    }

    public void setLoaded() {
        this.isloading = false;
    }
}
