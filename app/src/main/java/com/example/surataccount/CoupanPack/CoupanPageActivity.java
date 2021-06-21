package com.example.surataccount.CoupanPack;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.surataccount.ApiInterface.ApiInterfaceClass;
import com.example.surataccount.BaseUrl.BaseUrlClass;
import com.example.surataccount.BuyOfferPhotoCover.OfferMainPageActivity;
import com.example.surataccount.R;
import com.example.surataccount.User.Session_Use;
import com.example.surataccount.party.PartyActivity;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CoupanPageActivity extends AppCompatActivity {
    ImageView back_pess;
    RecyclerView photo_cover_recyclerView;
    ProgressBar progressBar;
    LinearLayoutManager linearLayoutManager;
    String Coup_SchemesCode,mobile,copan_value;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_coupan_page);
        Intent intent=getIntent();
        Coup_SchemesCode= intent.getStringExtra("coupanSceemeCode");
        copan_value=intent.getStringExtra("coupon");
        Log.d("Coup_SchemesCode",Coup_SchemesCode); Log.d("coupon",copan_value);
        Session_Use session_use=new Session_Use(CoupanPageActivity.this);
        mobile=session_use.getUsename();

        init();
        linearLayoutManager=new LinearLayoutManager(CoupanPageActivity.this);
        photo_cover_recyclerView.setLayoutManager(linearLayoutManager);
        //back pesss
        back_pess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1=new Intent(CoupanPageActivity.this, CoupanSummaryActivity.class);
                startActivity(intent1);
                finish();
            }
        });
        viewData();
    }

    private void viewData() {
        ProgressDialog progressDialog=new ProgressDialog(CoupanPageActivity.this);
        progressDialog.setTitle("please wait..");
        progressDialog.show();
        ApiInterfaceClass apiInterfaceClass= BaseUrlClass.getClient(CoupanPageActivity.this).create(ApiInterfaceClass.class);
        Call<CoupanPageModel> call=apiInterfaceClass.getCoupanData(Coup_SchemesCode,mobile,Integer.parseInt(copan_value));
        call.enqueue(new Callback<CoupanPageModel>() {
            @Override
            public void onResponse(Call<CoupanPageModel> call, Response<CoupanPageModel> response) {
                CoupanPageModel coupanPageModel=response.body();
                Log.d("cou",response.body().toString());
                if(response.isSuccessful())
                {
                    if(coupanPageModel.status.equals("success"))
                    {
                        List<CoupanPageModel.BookSchedule> data=new ArrayList<>();
                        data=response.body().bookSchedules;
                        CoupanPageAdpterClass adpterClass=new CoupanPageAdpterClass(CoupanPageActivity.this,data);
                        if(adpterClass.getItemCount()<=0)
                        {
                             progressDialog.dismiss();
                             final Dialog dialog = new Dialog(CoupanPageActivity.this);
                            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); // before
                            dialog.setContentView(R.layout.nodara_found_layout_file);
                            dialog.setCancelable(true);

                            ((Button) dialog.findViewById(R.id.nodata_btn)).setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Toast.makeText(getApplicationContext(), ((Button) v).getText().toString() + " Clicked", Toast.LENGTH_SHORT).show();
                                    dialog.dismiss();
                                    Intent intent=new Intent(CoupanPageActivity.this,CoupanSummaryActivity.class);
                                    startActivity(intent);
                                    finish();
                                }
                            });

                            dialog.show();


                        }
                        else
                        {
                            progressDialog.dismiss();

                            photo_cover_recyclerView.setAdapter(adpterClass);
                        }
                    }

                }
                else
                {
                    Toast.makeText(CoupanPageActivity.this, "not sucess", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<CoupanPageModel> call, Throwable t) {
                Toast.makeText(CoupanPageActivity.this, "failure", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void init() {
        //imageview id
        back_pess=(ImageView)findViewById(R.id.coupan_activity_back);
        photo_cover_recyclerView=(RecyclerView)findViewById(R.id.coupan_page_recyclerview);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent1=new Intent(CoupanPageActivity.this, CoupanSummaryActivity.class);
        startActivity(intent1);
        finish();
    }

    @Override
    protected void onStart() {
        super.onStart();
        viewData();
    }
}