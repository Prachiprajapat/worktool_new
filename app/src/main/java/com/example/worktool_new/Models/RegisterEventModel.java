package com.example.worktool_new.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class RegisterEventModel implements Serializable {
    @SerializedName("data")
    @Expose
    private ArrayList<Datum> data = null;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("status_code")
    @Expose
    private Integer statusCode;

    public ArrayList<Datum> getData() {
        return this.data;
    }

    public void setData(ArrayList<Datum> data2) {
        this.data = data2;
    }

    public Integer getStatusCode() {
        return this.statusCode;
    }

    public void setStatusCode(Integer statusCode2) {
        this.statusCode = statusCode2;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message2) {
        this.message = message2;
    }

    public class Datum implements Serializable {
        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("idEvent")
        @Expose
        private String idEvent;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getIdEvent() {
            return idEvent;
        }

        public void setIdEvent(String idEvent) {
            this.idEvent = idEvent;
        }
    }
}
