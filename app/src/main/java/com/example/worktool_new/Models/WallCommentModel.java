package com.example.worktool_new.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;

public class WallCommentModel {
    @SerializedName("data")
    @Expose
    private ArrayList<Datum> data = null;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("statuscode")
    @Expose
    private Integer statuscode;

    public ArrayList<Datum> getData() {
        return this.data;
    }

    public void setData(ArrayList<Datum> data2) {
        this.data = data2;
    }

    public Integer getStatuscode() {
        return this.statuscode;
    }

    public void setStatuscode(Integer statuscode2) {
        this.statuscode = statuscode2;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message2) {
        this.message = message2;
    }

    public class Datum {
        @SerializedName("adresse")
        @Expose
        private String adresse;
        @SerializedName("caf")
        @Expose
        private Object caf;
        @SerializedName("civilite")
        @Expose
        private String civilite;
        @SerializedName("cp")
        @Expose
        private String cp;
        @SerializedName("creation")
        @Expose
        private String creation;
        @SerializedName("date")
        @Expose
        private String date;
        @SerializedName("dateConnexion")
        @Expose
        private String dateConnexion;
        @SerializedName("dateheure")
        @Expose
        private String dateheure;
        @SerializedName("description")
        @Expose
        private String description;
        @SerializedName("disponibilite")
        @Expose
        private Object disponibilite;
        @SerializedName("domaine")
        @Expose
        private String domaine;
        @SerializedName("email")
        @Expose
        private String email;
        @SerializedName("formation")
        @Expose
        private String formation;
        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("idCompte")
        @Expose
        private String idCompte;
        @SerializedName("idConseiller")
        @Expose
        private String idConseiller;
        @SerializedName("idMur")
        @Expose
        private String idMur;
        @SerializedName("idProfil")
        @Expose
        private String idProfil;
        @SerializedName("image")
        @Expose
        private String image;
        @SerializedName("localisation")
        @Expose
        private String localisation;
        @SerializedName("nom")
        @Expose
        private String nom;
        @SerializedName("notifier_cv_15")
        @Expose
        private String notifierCv15;
        @SerializedName("notifier_cv_30")
        @Expose
        private String notifierCv30;
        @SerializedName("notifier_cv_45")
        @Expose
        private String notifierCv45;
        @SerializedName("notifier_profil_10")
        @Expose
        private String notifierProfil10;
        @SerializedName("notifier_profil_30")
        @Expose
        private String notifierProfil30;
        @SerializedName("notifier_profil_50")
        @Expose
        private String notifierProfil50;
        @SerializedName("pass")
        @Expose
        private String pass;
        @SerializedName("prenom")
        @Expose
        private String prenom;
        @SerializedName("quota")
        @Expose
        private String quota;
        @SerializedName("redirection")
        @Expose
        private String redirection;
        @SerializedName("situation")
        @Expose
        private Object situation;
        @SerializedName("telFixe")
        @Expose
        private String telFixe;
        @SerializedName("telPort")
        @Expose
        private String telPort;
        @SerializedName("texte")
        @Expose
        private String texte;
        @SerializedName("type")
        @Expose
        private String type;
        @SerializedName("ville")
        @Expose
        private String ville;

        public Datum() {
        }

        public String getId() {
            return this.id;
        }

        public void setId(String id2) {
            this.id = id2;
        }

        public String getIdMur() {
            return this.idMur;
        }

        public void setIdMur(String idMur2) {
            this.idMur = idMur2;
        }

        public String getTexte() {
            return this.texte;
        }

        public void setTexte(String texte2) {
            this.texte = texte2;
        }

        public String getIdCompte() {
            return this.idCompte;
        }

        public void setIdCompte(String idCompte2) {
            this.idCompte = idCompte2;
        }

        public String getDateheure() {
            return this.dateheure;
        }

        public void setDateheure(String dateheure2) {
            this.dateheure = dateheure2;
        }

        public String getCivilite() {
            return this.civilite;
        }

        public void setCivilite(String civilite2) {
            this.civilite = civilite2;
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

        public String getEmail() {
            return this.email;
        }

        public void setEmail(String email2) {
            this.email = email2;
        }

        public String getPass() {
            return this.pass;
        }

        public void setPass(String pass2) {
            this.pass = pass2;
        }

        public String getTelFixe() {
            return this.telFixe;
        }

        public void setTelFixe(String telFixe2) {
            this.telFixe = telFixe2;
        }

        public String getTelPort() {
            return this.telPort;
        }

        public void setTelPort(String telPort2) {
            this.telPort = telPort2;
        }

        public String getType() {
            return this.type;
        }

        public void setType(String type2) {
            this.type = type2;
        }

        public String getDate() {
            return this.date;
        }

        public void setDate(String date2) {
            this.date = date2;
        }

        public String getDateConnexion() {
            return this.dateConnexion;
        }

        public void setDateConnexion(String dateConnexion2) {
            this.dateConnexion = dateConnexion2;
        }

        public String getIdConseiller() {
            return this.idConseiller;
        }

        public void setIdConseiller(String idConseiller2) {
            this.idConseiller = idConseiller2;
        }

        public String getRedirection() {
            return this.redirection;
        }

        public void setRedirection(String redirection2) {
            this.redirection = redirection2;
        }

        public Object getDisponibilite() {
            return this.disponibilite;
        }

        public void setDisponibilite(Object disponibilite2) {
            this.disponibilite = disponibilite2;
        }

        public String getLocalisation() {
            return this.localisation;
        }

        public void setLocalisation(String localisation2) {
            this.localisation = localisation2;
        }

        public String getCreation() {
            return this.creation;
        }

        public void setCreation(String creation2) {
            this.creation = creation2;
        }

        public String getQuota() {
            return this.quota;
        }

        public void setQuota(String quota2) {
            this.quota = quota2;
        }

        public String getNotifierProfil10() {
            return this.notifierProfil10;
        }

        public void setNotifierProfil10(String notifierProfil102) {
            this.notifierProfil10 = notifierProfil102;
        }

        public String getNotifierProfil30() {
            return this.notifierProfil30;
        }

        public void setNotifierProfil30(String notifierProfil302) {
            this.notifierProfil30 = notifierProfil302;
        }

        public String getNotifierProfil50() {
            return this.notifierProfil50;
        }

        public void setNotifierProfil50(String notifierProfil502) {
            this.notifierProfil50 = notifierProfil502;
        }

        public String getNotifierCv15() {
            return this.notifierCv15;
        }

        public void setNotifierCv15(String notifierCv152) {
            this.notifierCv15 = notifierCv152;
        }

        public String getNotifierCv30() {
            return this.notifierCv30;
        }

        public void setNotifierCv30(String notifierCv302) {
            this.notifierCv30 = notifierCv302;
        }

        public String getNotifierCv45() {
            return this.notifierCv45;
        }

        public void setNotifierCv45(String notifierCv452) {
            this.notifierCv45 = notifierCv452;
        }

        public String getIdProfil() {
            return this.idProfil;
        }

        public void setIdProfil(String idProfil2) {
            this.idProfil = idProfil2;
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

        public String getImage() {
            return this.image;
        }

        public void setImage(String image2) {
            this.image = image2;
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
