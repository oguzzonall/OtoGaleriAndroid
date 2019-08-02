package com.example.asus.fbtotogaleriuygulamasi;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.asus.fbtotogaleriuygulamasi.Adepters.SliderAdapter;
import com.example.asus.fbtotogaleriuygulamasi.Models.FavoriIslem;
import com.example.asus.fbtotogaleriuygulamasi.Models.FavoriKontrol;
import com.example.asus.fbtotogaleriuygulamasi.Models.IlanDetayPojo;
import com.example.asus.fbtotogaleriuygulamasi.Models.SliderPojo;
import com.example.asus.fbtotogaleriuygulamasi.RestApi.ManagerAll;

import java.util.List;

import me.relex.circleindicator.CircleIndicator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class IlanDetay extends AppCompatActivity {
    private TextView ilandetayBaslık, ilandetayFiyat, ilandetayMarka, ilandetayMotorTipi, ilandetayModel, ilandetaySeri, ilandetayYil, ilandetayIlanTipi, ilandetayKimden, ilandetayMotorHacmi, ilandetaySurat, ilandetayYakitTipi, ilandetayOrtalamaYakit, ilandetayDepoHacmi, ilandetayKm;
    private Button ilanMesajGonder, ilandetayFavoriyeAl;
    private ViewPager ilandetaySlider;
    int ilanId;
    List<SliderPojo> list;
    SliderAdapter sliderAdapter;
    CircleIndicator circleIndicator;
    SharedPreferences sharedPreferences;
    int uyeid, otherId;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ilan_detay);
        Bundle bundle = getIntent().getExtras();
        ilanId = bundle.getInt("ilanid");
        sharedPreferences = getApplicationContext().getSharedPreferences("giris", 0);
        uyeid = sharedPreferences.getInt("uye_id", 0);

        tanimla();
        getIlanDetay();
        getResim();
        getTextButton();
        action();
    }

    public void tanimla() {

        circleIndicator = (CircleIndicator) findViewById(R.id.sliderCircle);
        ilandetayMotorTipi = (TextView) findViewById(R.id.ilandetayMotorTipi);
        ilandetayBaslık = (TextView) findViewById(R.id.ilandetayBaslık);
        ilandetayFiyat = (TextView) findViewById(R.id.ilandetayFiyat);
        ilandetayMarka = (TextView) findViewById(R.id.ilandetayMarka);
        ilandetayModel = (TextView) findViewById(R.id.ilandetayModel);
        ilandetaySeri = (TextView) findViewById(R.id.ilandetaySeri);
        ilandetayYil = (TextView) findViewById(R.id.ilandetayYil);
        ilandetayIlanTipi = (TextView) findViewById(R.id.ilandetayIlanTipi);
        ilandetayKimden = (TextView) findViewById(R.id.ilandetayKimden);
        ilandetayMotorHacmi = (TextView) findViewById(R.id.ilandetayMotorHacmi);
        ilandetaySurat = (TextView) findViewById(R.id.ilandetaySurat);
        ilandetayYakitTipi = (TextView) findViewById(R.id.ilandetayYakitTipi);
        ilandetayOrtalamaYakit = (TextView) findViewById(R.id.ilandetayOrtalamaYakit);
        ilandetayDepoHacmi = (TextView) findViewById(R.id.ilandetayDepoHacmi);
        ilandetayKm = (TextView) findViewById(R.id.ilandetayKm);

        ilanMesajGonder = (Button) findViewById(R.id.ilanMesajGonder);

        ilandetayFavoriyeAl = (Button) findViewById(R.id.ilandetayFavoriyeAl);

        ilandetaySlider = (ViewPager) findViewById(R.id.ilandetaySlider);

    }

    public void getIlanDetay() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("İlanlar");
        progressDialog.setMessage("İlanlar Detayı Listeleniyor, Lütfen Bekleyin..");
        progressDialog.setCancelable(false);
        progressDialog.show();
        Call<IlanDetayPojo> request = ManagerAll.getGetInstanse().IlaninDetayi(ilanId);
        request.enqueue(new Callback<IlanDetayPojo>() {
            @Override
            public void onResponse(Call<IlanDetayPojo> call, Response<IlanDetayPojo> response) {
                ilandetayMotorTipi.setText(response.body().getMotortipi());
                ilandetayBaslık.setText(response.body().getBaslik());
                ilandetayFiyat.setText(response.body().getUcret());
                ilandetayMarka.setText(response.body().getMarka());
                ilandetayModel.setText(response.body().getModel());
                ilandetaySeri.setText(response.body().getSeri());
                ilandetayYil.setText(response.body().getYil());
                ilandetayIlanTipi.setText(response.body().getIlantipi());
                ilandetayKimden.setText(response.body().getKimden());
                ilandetayMotorHacmi.setText(response.body().getMotorhacmi());
                ilandetaySurat.setText(response.body().getSurat());
                ilandetayYakitTipi.setText(response.body().getYakittipi());
                ilandetayOrtalamaYakit.setText(response.body().getOrtalamayakit());
                ilandetayDepoHacmi.setText(response.body().getDepohacmi());
                ilandetayKm.setText(response.body().getKm());
                otherId = response.body().getUye_id();
                if(uyeid==otherId)
                {
                    ilanMesajGonder.setVisibility(View.GONE);
                    ilandetayFavoriyeAl.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Call<IlanDetayPojo> call, Throwable t) {
                Toast.makeText(IlanDetay.this, "İşleminiz Gerçekleştirilemedi.İnternet Bağlantınızı Kontrol Ediniz.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void getResim() {
        Call<List<SliderPojo>> request = ManagerAll.getGetInstanse().IlaninResimleri(ilanId);
        request.enqueue(new Callback<List<SliderPojo>>() {
            @Override
            public void onResponse(Call<List<SliderPojo>> call, Response<List<SliderPojo>> response) {
                list = response.body();
                sliderAdapter = new SliderAdapter(list, getApplicationContext());
                ilandetaySlider.setAdapter(sliderAdapter);
                circleIndicator.setViewPager(ilandetaySlider);
                circleIndicator.bringToFront();
            }

            @Override
            public void onFailure(Call<List<SliderPojo>> call, Throwable t) {

            }
        });
    }

    public void getTextButton() {
        Call<FavoriKontrol> request = ManagerAll.getGetInstanse().getButtonName(ilanId, uyeid);
        request.enqueue(new Callback<FavoriKontrol>() {
            @Override
            public void onResponse(Call<FavoriKontrol> call, Response<FavoriKontrol> response) {
                if (response.body().isTf()) {
                    ilandetayFavoriyeAl.setText(response.body().getDurum());
                    progressDialog.cancel();
                } else {
                    ilandetayFavoriyeAl.setText(response.body().getDurum());
                    progressDialog.cancel();
                }
            }

            @Override
            public void onFailure(Call<FavoriKontrol> call, Throwable t) {
                Toast.makeText(IlanDetay.this, "İşleminiz Gerçekleştirilemedi.İnternet Bağlantınızı Kontrol Ediniz.", Toast.LENGTH_SHORT).show();
                progressDialog.cancel();
            }
        });
    }

    public void action() {
        ilandetayFavoriyeAl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Call<FavoriIslem> request = ManagerAll.getGetInstanse().favoriAlCikart(ilanId, uyeid);
                request.enqueue(new Callback<FavoriIslem>() {
                    @Override
                    public void onResponse(Call<FavoriIslem> call, Response<FavoriIslem> response) {
                        if (response.body().isTf()) {
                            Toast.makeText(IlanDetay.this, response.body().getText(), Toast.LENGTH_SHORT).show();
                            getTextButton();
                        } else {
                            Toast.makeText(IlanDetay.this, response.body().getText(), Toast.LENGTH_SHORT).show();
                            getTextButton();
                        }
                    }

                    @Override
                    public void onFailure(Call<FavoriIslem> call, Throwable t) {
                        Toast.makeText(IlanDetay.this, "İşleminiz Gerçekleştirilemedi.İnternet Bağlantınızı Kontrol Ediniz.", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
        ilanMesajGonder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(IlanDetay.this, ChatActivity.class);
                OtherId.setOtherId(otherId);
                startActivity(intent);
            }
        });
    }
}
