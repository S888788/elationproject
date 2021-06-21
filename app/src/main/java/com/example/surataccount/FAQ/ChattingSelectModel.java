package com.example.surataccount.FAQ;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class ChattingSelectModel implements Serializable {
    @SerializedName("status")
    public String status;
    @SerializedName("message")
    public  String message;
    @SerializedName("data")
    public List<ChattingSelectModel.SelectCHat> getChattingSelect;
    public  class  SelectCHat{
        @SerializedName("cha_id")
        public String cha_id;
        @SerializedName("ch_mobile_no")
        public  String ch_mobile_no;
        @SerializedName("msg_form")
        public String msg_form;
        @SerializedName("txt_msg")
        public  String txt_msg;
        @SerializedName("msg_date_time")
        public String msg_date_time;
        @SerializedName("ticket_no")
        public  String ticket_no;
        @SerializedName("status")
        public String status;

    }
}
