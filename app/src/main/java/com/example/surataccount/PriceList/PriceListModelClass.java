package com.example.surataccount.PriceList;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class PriceListModelClass implements Serializable {
    @SerializedName("status")
      public   String status;
    @SerializedName("message")
    public String message;
    @SerializedName("data")
    public List<PriceListModelClass.PriceListModel> getPrice;
    public class PriceListModel {
        @SerializedName("prod_id")
        public String prod_id;
        @SerializedName("name")
        public  String name;
        @SerializedName("price")
        public  String price;
        @SerializedName("cat_names")
        public  String cat_names;
    }
}
