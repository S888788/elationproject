package com.example.surataccount.FAQ;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.surataccount.R;

import java.util.List;

public class FAQmainAdpter extends RecyclerView.Adapter<FAQmainAdpter.myviewHolder> {
Context context;
List<FAQMainChattingModel.FAQData> list;

    public FAQmainAdpter(Context context, List<FAQMainChattingModel.FAQData> list) {
        this.context = context;
        this.list = list;
    }


    @NonNull
    @Override
    public myviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.faq_main_recyler_layout_file,parent,false);
        return new myviewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myviewHolder holder, int position) {
        holder.txt_msg.setText(list.get(position).txt_msg);
        holder.txt_date_ime.setText(list.get(position).msg_date_time);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public  class  myviewHolder extends RecyclerView.ViewHolder{
        TextView txt_msg,txt_date_ime;
        public myviewHolder(@NonNull View itemView) {
            super(itemView);
            txt_msg=itemView.findViewById(R.id.faq_main_txt_msg);
            txt_date_ime=itemView.findViewById(R.id.faq_main_msg_date_time);
        }
    }
}
