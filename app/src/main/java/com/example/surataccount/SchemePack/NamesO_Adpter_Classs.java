package com.example.surataccount.SchemePack;

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

public class NamesO_Adpter_Classs extends RecyclerView.Adapter<NamesO_Adpter_Classs.myvaiewHolder>{
    Context context;
    List<NamesO_Gettter_Setteer_Class> items;

    public NamesO_Adpter_Classs(Context context, List<NamesO_Gettter_Setteer_Class> items) {
        this.context = context;
        this.items=items;
    }

    @NonNull
    @Override
    public myvaiewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.cuopan_offer_recyrcle_view_layout_file,parent,false);
        return new myvaiewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myvaiewHolder holder, final int position) {
        NamesO_Gettter_Setteer_Class gettter_setteer_class=items.get(position);
        holder.tv_schemeName.setText(gettter_setteer_class.getSchemeName());
       holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NamesO_Gettter_Setteer_Class i=items.get(position);
            String schamename=    i.getSchemeName();
                Intent intent=new Intent(context, SchmeNameOrCuopanDetailsActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("schamename",schamename);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {

        return items.size();
    }

    public  class  myvaiewHolder extends RecyclerView.ViewHolder{

        private TextView tv_schemeName;
        public myvaiewHolder(@NonNull View itemView) {

            super(itemView);
            tv_schemeName=(TextView)itemView.findViewById(R.id.cuopan_name_sheme);
        }
    }
}
