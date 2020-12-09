package com.example.worktool_new.Views.Fragments;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import com.example.worktool_new.Adapters.Adapter_Members;
import com.example.worktool_new.Models.MemberModel;
import com.example.worktool_new.Models.deleteMemberModel.DeleteMemberModel;
import com.example.worktool_new.R;
import com.example.worktool_new.Retrofit.Apis;
import com.example.worktool_new.Util.DebounceClickListener;
import com.example.worktool_new.Util.SharedPreference.App;
import com.example.worktool_new.Views.Activities.Add_Member;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Members extends Fragment implements Adapter_Members.ondelete {
    private LinearLayout ll_add_member;
    /* access modifiers changed from: private */
    public Adapter_Members mAdapter;
    /* access modifiers changed from: private */
    public ArrayList<MemberModel.Compte> memberlist = new ArrayList<>();
    ArrayList<MemberModel.Compte> memberlist1 = new ArrayList<>();
    private ProgressDialog progress;
    /* access modifiers changed from: private */
    public RecyclerView rv_members;
    private EditText searchmembers;
    /* access modifiers changed from: private */
    public TextView tvNoDataFound;
    /* access modifiers changed from: private */
    public TextView tvTotalMember;
    private View view;
    String added="added";
    String added1="noadd";

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

            this.view = inflater.inflate(R.layout.fragment_members, container, false);


            init();

        return this.view;
    }

    public void init() {
        this.ll_add_member = (LinearLayout) this.view.findViewById(R.id.ll_add_member);
        this.rv_members = (RecyclerView) this.view.findViewById(R.id.rv_members);
        this.tvTotalMember = (TextView) this.view.findViewById(R.id.tvTotalMember);
        this.searchmembers = (EditText) this.view.findViewById(R.id.ed_searchMembers);
        this.tvNoDataFound = (TextView) this.view.findViewById(R.id.tvNoDataFound);
        try {
            Bundle  bundle=getArguments();
            Log.e("Frag",""+bundle.getString("added"));

            added1= bundle.getString("added");
        }
        catch (Exception e){e.printStackTrace();}

        if(added1!=null)
        {
            if(added.equals(added1))
            {
                getMembers();
            }
        }
        memberPosts();


        this.searchmembers.addTextChangedListener(new TextWatcher() {
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().length() > 0) {
                    Members.this.memberlist1.clear();
                    for (int i = 0; i < Members.this.memberlist.size(); i++) {
                        if ((((MemberModel.Compte) Members.this.memberlist.get(i)).getPrenom() != null && ((MemberModel.Compte) Members.this.memberlist.get(i)).getPrenom().toUpperCase().startsWith(s.toString().toUpperCase())) || (((MemberModel.Compte) Members.this.memberlist.get(i)).getNom() != null && ((MemberModel.Compte) Members.this.memberlist.get(i)).getNom().toUpperCase().startsWith(s.toString().toUpperCase()))) {
                            Members.this.memberlist1.add(Members.this.memberlist.get(i));
                        }
                    }
                    Members.this.mAdapter.updateList(Members.this.memberlist1);
                    return;
                }
                if (Members.this.memberlist1.size() > 0) {
                    Members.this.memberlist1.clear();
                }
                Members.this.mAdapter.updateList(Members.this.memberlist);
            }

            public void afterTextChanged(Editable s) {
            }
        });
        this.ll_add_member.setOnClickListener(new DebounceClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Members.this.startActivity(new Intent(Members.this.getContext(), Add_Member.class));
            }
        }));
    }

    public void getMembers()
    {
        showLoadingDialog();
        Log.e("In fn",""+added1);
        ((Apis) new Retrofit.Builder().baseUrl("http://devworktools.fr/contenu/conseiller/").addConverterFactory(GsonConverterFactory.create()).client(new OkHttpClient.Builder().addInterceptor(provideHttpLoggingInterceptor()).readTimeout(120, TimeUnit.SECONDS).writeTimeout(120, TimeUnit.SECONDS).build()).build().create(Apis.class)).getMembers(App.getAppPreference().getString("LoginId")).enqueue(new Callback<MemberModel>() {
            public void onResponse(Call<MemberModel> call, Response<MemberModel> response) {
                if (!response.isSuccessful()) {
                    Members.this.dismissLoadingDialog();
                    Members.this.rv_members.setVisibility(8);
                    Members.this.tvNoDataFound.setVisibility(0);
                    Toast.makeText(Members.this.getContext(), "Data Not Found", 0).show();
                } else if (response.body() != null && response.body().getStatusCode() != null && response.body().getStatusCode().intValue() == 200) {
                    if (response.body().getData() == null) {
                        Members.this.dismissLoadingDialog();
                        Members.this.rv_members.setVisibility(8);
                        Members.this.tvNoDataFound.setVisibility(0);
                        Toast.makeText(Members.this.getContext(), "Data Not Found", 0).show();
                    } else if (response.body().getData().getCompte().size() > 0) {
                        Members.this.dismissLoadingDialog();
                        Members.this.rv_members.setVisibility(0);
                        Members.this.tvNoDataFound.setVisibility(8);
                        TextView access$400 = Members.this.tvTotalMember;
                        Log.e("getting Data",""+added1);
                        access$400.setText("You added " + response.body().getData().getCompte().size() + " Members");
                        Members.this.memberlist.clear();
                        ArrayList unused = Members.this.memberlist = response.body().getData().getCompte();
                        Adapter_Members unused2 = Members.this.mAdapter = new Adapter_Members(Members.this.getContext(), Members.this.memberlist, Members.this);
                        Members.this.rv_members.setAdapter(Members.this.mAdapter);
                    } else {
                        Members.this.dismissLoadingDialog();
                        Members.this.rv_members.setVisibility(8);
                        Members.this.tvNoDataFound.setVisibility(0);
                        Toast.makeText(Members.this.getContext(), "Data Not Found", 0).show();
                    }
                }
            }

            public void onFailure(Call<MemberModel> call, Throwable t) {
                if (Members.this.isAdded()) {
                    Members.this.dismissLoadingDialog();
                }
                Members.this.rv_members.setVisibility(8);
                Members.this.tvNoDataFound.setVisibility(0);
            }
        });
    }


    /* access modifiers changed from: private */
    public void memberPosts() {
        if (isAdded()) {
            showLoadingDialog();
        }
        ((Apis) new Retrofit.Builder().baseUrl("http://devworktools.fr/contenu/conseiller/").addConverterFactory(GsonConverterFactory.create()).client(new OkHttpClient.Builder().addInterceptor(provideHttpLoggingInterceptor()).readTimeout(120, TimeUnit.SECONDS).writeTimeout(120, TimeUnit.SECONDS).build()).build().create(Apis.class)).getMembers(App.getAppPreference().getString("LoginId")).enqueue(new Callback<MemberModel>() {
            public void onResponse(Call<MemberModel> call, Response<MemberModel> response) {
                if (!Members.this.isAdded()) {
                    return;
                }
                if (!response.isSuccessful()) {
                    Members.this.dismissLoadingDialog();
                    Members.this.rv_members.setVisibility(8);
                    Members.this.tvNoDataFound.setVisibility(0);
                    Toast.makeText(Members.this.getContext(), "Data Not Found", 0).show();
                } else if (response.body() != null && response.body().getStatusCode() != null && response.body().getStatusCode().intValue() == 200) {
                    if (response.body().getData() == null) {
                        Members.this.dismissLoadingDialog();
                        Members.this.rv_members.setVisibility(8);
                        Members.this.tvNoDataFound.setVisibility(0);
                        Toast.makeText(Members.this.getContext(), "Data Not Found", 0).show();
                    } else if (response.body().getData().getCompte().size() > 0) {
                        Members.this.dismissLoadingDialog();
                        Members.this.rv_members.setVisibility(0);
                        Members.this.tvNoDataFound.setVisibility(8);
                        TextView access$400 = Members.this.tvTotalMember;
                        access$400.setText("You added " + response.body().getData().getCompte().size() + " Members");
                        Members.this.memberlist.clear();
                        ArrayList unused = Members.this.memberlist = response.body().getData().getCompte();
                        Adapter_Members unused2 = Members.this.mAdapter = new Adapter_Members(Members.this.getContext(), Members.this.memberlist, Members.this);
                        Members.this.rv_members.setAdapter(Members.this.mAdapter);
                    } else {
                        Members.this.dismissLoadingDialog();
                        Members.this.rv_members.setVisibility(8);
                        Members.this.tvNoDataFound.setVisibility(0);
                        Toast.makeText(Members.this.getContext(), "Data Not Found", 0).show();
                    }
                }
            }

            public void onFailure(Call<MemberModel> call, Throwable t) {
                if (Members.this.isAdded()) {
                    Members.this.dismissLoadingDialog();
                }
                Members.this.rv_members.setVisibility(8);
                Members.this.tvNoDataFound.setVisibility(0);
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

    public void ondelete(int memeberid) {
        PrintStream printStream = System.out;
        printStream.println("adkakda" + memeberid);
        showLoadingDialog();
        ((Apis) new Retrofit.Builder().baseUrl("http://devworktools.fr/contenu/conseiller/").addConverterFactory(GsonConverterFactory.create()).client(new OkHttpClient.Builder().addInterceptor(provideHttpLoggingInterceptor()).readTimeout(120, TimeUnit.SECONDS).writeTimeout(120, TimeUnit.SECONDS).build()).build().create(Apis.class)).deleteMember(String.valueOf(memeberid)).enqueue(new Callback<DeleteMemberModel>() {
            public void onResponse(Call<DeleteMemberModel> call, Response<DeleteMemberModel> response) {
                if (!response.isSuccessful()) {
                    Members.this.dismissLoadingDialog();
                    Toast.makeText(Members.this.getContext(), response.body().getStatusMessage(), 0).show();
                } else if (response.body() == null || response.body().getStatusCode() == null || response.body().getStatusCode().intValue() != 200) {
                    Members.this.dismissLoadingDialog();
                    Toast.makeText(Members.this.getContext(), response.body().getStatusMessage(), 0).show();
                } else {
                    Members.this.dismissLoadingDialog();
                    Toast.makeText(Members.this.getContext(), response.body().getStatusMessage(), 0).show();
                    Members.this.memberPosts();
                }
            }

            public void onFailure(Call<DeleteMemberModel> call, Throwable t) {
                Members.this.dismissLoadingDialog();
                Toast.makeText(Members.this.getContext(), t.getMessage(), 0).show();
            }
        });
    }

    public void onResume() {
        super.onResume();
        init();
    }

    private static HttpLoggingInterceptor provideHttpLoggingInterceptor() {
        return new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            public void log(String message) {
                Log.d("Injector", message);
            }
        });
    }
}
