package com.example.surataccount.BuyOfferPhotoCover;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PhotoTypeTable {
    @SerializedName("status")
    public String  status;
    @SerializedName("meassage")
    public String meassage;
    @SerializedName("data")
    public List<PhotoTypeTable.All_Photo_Type> all_photo_types;
    public class All_Photo_Type
    {
        @SerializedName("Photo_id")
        public  String Photo_id;


    }

}
