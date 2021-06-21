package com.example.surataccount.Photobook_Status;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
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
import com.android.volley.RetryPolicy;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.surataccount.ApiInterface.ApiInterfaceClass;
import com.example.surataccount.BaseUrl.BaseUrlClass;
import com.example.surataccount.Dashboard.Dashboard;
import com.example.surataccount.R;
import com.example.surataccount.User.Session_Use;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Phtobook_StatusActivity extends AppCompatActivity {
    ImageView photobook_status;
    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;

    private Photo_Status_Adpter_class adpter;
    String mobile,formdate,todate;
    EditText et_formDate,et_todate;
    Button btn_photo_search,btn_viewAllPending_billing_photobook_status;
    DatePickerDialog datePickerDialog;

    String monthh, dayy,st_code;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Session_Use use=new Session_Use(Phtobook_StatusActivity.this);
        mobile = use.getUsename();
        Log.d("pho stats",mobile);
        setContentView(R.layout.activity_phtobook__status);
      init();
        SharedPreferences sh = getSharedPreferences("stucode", Context.MODE_PRIVATE);
       st_code= sh.getString("st_code", "");
        //form date
        et_formDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar cldrs = Calendar.getInstance();
                int day = cldrs.get(Calendar.DAY_OF_MONTH);
                int month = cldrs.get(Calendar.MONTH);
                int year = cldrs.get(Calendar.YEAR);
                datePickerDialog = new DatePickerDialog(Phtobook_StatusActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {

                        monthOfYear =  monthOfYear + 1;
                        if( monthOfYear>9)
                        {
                            monthh= monthOfYear+"";
                        }
                        else
                        {
                            monthh="0"+monthOfYear;
                        }
                        if(dayOfMonth>9)
                        {
                            dayy=dayOfMonth+"";
                        }
                        else
                        {
                            dayy="0"+dayOfMonth;
                        }
                        et_formDate.setText(dayy+"/"+monthh+"/"+year);


                        formdate=(year+"-"+monthh+"-"+dayy);



                    }
                }, year, month, day);
                datePickerDialog.show();

            }
        });
    //to date
        et_todate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar cldrs = Calendar.getInstance();
                int day = cldrs.get(Calendar.DAY_OF_MONTH);
                int month = cldrs.get(Calendar.MONTH);
                int year = cldrs.get(Calendar.YEAR);
                datePickerDialog = new DatePickerDialog(Phtobook_StatusActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {

                        monthOfYear =  monthOfYear + 1;
                        if( monthOfYear>9)
                        {
                            monthh= monthOfYear+"";
                        }
                        else
                        {
                            monthh="0"+monthOfYear;
                        }
                        if(dayOfMonth>9)
                        {
                            dayy=dayOfMonth+"";
                        }
                        else
                        {
                            dayy="0"+dayOfMonth;
                        }
                        et_todate.setText(dayy+"/"+monthh+"/"+year);


                        todate=(year+"-"+monthh+"-"+dayy);



                    }
                }, year, month, day);
                datePickerDialog.show();

            }
        });


        photobook_status.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Phtobook_StatusActivity.this, Dashboard.class);

                startActivity(intent);
                finish();
            }
        });
        btn_photo_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               viewAllpadingdata(formdate,todate,st_code);
            }
        });


        linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);

        btn_viewAllPending_billing_photobook_status.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                jsonfetchDataSearch();
            }
        });


    }

    private void init() {
        recyclerView = (RecyclerView) findViewById(R.id.photo_book_status_recycler_view);

        photobook_status=(ImageView)findViewById(R.id.photo_status_back_button);
        et_formDate=(EditText)findViewById(R.id.et_photobook_search_formdate);
        et_todate=(EditText)findViewById(R.id.et_photobook_seach_todate);
        btn_viewAllPending_billing_photobook_status=(Button)findViewById(R.id.btn_viewAllPending_billing_photobook_status);
        btn_photo_search=(Button)findViewById(R.id.btn_photo_search_phookStatus);
    }

    private void jsonfetchDataSearch() {
        ProgressDialog progressDialog=new ProgressDialog(Phtobook_StatusActivity.this);
        progressDialog.setTitle("please wait...");
        progressDialog.show();
        ApiInterfaceClass apiInterfaceClass= BaseUrlClass.getClient(Phtobook_StatusActivity.this).create(ApiInterfaceClass.class);
        Call<Photobook_Gette_Pojo_Class> call=apiInterfaceClass.getPhotoBookStatus(mobile);
        call.enqueue(new Callback<Photobook_Gette_Pojo_Class>() {
            @Override
            public void onResponse(Call<Photobook_Gette_Pojo_Class> call, Response<Photobook_Gette_Pojo_Class> response) {
                Photobook_Gette_Pojo_Class gette_pojo_class=response.body();
                if(response.isSuccessful())
                {
                    if(gette_pojo_class.status.equals("success"))
                    {
                        List<Photobook_Gette_Pojo_Class.PriceListModel> data=new ArrayList<>();
                        data=response.body().getPrice;
                        adpter=new Photo_Status_Adpter_class(Phtobook_StatusActivity.this,data);
                        if(adpter.getItemCount()>0)
                        {
                            progressDialog.dismiss();
                            recyclerView.setAdapter(adpter);
                        }
                        else
                        {
                            Toast.makeText(Phtobook_StatusActivity.this, "no data", Toast.LENGTH_SHORT).show();
                        }


                    }
                }
            }

            @Override
            public void onFailure(Call<Photobook_Gette_Pojo_Class> call, Throwable t) {
                Toast.makeText(Phtobook_StatusActivity.this, "failed ", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void viewAllpadingdata(String formdates,String todates,String st_codes) {
        ProgressDialog progressDialog=new ProgressDialog(Phtobook_StatusActivity.this);
        progressDialog.setTitle("please wait...");
        progressDialog.show();
        ApiInterfaceClass apiInterfaceClass= BaseUrlClass.getClient(Phtobook_StatusActivity.this).create(ApiInterfaceClass.class);
        Call<PhotobookSearchingDateGetterSeter> call=apiInterfaceClass.getPhotoBookStatusFormDate(formdates,todates,st_codes);
        Log.d("call",call.toString());
        call.enqueue(new Callback<PhotobookSearchingDateGetterSeter>() {
            @Override
            public void onResponse(Call<PhotobookSearchingDateGetterSeter> call, Response<PhotobookSearchingDateGetterSeter> response) {
                PhotobookSearchingDateGetterSeter gette_pojo_class=response.body();
                Log.d("res",response.body().toString());
                if(response.isSuccessful())
                {
                    if(gette_pojo_class.status.equals("success"))
                    {
                        List<PhotobookSearchingDateGetterSeter.PriceListModelsss> data=new ArrayList<>();
                        data=response.body().getPricefdf;
                        Log.d("data",data.toString());
                     PhotoBookSearchingAdpter  adpter=new PhotoBookSearchingAdpter(Phtobook_StatusActivity.this,data);
                        if(adpter.getItemCount()>0)
                        {
                            progressDialog.dismiss();
                            recyclerView.setAdapter(adpter);
                        }
                        else
                        {
                            Toast.makeText(Phtobook_StatusActivity.this, "no data", Toast.LENGTH_SHORT).show();
                        }


                    }
                }
            }

            @Override
            public void onFailure(Call<PhotobookSearchingDateGetterSeter> call, Throwable t) {
                Toast.makeText(Phtobook_StatusActivity.this, "failed ", Toast.LENGTH_SHORT).show();
            }
        });



    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent=new Intent(Phtobook_StatusActivity.this, Dashboard.class);
        startActivity(intent);
        finish();
    }
}







