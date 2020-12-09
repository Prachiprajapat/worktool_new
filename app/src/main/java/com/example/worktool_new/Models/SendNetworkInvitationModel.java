package com.example.worktool_new.Models;

public class SendNetworkInvitationModel {
    String message;
    Integer status_code;

    public Integer getSuccess() {
        return this.status_code;
    }

    public void setSuccess(Integer status_code2) {
        this.status_code = status_code2;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message2) {
        this.message = message2;
    }
}
