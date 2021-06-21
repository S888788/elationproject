package com.example.surataccount.FAQ;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.surataccount.ApiInterface.ApiInterfaceClass;
import com.example.surataccount.BaseUrl.BaseUrlClass;
import com.example.surataccount.Dashboard.Dashboard;
import com.example.surataccount.R;
import com.example.surataccount.User.Session_Use;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewTicketActivity extends AppCompatActivity {
    EditText editText;
    Button btn_submit;
    String mobile;
    String msg;
    private ImageView iv_mic,im_book_new_ticket_back;
    private static final int REQUEST_CODE_SPEECH_INPUT = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_new_ticket);
        init();
        Session_Use user=new Session_Use(NewTicketActivity.this);
        mobile =user.getUsename();
        Log.d("faqnmob",mobile);
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
                    Toast.makeText(NewTicketActivity.this, " " + e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                msg=editText.getText().toString().trim();
                Log.d("mdf",msg);
                ProgressDialog progressDialog=new ProgressDialog(NewTicketActivity.this);
                progressDialog.setMessage("Please wait..");
                progressDialog.show();
                ApiInterfaceClass apiInterfaceClass= BaseUrlClass.getClient(NewTicketActivity.this).create(ApiInterfaceClass.class);
                Call<FAQInertDataModel> call=apiInterfaceClass.getFaqdata(mobile,msg,"0","client");
                call.enqueue(new Callback<FAQInertDataModel>() {
                    @Override
                    public void onResponse(Call<FAQInertDataModel> call, Response<FAQInertDataModel> response) {
                        FAQInertDataModel faqInertDataModel=response.body();

                        if(response.isSuccessful())
                        {
                            progressDialog.dismiss();
                            Toast.makeText(NewTicketActivity.this, faqInertDataModel.meassage, Toast.LENGTH_SHORT).show();
                            editText.setText(" ");
                        }

                    }

                    @Override
                    public void onFailure(Call<FAQInertDataModel> call, Throwable t) {
                        Toast.makeText(NewTicketActivity.this, "Failure", Toast.LENGTH_SHORT).show();

                    }
                });

            }

        });
        im_book_new_ticket_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(NewTicketActivity.this, Dashboard.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void init() {
        editText=(EditText)findViewById(R.id.faq_edit_chat);
        btn_submit=(Button)findViewById(R.id.faq_btn_submit);
        iv_mic = (ImageView) findViewById(R.id.faq_iv_mic);
        im_book_new_ticket_back = (ImageView) findViewById(R.id.im_book_new_ticket_back);
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

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent=new Intent(NewTicketActivity.this, Dashboard.class);
        startActivity(intent);
        finish();
    }
}