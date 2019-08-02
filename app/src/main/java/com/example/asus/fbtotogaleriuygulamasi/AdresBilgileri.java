package com.example.asus.fbtotogaleriuygulamasi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.asus.fbtotogaleriuygulamasi.Models.IlanVerPojo;
import com.example.asus.fbtotogaleriuygulamasi.R;

public class AdresBilgileri extends AppCompatActivity {

    Button adresBilgisiButon,aracBilgisiButonGeri;
    EditText sehirBilgiEditText,ilceBilgiEditText,mahalleBilgiEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adres_bilgileri);
        tanimla();
    }
    public void tanimla()
    {
        sehirBilgiEditText=(EditText)findViewById(R.id.sehirBilgiEditText);
        ilceBilgiEditText=(EditText)findViewById(R.id.ilceBilgiEditText);
        mahalleBilgiEditText=(EditText)findViewById(R.id.mahalleBilgiEditText);

        sehirBilgiEditText.setText(IlanVerPojo.getSehir());
        ilceBilgiEditText.setText(IlanVerPojo.getIlce());
        mahalleBilgiEditText.setText(IlanVerPojo.getMahalle());

        adresBilgisiButon=(Button)findViewById(R.id.adresBilgisiButon);
        adresBilgisiButon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!sehirBilgiEditText.getText().toString().equals("")&&
                        !ilceBilgiEditText.getText().toString().equals("")&&
                        !mahalleBilgiEditText.getText().toString().equals(""))
                {
                    IlanVerPojo.setSehir(sehirBilgiEditText.getText().toString());
                    IlanVerPojo.setIlce(ilceBilgiEditText.getText().toString());
                    IlanVerPojo.setMahalle(mahalleBilgiEditText.getText().toString());

                    Intent ıntent=new Intent(AdresBilgileri.this,AracBilgileri.class);
                    startActivity(ıntent);
                    overridePendingTransition(R.anim.anim_in,R.anim.anim_out);
                    finish();
                }else{
                    Toast.makeText(AdresBilgileri.this, "Lütfen Tüm Alanları Doldurunuz.", Toast.LENGTH_SHORT).show();
                }
            }
        });
        aracBilgisiButonGeri=(Button)findViewById(R.id.aracBilgisiButonGeri);
        aracBilgisiButonGeri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent ıntent=new Intent(AdresBilgileri.this,IlanTuru.class);
                startActivity(ıntent);
                overridePendingTransition(R.anim.anim_in_ters,R.anim.anim_out_ters);
                finish();
            }
        });


    }
}


