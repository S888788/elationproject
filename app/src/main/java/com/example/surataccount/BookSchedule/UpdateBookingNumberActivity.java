package com.example.surataccount.BookSchedule;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.surataccount.ApiInterface.ApiInterfaceClass;
import com.example.surataccount.BaseUrl.BaseUrlClass;
import com.example.surataccount.R;
import com.example.surataccount.User.Session_Use;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdateBookingNumberActivity extends AppCompatActivity {
Spinner sp_book_sch_up;
Button btn_sub;
String sh_date,booking_no,sch_time,desinger_id;
ImageView back_arrow;
    List<String> total_data=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_update_booking_number);
        init();
        Intent intent=getIntent();
        booking_no=intent.getStringExtra("booking_no");
        sh_date=intent.getStringExtra("sch_date");
        sch_time=intent.getStringExtra("sch_start_time");
        desinger_id=intent.getStringExtra("designer_id");

        viewData();
        back_arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(UpdateBookingNumberActivity.this,BookScheduleActivity.class);
                startActivity(intent);
                finish();

            }
        });
        total_data.add("Select Booking Number");
        btn_sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(sp_book_sch_up.getSelectedItemPosition()==0)
                {
                    Toast.makeText(UpdateBookingNumberActivity.this, "select booking number", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    booking_no=sp_book_sch_up.getSelectedItem().toString();
                    Log.d("spinner value si",booking_no);
                    UpdateApiHandlin(sh_date,desinger_id,sch_time,booking_no);
                }

            }
        });

    }

    private void UpdateApiHandlin(String sh_date, String desinger_id, String sch_time, String booking_no) {
       ApiInterfaceClass apiInterfaceClass= BaseUrlClass.getClient(UpdateBookingNumberActivity.this).create(ApiInterfaceClass.class);
        Call<BookScheduUodateBookingNumber> call=apiInterfaceClass.getBookingUpdateNumber(sh_date,desinger_id,sch_time,booking_no);
        call.enqueue(new Callback<BookScheduUodateBookingNumber>() {
            @Override
            public void onResponse(Call<BookScheduUodateBookingNumber> call, Response<BookScheduUodateBookingNumber> response) {
                BookScheduUodateBookingNumber bookScheduUodateBookingNumber=response.body();
                if(response.isSuccessful())
                {
                    if(bookScheduUodateBookingNumber.status.equals("Success"))
                    {
                        Toast.makeText(UpdateBookingNumberActivity.this, ""+bookScheduUodateBookingNumber.meassage, Toast.LENGTH_SHORT).show();
                        List<BookScheduUodateBookingNumber.BookingNumerUpdate> updateList=response.body().updatewBookingNumber;
                        Intent intent=new Intent(UpdateBookingNumberActivity.this,BookScheduleActivity.class);
                        startActivity(intent);
                        finish();

                    }
                    else
                    {
                        Toast.makeText(UpdateBookingNumberActivity.this, "status not matched", Toast.LENGTH_SHORT).show();
                    }

                }
                else
                {
                    Toast.makeText(UpdateBookingNumberActivity.this, "response failed", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<BookScheduUodateBookingNumber> call, Throwable t) {
                Toast.makeText(UpdateBookingNumberActivity.this, "failed", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void viewData() {
        Session_Use user=new Session_Use(UpdateBookingNumberActivity.this);
        String mobile =user.getUsename();
        Log.d("updatebook",mobile);
        ApiInterfaceClass apiInterfaceClass= BaseUrlClass.getClient(UpdateBookingNumberActivity.this).create(ApiInterfaceClass.class);
        Call<UpdateBookingNumberModel> call=apiInterfaceClass.getBookingNumber(mobile);

        call.enqueue(new Callback<UpdateBookingNumberModel>() {
            @Override
            public void onResponse(Call<UpdateBookingNumberModel> call, Response<UpdateBookingNumberModel> response) {
                UpdateBookingNumberModel updateBookingNumberModel=response.body();
                List<UpdateBookingNumberModel.BookingSelect> updateList=response.body().getBooling;
                if(response.isSuccessful())
                {
                    if(updateBookingNumberModel.status.equals("success"))
                    {
                        for(int o=0;o<updateList.size();o++)
                        {
                             total_data.add(updateList.get(o).BookingNumber);

                        }
                      ArrayAdapter  arrayAdapter = new ArrayAdapter(UpdateBookingNumberActivity.this, android.R.layout.simple_list_item_1, total_data);
                       sp_book_sch_up.setAdapter(arrayAdapter);
                    }
                    else
                    {
                        Toast.makeText(UpdateBookingNumberActivity.this, "status not matched..", Toast.LENGTH_SHORT).show();
                    }
                }
                else
                {
                    Toast.makeText(UpdateBookingNumberActivity.this, "response fail", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<UpdateBookingNumberModel> call, Throwable t) {
                Toast.makeText(UpdateBookingNumberActivity.this, "fail", Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void init() {
        sp_book_sch_up=(Spinner)findViewById(R.id.sp_book_sch_up);
        btn_sub=(Button)findViewById(R.id.btn_book_sch_update);
        back_arrow=(ImageView)findViewById(R.id.im_book_sch_update_back);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent=new Intent(UpdateBookingNumberActivity.this,BookScheduleActivity.class);
        startActivity(intent);
        finish();
    }
}