package com.example.asus.fbtotogaleriuygulamasi;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.asus.fbtotogaleriuygulamasi.Adepters.MesajAdapter;
import com.example.asus.fbtotogaleriuygulamasi.Models.MesajModel;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChatActivity extends AppCompatActivity {
    Context context=this;
    String userTable, otherTable, key, userId, otherId;
    SharedPreferences sharedPreferences;
    DatabaseReference reference;
    Button sendMesajButton;
    EditText mesajEditText;
    List<MesajModel> list;
    MesajAdapter adapter;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        tanimla();
        action();
        load();
    }

    public void tanimla() {
        sendMesajButton = findViewById(R.id.sendMesajButton);
        mesajEditText = findViewById(R.id.mesajEditText);
        reference = FirebaseDatabase.getInstance().getReference();
        otherId = String.valueOf(OtherId.getOtherId());
        sharedPreferences = getApplicationContext().getSharedPreferences("giris", 0);
        userId = String.valueOf(sharedPreferences.getInt("uye_id", 0));
        list=new ArrayList<>();
        adapter=new MesajAdapter(list,context,userId);
        recyclerView=findViewById(R.id.recyclerView);
        RecyclerView.LayoutManager manager=new LinearLayoutManager(context);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
    }

    public void action() {
        sendMesajButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMessage(mesajEditText.getText().toString(), userId, otherId);
            }
        });
    }

    public void sendMessage(String mesajBody, String UsrId, String othId) {
        userTable = "messages/" + UsrId + "/" + othId;
        otherTable = "messages/" + othId + "/" + UsrId;
        key = reference.child("messages").child(UsrId).child(othId).push().getKey();
        Map map = send(mesajBody, UsrId, othId);
        Map map2 = new HashMap();
        map2.put(userTable + "/" + key, map);
        map2.put(otherTable + "/" + key, map);
        mesajEditText.setText("");
        reference.updateChildren(map2, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {

            }
        });
    }

    public Map send(String mesajbody, String userId, String otherId) {
        Map msj = new HashMap();
        msj.put("mesaj", mesajbody);
        msj.put("from", userId);
        msj.put("to", otherId);
        return msj;
    }

    public void load() {
        reference.child("messages").child(userId).child(otherId).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                MesajModel m = dataSnapshot.getValue(MesajModel.class);
                list.add(m);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                MesajModel m = dataSnapshot.getValue(MesajModel.class);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                MesajModel m = dataSnapshot.getValue(MesajModel.class);
                list.remove(m);
                adapter.notifyDataSetChanged();
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
