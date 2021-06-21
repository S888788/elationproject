package com.example.surataccount.party;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.surataccount.Connection_Internet_Classs;
import com.example.surataccount.CoupanPack.CoupanSummaryActivity;
import com.example.surataccount.Dashboard.Dashboard;
import com.example.surataccount.R;
import com.example.surataccount.User.Session_Use;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PartyActivity extends AppCompatActivity {
    private String url="http://japware.com/surataccount/api/ledgersummary";

    RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private partyAdpterClass adpter;
    private List<PartyGetterSetterClass> items;
    RequestQueue requestQueue;
    ProgressBar progressBar;
    Connection_Internet_Classs connection_internet_classs;
    SwipeRefreshLayout swipeRefreshLayout;

    String mob;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportActionBar().show();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("KD's Party");

        setContentView(R.layout.activity_party);
        Intent intent=getIntent();
        String code=intent.getStringExtra("stcode");
        String name=intent.getStringExtra("stname");
        String balamt=intent.getStringExtra("balamt");

      //  led=getIntent().getStringExtra("led");

     //   Toast.makeText(this, ""+led, Toast.LENGTH_SHORT).show();


        Session_Use user=new Session_Use(PartyActivity.this);
        mob=user.getUsename();
        //Toast.makeText(this, "mobile"+mob, Toast.LENGTH_SHORT).show();
        Log.d("mob",mob);
        String otp=user.getOtp();
        // Toast.makeText(this, "otp is"+otp, Toast.LENGTH_SHORT).show();
        user.getPasswords();

        //Toast.makeText(this, "mob"+pass, Toast.LENGTH_SHORT).show();
        connection_internet_classs=new Connection_Internet_Classs(PartyActivity.this);
        swipeRefreshLayout=(SwipeRefreshLayout)findViewById(R.id.paty_swip);
        progressBar=(ProgressBar)findViewById(R.id.party_progress);
        progressBar.setVisibility(View.VISIBLE);
        recyclerView=(RecyclerView)findViewById(R.id.balacerecyclr);
        linearLayoutManager=new LinearLayoutManager(getApplicationContext());
        items=new ArrayList<>();
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(linearLayoutManager);


        fechjsondata();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // todo: goto back activity from here

                Intent intent = new Intent(PartyActivity.this, Dashboard.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void fechjsondata() {
        String u="http://japware.com/surataccount/api/ledgersummary?mobileno="+mob+"";
        Log.d("u",u);

        if (connection_internet_classs.isConnected()) {
            StringRequest stringRequest = new StringRequest(Request.Method.GET, u, new Response.Listener<String>() {

                @Override
                public void onResponse(String response) {
                    progressBar.setVisibility(View.GONE);
                    //Toast.makeText(PartyActivity.this, ""+response, Toast.LENGTH_SHORT).show();
                    if (!response.isEmpty()) {
                        try {

                            JSONObject object = new JSONObject(response);
                            if (object.getString("status").equalsIgnoreCase("success")) {
                                JSONArray jsonArray = object.getJSONArray("data");
                                if (jsonArray != null) {
                                    for (int i = 0; i < jsonArray.length(); i++) {
                                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                                        Collections.sort(items, PartyGetterSetterClass.BY_NAME_ALPHBETICAL);

                                        PartyGetterSetterClass getter_setter = new PartyGetterSetterClass(
                                                jsonObject.getString("PartyCode"),
                                                jsonObject.getString("PartyName"),
                                                jsonObject.getString("AmountPayable"),
                                                jsonObject.getString("AmountRecieved"),
                                                jsonObject.getString("BalanceAmt"),
                                                jsonObject.getString("OpeningBalance"));



                                        items.add(getter_setter);


                                    }
                                    if (items.size() > 0) {
                                        adpter = new partyAdpterClass(getApplicationContext(), items, swipeRefreshLayout);
                                      //  Toast.makeText(PartyActivity.this, "g"+adpter, Toast.LENGTH_SHORT).show();
                                        Log.d("h",adpter.toString());
                                        recyclerView.setAdapter(adpter);

                                    }
                                    else
                                    {
                                        final Dialog dialog = new Dialog(PartyActivity.this);
                                        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); // before
                                        dialog.setContentView(R.layout.nodara_found_layout_file);
                                        dialog.setCancelable(true);

                                        ((Button) dialog.findViewById(R.id.nodata_btn)).setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                dialog.dismiss();
                                                Intent intent=new Intent(PartyActivity.this,Dashboard.class);
                                                startActivity(intent);
                                                finish();
                                            }
                                        });

                                        dialog.show();

                                    }
                                } else {
                                    Toast.makeText(PartyActivity.this, "status failed", Toast.LENGTH_SHORT).show();
                                }

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }


                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    //Toast.makeText(PartyActivity.this, "gasg" + error.getMessage(), Toast.LENGTH_SHORT).show();
                    if (error instanceof TimeoutError || error instanceof NoConnectionError) {
                        Toast.makeText(PartyActivity.this, "NoConnection Error !", Toast.LENGTH_SHORT).show();
                    } else if (error instanceof AuthFailureError) {
                        Toast.makeText(PartyActivity.this, "Authentication Error !", Toast.LENGTH_SHORT).show();
                    } else if (error instanceof ServerError) {
                        Toast.makeText(PartyActivity.this, "Server side Error !", Toast.LENGTH_SHORT).show();
                    } else if (error instanceof NetworkError) {
                        Toast.makeText(PartyActivity.this, "Network Error !", Toast.LENGTH_SHORT).show();
                    } else if (error instanceof ParseError) {
                        Toast.makeText(PartyActivity.this, " Parse Error !", Toast.LENGTH_SHORT).show();
                    }


                }

            });
            requestQueue = Volley.newRequestQueue(getApplicationContext());
            int socketTimeout = 20000;
            RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
            stringRequest.setRetryPolicy(policy);
            requestQueue.add(stringRequest);
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.seach_menu,menu);
        MenuItem  searchitem=menu.findItem(R.id.action_search);
        SearchView searchView= (SearchView) searchitem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adpter.getFilter().filter(newText);
                return false;
            }
        });
        return true;
    }
    @Override
    public void onBackPressed()
    {
        Intent intent=new Intent(PartyActivity.this, Dashboard.class);
        startActivity(intent);
        finish();
    }








}