package com.example.surataccount.AboutPack;

import com.example.surataccount.BookSchedule.BookScheduleModelClass;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class AboutModelClass implements Serializable {
    @SerializedName("status")
    public String status;
    @SerializedName("message")
    public String message;
    @SerializedName("data")
    public List<AboutModelClass.AboutSchedule> aboutData;
    public class AboutSchedule {
        @SerializedName("T_id")
        public String T_id;
        @SerializedName("theory")
        public String theory;

    }
}
