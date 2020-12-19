package com.example.worktool_new.Models;

public class AddEngagementModel {
    String beneficiary;
    String bDate;
    String referant;
    String rDate;

    public AddEngagementModel(String beneficiary, String bDate, String referant, String rDate) {
        this.beneficiary = beneficiary;
        this.bDate = bDate;
        this.referant = referant;
        this.rDate = rDate;
    }

    public String getBeneficiary() {
        return beneficiary;
    }

    public void setBeneficiary(String beneficiary) {
        this.beneficiary = beneficiary;
    }

    public String getbDate() {
        return bDate;
    }

    public void setbDate(String bDate) {
        this.bDate = bDate;
    }

    public String getReferant() {
        return referant;
    }

    public void setReferant(String referant) {
        this.referant = referant;
    }

    public String getrDate() {
        return rDate;
    }

    public void setrDate(String rDate) {
        this.rDate = rDate;
    }
}
