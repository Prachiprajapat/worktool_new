package com.example.worktool_new.Models;

public class JsonModel {
    private String competence;
    private String competence_id;
    private String competence_type;
    private String level;

    public String getCompetence_id() {
        return this.competence_id;
    }

    public void setCompetence_id(String competence_id2) {
        this.competence_id = competence_id2;
    }

    public String getCompetence() {
        return this.competence;
    }

    public void setCompetence(String competence2) {
        this.competence = competence2;
    }

    public String getCompetence_type() {
        return this.competence_type;
    }

    public void setCompetence_type(String competence_type2) {
        this.competence_type = competence_type2;
    }

    public String getLevel() {
        return this.level;
    }

    public void setLevel(String level2) {
        this.level = level2;
    }
}
