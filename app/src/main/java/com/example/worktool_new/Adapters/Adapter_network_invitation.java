package com.example.worktool_new.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.recyclerview.widget.RecyclerView;
import com.example.worktool_new.Models.AcceptInvitationRejectionModel;
import com.example.worktool_new.Models.InvitationRecievedModel;
import com.example.worktool_new.R;
import com.example.worktool_new.Retrofit.Apis;
import com.example.worktool_new.Util.Common;
import com.example.worktool_new.Util.SharedPreference.AppConstants;
import com.squareup.picasso.Picasso;
import de.hdodenhof.circleimageview.CircleImageView;
import java.util.ArrayList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Adapter_network_invitation extends RecyclerView.Adapter<Adapter_network_invitation.ViewHolder> {
    /* access modifiers changed from: private */
    public Context context;
    /* access modifiers changed from: private */
    public ArrayList<InvitationRecievedModel.Datum> datamodelArraylist;

    public Adapter_network_invitation(Context context2, ArrayList<InvitationRecievedModel.Datum> datamodelArraylist2) {
        this.context = context2;
        this.datamodelArraylist = datamodelArraylist2;
    }

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(this.context).inflate(R.layout.item_invitation_recieved, parent, false));
    }

    public void onBindViewHolder(ViewHolder holder, final int position) {
        if (this.datamodelArraylist.get(position).getImage() != null) {
            if (!this.datamodelArraylist.get(position).getImage().isEmpty()) {
                Picasso.get().load(AppConstants.IMAGEURL + this.datamodelArraylist.get(position).getImage()).placeholder((int) R.drawable.profileplaceholder).error((int) R.drawable.profileplaceholder).into((ImageView) holder.iv_invitationImage);
            } else {
                holder.iv_invitationImage.setImageResource(R.drawable.profileplaceholder);
            }
        }
        TextView textView = holder.tv_invitationName;
        textView.setText(this.datamodelArraylist.get(position).getPrenom() + " " + this.datamodelArraylist.get(position).getNom());
        holder.bt_accept.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                ((Apis) new Retrofit.Builder().baseUrl("https://devworktools.fr/contenu/conseiller/").addConverterFactory(GsonConverterFactory.create()).build().create(Apis.class)).acceptInvite(Integer.parseInt(((InvitationRecievedModel.Datum) Adapter_network_invitation.this.datamodelArraylist.get(position)).getId())).enqueue(new Callback<AcceptInvitationRejectionModel>() {
                    public void onResponse(Call<AcceptInvitationRejectionModel> call, Response<AcceptInvitationRejectionModel> response) {
                        if (!Common.InternetCheckFragment(Adapter_network_invitation.this.context)) {
                            Common.showToastFragment(Adapter_network_invitation.this.context, "Internet Not Available,Please check Your Internet Connection");
                        } else if (response.body() != null) {
                            Common.showToastFragment(Adapter_network_invitation.this.context, response.body().getMessage());
                            Adapter_network_invitation.this.datamodelArraylist.remove(position);
                            Adapter_network_invitation.this.notifyItemRemoved(position);
                            Adapter_network_invitation.this.notifyItemRangeChanged(position, Adapter_network_invitation.this.datamodelArraylist.size());
                        } else {
                            Common.showToastFragment(Adapter_network_invitation.this.context, "Data not Found");
                        }
                    }

                    public void onFailure(Call<AcceptInvitationRejectionModel> call, Throwable t) {
                        Toast.makeText(Adapter_network_invitation.this.context, t.getLocalizedMessage(), 1).show();
                    }
                });
            }
        });
        holder.tvReject.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                ((Apis) new Retrofit.Builder().baseUrl("https://devworktools.fr/contenu/conseiller/").addConverterFactory(GsonConverterFactory.create()).build().create(Apis.class)).rejectInvite(Integer.parseInt(((InvitationRecievedModel.Datum) Adapter_network_invitation.this.datamodelArraylist.get(position)).getId())).enqueue(new Callback<AcceptInvitationRejectionModel>() {
                    public void onResponse(Call<AcceptInvitationRejectionModel> call, Response<AcceptInvitationRejectionModel> response) {
                        if (!Common.InternetCheckFragment(Adapter_network_invitation.this.context)) {
                            Common.showToastFragment(Adapter_network_invitation.this.context, "Internet Not Available,Please check Your Internet Connection");
                        } else if (response.body() != null) {
                            Common.showToastFragment(Adapter_network_invitation.this.context, response.body().getMessage());
                            Adapter_network_invitation.this.datamodelArraylist.remove(position);
                            Adapter_network_invitation.this.notifyItemRemoved(position);
                            Adapter_network_invitation.this.notifyItemRangeChanged(position, Adapter_network_invitation.this.datamodelArraylist.size());
                        } else {
                            Common.showToastFragment(Adapter_network_invitation.this.context, "Data not Found");
                        }
                    }

                    public void onFailure(Call<AcceptInvitationRejectionModel> call, Throwable t) {
                        Toast.makeText(Adapter_network_invitation.this.context, t.getLocalizedMessage(), 1).show();
                    }
                });
            }
        });
    }

    public int getItemCount() {
        ArrayList<InvitationRecievedModel.Datum> arrayList = this.datamodelArraylist;
        if (arrayList == null) {
            return 0;
        }
        return arrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        Button bt_accept;
        CircleImageView iv_invitationImage;
        TextView tvReject;
        TextView tv_invitationName;

        public ViewHolder(View itemView) {
            super(itemView);
            this.iv_invitationImage = (CircleImageView) itemView.findViewById(R.id.iv_invitationImage);
            this.tv_invitationName = (TextView) itemView.findViewById(R.id.tv_invitationName);
            this.bt_accept = (Button) itemView.findViewById(R.id.bt_accept);
            this.tvReject = (TextView) itemView.findViewById(R.id.tvReject);
        }
    }
}
