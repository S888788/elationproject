package com.example.surataccount.User;

import android.content.Context;
import android.content.SharedPreferences;

public class Session_Use {
    SharedPreferences sharedPreferences;
    public Session_Use(Context context) {
        this.context = context;
        sharedPreferences=context.getSharedPreferences("login_details",Context.MODE_PRIVATE);
    }

    Context context;
    public void removeUser()
    {
        sharedPreferences.edit().clear().commit();
    }
    private  String usename,passwords,otp;




    public String getOtp() {
        otp=sharedPreferences.getString("otp","");
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
        sharedPreferences.edit().putString("otp",otp).commit();
    }

    public String getUsename() {
        usename=sharedPreferences.getString("usename","");
        return usename;
    }

    public void setUsename(String usename) {
        this.usename = usename;
        sharedPreferences.edit().putString("usename",usename).commit();
    }

    public String getPasswords() {

        passwords=sharedPreferences.getString("passwords","");
        return passwords;
    }

    public void setPasswords(String passwords) {
        this.passwords = passwords;
        sharedPreferences.edit().putString("passwords",passwords).commit();
    }
}