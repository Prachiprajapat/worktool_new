package com.example.worktool_new.Models.getskills;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;

public class SkillModel {
    @SerializedName("data")
    @Expose
    private ArrayList<SkillBodyItem> data = null;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("status_code")
    @Expose
    private Integer statusCode;

    public ArrayList<SkillBodyItem> getData() {
        return this.data;
    }

    public void setData(ArrayList<SkillBodyItem> data2) {
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
}
