package com.example.surataccount.BuyOfferPhotoCover;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.surataccount.R;

import java.util.List;

public class OfferPageAdpter extends RecyclerView.Adapter<OfferPageAdpter.myviewholder> {
    Context context;
    List<PhotoTypeTable.All_Photo_Type> mList;

    public OfferPageAdpter(Context context, List<PhotoTypeTable.All_Photo_Type> mList) {
        this.context = context;
        this.mList = mList;
    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.offer_page_photo_all_type_reccycler_view_layout_file,parent,false);
        return new myviewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myviewholder holder, final int position) {

        holder.txt_cover_name.setText(mList.get(position).Photo_id);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context,PhotoCovverActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("photo_id",mList.get(position).Photo_id);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public  class myviewholder extends RecyclerView.ViewHolder{
        TextView txt_cover_name;
        public myviewholder(@NonNull View itemView) {
            super(itemView);
            txt_cover_name=(TextView)itemView.findViewById(R.id.offer_btn_photo_all_tupe);
        }
    }
}
