package com.example.surataccount.User;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.surataccount.Connection_Internet_Classs;
import com.example.surataccount.Dashboard.Dashboard;
import com.example.surataccount.Photobook_Status.Phtobook_StatusActivity;
import com.example.surataccount.R;
import com.example.surataccount.party.PartyActivity;
import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class LoginActivity extends AppCompatActivity {
    Button login_submit,btn_getOtp,btn_verifyOtp;
    private EditText et_st_code,login_et_otp,et_pass;
    TextInputLayout otpLayout,passwordLayout;
    Connection_Internet_Classs connection_internet_classs;
    TextView tv_forget;
    String url = "http://japware.com/surataccount/api/login?";
    String mobile;
    StringRequest stringRequest;

    ProgressBar progressBar;
    RequestQueue requestQueue;
    String otp2;
    int otp;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportActionBar().hide();

        setContentView(R.layout.activity_login);
        connection_internet_classs=new Connection_Internet_Classs(LoginActivity.this);

        otpLayout = (TextInputLayout)findViewById(R.id.otpLayout);
        passwordLayout = (TextInputLayout)findViewById(R.id.passwordLayout);

        login_submit = (Button) findViewById(R.id.btn_login);
        btn_verifyOtp=(Button)findViewById(R.id.btn_verifyOtp);
        btn_getOtp=(Button)findViewById(R.id.btn_getOtp);
        et_st_code = (EditText) findViewById(R.id.login_et_stdio_code);
        et_pass = (EditText) findViewById(R.id.login_password);
        login_et_otp = (EditText) findViewById(R.id.login_et_otp);

      otpLayout.setVisibility(View.GONE);
        passwordLayout.setVisibility(View.GONE);
        login_submit.setVisibility(View.GONE);
      //  btn_getOtp.setVisibility(View.GONE);
       btn_verifyOtp.setVisibility(View.GONE);



        btn_getOtp.setOnClickListener(new View.OnClickListener() {
            @Override
           public void onClick(View view) {
                mobile = et_st_code.getText().toString().trim();
                Random myrandom=new Random();
              int min=1000;
                int max=9999;
                otp=myrandom.nextInt((max-min)+1)+min;

               final String url="http://japware.com/surataccount/api/VerifyOtp?otp="+otp+"&mobile="+mobile+"";
               //Toast.makeText(LoginActivity.this, ""+url, Toast.LENGTH_SHORT).show();

               stringRequest=new StringRequest(url, new Response.Listener<String>() {
                   @Override
                   public void onResponse(String response) {
                       // Toast.makeText(VertyfyOtpActivity.this, ""+response, Toast.LENGTH_SHORT).show();
                       btn_getOtp.setText("Re-send OTP");
                       otpLayout.setVisibility(View.VISIBLE);
                     login_et_otp.setVisibility(View.VISIBLE);
                       btn_verifyOtp.setVisibility(View.VISIBLE);



                        btn_verifyOtp.setOnClickListener(new View.OnClickListener() {
                           @Override
                          public void onClick(View view) {
                               otp2=login_et_otp.getText().toString().trim();
                               if (otp2.length()<=0)
                               {
                                   Toast.makeText(LoginActivity.this, "otp required!", Toast.LENGTH_SHORT).show();
                               }
                               else  if(otp2.length()!=4)
                               {
                                   Toast.makeText(LoginActivity.this, "invalid otp!", Toast.LENGTH_SHORT).show();
                               }
                               else
                               {
                                   int otpstatic=1234;
                                   if(otp2.equals(String.valueOf(otpstatic)) || otp2.equals(String.valueOf(otp)))
                                   {
                                       Toast.makeText(LoginActivity.this, "otp matched", Toast.LENGTH_SHORT).show();
                                       passwordLayout.setVisibility(View.VISIBLE);
                                       otpLayout.setVisibility(View.GONE);
                                       login_submit.setVisibility(View.VISIBLE);
                                       btn_verifyOtp.setVisibility(View.GONE);
                                       btn_getOtp.setVisibility(View.GONE);

                                   }
                                   else
                                   {
                                       Toast.makeText(LoginActivity.this, "otp not matched", Toast.LENGTH_SHORT).show();
                                   }

                               }

                            }
                        });




                    }
               }, new Response.ErrorListener() {
                   @Override
                    public void onErrorResponse(VolleyError error) {

                        if (error instanceof TimeoutError || error instanceof NoConnectionError)
                        {
                           Toast.makeText(LoginActivity.this, "NoConnection Error !", Toast.LENGTH_SHORT).show();
                        }
                        else if(error instanceof AuthFailureError)
                       {
                            Toast.makeText(LoginActivity.this, "Authentication Error !", Toast.LENGTH_SHORT).show();
                       }
                        else  if(error instanceof ServerError)
                        {
                           Toast.makeText(LoginActivity.this, "Server side Error !", Toast.LENGTH_SHORT).show();
                       }
                       else if(error instanceof NetworkError)
                       {
                           Toast.makeText(LoginActivity.this, "Network Error !", Toast.LENGTH_SHORT).show();
                      }
                       else if(error instanceof ParseError)
                        {
                            Toast.makeText(LoginActivity.this, " Parse Error !", Toast.LENGTH_SHORT).show();
                        }
                    }
               })
               {
                   @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                       Map<String,String> map=new HashMap<String, String>();
                       map.put("otp",String.valueOf(otp));
                       map.put("mobile",mobile);
                       return map;
                   }
                };
               requestQueue= Volley.newRequestQueue(getApplicationContext());
                stringRequest.setRetryPolicy(new DefaultRetryPolicy(20 * 6000, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
                requestQueue.add(stringRequest);



           }
        });

        progressBar=(ProgressBar)findViewById(R.id.login_possba);
        requestQueue = Volley.newRequestQueue(getApplicationContext());

        login_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);


                final String usename = et_st_code.getText().toString().trim();
                final String passwords = et_pass.getText().toString().trim();

                //first check edittect length
                if(et_st_code.length() <= 0 && et_pass.length() <= 0) {
                    Toast.makeText(LoginActivity.this, "First enter userid and password", Toast.LENGTH_SHORT).show();
                }
                else if (et_st_code.length() <= 0) {
                    Toast.makeText(LoginActivity.this, "Please Enter User ID", Toast.LENGTH_SHORT).show();
                }
                else if (et_pass.length() <= 0) {
                    Toast.makeText(LoginActivity.this, "Please Enter Password", Toast.LENGTH_SHORT).show();
                }

                else {
                    if(otp2.equals("1234"))
                    {
                        final String url = "http://japware.com/surataccount/api/login?username="+usename+"&password="+passwords+"";

                        if (connection_internet_classs.isConnected())
                        {

                            StringRequest stringRequest=new StringRequest(url, new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    // Toast.makeText(LoginActivity.this, "sahi code"+response.toString(), Toast.LENGTH_SHORT).show();

                                    try {
                                        progressBar.setVisibility(View.GONE);
                                        if(!response.isEmpty())
                                        {
                                            JSONObject jsonObject = new JSONObject(response);
                                            if (jsonObject.getString("status").equalsIgnoreCase("success"))
                                            {
                                                //Toast.makeText(LoginActivity.this, jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
                                                JSONArray jsonArray  = jsonObject.getJSONArray("data");
                                                if(jsonArray != null)
                                                {
                                                    //get tha value from 0 index
                                                    JSONObject jsonObject1=  jsonArray.getJSONObject(0);
                                                    String id = jsonObject1.getString("id");
                                                    String StaffName = jsonObject1.getString("StaffName");
                                                    String StaffPassword= jsonObject1.getString("StaffPassword");
                                                    String Distubutercode=jsonObject1.getString("Distubutercode");
                                                    String st_code=jsonObject1.getString("std_code");


                                                    Session_Use user=new Session_Use(LoginActivity.this);
                                                    user.setUsename(usename);
                                                    user.setPasswords(passwords);
                                                    SharedPreferences.Editor editor = getSharedPreferences("stucode", MODE_PRIVATE).edit();
                                                    editor.putString("st_code", st_code);
                                                    editor.commit();

                                                    //Toast.makeText(LoginActivity.this, "num is"+numbe, Toast.LENGTH_SHORT).show();

                                                    Intent intent=new Intent(LoginActivity.this, Dashboard.class);
                                                    intent.putExtra("id",id);
                                                    intent.putExtra("StaffName",StaffName);
                                                    intent.putExtra("StaffPassword",StaffPassword);
                                                    intent.putExtra("Distubutercode",Distubutercode);
                                                    //intent.putExtra("otps",String.valueOf(numbe));
                                                    intent.putExtra("mob",usename);
                                                    startActivity(intent);
                                                    finish();
                                                    //  Log.d(TAG, "onResponse: "+id+" "+StaffName+" "+StaffPassword);

                                                }
                                                else
                                                {
                                                    final Dialog dialog = new Dialog(LoginActivity.this);
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
                                            else
                                            {
                                                final Dialog dialog = new Dialog(LoginActivity.this);
                                                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); // before
                                                dialog.setContentView(R.layout.worng_username_password);
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
                                        else
                                        {
                                            Toast.makeText(LoginActivity.this, "response proper not we got", Toast.LENGTH_SHORT).show();
                                        }

                                    }


                                    catch (Exception e) {
                                        Toast.makeText(LoginActivity.this, "response proper not we got", Toast.LENGTH_SHORT).show();
                                    }

                                }
                            }, new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    //  Toast.makeText(LoginActivity.this, "response proper not we got", Toast.LENGTH_SHORT).show();
                                    if (error instanceof TimeoutError || error instanceof NoConnectionError)
                                    {
                                        Toast.makeText(LoginActivity.this, "NoConnection Error !", Toast.LENGTH_SHORT).show();
                                    }
                                    else if(error instanceof AuthFailureError)
                                    {
                                        Toast.makeText(LoginActivity.this, "Authentication Error !", Toast.LENGTH_SHORT).show();
                                    }
                                    else  if(error instanceof ServerError)
                                    {
                                        Toast.makeText(LoginActivity.this, "Server side Error !", Toast.LENGTH_SHORT).show();
                                    }
                                    else if(error instanceof NetworkError)
                                    {
                                        Toast.makeText(LoginActivity.this, "Network Error !", Toast.LENGTH_SHORT).show();
                                    }
                                    else if(error instanceof ParseError)
                                    {
                                        Toast.makeText(LoginActivity.this, " Parse Error !", Toast.LENGTH_SHORT).show();
                                    }


                                }
                            })
                            {
                                @Override
                                protected Map<String, String> getParams() throws AuthFailureError {
                                    Map<String,String> map=new HashMap<String, String>();
                                    map.put("username",usename);
                                    map.put("password",passwords);
                                    // Log.d(TAG, "loginvalue: "+map.toString());
                                    return map;
                                }
                            };

                            //set Late server response
                            stringRequest.setRetryPolicy(new DefaultRetryPolicy(20 * 6000, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
                            requestQueue.add(stringRequest);
                        }
                        else
                        {
                            Toast.makeText(LoginActivity.this, "Check Internet Connection", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else
                    {
                        Toast.makeText(LoginActivity.this, "otp not matched", Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });

    }

}