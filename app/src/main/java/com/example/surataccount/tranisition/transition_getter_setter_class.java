package com.example.surataccount.tranisition;

import com.google.gson.annotations.SerializedName;

public class transition_getter_setter_class {
    @SerializedName("Vouchernumber")
    private  String Vouchernumber;
    @SerializedName("Voucherdate")
    private String Voucherdate ;


    @SerializedName("StudioCode")
    private String StudioCode;
    @SerializedName("Studioname")
    private String Studioname;
    @SerializedName("AmtPayable")
    private String AmtPayable;
    @SerializedName("AmtRecieved")
    private String AmtRecieved;

    public transition_getter_setter_class(String vouchernumber, String voucherdate,  String studioCode, String studioname, String amtPayable, String amtRecieved) {
        Vouchernumber = vouchernumber;
        Voucherdate = voucherdate;

        StudioCode = studioCode;
        Studioname = studioname;
        AmtPayable = amtPayable;
        AmtRecieved = amtRecieved;
    }



    public String getVouchernumber() {
        return Vouchernumber;
    }

    public void setVouchernumber(String vouchernumber) {
        Vouchernumber = vouchernumber;
    }

    public String getVoucherdate() {
        return Voucherdate;
    }

    public void setVoucherdate(String voucherdate) {
        Voucherdate = voucherdate;
    }

    public String getStudioCode() {
        return StudioCode;
    }

    public void setStudioCode(String studioCode) {
        StudioCode = studioCode;
    }

    public String getStudioname() {
        return Studioname;
    }

    public void setStudioname(String studioname) {
        Studioname = studioname;
    }

    public String getAmtPayable() {
        return AmtPayable;
    }

    public void setAmtPayable(String amtPayable) {
        AmtPayable = amtPayable;
    }

    public String getAmtRecieved() {
        return AmtRecieved;
    }

    public void setAmtRecieved(String amtRecieved) {
        AmtRecieved = amtRecieved;
    }

    @Override
    public String toString() {
        return "ViewDetailsPojo{" +
                "Vouchernumber='" + Vouchernumber + '\'' +
                ", Voucherdate='" + Voucherdate + '\'' +

                ", StudioCode='" + StudioCode + '\'' +
                ", Studioname='" + Studioname + '\'' +
                ", AmtPayable='" + AmtPayable + '\'' +
                ", AmtRecieved='" + AmtRecieved + '\'' +
                '}';
    }
}
