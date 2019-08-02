package com.example.asus.fbtotogaleriuygulamasi.Adepters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.asus.fbtotogaleriuygulamasi.IlanDetay;
import com.example.asus.fbtotogaleriuygulamasi.Ilanlar;
import com.example.asus.fbtotogaleriuygulamasi.Models.FavoriSliderPojo;
import com.example.asus.fbtotogaleriuygulamasi.Models.SliderPojo;
import com.example.asus.fbtotogaleriuygulamasi.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class FavoriSliderAdapter extends PagerAdapter {

    List<FavoriSliderPojo> list;
    Context context;
    LayoutInflater layoutInflater;
    Activity activity;

    public FavoriSliderAdapter(List<FavoriSliderPojo> list, Context context, Activity activity) {
        this.list = list;
        this.context = context;
        this.activity = activity;
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
    public Object instantiateItem(ViewGroup container, final int position) {
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.sliderlayout, container, false);

        ImageView ımageView = (ImageView) view.findViewById(R.id.sliderImageView);
        Picasso.with(context).load("http://oguzzonall.somee.com" + list.get(position).getResimyolu()).resize(1050, 550).into(ımageView);

        ımageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (list.get(position).getIlanid() > 0) {
                    Intent intent = new Intent(activity, IlanDetay.class);
                    intent.putExtra("ilanid", list.get(position).getIlanid());
                    activity.startActivity(intent);
                }
            }
        });
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }


}

