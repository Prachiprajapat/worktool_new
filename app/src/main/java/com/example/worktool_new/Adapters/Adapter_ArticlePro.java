package com.example.worktool_new.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.example.worktool_new.Models.ArticleProModel;
import com.example.worktool_new.R;
import com.example.worktool_new.Util.SharedPreference.AppConstants;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;

public class Adapter_ArticlePro extends RecyclerView.Adapter<Adapter_ArticlePro.ViewHolder> {
    private Context context;
    private ArrayList<ArticleProModel.Datum> datamodelArraylist;

    public Adapter_ArticlePro(Context context2, ArrayList<ArticleProModel.Datum> datamodelArraylist2) {
        this.context = context2;
        this.datamodelArraylist = datamodelArraylist2;
    }

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(this.context).inflate(R.layout.item_articlepro, parent, false));
    }

    public void onBindViewHolder(ViewHolder holder, int position) {
        TextView textView = holder.tvArticleAuthor;
        textView.setText("Author: " + this.datamodelArraylist.get(position).getPrenom() + " " + this.datamodelArraylist.get(position).getNom());
        holder.tvArticleTitle.setText(this.datamodelArraylist.get(position).getTitre());
        TextView textView2 = holder.tvArticleDate;
        textView2.setText("Date: " + this.datamodelArraylist.get(position).getDate());
        if (this.datamodelArraylist.get(position).getFile() != null) {
            Picasso.get().load(AppConstants.IMAGEURL + this.datamodelArraylist.get(position).getFile()).placeholder((int) R.drawable.placeholder).error((int) R.drawable.placeholder).into(holder.llArticleBackground);
            return;
        }
        holder.llArticleBackground.setBackgroundResource(R.drawable.placeholder);
    }

    public int getItemCount() {
        ArrayList<ArticleProModel.Datum> arrayList = this.datamodelArraylist;
        if (arrayList == null) {
            return 0;
        }
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView llArticleBackground;
        TextView tvArticleAuthor;
        TextView tvArticleDate;
        TextView tvArticleTitle;

        public ViewHolder(View itemView) {
            super(itemView);
            this.tvArticleTitle = (TextView) itemView.findViewById(R.id.tvArticleTitle);
            this.tvArticleAuthor = (TextView) itemView.findViewById(R.id.tvArticleAuthor);
            this.tvArticleDate = (TextView) itemView.findViewById(R.id.tvArticleDate);
            this.llArticleBackground = (ImageView) itemView.findViewById(R.id.llArticleBackground);
        }
    }
}
