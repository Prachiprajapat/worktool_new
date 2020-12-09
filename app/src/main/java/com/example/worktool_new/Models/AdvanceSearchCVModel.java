package com.example.worktool_new.Models;

import java.io.Serializable;
import java.util.ArrayList;

public class AdvanceSearchCVModel implements Serializable {
    ArrayList<Body> data;
    String message;
    Integer status_code;

    public ArrayList<Body> getData() {
        return this.data;
    }

    public void setData(ArrayList<Body> data2) {
        this.data = data2;
    }

    public Integer getStatus_code() {
        return this.status_code;
    }

    public void setStatus_code(Integer status_code2) {
        this.status_code = status_code2;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message2) {
        this.message = message2;
    }

    public class Body implements Serializable {
        Object disponibilite;
        String fichier;
        String idCvliste;
        String nom;
        String prenom;
        Object time;
        Object titre;
        String typecv;

        public Body() {
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
