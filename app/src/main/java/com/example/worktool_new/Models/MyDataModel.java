package com.example.worktool_new.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;

public class MyDataModel {
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
        @SerializedName("defaut")
        @Expose
        private Object defaut;
        @SerializedName("fichier")
        @Expose
        private String fichier;
        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("idCompte")
        @Expose
        private String idCompte;
        @SerializedName("idFichier")
        @Expose
        private String idFichier;
        @SerializedName("image")
        @Expose
        private String image;
        @SerializedName("lettre_motivation")
        @Expose
        private String lettreMotivation;
        @SerializedName("table")
        @Expose
        private String table;
        @SerializedName("type")
        @Expose
        private String type;

        public Datum() {
        }

        public String getIdFichier() {
            return this.idFichier;
        }

        public void setIdFichier(String idFichier2) {
            this.idFichier = idFichier2;
        }

        public String getIdCompte() {
            return this.idCompte;
        }

        public void setIdCompte(String idCompte2) {
            this.idCompte = idCompte2;
        }

        public String getFichier() {
            return this.fichier;
        }

        public void setFichier(String fichier2) {
            this.fichier = fichier2;
        }

        public String getType() {
            return this.type;
        }

        public void setType(String type2) {
            this.type = type2;
        }

        public Object getDefaut() {
            return this.defaut;
        }

        public void setDefaut(Object defaut2) {
            this.defaut = defaut2;
        }

        public String getLettreMotivation() {
            return this.lettreMotivation;
        }

        public void setLettreMotivation(String lettreMotivation2) {
            this.lettreMotivation = lettreMotivation2;
        }

        public String getTable() {
            return this.table;
        }

        public void setTable(String table2) {
            this.table = table2;
        }

        public String getId() {
            return this.id;
        }

        public void setId(String id2) {
            this.id = id2;
        }

        public String getImage() {
            return this.image;
        }

        public void setImage(String image2) {
            this.image = image2;
        }
    }
}
