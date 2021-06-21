package com.example.surataccount.CoupanPack;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class CoupanPageModel implements Serializable {
    @SerializedName("status")
    public String status;
    @SerializedName("message")
    public String message;
    @SerializedName("data")
    public List<CoupanPageModel.BookSchedule> bookSchedules;
    public  class  BookSchedule{
        @SerializedName("Coup_id")
        public  String Coup_id;
        @SerializedName("Coup_UserName")
        public String Coup_UserName;
        @SerializedName("Coup_Password")
        public  String Coup_Password;
        @SerializedName("Coup_DistributorCode")
        public String Coup_DistributorCode;
        @SerializedName("Coup_StudioCode")
        public  String Coup_StudioCode;
        @SerializedName("Coup_StudioName")
        public  String Coup_StudioName;
        @SerializedName("Coup_SchemeName")
        public String Coup_SchemeName;
        @SerializedName("Coup_SchemesCode")
        public  String Coup_SchemesCode;
        @SerializedName("Coup_IssueDate")
        public String Coup_IssueDate;
        @SerializedName("Coup_Status")
        public  String Coup_Status;
        @SerializedName("Coup_ExpiryDate")
        public String Coup_ExpiryDate;
        @SerializedName("std_mobile")
        public  String std_mobile;
    }
}
