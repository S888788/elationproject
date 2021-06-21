package com.example.surataccount.AboutPack;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.surataccount.R;

import java.util.List;

public class AboutAdpter extends RecyclerView.Adapter<AboutAdpter.myviewholder> {
    Context context;
    List<AboutModelClass.AboutSchedule> list;
    public AboutAdpter(Context context, List<AboutModelClass.AboutSchedule> list) {
        this.context = context;
        this.list = list;
    }
    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.about_page_recy_layout_file,parent,false);
       return new myviewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myviewholder holder, int position) {
        holder.txt_about.setText(list.get(position).theory);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class myviewholder extends RecyclerView.ViewHolder{
        TextView txt_about;
        public myviewholder(@NonNull View itemView) {
            super(itemView);
            txt_about=itemView.findViewById(R.id.txt_about_therory);
        }
    }
}
