package com.example.surataccount.CoupanPack;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.surataccount.R;

import java.util.List;

public class CoupanPageAdpterClass extends RecyclerView.Adapter<CoupanPageAdpterClass.myviewholder> {
    Context context;
    List<CoupanPageModel.BookSchedule> list;

    public CoupanPageAdpterClass(Context context, List<CoupanPageModel.BookSchedule> list) {
        this.context = context;
        this.list = list;
    }
    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.coupan_recycler_view_recy_layout,parent,false);
        return new myviewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myviewholder holder, int position) {
        holder.username.setText(list.get(position).Coup_UserName);
        holder.tv_password.setText(list.get(position).Coup_Password);
        holder.tv_StudioName.setText(list.get(position).Coup_StudioName);
        holder.tv_Coup_SchemeName.setText(list.get(position).Coup_SchemeName);
        holder.tv_Coup_SchemesCode.setText(list.get(position).Coup_SchemesCode);
        holder.tv_Coup_IssueDate.setText(list.get(position).Coup_IssueDate);
        holder.tv_Coup_ExpiryDate.setText(list.get(position).Coup_ExpiryDate);
        holder.tv_Coup_Status.setText(list.get(position).Coup_Status);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class myviewholder extends RecyclerView.ViewHolder{
        TextView username,tv_password,tv_StudioName,tv_Coup_SchemeName,tv_Coup_SchemesCode,tv_Coup_IssueDate,tv_Coup_Status,tv_Coup_ExpiryDate;
        public myviewholder(@NonNull View itemView) {
            super(itemView);
            username=itemView.findViewById(R.id.coupan_deails_Coup_UserName);
            tv_Coup_SchemeName=itemView.findViewById(R.id.coupan_deails_Coup_SchemeName);
            tv_password=itemView.findViewById(R.id.coupan_deails_Coup_Password);
            tv_StudioName=itemView.findViewById(R.id.coupan_deails_Coup_StudioName);
            tv_Coup_SchemesCode=itemView.findViewById(R.id.coupan_deails_Coup_SchemesCode);
            tv_Coup_IssueDate=itemView.findViewById(R.id.coupan_deails_Coup_IssueDate);
            tv_Coup_Status=itemView.findViewById(R.id.coupan_deails_Coup_Status);
            tv_Coup_ExpiryDate=itemView.findViewById(R.id.coupan_deails_Coup_ExpiryDate);


        }
    }
}
