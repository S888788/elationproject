package com.example.surataccount.BuyOfferPhotoCover;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.surataccount.CoupanPack.CoupanSummaryActivity;
import com.example.surataccount.Dashboard.Dashboard;
import com.example.surataccount.PriceList.PriceListActivity;
import com.example.surataccount.R;
import com.example.surataccount.SchemePack.CoupanOffersctivity2;

public class OfferMainPageActivity extends AppCompatActivity {
    ImageView back_pess;
    Button scheme_activity,offer_proper,offer_PHOTONOOK_PRICE,offer_DESIGN_PRICE;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_offer_main_page);
        init();
        back_pess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1=new Intent(OfferMainPageActivity.this, Dashboard.class);
                startActivity(intent1);
                finish();
            }
        });
//        Scheme activity
       scheme_activity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(OfferMainPageActivity.this, CoupanOffersctivity2.class);
                startActivity(intent);
                finish();
            }
        });
       offer_proper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(OfferMainPageActivity.this, PriceListActivity.class);
                intent.putExtra("cat_name","o");
                startActivity(intent);
                finish();
            }
        });
       offer_PHOTONOOK_PRICE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(OfferMainPageActivity.this, PriceListActivity.class);
                intent.putExtra("cat_name","p");
                startActivity(intent);
                finish();
            }
        });
        offer_DESIGN_PRICE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(OfferMainPageActivity.this, PriceListActivity.class);
                intent.putExtra("cat_name","d");
                startActivity(intent);
                finish();
            }
        });

    }

    private void init() {
        //imageview id
        back_pess=(ImageView)findViewById(R.id.offer_main_page_activity_back);
       scheme_activity=(Button)findViewById(R.id.scheme_activity);
        offer_proper=(Button)findViewById(R.id.offer_proper_activity);
        offer_PHOTONOOK_PRICE=(Button)findViewById(R.id.offer_PHOTONOOK_PRICE);
        offer_DESIGN_PRICE=(Button)findViewById(R.id.offer_DESIGN_PRICE);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent1=new Intent(OfferMainPageActivity.this, Dashboard.class);
        startActivity(intent1);
        finish();
    }
}