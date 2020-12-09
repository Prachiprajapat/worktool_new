package com.example.worktool_new.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PostProfileModel {
    @SerializedName("data")
    @Expose
    private PostProfileBodItem data;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("statuscode")
    @Expose
    private Integer statuscode;

    public PostProfileBodItem getData() {
        return this.data;
    }

    public void setData(PostProfileBodItem data2) {
        this.data = data2;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message2) {
        this.message = message2;
    }

    public Integer getStatuscode() {
        return this.statuscode;
    }

    public void setStatuscode(Integer statuscode2) {
        this.statuscode = statuscode2;
    }
}
