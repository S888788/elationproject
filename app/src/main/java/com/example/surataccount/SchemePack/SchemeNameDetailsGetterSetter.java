package com.example.surataccount.SchemePack;

import com.google.gson.annotations.SerializedName;

public class SchemeNameDetailsGetterSetter {
    @SerializedName("SchemeId")
    private String SchemeId;
    @SerializedName("SchemeCode")
    private String SchemeCode;
    @SerializedName("SchemeName")
    private String SchemeName;
    @SerializedName("DepositeAmt")
    private String DepositeAmt;
    @SerializedName("SchemeDetail")
    private String SchemeDetail;
    @SerializedName("SchemeExpiry")
    private String SchemeExpiry;

    public SchemeNameDetailsGetterSetter(String schemeId, String schemeCode, String schemeName, String depositeAmt, String schemeDetail, String schemeExpiry) {
        SchemeId = schemeId;
        SchemeCode = schemeCode;
        SchemeName = schemeName;
        DepositeAmt = depositeAmt;
        SchemeDetail = schemeDetail;
        SchemeExpiry = schemeExpiry;
    }

    public String getSchemeId() {
        return SchemeId;
    }

    public void setSchemeId(String schemeId) {
        SchemeId = schemeId;
    }

    public String getSchemeCode() {
        return SchemeCode;
    }

    public void setSchemeCode(String schemeCode) {
        SchemeCode = schemeCode;
    }

    public String getSchemeName() {
        return SchemeName;
    }

    public void setSchemeName(String schemeName) {
        SchemeName = schemeName;
    }

    public String getDepositeAmt() {
        return DepositeAmt;
    }

    public void setDepositeAmt(String depositeAmt) {
        DepositeAmt = depositeAmt;
    }

    public String getSchemeDetail() {
        return SchemeDetail;
    }

    public void setSchemeDetail(String schemeDetail) {
        SchemeDetail = schemeDetail;
    }

    public String getSchemeExpiry() {
        return SchemeExpiry;
    }

    public void setSchemeExpiry(String schemeExpiry) {
        SchemeExpiry = schemeExpiry;
    }
}
