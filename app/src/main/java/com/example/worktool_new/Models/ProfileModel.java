package com.example.worktool_new.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProfileModel {
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
        @SerializedName("adresse")
        @Expose
        private String adresse;
        @SerializedName("civ")
        @Expose
        private String civ;
        @SerializedName("cp")
        @Expose
        private String cp;
        @SerializedName("dannee")
        @Expose
        private String dannee;
        @SerializedName("date")
        @Expose
        private String date;
        @SerializedName("desc")
        @Expose
        private String desc;
        @SerializedName("djour")
        @Expose
        private String djour;
        @SerializedName("dmois")
        @Expose
        private String dmois;
        @SerializedName("image")
        @Expose
        private String image;
        @SerializedName("mail")
        @Expose
        private String mail;
        @SerializedName("nom")
        @Expose
        private String nom;
        @SerializedName("prenom")
        @Expose
        private String prenom;
        @SerializedName("telfixe")
        @Expose
        private String telfixe;
        @SerializedName("telport")
        @Expose
        private String telport;
        @SerializedName("ville")
        @Expose
        private String ville;

        public Data() {
        }

        public String getDesc() {
            return this.desc;
        }

        public void setDesc(String desc2) {
            this.desc = desc2;
        }

        public String getVille() {
            return this.ville;
        }

        public void setVille(String ville2) {
            this.ville = ville2;
        }

        public String getCp() {
            return this.cp;
        }

        public void setCp(String cp2) {
            this.cp = cp2;
        }

        public String getAdresse() {
            return this.adresse;
        }

        public void setAdresse(String adresse2) {
            this.adresse = adresse2;
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

        public String getMail() {
            return this.mail;
        }

        public void setMail(String mail2) {
            this.mail = mail2;
        }

        public String getTelfixe() {
            return this.telfixe;
        }

        public void setTelfixe(String telfixe2) {
            this.telfixe = telfixe2;
        }

        public String getTelport() {
            return this.telport;
        }

        public void setTelport(String telport2) {
            this.telport = telport2;
        }

        public String getDate() {
            return this.date;
        }

        public void setDate(String date2) {
            this.date = date2;
        }

        public String getCiv() {
            return this.civ;
        }

        public void setCiv(String civ2) {
            this.civ = civ2;
        }

        public String getDannee() {
            return this.dannee;
        }

        public void setDannee(String dannee2) {
            this.dannee = dannee2;
        }

        public String getDmois() {
            return this.dmois;
        }

        public void setDmois(String dmois2) {
            this.dmois = dmois2;
        }

        public String getDjour() {
            return this.djour;
        }

        public void setDjour(String djour2) {
            this.djour = djour2;
        }

        public String getImage() {
            return this.image;
        }

        public void setImage(String image2) {
            this.image = image2;
        }
    }
}
