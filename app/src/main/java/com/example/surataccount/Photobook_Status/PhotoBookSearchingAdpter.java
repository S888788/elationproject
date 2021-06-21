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

public class PhotoBookSearchingAdpter extends RecyclerView.Adapter<PhotoBookSearchingAdpter.myviewHolder> {
    Context context;
    List<PhotobookSearchingDateGetterSeter.PriceListModelsss> mList;


    public PhotoBookSearchingAdpter(Context context, List<PhotobookSearchingDateGetterSeter.PriceListModelsss> mList) {
        this.context = context;
        this.mList = mList;
    }

    @NonNull
    @Override
    public myviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.photo_searching_photobooksaraus,parent,false);
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

            booking_dt=itemView.findViewById(R.id.photo_status_booking_dat_searching);
            studioName=itemView.findViewById(R.id.photo_book_status_st_name_searching);
            booking_number=itemView.findViewById(R.id.photo_BOOKING_NUMBER_searching);
            server_id=itemView.findViewById(R.id.photo_status_SERVER_ID_searching);
            bill_amount=itemView.findViewById(R.id.photo_status_BillDetail_searching);
            DispatchDetail=itemView.findViewById(R.id.photo_status_DispatchDetail_searching);
            stusts=itemView.findViewById(R.id.photo_status_Stusts_searching);

        }
    }

}