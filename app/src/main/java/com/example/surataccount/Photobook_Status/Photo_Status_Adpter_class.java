package com.example.surataccount.Photobook_Status;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.surataccount.R;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

public class Photo_Status_Adpter_class extends RecyclerView.Adapter<Photo_Status_Adpter_class.myviewHolder> {
    Context context;
    List<Photobook_Gette_Pojo_Class.PriceListModel> mList;


    public Photo_Status_Adpter_class(Context context, List<Photobook_Gette_Pojo_Class.PriceListModel> mList) {
        this.context = context;
        this.mList = mList;

    }

    @NonNull
    @Override
    public myviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.photobook_status_recycler_view_layout_file,parent,false);
        return new myviewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myviewHolder holder, int position) {

        String date=mList.get(position).Date;
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMMM-YYYY");
        String d=dateFormat.format(Date.valueOf(date));
        holder.booking_dt.setText(d);
        holder.booking_number.setText(mList.get(position).Booking);
        holder.server_id.setText(mList.get(position).ServerId);

        holder.bill_amount.setText(mList.get(position).BillDetail);
        holder.DispatchDetail.setText(mList.get(position).DispatchDetail);
        holder.studioName.setText(mList.get(position).StudioName);
        holder.stusts.setText(mList.get(position).Status);





    }

    @Override
    public int getItemCount() {
        return mList.size();
    }


    public class myviewHolder extends RecyclerView.ViewHolder{
        private TextView booking_dt,booking_number,server_id,bill_amount,DispatchDetail,studioName,stusts;


        public myviewHolder(@NonNull View itemView) {
            super(itemView);

            booking_dt=itemView.findViewById(R.id.photo_status_booking_dat);
            studioName=itemView.findViewById(R.id.photo_book_status_st_name);
            booking_number=itemView.findViewById(R.id.photo_BOOKING_NUMBER);
            server_id=itemView.findViewById(R.id.photo_status_SERVER_ID);
            bill_amount=itemView.findViewById(R.id.photo_status_BillDetail);
            DispatchDetail=itemView.findViewById(R.id.photo_status_DispatchDetail);
            stusts=itemView.findViewById(R.id.photo_status_Stusts);

        }
    }

}
