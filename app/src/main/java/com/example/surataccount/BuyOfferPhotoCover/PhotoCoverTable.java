package com.example.surataccount.BuyOfferPhotoCover;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class PhotoCoverTable implements Serializable {
    @SerializedName("status")
    public String  status;
    @SerializedName("meassage")
    public String meassage;
    @SerializedName("data")
    public List<All_Photo> datals;


    public class  All_Photo
    {
        @SerializedName("Cover_id")
        public  String Cover_id;
        @SerializedName("PhotoCover")
        public String PhotoCover;
        @SerializedName("CoverName")
        public  String CoverName;
        @SerializedName("Offer")
        public  String Offer;
        @SerializedName("MRP")
        public  String MRP;
        @SerializedName("Visible")
        public  String Visible;
        @SerializedName("Photo_id")
        public  String Photo_id;
    }

}
