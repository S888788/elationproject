package com.example.surataccount.PriceList;

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
import android.widget.Toast;

import com.example.surataccount.AboutPack.AboutActivity;
import com.example.surataccount.ApiInterface.ApiInterfaceClass;
import com.example.surataccount.BaseUrl.BaseUrlClass;
import com.example.surataccount.BuyOfferPhotoCover.OfferMainPageActivity;
import com.example.surataccount.Dashboard.Dashboard;
import com.example.surataccount.Photobook_Status.Phtobook_StatusActivity;
import com.example.surataccount.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PriceListActivity extends AppCompatActivity {
RecyclerView recyclerView;
ImageView back_press;
String  cat_name;
LinearLayoutManager linearLayoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_price_list);
       Intent intent = getIntent();
      cat_name= intent.getStringExtra("cat_name");
      Log.d("cat",cat_name);
        init();
        back_press.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(PriceListActivity.this, OfferMainPageActivity.class);
                startActivity(intent);
                finish();
            }
        });
        recyclerView.setLayoutManager(linearLayoutManager);
        viewdata();
    }

    private void viewdata() {
        ProgressDialog progressDialog=new ProgressDialog(PriceListActivity.this);
        progressDialog.setTitle("please wait....");
        progressDialog.show();;

        ApiInterfaceClass apiInterfaceClass= BaseUrlClass.getClient(PriceListActivity.this).create(ApiInterfaceClass.class);
        Call<PriceListModelClass> call=apiInterfaceClass.getPriceListData(cat_name);
        call.enqueue(new Callback<PriceListModelClass>() {
            @Override
            public void onResponse(Call<PriceListModelClass> call, Response<PriceListModelClass> response) {
                PriceListModelClass priceListModelClass=response.body();
                if(response.isSuccessful())
                {
                    if(priceListModelClass.status.equals("success"))
                    {
                        List<PriceListModelClass.PriceListModel> data=new ArrayList<>();
                        data=response.body().getPrice;
                        Log.d("data",data.toString());
                        PriceListAdpterClass adpterClass=new PriceListAdpterClass(PriceListActivity.this,data);
                        Log.d("dataad",adpterClass.toString());
                        if(adpterClass.getItemCount()>0)
                        {

                            recyclerView.setAdapter(adpterClass);
                            progressDialog.dismiss();
                        }
                        else
                        {
                            progressDialog.dismiss();
                            final Dialog dialog = new Dialog(PriceListActivity.this);
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
            }

            @Override
            public void onFailure(Call<PriceListModelClass> call, Throwable t) {
                Toast.makeText(PriceListActivity.this, "fauilure worliknf", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void init() {
        recyclerView=(RecyclerView)findViewById(R.id.price_list_recy);
        linearLayoutManager=new LinearLayoutManager(PriceListActivity.this);
        back_press=(ImageView)findViewById(R.id.price_lis_activity_back);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent=new Intent(PriceListActivity.this, OfferMainPageActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onStart() {
        super.onStart();
        viewdata();
    }

    @Override
    protected void onResume() {
        super.onResume();
        viewdata();
    }
}