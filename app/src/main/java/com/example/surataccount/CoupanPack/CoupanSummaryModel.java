package com.example.surataccount.CoupanPack;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class CoupanSummaryModel implements Serializable {
    @SerializedName("status")
    public String status;
    @SerializedName("message")
    public String message;
    @SerializedName("data")
    public List<CoupanSummaryModel.CoupanSummary> dataListss;

    public class CoupanSummary {

        @SerializedName("Coup_StudioCode")
        public String Coup_StudioCode;
        @SerializedName("Coup_StudioName")
        public String Coup_StudioName;
        @SerializedName("Coup_SchemeName")
        public String Coup_SchemeName;
        @SerializedName("Coup_DistributorCode")
        public String Coup_DistributorCode;
        @SerializedName("Coup_SchemesCode")
        public String Coup_SchemesCode;
        @SerializedName("IssueCoupan")
        public String IssueCoupan;
        @SerializedName("UsedCoupon")
        public String UsedCoupon;
        @SerializedName("std_mobile")
        public String std_mobile;

    }
}
