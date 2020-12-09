package com.example.worktool_new.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;

public class MyCVModel {
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
        @SerializedName("disponibilite")
        @Expose
        private Object disponibilite;
        @SerializedName("fichier")
        @Expose
        private String fichier;
        @SerializedName("idCvliste")
        @Expose
        private String idCvliste;
        @SerializedName("nom")
        @Expose
        private String nom;
        @SerializedName("prenom")
        @Expose
        private String prenom;
        @SerializedName("time")
        @Expose
        private Object time;
        @SerializedName("titre")
        @Expose
        private Object titre;
        @SerializedName("typecv")
        @Expose
        private String typecv;

        public Datum() {
        }

        public Object getDisponibilite() {
            return this.disponibilite;
        }

        public void setDisponibilite(Object disponibilite2) {
            this.disponibilite = disponibilite2;
        }

        public Object getTitre() {
            return this.titre;
        }

        public void setTitre(Object titre2) {
            this.titre = titre2;
        }

        public Object getTime() {
            return this.time;
        }

        public void setTime(Object time2) {
            this.time = time2;
        }

        public String getTypecv() {
            return this.typecv;
        }

        public void setTypecv(String typecv2) {
            this.typecv = typecv2;
        }

        public String getFichier() {
            return this.fichier;
        }

        public void setFichier(String fichier2) {
            this.fichier = fichier2;
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

        public String getIdCvliste() {
            return this.idCvliste;
        }

        public void setIdCvliste(String idCvliste2) {
            this.idCvliste = idCvliste2;
        }
    }
}
