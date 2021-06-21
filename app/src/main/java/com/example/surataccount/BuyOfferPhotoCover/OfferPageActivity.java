package com.example.surataccount.BuyOfferPhotoCover;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.surataccount.ApiInterface.ApiInterfaceClass;
import com.example.surataccount.BaseUrl.BaseUrlClass;
import com.example.surataccount.CoupanPack.CoupanSummaryActivity;
import com.example.surataccount.Dashboard.Dashboard;
import com.example.surataccount.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OfferPageActivity extends AppCompatActivity {
    Button photocover,coupen_activity;
    ImageView back_pess;
    RecyclerView photo_cover_recyclerView;
    ProgressBar progressBar;
    LinearLayoutManager linearLayoutManager;
    OfferPageAdpter offerPageAdpter;
    List<PhotoTypeTable.All_Photo_Type> show_data=new ArrayList<>();
    PhotoTypeTable photoTypeTable;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_offer_page);
        //imageview id
       back_pess=(ImageView)findViewById(R.id.offer_page_activity_back);

        photo_cover_recyclerView=(RecyclerView)findViewById(R.id.allcoerpotor_recyclerview);
        //progress bar
       // progressBar=(ProgressBar)findViewById(R.id.photocoverprogreeBar);
        photo_cover_recyclerView.setHasFixedSize(true);
        linearLayoutManager=new LinearLayoutManager(OfferPageActivity.this);
        photo_cover_recyclerView.setLayoutManager(linearLayoutManager);
        //back pesss

       back_pess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1=new Intent(OfferPageActivity.this, Dashboard.class);
                startActivity(intent1);
                finish();
            }
        });

        //coupan activity

        //viewData
        viewdatas();


    }

    private void viewdatas() {
        ProgressDialog progressDialog=new ProgressDialog(OfferPageActivity.this);
        progressDialog.setTitle("please wait..");
        progressDialog.show();
        ApiInterfaceClass apiInterfaceClass = BaseUrlClass.getClient(OfferPageActivity.this).create(ApiInterfaceClass.class);
        Call<PhotoTypeTable> call=apiInterfaceClass.getPhotoTypePhotoCoverImage();
        call.enqueue(new Callback<PhotoTypeTable>() {
            @Override
            public void onResponse(Call<PhotoTypeTable> call, Response<PhotoTypeTable> response) {
                photoTypeTable=response.body();
                if (response.isSuccessful())
                {
                   show_data = response.body().all_photo_types;
                  offerPageAdpter=new OfferPageAdpter(OfferPageActivity.this,show_data);
                  if(offerPageAdpter.getItemCount()>0) {
                      progressDialog.dismiss();
                      photo_cover_recyclerView.setAdapter(offerPageAdpter);
                  }
                  else
                  {
                      Toast.makeText(OfferPageActivity.this, "no data found..", Toast.LENGTH_SHORT).show();
                  }
                    
                }
                else 
                {
                    Toast.makeText(OfferPageActivity.this, "Response is not perfect", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<PhotoTypeTable> call, Throwable t) {
                Toast.makeText(OfferPageActivity.this, "Some things worng ...", Toast.LENGTH_SHORT).show();

            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        viewdatas();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent1=new Intent(OfferPageActivity.this, Dashboard.class);
        startActivity(intent1);
        finish();
    }
}