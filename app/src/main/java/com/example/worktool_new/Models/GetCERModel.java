package com.example.worktool_new.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetCERModel {
    @SerializedName("data")
    @Expose
    private Data data;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("status_code")
    @Expose
    private Integer statusCode;

    public Data getData() {
        return this.data;
    }

    public void setData(Data data2) {
        this.data = data2;
    }

    public Integer getStatusCode() {
        return this.statusCode;
    }

    public void setStatusCode(Integer statusCode2) {
        this.statusCode = statusCode2;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message2) {
        this.message = message2;
    }

    public class Data {
        @SerializedName("ajourne")
        @Expose
        private String ajourne;
        @SerializedName("association")
        @Expose
        private String association;
        @SerializedName("atout_env_social")
        @Expose
        private String atoutEnvSocial;
        @SerializedName("atout_logement")
        @Expose
        private String atoutLogement;
        @SerializedName("atout_mobilite")
        @Expose
        private String atoutMobilite;
        @SerializedName("atout_sante")
        @Expose
        private String atoutSante;
        @SerializedName("atout_situation_perso")
        @Expose
        private String atoutSituationPerso;
        @SerializedName("benevolat")
        @Expose
        private String benevolat;
        @SerializedName("besoin")
        @Expose
        private String besoin;
        @SerializedName("ccas")
        @Expose
        private String ccas;
        @SerializedName("commentaire")
        @Expose
        private Object commentaire;
        @SerializedName("compte_id")
        @Expose
        private String compteId;
        @SerializedName("conseiller_id")
        @Expose
        private String conseillerId;
        @SerializedName("contrat")
        @Expose
        private String contrat;
        @SerializedName("date")
        @Expose
        private String date;
        @SerializedName("dure_validation")
        @Expose
        private String dureValidation;
        @SerializedName("echeance_benef")
        @Expose
        private String echeanceBenef;
        @SerializedName("echeance_ref")
        @Expose
        private String echeanceRef;
        @SerializedName("emploi")
        @Expose
        private String emploi;
        @SerializedName("engagement_benef")
        @Expose
        private String engagementBenef;
        @SerializedName("engagement_ref")
        @Expose
        private String engagementRef;
        @SerializedName("formation")
        @Expose
        private String formation;
        @SerializedName("frein_env_social")
        @Expose
        private String freinEnvSocial;
        @SerializedName("frein_logement")
        @Expose
        private String freinLogement;
        @SerializedName("frein_mobilite")
        @Expose
        private String freinMobilite;
        @SerializedName("frein_sante")
        @Expose
        private String freinSante;
        @SerializedName("frein_situation_perso")
        @Expose
        private String freinSituationPerso;
        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("insertion")
        @Expose
        private String insertion;
        @SerializedName("mds")
        @Expose
        private String mds;
        @SerializedName("motif")
        @Expose
        private String motif;
        @SerializedName("motif_orientation")
        @Expose
        private Object motifOrientation;
        @SerializedName("next_rdv")
        @Expose
        private String nextRdv;
        @SerializedName("preconisation")
        @Expose
        private String preconisation;
        @SerializedName("signature_conseiller")
        @Expose
        private String signatureConseiller;
        @SerializedName("signature_jeune")
        @Expose
        private String signatureJeune;
        @SerializedName("situation_actuel")
        @Expose
        private String situationActuel;
        @SerializedName("validite")
        @Expose
        private String validite;

        public Data() {
        }

        public String getId() {
            return this.id;
        }

        public void setId(String id2) {
            this.id = id2;
        }

        public String getCompteId() {
            return this.compteId;
        }

        public void setCompteId(String compteId2) {
            this.compteId = compteId2;
        }

        public String getConseillerId() {
            return this.conseillerId;
        }

        public void setConseillerId(String conseillerId2) {
            this.conseillerId = conseillerId2;
        }

        public String getEmploi() {
            return this.emploi;
        }

        public void setEmploi(String emploi2) {
            this.emploi = emploi2;
        }

        public String getFormation() {
            return this.formation;
        }

        public void setFormation(String formation2) {
            this.formation = formation2;
        }

        public String getBenevolat() {
            return this.benevolat;
        }

        public void setBenevolat(String benevolat2) {
            this.benevolat = benevolat2;
        }

        public String getInsertion() {
            return this.insertion;
        }

        public void setInsertion(String insertion2) {
            this.insertion = insertion2;
        }

        public Object getCommentaire() {
            return this.commentaire;
        }

        public void setCommentaire(Object commentaire2) {
            this.commentaire = commentaire2;
        }

        public String getDate() {
            return this.date;
        }

        public void setDate(String date2) {
            this.date = date2;
        }

        public String getMotif() {
            return this.motif;
        }

        public void setMotif(String motif2) {
            this.motif = motif2;
        }

        public String getAtoutSante() {
            return this.atoutSante;
        }

        public void setAtoutSante(String atoutSante2) {
            this.atoutSante = atoutSante2;
        }

        public String getAtoutMobilite() {
            return this.atoutMobilite;
        }

        public void setAtoutMobilite(String atoutMobilite2) {
            this.atoutMobilite = atoutMobilite2;
        }

        public String getAtoutLogement() {
            return this.atoutLogement;
        }

        public void setAtoutLogement(String atoutLogement2) {
            this.atoutLogement = atoutLogement2;
        }

        public String getAtoutEnvSocial() {
            return this.atoutEnvSocial;
        }

        public void setAtoutEnvSocial(String atoutEnvSocial2) {
            this.atoutEnvSocial = atoutEnvSocial2;
        }

        public String getAtoutSituationPerso() {
            return this.atoutSituationPerso;
        }

        public void setAtoutSituationPerso(String atoutSituationPerso2) {
            this.atoutSituationPerso = atoutSituationPerso2;
        }

        public String getFreinSante() {
            return this.freinSante;
        }

        public void setFreinSante(String freinSante2) {
            this.freinSante = freinSante2;
        }

        public String getFreinMobilite() {
            return this.freinMobilite;
        }

        public void setFreinMobilite(String freinMobilite2) {
            this.freinMobilite = freinMobilite2;
        }

        public String getFreinLogement() {
            return this.freinLogement;
        }

        public void setFreinLogement(String freinLogement2) {
            this.freinLogement = freinLogement2;
        }

        public String getFreinEnvSocial() {
            return this.freinEnvSocial;
        }

        public void setFreinEnvSocial(String freinEnvSocial2) {
            this.freinEnvSocial = freinEnvSocial2;
        }

        public String getFreinSituationPerso() {
            return this.freinSituationPerso;
        }

        public void setFreinSituationPerso(String freinSituationPerso2) {
            this.freinSituationPerso = freinSituationPerso2;
        }

        public String getSituationActuel() {
            return this.situationActuel;
        }

        public void setSituationActuel(String situationActuel2) {
            this.situationActuel = situationActuel2;
        }

        public String getBesoin() {
            return this.besoin;
        }

        public void setBesoin(String besoin2) {
            this.besoin = besoin2;
        }

        public String getEngagementBenef() {
            return this.engagementBenef;
        }

        public void setEngagementBenef(String engagementBenef2) {
            this.engagementBenef = engagementBenef2;
        }

        public String getEcheanceBenef() {
            return this.echeanceBenef;
        }

        public void setEcheanceBenef(String echeanceBenef2) {
            this.echeanceBenef = echeanceBenef2;
        }

        public String getEngagementRef() {
            return this.engagementRef;
        }

        public void setEngagementRef(String engagementRef2) {
            this.engagementRef = engagementRef2;
        }

        public String getEcheanceRef() {
            return this.echeanceRef;
        }

        public void setEcheanceRef(String echeanceRef2) {
            this.echeanceRef = echeanceRef2;
        }

        public String getValidite() {
            return this.validite;
        }

        public void setValidite(String validite2) {
            this.validite = validite2;
        }

        public String getAjourne() {
            return this.ajourne;
        }

        public void setAjourne(String ajourne2) {
            this.ajourne = ajourne2;
        }

        public String getPreconisation() {
            return this.preconisation;
        }

        public void setPreconisation(String preconisation2) {
            this.preconisation = preconisation2;
        }

        public Object getMotifOrientation() {
            return this.motifOrientation;
        }

        public void setMotifOrientation(Object motifOrientation2) {
            this.motifOrientation = motifOrientation2;
        }

        public String getNextRdv() {
            return this.nextRdv;
        }

        public void setNextRdv(String nextRdv2) {
            this.nextRdv = nextRdv2;
        }

        public String getContrat() {
            return this.contrat;
        }

        public void setContrat(String contrat2) {
            this.contrat = contrat2;
        }

        public String getMds() {
            return this.mds;
        }

        public void setMds(String mds2) {
            this.mds = mds2;
        }

        public String getCcas() {
            return this.ccas;
        }

        public void setCcas(String ccas2) {
            this.ccas = ccas2;
        }

        public String getAssociation() {
            return this.association;
        }

        public void setAssociation(String association2) {
            this.association = association2;
        }

        public String getDureValidation() {
            return this.dureValidation;
        }

        public void setDureValidation(String dureValidation2) {
            this.dureValidation = dureValidation2;
        }

        public String getSignatureConseiller() {
            return this.signatureConseiller;
        }

        public void setSignatureConseiller(String signatureConseiller2) {
            this.signatureConseiller = signatureConseiller2;
        }

        public String getSignatureJeune() {
            return this.signatureJeune;
        }

        public void setSignatureJeune(String signatureJeune2) {
            this.signatureJeune = signatureJeune2;
        }
    }
}
