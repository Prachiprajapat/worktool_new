package com.example.worktool_new.Models.getskills;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SkillBodyItem {
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("label")
    @Expose
    private String label;
    @SerializedName("type")
    @Expose
    private String type;

    public String getId() {
        return this.id;
    }

    public void setId(String id2) {
        this.id = id2;
    }

    public String getLabel() {
        return this.label;
    }

    public void setLabel(String label2) {
        this.label = label2;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type2) {
        this.type = type2;
    }
}
