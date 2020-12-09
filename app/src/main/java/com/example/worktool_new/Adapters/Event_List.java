package com.example.worktool_new.Adapters;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;
import com.example.worktool_new.Models.ListEventsModel;
import com.example.worktool_new.R;
import com.example.worktool_new.Retrofit.Apis;
import com.example.worktool_new.Util.SharedPreference.App;
import com.example.worktool_new.Util.SharedPreference.AppConstants;
import com.example.worktool_new.Views.Activities.Detail_ListEvent;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Event_List extends RecyclerView.Adapter<Event_List.ViewHolder> {
    private Context context;
    Dialog dialog;
    private ArrayList<ListEventsModel.Datum> datamodelArraylist;

    public Event_List(Context context2, ArrayList<ListEventsModel.Datum> datamodelArraylist2) {
        this.context = context2;
        this.datamodelArraylist = datamodelArraylist2;
    }

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(this.context).inflate(R.layout.item_events, parent, false));
    }

    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Event_List.this.context, Detail_ListEvent.class);
                intent.putExtra("activity", "detail");
                intent.putExtra("position", position);
                intent.putExtra("eventlist", Event_List.this.datamodelArraylist);
                Event_List.this.context.startActivity(intent);
            }
        });
        holder.tvListEventName.setText(this.datamodelArraylist.get(position).getPrenom() + " " + this.datamodelArraylist.get(position).getNom());
        holder.tvListEventTitle.setText(this.datamodelArraylist.get(position).getTitre());
        holder.tvListEventType.setText("Type: " + this.datamodelArraylist.get(position).getEventType());
        String ListEventImage = AppConstants.IMAGEURL + this.datamodelArraylist.get(position).getFile();
        if (this.datamodelArraylist.get(position).getFile() != null) {
            Picasso.get().load(ListEventImage).placeholder((int) R.drawable.profileplaceholder).error((int) R.drawable.profileplaceholder).into(holder.ivListEventImage);
        } else {
            holder.ivListEventImage.setImageResource(R.drawable.profileplaceholder);
        }
        holder.registerTV.setText("Register");
        String type = Event_List.this.datamodelArraylist.get(position).getStatus(); // get from api of list events
        Log.i("type",type);
        Log.i("type",datamodelArraylist.get(position).getIdEvent());
        if(type.equals("Subscribed")){
            holder.registerTV.setEnabled(false);
            holder.registerTV.setText("Subscribed");
            holder.registerTV.setTextColor(R.color.colorGrey);
        }
        else if(type.equals("Register")) {
            holder.registerLayout.setOnClickListener(new View.OnClickListener() {
                @SuppressLint("ResourceAsColor")
                @Override
                public void onClick(View v) {
                    final String idEvent = Event_List.this.datamodelArraylist.get(position).getIdEvent();
                    Dialog dialog2 = new Dialog(context);
                    dialog = dialog2;
                    dialog2.requestWindowFeature(1);
                    dialog.setContentView(R.layout.custom_dialog_event_list);
                    dialog.setCancelable(false);
                    dialog.show();
                    ( dialog.findViewById(R.id.btnCommentDialog)).setOnClickListener(new View.OnClickListener() {
                        public void onClick(View v) {
                            dialog.dismiss();
                        }
                    });
                    ( dialog.findViewById(R.id.tvCommentDeleteYes)).setOnClickListener(new View.OnClickListener() {
                        public void onClick(View v) {
                            uploadRegisterEvent(idEvent, holder.registerTV);
                            dialog.dismiss();
                            holder.registerTV.setEnabled(false);
                        }
                    });
                    ( dialog.findViewById(R.id.tvCommentDeleteNo)).setOnClickListener(new View.OnClickListener() {
                        public void onClick(View v) {
                            dialog.dismiss();
                        }
                    });

                }
            });
        }
    }

    private void uploadRegisterEvent(String idEvent, final TextView registerTV) {
        String id = App.getAppPreference().getString("LoginId");
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://devworktools.fr/contenu/conseiller/").addConverterFactory(GsonConverterFactory.create()).build();
        ((Apis) retrofit.create(Apis.class)).registerEvent(id, idEvent).enqueue(new Callback<ResponseBody>() {
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if(response.isSuccessful()){
                    Toast.makeText(context, "Subscribed successfully", Toast.LENGTH_SHORT).show();
                    registerTV.setText("Subscribed");
                    registerTV.setEnabled(false);
                    registerTV.setTextColor(R.color.colorGrey);
                }
            }

            public void onFailure(Call<ResponseBody> call, Throwable t) {
            }
        });
    }

    public int getItemCount() {
        ArrayList<ListEventsModel.Datum> arrayList = this.datamodelArraylist;
        if (arrayList == null) {
            return 0;
        }
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView ivListEventImage;
        TextView tvListEventName;
        TextView tvListEventTitle;
        TextView tvListEventType, registerTV;
        RelativeLayout registerLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            this.ivListEventImage = (ImageView) itemView.findViewById(R.id.ivListEventImage);
            this.tvListEventName = (TextView) itemView.findViewById(R.id.tvListEventName);
            this.tvListEventTitle = (TextView) itemView.findViewById(R.id.tvListEventTitle);
            this.tvListEventType = (TextView) itemView.findViewById(R.id.tvListEventType);
            this.registerLayout = itemView.findViewById(R.id.registerEventLayout);
            registerTV = itemView.findViewById(R.id.registerTxtView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

        }
    }
}
