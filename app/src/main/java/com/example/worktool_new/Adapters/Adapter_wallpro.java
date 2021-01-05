package com.example.worktool_new.Adapters;

import android.content.Context;
import android.content.Intent;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.core.text.HtmlCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.example.worktool_new.Models.WallProModel;
import com.example.worktool_new.R;
import com.example.worktool_new.Util.PreviewActivity;
import com.example.worktool_new.Util.SharedPreference.App;
import com.example.worktool_new.Util.SharedPreference.AppConstants;
import com.example.worktool_new.Views.Activities.CommentActivity;
import com.squareup.picasso.Picasso;
import de.hdodenhof.circleimageview.CircleImageView;
import java.util.ArrayList;
import java.util.Iterator;

public class Adapter_wallpro extends RecyclerView.Adapter<Adapter_wallpro.ViewHolder> {
    /* access modifiers changed from: private */
    public Context context;
    /* access modifiers changed from: private */
    public ArrayList<WallProModel.Datum> datamodelArraylist;
    /* access modifiers changed from: private */
    public deleteApi deleteApi;
    /* access modifiers changed from: private */
    public ArrayList<WallProModel.Datum> orig;

    public interface deleteApi {
        void deletePost(ArrayList<WallProModel.Datum> arrayList, int i, int i2);
    }

    public Adapter_wallpro(Context context2, ArrayList<WallProModel.Datum> datamodelArraylist2, deleteApi deleteApi2) {
        this.context = context2;
        this.datamodelArraylist = datamodelArraylist2;
        this.deleteApi = deleteApi2;
    }

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(this.context).inflate(R.layout.item_wallpro, parent, false));
    }

    public void onBindViewHolder(ViewHolder holder, final int position) {
        if (this.datamodelArraylist.get(position).getComment() == null || this.datamodelArraylist.get(position).getComment().isEmpty()) {
            holder.rlWallRecentComment.setVisibility(8);
        } else {
            holder.rlWallRecentComment.setVisibility(0);
        }
        if (this.datamodelArraylist.get(position).getCommentimage() == null || this.datamodelArraylist.get(position).getCommentimage().isEmpty()) {
            holder.civComment.setImageResource(R.drawable.placeholder);
        } else {
            Picasso.get().load(AppConstants.IMAGEURL + this.datamodelArraylist.get(position).getCommentimage()).placeholder((int) R.drawable.placeholder).error((int) R.drawable.placeholder).into((ImageView) holder.civComment);
        }
        if (this.datamodelArraylist.get(position).getCommentprenom() != null && this.datamodelArraylist.get(position).getCommentnom() != null && !this.datamodelArraylist.get(position).getCommentprenom().isEmpty() && !this.datamodelArraylist.get(position).getCommentnom().isEmpty()) {
            holder.tvCommentName.setText(this.datamodelArraylist.get(position).getCommentprenom() + this.datamodelArraylist.get(position).getCommentnom());
        }
        if (this.datamodelArraylist.get(position).getCommentdate() != null && !this.datamodelArraylist.get(position).getCommentdate().isEmpty()) {
            holder.tvCommentDate.setText(this.datamodelArraylist.get(position).getCommentdate());
        }
        if (this.datamodelArraylist.get(position).getComment() != null && !this.datamodelArraylist.get(position).getComment().isEmpty()) {
            holder.tvComment.setText(HtmlCompat.fromHtml(this.datamodelArraylist.get(position).getComment(), 63));
        }
        holder.llRemovePost.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (((WallProModel.Datum) Adapter_wallpro.this.datamodelArraylist.get(position)).getId() != null && !((WallProModel.Datum) Adapter_wallpro.this.datamodelArraylist.get(position)).getId().isEmpty()) {
                    Adapter_wallpro.this.deleteApi.deletePost(Adapter_wallpro.this.datamodelArraylist, position, Integer.valueOf(((WallProModel.Datum) Adapter_wallpro.this.datamodelArraylist.get(position)).getId()).intValue());
                }
            }
        });
        holder.tvViewAllComments.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(Adapter_wallpro.this.context, CommentActivity.class);
                intent.putExtra("commentType", "wallpro");
                intent.putExtra("wallId", ((WallProModel.Datum) Adapter_wallpro.this.datamodelArraylist.get(position)).getId());
                intent.putExtra("wallImage", ((WallProModel.Datum) Adapter_wallpro.this.datamodelArraylist.get(position)).getImage());
                intent.putExtra("wallName", ((WallProModel.Datum) Adapter_wallpro.this.datamodelArraylist.get(position)).getCivilite() + " " + ((WallProModel.Datum) Adapter_wallpro.this.datamodelArraylist.get(position)).getPrenom() + " " + ((WallProModel.Datum) Adapter_wallpro.this.datamodelArraylist.get(position)).getNom());
                intent.putExtra("wallDateTime", ((WallProModel.Datum) Adapter_wallpro.this.datamodelArraylist.get(position)).getDateheure());
                intent.putExtra("wallTitle", "");
                intent.putExtra("wallEmail", ((WallProModel.Datum) Adapter_wallpro.this.datamodelArraylist.get(position)).getEmail());
                intent.putExtra("wallDescription", ((WallProModel.Datum) Adapter_wallpro.this.datamodelArraylist.get(position)).getTexte());
                Adapter_wallpro.this.context.startActivity(intent);
            }
        });
        holder.edit_cv4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(Adapter_wallpro.this.context, CommentActivity.class);
                intent.putExtra("commentType", "wallpro");
                intent.putExtra("wallId", ((WallProModel.Datum) Adapter_wallpro.this.datamodelArraylist.get(position)).getId());
                intent.putExtra("wallImage", ((WallProModel.Datum) Adapter_wallpro.this.datamodelArraylist.get(position)).getImage());
                intent.putExtra("wallName", ((WallProModel.Datum) Adapter_wallpro.this.datamodelArraylist.get(position)).getCivilite() + " " + ((WallProModel.Datum) Adapter_wallpro.this.datamodelArraylist.get(position)).getPrenom() + " " + ((WallProModel.Datum) Adapter_wallpro.this.datamodelArraylist.get(position)).getNom());
                intent.putExtra("wallDateTime", ((WallProModel.Datum) Adapter_wallpro.this.datamodelArraylist.get(position)).getDateheure());
                intent.putExtra("wallTitle", "");
                intent.putExtra("wallEmail", ((WallProModel.Datum) Adapter_wallpro.this.datamodelArraylist.get(position)).getEmail());
                intent.putExtra("wallDescription", ((WallProModel.Datum) Adapter_wallpro.this.datamodelArraylist.get(position)).getTexte());
                Adapter_wallpro.this.context.startActivity(intent);
            }
        });
        if (App.getAppPreference().getString("type").equals("counselor")) {
            holder.linApply.setVisibility(8);
        } else {
            holder.linApply.setVisibility(0);
        }
        if (!(this.datamodelArraylist.get(position).getNom() == null && this.datamodelArraylist.get(position).getPrenom() == null)) {
            holder.tvName.setText(this.datamodelArraylist.get(position).getCivilite() + " " + this.datamodelArraylist.get(position).getPrenom() + " " + this.datamodelArraylist.get(position).getNom());
        }
        if (!this.datamodelArraylist.get(position).getDateheure().isEmpty()) {
            holder.tvDateTime.setText(this.datamodelArraylist.get(position).getDateheure());
        }
        String ProfileImage = AppConstants.IMAGEURL + this.datamodelArraylist.get(position).getImage();
        if (this.datamodelArraylist.get(position).getImage() != null) {
            Picasso.get().load(ProfileImage).placeholder((int) R.drawable.profileplaceholder).error((int) R.drawable.profileplaceholder).into((ImageView) holder.ivWallProfilePost);
        } else {
            holder.ivWallProfilePost.setImageResource(R.drawable.profileplaceholder);
        }
        if (this.datamodelArraylist.get(position).getFiletype() == null) {
            return;
        }
        if (this.datamodelArraylist.get(position).getFiletype().equals("image")) {
            holder.llText.setVisibility(0);
            holder.llFile.setVisibility(8);
            holder.llImage.setVisibility(0);
            holder.llVideo.setVisibility(8);
            holder.llImage.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    if (((WallProModel.Datum) Adapter_wallpro.this.datamodelArraylist.get(position)).getFile() != null) {
                        Intent intent = new Intent(Adapter_wallpro.this.context, PreviewActivity.class);
                        intent.putExtra("url", AppConstants.IMAGEURL + ((WallProModel.Datum) Adapter_wallpro.this.datamodelArraylist.get(position)).getFile());
                        Adapter_wallpro.this.context.startActivity(intent);
                    }
                }
            });
            String VideoThumbNail = AppConstants.IMAGEURL + this.datamodelArraylist.get(position).getFile();
            if (this.datamodelArraylist.get(position).getFile() != null) {
                Picasso.get().load(VideoThumbNail).placeholder((int) R.drawable.profileplaceholder).error((int) R.drawable.profileplaceholder).resize(80, 80).into((ImageView) holder.civWallimage);
            } else {
                holder.civWallimage.setImageResource(R.drawable.profileplaceholder);
            }
            if (!this.datamodelArraylist.get(position).getEmail().isEmpty()) {
                holder.tvEmail.setText(this.datamodelArraylist.get(position).getEmail());
            }
            if (this.datamodelArraylist.get(position).getTexte() != null) {
                holder.tvWallDescription.setText(Html.fromHtml(String.valueOf(HtmlCompat.fromHtml(this.datamodelArraylist.get(position).getTexte(), 63))));
            }
        } else if (this.datamodelArraylist.get(position).getFiletype().equals("video")) {
            holder.llText.setVisibility(0);
            holder.llFile.setVisibility(8);
            holder.llImage.setVisibility(8);
            holder.llVideo.setVisibility(0);
            String VideoThumbNail2 = AppConstants.IMAGEURL + this.datamodelArraylist.get(position).getFile();
            if (this.datamodelArraylist.get(position).getFile() != null) {
                Picasso.get().load(VideoThumbNail2).placeholder((int) R.drawable.profileplaceholder).error((int) R.drawable.profileplaceholder).into((ImageView) holder.civWallThumbnail);
            } else {
                holder.civWallThumbnail.setImageResource(R.drawable.profileplaceholder);
            }
            if (!this.datamodelArraylist.get(position).getEmail().isEmpty()) {
                holder.tvEmail.setText(this.datamodelArraylist.get(position).getEmail());
            }
            if (this.datamodelArraylist.get(position).getTexte() != null) {
                holder.tvWallDescription.setText(Html.fromHtml(String.valueOf(HtmlCompat.fromHtml(this.datamodelArraylist.get(position).getTexte(), 63))));
            }
        } else if (this.datamodelArraylist.get(position).getFiletype().equals("document")) {
            holder.llText.setVisibility(0);
            holder.llFile.setVisibility(0);
            holder.llImage.setVisibility(8);
            holder.llVideo.setVisibility(8);
            if (this.datamodelArraylist.get(position).getFile() != null) {
                holder.tvWallFileName.setText(this.datamodelArraylist.get(position).getFile().toString());
            }
            if (!this.datamodelArraylist.get(position).getEmail().isEmpty()) {
                holder.tvEmail.setText(this.datamodelArraylist.get(position).getEmail());
            }
            if (this.datamodelArraylist.get(position).getTexte() != null) {
                holder.tvWallDescription.setText(Html.fromHtml(String.valueOf(HtmlCompat.fromHtml(this.datamodelArraylist.get(position).getTexte(), 63))));
            }
        } else {
            holder.llText.setVisibility(0);
            holder.llFile.setVisibility(8);
            holder.llImage.setVisibility(8);
            holder.llVideo.setVisibility(8);
            if (!this.datamodelArraylist.get(position).getEmail().isEmpty()) {
                holder.tvEmail.setText(this.datamodelArraylist.get(position).getEmail());
            }
            if (this.datamodelArraylist.get(position).getTexte() != null) {
                holder.tvWallDescription.setText(Html.fromHtml(String.valueOf(HtmlCompat.fromHtml(this.datamodelArraylist.get(position).getTexte(), 63))));
            }
        }
    }

    public Filter getFilter() {
        return new Filter() {
            /* access modifiers changed from: protected */
            public Filter.FilterResults performFiltering(CharSequence constraint) {
                Filter.FilterResults oReturn = new Filter.FilterResults();
                ArrayList<WallProModel.Datum> results = new ArrayList<>();
                if (Adapter_wallpro.this.orig == null) {
                    Adapter_wallpro adapter_wallpro = Adapter_wallpro.this;
                    ArrayList unused = adapter_wallpro.orig = adapter_wallpro.datamodelArraylist;
                }
                if (constraint != null) {
                    if (Adapter_wallpro.this.orig != null && Adapter_wallpro.this.orig.size() > 0) {
                        Iterator it = Adapter_wallpro.this.orig.iterator();
                        while (it.hasNext()) {
                            WallProModel.Datum g = (WallProModel.Datum) it.next();
                            if (g.getPrenom().toUpperCase().contains(constraint.toString().toUpperCase()) || g.getNom().toUpperCase().contains(constraint.toString().toUpperCase()) || g.getCivilite().toUpperCase().contains(constraint.toString().toUpperCase()) || g.getTexte().toUpperCase().contains(constraint.toString().toUpperCase()) || g.getEmail().toUpperCase().contains(constraint.toString().toUpperCase()) || g.getDateheure().toUpperCase().contains(constraint.toString().toUpperCase()) || g.getComment().toUpperCase().contains(constraint.toString().toUpperCase()) || g.getDescription().toUpperCase().contains(constraint.toString().toUpperCase())) {
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
                ArrayList unused = Adapter_wallpro.this.datamodelArraylist = (ArrayList) results.values;
                Adapter_wallpro.this.notifyDataSetChanged();
            }
        };
    }

    public int getItemCount() {
        ArrayList<WallProModel.Datum> arrayList = this.datamodelArraylist;
        if (arrayList == null) {
            return 0;
        }
        return arrayList.size();
    }

    public void removeItem(int position) {
        this.datamodelArraylist.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, this.datamodelArraylist.size());
    }

    public void updateList(ArrayList<WallProModel.Datum> wallProModelArraylist) {
        this.datamodelArraylist = wallProModelArraylist;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CircleImageView civComment;
        CircleImageView civWallThumbnail;
        CircleImageView civWallimage;
        LinearLayout edit_cv4;
        ImageView ivProfile;
        CircleImageView ivWallProfilePost;
        LinearLayout linApply;
        LinearLayout llFile;
        LinearLayout llImage;
        LinearLayout llRemovePost;
        LinearLayout llText;
        RelativeLayout llVideo;
        RelativeLayout rlWallRecentComment;
        TextView tvComment;
        TextView tvCommentDate;
        TextView tvCommentName;
        TextView tvDateTime;
        TextView tvEmail;
        TextView tvName;
        TextView tvViewAllComments;
        TextView tvWallDescription;
        TextView tvWallFileName;
        TextView tvWallTitle;

        public ViewHolder(View itemView) {
            super(itemView);
            this.linApply = (LinearLayout) itemView.findViewById(R.id.linApply);
            this.llRemovePost = (LinearLayout) itemView.findViewById(R.id.llRemovePost);
            this.rlWallRecentComment = (RelativeLayout) itemView.findViewById(R.id.rlWallRecentComment);
            this.llText = (LinearLayout) itemView.findViewById(R.id.llText);
            this.llFile = (LinearLayout) itemView.findViewById(R.id.llFile);
            this.llImage = (LinearLayout) itemView.findViewById(R.id.llImage);
            this.llVideo = (RelativeLayout) itemView.findViewById(R.id.llVideo);
            this.ivWallProfilePost = (CircleImageView) itemView.findViewById(R.id.ivWallProfilePost);
            this.edit_cv4 = (LinearLayout) itemView.findViewById(R.id.edit_cv4);
            this.tvViewAllComments = (TextView) itemView.findViewById(R.id.tvViewAllComments);
            this.tvName = (TextView) itemView.findViewById(R.id.tvName);
            this.tvDateTime = (TextView) itemView.findViewById(R.id.tvDateTime);
            this.tvEmail = (TextView) itemView.findViewById(R.id.tvEmail);
            this.tvWallTitle = (TextView) itemView.findViewById(R.id.tvWallTitle);
            this.tvWallDescription = (TextView) itemView.findViewById(R.id.tvWallDescription);
            this.tvWallFileName = (TextView) itemView.findViewById(R.id.tvWallFileName);
            this.civWallimage = (CircleImageView) itemView.findViewById(R.id.civWallimage);
            this.civWallThumbnail = (CircleImageView) itemView.findViewById(R.id.civWallThumbnail);
            this.civComment = (CircleImageView) itemView.findViewById(R.id.civComment);
            this.tvCommentName = (TextView) itemView.findViewById(R.id.tvCommentName);
            this.tvCommentDate = (TextView) itemView.findViewById(R.id.tvCommentDate);
            this.tvComment = (TextView) itemView.findViewById(R.id.tvComment);
        }
    }
}
