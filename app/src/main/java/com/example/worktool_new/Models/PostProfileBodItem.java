package com.example.worktool_new.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PostProfileBodItem {
    @SerializedName("error")
    @Expose
    private Integer error;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("size")
    @Expose
    private Integer size;
    @SerializedName("tmp_name")
    @Expose
    private String tmpName;
    @SerializedName("type")
    @Expose
    private String type;

    public String getName() {
        return this.name;
    }

    public void setName(String name2) {
        this.name = name2;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type2) {
        this.type = type2;
    }

    public String getTmpName() {
        return this.tmpName;
    }

    public void setTmpName(String tmpName2) {
        this.tmpName = tmpName2;
    }

    public Integer getError() {
        return this.error;
    }

    public void setError(Integer error2) {
        this.error = error2;
    }

    public Integer getSize() {
        return this.size;
    }

    public void setSize(Integer size2) {
        this.size = size2;
    }
}
