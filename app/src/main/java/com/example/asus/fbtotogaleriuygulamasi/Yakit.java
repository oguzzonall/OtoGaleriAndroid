package com.example.asus.fbtotogaleriuygulamasi;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.asus.fbtotogaleriuygulamasi.Models.IlanSonucPojo;
import com.example.asus.fbtotogaleriuygulamasi.Models.IlanVerPojo;
import com.example.asus.fbtotogaleriuygulamasi.RestApi.ManagerAll;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Yakit extends AppCompatActivity {

    EditText yakitTipiBilgiEditText, ortalamaYakitEditText, depoHacmiEditText;
    Button yakitTüketimBilgisiButon, yakitTüketimBilgisiButonGeri;
    SharedPreferences sharedPreferences;
    private View mProgressView;
    private View myakitFormView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yakit);
        tanimla();
    }

    public void tanimla() {

        mProgressView = findViewById(R.id.progressYakit);
        myakitFormView = findViewById(R.id.yakit_view);
        sharedPreferences = getApplicationContext().getSharedPreferences("giris", 0);
        IlanVerPojo.setUye_id(sharedPreferences.getInt("uye_id", 0));

        yakitTipiBilgiEditText = (EditText) findViewById(R.id.yakitTipiBilgiEditText);
        ortalamaYakitEditText = (EditText) findViewById(R.id.ortalamaYakitEditText);
        depoHacmiEditText = (EditText) findViewById(R.id.depoHacmiEditText);

        yakitTipiBilgiEditText.setText(IlanVerPojo.getYakittipi());
        ortalamaYakitEditText.setText(IlanVerPojo.getOrtalamayakit());
        depoHacmiEditText.setText(IlanVerPojo.getDepohacmi());

        yakitTüketimBilgisiButon = (Button) findViewById(R.id.yakitTüketimBilgisiButon);
        yakitTüketimBilgisiButon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showProgress(true);
                if (!yakitTipiBilgiEditText.getText().toString().equals("") &&
                        !ortalamaYakitEditText.getText().toString().equals("") &&
                        !depoHacmiEditText.getText().toString().equals("")) {
                    IlanVerPojo.setYakittipi(yakitTipiBilgiEditText.getText().toString());
                    IlanVerPojo.setOrtalamayakit(ortalamaYakitEditText.getText().toString());
                    IlanVerPojo.setDepohacmi(depoHacmiEditText.getText().toString());

                    ilaniYayinla(IlanVerPojo.getUye_id(), IlanVerPojo.getSehir(), IlanVerPojo.getIlce(), IlanVerPojo.getMahalle(), IlanVerPojo.getMarka(), IlanVerPojo.getSeri(), IlanVerPojo.getModel(), IlanVerPojo.getYil(), IlanVerPojo.getIlantipi(), IlanVerPojo.getKimden(), IlanVerPojo.getBaslik(), IlanVerPojo.getAciklama(), IlanVerPojo.getMotortipi(), IlanVerPojo.getMotorhacmi(), IlanVerPojo.getSurat(), IlanVerPojo.getYakittipi(), IlanVerPojo.getOrtalamayakit(), IlanVerPojo.getDepohacmi(), IlanVerPojo.getKm(), IlanVerPojo.getUcret());

                } else {
                    Toast.makeText(Yakit.this, "Lütfen Tüm Alanları Giriniz.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        yakitTüketimBilgisiButonGeri = (Button) findViewById(R.id.yakitTüketimBilgisiButonGeri);
        yakitTüketimBilgisiButonGeri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent ıntent = new Intent(Yakit.this, MotorPerformans.class);
                startActivity(ıntent);
                overridePendingTransition(R.anim.anim_in_ters, R.anim.anim_out_ters);
                finish();
            }
        });
    }

    public void ilaniYayinla(int uye_id, String sehir, String ilce, String mahalle,
                             String marka, String seri, String model, String yil, String ilantipi,
                             String kimden, String baslik, String aciklama, String motortipi,
                             String motorhacmi, String surat, String yakittipi, String ortalamayakit, String depohacmi, String km, String ucret) {
        Call<IlanSonucPojo> request = ManagerAll.getGetInstanse().ilanver(uye_id, sehir, ilce, mahalle, marka, seri, model, yil, ilantipi, kimden, baslik, aciklama, motortipi, motorhacmi, surat, yakittipi, ortalamayakit, depohacmi, km, ucret);
        request.enqueue(new Callback<IlanSonucPojo>() {
            @Override
            public void onResponse(Call<IlanSonucPojo> call, Response<IlanSonucPojo> response) {
                if (response.body().isTf()) {
                    Toast.makeText(Yakit.this, "İlanınız yayınlanmıştır.", Toast.LENGTH_SHORT).show();
                    Intent ıntent = new Intent(Yakit.this, IlanResimler.class);
                    ıntent.putExtra("ilan_id", response.body().getIlanid());
                    ıntent.putExtra("uye_id", response.body().getUyeid());
                    startActivity(ıntent);
                    overridePendingTransition(R.anim.anim_in, R.anim.anim_out);
                    finish();
                } else {
                    Toast.makeText(Yakit.this, "İlanınız Yayınlanmamıştır.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<IlanSonucPojo> call, Throwable t) {

            }
        });
    }

    private void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            myakitFormView.setVisibility(show ? View.GONE : View.VISIBLE);
            myakitFormView.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    myakitFormView.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });

            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mProgressView.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            myakitFormView.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }
}

