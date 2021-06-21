package com.example.surataccount.SchemePack;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.surataccount.R;

import java.util.List;

import static com.example.surataccount.R.layout.scheme_detail_recycler_view_layout_file;

public class SchemeNameDetailsAdpterClass extends RecyclerView.Adapter<SchemeNameDetailsAdpterClass.myviewHolder> {
    Context context;
    List<SchemeNameDetailsGetterSetter> item;

    public SchemeNameDetailsAdpterClass(Context context, List<SchemeNameDetailsGetterSetter> item) {
        this.context = context;
        this.item = item;
    }

    @NonNull
    @Override
    public myviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(scheme_detail_recycler_view_layout_file,parent,false);
        return new myviewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myviewHolder holder, int position) {

        SchemeNameDetailsGetterSetter getterSetter=item.get(position);
        holder.tv_SchemeId.setText(getterSetter.getSchemeId());
        holder.tv_SchemeCode.setText(getterSetter.getSchemeCode());
        holder.tv_SchemeName.setText(getterSetter.getSchemeName());
        holder.tv_DepositeAmt.setText(getterSetter.getDepositeAmt());
        holder.tv_SchemeDetail.setText(getterSetter.getSchemeDetail());
        holder.tv_SchemeExpiry.setText(getterSetter.getSchemeExpiry());

    }

    @Override
    public int getItemCount() {
        return item.size();
    }

    public class myviewHolder extends RecyclerView.ViewHolder{
        private TextView tv_SchemeId,tv_SchemeCode,tv_SchemeName,tv_DepositeAmt,tv_SchemeDetail,tv_SchemeExpiry;

        public myviewHolder(@NonNull View itemView) {
            super(itemView);
            tv_SchemeId=(TextView)itemView.findViewById(R.id.cuopan_detail_scheme_id);
            tv_SchemeCode=(TextView)itemView.findViewById(R.id.cuopan_detail_scheme_code);
            tv_SchemeName=(TextView)itemView.findViewById(R.id.cuopan_detail_SchemeName);
            tv_DepositeAmt=(TextView)itemView.findViewById(R.id.cuopan_detail_depositeAmt);
            tv_SchemeDetail=(TextView)itemView.findViewById(R.id.cuopan_detail_schemeDetail);
            tv_SchemeExpiry=(TextView)itemView.findViewById(R.id.cuopan_detail_SchemeExpiry);
        }
    }
}
