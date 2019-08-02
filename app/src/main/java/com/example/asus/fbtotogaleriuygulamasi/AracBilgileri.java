package com.example.asus.fbtotogaleriuygulamasi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.asus.fbtotogaleriuygulamasi.Models.IlanVerPojo;

public class AracBilgileri extends AppCompatActivity {

    Button aracBilgisiButon, aracBilgisiButonGeri;
    EditText kmBilgiEditText, yilBilgiEditText, modelBilgiEditText, seriBilgiEditText, markaBilgiEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arac_bilgileri);
        tanimla();
    }

    public void tanimla() {
        kmBilgiEditText = (EditText) findViewById(R.id.kmBilgiEditText);
        yilBilgiEditText = (EditText) findViewById(R.id.yilBilgiEditText);
        modelBilgiEditText = (EditText) findViewById(R.id.modelBilgiEditText);
        seriBilgiEditText = (EditText) findViewById(R.id.seriBilgiEditText);
        markaBilgiEditText = (EditText) findViewById(R.id.markaBilgiEditText);

        kmBilgiEditText.setText(IlanVerPojo.getKm());
        yilBilgiEditText.setText(IlanVerPojo.getYil());
        modelBilgiEditText.setText(IlanVerPojo.getModel());
        seriBilgiEditText.setText(IlanVerPojo.getSeri());
        markaBilgiEditText.setText(IlanVerPojo.getMarka());

        aracBilgisiButon = (Button) findViewById(R.id.aracBilgisiButon);
        aracBilgisiButon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!kmBilgiEditText.getText().toString().equals("") &&
                        !yilBilgiEditText.getText().toString().equals("") &&
                        !modelBilgiEditText.getText().toString().equals("") &&
                        !seriBilgiEditText.getText().toString().equals("") &&
                        !markaBilgiEditText.getText().toString().equals("")) {

                    IlanVerPojo.setKm(kmBilgiEditText.getText().toString());
                    IlanVerPojo.setYil(yilBilgiEditText.getText().toString());
                    IlanVerPojo.setModel(modelBilgiEditText.getText().toString());
                    IlanVerPojo.setSeri(seriBilgiEditText.getText().toString());
                    IlanVerPojo.setMarka(markaBilgiEditText.getText().toString());

                    Intent ıntent = new Intent(AracBilgileri.this, MotorPerformans.class);
                    startActivity(ıntent);
                    overridePendingTransition(R.anim.anim_in, R.anim.anim_out);
                    finish();
                } else {
                    Toast.makeText(AracBilgileri.this, "Lütfen Tüm Alanları Giriniz.", Toast.LENGTH_SHORT).show();
                }

            }
        });

        //geri butonu çalışması
        aracBilgisiButonGeri = (Button) findViewById(R.id.aracBilgisiButonGeri);
        aracBilgisiButonGeri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent ıntent = new Intent(AracBilgileri.this, AdresBilgileri.class);
                startActivity(ıntent);
                overridePendingTransition(R.anim.anim_in_ters, R.anim.anim_out_ters);
                finish();
            }
        });

    }
}

