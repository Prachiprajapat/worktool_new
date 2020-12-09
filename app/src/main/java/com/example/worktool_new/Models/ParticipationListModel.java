package com.example.worktool_new.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class ParticipationListModel {
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

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message2) {
        this.message = message2;
    }

    public Integer getStatusCode() {
        return this.statusCode;
    }

    public void setStatusCode(Integer statusCode2) {
        this.statusCode = statusCode2;
    }

    public class Datum {
        @SerializedName("adresse")
        @Expose
        private String adresse;

        public List[] getData() {
            return data;
        }

        public void setData(List[] data) {
            this.data = data;
        }

        @SerializedName("data")
        @Expose
        private List[] data = null;
        @SerializedName("caf")
        @Expose
        private Object caf;
        @SerializedName("cp")
        @Expose
        private String cp;
        @SerializedName("description")
        @Expose
        private String description;
        @SerializedName("domaine")
        @Expose
        private String domaine;
        @SerializedName("formation")
        @Expose
        private String formation;
        @SerializedName("idCompte")
        @Expose
        private String idCompte;
        @SerializedName("image")
        @Expose
        private String image;
        @SerializedName("situation")
        @Expose
        private Object situation;
        @SerializedName("vill")
        @Expose
        private String ville;
        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("email")
        @Expose
        private String email;

        public Datum() {
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getIdCompte() {
            return this.idCompte;
        }

        public void setIdCompte(String idCompte2) {
            this.idCompte = idCompte2;
        }

        public String getImage() {
            return this.image;
        }

        public void setImage(String image2) {
            this.image = image2;
        }

        public String getDescription() {
            return this.description;
        }

        public void setDescription(String description2) {
            this.description = description2;
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

        public String getFormation() {
            return this.formation;
        }

        public void setFormation(String formation2) {
            this.formation = formation2;
        }

        public String getDomaine() {
            return this.domaine;
        }

        public void setDomaine(String domaine2) {
            this.domaine = domaine2;
        }

        public Object getSituation() {
            return this.situation;
        }

        public void setSituation(Object situation2) {
            this.situation = situation2;
        }

        public Object getCaf() {
            return this.caf;
        }

        public void setCaf(Object caf2) {
            this.caf = caf2;
        }
    }
}
