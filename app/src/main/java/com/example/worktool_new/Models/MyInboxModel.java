package com.example.worktool_new.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import java.util.List;

public class MyInboxModel {
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
        @SerializedName("date")
        @Expose
        private String date;
        @SerializedName("file")
        @Expose
        private List<String> file = null;
        @SerializedName("idCompteDest")
        @Expose
        private String idCompteDest;
        @SerializedName("idCompteExp")
        @Expose
        private String idCompteExp;
        @SerializedName("idFichier")
        @Expose
        private String idFichier;
        @SerializedName("idFichier2")
        @Expose
        private String idFichier2;
        @SerializedName("idFichier3")
        @Expose
        private String idFichier3;
        @SerializedName("idMessage")
        @Expose
        private String idMessage;
        @SerializedName("image")
        @Expose
        private Object image;
        @SerializedName("message")
        @Expose
        private String message;
        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("statut")
        @Expose
        private String statut;
        @SerializedName("sujet")
        @Expose
        private String sujet;

        public Datum() {
        }

        public String getIdMessage() {
            return this.idMessage;
        }

        public void setIdMessage(String idMessage2) {
            this.idMessage = idMessage2;
        }

        public String getIdCompteExp() {
            return this.idCompteExp;
        }

        public void setIdCompteExp(String idCompteExp2) {
            this.idCompteExp = idCompteExp2;
        }

        public String getIdCompteDest() {
            return this.idCompteDest;
        }

        public void setIdCompteDest(String idCompteDest2) {
            this.idCompteDest = idCompteDest2;
        }

        public String getSujet() {
            return this.sujet;
        }

        public void setSujet(String sujet2) {
            this.sujet = sujet2;
        }

        public String getMessage() {
            return this.message;
        }

        public void setMessage(String message2) {
            this.message = message2;
        }

        public String getIdFichier() {
            return this.idFichier;
        }

        public void setIdFichier(String idFichier4) {
            this.idFichier = idFichier4;
        }

        public String getIdFichier2() {
            return this.idFichier2;
        }

        public void setIdFichier2(String idFichier22) {
            this.idFichier2 = idFichier22;
        }

        public String getIdFichier3() {
            return this.idFichier3;
        }

        public void setIdFichier3(String idFichier32) {
            this.idFichier3 = idFichier32;
        }

        public String getStatut() {
            return this.statut;
        }

        public void setStatut(String statut2) {
            this.statut = statut2;
        }

        public String getDate() {
            return this.date;
        }

        public void setDate(String date2) {
            this.date = date2;
        }

        public List<String> getFile() {
            return this.file;
        }

        public void setFile(List<String> file2) {
            this.file = file2;
        }

        public String getName() {
            return this.name;
        }

        public void setName(String name2) {
            this.name = name2;
        }

        public Object getImage() {
            return this.image;
        }

        public void setImage(Object image2) {
            this.image = image2;
        }
    }
}