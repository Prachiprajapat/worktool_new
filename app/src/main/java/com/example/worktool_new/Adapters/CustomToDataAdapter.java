package com.example.worktool_new.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.example.worktool_new.Models.ToModel;
import com.example.worktool_new.R;
import java.util.ArrayList;

public class CustomToDataAdapter extends BaseAdapter {
    ArrayList<ToModel.Datum> body;
    Context context;
    private LayoutInflater inflter;

    public CustomToDataAdapter(Context context2, ArrayList<ToModel.Datum> body2) {
        this.context = context2;
        this.body = body2;
        this.inflter = LayoutInflater.from(context2);
    }

    public int getCount() {
        return this.body.size();
    }

    public Object getItem(int position) {
        return this.body.get(position);
    }

    public long getItemId(int position) {
        return 0;
    }

    public View getView(int position, View view, ViewGroup parent) {
        View view2 = this.inflter.inflate(R.layout.spinner_item, (ViewGroup) null);
        ((TextView) view2.findViewById(R.id.text1)).setText(this.body.get(position).getPrenom() + " " + this.body.get(position).getNom());
        return view2;
    }
}
