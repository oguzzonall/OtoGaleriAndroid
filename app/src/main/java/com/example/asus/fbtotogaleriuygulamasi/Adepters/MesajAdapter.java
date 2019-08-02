package com.example.asus.fbtotogaleriuygulamasi.Adepters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.asus.fbtotogaleriuygulamasi.Models.MesajModel;
import com.example.asus.fbtotogaleriuygulamasi.R;

import java.util.List;

public class MesajAdapter extends RecyclerView.Adapter {
    List<MesajModel> list;
    boolean state = false;
    static final int   user = 5, other = 10;
    Context context;
    String userId;

    public MesajAdapter(List<MesajModel> list, Context context,String userId) {
        this.list = list;
        this.context = context;
        this.userId=userId;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view;
        if (viewType == user) {
            view = LayoutInflater.from(context).inflate(R.layout.user, viewGroup, false);
            return new ViewHolder(view);
        } else {
            view = LayoutInflater.from(context).inflate(R.layout.other, viewGroup, false);
            return new ViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder viewHolder, int position) {
        MesajModel m= list.get(position);
        ((ViewHolder)viewHolder).setle(m);
/*        switch (viewHolder.getItemViewType())
        {
            case user:
                ((ViewHolder)viewHolder).setle(m);
                break;
            case other:
                ((ViewHolder)viewHolder).setle(m);
                break;
        }*/
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView mesajbody;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            if(state)
            {
                mesajbody=itemView.findViewById(R.id.userText);
            }
            else {
                mesajbody=itemView.findViewById(R.id.otherText);
            }
        }

        void  setle(MesajModel msj)
        {
            mesajbody.setText(msj.getMesaj().toString());
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (list.get(position).getFrom().equals(userId)) {
            state = true;
            return user;
        } else {
            state = false;
            return other;
        }
    }
}
