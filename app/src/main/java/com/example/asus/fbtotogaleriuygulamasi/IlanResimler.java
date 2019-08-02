package com.example.asus.fbtotogaleriuygulamasi;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.asus.fbtotogaleriuygulamasi.Models.ResimEklePojo;
import com.example.asus.fbtotogaleriuygulamasi.RestApi.ManagerAll;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class IlanResimler extends AppCompatActivity {
    Button resimSecButon, resimEkleButon, cikButon;
    ImageView secilenIlanResmiImageView;
    Bitmap bitmap;
    int uye_id,ilan_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ilan_resimler);
        tanimla();
        Bundle bundle=getIntent().getExtras();
        uye_id=bundle.getInt("uye_id");
        ilan_id=bundle.getInt("ilan_id");
    }

    public void tanimla() {
        resimSecButon = findViewById(R.id.resimSecButon);
        resimEkleButon = findViewById(R.id.resimEkleButon);
        cikButon = findViewById(R.id.cikButon);
        secilenIlanResmiImageView = findViewById(R.id.secilenIlanResmiImageView);
        resimSecButon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ResimGoster();
            }
        });

        resimEkleButon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                yükle();
            }
        });
    }

    public void ResimGoster() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, 777);
    }

    public void yükle()
    {
        String image=imageToString();
        Call<ResimEklePojo> request=ManagerAll.getGetInstanse().resimEkle(uye_id,ilan_id,image);
        request.enqueue(new Callback<ResimEklePojo>() {
            @Override
            public void onResponse(Call<ResimEklePojo> call, Response<ResimEklePojo> response) {
                if(response.body().isTf())
                {
                    Toast.makeText(IlanResimler.this,response.body().getSonuc(), Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(IlanResimler.this,response.body().getSonuc(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResimEklePojo> call, Throwable t) {

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 777 && resultCode == RESULT_OK && data != null) {
            Uri path = data.getData();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), path);
                secilenIlanResmiImageView.setImageBitmap(bitmap);
                secilenIlanResmiImageView.setVisibility(View.VISIBLE);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public String imageToString() {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        byte[] byt = byteArrayOutputStream.toByteArray();
        String imagetoString = Base64.encodeToString(byt, Base64.DEFAULT);
        return imagetoString;
    }
}
