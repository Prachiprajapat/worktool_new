package com.example.worktool_new.Models;

import java.util.ArrayList;

public class AddCerModel {
    String ajourne;
    String association;
    String atout_env_social;
    String atout_logement;
    String atout_mobilite;
    String atout_sante;
    String atout_situation_perso;
    String besoin;
    String ccas;
    String contrat;
    String dure_validation;
    ArrayList<Engagement> engdata;
    String frein_env_social;
    String frein_logement;
    String frein_mobilite;
    String frein_sante;
    String frein_situation_perso;
    Integer idMember;
    String mds;
    String message;
    String motif;
    String next_rdv;
    String preconisation;
    String situation_actuel;
    Integer status_code;
    String validite;

    public Integer getIdMember() {
        return this.idMember;
    }

    public void setIdMember(Integer idMember2) {
        this.idMember = idMember2;
    }

    public String getMds() {
        return this.mds;
    }

    public void setMds(String mds2) {
        this.mds = mds2;
    }

    public String getCcas() {
        return this.ccas;
    }

    public void setCcas(String ccas2) {
        this.ccas = ccas2;
    }

    public String getAssociation() {
        return this.association;
    }

    public void setAssociation(String association2) {
        this.association = association2;
    }

    public String getMotif() {
        return this.motif;
    }

    public void setMotif(String motif2) {
        this.motif = motif2;
    }

    public String getAtout_sante() {
        return this.atout_sante;
    }

    public void setAtout_sante(String atout_sante2) {
        this.atout_sante = atout_sante2;
    }

    public String getAtout_mobilite() {
        return this.atout_mobilite;
    }

    public void setAtout_mobilite(String atout_mobilite2) {
        this.atout_mobilite = atout_mobilite2;
    }

    public String getAtout_logement() {
        return this.atout_logement;
    }

    public void setAtout_logement(String atout_logement2) {
        this.atout_logement = atout_logement2;
    }

    public String getAtout_env_social() {
        return this.atout_env_social;
    }

    public void setAtout_env_social(String atout_env_social2) {
        this.atout_env_social = atout_env_social2;
    }

    public String getAtout_situation_perso() {
        return this.atout_situation_perso;
    }

    public void setAtout_situation_perso(String atout_situation_perso2) {
        this.atout_situation_perso = atout_situation_perso2;
    }

    public String getFrein_sante() {
        return this.frein_sante;
    }

    public void setFrein_sante(String frein_sante2) {
        this.frein_sante = frein_sante2;
    }

    public String getFrein_mobilite() {
        return this.frein_mobilite;
    }

    public void setFrein_mobilite(String frein_mobilite2) {
        this.frein_mobilite = frein_mobilite2;
    }

    public String getFrein_logement() {
        return this.frein_logement;
    }

    public void setFrein_logement(String frein_logement2) {
        this.frein_logement = frein_logement2;
    }

    public String getFrein_env_social() {
        return this.frein_env_social;
    }

    public void setFrein_env_social(String frein_env_social2) {
        this.frein_env_social = frein_env_social2;
    }

    public String getFrein_situation_perso() {
        return this.frein_situation_perso;
    }

    public void setFrein_situation_perso(String frein_situation_perso2) {
        this.frein_situation_perso = frein_situation_perso2;
    }

    public String getSituation_actuel() {
        return this.situation_actuel;
    }

    public void setSituation_actuel(String situation_actuel2) {
        this.situation_actuel = situation_actuel2;
    }

    public String getBesoin() {
        return this.besoin;
    }

    public void setBesoin(String besoin2) {
        this.besoin = besoin2;
    }

    public String getNext_rdv() {
        return this.next_rdv;
    }

    public void setNext_rdv(String next_rdv2) {
        this.next_rdv = next_rdv2;
    }

    public String getValidite() {
        return this.validite;
    }

    public void setValidite(String validite2) {
        this.validite = validite2;
    }

    public String getAjourne() {
        return this.ajourne;
    }

    public void setAjourne(String ajourne2) {
        this.ajourne = ajourne2;
    }

    public String getPreconisation() {
        return this.preconisation;
    }

    public void setPreconisation(String preconisation2) {
        this.preconisation = preconisation2;
    }

    public String getDure_validation() {
        return this.dure_validation;
    }

    public void setDure_validation(String dure_validation2) {
        this.dure_validation = dure_validation2;
    }

    public String getContrat() {
        return this.contrat;
    }

    public void setContrat(String contrat2) {
        this.contrat = contrat2;
    }

    public ArrayList<Engagement> getEngdata() {
        return this.engdata;
    }

    public void setEngdata(ArrayList<Engagement> engdata2) {
        this.engdata = engdata2;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message2) {
        this.message = message2;
    }

    public Integer getStatus_code() {
        return this.status_code;
    }

    public void setStatus_code(Integer status_code2) {
        this.status_code = status_code2;
    }

    public static class Engagement {
        String echeance_benef;
        String echeance_ref;
        String engagement_benef;
        String engagement_ref;

        public String getEngagement_benef() {
            return this.engagement_benef;
        }

        public void setEngagement_benef(String engagement_benef2) {
            this.engagement_benef = engagement_benef2;
        }

        public String getEngagement_ref() {
            return this.engagement_ref;
        }

        public void setEngagement_ref(String engagement_ref2) {
            this.engagement_ref = engagement_ref2;
        }

        public String getEcheance_benef() {
            return this.echeance_benef;
        }

        public void setEcheance_benef(String echeance_benef2) {
            this.echeance_benef = echeance_benef2;
        }

        public String getEcheance_ref() {
            return this.echeance_ref;
        }

        public void setEcheance_ref(String echeance_ref2) {
            this.echeance_ref = echeance_ref2;
        }
    }
}
