package com.example.worktool_new.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NewCvModel {
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
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("time")
    @Expose
    private Object time;
    @SerializedName("titre")
    @Expose
    private Object titre;
    @SerializedName("typecv")
    @Expose
    private String typecv;

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

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status2) {
        this.status = status2;
    }
}
