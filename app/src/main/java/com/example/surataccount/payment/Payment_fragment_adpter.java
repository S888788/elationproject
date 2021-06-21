package com.example.surataccount.payment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.surataccount.R;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

public class Payment_fragment_adpter extends RecyclerView.Adapter<Payment_fragment_adpter.myviewholder> {
    Context context;
    List<Payment_frag_getter_setter_class> mList;
    ImageView billedimg,dipodteimg;

    public Payment_fragment_adpter(Context context, List<Payment_frag_getter_setter_class> mList, ImageView billedimg, ImageView dipodteimg) {
        this.context = context;
        this.mList = mList;
        this.billedimg = billedimg;
        this.dipodteimg = dipodteimg;
    }
    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.payment_fragment_rrcycler_layout,parent,false);

        return new myviewholder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull myviewholder holder, int position) {
        Payment_frag_getter_setter_class items = mList.get(position);

        String []dat=items.getVouchernumber().split(" ");
        holder.tv_tr_code.setText(items.getVouchernumber());
        String date=items.getVoucherdate();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMMM-YYYY");
        String d= dateFormat.format(Date.valueOf(date));

        holder.tv_tr_date.setText(d);
        holder.tv_st_code.setText(items.getStudioCode());
        holder.tv_st_name.setText(items.getStudioname());
        holder.tv_amt_pay.setText(items.getAmtPayable());
        holder.tv_amt_rec.setText(items.getAmtRecieved());
        String valu=items.getAmtRecieved();
        if (valu.equals("0"))
        {
            holder.billed.setVisibility(View.VISIBLE);
            holder.billedtextview.setVisibility(View.VISIBLE);
            holder.diposite.setVisibility(View.GONE);
            holder.cashedtextview.setVisibility(View.GONE);

        }
        else
        {
            holder.diposite.setVisibility(View.VISIBLE);
            holder.cashedtextview.setVisibility(View.VISIBLE);
            holder.billed.setVisibility(View.GONE);
            holder.billedtextview.setVisibility(View.GONE);
        }

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public  class myviewholder extends RecyclerView.ViewHolder{
        public TextView cashedtextview,billedtextview,tv_data,tv_st_code,tv_st_name,tv_tr_date,tv_tr_code,tv_amt_pay,tv_amt_rec;
        public ImageView billed,diposite;

        public myviewholder(@NonNull View itemView) {

            super(itemView);
            tv_tr_code=(TextView) itemView.findViewById(R.id.tv_customPayment_transactionID);
            tv_tr_date=(TextView) itemView.findViewById(R.id.tv_customPayment_transactionDate);

            tv_st_code=(TextView) itemView.findViewById(R.id.tv_customTPayment_studioCode);
            tv_st_name=(TextView) itemView.findViewById(R.id.tv_customTPayment_studioName);
            tv_amt_pay=(TextView) itemView.findViewById(R.id.tv_payment_payble_amt);
            tv_amt_rec=(TextView) itemView.findViewById(R.id.tv_payment_receive_amt);
            billed=(ImageView)itemView.findViewById(R.id.transaction_payment_billed);
            diposite=(ImageView)itemView.findViewById(R.id.transaction_payment_deposit);
            cashedtextview=(TextView) itemView.findViewById(R.id.tv_payment_cash);
            billedtextview=(TextView) itemView.findViewById(R.id.tv_payment_billed);

        }
    }

}
