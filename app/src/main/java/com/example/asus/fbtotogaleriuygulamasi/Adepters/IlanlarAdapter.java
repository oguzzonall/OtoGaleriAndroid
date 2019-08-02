package com.example.asus.fbtotogaleriuygulamasi.Adepters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.asus.fbtotogaleriuygulamasi.Models.IlanlarPojo;
import com.example.asus.fbtotogaleriuygulamasi.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class IlanlarAdapter extends BaseAdapter {
    List<IlanlarPojo> ilanlarpojo;
    Context context;

    public IlanlarAdapter(List<IlanlarPojo> ilanlarpojo, Context context) {
        this.ilanlarpojo = ilanlarpojo;
        this.context = context;
    }

    @Override
    public int getCount() {
        return ilanlarpojo.size();
    }

    @Override
    public Object getItem(int position) {
        return ilanlarpojo.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView=LayoutInflater.from(context).inflate(R.layout.ilanlarlayout,parent,false);
        TextView baslik,fiyat,adres;
        ImageView resim;

        baslik=convertView.findViewById(R.id.ilanlarIlanBaslik);
        fiyat=convertView.findViewById(R.id.ilanlarIlanFiyat);
        adres=convertView.findViewById(R.id.ilanlarIlanAdres);
        resim=convertView.findViewById(R.id.ilanlarIlanResim);

        baslik.setText(ilanlarpojo.get(position).getBaslik());
        fiyat.setText(ilanlarpojo.get(position).getFiyat());
        adres.setText(ilanlarpojo.get(position).getIl()+" / "+ilanlarpojo.get(position).getIlce()+" / "+ilanlarpojo.get(position).getMahalle());
        Picasso.with(context).load("http://oguzzonall.somee.com" + ilanlarpojo.get(position).getResim()).resize(100,100).into(resim);


        return convertView;
    }
}
