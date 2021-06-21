package com.example.surataccount.SchemePack;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.surataccount.BuyOfferPhotoCover.OfferMainPageActivity;
import com.example.surataccount.Photobook_Status.Phtobook_StatusActivity;
import com.example.surataccount.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class CoupanOffersctivity2 extends AppCompatActivity {
ImageView back_press;
RecyclerView recyclerView;
LinearLayoutManager linearLayoutManager;
List<NamesO_Gettter_Setteer_Class> items=new ArrayList<>();
NamesO_Adpter_Classs adpter;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_coupan_offersctivity2);

        back_press=(ImageView)findViewById(R.id.coupan_back_buttonk);
        recyclerView=(RecyclerView)findViewById(R.id.offe_myschems_RecyclerView);
        //progress bar
        progressBar=(ProgressBar)findViewById(R.id.coupan_offer_progreeBar);
        linearLayoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

      back_press.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              Intent intent=new Intent(CoupanOffersctivity2.this, OfferMainPageActivity.class);
              startActivity(intent);
              finish();
          }
      });
        viewDatas();

    }

    private void viewDatas() {
        String u="http://japware.com/surataccount/api/allschemes";
        Log.d("u",u);
        StringRequest stringRequest=new StringRequest(u, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("respone",response);
                progressBar.setVisibility(View.GONE);
                if(!response.isEmpty())
                {
                    try {
                        JSONObject object = new JSONObject(response);
                        if (object.getString("status").equalsIgnoreCase("success")) {
                            JSONArray jsonArray = object.getJSONArray("data");
                            if (jsonArray != null) {
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);

                                   NamesO_Gettter_Setteer_Class getter_setter= new NamesO_Gettter_Setteer_Class(
                                            jsonObject.getString("SchemeName") );

                                    items.add(getter_setter);


                                }
                                if (items.size() > 0) {
                                    adpter = new NamesO_Adpter_Classs(getApplicationContext(),items);
                                    recyclerView.setAdapter(adpter);
                                }
                                else
                                {
                                    final Dialog dialog = new Dialog(CoupanOffersctivity2.this);
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
                            } else {
                                Toast.makeText(CoupanOffersctivity2.this, "status failed", Toast.LENGTH_SHORT).show();
                            }


                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                else
                {
                    Toast.makeText(CoupanOffersctivity2.this, "respone waiting", Toast.LENGTH_SHORT).show();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (error instanceof TimeoutError || error instanceof NoConnectionError)
                {
                    Toast.makeText(CoupanOffersctivity2.this, "NoConnection Error !", Toast.LENGTH_SHORT).show();
                }
                else if(error instanceof AuthFailureError)
                {
                    Toast.makeText(CoupanOffersctivity2.this, "Authentication Error !", Toast.LENGTH_SHORT).show();
                }
                else  if(error instanceof ServerError)
                {
                    Toast.makeText(CoupanOffersctivity2.this, "Server side Error !", Toast.LENGTH_SHORT).show();
                }
                else if(error instanceof NetworkError)
                {
                    Toast.makeText(CoupanOffersctivity2.this, "Network Error !", Toast.LENGTH_SHORT).show();
                }
                else if(error instanceof ParseError)
                {
                    Toast.makeText(CoupanOffersctivity2.this, " Parse Error !", Toast.LENGTH_SHORT).show();
                }

            }
        });
       RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        int socketTimeout = 20000;
        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        stringRequest.setRetryPolicy(policy);
        requestQueue.add(stringRequest);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent=new Intent(CoupanOffersctivity2.this, OfferMainPageActivity.class);
        startActivity(intent);
        finish();
    }
}