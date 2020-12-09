package com.example.worktool_new.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.ArrayList;

public class GetEngagementModel implements Serializable {
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
        @SerializedName("benef_ech")
        @Expose
        private String benefEch;
        @SerializedName("benef_engagement")
        @Expose
        private String benefEngagement;
        @SerializedName("cer_id")
        @Expose
        private String cerId;
        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("ref_ech")
        @Expose
        private String refEch;
        @SerializedName("ref_engagement")
        @Expose
        private String refEngagement;
        @SerializedName("valide_benef")
        @Expose
        private String valideBenef;
        @SerializedName("valide_ref")
        @Expose
        private String valideRef;

        public Datum() {
        }

        public String getId() {
            return this.id;
        }

        public void setId(String id2) {
            this.id = id2;
        }

        public String getCerId() {
            return this.cerId;
        }

        public void setCerId(String cerId2) {
            this.cerId = cerId2;
        }

        public String getBenefEngagement() {
            return this.benefEngagement;
        }

        public void setBenefEngagement(String benefEngagement2) {
            this.benefEngagement = benefEngagement2;
        }

        public String getRefEngagement() {
            return this.refEngagement;
        }

        public void setRefEngagement(String refEngagement2) {
            this.refEngagement = refEngagement2;
        }

        public String getBenefEch() {
            return this.benefEch;
        }

        public void setBenefEch(String benefEch2) {
            this.benefEch = benefEch2;
        }

        public String getRefEch() {
            return this.refEch;
        }

        public void setRefEch(String refEch2) {
            this.refEch = refEch2;
        }

        public String getValideBenef() {
            return this.valideBenef;
        }

        public void setValideBenef(String valideBenef2) {
            this.valideBenef = valideBenef2;
        }

        public String getValideRef() {
            return this.valideRef;
        }

        public void setValideRef(String valideRef2) {
            this.valideRef = valideRef2;
        }
    }
}
