package com.example.surataccount.FAQ;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.surataccount.R;

import java.util.List;

public class ChattingAdpterClass extends RecyclerView.Adapter<ChattingAdpterClass.myviewclass> {
    Context context;
    List<ChattingSelectModel.SelectCHat> list;
    public ChattingAdpterClass(Context context, List<ChattingSelectModel.SelectCHat> list) {
        this.context = context;
        this.list = list;
    }
    @NonNull
    @Override
    public myviewclass onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.fqa_recyler_layout_file,parent,false);
        return new myviewclass(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myviewclass holder, int position) {
      String[] mColors = {"#3F51B5","#FF9800","#009688","#673AB7"};
        holder.itemView.setBackgroundColor(Color.parseColor(mColors[position % 2]));

        holder.txt_msg.setText(list.get(position).txt_msg);
        holder.txt_datetime.setText(list.get(position).msg_date_time);
        holder.txt_ticket.setText(list.get(position).ticket_no);
        holder.txt_status.setText(list.get(position).status);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               
                Intent intent=new Intent(context,FAQCHattingMainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);

                intent.putExtra("msg_form",list.get(position).msg_form);
                intent.putExtra("ticket_no",list.get(position).ticket_no);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size() ;
    }

    public  class  myviewclass extends RecyclerView.ViewHolder{
        TextView txt_msg,txt_datetime,txt_ticket,txt_status;
        public myviewclass(@NonNull View itemView) {
            super(itemView);
            txt_msg=itemView.findViewById(R.id.faq_txt_msg);
            txt_datetime=itemView.findViewById(R.id.faq_msg_date_time);
            txt_ticket=itemView.findViewById(R.id.faq_ticket_no);
            txt_status=itemView.findViewById(R.id.faq_status);

        }
    }
}
