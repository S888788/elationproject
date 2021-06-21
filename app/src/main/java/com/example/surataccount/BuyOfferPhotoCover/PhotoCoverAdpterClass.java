package com.example.surataccount.BuyOfferPhotoCover;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.surataccount.R;

import java.util.List;

public class PhotoCoverAdpterClass extends RecyclerView.Adapter<PhotoCoverAdpterClass.myviewholder> {
    Context context;
    List<PhotoCoverTable.All_Photo> photoList;
    ProgressDialog progressDialog;

    public PhotoCoverAdpterClass(Context context, List<PhotoCoverTable.All_Photo> photoList) {
        this.context = context;
        this.photoList = photoList;
    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.photocover_recycler_layout_file,parent,false);
        return new myviewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myviewholder holder, final int position) {

        holder.txt_cover_name.setText(photoList.get(position).CoverName);
        holder.txt_mrp.setText("MRP :-"+photoList.get(position).MRP);
        Glide.with(context)
                .load(photoList.get(position).PhotoCover)
                .into(holder.photo_cover_img);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(context, "Photo click", Toast.LENGTH_SHORT).show();
              Intent intent=new Intent(context,PhotoCoverFullScreenActivity.class);
               intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
              intent.putExtra("img",photoList.get(position).PhotoCover);
                intent.putExtra("mrp",photoList.get(position).MRP);
               // Toast.makeText(context, photoList.get(position).PhotoCover+"", Toast.LENGTH_SHORT).show();
                context.startActivity(intent);


            }
        });
    }

    @Override
    public int getItemCount() {
        return photoList.size();
    }

    public  class myviewholder extends RecyclerView.ViewHolder{
    ImageView photo_cover_img;
    TextView txt_cover_name,txt_mrp;
    public myviewholder(@NonNull View itemView) {
        super(itemView);
        photo_cover_img=(ImageView)itemView.findViewById(R.id.photo_cover_img);
        txt_cover_name=(TextView)itemView.findViewById(R.id.txt_photo_cover_name);
        txt_mrp=(TextView)itemView.findViewById(R.id.txt_mrp);
    }
}

}
