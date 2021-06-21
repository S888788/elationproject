package com.example.surataccount;

import android.app.Service;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class Connection_Internet_Classs {
    Context context;
    public Connection_Internet_Classs(Context context)
    {
        this.context=context;
    }
    public  boolean isConnected()
    {
        ConnectivityManager connectivityManager=(ConnectivityManager)context.getSystemService(Service.CONNECTIVITY_SERVICE);
        if (connectivityManager!=null)
        {
            NetworkInfo networkInfo=connectivityManager.getActiveNetworkInfo();
            if (networkInfo!=null)
            {
                return true;

            }
        }
        return false;
    }
}
