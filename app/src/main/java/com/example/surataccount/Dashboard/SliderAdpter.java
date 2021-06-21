package com.example.surataccount.Dashboard;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.android.volley.toolbox.ImageLoader;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.example.surataccount.R;
import com.smarteist.autoimageslider.SliderViewAdapter;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class SliderAdpter extends SliderViewAdapter<SliderAdpter.SliderAdapterVH> {

    // creating a variable for
    // context and array list.
    private Context context;
    private List<SliderGetterSetter> mSliderItems = new ArrayList<>();

    // constructor for our adapter class.
    public SliderAdpter(Context context, List<SliderGetterSetter> mSliderItems) {
        this.context = context;
        this.mSliderItems = mSliderItems;
    }

    @Override
    public SliderAdapterVH onCreateViewHolder(ViewGroup parent) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.dashboard_slider_item_layout, null);
        return new SliderAdapterVH(inflate);
    }

    @Override
    public void onBindViewHolder(SliderAdapterVH viewHolder, int position) {
        final SliderGetterSetter sliderItem = mSliderItems.get(position);
       // Picasso.get().load(sliderItem.getSliderImageUrl()).into(viewHolder.imageView);
        Glide.with(context)
                .load(sliderItem.sliderImageUrl)
                .apply(RequestOptions.skipMemoryCacheOf(true))
                .apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.NONE))
                .into(viewHolder.imageView);
    }

    @Override
    public int getCount() {
        return mSliderItems.size();
    }


    // view holder class for initializing our view holder.
    class SliderAdapterVH extends SliderViewAdapter.ViewHolder {

        // variables for our view and image view.
        View itemView;
        ImageView imageView;

        public SliderAdapterVH(View itemView) {
            super(itemView);
            // initializing our views.
            imageView = itemView.findViewById(R.id.idIVimage);
            this.itemView = itemView;
        }
    }
}

