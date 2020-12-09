package com.example.worktool_new.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.recyclerview.widget.RecyclerView;
import com.example.worktool_new.Models.SendInvitationModel;
import com.example.worktool_new.Models.SendNetworkInvitationModel;
import com.example.worktool_new.R;
import com.example.worktool_new.Retrofit.Apis;
import com.example.worktool_new.Util.Common;
import com.example.worktool_new.Util.SharedPreference.AppConstants;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AdapterSendInvitation extends RecyclerView.Adapter<AdapterSendInvitation.ViewHolder> {
    /* access modifiers changed from: private */
    public Context context;
    /* access modifiers changed from: private */
    public ArrayList<SendInvitationModel.Datum> datamodelArraylist;

    public AdapterSendInvitation(Context context2, ArrayList<SendInvitationModel.Datum> datamodelArraylist2) {
        this.context = context2;
        this.datamodelArraylist = datamodelArraylist2;
    }

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(this.context).inflate(R.layout.item_sendinvitation, parent, false));
    }

    public void onBindViewHolder(ViewHolder holder, final int position) {
        String NetworkName = this.datamodelArraylist.get(position).getPrenom() + " " + this.datamodelArraylist.get(position).getNom();
        if (NetworkName != null || !NetworkName.isEmpty()) {
            holder.tv_invite_name.setText(NetworkName);
        }
        holder.tv_invite.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Common.showLoadingFragment(AdapterSendInvitation.this.context, "Loading");
                ((Apis) new Retrofit.Builder().baseUrl("http://devworktools.fr/contenu/conseiller/").addConverterFactory(GsonConverterFactory.create()).build().create(Apis.class)).sendInvite(Integer.parseInt(((SendInvitationModel.Datum) AdapterSendInvitation.this.datamodelArraylist.get(position)).getIdConseiller()), Integer.parseInt(((SendInvitationModel.Datum) AdapterSendInvitation.this.datamodelArraylist.get(position)).getIdCompte())).enqueue(new Callback<SendNetworkInvitationModel>() {
                    public void onResponse(Call<SendNetworkInvitationModel> call, Response<SendNetworkInvitationModel> response) {
                        if (!Common.InternetCheckFragment(AdapterSendInvitation.this.context)) {
                            Common.dismissFragment();
                            Common.showToastFragment(AdapterSendInvitation.this.context, "Internet Not Available,Please check Your Internet Connection");
                        } else if (response.body() == null) {
                        } else {
                            if (response.body().getSuccess() == null || response.body().getSuccess().intValue() != 200) {
                                Common.dismissFragment();
                                Common.showToastFragment(AdapterSendInvitation.this.context, "Data not Found");
                                return;
                            }
                            Common.dismissFragment();
                            if (response.body() != null) {
                                Common.dismissFragment();
                                Common.showToastFragment(AdapterSendInvitation.this.context, response.body().getMessage());
                                return;
                            }
                            Common.dismissFragment();
                            if (response.body() != null) {
                                Common.showToastFragment(AdapterSendInvitation.this.context, response.body().getMessage());
                            }
                        }
                    }

                    public void onFailure(Call<SendNetworkInvitationModel> call, Throwable t) {
                        Common.dismissFragment();
                        Toast.makeText(AdapterSendInvitation.this.context, t.getLocalizedMessage(), 1).show();
                    }
                });
            }
        });
        String NetworkImage = AppConstants.IMAGEURL + this.datamodelArraylist.get(position).getImage();
        if (this.datamodelArraylist.get(position).getImage() != null) {
            Picasso.get().load(NetworkImage).placeholder((int) R.drawable.placeholder).error((int) R.drawable.placeholder).into(holder.iv_invite_image);
        } else {
            holder.iv_invite_image.setImageResource(R.drawable.placeholder);
        }
    }

    public int getItemCount() {
        ArrayList<SendInvitationModel.Datum> arrayList = this.datamodelArraylist;
        if (arrayList == null) {
            return 0;
        }
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView iv_invite_image;
        TextView tv_invite;
        TextView tv_invite_name;

        public ViewHolder(View itemView) {
            super(itemView);
            this.iv_invite_image = (ImageView) itemView.findViewById(R.id.iv_invite_image);
            this.tv_invite_name = (TextView) itemView.findViewById(R.id.tv_invite_name);
            this.tv_invite = (TextView) itemView.findViewById(R.id.tv_invite);
        }
    }
}
