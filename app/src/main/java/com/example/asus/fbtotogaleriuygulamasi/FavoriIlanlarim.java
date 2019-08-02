package com.example.asus.fbtotogaleriuygulamasi;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.asus.fbtotogaleriuygulamasi.Adepters.FavoriIlanlarimAdapter;
import com.example.asus.fbtotogaleriuygulamasi.Adepters.IlanlarAdapter;
import com.example.asus.fbtotogaleriuygulamasi.Models.IlanlarPojo;
import com.example.asus.fbtotogaleriuygulamasi.RestApi.ManagerAll;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FavoriIlanlarim extends AppCompatActivity {
    ListView listView;
    FavoriIlanlarimAdapter favoriIlanlarimAdapter;
    List<IlanlarPojo> favoriIlanlarimPojoList;
    SharedPreferences sharedPreferences;
    int uyeid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favori_ilanlarim);
        listView = findViewById(R.id.favoriIlanlarimListView);
        sharedPreferences = getApplicationContext().getSharedPreferences("giris", 0);
        uyeid=sharedPreferences.getInt("uye_id", 0);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(FavoriIlanlarim.this,IlanDetay.class);
                intent.putExtra("ilanid",favoriIlanlarimPojoList.get(position).getIlanid());
                startActivity(intent);
            }
        });
        FavoriIlanlarimiGoruntule();
    }

    public void FavoriIlanlarimiGoruntule() {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("İlanlar");
        progressDialog.setMessage("İlanlar Listeleniyor, Lütfen Bekleyin..");
        progressDialog.setCancelable(false);
        progressDialog.show();

        favoriIlanlarimPojoList = new ArrayList<>();
        Call<List<IlanlarPojo>> request = ManagerAll.getGetInstanse().FavoriIlanlarimiGetir(uyeid);
        request.enqueue(new Callback<List<IlanlarPojo>>() {
            @Override
            public void onResponse(Call<List<IlanlarPojo>> call, Response<List<IlanlarPojo>> response) {
                Log.i("awdwdw",response.body().toString());
                if (response.isSuccessful()) {
                    if (response.body().get(0).isTf()) {
                        favoriIlanlarimPojoList = response.body();
                        favoriIlanlarimAdapter = new FavoriIlanlarimAdapter(favoriIlanlarimPojoList,getApplicationContext());
                        listView.setAdapter(favoriIlanlarimAdapter);

                        Toast.makeText(FavoriIlanlarim.this, response.body().size()+" tane İlan listelenmiştir.", Toast.LENGTH_SHORT).show();
                        progressDialog.cancel();
                    } else {
                        Toast.makeText(FavoriIlanlarim.this,response.body().get(0).getAciklama(), Toast.LENGTH_SHORT).show();
                        progressDialog.cancel();
                    }
                }
            }

            @Override
            public void onFailure(Call<List<IlanlarPojo>> call, Throwable t) {
                Toast.makeText(FavoriIlanlarim.this, "İnternet Bağlantınızı Kontrol Ediniz.", Toast.LENGTH_SHORT).show();
                progressDialog.cancel();
            }
        });
    }
}
