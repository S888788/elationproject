package com.example.surataccount.BookSchedule;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.surataccount.R;

import java.util.List;

public class BookScheduleAdpter extends RecyclerView.Adapter<BookScheduleAdpter.myviewholder> {
    Context context;
    List<BookScheduleModelClass.BookSchedule> bookScheduleList;

    public BookScheduleAdpter(Context context, List<BookScheduleModelClass.BookSchedule> bookScheduleList) {
        this.context = context;
        this.bookScheduleList = bookScheduleList;
    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.book_schedule_recler_view_layout,parent,false);
        return new myviewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final myviewholder holder, final int position) {
        holder.tv_sch_start_time.setText(bookScheduleList.get(position).sch_start_time);
        if (bookScheduleList.get(position).booking_no.length()< 2)
        {
            holder.tv_booking_no.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent=new Intent(context,UpdateBookingNumberActivity.class);
                    intent.putExtra("booking_no",bookScheduleList.get(position).booking_no);
                    intent.putExtra("sch_start_time",bookScheduleList.get(position).sch_start_time);
                    intent.putExtra("designer_id",bookScheduleList.get(position).designer_id);
                    intent.putExtra("sch_date",bookScheduleList.get(position).sch_date);
                    context.startActivity(intent);
                }
            });
        }
        else
        {
            holder.tv_booking_no.setVisibility(View.GONE);
            holder.img_sch.setVisibility(View.VISIBLE);
        }


    }

    @Override
    public int getItemCount() {
        return bookScheduleList.size();
    }

    public class myviewholder extends RecyclerView.ViewHolder{
        TextView tv_sch_start_time,tv_booking_no;
        ImageView img_booking,img_sch;

        public myviewholder(@NonNull View itemView) {
            super(itemView);
            tv_sch_start_time=(TextView)itemView.findViewById(R.id.tv_book_sch_schtime);
            tv_booking_no=(TextView)itemView.findViewById(R.id.tv_book_sch_booking);
            img_booking=(ImageView)itemView.findViewById(R.id.im_book_sche);
            img_sch=(ImageView)itemView.findViewById(R.id.im_book_scheh);


        }
    }
}
