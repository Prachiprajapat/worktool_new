package com.example.worktool_new.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class ForwardMailModel {

    @SerializedName("data")
    @Expose
    private ArrayList<MailDatum> data = null;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("status_code")
    @Expose
    private Integer statusCode;

    public ArrayList<MailDatum> getData() {
        return this.data;
    }

    public void setData(ArrayList<MailDatum> data2) {
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

    public class MailDatum {
        @SerializedName("idCompte")
        @Expose
        private String idCompte;
        @SerializedName("type")
        @Expose
        private String type;
        @SerializedName("nom")
        @Expose
        private String nom;
        @SerializedName("prenom")
        @Expose
        private String prenom;

        public String getType() {
            return this.type;
        }

        public void setType(String type2) {
            this.type = type2;
        }

        public String getNom() {
            return nom;
        }

        public void setNom(String nom) {
            this.nom = nom;
        }

        public String getPrenom() {
            return prenom;
        }

        public void setPrenom(String prenom) {
            this.prenom = prenom;
        }

        public String getIdCompte() {
            return idCompte;
        }

        public void setIdCompte(String idCompte) {
            this.idCompte = idCompte;
        }
    }
}
