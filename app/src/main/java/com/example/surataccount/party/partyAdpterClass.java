package com.example.surataccount.party;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.surataccount.R;
import com.example.surataccount.viewDetails.PaymentModeActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class partyAdpterClass  extends RecyclerView.Adapter<partyAdpterClass.MyviewHolde> implements Filterable {
    Context context;
    List<PartyGetterSetterClass> item;
    List<PartyGetterSetterClass> studioNameList;
    SwipeRefreshLayout swipeRefreshLayout;



    public partyAdpterClass(Context context, List<PartyGetterSetterClass> item, SwipeRefreshLayout swipeRefreshLayout) {

        this.context = context;
        this.item = item;
        studioNameList=new ArrayList<>(item);
        this.swipeRefreshLayout=swipeRefreshLayout;


    }

    @NonNull
    @Override
    public MyviewHolde onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.party_recycler_layout,parent,false);
        return new MyviewHolde(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyviewHolde holder, final int position) {
        final PartyGetterSetterClass cont=item.get(position);
        holder.oping_balance.setText(item.get(position).getOpeningBalance());
        holder.tv_party_code.setText(item.get(position).getPartyCode());
        holder.tv_party_name.setText(item.get(position).getPartyName());
        holder.tv_amt_pay.setText(item.get(position).getAmountPayable());
        holder.tv_amt_rec.setText(item.get(position).getAmountRecieved());

        String date=cont.getBalanceAmt();
        double ou=Math.round(Double.valueOf(date)*100)/100;
       // Toast.makeText(context, "ghj"+ou, Toast.LENGTH_SHORT).show();
        holder.tv_bal_amt.setText(String.valueOf(ou));
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                PartyGetterSetterClass contan = item.get(position);
                String namepaty = contan.getPartyName();
                String patycode = contan.getPartyCode();
                String balAmt = contan.getBalanceAmt();
                Intent i2 = new Intent(context, PaymentModeActivity.class);
                i2.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);

                SharedPreferences sharedpreferences;
                sharedpreferences = context.getSharedPreferences("stcodebmtname", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.putString("stname",namepaty);
                editor.putString("stcode", patycode);
                editor.putString("balamt", balAmt);
                editor.commit();
                context.startActivity(i2);
            }
        });
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Handler handler=new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        item.add(0,item.get(new Random().nextInt(item.size())));
                        partyAdpterClass.this.notifyDataSetChanged();
                        swipeRefreshLayout.setRefreshing(false);

                    }
                },2000);
            }
        });

    }

    @Override
    public int getItemCount() {
        return item.size();
    }

    @Override
    public Filter getFilter() {
        return studioNameListFilte;
    }
    private Filter studioNameListFilte =new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            List<PartyGetterSetterClass> filterlist =new ArrayList<>();
            if(charSequence==null ||charSequence.length()==0)
            {
                filterlist.addAll(studioNameList);
            }
            else
            {
                String filtterPatten=charSequence.toString().toUpperCase().trim();
                for (PartyGetterSetterClass item : studioNameList)
                {
                    if (item.getPartyName().toUpperCase().contains(filtterPatten))
                    {
                        filterlist.add(item);
                    }
                }
            }
            FilterResults results=new FilterResults();
            results.values=filterlist;
            return  results;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            item.clear();
            item.addAll((List)filterResults.values);
            notifyDataSetChanged();

        }
    };

    public  class MyviewHolde extends RecyclerView.ViewHolder{
        public TextView tv_party_code,tv_party_name,tv_amt_pay,tv_amt_rec,tv_bal_amt,oping_balance;
        public LinearLayout linearLayout;

        public MyviewHolde(@NonNull View itemView) {

            super(itemView);
            tv_party_code=itemView.findViewById(R.id.bal_tv_party_code);
            tv_party_name=itemView.findViewById(R.id.bal_tv_party_name);
            tv_amt_pay=itemView.findViewById(R.id.bal_tv_amt_pay);
            tv_amt_rec=itemView.findViewById(R.id.bal_tv_amt_rec);
            tv_bal_amt=itemView.findViewById(R.id.bal_tv_bal_amt);
            oping_balance=itemView.findViewById(R.id.opting_balance);
            linearLayout=itemView.findViewById(R.id.bal_recy_lay_liner);
        }
    }

}
