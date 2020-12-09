package com.example.worktool_new.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;

public class StatsModel {
    @SerializedName("data")
    @Expose
    private ArrayList<Datum> data = null;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("status_code")
    @Expose
    private Integer statusCode;

    public ArrayList<Datum> getData() {
        return this.data;
    }

    public void setData(ArrayList<Datum> data2) {
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

    public class Datum {
        @SerializedName("civilite")
        @Expose
        private String civilite;
        @SerializedName("connection")
        @Expose
        private String connection;
        @SerializedName("contract")
        @Expose
        private String contract;
        @SerializedName("creation")
        @Expose
        private String creation;
        @SerializedName("date")
        @Expose
        private String date;
        @SerializedName("dateConnexion")
        @Expose
        private String dateConnexion;
        @SerializedName("disponibilite")
        @Expose
        private String disponibilite;
        @SerializedName("email")
        @Expose
        private String email;
        @SerializedName("idCompte")
        @Expose
        private String idCompte;
        @SerializedName("idConseiller")
        @Expose
        private String idConseiller;
        @SerializedName("localisation")
        @Expose
        private String localisation;
        @SerializedName("match")
        @Expose
        private String match;
        @SerializedName("nom")
        @Expose
        private String nom;
        @SerializedName("notifier_cv_15")
        @Expose
        private String notifierCv15;
        @SerializedName("notifier_cv_30")
        @Expose
        private String notifierCv30;
        @SerializedName("notifier_cv_45")
        @Expose
        private String notifierCv45;
        @SerializedName("notifier_profil_10")
        @Expose
        private String notifierProfil10;
        @SerializedName("notifier_profil_30")
        @Expose
        private String notifierProfil30;
        @SerializedName("notifier_profil_50")
        @Expose
        private String notifierProfil50;
        @SerializedName("pass")
        @Expose
        private String pass;
        @SerializedName("postuler")
        @Expose
        private String postuler;
        @SerializedName("prenom")
        @Expose
        private String prenom;
        @SerializedName("quota")
        @Expose
        private String quota;
        @SerializedName("redirection")
        @Expose
        private String redirection;
        @SerializedName("savoir")
        @Expose
        private String savoir;
        @SerializedName("telFixe")
        @Expose
        private String telFixe;
        @SerializedName("telPort")
        @Expose
        private String telPort;
        @SerializedName("type")
        @Expose
        private String type;

        public Datum() {
        }

        public String getIdCompte() {
            return this.idCompte;
        }

        public void setIdCompte(String idCompte2) {
            this.idCompte = idCompte2;
        }

        public String getCivilite() {
            return this.civilite;
        }

        public void setCivilite(String civilite2) {
            this.civilite = civilite2;
        }

        public String getNom() {
            return this.nom;
        }

        public void setNom(String nom2) {
            this.nom = nom2;
        }

        public String getPrenom() {
            return this.prenom;
        }

        public void setPrenom(String prenom2) {
            this.prenom = prenom2;
        }

        public String getEmail() {
            return this.email;
        }

        public void setEmail(String email2) {
            this.email = email2;
        }

        public String getPass() {
            return this.pass;
        }

        public void setPass(String pass2) {
            this.pass = pass2;
        }

        public String getTelFixe() {
            return this.telFixe;
        }

        public void setTelFixe(String telFixe2) {
            this.telFixe = telFixe2;
        }

        public String getTelPort() {
            return this.telPort;
        }

        public void setTelPort(String telPort2) {
            this.telPort = telPort2;
        }

        public String getType() {
            return this.type;
        }

        public void setType(String type2) {
            this.type = type2;
        }

        public String getDate() {
            return this.date;
        }

        public void setDate(String date2) {
            this.date = date2;
        }

        public String getDateConnexion() {
            return this.dateConnexion;
        }

        public void setDateConnexion(String dateConnexion2) {
            this.dateConnexion = dateConnexion2;
        }

        public String getIdConseiller() {
            return this.idConseiller;
        }

        public void setIdConseiller(String idConseiller2) {
            this.idConseiller = idConseiller2;
        }

        public String getRedirection() {
            return this.redirection;
        }

        public void setRedirection(String redirection2) {
            this.redirection = redirection2;
        }

        public String getDisponibilite() {
            return this.disponibilite;
        }

        public void setDisponibilite(String disponibilite2) {
            this.disponibilite = disponibilite2;
        }

        public String getLocalisation() {
            return this.localisation;
        }

        public void setLocalisation(String localisation2) {
            this.localisation = localisation2;
        }

        public String getCreation() {
            return this.creation;
        }

        public void setCreation(String creation2) {
            this.creation = creation2;
        }

        public String getQuota() {
            return this.quota;
        }

        public void setQuota(String quota2) {
            this.quota = quota2;
        }

        public String getNotifierProfil10() {
            return this.notifierProfil10;
        }

        public void setNotifierProfil10(String notifierProfil102) {
            this.notifierProfil10 = notifierProfil102;
        }

        public String getNotifierProfil30() {
            return this.notifierProfil30;
        }

        public void setNotifierProfil30(String notifierProfil302) {
            this.notifierProfil30 = notifierProfil302;
        }

        public String getNotifierProfil50() {
            return this.notifierProfil50;
        }

        public void setNotifierProfil50(String notifierProfil502) {
            this.notifierProfil50 = notifierProfil502;
        }

        public String getNotifierCv15() {
            return this.notifierCv15;
        }

        public void setNotifierCv15(String notifierCv152) {
            this.notifierCv15 = notifierCv152;
        }

        public String getNotifierCv30() {
            return this.notifierCv30;
        }

        public void setNotifierCv30(String notifierCv302) {
            this.notifierCv30 = notifierCv302;
        }

        public String getNotifierCv45() {
            return this.notifierCv45;
        }

        public void setNotifierCv45(String notifierCv452) {
            this.notifierCv45 = notifierCv452;
        }

        public String getMatch() {
            return this.match;
        }

        public void setMatch(String match2) {
            this.match = match2;
        }

        public String getContract() {
            return this.contract;
        }

        public void setContract(String contract2) {
            this.contract = contract2;
        }

        public String getConnection() {
            return this.connection;
        }

        public void setConnection(String connection2) {
            this.connection = connection2;
        }

        public String getPostuler() {
            return this.postuler;
        }

        public void setPostuler(String postuler2) {
            this.postuler = postuler2;
        }

        public String getSavoir() {
            return this.savoir;
        }

        public void setSavoir(String savoir2) {
            this.savoir = savoir2;
        }
    }
}
