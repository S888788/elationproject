package com.example.surataccount.BookSchedule;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class UpdateBookingNumberModel implements Serializable {
    @SerializedName("status")
    public  String status;
    @SerializedName("meassage")
    public  String meassage;
    @SerializedName("data")
   public List<BookingSelect> getBooling;
    public class  BookingSelect{
        @SerializedName("BookingNumber")
        public String BookingNumber;
    }
}
