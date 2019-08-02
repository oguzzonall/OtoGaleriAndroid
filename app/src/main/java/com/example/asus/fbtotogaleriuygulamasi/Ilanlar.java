package com.example.asus.fbtotogaleriuygulamasi;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.asus.fbtotogaleriuygulamasi.Adepters.IlanlarAdapter;
import com.example.asus.fbtotogaleriuygulamasi.Models.IlanlarPojo;
import com.example.asus.fbtotogaleriuygulamasi.RestApi.ManagerAll;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Ilanlar extends AppCompatActivity {

    ListView listView;
    IlanlarAdapter ilanlarAdapter;
    List<IlanlarPojo> ilanlarPojoList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ilanlar);
        listView = findViewById(R.id.ilanlarListView);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(Ilanlar.this,IlanDetay.class);
                intent.putExtra("ilanid",ilanlarPojoList.get(position).getIlanid());
                startActivity(intent);
            }
        });
        ilanlariGoruntule();
    }

    public void ilanlariGoruntule() {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("İlanlar");
        progressDialog.setMessage("İlanlar Listeleniyor, Lütfen Bekleyin..");
        progressDialog.setCancelable(false);
        progressDialog.show();

        ilanlarPojoList = new ArrayList<>();
        Call<List<IlanlarPojo>> request = ManagerAll.getGetInstanse().IlanlariGetir();
        request.enqueue(new Callback<List<IlanlarPojo>>() {
            @Override
            public void onResponse(Call<List<IlanlarPojo>> call, Response<List<IlanlarPojo>> response) {
                if (response.isSuccessful()) {
                    if (response.body().get(0).isTf()) {
                        ilanlarPojoList = response.body();
                        ilanlarAdapter = new IlanlarAdapter(ilanlarPojoList, getApplicationContext());
                        listView.setAdapter(ilanlarAdapter);

                        Toast.makeText(Ilanlar.this, response.body().size()+" tane İlan listelenmiştir.", Toast.LENGTH_SHORT).show();
                        progressDialog.cancel();
                    } else {
                        Toast.makeText(Ilanlar.this,response.body().get(0).getAciklama(), Toast.LENGTH_SHORT).show();
                        progressDialog.cancel();
                    }
                }
            }

            @Override
            public void onFailure(Call<List<IlanlarPojo>> call, Throwable t) {
                Toast.makeText(Ilanlar.this, "İnternet Bağlantınızı Kontrol Ediniz.", Toast.LENGTH_SHORT).show();
                progressDialog.cancel();
            }
        });
    }
}
