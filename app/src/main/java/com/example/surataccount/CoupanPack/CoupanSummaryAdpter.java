package com.example.surataccount.CoupanPack;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.surataccount.FAQ.FAQListActivity;
import com.example.surataccount.FAQ.NewTicketActivity;
import com.example.surataccount.R;


import java.util.List;

public class CoupanSummaryAdpter extends RecyclerView.Adapter<CoupanSummaryAdpter.myviewholder> {
    Context context;
    List<CoupanSummaryModel.CoupanSummary> list;
    public CoupanSummaryAdpter(Context context, List<CoupanSummaryModel.CoupanSummary> list) {
        this.context = context;
        this.list = list;
    }



    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.coupansummary_recleer_layout_file,parent,false);
        return new myviewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myviewholder holder, int position) {
        holder.coupan_summary_stu_name.setText(list.get(position).Coup_StudioName);
        holder.tv_Coup_SchemeName.setText(list.get(position).Coup_SchemeName);
        holder.tv_IssueCoupan.setText(list.get(position).IssueCoupan);
        holder.tv_UsedCoupon.setText(list.get(position).UsedCoupon);
        holder.tv_std_mobile.setText(list.get(position).std_mobile);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final CharSequence[] items = {"Unused Coupon", "Used Coupon", "All Coupon"};
//
                AlertDialog.Builder builder=new AlertDialog.Builder(context);
                builder.setTitle("");
                builder.setIcon(R.drawable.splash);
                builder.setSingleChoiceItems(items, -1, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int item) {
                        Log.d("coupan", items[item].toString());
                        if(items[item].equals("Unused Coupon"))
                        {
                            String Coup_SchemesCode=list.get(position).Coup_SchemesCode;
                            Intent i2 = new Intent(context, CoupanPageActivity.class);
                            i2.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                            i2.putExtra("coupanSceemeCode",Coup_SchemesCode);
                            i2.putExtra("coupon","0");
                            context.startActivity(i2);
                        }
                        else  if(items[item].equals("Used Coupon"))
                        {
                            String Coup_SchemesCode=list.get(position).Coup_SchemesCode;
                            Intent i2 = new Intent(context, CoupanPageActivity.class);
                            i2.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                            i2.putExtra("coupanSceemeCode",Coup_SchemesCode);
                            i2.putExtra("coupon","1");
                            context.startActivity(i2);
                        }
                        else if(items[item].equals("All Coupon"))
                        {
                            String Coup_SchemesCode=list.get(position).Coup_SchemesCode;
                            Intent i2 = new Intent(context, CoupanPageActivity.class);
                            i2.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                            i2.putExtra("coupanSceemeCode",Coup_SchemesCode);
                            i2.putExtra("coupon","2");
                            context.startActivity(i2);
                        }



                    }
                });

                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        builder.setCancelable(true);
                    }
                });
                AlertDialog alert = builder.create();
                alert.show();


            }
        });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class myviewholder extends RecyclerView.ViewHolder{
        TextView coupan_summary_stu_name,tv_Coup_SchemeName,tv_IssueCoupan,tv_UsedCoupon,tv_std_mobile;


        public myviewholder(@NonNull View itemView) {
            super(itemView);
            coupan_summary_stu_name=itemView.findViewById(R.id.coupan_summary_stu_name);
            tv_Coup_SchemeName=itemView.findViewById(R.id.coupan_summary_Coup_SchemeName);
            tv_IssueCoupan=itemView.findViewById(R.id.coupan_summary_IssueCoupan);
            tv_UsedCoupon=itemView.findViewById(R.id.coupan_summary_UsedCoupon);
            tv_std_mobile=itemView.findViewById(R.id.coupan_summary_std_mobile);


        }
    }
}
