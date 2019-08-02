package com.example.asus.fbtotogaleriuygulamasi.Adepters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.asus.fbtotogaleriuygulamasi.Models.SliderPojo;
import com.example.asus.fbtotogaleriuygulamasi.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class SliderAdapter extends PagerAdapter {

    List<SliderPojo> list;
    Context context;
    LayoutInflater layoutInflater;

    public SliderAdapter(List<SliderPojo> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return (view == (RelativeLayout) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.sliderlayout, container, false);

        ImageView ımageView = (ImageView) view.findViewById(R.id.sliderImageView);
        Picasso.with(context).load("http://oguzzonall.somee.com" + list.get(position).getResim()).resize(1050, 550).into(ımageView);

        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}

