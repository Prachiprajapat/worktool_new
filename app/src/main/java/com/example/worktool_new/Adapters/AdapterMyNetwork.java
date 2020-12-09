package com.example.worktool_new.Adapters;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.example.worktool_new.Models.ExcludeInvitationModel;
import com.example.worktool_new.Models.MyNetworkModel;
import com.example.worktool_new.Models.WallModel;
import com.example.worktool_new.R;
import com.example.worktool_new.Retrofit.Apis;
import com.example.worktool_new.Util.Common;
import com.example.worktool_new.Util.SharedPreference.AppConstants;
import com.example.worktool_new.Views.Fragments.Wall;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AdapterMyNetwork extends RecyclerView.Adapter<AdapterMyNetwork.ViewHolder> {
    Dialog dialog;
    /* access modifiers changed from: private */
    public Context context;
    /* access modifiers changed from: private */
    public ArrayList<MyNetworkModel.Datum> datamodelArraylist;

    public AdapterMyNetwork(Context context2, ArrayList<MyNetworkModel.Datum> datamodelArraylist2) {
        this.context = context2;
        this.datamodelArraylist = datamodelArraylist2;
    }

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(this.context).inflate(R.layout.item_my_network, parent, false));
    }

    public void onBindViewHolder(ViewHolder holder, final int position) {
        String NetworkName = this.datamodelArraylist.get(position).getPrenom() + " " + this.datamodelArraylist.get(position).getNom();
        if (NetworkName != null || !NetworkName.isEmpty()) {
            holder.tv_name.setText(NetworkName);
        }
        String NetworkImage = AppConstants.IMAGEURL + this.datamodelArraylist.get(position).getImage();
        if (this.datamodelArraylist.get(position).getImage() != null) {
            Picasso.get().load(NetworkImage).placeholder((int) R.drawable.placeholder).error((int) R.drawable.placeholder).into(holder.iv_image);
        } else {
            holder.iv_image.setImageResource(R.drawable.placeholder);
        }
        holder.tvExclude.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
             deletePost(position);

            }
        });
    }
    public void deletePost(final int position) {
        Dialog dialog2 = new Dialog(context);
        this.dialog = dialog2;
        dialog2.requestWindowFeature(1);
        this.dialog.setContentView(R.layout.customdialogremove);
        this.dialog.setCancelable(false);
        this.dialog.show();
        ((Button) this.dialog.findViewById(R.id.btnCommentDialog)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        ((TextView) this.dialog.findViewById(R.id.tvCommentDeleteYes)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                deleteApi(position);

            }
        });
        ((TextView) this.dialog.findViewById(R.id.tvCommentDeleteNo)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }

    private void deleteApi(final int position) {
        ((Apis) new Retrofit.Builder().baseUrl("http://devworktools.fr/contenu/conseiller/").addConverterFactory(GsonConverterFactory.create()).build().create(Apis.class)).excludeInvitation(Integer.parseInt(((MyNetworkModel.Datum) AdapterMyNetwork.this.datamodelArraylist.get(position)).getId())).enqueue(new Callback<ExcludeInvitationModel>() {
            public void onResponse(Call<ExcludeInvitationModel> call, Response<ExcludeInvitationModel> response) {
                if (!Common.InternetCheckFragment(AdapterMyNetwork.this.context)) {
                    Common.showToastFragment(AdapterMyNetwork.this.context, "Internet Not Available,Please check Your Internet Connection");
                    dialog.dismiss();
                } else if (response.body() != null) {
                    Common.showToastFragment(AdapterMyNetwork.this.context, response.body().getMessage());
                    AdapterMyNetwork.this.datamodelArraylist.remove(position);
                    AdapterMyNetwork.this.notifyItemRemoved(position);
                    AdapterMyNetwork.this.notifyItemRangeChanged(position, AdapterMyNetwork.this.datamodelArraylist.size());
                    dialog.dismiss();
                } else {
                    Common.showToastFragment(AdapterMyNetwork.this.context, "Data not Found");
                    dialog.dismiss();
                }
            }

            public void onFailure(Call<ExcludeInvitationModel> call, Throwable t) {
                Common.showToastFragment(AdapterMyNetwork.this.context, t.getLocalizedMessage());
            }
        });
    }

    public void updateList(ArrayList<MyNetworkModel.Datum> dataModelArrayList) {
        this.datamodelArraylist = dataModelArrayList;
        notifyDataSetChanged();
    }

    public int getItemCount() {
        ArrayList<MyNetworkModel.Datum> arrayList = this.datamodelArraylist;
        if (arrayList == null) {
            return 0;
        }
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView iv_image;
        TextView tvExclude;
        TextView tv_name;

        public ViewHolder(View itemView) {
            super(itemView);
            this.tv_name = (TextView) itemView.findViewById(R.id.tv_name);
            this.iv_image = (ImageView) itemView.findViewById(R.id.iv_image);
            this.tvExclude = (TextView) itemView.findViewById(R.id.tvExclude);
        }
    }
}
