package com.example.surataccount.BookSchedule;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class BookScheduleModelClass implements Serializable {
@SerializedName("status")
    public String status;
@SerializedName("meassage")
    public String meassage;
@SerializedName("data")
public List<BookSchedule> bookSchedules;
    public  class  BookSchedule{
    @SerializedName("sch_id")
    public  String sch_id;
    @SerializedName("designer_id")
    public String designer_id;
    @SerializedName("sch_date")
    public  String sch_date;
    @SerializedName("sch_start_time")
    public String sch_start_time;
    @SerializedName("booking_no")
        public  String booking_no;

    }
}
