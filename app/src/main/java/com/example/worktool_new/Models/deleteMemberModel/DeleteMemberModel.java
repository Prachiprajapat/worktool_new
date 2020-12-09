package com.example.worktool_new.Models.deleteMemberModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DeleteMemberModel {
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("status_code")
    @Expose
    private Integer statusCode;

    public Integer getStatusCode() {
        return this.statusCode;
    }

    public void setStatusCode(Integer statusCode2) {
        this.statusCode = statusCode2;
    }

    public String getStatusMessage() {
        return this.message;
    }

    public void setStatusMessage(String message2) {
        this.message = message2;
    }
}
