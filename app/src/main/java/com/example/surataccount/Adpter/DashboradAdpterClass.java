package com.example.surataccount.Adpter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.surataccount.AboutPack.AboutActivity;
import com.example.surataccount.BookSchedule.BookScheduleActivity;
import com.example.surataccount.BuyOfferPhotoCover.OfferMainPageActivity;
import com.example.surataccount.BuyOfferPhotoCover.OfferPageActivity;
import com.example.surataccount.CoupanPack.CoupanSummaryActivity;
import com.example.surataccount.Dashboard.Dashboard;
import com.example.surataccount.FAQ.FAQListActivity;
import com.example.surataccount.FAQ.NewTicketActivity;
import com.example.surataccount.LastedDesign.LastedDesignActivity;
import com.example.surataccount.Photobook_Status.Phtobook_StatusActivity;
import com.example.surataccount.R;
import com.example.surataccount.User.LoginActivity;
import com.example.surataccount.User.Session_Use;
import com.example.surataccount.billed.billed_getter_setter_class;
import com.example.surataccount.party.PartyActivity;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

public class DashboradAdpterClass extends RecyclerView.Adapter<DashboradAdpterClass.viewholder> {
    Context context;
    List<String> title;
    List<Integer> images;

    LayoutInflater layoutInflater;

    public DashboradAdpterClass(Context context, List<String> title, List<Integer> images) {
        this.context = context;
        this.title = title;
        this.images = images;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view=layoutInflater.inflate(R.layout.dashborde_gridview_layout_file,parent,false);
       return  new viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {
        String ti=title.get(position);
        holder.textView.setText(title.get(position));
        holder.imageView.setImageResource(images.get(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              if(ti.equals("Ledger & Payment"))
              {
                  Intent intent=new Intent(context, PartyActivity.class);
                  intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                  context.startActivity(intent);
              }
              else if(ti.equals("Photobook Design"))
              {
                  Intent intent=new Intent(context, LastedDesignActivity.class);
                  intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                  context.startActivity(intent);
              }
              else  if(ti.equals("Photobook Status"))
              {
                  Intent intent=new Intent(context, Phtobook_StatusActivity.class);
                  intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                  context.startActivity(intent);
              }
              else if(ti.equals("Coupon"))
              {
                  Intent intent=new Intent(context, CoupanSummaryActivity.class);
                  intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                  context.startActivity(intent);
              }
              else if(ti.equals("ReadyMade Cover"))
              {
                  Intent intent=new Intent(context, OfferPageActivity.class);
                  intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                  context.startActivity(intent);

              }
              else if (ti.equals("PriceList/Offer"))
              {
                  Intent intent=new Intent(context, OfferMainPageActivity.class);
                  intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                  context.startActivity(intent);

              }
              else if (ti.equals("Scheduler"))
              {
                  Intent intent=new Intent(context, BookScheduleActivity.class);
                  intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                  context.startActivity(intent);
              }
              else if(ti.equals("FAQ/Support"))
              {
                  final CharSequence[] items = {"New Ticket", "Common Question", "My  Support", "How to use App"};
//
                AlertDialog.Builder builder=new AlertDialog.Builder(context);
                builder.setTitle(" ....User Help Section ...");
                builder.setIcon(R.drawable.splash);
                builder.setSingleChoiceItems(items, -1, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int item) {
                        Toast.makeText(context, items[item], Toast.LENGTH_SHORT).show();
                        if(items[item].equals("New Ticket"))
                        {
                            Intent intent=new Intent(context, NewTicketActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                            context.startActivity(intent);
                        }
                        else  if(items[item].equals("Common Question"))
                        {

                        }
                        else  if(items[item].equals("My  Support"))
                        {
                            Intent intent=new Intent(context,FAQListActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                            context.startActivity(intent);

                        }
                        else if(items[item].equals("How to use App"))
                        {

                        }
                    }
                });

                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                builder.setCancelable(true);
                            }
                        });
                AlertDialog alert = builder.create();
                alert.show();
            }


              else if(ti.equals("About Us"))
              {
                  Intent intent=new Intent(context, AboutActivity.class);
                  intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                  context.startActivity(intent);
              }
              else if(ti.equals("Logout"))
              {
                  new Session_Use(context).removeUser();
                Intent intent=new Intent(context, LoginActivity.class);
                context.startActivity(intent);
              }


            }
        });
    }

    @Override
    public int getItemCount() {
        return title.size();
    }

    public  class  viewholder extends RecyclerView.ViewHolder{
        TextView textView;
        ImageView imageView;

        public viewholder(@NonNull View itemView) {
            super(itemView);
            textView=(TextView)itemView.findViewById(R.id.txt_dash_bord_grid);
            imageView=(ImageView)itemView.findViewById(R.id.dash_gridview_im);
        }
    }

    public static class billed_adpter_class extends RecyclerView.Adapter<billed_adpter_class.myviewholers> {
        Context context;
        List<billed_getter_setter_class> mList;
        ImageView billedimg,dipodteimg;

        public billed_adpter_class(Context context, List<billed_getter_setter_class> mList, ImageView billedimg, ImageView dipodteimg) {
            this.context = context;
            this.mList = mList;
            this.billedimg = billedimg;
            this.dipodteimg = dipodteimg;
        }
        @NonNull
        @Override
        public myviewholers onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.billed_frag_recycler_layout,parent,false);

            return new myviewholers(view);


        }

        @Override
        public void onBindViewHolder(@NonNull myviewholers holder, int position) {
            billed_getter_setter_class items = mList.get(position);

            String []dat=items.getVouchernumber().split(" ");
            holder.tv_tr_code.setText(items.getVouchernumber());
            String date=items.getVoucherdate();
            SimpleDateFormat dateFormat=new SimpleDateFormat("dd-MMMM-YYYY");
            String d= dateFormat.format(Date.valueOf(date));


            holder.tv_tr_date.setText(d);

           // holder.tv_tr_date.setText(items.getTrasactionDate());
            holder.tv_st_code.setText(items.getStudioCode());
            holder.tv_st_name.setText(items.getStudioname());
            holder.tv_amt_pay.setText(items.getAmtPayable());
            holder.tv_amt_rec.setText(items.getAmtRecieved());
            String valu=items.getAmtRecieved();
            if (valu.equals("0"))
            {
                holder.diposite.setVisibility(View.VISIBLE);
                holder.cashedtextview.setVisibility(View.VISIBLE);

            }
            else
            {
                holder.billed.setVisibility(View.VISIBLE);
                holder.billedtextview.setVisibility(View.VISIBLE);
            }
        }

        @Override
        public int getItemCount() {
            return mList.size();
        }

        public class myviewholers extends RecyclerView.ViewHolder{
            public TextView cashedtextview,billedtextview,tv_data,tv_st_code,tv_st_name,tv_tr_date,tv_tr_code,tv_amt_pay,tv_amt_rec;
            public ImageView billed,diposite;


            public myviewholers(@NonNull View itemView) {
                super(itemView);
                tv_tr_code=(TextView) itemView.findViewById(R.id.tv_billed_fra_re_lay_tra_code);
                tv_tr_date=(TextView) itemView.findViewById(R.id.tv_billed__fra_re_lay_tra_date);

                tv_st_code=(TextView) itemView.findViewById(R.id.tv_billed_frag_re_lay_st_code);
                tv_st_name=(TextView) itemView.findViewById(R.id.tv_billed_frag_re_lay_st_name);
                tv_amt_pay=(TextView) itemView.findViewById(R.id.tv_billed_fra_re_lay_amt_pay);
                tv_amt_rec=(TextView) itemView.findViewById(R.id.tv_billed_fra_re_lay_amt_rec);
                billed=(ImageView)itemView.findViewById(R.id.bill_image_bil);
                diposite=(ImageView)itemView.findViewById(R.id.dip_image_bil);
                cashedtextview=(TextView) itemView.findViewById(R.id.tv_billed_cashed);
                billedtextview=(TextView) itemView.findViewById(R.id.billed_tv_billed);

            }
        }
    }
}
