package com.example.surataccount.FAQ;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.surataccount.ApiInterface.ApiInterfaceClass;
import com.example.surataccount.BaseUrl.BaseUrlClass;
import com.example.surataccount.Dashboard.Dashboard;
import com.example.surataccount.Photobook_Status.Phtobook_StatusActivity;
import com.example.surataccount.R;
import com.example.surataccount.User.Session_Use;


import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FAQListActivity extends AppCompatActivity {
    String mobile;
    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    ImageView im_faq_list_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_f_a_q_list);
       inits();
       linearLayoutManager=new LinearLayoutManager(FAQListActivity.this);
       recyclerView.setLayoutManager(linearLayoutManager);
        Session_Use user=new Session_Use(FAQListActivity.this);
        mobile =user.getUsename();
        Log.d("faqnmob",mobile);
        FaqChattingSelect();
        im_faq_list_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(FAQListActivity.this, Dashboard.class);
                startActivity(intent);
                finish();
            }
        });

    }

    private void FaqChattingSelect() {
        ProgressDialog progressDialog=new ProgressDialog(FAQListActivity.this);
        progressDialog.setMessage("Please wait..");
        progressDialog.show();
        ApiInterfaceClass apiInterfaceClass=BaseUrlClass.getClient(FAQListActivity.this).create(ApiInterfaceClass.class);
        Call<ChattingSelectModel> call=apiInterfaceClass.getChattSelectData(mobile,"client");
        call.enqueue(new Callback<ChattingSelectModel>() {
            @Override
            public void onResponse(Call<ChattingSelectModel> call, Response<ChattingSelectModel> response) {
                ChattingSelectModel chattingSelectModel=response.body();
                if(response.isSuccessful())
                {
                    if(chattingSelectModel.status.equals("success"))
                    {
                        List<ChattingSelectModel.SelectCHat> data=new ArrayList<>();
                         data= response.body().getChattingSelect;
                        ChattingAdpterClass adpterClass=new ChattingAdpterClass(FAQListActivity.this,data);
                         if(adpterClass.getItemCount()>0)
                         {
                             progressDialog.dismiss();
                             recyclerView.setAdapter(adpterClass);
                         }
                         else
                         {
                             progressDialog.dismiss();
                             final Dialog dialog = new Dialog(FAQListActivity.this);
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
            public void onFailure(Call<ChattingSelectModel> call, Throwable t) {
                Toast.makeText(FAQListActivity.this, "failure", Toast.LENGTH_SHORT).show();
            }
        });
    }



    private void inits() {
        recyclerView=(RecyclerView)findViewById(R.id.faq_listview);
        im_faq_list_back=(ImageView)findViewById(R.id.im_faq_list_back);
    }

    @Override
    protected void onStart() {
        super.onStart();
        FaqChattingSelect();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent=new Intent(FAQListActivity.this, Dashboard.class);
        startActivity(intent);
        finish();
    }
}

