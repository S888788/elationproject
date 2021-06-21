package com.example.surataccount.PriceList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.surataccount.R;
import com.example.surataccount.SchemePack.NamesO_Adpter_Classs;

import java.util.List;

public class PriceListAdpterClass extends RecyclerView.Adapter<PriceListAdpterClass.myviewholder> {
    Context context;

    public PriceListAdpterClass(Context context, List<PriceListModelClass.PriceListModel> listModels) {
        this.context = context;
        this.listModels = listModels;
    }

    List<PriceListModelClass.PriceListModel> listModels;
    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.price_list_recycler_layout_file,parent,false);
        return new myviewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myviewholder holder, int position) {
        holder.txt_name.setText(listModels.get(position).name);
        holder.txtx_price.setText(listModels.get(position).price);
    }

    @Override
    public int getItemCount() {
        return listModels.size();
    }

    public class  myviewholder extends RecyclerView.ViewHolder{
        TextView txt_name,txtx_price;
        public myviewholder(@NonNull View itemView) {
            super(itemView);
            txt_name=(TextView)itemView.findViewById(R.id.pricelist_name);
            txtx_price=(TextView)itemView.findViewById(R.id.pricelist_price);

        }
    }
}
