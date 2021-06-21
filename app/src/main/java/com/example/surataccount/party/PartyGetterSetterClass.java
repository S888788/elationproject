package com.example.surataccount.party;

import com.google.gson.annotations.SerializedName;

import java.util.Comparator;

public class PartyGetterSetterClass {
    @SerializedName("PartyCode")
    private String PartyCode;
    @SerializedName("PartyName")
    private String PartyName;
    @SerializedName("AmountPayable")
    private  String AmountPayable;
    @SerializedName("AmountRecieved")
    private  String AmountRecieved;
    @SerializedName("BalanceAmt")
    private String BalanceAmt;
    @SerializedName("OpeningBalance")
    private String OpeningBalance;
    @SerializedName("DistubuterCode")
    private String DistubuterCode;


    public PartyGetterSetterClass(String partyCode, String partyName, String amountPayable, String amountRecieved, String balanceAmt, String openingBalance) {
        PartyCode = partyCode;
        PartyName = partyName;
        AmountPayable = amountPayable;
        AmountRecieved = amountRecieved;
        BalanceAmt = balanceAmt;
        OpeningBalance = openingBalance;
    }
        //sorting according to name
    public static final Comparator<PartyGetterSetterClass> BY_NAME_ALPHBETICAL=new Comparator<PartyGetterSetterClass>() {
            @Override
            public int compare(PartyGetterSetterClass PartyGetterSetterClass, PartyGetterSetterClass t1) {

                return PartyGetterSetterClass.getPartyName().compareTo(t1.getPartyName());
            }
        };

    public String getPartyCode() {
        return PartyCode;
    }

    public void setPartyCode(String partyCode) {
        PartyCode = partyCode;
    }

    public String getPartyName() {
        return PartyName;
    }

    public void setPartyName(String partyName) {
        PartyName = partyName;
    }

    public String getAmountPayable() {
        return AmountPayable;
    }

    public void setAmountPayable(String amountPayable) {
        AmountPayable = amountPayable;
    }

    public String getAmountRecieved() {
        return AmountRecieved;
    }

    public void setAmountRecieved(String amountRecieved) {
        AmountRecieved = amountRecieved;
    }

    public String getBalanceAmt() {
        return BalanceAmt;
    }

    public void setBalanceAmt(String balanceAmt) {
        BalanceAmt = balanceAmt;
    }

    public String getOpeningBalance() {
        return OpeningBalance;
    }

    public void setOpeningBalance(String openingBalance) {
        OpeningBalance = openingBalance;
    }

    @Override
    public String toString() {
        return "partyGeetterSetter{" +
                "PartyCode='" + PartyCode + '\'' +
                ", PartyName='" + PartyName + '\'' +
                ", AmountPayable='" + AmountPayable + '\'' +
                ", AmountRecieved='" + AmountRecieved + '\'' +
                ", BalanceAmt='" + BalanceAmt + '\'' +
                '}';
    }
}


