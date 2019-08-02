package com.example.asus.fbtotogaleriuygulamasi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.asus.fbtotogaleriuygulamasi.Models.IlanVerPojo;

public class MotorPerformans extends AppCompatActivity {


    EditText motorTipiBilgiEditText, motorHacmiEditText, azamiSüratEditText;
    Button motorBilgisiButon, motorBilgisiButonGeri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_motor_performans);
        tanimla();
    }

    public void tanimla() {
        motorTipiBilgiEditText = (EditText) findViewById(R.id.motorTipiBilgiEditText);
        motorHacmiEditText = (EditText) findViewById(R.id.motorHacmiEditText);
        azamiSüratEditText = (EditText) findViewById(R.id.azamiSüratEditText);

        motorTipiBilgiEditText.setText(IlanVerPojo.getMotortipi());
        motorHacmiEditText.setText(IlanVerPojo.getMotorhacmi());
        azamiSüratEditText.setText(IlanVerPojo.getSurat());

        motorBilgisiButon = (Button) findViewById(R.id.motorBilgisiButon);
        motorBilgisiButon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!motorTipiBilgiEditText.getText().toString().equals("") &&
                        !motorHacmiEditText.getText().toString().equals("") &&
                        !azamiSüratEditText.getText().toString().equals("")) {

                    IlanVerPojo.setMotortipi(motorTipiBilgiEditText.getText().toString());
                    IlanVerPojo.setMotorhacmi(motorHacmiEditText.getText().toString());
                    IlanVerPojo.setSurat(azamiSüratEditText.getText().toString());

                    Intent ıntent = new Intent(MotorPerformans.this, Yakit.class);
                    startActivity(ıntent);
                    overridePendingTransition(R.anim.anim_in, R.anim.anim_out);
                    finish();
                } else {
                    Toast.makeText(MotorPerformans.this, "Lütfen Tüm Alanları Doldurunuz.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        motorBilgisiButonGeri = (Button) findViewById(R.id.motorBilgisiButonGeri);
        motorBilgisiButonGeri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent ıntent = new Intent(MotorPerformans.this, AracBilgileri.class);
                startActivity(ıntent);
                overridePendingTransition(R.anim.anim_in_ters, R.anim.anim_out_ters);
                finish();
            }
        });


    }
}


//