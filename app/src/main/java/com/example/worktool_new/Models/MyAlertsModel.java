package com.example.worktool_new.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;

public class MyAlertsModel {
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
        @SerializedName("exp")
        @Expose
        private String exp;
        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("lien")
        @Expose
        private String lien;
        @SerializedName("lienDel")
        @Expose
        private String lienDel;
        @SerializedName("sujet")
        @Expose
        private String sujet;

        public Datum() {
        }

        public String getLien() {
            return this.lien;
        }

        public void setLien(String lien2) {
            this.lien = lien2;
        }

        public String getLienDel() {
            return this.lienDel;
        }

        public void setLienDel(String lienDel2) {
            this.lienDel = lienDel2;
        }

        public String getExp() {
            return this.exp;
        }

        public void setExp(String exp2) {
            this.exp = exp2;
        }

        public String getId() {
            return this.id;
        }

        public void setId(String id2) {
            this.id = id2;
        }

        public String getSujet() {
            return this.sujet;
        }

        public void setSujet(String sujet2) {
            this.sujet = sujet2;
        }
    }
}
