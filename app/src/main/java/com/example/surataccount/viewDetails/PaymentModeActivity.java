package com.example.surataccount.viewDetails;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.surataccount.Dashboard.Dashboard;
import com.example.surataccount.R;
import com.example.surataccount.User.Session_Use;
import com.example.surataccount.party.PartyActivity;
import com.example.surataccount.widActivity;

public class PaymentModeActivity extends AppCompatActivity {

    public String name2,code2,balamt;
    Button details_view_details,details_widrals;
    ImageView payment_back_pess;
    String stname,stcode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_payment_mode);
        payment_back_pess=(ImageView)findViewById(R.id.payment_mode_activity_back);

        details_view_details=(Button)findViewById(R.id.payment_mode_activity_det_view);
        details_widrals=(Button)findViewById(R.id.payment_mode_activity_details_wid);

        Session_Use user=new Session_Use(PaymentModeActivity.this);
        final String mobile=user.getUsename();
        SharedPreferences sh = getSharedPreferences("stcodebmtname", MODE_PRIVATE);
        stname = sh.getString("stname", "");
        stcode = sh.getString("stcode", "");
        balamt = sh.getString("balamt", "");

        payment_back_pess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1=new Intent(PaymentModeActivity.this, PartyActivity.class);
                startActivity(intent1);
                finish();
            }
        });
        //view details activity
        details_view_details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(PaymentModeActivity.this, viewDetailsActivity.class);
                startActivity(intent);
                finish();
            }
        });
        //wid activity
        details_widrals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(PaymentModeActivity.this, widActivity.class);
                startActivity(intent);
                finish();
            }
        });



    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent1=new Intent(PaymentModeActivity.this, PartyActivity.class);
        startActivity(intent1);
        finish();
    }
}