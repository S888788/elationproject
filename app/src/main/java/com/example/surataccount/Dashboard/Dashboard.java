package com.example.surataccount.Dashboard;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Adapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.surataccount.Adpter.DashboradAdpterClass;
import com.example.surataccount.BookSchedule.BookScheduleActivity;
import com.example.surataccount.BuyOfferPhotoCover.OfferMainPageActivity;
import com.example.surataccount.CoupanPack.CoupanSummaryActivity;
import com.example.surataccount.FAQ.FAQListActivity;
import com.example.surataccount.FAQ.NewTicketActivity;
import com.example.surataccount.LastedDesign.LastedDesignActivity;
import com.example.surataccount.Photobook_Status.Phtobook_StatusActivity;
import com.example.surataccount.R;
import com.example.surataccount.User.LoginActivity;
import com.example.surataccount.User.Session_Use;
import com.example.surataccount.party.PartyActivity;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


public class Dashboard extends AppCompatActivity  {
    String mobile;
    RecyclerView dashboard_gridview;
    List<String> tiule=new ArrayList<>();
    RequestQueue rq;
    List<Integer> images=new ArrayList<>();
    DashboradAdpterClass adapter;
    String slidUrl="http://japware.com/surataccount/api/sliderimage?slider_type=0";
    private SliderView sliderView;
    List<SliderGetterSetter> listImage=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        //this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.dashboad_activity);

        Session_Use user=new Session_Use(Dashboard.this);
        mobile =user.getUsename();
        Toast.makeText(this, "mob"+mobile, Toast.LENGTH_SHORT).show();
        SharedPreferences shared = getSharedPreferences("mypdg", MODE_PRIVATE);
        String channel = (shared.getString("Distubutercode", ""));
        Log.d("dis",channel);

        init();
        images.add(R.drawable.ledger);
        images.add(R.drawable.design);
        images.add(R.drawable.photobook);
        images.add(R.drawable.coupon);
        images.add(R.drawable.readymadecover);
        images.add(R.drawable.pricelist);
        images.add(R.drawable.scheduler);
        images.add(R.drawable.support);
        images.add(R.drawable.about);
        images.add(R.drawable.logout);


    tiule.add("Ledger & Payment");
        tiule.add("Photobook Design");
        tiule.add("Photobook Status");
        tiule.add("Coupon");
        tiule.add("ReadyMade Cover");
        tiule.add("PriceList/Offer");
        tiule.add("Scheduler");
        tiule.add("FAQ/Support");
        tiule.add("About Us");
        tiule.add("Logout");
        adapter=new DashboradAdpterClass(Dashboard.this,tiule,images);
        dashboard_gridview.setLayoutManager(new GridLayoutManager(Dashboard.this,3));
        dashboard_gridview.setAdapter(adapter);
        sendRequest();
    }

    private void sendRequest() {
        StringRequest stringRequest=new StringRequest(Request.Method.GET, slidUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
Log.d("imf",response);
                if (!response.isEmpty()) {
                    try {

                        JSONObject object = new JSONObject(response);
                        if (object.getString("status").equalsIgnoreCase("success12233")) {
                            JSONArray jsonArray = object.getJSONArray("data");
                            if (jsonArray != null) {
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    SliderGetterSetter sliderGetterSetter=new SliderGetterSetter(jsonObject.getString("imageurl"));

                                    listImage.add(sliderGetterSetter);
                                    SliderAdpter sliderAdpter=new SliderAdpter(getApplicationContext(),listImage);
                                    sliderView.setSliderAdapter(sliderAdpter);

                                    sliderView.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
                                    sliderView.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH);
                                    sliderView.setScrollTimeInSec(3);

                                    // below line is for setting auto
                                    // cycle animation to our slider
                                    sliderView.setAutoCycle(true);

                                    // below line is use to start
                                    // the animation of our slider view.
                                    sliderView.startAutoCycle();

                                }

                            } else {
                                Toast.makeText(Dashboard.this, "status failed", Toast.LENGTH_SHORT).show();
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
                Toast.makeText(Dashboard.this, "errir", Toast.LENGTH_SHORT).show();
            }
        });
        rq= Volley.newRequestQueue(getApplicationContext());
        rq.add(stringRequest);
    }

    private void init() {
        dashboard_gridview=(RecyclerView)findViewById(R.id.dashboard_recycler_view);
        sliderView = findViewById(R.id.slider_font);
    }


    @Override
    public void onBackPressed()
    {
        final AlertDialog.Builder builder=new AlertDialog.Builder(Dashboard.this);
        builder.setMessage("Are you sure want to do this ? ");
        builder.setCancelable(true);
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {

                dialog.cancel();
            }
        });
        builder.setPositiveButton("Yes !", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                finish();
            }
        });
        AlertDialog alertDialog=builder.create();
        alertDialog.show();
    }
}
