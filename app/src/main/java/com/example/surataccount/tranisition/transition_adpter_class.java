package com.example.surataccount.tranisition;

import android.content.Context;
import android.util.Log;
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
import java.util.Locale;

public class transition_adpter_class extends RecyclerView.Adapter <transition_adpter_class.Myviewholderad>{
    Context context;
    List<transition_getter_setter_class> mList;
    ImageView billedimg,dipodteimg;

    public transition_adpter_class(Context context, List<transition_getter_setter_class> mList, ImageView billedimg, ImageView dipodteimg) {
        this.context = context;
        this.mList = mList;
        this.billedimg = billedimg;
        this.dipodteimg = dipodteimg;
    }

    @Override
    public Myviewholderad onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.transition_recycler_layout,parent,false);

        return new Myviewholderad(view);
    }
    @Override
    public void onBindViewHolder(@NonNull Myviewholderad holder, int position) {

        transition_getter_setter_class items =mList.get(position);

        String []dat=items.getVouchernumber().split(" ");
        holder.tv_tr_code.setText(items.getVouchernumber());
        String da=items.getVoucherdate();
        Log.d("date",da);
        SimpleDateFormat dateFormat=new SimpleDateFormat("dd-MMMM-YYYY", Locale.US);
             String d= dateFormat.format(Date.valueOf(da));
        Log.d("date",d);
        holder.tv_tr_date.setText(d);
        holder.tv_st_code.setText(items.getStudioCode());
        holder.tv_st_name.setText(items.getStudioname());
        holder.tv_amt_pay.setText(items.getAmtPayable());
        holder.tv_amt_rec.setText(items.getAmtRecieved());


    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class Myviewholderad extends RecyclerView.ViewHolder{
        public TextView cashedtextview,billedtextview,tv_data,tv_st_code,tv_st_name,tv_tr_date,tv_tr_code,tv_amt_pay,tv_amt_rec;
        public ImageView billed,diposite;
        public Myviewholderad(@NonNull View itemView) {
            super(itemView);
            tv_st_code=(TextView) itemView.findViewById(R.id.tv_customTransaction_studioCode);

            tv_st_name=(TextView) itemView.findViewById(R.id.tv_customTransaction_studioName);

            tv_amt_pay=(TextView) itemView.findViewById(R.id.tv_vi_det_fra_re_lay_amt_pay);
            tv_amt_rec=(TextView) itemView.findViewById(R.id.tv_vi_det_fra_re_lay_amt_rec);


            tv_tr_code=(TextView) itemView.findViewById(R.id.tv_customTransaction_transactionID);
            tv_tr_date=(TextView) itemView.findViewById(R.id.tv_customTransaction_transactionDate);

        }
    }
}
