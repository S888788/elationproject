package com.example.surataccount;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.surataccount.User.Session_Use;
import com.example.surataccount.viewDetails.PaymentModeActivity;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class widActivity extends AppCompatActivity {
    ImageView payment_back_button;
    EditText et_vouch_date,et_check_date,et_amount_mode,et_amount,et_remark;
    private RequestQueue requestQueue;
    Button payment_button;
    TextView payment_st_name;
    private RadioButton radio_cash_payment,radio_upi,radio_bank;
    private StringRequest stringRequest;
    String voucher_date,voucher_no="",rec_no,ch_date,am_bank,amot,mak,cash_selected;
    RadioGroup indi_slives,cash_bank_upis;
    String balamt,stname,stcode;

    int indigo_selected=1;
    DatePickerDialog datePickerDialog;
    String monthh, dayy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_wid);
        payment_back_button=(ImageView)findViewById(R.id.payment_back_button);
        payment_st_name=(TextView)findViewById(R.id.payment_st_name);


        et_vouch_date=(EditText) findViewById(R.id.et_voucher_date);

        final Calendar cldr = Calendar.getInstance();
        int day = cldr.get(Calendar.DAY_OF_MONTH);
        int month = cldr.get(Calendar.MONTH);
        int year = cldr.get(Calendar.YEAR);
        et_vouch_date.setText((0+day)+ "/"+ "0"+(month + 1) + "/" + year);
        et_vouch_date.setEnabled(false);



        radio_cash_payment=(RadioButton)findViewById(R.id.radio_cash_payment);
        radio_bank=(RadioButton)findViewById(R.id.radio_bank);



        et_amount_mode=(EditText)findViewById(R.id.payment_amount_mode);


        et_check_date=(EditText)findViewById(R.id.et_check_date);
        et_check_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                datePickerDialog = new DatePickerDialog(widActivity.this, new DatePickerDialog.OnDateSetListener() {
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
                        et_check_date.setText(dayy+"/"+monthh+"/"+year);


            ch_date=(year+"-"+monthh+"-"+dayy);
            Log.d("check",ch_date);

                    }
                }, year, month, day);
                datePickerDialog.show();
            }
        });

        et_amount=(EditText)findViewById(R.id.et_payment_amount);
        et_remark=(EditText)findViewById(R.id.et_payment_remark);
        cash_bank_upis=(RadioGroup)findViewById(R.id.cash_bank_upis);
        cash_bank_upis.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if(i==R.id.radio_cash_payment)
                {
                    et_amount_mode.setText("CASH");
                    et_amount_mode.setEnabled(false);


                }
                if(i==R.id.radio_bank)
                {
                    et_amount_mode.setEnabled(true);
                    et_amount_mode.setText("");
                }
                else
                {
                    et_amount_mode.setEnabled(false);
                }


            }
        });
        indi_slives=(RadioGroup)findViewById(R.id.indi_slives);

        payment_button=(Button)findViewById(R.id.btn_payment_button);

        SharedPreferences sh = getSharedPreferences("stcodebmtname", MODE_PRIVATE);

        stname = sh.getString("stname", "");
        stcode = sh.getString("stcode", "");
        balamt = sh.getString("balamt", "");

        //Toast.makeText(widActivity.this, "code is =" + codes+"names="+names+"balamt is :"+balamt, Toast.LENGTH_SHORT).show();
       Session_Use user=new Session_Use(widActivity.this);
        rec_no=user.getUsename();
        payment_back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(widActivity.this, PaymentModeActivity.class);
                startActivity(intent);
                finish();
            }
        });
       // Toast.makeText(widActivity.this, "payment mob is:"+rec_no, Toast.LENGTH_SHORT).show();

                payment_st_name.setText(stname);


        payment_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Date now=new Date();
                SimpleDateFormat dateFormat=new SimpleDateFormat("YYYY-MM-dd");
                voucher_date=dateFormat.format(now);
                am_bank = et_amount_mode.getText().toString().trim();
                    amot=et_amount.getText().toString().trim();
                mak=et_remark.getText().toString().trim();
                requestQueue = Volley.newRequestQueue(getApplicationContext());
                if(ch_date.isEmpty())
                {
                    et_check_date.setError("Empty");
                    et_check_date.requestFocus();
                    return;
                }
                else if(am_bank.isEmpty())
                {
                    et_amount_mode.setError("Empty");
                    et_amount_mode.requestFocus();
                    return;
                }
                else if(amot.isEmpty())
                {
                    et_amount.setError("Empty");
                    et_amount.requestFocus();
                    return;
                }


                else {

                   int cashId = cash_bank_upis.getCheckedRadioButtonId();
                    if (et_amount_mode.equals(cashId))
                    {

                    }
                    RadioButton selected_cash = cash_bank_upis.findViewById(cashId);

                    if (selected_cash == null) {
                        Toast.makeText(widActivity.this, "selected Cash or Bank or Upi", Toast.LENGTH_SHORT).show();
                        return;

                    } else {
                        cash_selected = selected_cash.getText().toString().trim();
                       // Toast.makeText(widActivity.this, "gb is:"+cash_selected, Toast.LENGTH_SHORT).show();


                    }
                    //indigo ot slive selected
                    int indigoId = indi_slives.getCheckedRadioButtonId();

                    //Toast.makeText(getContext(), ""+indigoId, Toast.LENGTH_SHORT).show();
                    RadioButton selected_indigo = indi_slives.findViewById(indigoId);
                    if (selected_indigo == null) {
                        Toast.makeText(widActivity.this, "selected Indigo or Sliver", Toast.LENGTH_SHORT).show();
                        return;

                    } else {

                        indigo_selected = selected_indigo.getText().toString().trim().equals("indigo")?1:0;



                        // Toast.makeText(getContext(), "indido"+indigo_selected, Toast.LENGTH_SHORT).show();

                    }
                }
                final String url = "http://japware.com/surataccount/api/paymentreceipt?VoucherNumber="+voucher_no+ "&VoucherDate="+voucher_date+ "&IsCash="+cash_selected+ "&StudioCode="+stcode+" &StudioName="+stname+  "&IsIndigo="+indigo_selected+ "&ChequeDate="+ch_date+ "&Amount="+amot+ "&BankName="+am_bank+"&Remark="+mak+"&ReceiptNumber="+rec_no+"";
                Log.d("sh",url);
                stringRequest=new StringRequest(url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                         //Toast.makeText(widActivity.this, "sahi code"+response.toString(), Toast.LENGTH_SHORT).show();
                        try {
                            if(!response.isEmpty())
                            {
                                JSONObject jsonObject = new JSONObject(response);
                                if (jsonObject.getString("status").equalsIgnoreCase("Success"))
                                {
                                    Toast.makeText(widActivity.this, jsonObject.getString("meassage"), Toast.LENGTH_SHORT).show();
                                    JSONArray jsonArray  = jsonObject.getJSONArray("data");
                                    if(jsonArray != null)
                                    {
                                        //get tha value from 0 index
                                        JSONObject jsonObject1=  jsonArray.getJSONObject(0);
                                        String VoucherNumber = jsonObject1.getString("VoucherNumber");
                                        String VoucherDate = jsonObject1.getString("VoucherDate");
                                        String IsCash= jsonObject1.getString("IsCash");
                                        String StudioCode= jsonObject1.getString("StudioCode");
                                        String StudioName= jsonObject1.getString("StudioName");
                                        String Std_GSTNumber= jsonObject1.getString("Std_GSTNumber");
                                        String Std_State= jsonObject1.getString("Std_State");
                                        String IsIndigo= jsonObject1.getString("IsIndigo");
                                        String ChequeDate= jsonObject1.getString("ChequeDate");
                                        String Amount= jsonObject1.getString("Amount");
                                        String BankName= jsonObject1.getString("BankName");
                                        String Remark= jsonObject1.getString("Remark");
                                        String ReceiptNumber=jsonObject1.getString("ReceiptNumber");
                                    }
                                    else
                                    {
                                        Toast.makeText(widActivity.this, "data not found", Toast.LENGTH_SHORT).show();
                                    }

                                }
                                else
                                {
                                    Toast.makeText(widActivity.this, "data not Matched", Toast.LENGTH_SHORT).show();
                                }
                            }
                            else
                            {
                                Toast.makeText(widActivity.this, "empty", Toast.LENGTH_SHORT).show();
                            }

                        }


                        catch (Exception e) {
                            Toast.makeText(widActivity.this, "catch", Toast.LENGTH_SHORT).show();
                        }

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(widActivity.this, error+"glat ", Toast.LENGTH_SHORT).show();
                    }
                })
                {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String,String> map=new HashMap<String, String>();
                        map.put("VoucherNumber",voucher_no);
                        map.put("VoucherDate",voucher_date);
                        map.put("IsCash", cash_selected);
                        map.put("StudioName",stname);
                        map.put("ReceiptNumber",rec_no);
                        map.put("IsIndigo",String.valueOf(indigo_selected));
                        map.put("ChequeDate",ch_date);
                        map.put("Amount",amot);
                        map.put("BankName",am_bank);
                        map.put("Rremark",mak);
                        return map;



                    }
                };
                stringRequest.setRetryPolicy(new DefaultRetryPolicy(20 * 6000, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
                requestQueue=Volley.newRequestQueue(getApplicationContext());
                requestQueue.add(stringRequest);
                et_vouch_date.setText("");
                cash_bank_upis.clearCheck();
                indi_slives.clearCheck();
                et_check_date.setText("");
                et_amount.setText("");
                et_amount_mode.setText("");
                et_remark.setText("");


            }
        });
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent=new Intent(widActivity.this, PaymentModeActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }

}
