package com.example.worktool_new.Adapters;

import android.app.Dialog;
import android.content.Context;
import android.media.Image;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.worktool_new.Models.DeleteAgendaModel;
import com.example.worktool_new.Models.MyAgendaModel;
import com.example.worktool_new.Models.MyEventModel;
import com.example.worktool_new.R;
import com.example.worktool_new.Retrofit.Apis;
import com.example.worktool_new.Views.Fragments.MyAgenda;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.worktool_new.Util.BaseApplication.getContext;

public class AdapterAgenda extends RecyclerView.Adapter<AdapterAgenda.ViewHolder> {
    private Context context;
    private ArrayList<MyAgendaModel.Datum> datamodelArraylist;
    onAgendaClick onAgendaClick;
    Dialog dialog;

    public interface onAgendaClick {
        void onAgendaDelete(int i, int i2);
    }

    public AdapterAgenda(Context context2, ArrayList<MyAgendaModel.Datum> datamodelArraylist2,onAgendaClick onAgendaClick2) {
        this.context = context2;
        this.datamodelArraylist = datamodelArraylist2;
        this.onAgendaClick = onAgendaClick2;
    }

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(this.context).inflate(R.layout.item_agenda, parent, false));
    }

    public void onBindViewHolder(ViewHolder holder, final int position) {
        if (this.datamodelArraylist.get(position).getTitre() != null) {
            holder.tvAgendaTitle.setText(this.datamodelArraylist.get(position).getTitre());
        }
        if (this.datamodelArraylist.get(position).getDate() != null || !this.datamodelArraylist.get(position).getDate().isEmpty()) {
            String[] separated = this.datamodelArraylist.get(position).getDate().split(" ");
            holder.tvAgendaDate.setText(separated[0]);
            // holder.tvAgendaTime.setText(separated[1]);
        }
        holder.deleteAgenda.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Log.i("position", String.valueOf(position));
                int id =Integer.parseInt(AdapterAgenda.this.datamodelArraylist.get(position).getIdEvent());
                // Log.i("id", String.valueOf(Integer.parseInt(((MyAgendaModel.Datum) AdapterAgenda.this.datamodelArraylist.get(position)).getIdEvent())));
                AdapterAgenda.this.onAgendaClick.onAgendaDelete(position,id);
            }
        });
    }

    public int getItemCount() {
        ArrayList<MyAgendaModel.Datum> arrayList = this.datamodelArraylist;
        if (arrayList == null) {
            return 0;
        }
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvAgendaDate;
        TextView tvAgendaTime;
        TextView tvAgendaTitle;
        ImageView deleteAgenda;

        public ViewHolder(View itemView) {
            super(itemView);
            this.tvAgendaTitle = (TextView) itemView.findViewById(R.id.tvAgendaTitle);
            this.tvAgendaTime = (TextView) itemView.findViewById(R.id.tvAgendaTime);
            this.tvAgendaDate = (TextView) itemView.findViewById(R.id.tvAgendaDate);
            this.deleteAgenda = (ImageView) itemView.findViewById(R.id.deleteAgenda);
        }
    }
}
