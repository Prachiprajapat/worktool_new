package com.example.worktool_new.Views.Fragments;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import com.example.worktool_new.Adapters.AdapterInbox;
import com.example.worktool_new.Adapters.AdapterSentMail;
import com.example.worktool_new.Models.MyInboxModel;
import com.example.worktool_new.Models.MySentMessageModel;
import com.example.worktool_new.R;
import com.example.worktool_new.Retrofit.Apis;
import com.example.worktool_new.Util.SharedPreference.App;
import com.example.worktool_new.Views.Activities.ComposeMail;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Inbox extends Fragment {
    /* access modifiers changed from: private */
    public AdapterInbox mAdapter;
    /* access modifiers changed from: private */
    public AdapterSentMail mAdapterSentMail;
    private ProgressDialog progress;
    RecyclerView recyclerInbox;
    RelativeLayout relInbox;
    RelativeLayout relSendMail;
    RelativeLayout rlComposeMail;
    TextView tvInbox;
    TextView tvNoDataFound;
    TextView tvSendMail;
    View view;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (this.view == null) {
            View inflate = inflater.inflate(R.layout.fragment_inbox, container, false);
            this.view = inflate;
            this.recyclerInbox = (RecyclerView) inflate.findViewById(R.id.recyclerInbox);
            this.rlComposeMail = (RelativeLayout) this.view.findViewById(R.id.rlComposeMail);
            this.relInbox = (RelativeLayout) this.view.findViewById(R.id.rel_Inbox);
            this.relSendMail = (RelativeLayout) this.view.findViewById(R.id.rel_SendMail);
            this.tvInbox = (TextView) this.view.findViewById(R.id.tvInbox);
            this.tvSendMail = (TextView) this.view.findViewById(R.id.tvSendMail);
            this.tvNoDataFound = (TextView) this.view.findViewById(R.id.tvNoDataFound);
            InboxPosts();
            this.rlComposeMail.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Inbox.this.startActivity(new Intent(Inbox.this.getActivity(), ComposeMail.class));
                }
            });
            this.relInbox.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Inbox.this.tvInbox.setTextColor(Inbox.this.getResources().getColor(R.color.colorPrimary));
                    Inbox.this.tvSendMail.setTextColor(Inbox.this.getResources().getColor(R.color.colorWhite));
                    Inbox.this.relInbox.setBackground(Inbox.this.getResources().getDrawable(R.drawable.serach_background));
                    Inbox.this.relSendMail.setBackground(ContextCompat.getDrawable(Inbox.this.getContext(), R.drawable.button_style_toggle));
                    Inbox.this.InboxPosts();
                }
            });
            this.relSendMail.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Inbox.this.tvInbox.setTextColor(Inbox.this.getResources().getColor(R.color.colorWhite));
                    Inbox.this.tvSendMail.setTextColor(Inbox.this.getResources().getColor(R.color.colorPrimary));
                    Inbox.this.relInbox.setBackground(Inbox.this.getResources().getDrawable(R.drawable.button_style_toggle));
                    Inbox.this.relSendMail.setBackground(ContextCompat.getDrawable(Inbox.this.getContext(), R.drawable.serach_background));
                    Inbox.this.SendMailPosts();
                }
            });
        }
        return this.view;
    }

    /* access modifiers changed from: private */
    public void InboxPosts() {
        if (isAdded()) {
            showLoadingDialog();
        }
        ((Apis) new Retrofit.Builder().baseUrl("http://devworktools.fr/contenu/conseiller/").addConverterFactory(GsonConverterFactory.create()).build().create(Apis.class)).getMyInbox(App.getAppPreference().getString("LoginId")).enqueue(new Callback<MyInboxModel>() {
            public void onResponse(Call<MyInboxModel> call, Response<MyInboxModel> response) {
                if (!response.isSuccessful()) {
                    Inbox.this.dismissLoadingDialog();
                    Inbox.this.recyclerInbox.setVisibility(8);
                    Inbox.this.tvNoDataFound.setVisibility(0);
                    Toast.makeText(Inbox.this.getContext(), "Data Not Found", 0).show();
                } else if (response.body() == null || response.body().getStatusCode() == null || response.body().getStatusCode().intValue() != 200) {
                    Inbox.this.recyclerInbox.setVisibility(8);
                    Inbox.this.tvNoDataFound.setVisibility(0);
                    Inbox.this.dismissLoadingDialog();
                    Toast.makeText(Inbox.this.getContext(), "Data Not Found", 0).show();
                } else if (response.body() == null || response.body().getData().size() <= 0) {
                    Inbox.this.dismissLoadingDialog();
                    Inbox.this.recyclerInbox.setVisibility(8);
                    Inbox.this.tvNoDataFound.setVisibility(0);
                    Toast.makeText(Inbox.this.getContext(), "Data Not Found", 0).show();
                } else {
                    Inbox.this.dismissLoadingDialog();
                    Inbox.this.recyclerInbox.setVisibility(0);
                    Inbox.this.tvNoDataFound.setVisibility(8);
                    AdapterInbox unused = Inbox.this.mAdapter = new AdapterInbox(Inbox.this.getActivity(), response.body().getData());
                    Inbox.this.recyclerInbox.setAdapter(Inbox.this.mAdapter);
                }
            }

            public void onFailure(Call<MyInboxModel> call, Throwable t) {
                Inbox.this.recyclerInbox.setVisibility(8);
                Inbox.this.tvNoDataFound.setVisibility(0);
                Inbox.this.dismissLoadingDialog();
                Toast.makeText(Inbox.this.getContext(), t.getLocalizedMessage(), 1).show();
            }
        });
    }

    /* access modifiers changed from: private */
    public void SendMailPosts() {
        if (isAdded()) {
            showLoadingDialog();
        }
        ((Apis) new Retrofit.Builder().baseUrl("http://devworktools.fr/contenu/conseiller/").addConverterFactory(GsonConverterFactory.create()).build().create(Apis.class)).getMySentMessage(App.getAppPreference().getString("LoginId")).enqueue(new Callback<MySentMessageModel>() {
            public void onResponse(Call<MySentMessageModel> call, Response<MySentMessageModel> response) {
                if (!response.isSuccessful()) {
                    Inbox.this.dismissLoadingDialog();
                    Inbox.this.recyclerInbox.setVisibility(8);
                    Inbox.this.tvNoDataFound.setVisibility(0);
                    Toast.makeText(Inbox.this.getContext(), "Data Not Found", 0).show();
                } else if (response.body() == null || response.body().getStatusCode() == null || response.body().getStatusCode().intValue() != 200) {
                    Inbox.this.dismissLoadingDialog();
                    Inbox.this.recyclerInbox.setVisibility(8);
                    Inbox.this.tvNoDataFound.setVisibility(0);
                    Toast.makeText(Inbox.this.getContext(), "Data Not Found", 0).show();
                } else if (response.body() == null || response.body().getData().size() <= 0) {
                    Inbox.this.dismissLoadingDialog();
                    Inbox.this.recyclerInbox.setVisibility(8);
                    Inbox.this.tvNoDataFound.setVisibility(0);
                    Toast.makeText(Inbox.this.getContext(), "Data Not Found", 0).show();
                } else {
                    Inbox.this.dismissLoadingDialog();
                    Inbox.this.recyclerInbox.setVisibility(0);
                    Inbox.this.tvNoDataFound.setVisibility(8);
                    AdapterSentMail unused = Inbox.this.mAdapterSentMail = new AdapterSentMail(Inbox.this.getActivity(), response.body().getData());
                    Inbox.this.recyclerInbox.setAdapter(Inbox.this.mAdapterSentMail);
                }
            }

            public void onFailure(Call<MySentMessageModel> call, Throwable t) {
                Inbox.this.recyclerInbox.setVisibility(8);
                Inbox.this.tvNoDataFound.setVisibility(0);
                Inbox.this.dismissLoadingDialog();
                Toast.makeText(Inbox.this.getContext(), t.getLocalizedMessage(), 1).show();
            }
        });
    }

    public void showLoadingDialog() {
        if (this.progress == null) {
            ProgressDialog progressDialog = new ProgressDialog(getContext());
            this.progress = progressDialog;
            progressDialog.setTitle(getString(R.string.loading_title));
            this.progress.setMessage(getString(R.string.loading_message));
            this.progress.setCancelable(false);
        }
        this.progress.show();
    }

    public void dismissLoadingDialog() {
        ProgressDialog progressDialog = this.progress;
        if (progressDialog != null && progressDialog.isShowing()) {
            this.progress.dismiss();
        }
    }
}
