package com.example.surataccount.tranisition;


import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.surataccount.Dashboard.Dashboard;
import com.example.surataccount.R;
import com.example.surataccount.User.Session_Use;
import com.example.surataccount.party.PartyActivity;
import com.example.surataccount.viewDetails.PaymentModeActivity;
import com.google.gson.JsonSyntaxException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static android.content.ContentValues.TAG;
import static android.content.Context.MODE_PRIVATE;


public class Tranisition_Fragment extends Fragment {
    private ImageView billedicon,dipositeicon,backpesssicontansition;
    private RecyclerView recyclerView;
//otp
    EditText todate,formdate;
    Button tn_form_to_btn;
    private LinearLayoutManager linearLayoutManager;
    private RequestQueue requestQueue;

    String fomda,toda;
        String stname,stcode;

    String balamt="";
    final String Share_preffs = "";
    transition_adpter_class ad;
    ProgressBar progressBar;
    private List<transition_getter_setter_class> mList = new ArrayList<>();
    String datatype="2";
    DatePickerDialog datePickerDialog;

    String monthh, dayy;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_tranisition_, container, false);

        SharedPreferences sh = getContext().getSharedPreferences("stcodebmtname", MODE_PRIVATE);
        stname = sh.getString("stname", "");
        stcode = sh.getString("stcode", "");
        balamt = sh.getString("balamt", "");


        recyclerView = (RecyclerView) view.findViewById(R.id.frg_view_detail_recy);
        backpesssicontansition=(ImageView)view. findViewById(R.id.backpesssicontansition);
        linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        progressBar=(ProgressBar)view.findViewById(R.id.tasition_possba);
        TextView tn_st_name=(TextView)view.findViewById(R.id.tn_st_name);
        TextView tn_balamt=(TextView)view.findViewById(R.id.tn_bal_amount);


        double ou=Math.round(Double.valueOf(balamt)*100)/100;
        tn_st_name.setText(stname);
        tn_balamt.setText("Balance Amount  :"+" "+ou);

        //unpack our  data from our bundle


        Session_Use session_use=new Session_Use(getActivity());
        final String mob=session_use.getUsename();









        progressBar.setVisibility(View.VISIBLE);

        backpesssicontansition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(getActivity(), PaymentModeActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });

               formdate=(EditText)view.findViewById(R.id.tn_et_formdate);
        formdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar cldrs = Calendar.getInstance();
                int day = cldrs.get(Calendar.DAY_OF_MONTH);
                int month = cldrs.get(Calendar.MONTH);
                int year = cldrs.get(Calendar.YEAR);
                datePickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
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
                        formdate.setText(dayy+"/"+monthh+"/"+year);


                        fomda=(year+"-"+monthh+"-"+dayy);
                        Log.d("check", fomda);



                    }
                }, year, month, day);
                datePickerDialog.show();

            }
        });
        todate=(EditText)view.findViewById(R.id.tn_et_todate);
        todate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
               // Toast.makeText(getActivity(), "Year"+year+"\nmonth"+month+"\nday"+day, Toast.LENGTH_SHORT).show();
                datePickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
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
                        todate.setText(dayy+"/"+monthh+"/"+year);


                        toda=(year+"-"+monthh+"-"+dayy);
                        Log.d("check", fomda);





                    }
                }, year, month, day);
                datePickerDialog.show();

            }
        });

        tn_form_to_btn=(Button)view.findViewById(R.id.tn_form_to_btn);
        tn_form_to_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               if (formdate.getText().toString().isEmpty())
               {
                   formdate.setError("Empty");
                   formdate.requestFocus();
                   return;
               }
               else
               {
                   if (todate.getText().toString().isEmpty())
                   {
                       todate.setError("Empty");
                       todate.requestFocus();
                       return;
                   }
                   else
                   {
                       String urls="http://japware.com/surataccount/api/searchdata?studioCode="+stcode+"&fromdate="+fomda+"&todate="+toda+"&datatype="+datatype+"";
                       Log.d("u",urls);
                    //  Toast.makeText(getActivity(), "u"+urls, Toast.LENGTH_SHORT).show();
                       StringRequest s=new StringRequest(urls, new Response.Listener<String>() {
                           @Override
                           public void onResponse(String response) {
                               //Toast.makeText(getActivity(), ""+response, Toast.LENGTH_SHORT).show();
                               Log.d("response",response);
                               try {
                                   mList.clear();
                                   progressBar.setVisibility(View.GONE);
                                   if(!response.isEmpty()){

                                       JSONObject jsonObject = new JSONObject(response);
                                       if(jsonObject.getString("status").equalsIgnoreCase("success")){

                                           JSONArray jsonArray  = jsonObject.getJSONArray("data");
                                           if(jsonArray != null){

                                               for(int i=0;i<jsonArray.length();i++){

                                                   JSONObject jsonObject1=  jsonArray.getJSONObject(i);
                                                   transition_getter_setter_class viewDetailsPojo = new transition_getter_setter_class(
                                                           jsonObject1.getString("Vouchernumber"),
                                                           jsonObject1.getString("Voucherdate"),

                                                           jsonObject1.getString("StudioCode"),
                                                           jsonObject1.getString("Studioname"),
                                                           jsonObject1.getString("AmtPayable"),
                                                           jsonObject1.getString("AmtRecieved"));
                                                   mList.add(viewDetailsPojo);
                                               }


                                               if(mList.size()>0){
                                                   ad = new transition_adpter_class(getContext(),mList,billedicon,dipositeicon);
                                                   recyclerView.setAdapter(ad);
                                                   recyclerView.setVisibility(View.VISIBLE);
                                               }
                                               else
                                               {
                                                   recyclerView.setVisibility(View.GONE);
                                                   final Dialog dialog = new Dialog(getContext());
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
                                       }else {
                                           Toast.makeText(getActivity(), "status failed", Toast.LENGTH_SHORT).show();
                                       }

                                   }else {
                                       Toast.makeText(getActivity(), "Something went wrong", Toast.LENGTH_SHORT).show();
                                   }


                               } catch (JsonSyntaxException e) {
                                   Toast.makeText(getContext(), "aap galati kr rahe hai", Toast.LENGTH_SHORT).show();
                                   e.printStackTrace();
                               } catch (JSONException e) {
                                   e.printStackTrace();
                               }


                           }
                       }, new Response.ErrorListener() {
                           @Override
                           public void onErrorResponse(VolleyError error) {
                               //Toast.makeText(getActivity(), "error"+error.getMessage(), Toast.LENGTH_SHORT).show();
                               if (error instanceof TimeoutError || error instanceof NoConnectionError)
                               {
                                   Toast.makeText(getActivity(), "NoConnection Error !", Toast.LENGTH_SHORT).show();
                               }
                               else if(error instanceof AuthFailureError)
                               {
                                   Toast.makeText(getActivity(), "Authentication Error !", Toast.LENGTH_SHORT).show();
                               }
                               else  if(error instanceof ServerError)
                               {
                                   Toast.makeText(getActivity(), "Server side Error !", Toast.LENGTH_SHORT).show();
                               }
                               else if(error instanceof NetworkError)
                               {
                                   Toast.makeText(getActivity(), "Network Error !", Toast.LENGTH_SHORT).show();
                               }
                               else if(error instanceof ParseError)
                               {
                                   Toast.makeText(getActivity(), " Parse Error !", Toast.LENGTH_SHORT).show();
                               }




                           }
                       })
                       {
                           @Override
                           protected Map<String, String> getParams() throws AuthFailureError {
                               Map<String,String> map2=new HashMap<String, String>();
                               map2.put("studioCode",stcode);
                               map2.put("fromdate",fomda);
                               map2.put("todate",toda);
                               map2.put("datatype",datatype);

                               return map2;
                           }

                       };

                       //late
                       requestQueue = Volley.newRequestQueue(getContext());
                       int socketTimeout=20000;
                       RetryPolicy policy=new DefaultRetryPolicy(socketTimeout,DefaultRetryPolicy.DEFAULT_MAX_RETRIES,DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
                       s.setRetryPolicy(policy);
                       requestQueue.add(s);
                       todate.setText("");
                       formdate.setText("");


                   }
               }
            }
        });

        //Toast.makeText(getContext(), ""+mob, Toast.LENGTH_SHORT).show();
        fetchJsondata();



        return view;
    }



    private void fetchJsondata() {
        final  String url="http://japware.com/surataccount/api/PartyLedger?StudioCode="+stcode+"&datatype="+datatype+"";

        StringRequest stringRequest = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
               // Toast.makeText(getContext(), "" + response, Toast.LENGTH_SHORT).show();

                Log.d(TAG, "onCreateViewTrass: "+response);
                try {

                    progressBar.setVisibility(View.GONE);
                    if(!response.isEmpty()){

                        JSONObject jsonObject = new JSONObject(response);
                        if(jsonObject.getString("status").equalsIgnoreCase("success")){

                            JSONArray jsonArray  = jsonObject.getJSONArray("data");
                            if(jsonArray != null){

                                for(int i=0;i<jsonArray.length();i++){

                                    JSONObject jsonObject1=  jsonArray.getJSONObject(i);
                                    transition_getter_setter_class viewDetailsPojo = new transition_getter_setter_class(
                                            jsonObject1.getString("Vouchernumber"),
                                            jsonObject1.getString("Voucherdate"),

                                            jsonObject1.getString("StudioCode"),
                                            jsonObject1.getString("Studioname"),
                                            jsonObject1.getString("AmtPayable"),
                                            jsonObject1.getString("AmtRecieved"));
                                        mList.add(viewDetailsPojo);
                                }


                                if(mList.size()>0){
                                    ad = new transition_adpter_class(getContext(),mList,billedicon,dipositeicon);
                                    recyclerView.setAdapter(ad);
                                }
                                else
                                {
                                    final Dialog dialog = new Dialog(getContext());
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
                        }else {
                            Toast.makeText(getActivity(), "Data not Matched", Toast.LENGTH_SHORT).show();
                        }

                    }else {
                        Toast.makeText(getActivity(), "Something went wrong", Toast.LENGTH_SHORT).show();
                    }


                } catch (JsonSyntaxException e) {
                    Toast.makeText(getContext(), "aap galati kr rahe hai", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
               // Toast.makeText(getContext(), "" + error.getMessage(), Toast.LENGTH_SHORT).show();
                if (error instanceof TimeoutError || error instanceof NoConnectionError)
                {
                    Toast.makeText(getActivity(), "NoConnection Error !", Toast.LENGTH_SHORT).show();
                }
                else if(error instanceof AuthFailureError)
                {
                    Toast.makeText(getActivity(), "Authentication Error !", Toast.LENGTH_SHORT).show();
                }
                else  if(error instanceof ServerError)
                {
                    Toast.makeText(getActivity(), "Server side Error !", Toast.LENGTH_SHORT).show();
                }
                else if(error instanceof NetworkError)
                {
                    Toast.makeText(getActivity(), "Network Error !", Toast.LENGTH_SHORT).show();
                }
                else if(error instanceof ParseError)
                {
                    Toast.makeText(getActivity(), " Parse Error !", Toast.LENGTH_SHORT).show();
                }



            }
        });

        requestQueue = Volley.newRequestQueue(getContext());
        int socketTimeout=20000;
        RetryPolicy policy=new DefaultRetryPolicy(socketTimeout,DefaultRetryPolicy.DEFAULT_MAX_RETRIES,DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        stringRequest.setRetryPolicy(policy);
        requestQueue.add(stringRequest);

    }






}
