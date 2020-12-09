package com.example.worktool_new.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;

public class LoginModel {
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
        @SerializedName("dateConnexion")
        @Expose
        private String dateConnexion;
        @SerializedName("dob")
        @Expose
        private String dob;
        @SerializedName("idCompte")
        @Expose
        private String idCompte;
        @SerializedName("image")
        @Expose
        private String image;
        @SerializedName("localisation")
        @Expose
        private String localisation;
        @SerializedName("nom")
        @Expose
        private String nom;
        @SerializedName("pass")
        @Expose
        private String pass;
        @SerializedName("prenom")
        @Expose
        private String prenom;
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

        public String getType() {
            return this.type;
        }

        public void setType(String type2) {
            this.type = type2;
        }

        public String getDateConnexion() {
            return this.dateConnexion;
        }

        public void setDateConnexion(String dateConnexion2) {
            this.dateConnexion = dateConnexion2;
        }

        public String getLocalisation() {
            return this.localisation;
        }

        public void setLocalisation(String localisation2) {
            this.localisation = localisation2;
        }

        public String getPass() {
            return this.pass;
        }

        public void setPass(String pass2) {
            this.pass = pass2;
        }

        public String getDob() {
            return this.dob;
        }

        public void setDob(String dob2) {
            this.dob = dob2;
        }

        public String getImage() {
            return this.image;
        }

        public void setImage(String image2) {
            this.image = image2;
        }
    }
}
