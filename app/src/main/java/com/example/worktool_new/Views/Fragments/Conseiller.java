package com.example.worktool_new.Views.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.worktool_new.Adapters.Adapter_MyData;
import com.example.worktool_new.Adapters.Adapter_conseiller;
import com.example.worktool_new.Models.ConseillerModel;
import com.example.worktool_new.Models.MyDataModel;
import com.example.worktool_new.Models.ParticipationListModel;
import com.example.worktool_new.R;
import com.example.worktool_new.Retrofit.Apis;
import com.example.worktool_new.Util.SharedPreference.App;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class Conseiller extends Fragment {
    View view;
    private Adapter_conseiller adapter;
    public RecyclerView conseiller_list;
    private ArrayList<ConseillerModel> arrayList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_conseiller, container, false);

        conseiller_list = view.findViewById(R.id.rv_counselor);
        getConseillerData();
        return view;
    }

    public void getConseillerData() {
        ((Apis) new Retrofit.Builder().baseUrl("http://devworktools.fr/contenu/conseiller/").addConverterFactory(GsonConverterFactory.create()).build().create(Apis.class)).conseiller(("11278")).enqueue(new Callback<ConseillerModel>() {
            @Override
            public void onResponse(Call<ConseillerModel> call, Response<ConseillerModel> response) {
                  Log.i("ididid",""+response.body().getData());
                ArrayList<ConseillerModel.Datum> data = response.body().getData();
                adapter = new Adapter_conseiller(getContext(), data);
                conseiller_list.setAdapter(adapter);
                Toast.makeText(getContext(), "Data received successfully!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<ConseillerModel> call, Throwable t) {
                Toast.makeText(getContext(), ""+t, Toast.LENGTH_SHORT).show();
                Log.i("throwable",t+"");
            }
        });
    }
}