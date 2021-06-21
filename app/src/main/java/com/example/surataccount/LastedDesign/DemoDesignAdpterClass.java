package com.example.surataccount.LastedDesign;

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

public class DemoDesignAdpterClass extends RecyclerView.Adapter<DemoDesignAdpterClass.myviewholder> {
    Context context;

    public DemoDesignAdpterClass(Context context, List<DemoDesignModel.DESIGNData> mlist) {
        this.context = context;
        this.mlist = mlist;
    }

    List<DemoDesignModel.DESIGNData> mlist;

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.demo_design_recycler_view_layout_file,parent,false);
        return  new myviewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myviewholder holder, int position) {
        Glide.with(context)
                .load(mlist.get(position).ImagePath)
                .into(holder.demo_image_path);
        holder.txt_album.setText(mlist.get(position).AlbumName);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context,DemoAlbumPageActivity.class);
                context.startActivity(intent);
                
            }
        });
    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }

    public class myviewholder extends RecyclerView.ViewHolder{
        ImageView demo_image_path;
        TextView txt_album;
        public myviewholder(@NonNull View itemView) {
            super(itemView);
            demo_image_path=itemView.findViewById(R.id.im_demo_design_ImagePath);
            txt_album=itemView.findViewById(R.id.txt_demo_design_AlbumName);

        }
    }
}
