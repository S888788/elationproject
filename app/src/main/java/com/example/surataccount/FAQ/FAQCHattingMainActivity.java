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

public class FAQCHattingMainActivity extends AppCompatActivity {
    RecyclerView faq_main_recler;
    LinearLayoutManager linearLayoutManager;
    String msg_form,tocket_no,mobile;
    EditText editText;
    Button btn_submit;

    String msg;
    private ImageView iv_mic;
    private static final int REQUEST_CODE_SPEECH_INPUT = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_f_a_q_c_hatting_main);
        init();
        linearLayoutManager=new LinearLayoutManager(FAQCHattingMainActivity.this);
        faq_main_recler.setLayoutManager(linearLayoutManager);
        Intent intent=getIntent();
       msg_form= intent.getStringExtra("msg_form");
       tocket_no=intent.getStringExtra("ticket_no");
       Log.d("tocket_no",tocket_no);
        Session_Use session_use=new Session_Use(FAQCHattingMainActivity.this);
        mobile=session_use.getUsename();

        iv_mic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
                intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Speak to text");

                try {
                    startActivityForResult(intent, REQUEST_CODE_SPEECH_INPUT);
                }
                catch (Exception e) {
                    Toast.makeText(FAQCHattingMainActivity.this, " " + e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                msg=editText.getText().toString().trim();
                Log.d("mdf",msg);
                ProgressDialog progressDialog=new ProgressDialog(FAQCHattingMainActivity.this);
                progressDialog.setMessage("Please wait..");
                progressDialog.show();
                ApiInterfaceClass apiInterfaceClass= BaseUrlClass.getClient(FAQCHattingMainActivity.this).create(ApiInterfaceClass.class);
                Call<FAQInertDataModel> call=apiInterfaceClass.getFaqdata(mobile,msg,tocket_no,"client");
                call.enqueue(new Callback<FAQInertDataModel>() {
                    @Override
                    public void onResponse(Call<FAQInertDataModel> call, Response<FAQInertDataModel> response) {
                        FAQInertDataModel faqInertDataModel=response.body();

                        if(response.isSuccessful())
                        {
                            progressDialog.dismiss();

                            Toast.makeText(FAQCHattingMainActivity.this, faqInertDataModel.meassage, Toast.LENGTH_SHORT).show();
                            viewData();
                            editText.setText(" ");
                        }

                    }

                    @Override
                    public void onFailure(Call<FAQInertDataModel> call, Throwable t) {
                        Toast.makeText(FAQCHattingMainActivity.this, "Failure", Toast.LENGTH_SHORT).show();

                    }
                });

            }

        });
        viewData();
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_SPEECH_INPUT) {
            if (resultCode == RESULT_OK && data != null) {
                ArrayList<String> result = data.getStringArrayListExtra(
                        RecognizerIntent.EXTRA_RESULTS);
                editText.setText(Objects.requireNonNull(result).get(0));
            }
        }
    }

    private void viewData() {
        ProgressDialog progressDialog=new ProgressDialog(FAQCHattingMainActivity.this);
        progressDialog.setMessage("Please wait..");
        progressDialog.show();
        ApiInterfaceClass apiInterfaceClass= BaseUrlClass.getClient(FAQCHattingMainActivity.this).create(ApiInterfaceClass.class);
        Call<FAQMainChattingModel> call=apiInterfaceClass.getChattSelectWithTicket(mobile,msg_form,tocket_no);
        call.enqueue(new Callback<FAQMainChattingModel>() {
            @Override
            public void onResponse(Call<FAQMainChattingModel> call, Response<FAQMainChattingModel> response) {
                FAQMainChattingModel faqMainChattingModel=response.body();
                if(response.isSuccessful())
                {
                    if(faqMainChattingModel.status.equals("success"))
                    {
                        List<FAQMainChattingModel.FAQData> data=new ArrayList<>();
                        data=response.body().getFAQ;
                        FAQmainAdpter adpter=new FAQmainAdpter(FAQCHattingMainActivity.this,data);
                        if(adpter.getItemCount()>0)
                        {
                            progressDialog.dismiss();
                            faq_main_recler.setAdapter(adpter);

                        }
                        else
                        {
                            progressDialog.dismiss();
                            final Dialog dialog = new Dialog(FAQCHattingMainActivity.this);
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
                else
                {
                    Toast.makeText(FAQCHattingMainActivity.this, "response not suceess", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<FAQMainChattingModel> call, Throwable t) {
                Toast.makeText(FAQCHattingMainActivity.this, "failure", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void init()
    {
        faq_main_recler = (RecyclerView) findViewById(R.id.faq_main_recler);
        editText=(EditText)findViewById(R.id.faq_edit_chat_token_ge_all);
        btn_submit=(Button)findViewById(R.id.faq_btn_submit_token_ge_all);
        iv_mic = (ImageView) findViewById(R.id.faq_iv_mic_token_ge_all);
    }

    @Override
    protected void onStart() {
        super.onStart();
        viewData();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent=new Intent(FAQCHattingMainActivity.this,FAQListActivity.class);
        startActivity(intent);
        finish();
    }
}