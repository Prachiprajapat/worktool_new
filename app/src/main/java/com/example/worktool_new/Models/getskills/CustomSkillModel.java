package com.example.worktool_new.Models.getskills;

import java.util.ArrayList;

public class CustomSkillModel {
    Boolean isadded;
    String name;
    ArrayList<SkillBodyItem> skillBodyItemArrayList;

    public CustomSkillModel(String name2, ArrayList<SkillBodyItem> skillBodyItemArrayList2, Boolean isadded2) {
        this.name = name2;
        this.skillBodyItemArrayList = skillBodyItemArrayList2;
        this.isadded = isadded2;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name2) {
        this.name = name2;
    }

    public ArrayList<SkillBodyItem> getSkillBodyItemArrayList() {
        return this.skillBodyItemArrayList;
    }

    public void setSkillBodyItemArrayList(ArrayList<SkillBodyItem> skillBodyItemArrayList2) {
        this.skillBodyItemArrayList = skillBodyItemArrayList2;
    }

    public Boolean getIsadded() {
        return this.isadded;
    }

    public void setIsadded(Boolean isadded2) {
        this.isadded = isadded2;
    }
}
