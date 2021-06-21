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
import com.example.surataccount.Photobook_Status.Phtobook_StatusActivity;
import com.example.surataccount.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class SchmeNameOrCuopanDetailsActivity extends AppCompatActivity {
LinearLayoutManager linearLayoutManager;
RecyclerView recyclerView;
ImageView cuopan_detail_back_pess;
    ProgressBar progressBar;
     String schamename="";
     List<SchemeNameDetailsGetterSetter> items=new ArrayList<>();
     SchemeNameDetailsAdpterClass adpter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_schme_name_or_cuopan_details);
        //get SchemeName

        schamename=getIntent().getStringExtra("schamename");

        cuopan_detail_back_pess=(ImageView)findViewById(R.id.cuopan_detail_back_pess);
        recyclerView=(RecyclerView)findViewById(R.id.scheme_detail_recycler_view);
        //progress bar
        progressBar=(ProgressBar)findViewById(R.id.coupan_detail_progreeBar);
        linearLayoutManager=new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        cuopan_detail_back_pess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SchmeNameOrCuopanDetailsActivity.this,CoupanOffersctivity2.class);
                startActivity(intent);
                finish();
            }
        });
        viewDatas();

    }

    private void viewDatas() {
        String url="http://japware.com/surataccount/api/schemesdetail?SchemeName="+schamename+"";
        Log.d("u",url);
        StringRequest stringRequest=new StringRequest(url, new Response.Listener<String>() {
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

                                    SchemeNameDetailsGetterSetter getter_setter= new SchemeNameDetailsGetterSetter(
                                            jsonObject.getString("SchemeId"),
                                            jsonObject.getString("SchemeCode"),
                                            jsonObject.getString("SchemeName"),
                                            jsonObject.getString("DepositeAmt"),
                                            jsonObject.getString("SchemeDetail"),
                                            jsonObject.getString("SchemeExpiry"));

                                    items.add(getter_setter);


                                }
                                if (items.size() > 0) {
                                    adpter = new SchemeNameDetailsAdpterClass(getApplicationContext(),items);
                                    recyclerView.setAdapter(adpter);
                                }
                                else
                                {
                                    final Dialog dialog = new Dialog(SchmeNameOrCuopanDetailsActivity.this);
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
                                Toast.makeText(SchmeNameOrCuopanDetailsActivity.this, "status failed", Toast.LENGTH_SHORT).show();
                            }


                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                else
                {
                    Toast.makeText(SchmeNameOrCuopanDetailsActivity.this, "respone waiting", Toast.LENGTH_SHORT).show();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (error instanceof TimeoutError || error instanceof NoConnectionError)
                {
                    Toast.makeText(SchmeNameOrCuopanDetailsActivity.this, "NoConnection Error !", Toast.LENGTH_SHORT).show();
                }
                else if(error instanceof AuthFailureError)
                {
                    Toast.makeText(SchmeNameOrCuopanDetailsActivity.this, "Authentication Error !", Toast.LENGTH_SHORT).show();
                }
                else  if(error instanceof ServerError)
                {
                    Toast.makeText(SchmeNameOrCuopanDetailsActivity.this, "Server side Error !", Toast.LENGTH_SHORT).show();
                }
                else if(error instanceof NetworkError)
                {
                    Toast.makeText(SchmeNameOrCuopanDetailsActivity.this, "Network Error !", Toast.LENGTH_SHORT).show();
                }
                else if(error instanceof ParseError)
                {
                    Toast.makeText(SchmeNameOrCuopanDetailsActivity.this, " Parse Error !", Toast.LENGTH_SHORT).show();
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
        Intent intent=new Intent(SchmeNameOrCuopanDetailsActivity.this,CoupanOffersctivity2.class);
        startActivity(intent);
        finish();
    }
}