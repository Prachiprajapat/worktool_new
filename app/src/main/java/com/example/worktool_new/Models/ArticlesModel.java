package com.example.worktool_new.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;

public class ArticlesModel {
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
        @SerializedName("afficheVideo")
        @Expose
        private String afficheVideo;
        @SerializedName("contenu")
        @Expose
        private String contenu;
        @SerializedName("date")
        @Expose
        private String date;
        @SerializedName("file")
        @Expose
        private String file;
        @SerializedName("idArticle")
        @Expose
        private String idArticle;
        @SerializedName("idCompteAuteur")
        @Expose
        private String idCompteAuteur;
        @SerializedName("idFichier")
        @Expose
        private String idFichier;
        @SerializedName("nom")
        @Expose
        private String nom;
        @SerializedName("prenom")
        @Expose
        private String prenom;
        @SerializedName("resume")
        @Expose
        private String resume;
        @SerializedName("rubrique")
        @Expose
        private String rubrique;
        @SerializedName("statut")
        @Expose
        private String statut;
        @SerializedName("titre")
        @Expose
        private String titre;
        @SerializedName("video")
        @Expose
        private String video;

        public Datum() {
        }

        public String getIdArticle() {
            return this.idArticle;
        }

        public void setIdArticle(String idArticle2) {
            this.idArticle = idArticle2;
        }

        public String getIdCompteAuteur() {
            return this.idCompteAuteur;
        }

        public void setIdCompteAuteur(String idCompteAuteur2) {
            this.idCompteAuteur = idCompteAuteur2;
        }

        public String getTitre() {
            return this.titre;
        }

        public void setTitre(String titre2) {
            this.titre = titre2;
        }

        public String getRubrique() {
            return this.rubrique;
        }

        public void setRubrique(String rubrique2) {
            this.rubrique = rubrique2;
        }

        public String getResume() {
            return this.resume;
        }

        public void setResume(String resume2) {
            this.resume = resume2;
        }

        public String getContenu() {
            return this.contenu;
        }

        public void setContenu(String contenu2) {
            this.contenu = contenu2;
        }

        public String getIdFichier() {
            return this.idFichier;
        }

        public void setIdFichier(String idFichier2) {
            this.idFichier = idFichier2;
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

        public String getVideo() {
            return this.video;
        }

        public void setVideo(String video2) {
            this.video = video2;
        }

        public String getAfficheVideo() {
            return this.afficheVideo;
        }

        public void setAfficheVideo(String afficheVideo2) {
            this.afficheVideo = afficheVideo2;
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

        public String getFile() {
            return this.file;
        }

        public void setFile(String file2) {
            this.file = file2;
        }
    }
}
