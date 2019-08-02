package com.example.asus.fbtotogaleriuygulamasi;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.asus.fbtotogaleriuygulamasi.Adepters.FavoriSliderAdapter;
import com.example.asus.fbtotogaleriuygulamasi.Models.FavoriSliderPojo;
import com.example.asus.fbtotogaleriuygulamasi.R;
import com.example.asus.fbtotogaleriuygulamasi.RestApi.ManagerAll;

import java.util.List;

import me.relex.circleindicator.CircleIndicator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    SharedPreferences sharedPreferences;
    String navHeaderText;
    TextView navHeaderTextView;
    SharedPreferences.Editor editor;
    Button ilanVerButon, ilanlarimMenuButton, ilanlarbutton,favoriilanbutton,kisimesajlari;
    ViewPager mainActivitySliderFavori;
    CircleIndicator mainActivitysliderCircle;
    int uyeid;
    FavoriSliderAdapter favoriSliderAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        sharedPreferences = getApplicationContext().getSharedPreferences("giris", 0);
        navHeaderText = sharedPreferences.getString("uye_KullaniciAdi", null);
        uyeid = sharedPreferences.getInt("uye_id", 0);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        View headerView = navigationView.getHeaderView(0);
        navHeaderTextView = headerView.findViewById(R.id.textView);
        navHeaderTextView.setText(navHeaderText);
        tanimla();
        getFavoriSlider();
    }

    public void tanimla() {

        mainActivitySliderFavori = findViewById(R.id.mainActivitySliderFavori);
        mainActivitysliderCircle = findViewById(R.id.mainActivitysliderCircle);

        favoriilanbutton=findViewById(R.id.favoriilanbutton);
        favoriilanbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ıntent = new Intent(MainActivity.this, FavoriIlanlarim.class);
                startActivity(ıntent);
                overridePendingTransition(R.anim.anim_in, R.anim.anim_out);
            }
        });

        ilanVerButon = (Button) findViewById(R.id.ilanVerButon);
        ilanVerButon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ıntent = new Intent(MainActivity.this, IlanBilgileri.class);
                startActivity(ıntent);
                overridePendingTransition(R.anim.anim_in, R.anim.anim_out);
            }
        });

        ilanlarimMenuButton = findViewById(R.id.ilanlarimMenuButton);
        ilanlarimMenuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ıntent = new Intent(MainActivity.this, Ilanlarim.class);
                startActivity(ıntent);
                overridePendingTransition(R.anim.anim_in, R.anim.anim_out);
            }
        });

        ilanlarbutton = findViewById(R.id.ilanlarbutton);
        ilanlarbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ıntent = new Intent(MainActivity.this, Ilanlar.class);
                startActivity(ıntent);
                overridePendingTransition(R.anim.anim_in, R.anim.anim_out);
            }
        });

        kisimesajlari=findViewById(R.id.kisimesajlari);
        kisimesajlari.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ıntent = new Intent(MainActivity.this, MesajlarActivity.class);
                startActivity(ıntent);
                overridePendingTransition(R.anim.anim_in, R.anim.anim_out);
            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.ilanlarid) {
            Intent intent = new Intent(MainActivity.this, Ilanlar.class);
            startActivity(intent);
        } else if (id == R.id.ilanverid) {
            Intent intent = new Intent(MainActivity.this, IlanBilgileri.class);
            startActivity(intent);
        } else if (id == R.id.favoriilanid) {

        } else if (id == R.id.iletisimbilgileriid) {

        } else if (id == R.id.mesajlarimid) {

        } else if (id == R.id.ilanlarimid) {
            Intent intent = new Intent(MainActivity.this, Ilanlarim.class);
            startActivity(intent);
        } else if (id == R.id.cikisYap) {
            editor = sharedPreferences.edit();
            editor.clear();
            editor.commit();
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void getFavoriSlider() {
        Call<List<FavoriSliderPojo>> request = ManagerAll.getGetInstanse().getSliderFavori(uyeid);
        request.enqueue(new Callback<List<FavoriSliderPojo>>() {
            @Override
            public void onResponse(Call<List<FavoriSliderPojo>> call, Response<List<FavoriSliderPojo>> response) {
                if (response.body().get(0).isTf()) {
                    if (response.body().size() > 0) {
                        favoriSliderAdapter = new FavoriSliderAdapter(response.body(), MainActivity.this, MainActivity.this);
                        mainActivitySliderFavori.setAdapter(favoriSliderAdapter);
                        mainActivitysliderCircle.setViewPager(mainActivitySliderFavori);
                        mainActivitysliderCircle.bringToFront();
                    }
                }
                else {
                    favoriSliderAdapter = new FavoriSliderAdapter(response.body(), MainActivity.this, MainActivity.this);
                    mainActivitySliderFavori.setAdapter(favoriSliderAdapter);
                    mainActivitysliderCircle.setViewPager(mainActivitySliderFavori);
                    mainActivitysliderCircle.bringToFront();
                }
            }

            @Override
            public void onFailure(Call<List<FavoriSliderPojo>> call, Throwable t) {

            }
        });
    }
    @Override
    public void onRestart()
    {
        super.onRestart();
        getFavoriSlider();
    }
}
