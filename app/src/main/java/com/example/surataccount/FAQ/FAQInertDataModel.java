package com.example.surataccount.FAQ;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class FAQInertDataModel implements Serializable {
    @SerializedName("status")
    public  String status;
    @SerializedName("meassage")
    public  String meassage;
    @SerializedName("data")
    public List<FAQData> getFAQ;
    public  class  FAQData{
        @SerializedName("ch_mobile_no")
        public  String ch_mobile_no;
        @SerializedName("txt_msg")
        public  String txt_msg;
        @SerializedName("ticket_no")
        public  String ticket_no;
    }
}
