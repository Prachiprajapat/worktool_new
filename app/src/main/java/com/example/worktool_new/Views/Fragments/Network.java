package com.example.worktool_new.Views.Fragments;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.worktool_new.Adapters.AdapterMyNetwork;
import com.example.worktool_new.Adapters.AdapterSendInvitation;
import com.example.worktool_new.Adapters.Adapter_network_invitation;
import com.example.worktool_new.Models.InvitationRecievedModel;
import com.example.worktool_new.Models.MyNetworkModel;
import com.example.worktool_new.Models.SendInvitationModel;
import com.example.worktool_new.R;
import com.example.worktool_new.Retrofit.Apis;
import com.example.worktool_new.Util.SharedPreference.App;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Network extends Fragment {
    private Button btnSearchInvitationMember;
    /* access modifiers changed from: private */
    public EditText etEmailAddress;
    /* access modifiers changed from: private */
    public EditText etName;
    private EditText etSearchNetwork;
    private LinearLayout llInvitationReceived;
    private LinearLayout llMyNetwork;
    private LinearLayout llSendInvitation;
    /* access modifiers changed from: private */
    public LinearLayout llsendInvitationlayout;
    /* access modifiers changed from: private */
    public Adapter_network_invitation mAdapter;
    /* access modifiers changed from: private */
    public AdapterMyNetwork mNetworkAdapter;
    /* access modifiers changed from: private */
    public AdapterSendInvitation mSendInvitationAdapter;
    ArrayList<MyNetworkModel.Datum> networkList = new ArrayList<>();
    ArrayList<MyNetworkModel.Datum> networkList1 = new ArrayList<>();
    private ProgressDialog progress;
    /* access modifiers changed from: private */
    public RelativeLayout rlSearch;
    /* access modifiers changed from: private */
    public RecyclerView rv_invitation;
    /* access modifiers changed from: private */
    public TextView tvNoDataFound;
    private View view;
    View viewInvitationReceived;
    View viewMyNetwork;
    View viewSendInvitation;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (this.view == null) {
            View inflate = inflater.inflate(R.layout.fragment_network, container, false);
            this.view = inflate;
            this.rv_invitation = (RecyclerView) inflate.findViewById(R.id.rv_invitation);
            this.etName = (EditText) this.view.findViewById(R.id.etName);
            this.etSearchNetwork = (EditText) this.view.findViewById(R.id.etSearchNetwork);
            this.etEmailAddress = (EditText) this.view.findViewById(R.id.etEmailAddress);
            this.tvNoDataFound = (TextView) this.view.findViewById(R.id.tvNoDataFound);
            this.rlSearch = (RelativeLayout) this.view.findViewById(R.id.rlSearch);
            this.btnSearchInvitationMember = (Button) this.view.findViewById(R.id.btnSearchInvitationMember);
            this.viewInvitationReceived = this.view.findViewById(R.id.viewInvitationReceived);
            this.viewMyNetwork = this.view.findViewById(R.id.viewMyNetwork);
            this.viewSendInvitation = this.view.findViewById(R.id.viewSendInvitation);
            this.llInvitationReceived = (LinearLayout) this.view.findViewById(R.id.llInvitationReceived);
            this.llMyNetwork = (LinearLayout) this.view.findViewById(R.id.llMyNetwork);
            this.llSendInvitation = (LinearLayout) this.view.findViewById(R.id.llSendInvitation);
            this.llsendInvitationlayout = (LinearLayout) this.view.findViewById(R.id.llsendInvitationlayout);
            GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2);
            myNetworkPosts();
            this.rv_invitation.setLayoutManager(gridLayoutManager);
            this.viewMyNetwork.setVisibility(0);
            this.llInvitationReceived.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Network.this.rlSearch.setVisibility(8);
                    GridLayoutManager gridLayoutManager = new GridLayoutManager(Network.this.getActivity(), 2);
                    Network.this.invitationRecievedPosts();
                    Network.this.rv_invitation.setLayoutManager(gridLayoutManager);
                    Network.this.viewInvitationReceived.setVisibility(0);
                    Network.this.viewMyNetwork.setVisibility(8);
                    Network.this.viewSendInvitation.setVisibility(8);
                    Network.this.llsendInvitationlayout.setVisibility(8);
                    Network.this.rv_invitation.setVisibility(0);
                }
            });
            this.llMyNetwork.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Network.this.rlSearch.setVisibility(0);
                    GridLayoutManager gridLayoutManager = new GridLayoutManager(Network.this.getActivity(), 2);
                    Network.this.myNetworkPosts();
                    Network.this.rv_invitation.setLayoutManager(gridLayoutManager);
                    Network.this.viewMyNetwork.setVisibility(0);
                    Network.this.viewInvitationReceived.setVisibility(8);
                    Network.this.viewSendInvitation.setVisibility(8);
                    Network.this.llsendInvitationlayout.setVisibility(8);
                    Network.this.rv_invitation.setVisibility(0);
                }
            });
            this.llSendInvitation.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Network.this.rlSearch.setVisibility(8);
                    Network.this.llsendInvitationlayout.setVisibility(0);
                    Network.this.rv_invitation.setVisibility(8);
                    Network.this.tvNoDataFound.setVisibility(8);
                    Network.this.viewMyNetwork.setVisibility(8);
                    Network.this.viewInvitationReceived.setVisibility(8);
                    Network.this.viewSendInvitation.setVisibility(0);
                }
            });
            this.btnSearchInvitationMember.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    String name = Network.this.etName.getText().toString();
                    String email = Network.this.etEmailAddress.getText().toString();
                    if (name.isEmpty() && email.isEmpty()) {
                        Toast.makeText(Network.this.requireContext(), "Please enter name or email address", 0).show();
                        return;
                    }
                    Network.this.rv_invitation.setLayoutManager(new GridLayoutManager(Network.this.getActivity(), 1));
                    Network.this.sendInvitationNetworkPosts(name, email);
                }
            });
            this.etSearchNetwork.addTextChangedListener(new TextWatcher() {
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                }

                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    if (s.toString().length() > 0) {
                        Network.this.networkList1.clear();
                        for (int i = 0; i < Network.this.networkList.size(); i++) {
                            if ((Network.this.networkList.get(i).getPrenom() != null && Network.this.networkList.get(i).getPrenom().toUpperCase().startsWith(s.toString().toUpperCase())) || (Network.this.networkList.get(i).getNom() != null && Network.this.networkList.get(i).getNom().toUpperCase().startsWith(s.toString().toUpperCase()))) {
                                Network.this.networkList1.add(Network.this.networkList.get(i));
                            }
                        }
                        Network.this.mNetworkAdapter.updateList(Network.this.networkList1);
                        return;
                    }
                    if (Network.this.networkList1.size() > 0) {
                        Network.this.networkList1.clear();
                    }
                    Network.this.mNetworkAdapter.updateList(Network.this.networkList);
                }

                public void afterTextChanged(Editable s) {
                }
            });
        }
        return this.view;
    }

    /* access modifiers changed from: private */
    public void sendInvitationNetworkPosts(String name, String email) {
        Map<String, String> params = new HashMap<>();
        params.put("idCompte", App.getAppPreference().getString("LoginId"));
        params.put("nom", name);
        params.put("mail", email);
        showLoadingDialog();
        ((Apis) new Retrofit.Builder().baseUrl("http://devworktools.fr/contenu/conseiller/").addConverterFactory(GsonConverterFactory.create()).build().create(Apis.class)).searchInvitationMember(params).enqueue(new Callback<SendInvitationModel>() {
            public void onResponse(Call<SendInvitationModel> call, Response<SendInvitationModel> response) {
                if (!response.isSuccessful()) {
                    Network.this.dismissLoadingDialog();
                    Network.this.rv_invitation.setVisibility(8);
                    Network.this.tvNoDataFound.setVisibility(0);
                    Toast.makeText(Network.this.getContext(), "Data Not Found", 0).show();
                } else if (response.body() == null || response.body().getStatuscode() == null || response.body().getStatuscode().intValue() != 200) {
                    Network.this.dismissLoadingDialog();
                    Network.this.rv_invitation.setVisibility(8);
                    Network.this.tvNoDataFound.setVisibility(0);
                    Toast.makeText(Network.this.getContext(), "Data Not Found", 0).show();
                } else if (response.body().getData() == null || response.body().getData().isEmpty()) {
                    Network.this.dismissLoadingDialog();
                    Network.this.rv_invitation.setVisibility(8);
                    Network.this.tvNoDataFound.setVisibility(0);
                    Toast.makeText(Network.this.getContext(), "Data Not Found", 0).show();
                } else if (response.body().getData().size() > 0) {
                    Network.this.dismissLoadingDialog();
                    Network.this.etName.setText("");
                    Network.this.etEmailAddress.setText("");
                    Network.this.rv_invitation.setVisibility(0);
                    Network.this.tvNoDataFound.setVisibility(8);
                    AdapterSendInvitation unused = Network.this.mSendInvitationAdapter = new AdapterSendInvitation(Network.this.getActivity(), response.body().getData());
                    Network.this.rv_invitation.setAdapter(Network.this.mSendInvitationAdapter);
                } else {
                    Network.this.dismissLoadingDialog();
                    Network.this.rv_invitation.setVisibility(8);
                    Network.this.tvNoDataFound.setVisibility(0);
                    Toast.makeText(Network.this.getContext(), "Data Not Found", 0).show();
                }
            }

            public void onFailure(Call<SendInvitationModel> call, Throwable t) {
                Network.this.dismissLoadingDialog();
                Network.this.rv_invitation.setVisibility(8);
                Network.this.tvNoDataFound.setVisibility(0);
                Toast.makeText(Network.this.getContext(), t.getLocalizedMessage(), 1).show();
            }
        });
    }

    /* access modifiers changed from: private */
    public void myNetworkPosts() {
        if (isAdded()) {
            this.rlSearch.setVisibility(0);
        }
        showLoadingDialog();
        ((Apis) new Retrofit.Builder().baseUrl("http://devworktools.fr/contenu/conseiller/").addConverterFactory(GsonConverterFactory.create()).build().create(Apis.class)).myNetwork(App.getAppPreference().getString("LoginId")).enqueue(new Callback<MyNetworkModel>() {
            public void onResponse(Call<MyNetworkModel> call, Response<MyNetworkModel> response) {
                if (!response.isSuccessful()) {
                    Network.this.dismissLoadingDialog();
                    Network.this.rv_invitation.setVisibility(8);
                    Network.this.tvNoDataFound.setVisibility(0);
                    Toast.makeText(Network.this.getContext(), "Data Not Found", 0).show();
                } else if (response.body() == null || response.body().getStatusCode() == null || response.body().getStatusCode().intValue() != 200) {
                    Network.this.dismissLoadingDialog();
                    Network.this.rv_invitation.setVisibility(8);
                    Network.this.tvNoDataFound.setVisibility(0);
                    Toast.makeText(Network.this.getContext(), "Data Not Found", 0).show();
                } else if (response.body().getData() == null || response.body().getData().isEmpty()) {
                    Network.this.dismissLoadingDialog();
                    Network.this.rv_invitation.setVisibility(8);
                    Network.this.tvNoDataFound.setVisibility(0);
                    Toast.makeText(Network.this.getContext(), "Data Not Found", 0).show();
                } else if (response.body().getData().size() > 0) {
                    Network.this.dismissLoadingDialog();
                    Network.this.rv_invitation.setVisibility(0);
                    Network.this.tvNoDataFound.setVisibility(8);
                    Network.this.networkList = response.body().getData();
                    AdapterMyNetwork unused = Network.this.mNetworkAdapter = new AdapterMyNetwork(Network.this.getActivity(), Network.this.networkList);
                    Network.this.rv_invitation.setAdapter(Network.this.mNetworkAdapter);
                } else {
                    Network.this.dismissLoadingDialog();
                    Network.this.rv_invitation.setVisibility(8);
                    Network.this.tvNoDataFound.setVisibility(0);
                    Toast.makeText(Network.this.getContext(), "Data Not Found", 0).show();
                }
            }

            public void onFailure(Call<MyNetworkModel> call, Throwable t) {
                Network.this.dismissLoadingDialog();
                Network.this.rv_invitation.setVisibility(8);
                Network.this.tvNoDataFound.setVisibility(0);
                Toast.makeText(Network.this.getContext(), t.getLocalizedMessage(), 1).show();
            }
        });
    }

    /* access modifiers changed from: private */
    public void invitationRecievedPosts() {
        showLoadingDialog();
        ((Apis) new Retrofit.Builder().baseUrl("http://devworktools.fr/contenu/conseiller/").addConverterFactory(GsonConverterFactory.create()).build().create(Apis.class)).getInvitationRecieved(App.getAppPreference().getString("LoginId")).enqueue(new Callback<InvitationRecievedModel>() {
            public void onResponse(Call<InvitationRecievedModel> call, Response<InvitationRecievedModel> response) {
                if (!response.isSuccessful()) {
                    Network.this.dismissLoadingDialog();
                    Network.this.rv_invitation.setVisibility(8);
                    Network.this.tvNoDataFound.setVisibility(0);
                    Toast.makeText(Network.this.getContext(), "Data Not Found", 0).show();
                } else if (response.body() != null && response.body().getStatusCode().intValue() == 200) {
                    if (response.body().getData() == null || response.body().getData().isEmpty()) {
                        Network.this.dismissLoadingDialog();
                        Network.this.rv_invitation.setVisibility(8);
                        Network.this.tvNoDataFound.setVisibility(0);
                        Toast.makeText(Network.this.getContext(), "Data Not Found", 0).show();
                    } else if (response.body().getData().size() > 0) {
                        Network.this.rv_invitation.setVisibility(0);
                        Network.this.tvNoDataFound.setVisibility(8);
                        Network.this.dismissLoadingDialog();
                        Adapter_network_invitation unused = Network.this.mAdapter = new Adapter_network_invitation(Network.this.getActivity(), response.body().getData());
                        Network.this.rv_invitation.setAdapter(Network.this.mAdapter);
                    }
                }
            }

            public void onFailure(Call<InvitationRecievedModel> call, Throwable t) {
                Network.this.dismissLoadingDialog();
                Network.this.rv_invitation.setVisibility(8);
                Network.this.tvNoDataFound.setVisibility(0);
                Toast.makeText(Network.this.getContext(), t.getLocalizedMessage(), 1).show();
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
