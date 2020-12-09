package com.example.worktool_new.Models;

public class AddSkillModel {

    String skillName;
    Float skillRating;

    public AddSkillModel(String skillName, Float skillRating) {
        this.skillName = skillName;
        this.skillRating = skillRating;
    }

    public String getSkillName() {
        return skillName;
    }

    public void setSkillName(String skillName) {
        this.skillName = skillName;
    }

    public Float getSkillRating() {
        return skillRating;
    }

    public void setSkillRating(Float skillRating) {
        this.skillRating = skillRating;
    }
}
