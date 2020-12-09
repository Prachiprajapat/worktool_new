package com.example.worktool_new.Adapters;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.text.Html;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.recyclerview.widget.RecyclerView;
import com.example.worktool_new.Models.DeleteMessageModel;
import com.example.worktool_new.Models.MyInboxModel;
import com.example.worktool_new.R;
import com.example.worktool_new.Retrofit.Apis;
import com.example.worktool_new.Util.Common;
import com.example.worktool_new.Util.SharedPreference.App;
import com.example.worktool_new.Util.SharedPreference.AppConstants;
import com.example.worktool_new.Views.Activities.MailBoxInner;
import com.squareup.picasso.Picasso;
import de.hdodenhof.circleimageview.CircleImageView;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AdapterInbox extends RecyclerView.Adapter<AdapterInbox.ViewHolder> {
    /* access modifiers changed from: private */
    public Context context;
    /* access modifiers changed from: private */
    public ArrayList<MyInboxModel.Datum> datamodelArraylist;

    public AdapterInbox(Context context2, ArrayList<MyInboxModel.Datum> datamodelArraylist2) {
        this.context = context2;
        this.datamodelArraylist = datamodelArraylist2;
    }

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(this.context).inflate(R.layout.item_inbox, parent, false));
    }

    public void onBindViewHolder(ViewHolder holder, final int position) {

        holder.llInnerDetail.setOnLongClickListener(new View.OnLongClickListener() {
            public boolean onLongClick(View v) {
                final Dialog dialog = new Dialog(AdapterInbox.this.context);
                dialog.requestWindowFeature(1);
                dialog.setContentView(R.layout.delete_message_dialog);
                dialog.setCancelable(false);
                dialog.show();
                ((TextView) dialog.findViewById(R.id.tvNo)).setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                ((TextView) dialog.findViewById(R.id.tvYes)).setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        dialog.dismiss();
                        Common.showLoadingFragment(AdapterInbox.this.context, "Loading,Please Wait");
                        ((Apis) new Retrofit.Builder().baseUrl("http://devworktools.fr/contenu/conseiller/").addConverterFactory(GsonConverterFactory.create()).build().create(Apis.class)).deleteMessage(Integer.parseInt(App.getAppPreference().getString("LoginId")), Integer.parseInt(((MyInboxModel.Datum) AdapterInbox.this.datamodelArraylist.get(position)).getIdMessage())).enqueue(new Callback<DeleteMessageModel>() {
                            public void onResponse(Call<DeleteMessageModel> call, Response<DeleteMessageModel> response) {
                                if (!Common.InternetCheckFragment(AdapterInbox.this.context)) {
                                    Common.dismissFragment();
                                    Toast.makeText(AdapterInbox.this.context, "Internet Not Available,Please check Your Internet Connection", 0).show();
                                } else if (response.body() != null) {
                                    Common.dismissFragment();
                                    Toast.makeText(AdapterInbox.this.context, "Comment Deleted Successfully", 0).show();
                                    dialog.dismiss();
                                    AdapterInbox.this.datamodelArraylist.remove(position);
                                    AdapterInbox.this.notifyItemRemoved(position);
                                    AdapterInbox.this.notifyItemRangeChanged(position, AdapterInbox.this.datamodelArraylist.size());
                                } else {
                                    Common.dismissFragment();
                                    Toast.makeText(AdapterInbox.this.context, "Data not Found", 0).show();
                                }
                            }

                            public void onFailure(Call<DeleteMessageModel> call, Throwable t) {
                                Common.dismissFragment();
                                Toast.makeText(AdapterInbox.this.context, t.getLocalizedMessage(), 1).show();
                            }
                        });
                    }
                });
                return false;
            }
        });

        final String InboxProfileImage = AppConstants.IMAGEURL + this.datamodelArraylist.get(position).getImage();
        if (this.datamodelArraylist.get(position).getImage() != null) {
            Picasso.get().load(InboxProfileImage).placeholder((int) R.drawable.profileplaceholder).
                    error((int) R.drawable.profileplaceholder).into((ImageView) holder.personImage);
        } else {
            holder.personImage.setImageResource(R.drawable.profileplaceholder);
        }
        getDate(Long.parseLong(this.datamodelArraylist.get(position).getDate()), holder, position);
        holder.tvInboxName.setText(this.datamodelArraylist.get(position).getName());
        holder.tvInboxSubject.setText(this.datamodelArraylist.get(position).getSujet() + " : ");
        if (!this.datamodelArraylist.get(position).getFile().isEmpty()) {
            holder.articleImage.setVisibility(0);
            holder.tvInboxFileName.setText(this.datamodelArraylist.get(position).getFile().get(0));
        } else {
            holder.articleImage.setVisibility(8);
        }
        holder.tvInboxMessage.setText(Html.fromHtml(Html.fromHtml(this.datamodelArraylist.get(position).getMessage()).toString().trim()));

        holder.llInnerDetail.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(AdapterInbox.this.context, MailBoxInner.class);
                intent.putExtra("from", "inboxAdapter");
                intent.putExtra("name",datamodelArraylist.get(position).getName());
                intent.putExtra("subject",datamodelArraylist.get(position).getSujet());
                intent.putExtra("date",datamodelArraylist.get(position).getDate());
                intent.putExtra("message",datamodelArraylist.get(position).getMessage());
                intent.putExtra("image", datamodelArraylist.get(position).getFile().toString());
                intent.putExtra("recieverimage", InboxProfileImage);
                intent.putExtra("mailid", datamodelArraylist.get(position).getIdMessage());
                AdapterInbox.this.context.startActivity(intent);

                Log.i("inboxname", ""+datamodelArraylist.get(position).getName());
                Log.i("inboxsub", ""+datamodelArraylist.get(position).getSujet());
                Log.i("inboxdate", ""+datamodelArraylist.get(position).getDate());
                Log.i("inboxmes", ""+datamodelArraylist.get(position).getMessage());
                Log.i("inboximg", ""+datamodelArraylist.get(position).getFile().toString());
                Log.i("inboxrecimg", ""+datamodelArraylist.get(position).getImage().toString());
            }
        });
    }

    private void getDate(long time, ViewHolder holder, int position) {
        Calendar cal = Calendar.getInstance(Locale.ENGLISH);
        cal.setTimeInMillis(1000 * time);
        holder.tvInboxDate.setText(DateFormat.format("yyyy-MM-dd HH:mm:ss", cal).toString());
    }

    public int getItemCount() {
        ArrayList<MyInboxModel.Datum> arrayList = this.datamodelArraylist;
        if (arrayList == null) {
            return 0;
        }
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        RelativeLayout articleImage;
        LinearLayout llInnerDetail;
        CircleImageView personImage;
        TextView tvInboxDate;
        TextView tvInboxEmail;
        TextView tvInboxFileName;
        TextView tvInboxMessage;
        TextView tvInboxName;
        TextView tvInboxSubject;
        TextView tvInboxTime;

        public ViewHolder(View itemView) {
            super(itemView);
            this.llInnerDetail = (LinearLayout) itemView.findViewById(R.id.llInnerDetail);
            this.articleImage = (RelativeLayout) itemView.findViewById(R.id.articleImage);
            this.personImage = (CircleImageView) itemView.findViewById(R.id.personImage);
            this.tvInboxName = (TextView) itemView.findViewById(R.id.tvInboxName);
            this.tvInboxDate = (TextView) itemView.findViewById(R.id.tvInboxDate);
            this.tvInboxTime = (TextView) itemView.findViewById(R.id.tvInboxTime);
            this.tvInboxSubject = (TextView) itemView.findViewById(R.id.tvInboxSubject);
            this.tvInboxEmail = (TextView) itemView.findViewById(R.id.tvInboxEmail);
            this.tvInboxFileName = (TextView) itemView.findViewById(R.id.tvInboxFileName);
            this.tvInboxMessage = (TextView) itemView.findViewById(R.id.tvInboxMessage);
        }
    }
}