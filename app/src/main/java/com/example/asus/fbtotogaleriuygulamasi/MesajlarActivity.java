package com.example.asus.fbtotogaleriuygulamasi;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.asus.fbtotogaleriuygulamasi.Adepters.MesajAdapter;
import com.example.asus.fbtotogaleriuygulamasi.Adepters.MesajlarListAdapter;
import com.example.asus.fbtotogaleriuygulamasi.Models.MesajModel;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class MesajlarActivity extends AppCompatActivity {
    List<String> otherIdList;
    String userId;
    SharedPreferences sharedPreferences;
    DatabaseReference reference;
    MesajlarListAdapter mesajlarListAdapter;
    Context context=this;
    ListView mesajlarListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mesajlar);
        otherIdList = new ArrayList<>();
        sharedPreferences = getApplicationContext().getSharedPreferences("giris", 0);
        userId = String.valueOf(sharedPreferences.getInt("uye_id", 0));
        reference = FirebaseDatabase.getInstance().getReference();
        mesajlarListAdapter=new MesajlarListAdapter(context,otherIdList,userId,MesajlarActivity.this);
        mesajlarListView=findViewById(R.id.mesajlarListView);
        mesajlarListView.setAdapter(mesajlarListAdapter);
        listele();
    }

    public void listele() {
        reference.child("messages").child(userId).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                otherIdList.add(dataSnapshot.getKey());
                mesajlarListAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                MesajModel m=dataSnapshot.getValue(MesajModel.class);
                otherIdList.remove(m);
                mesajlarListAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
