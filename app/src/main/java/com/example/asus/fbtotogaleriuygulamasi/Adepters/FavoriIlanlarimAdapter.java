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

public class FavoriIlanlarimAdapter extends BaseAdapter {
    List<IlanlarPojo> favoriIlanlarimpojo;
    Context context;

    public FavoriIlanlarimAdapter(List<IlanlarPojo> favoriIlanlarimpojo, Context context) {
        this.favoriIlanlarimpojo = favoriIlanlarimpojo;
        this.context = context;
    }

    @Override
    public int getCount() {
        return favoriIlanlarimpojo.size();
    }

    @Override
    public Object getItem(int position) {
        return favoriIlanlarimpojo.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(R.layout.favoriilanlarim, parent, false);
        TextView baslik, fiyat, adres;
        ImageView resim;

        baslik = convertView.findViewById(R.id.favoriIlanlarIlanBaslik);
        fiyat = convertView.findViewById(R.id.favoriIlanlarIlanFiyat);
        adres = convertView.findViewById(R.id.favoriIlanlarIlanAdres);
        resim = convertView.findViewById(R.id.favoriIlanlarIlanResim);

        baslik.setText(favoriIlanlarimpojo.get(position).getBaslik());
        fiyat.setText(favoriIlanlarimpojo.get(position).getFiyat());
        adres.setText(favoriIlanlarimpojo.get(position).getIl() + " / " + favoriIlanlarimpojo.get(position).getIlce() + " / " + favoriIlanlarimpojo.get(position).getMahalle());
        Picasso.with(context).load("http://oguzzonall.somee.com" + favoriIlanlarimpojo.get(position).getResim()).resize(100, 100).into(resim);

        return convertView;
    }
}
