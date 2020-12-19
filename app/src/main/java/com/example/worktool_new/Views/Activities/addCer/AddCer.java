package com.example.worktool_new.Views.Activities.addCer;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.worktool_new.Models.GetCERModel;
import com.example.worktool_new.Models.GetEngagementModel;
import com.example.worktool_new.R;
import com.example.worktool_new.Retrofit.Apis;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AddCer extends AppCompatActivity {
    String association;
    LinearLayout atouts;
    LinearLayout besoin;
    LinearLayout cardonnes;
    String ccas;
    LinearLayout decsions;
    LinearLayout duree;
    LinearLayout enagagements;
    ArrayList<GetEngagementModel.Datum> engagementList;
    String etBeneficaire;
    String etBeneficaireDate;
    String etBesoin;
    String etContratAjournePour;
    String etContratValue;
    String etDureeValidationDuContrat;
    String etEnvironmentSocial;
    String etEnvironmentSocialFreins;
    String etLogement;
    String etLogementFreins;
    String etMobLite;
    String etMobLiteFreins;
    String etNumeroDuContrat;
    String etPrenocisations;
    String etReferent;
    String etReferentDate;
    String etSante;
    String etSanteFreins;
    String etSituation;
    String etSituationActuelle;
    String etSituationFreins;
    String etmotifs;
    String etprochainBeneficiary;
    LinearLayout friens;
    String mds;
    String memberId;
    LinearLayout motifis;
    LinearLayout prochain;
    private ProgressDialog progress;
    LinearLayout signature;
    String signature_pad_benefecire;
    String signature_pad_consellor;
    LinearLayout situation;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) R.layout.add_a_cer);
        init();
    }

    private void init() {
        this.cardonnes = (LinearLayout) findViewById(R.id.ll_cardonness);
        this.motifis = (LinearLayout) findViewById(R.id.ll_motifs);
        this.atouts = (LinearLayout) findViewById(R.id.ll_Atouts);
        this.friens = (LinearLayout) findViewById(R.id.ll_friens);
        this.situation = (LinearLayout) findViewById(R.id.ll_situation);
        this.besoin = (LinearLayout) findViewById(R.id.ll_besoin);
        this.enagagements = (LinearLayout) findViewById(R.id.ll_engagements);
        this.prochain = (LinearLayout) findViewById(R.id.ll_prochains);
        this.decsions = (LinearLayout) findViewById(R.id.ll_deciosons);
        this.duree = (LinearLayout) findViewById(R.id.ll_dureee);
        this.signature = (LinearLayout) findViewById(R.id.ll_signature);
        String Id = getIntent().getStringExtra("MemberId");
        final String memberId2 = getIntent().getStringExtra("MemberId");
        Log.i("mds",getIntent().getStringExtra("MemberId")+"");
        this.engagementList = new ArrayList<>();
        if (Id != null && !Id.isEmpty()) {
            getCERData(Id);
        }
        this.cardonnes.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(AddCer.this, Cardonnes_Activity.class);
                intent.putExtra("memberId", memberId2);
                intent.putExtra("mds", AddCer.this.mds);
                Log.i("mds",AddCer.this.mds+"");
                intent.putExtra("ccas", AddCer.this.ccas);
                intent.putExtra("association", AddCer.this.association);
                AddCer.this.startActivity(intent);
            }
        });
        this.motifis.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(AddCer.this, Motifs_Activity.class);
                intent.putExtra("memberId", memberId2);
                intent.putExtra("etmotifs", AddCer.this.etmotifs);
                AddCer.this.startActivity(intent);
            }
        });
        this.atouts.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(AddCer.this, Atouts_Activity.class);
                intent.putExtra("memberId", memberId2);
                intent.putExtra("etSante", AddCer.this.etSante);
                intent.putExtra("etMobLite", AddCer.this.etMobLite);
                intent.putExtra("etLogement", AddCer.this.etLogement);
                intent.putExtra("etEnvironmentSocial", AddCer.this.etEnvironmentSocial);
                intent.putExtra("etSituation", AddCer.this.etSituation);
                AddCer.this.startActivity(intent);
            }
        });
        this.friens.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(AddCer.this, Freins_Activity.class);
                intent.putExtra("memberId", memberId2);
                intent.putExtra("etSante", AddCer.this.etSanteFreins);
                intent.putExtra("etMobLite", AddCer.this.etMobLiteFreins);
                intent.putExtra("etLogement", AddCer.this.etLogementFreins);
                intent.putExtra("etEnvironmentSocial", AddCer.this.etEnvironmentSocialFreins);
                intent.putExtra("etSituation", AddCer.this.etSituationFreins);
                AddCer.this.startActivity(intent);
            }
        });
        this.situation.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(AddCer.this, Situation_Activity.class);
                intent.putExtra("memberId", memberId2);
                intent.putExtra("etSituationActuelles", AddCer.this.etSituationActuelle);
                AddCer.this.startActivity(intent);
            }
        });
        this.besoin.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(AddCer.this, Besoin_Activity.class);
                intent.putExtra("memberId", memberId2);
                intent.putExtra("etBesoins", AddCer.this.etBesoin);
                AddCer.this.startActivity(intent);
            }
        });
        this.enagagements.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(AddCer.this, Engagemnets_Activity.class);
                intent.putExtra("memberId", memberId2);
                intent.putExtra("engagementList", AddCer.this.engagementList);
                AddCer.this.startActivity(intent);
            }
        });
        this.prochain.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(AddCer.this, Prochains_Activity.class);
                intent.putExtra("memberId", memberId2);
                intent.putExtra("etprochainBeneficiary", AddCer.this.etprochainBeneficiary);
                AddCer.this.startActivity(intent);
            }
        });
        this.decsions.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(AddCer.this, Decisions_Activity.class);
                intent.putExtra("memberId", memberId2);
                intent.putExtra("etContratValue", AddCer.this.etContratValue);
                intent.putExtra("etContratAjournePour", AddCer.this.etContratAjournePour);
                intent.putExtra("etPrenocisations", AddCer.this.etPrenocisations);
                AddCer.this.startActivity(intent);
            }
        });
        this.duree.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(AddCer.this, Duree_Activity.class);
                intent.putExtra("memberId", memberId2);
                intent.putExtra("etDureeValidationDuContrat", AddCer.this.etDureeValidationDuContrat);
                AddCer.this.startActivity(intent);
            }
        });
        this.signature.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(AddCer.this, Signature_Activity.class);
                intent.putExtra("memberId", memberId2);
                intent.putExtra("etNumeroDuContrat", AddCer.this.etNumeroDuContrat);
                intent.putExtra("signature_pad_consellor", AddCer.this.signature_pad_consellor);
                intent.putExtra("signature_pad_benefecire", AddCer.this.signature_pad_benefecire);
                AddCer.this.startActivity(intent);
            }
        });
    }

    public void getCERData(String id) {
        showLoadingDialog();
        ((Apis) new Retrofit.Builder().baseUrl("http://devworktools.fr/contenu/conseiller/").addConverterFactory(GsonConverterFactory.create()).client(new OkHttpClient.Builder().addInterceptor(provideHttpLoggingInterceptor()).readTimeout(120, TimeUnit.SECONDS).writeTimeout(120, TimeUnit.SECONDS).build()).build().create(Apis.class)).getCerData(id).enqueue(new Callback<GetCERModel>() {
            public void onResponse(Call<GetCERModel> call, Response<GetCERModel> response) {
                if (!response.isSuccessful()) {
                    AddCer.this.dismissLoadingDialog();
                    Toast.makeText(AddCer.this, "Data Not Found", 0).show();
                } else if (response.body() != null && response.body().getData() != null) {
                    AddCer.this.dismissLoadingDialog();
                    AddCer.this.memberId = response.body().getData().getId();
                    AddCer.this.mds = response.body().getData().getMds();
                    AddCer.this.ccas = response.body().getData().getCcas();
                    AddCer.this.association = response.body().getData().getAssociation();
                    AddCer.this.etmotifs = response.body().getData().getMotif();
                    AddCer.this.etSante = response.body().getData().getAtoutSante();
                    AddCer.this.etMobLite = response.body().getData().getAtoutMobilite();
                    AddCer.this.etLogement = response.body().getData().getAtoutLogement();
                    AddCer.this.etEnvironmentSocial = response.body().getData().getAtoutEnvSocial();
                    AddCer.this.etSituation = response.body().getData().getAtoutSituationPerso();
                    AddCer.this.etSanteFreins = response.body().getData().getFreinSante();
                    AddCer.this.etMobLiteFreins = response.body().getData().getFreinMobilite();
                    AddCer.this.etLogementFreins = response.body().getData().getFreinLogement();
                    AddCer.this.etEnvironmentSocialFreins = response.body().getData().getFreinEnvSocial();
                    AddCer.this.etSituationFreins = response.body().getData().getFreinSituationPerso();
                    AddCer.this.etSituationActuelle = response.body().getData().getAtoutSituationPerso();
                    AddCer.this.etBesoin = response.body().getData().getBesoin();
                    AddCer.this.etprochainBeneficiary = response.body().getData().getNextRdv();
                    AddCer.this.etContratValue = response.body().getData().getContrat();
                    AddCer.this.etContratAjournePour = response.body().getData().getAjourne();
                    AddCer.this.etPrenocisations = response.body().getData().getPreconisation();
                    AddCer.this.etDureeValidationDuContrat = response.body().getData().getDureValidation();
                    AddCer.this.signature_pad_consellor = response.body().getData().getSignatureConseiller();
                    AddCer.this.signature_pad_benefecire = response.body().getData().getSignatureJeune();
                    String idCer = response.body().getData().getId();
                    if (idCer != null && !idCer.isEmpty()) {
                        AddCer.this.getEngagementData(idCer);
                    }
                }
            }

            public void onFailure(Call<GetCERModel> call, Throwable t) {
                AddCer.this.dismissLoadingDialog();
                Toast.makeText(AddCer.this, t.getLocalizedMessage(), 1).show();
            }
        });
    }

    public void getEngagementData(String id) {
        showLoadingDialog();
        ((Apis) new Retrofit.Builder().baseUrl("http://devworktools.fr/contenu/conseiller/").addConverterFactory(GsonConverterFactory.create()).build().create(Apis.class)).getEngagementData(id).enqueue(new Callback<GetEngagementModel>() {
            public void onResponse(Call<GetEngagementModel> call, Response<GetEngagementModel> response) {
                if (!response.isSuccessful()) {
                    AddCer.this.dismissLoadingDialog();
                    Toast.makeText(AddCer.this, "Data Not Found", 0).show();
                } else if (response.body() == null || response.body().getData().isEmpty()) {
                    AddCer.this.dismissLoadingDialog();
                    Toast.makeText(AddCer.this, response.body().getMessage(), 0).show();
                } else if (response.body().getStatusCode().intValue() == 200) {
                    AddCer.this.engagementList = response.body().getData();
                    AddCer.this.dismissLoadingDialog();
                } else {
                    AddCer.this.dismissLoadingDialog();
                    Toast.makeText(AddCer.this, response.body().getMessage(), 0).show();
                }
            }

            public void onFailure(Call<GetEngagementModel> call, Throwable t) {
                AddCer.this.dismissLoadingDialog();
                Toast.makeText(AddCer.this, t.getLocalizedMessage(), 1).show();
            }
        });
    }

    public void showLoadingDialog() {
        if (this.progress == null) {
            ProgressDialog progressDialog = new ProgressDialog(this);
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

    private static HttpLoggingInterceptor provideHttpLoggingInterceptor() {
        return new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            public void log(String message) {
                Log.d("Injector", message);
            }
        });
    }
}
