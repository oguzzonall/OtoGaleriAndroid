package com.example.asus.fbtotogaleriuygulamasi.Adepters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.asus.fbtotogaleriuygulamasi.ChatActivity;
import com.example.asus.fbtotogaleriuygulamasi.MesajlarActivity;
import com.example.asus.fbtotogaleriuygulamasi.Models.UserPojo;
import com.example.asus.fbtotogaleriuygulamasi.OtherId;
import com.example.asus.fbtotogaleriuygulamasi.R;
import com.example.asus.fbtotogaleriuygulamasi.RestApi.ManagerAll;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MesajlarListAdapter extends BaseAdapter {
    Context context;
    List<String> otherIdList;
    String userId;
    Activity activity;

    public MesajlarListAdapter(Context context, List<String> otherIdList, String userId,Activity activity) {
        this.context = context;
        this.otherIdList = otherIdList;
        this.userId = userId;
        this.activity=activity;
    }

    @Override
    public int getCount() {
        return otherIdList.size();
    }

    @Override
    public Object getItem(int position) {
        return otherIdList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(R.layout.other, parent, false);
        TextView textView = convertView.findViewById(R.id.otherText);
        istekAt(Integer.valueOf(otherIdList.get(position)), textView);
        LinearLayout linearLayout = convertView.findViewById(R.id.linearmesaj);
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity,ChatActivity.class);
                OtherId.setOtherId(Integer.valueOf(otherIdList.get(position)));
                activity.startActivity(intent);
            }
        });
        return convertView;
    }

    public void istekAt(int uye_id, final TextView textView) {
        Call<UserPojo> request = ManagerAll.getGetInstanse().getUserById(uye_id);
        request.enqueue(new Callback<UserPojo>() {
            @Override
            public void onResponse(Call<UserPojo> call, Response<UserPojo> response) {
                if (response.isSuccessful()) {
                    textView.setText(response.body().getKadi());
                }
            }

            @Override
            public void onFailure(Call<UserPojo> call, Throwable t) {

            }
        });
    }
}
