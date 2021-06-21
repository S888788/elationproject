package com.example.surataccount.BookSchedule;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.surataccount.ApiInterface.ApiInterfaceClass;
import com.example.surataccount.BaseUrl.BaseUrlClass;
import com.example.surataccount.Dashboard.Dashboard;
import com.example.surataccount.R;

import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BookScheduleActivity extends AppCompatActivity {
EditText select_schedule_date;
Button btn_continue;
String monthh, dayy,sch_date;
RecyclerView schedule_recycler;
LinearLayoutManager linearLayout;
String dedigner_id="1";
SharedPreferences sharedPreferences;
ProgressBar book_sch_progress;
ImageView back_arrow;
View view_book_sche;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_schedule);
        init();
        //back button click
        back_arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(BookScheduleActivity.this, Dashboard.class);
                startActivity(intent);
                finish();

            }
        });
        select_schedule_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar= Calendar.getInstance();
                int year=calendar.get(Calendar.YEAR);
                int month=calendar.get(Calendar.MONTH);
                int day=calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog=new DatePickerDialog(BookScheduleActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                        Log.d("mont",String.valueOf(month));
                        Log.d("da",String.valueOf(dayOfMonth));
                        month= month+1;
                        Log.d("month",String.valueOf(month));
                        if(month>9)
                        {
                            monthh=month+"";
                            Log.d("monthnn",monthh);

                        }
                        else
                        {
                            monthh="0"+month;
                            Log.d("monthnn",monthh);
                        }
                        if(dayOfMonth>9)
                        {
                            dayy=dayOfMonth+"";
                            Log.d("day",dayy);
                        }
                        else
                        {
                            dayy="0"+dayOfMonth;
                            Log.d("day",dayy);
                        }
                        select_schedule_date.setText(dayy+"/"+monthh+"/"+year);
                        sch_date=(year+"-"+monthh+"-"+dayy);
                        Log.d("formate date",sch_date);

                    }
                },year,month,day);
                datePickerDialog.show();


            }
        });
        btn_continue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(select_schedule_date.getText().toString().isEmpty())
                {
                    Toast.makeText(BookScheduleActivity.this, "Please Select Schedule Date !.. ", Toast.LENGTH_LONG).show();
                }
                else
                {
                    view_book_sche.setVisibility(View.VISIBLE);
                    APIhandeling(sch_date,dedigner_id);
                }
            }
        });
    }
    private void APIhandeling(final String sch_date, final String dedigner_id) {
        book_sch_progress.setVisibility(View.VISIBLE);
        view_book_sche.setVisibility(View.VISIBLE);
        ApiInterfaceClass apiInterfaceClass= BaseUrlClass.getClient(BookScheduleActivity.this).create(ApiInterfaceClass.class);
        Call<BookScheduleModelClass> call=apiInterfaceClass.getBookScheduleData(sch_date,"1");
        call.enqueue(new Callback<BookScheduleModelClass>() {
            @Override
            public void onResponse(Call<BookScheduleModelClass> call, Response<BookScheduleModelClass> response) {
                BookScheduleModelClass bookScheduleModelClass=response.body();
                if(response.isSuccessful())
                {
                    if(bookScheduleModelClass.status.equals("success"))
                    {
                        //Toast.makeText(BookScheduleActivity.this, bookScheduleModelClass.meassage, Toast.LENGTH_SHORT).show();
                        List<BookScheduleModelClass.BookSchedule> bookScheduleList=response.body().bookSchedules;
                         sharedPreferences = getSharedPreferences("ori", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("sch_date", sch_date);
                        editor.putString("designer_id",dedigner_id);
                        editor.commit();
                        view_book_sche.setVisibility(View.VISIBLE);
                        linearLayout=new LinearLayoutManager(BookScheduleActivity.this);
                        book_sch_progress.setVisibility(View.GONE);
                        schedule_recycler.setLayoutManager(linearLayout);
                        BookScheduleAdpter adpter=new BookScheduleAdpter(BookScheduleActivity.this,bookScheduleList);
                        if(adpter.getItemCount()>0)
                        {
                            schedule_recycler.setAdapter(adpter);
                            select_schedule_date.setText("");
                            schedule_recycler.setVisibility(View.VISIBLE);
                        }
                        else
                        {
                            Toast.makeText(BookScheduleActivity.this, "No data found..", Toast.LENGTH_LONG).show();
                            schedule_recycler.setVisibility(View.GONE);
                            select_schedule_date.setText("");
                        }


                    }
                    else
                    {
                        Toast.makeText(BookScheduleActivity.this, "status not matched", Toast.LENGTH_SHORT).show();
                    }
                }
            }
            @Override
            public void onFailure(Call<BookScheduleModelClass> call, Throwable t) {
                book_sch_progress.setVisibility(View.VISIBLE);
                Toast.makeText(BookScheduleActivity.this, "failure", Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void init()
    {
        select_schedule_date=(EditText)findViewById(R.id.book_schdule_et_sele_date);
        btn_continue=(Button)findViewById(R.id.btn_book_sch);
        schedule_recycler=(RecyclerView)findViewById(R.id.book_sch_recycler);
        book_sch_progress=(ProgressBar)findViewById(R.id.book_sch_progress);
        back_arrow=(ImageView)findViewById(R.id.im_book_sch_back);
        view_book_sche=(View)findViewById(R.id.view_book_sche);
    }

    @Override
    protected void onStart() {
        if(sch_date!=null && dedigner_id!=null) {
            APIhandeling(sch_date, dedigner_id);
            view_book_sche.setVisibility(View.VISIBLE);
            book_sch_progress.setVisibility(View.GONE);
        }
        else
        {
            super.onStart();
            view_book_sche.setVisibility(View.GONE);
            book_sch_progress.setVisibility(View.GONE);
            SharedPreferences sh = getSharedPreferences("ori", MODE_PRIVATE);
            sch_date = sh.getString("sch_date", "");
            dedigner_id = sh.getString("designer_id", "");

        }

    }
    @Override
    protected void onPause() {
        super.onPause();
        book_sch_progress.setVisibility(View.VISIBLE);
        SharedPreferences sh = getSharedPreferences("ori",Context.MODE_PRIVATE);
       sch_date = sh.getString("sch_date", "");
       dedigner_id= sh.getString("designer_id", "");

    }

    @Override
    protected void onResume() {
        super.onResume();
        if(sch_date!=null && dedigner_id!=null)
        {
            APIhandeling(sch_date,dedigner_id);
        }
        else
        {
            Toast.makeText(this, "resume working..", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent=new Intent(BookScheduleActivity.this, Dashboard.class);
        startActivity(intent);
        finish();
    }
}