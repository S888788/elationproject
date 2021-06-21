package com.example.surataccount.Photobook_Status;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;


public class Photobook_Gette_Pojo_Class implements Serializable {
    @SerializedName("status")
    public   String status;
    @SerializedName("message")
    public String message;
    @SerializedName("data")
    public List<Photobook_Gette_Pojo_Class.PriceListModel> getPrice;
    public class PriceListModel {

        @SerializedName("Distubutercode")
        String Distubutercode;
        @SerializedName("StudioCode")
        String StudioCode;
        @SerializedName("StudioName")
        String StudioName;
        @SerializedName("Date")
        String Date;
        @SerializedName("Booking")
        String Booking;
        @SerializedName("ServerId")
        String ServerId;
        @SerializedName("BillDetail")
        String BillDetail;
        @SerializedName("DispatchDetail")
        String DispatchDetail;
        @SerializedName("Status")
        String Status;

    }
}
