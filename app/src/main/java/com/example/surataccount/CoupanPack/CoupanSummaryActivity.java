package com.example.surataccount.CoupanPack;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.surataccount.ApiInterface.ApiInterfaceClass;
import com.example.surataccount.BaseUrl.BaseUrlClass;
import com.example.surataccount.BuyOfferPhotoCover.OfferMainPageActivity;
import com.example.surataccount.Dashboard.Dashboard;
import com.example.surataccount.R;
import com.example.surataccount.User.Session_Use;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CoupanSummaryActivity extends AppCompatActivity {
    ImageView back_pess;
    RecyclerView photo_cover_recyclerView;
    ProgressBar progressBar;
    LinearLayoutManager linearLayoutManager;
    String mobile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_coupan_summary);
        Session_Use user=new Session_Use(CoupanSummaryActivity.this);
        mobile =user.getUsename();
        Log.d("mob",mobile);
        init();
        linearLayoutManager=new LinearLayoutManager(CoupanSummaryActivity.this);
        photo_cover_recyclerView.setLayoutManager(linearLayoutManager);
        //back pesss
        back_pess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1=new Intent(CoupanSummaryActivity.this, Dashboard.class);
                startActivity(intent1);
                finish();
            }
        });
        viewData();

    }

    private void init() {
        back_pess=(ImageView)findViewById(R.id.coupan_summary_activity_back);
        photo_cover_recyclerView=(RecyclerView)findViewById(R.id.coupan_summary_recyclerview);
    }

    private void viewData() {
        ProgressDialog progressDialog=new ProgressDialog(CoupanSummaryActivity.this);
        progressDialog.setTitle("please wait..");
        progressDialog.show();
        ApiInterfaceClass apiInterfaceClass= BaseUrlClass.getClient(CoupanSummaryActivity.this).create(ApiInterfaceClass.class);
        Call<CoupanSummaryModel> call=apiInterfaceClass.getCoupanSummarydata(mobile);
        call.enqueue(new Callback<CoupanSummaryModel>() {
            @Override
            public void onResponse(Call<CoupanSummaryModel> call, Response<CoupanSummaryModel> response) {
            CoupanSummaryModel coupanSummaryModel=response.body();
            if(response.isSuccessful())
            {
                if(coupanSummaryModel.status.equals("success"))
                {
                    List<CoupanSummaryModel.CoupanSummary> data=new ArrayList<>();
                    data=response.body().dataListss;
                  CoupanSummaryAdpter coupanSummaryAdpter=new CoupanSummaryAdpter(CoupanSummaryActivity.this,data);
                  if(coupanSummaryAdpter.getItemCount()>0)
                  {
                      progressDialog.dismiss();
                      photo_cover_recyclerView.setAdapter(coupanSummaryAdpter);
                  }
                  else
                  {

                      progressDialog.dismiss();
                      final Dialog dialog = new Dialog(CoupanSummaryActivity.this);
                      dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); // before
                      dialog.setContentView(R.layout.nodara_found_layout_file);
                      dialog.setCancelable(true);

                      ((Button) dialog.findViewById(R.id.nodata_btn)).setOnClickListener(new View.OnClickListener() {
                          @Override
                          public void onClick(View v) {

                              dialog.dismiss();

                          }
                      });

                      dialog.show();
                  }

                }

            }
            else
            {
                Toast.makeText(CoupanSummaryActivity.this, "not sucessfully", Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }
            }

            @Override
            public void onFailure(Call<CoupanSummaryModel> call, Throwable t) {
                Toast.makeText(CoupanSummaryActivity.this, "Faiuure", Toast.LENGTH_SHORT).show();
            }
        });

    }



    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent1=new Intent(CoupanSummaryActivity.this, Dashboard.class);
        startActivity(intent1);
        finish();
    }
}