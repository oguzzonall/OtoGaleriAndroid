package com.example.asus.fbtotogaleriuygulamasi;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.asus.fbtotogaleriuygulamasi.Adepters.IlanlarimAdapter;
import com.example.asus.fbtotogaleriuygulamasi.Models.IlanlarimPojo;
import com.example.asus.fbtotogaleriuygulamasi.Models.IlanlarimSilPojo;
import com.example.asus.fbtotogaleriuygulamasi.RestApi.ManagerAll;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Ilanlarim extends AppCompatActivity {
    ListView listView;
    IlanlarimAdapter ılanlarimAdapter;
    List<IlanlarimPojo> ılanlarimPojos;
    SharedPreferences sharedPreferences;
    int uyeid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ilanlarim);
        listView = findViewById(R.id.ilanlarimListView);
        sharedPreferences = getApplicationContext().getSharedPreferences("giris", 0);
        uyeid = sharedPreferences.getInt("uye_id", 0);
        ilanlarimiGoruntule();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ilanlarimAlertDialog(Ilanlarim.this, ılanlarimPojos.get(position).getIlanid());
            }
        });
    }

    public void ilanlarimiGoruntule() {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("İlanlarım");
        progressDialog.setMessage("İlanlarınız Yükleniyor, Lütfen Bekleyin..");
        progressDialog.setCancelable(false);
        progressDialog.show();

        ılanlarimPojos = new ArrayList<>();
        Call<List<IlanlarimPojo>> request = ManagerAll.getGetInstanse().Ilanlarim(uyeid);
        request.enqueue(new Callback<List<IlanlarimPojo>>() {
            @Override
            public void onResponse(Call<List<IlanlarimPojo>> call, Response<List<IlanlarimPojo>> response) {
                if (response.isSuccessful()) {
                    if (response.body().get(0).isTf()) {
                        ılanlarimPojos = response.body();
                        ılanlarimAdapter = new IlanlarimAdapter(ılanlarimPojos, getApplicationContext(), Ilanlarim.this);
                        listView.setAdapter(ılanlarimAdapter);
                        Toast.makeText(Ilanlarim.this, response.body().size() + " İlanınız bulunmaktadır.", Toast.LENGTH_SHORT).show();
                        progressDialog.cancel();
                    } else {
                        Toast.makeText(Ilanlarim.this, "İlanınız bulunmamaktadır.", Toast.LENGTH_SHORT).show();
                        progressDialog.cancel();
                    }
                }
            }

            @Override
            public void onFailure(Call<List<IlanlarimPojo>> call, Throwable t) {
                Toast.makeText(Ilanlarim.this,"İşleminizi Şuanda Gerçekleştiremiyoruz.Lütfen İnternet Bağlantınızı Kontrol Ediniz.", Toast.LENGTH_SHORT).show();
                progressDialog.cancel();
            }
        });
    }

    public void ilanlarimAlertDialog(Activity activity, final int ilanid) {
        LayoutInflater inflater = activity.getLayoutInflater();
        View view = inflater.inflate(R.layout.alertlayout, null);

        Button button = view.findViewById(R.id.buton);
        Button buttonCik = view.findViewById(R.id.buton2);

        AlertDialog.Builder alert = new AlertDialog.Builder(activity);
        alert.setView(view);
        alert.setCancelable(false);
        final AlertDialog dialog = alert.create();

        buttonCik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sil(ilanid);
                dialog.cancel();
            }
        });
        dialog.show();
    }

    public void sil(final int ilanid) {
        Call<IlanlarimSilPojo> request = ManagerAll.getGetInstanse().IlanlarimSil(ilanid);
        request.enqueue(new Callback<IlanlarimSilPojo>() {
            @Override
            public void onResponse(Call<IlanlarimSilPojo> call, Response<IlanlarimSilPojo> response) {
                if (response.body().isTf()) {
                    ilanlarimiGoruntule();
                }
            }

            @Override
            public void onFailure(Call<IlanlarimSilPojo> call, Throwable t) {

            }
        });
    }

}
