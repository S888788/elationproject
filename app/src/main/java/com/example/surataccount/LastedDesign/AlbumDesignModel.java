package com.example.surataccount.LastedDesign;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class AlbumDesignModel implements Serializable {
    @SerializedName("status")
    public  String status;
    @SerializedName("meassage")
    public  String meassage;
    @SerializedName("data")
    public List<DemoDesignModel.DESIGNData> getDesihn;
    public  class  DESIGNData{
        @SerializedName("Tid")
        public  String Tid;
        @SerializedName("AlbumId")
        public  String AlbumId;
        @SerializedName("AlbumName")
        public  String AlbumName;
        @SerializedName("ImagePath")
        public  String ImagePath;
        @SerializedName("Pagetype")
        public  String Pagetype;


    }
}
