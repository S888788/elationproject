package com.example.surataccount.AboutPack;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.surataccount.ApiInterface.ApiInterfaceClass;
import com.example.surataccount.BaseUrl.BaseUrlClass;
import com.example.surataccount.Dashboard.Dashboard;
import com.example.surataccount.Dashboard.SliderAdpter;
import com.example.surataccount.Dashboard.SliderGetterSetter;
import com.example.surataccount.R;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

public class AboutActivity extends AppCompatActivity {
RecyclerView recyclerView;
ImageView back_press;
    RequestQueue rq;
String slidUrl="http://japware.com/surataccount/api/sliderimage?slider_type=0";
    private SliderView sliderView;
    List<SliderGetterSetter> listImage=new ArrayList<>();
    LinearLayoutManager linearLayoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_about);
        init();
        sendRequest();
        viewtherory();
        back_press.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(AboutActivity.this, Dashboard.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void viewtherory() {
        ProgressDialog dialog=new ProgressDialog(AboutActivity.this);
        dialog.setTitle("please wait...");
        dialog.show();
        ApiInterfaceClass apiInterfaceClass= BaseUrlClass.getClient(AboutActivity.this).create(ApiInterfaceClass.class);
        Call<AboutModelClass> call=apiInterfaceClass.getTherory();
        call.enqueue(new Callback<AboutModelClass>() {
            @Override
            public void onResponse(Call<AboutModelClass> call, retrofit2.Response<AboutModelClass> response) {
                AboutModelClass aboutModelClass=response.body();
                if(response.isSuccessful())
                {
                    List<AboutModelClass.AboutSchedule> data=new ArrayList<>();
                    data=response.body().aboutData;
                    AboutAdpter aboutAdpter=new AboutAdpter(AboutActivity.this,data);
                    recyclerView.setLayoutManager(linearLayoutManager);
                    if(aboutAdpter.getItemCount()>0) {
                        recyclerView.setAdapter(aboutAdpter);
                        dialog.dismiss();
                    }
                    else
                    {
                        Toast.makeText(AboutActivity.this, "no data found..", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                }
            }

            @Override
            public void onFailure(Call<AboutModelClass> call, Throwable t) {
                Toast.makeText(AboutActivity.this, "no data found..", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void init() {
        recyclerView=(RecyclerView)findViewById(R.id.about_recycler);
        back_press=(ImageView)findViewById(R.id.about_activity_back);
        sliderView = findViewById(R.id.slider_back);
        linearLayoutManager=new LinearLayoutManager(AboutActivity.this);
    }
    private void sendRequest() {
        StringRequest stringRequest=new StringRequest(Request.Method.GET, slidUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

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
                                    sliderView.setScrollTimeInSec(2);

                                    // below line is for setting auto
                                    // cycle animation to our slider
                                    sliderView.setAutoCycle(true);

                                    // below line is use to start
                                    // the animation of our slider view.
                                    sliderView.startAutoCycle();

                                }

                            } else {
                                Toast.makeText(AboutActivity.this, "status failed", Toast.LENGTH_SHORT).show();
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
                Toast.makeText(AboutActivity.this, "errir", Toast.LENGTH_SHORT).show();
            }
        });
        rq= Volley.newRequestQueue(getApplicationContext());
        rq.add(stringRequest);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent=new Intent(AboutActivity.this, Dashboard.class);
        startActivity(intent);
        finish();

    }

    @Override
    protected void onStart() {
        super.onStart();
        sendRequest();

    }
}