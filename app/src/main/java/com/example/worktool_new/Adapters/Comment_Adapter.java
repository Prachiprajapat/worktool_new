package com.example.worktool_new.Adapters;

import android.app.Dialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.core.text.HtmlCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.example.worktool_new.Models.CommentDeleteModel;
import com.example.worktool_new.Models.WallCommentModel;
import com.example.worktool_new.R;
import com.example.worktool_new.Retrofit.Apis;
import com.example.worktool_new.Util.Common;
import com.example.worktool_new.Util.SharedPreference.App;
import com.example.worktool_new.Util.SharedPreference.AppConstants;
import com.example.worktool_new.Views.Activities.CommentActivity;
import com.squareup.picasso.Picasso;
import de.hdodenhof.circleimageview.CircleImageView;
import java.util.ArrayList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Comment_Adapter extends RecyclerView.Adapter<Comment_Adapter.PopularPostViewHolder> {
    /* access modifiers changed from: private */
    public CommentActivity commentActivity;
    /* access modifiers changed from: private */
    public ArrayList<WallCommentModel.Datum> dataModelArrayList;

    public Comment_Adapter(CommentActivity commentActivity2, ArrayList<WallCommentModel.Datum> dataModelArrayList2) {
        this.commentActivity = commentActivity2;
        this.dataModelArrayList = dataModelArrayList2;
    }

    public PopularPostViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new PopularPostViewHolder(LayoutInflater.from(this.commentActivity).inflate(R.layout.item_commentlist, parent, false));
    }

    public void onBindViewHolder(PopularPostViewHolder holder, int position) {
        if (!(this.dataModelArrayList.get(position).getPrenom() == null || this.dataModelArrayList.get(position).getNom() == null)) {
            TextView textView = holder.name;
            textView.setText(this.dataModelArrayList.get(position).getPrenom() + " " + this.dataModelArrayList.get(position).getNom());
        }
        if (this.dataModelArrayList.get(position).getTexte() != null && !this.dataModelArrayList.get(position).getTexte().isEmpty()) {
            holder.comment.setText(HtmlCompat.fromHtml(this.dataModelArrayList.get(position).getTexte(), 63));
        }
        if (this.dataModelArrayList.get(position).getImage().isEmpty()) {
            holder.image_url.setImageResource(R.drawable.user);
        } else {
            Picasso picasso = Picasso.get();
            picasso.load(AppConstants.IMAGEURL + this.dataModelArrayList.get(position).getImage()).placeholder((int) R.drawable.user).error((int) R.drawable.user).into((ImageView) holder.image_url);
        }
        if (this.dataModelArrayList.get(position).getDateheure() != null && !this.dataModelArrayList.get(position).getDateheure().isEmpty()) {
            holder.commentDate.setText(this.dataModelArrayList.get(position).getDateheure());
        }
    }

    public int getItemCount() {
        ArrayList<WallCommentModel.Datum> arrayList = this.dataModelArrayList;
        if (arrayList == null) {
            return 0;
        }
        return arrayList.size();
    }

    class PopularPostViewHolder extends RecyclerView.ViewHolder {
        TextView comment;
        TextView commentDate;
        CircleImageView image_url;
        TextView name;

        PopularPostViewHolder(View itemView) {
            super(itemView);
            this.comment = (TextView) itemView.findViewById(R.id.comment);
            this.name = (TextView) itemView.findViewById(R.id.name);
            this.image_url = (CircleImageView) itemView.findViewById(R.id.image_url);
            this.commentDate = (TextView) itemView.findViewById(R.id.commentDate);
            itemView.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    if (((WallCommentModel.Datum) Comment_Adapter.this.dataModelArrayList.get(PopularPostViewHolder.this.getAdapterPosition())).getIdCompte().equals(App.getAppPreference().getString("LoginId"))) {
                        final Dialog dialog = new Dialog(Comment_Adapter.this.commentActivity);
                        dialog.requestWindowFeature(1);
                        dialog.setContentView(R.layout.customdialog);
                        dialog.setCancelable(false);
                        dialog.show();
                        ((Button) dialog.findViewById(R.id.btnCommentDialog)).setOnClickListener(new View.OnClickListener() {
                            public void onClick(View v) {
                                dialog.dismiss();
                            }
                        });
                        ((TextView) dialog.findViewById(R.id.tvCommentDelete)).setOnClickListener(new View.OnClickListener() {
                            public void onClick(View v) {
                                dialog.dismiss();
                                ((Apis) new Retrofit.Builder().baseUrl("http://devworktools.fr/contenu/conseiller/").addConverterFactory(GsonConverterFactory.create()).build().create(Apis.class)).deleteComment(Integer.parseInt(((WallCommentModel.Datum) Comment_Adapter.this.dataModelArrayList.get(PopularPostViewHolder.this.getAdapterPosition())).getId())).enqueue(new Callback<CommentDeleteModel>() {
                                    public void onResponse(Call<CommentDeleteModel> call, Response<CommentDeleteModel> response) {
                                        if (!Common.InternetCheck(Comment_Adapter.this.commentActivity)) {
                                            Common.showToast(Comment_Adapter.this.commentActivity, "Internet Not Available,Please check Your Internet Connection");
                                        } else if (response.body() != null) {
                                            Common.showToast(Comment_Adapter.this.commentActivity, "Comment Deleted Successfully");
                                            dialog.dismiss();
                                            Comment_Adapter.this.dataModelArrayList.remove(PopularPostViewHolder.this.getAdapterPosition());
                                            Comment_Adapter.this.notifyItemRemoved(PopularPostViewHolder.this.getAdapterPosition());
                                            Comment_Adapter.this.notifyItemRangeChanged(PopularPostViewHolder.this.getAdapterPosition(), Comment_Adapter.this.dataModelArrayList.size());
                                        } else {
                                            Common.showToast(Comment_Adapter.this.commentActivity, "Data not Found");
                                        }
                                    }

                                    public void onFailure(Call<CommentDeleteModel> call, Throwable t) {
                                        Toast.makeText(Comment_Adapter.this.commentActivity, t.getLocalizedMessage(), 1).show();
                                    }
                                });
                            }
                        });
                    }
                }
            });
        }
    }
}
