package com.example.surataccount.SchemePack;

import com.google.gson.annotations.SerializedName;

public class NamesO_Gettter_Setteer_Class {
    @SerializedName("SchemeName")
    private String schemeName;

    public NamesO_Gettter_Setteer_Class(String schemeName) {
        this.schemeName = schemeName;
    }

    public String getSchemeName() {
        return schemeName;
    }

    public void setSchemeName(String schemeName) {
        this.schemeName = schemeName;
    }
}
