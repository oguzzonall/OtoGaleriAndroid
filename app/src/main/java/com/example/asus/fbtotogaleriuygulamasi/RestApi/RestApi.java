package com.example.asus.fbtotogaleriuygulamasi.RestApi;

import com.example.asus.fbtotogaleriuygulamasi.Models.DogrulamaPojo;
import com.example.asus.fbtotogaleriuygulamasi.Models.FavoriIslem;
import com.example.asus.fbtotogaleriuygulamasi.Models.FavoriKontrol;
import com.example.asus.fbtotogaleriuygulamasi.Models.FavoriSliderPojo;
import com.example.asus.fbtotogaleriuygulamasi.Models.IlanDetayPojo;
import com.example.asus.fbtotogaleriuygulamasi.Models.IlanSonucPojo;
import com.example.asus.fbtotogaleriuygulamasi.Models.IlanlarPojo;
import com.example.asus.fbtotogaleriuygulamasi.Models.IlanlarimPojo;
import com.example.asus.fbtotogaleriuygulamasi.Models.IlanlarimSilPojo;
import com.example.asus.fbtotogaleriuygulamasi.Models.LoginPojo;
import com.example.asus.fbtotogaleriuygulamasi.Models.RegisterPojo;
import com.example.asus.fbtotogaleriuygulamasi.Models.ResimEklePojo;
import com.example.asus.fbtotogaleriuygulamasi.Models.SliderPojo;
import com.example.asus.fbtotogaleriuygulamasi.Models.UserPojo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface RestApi {
    @FormUrlEncoded
    @POST("/api/login/GirisYap")
    Call<LoginPojo> control(@Field("kad") String ad, @Field("sifre") String sifre);

    @FormUrlEncoded
    @POST("/api/Kayit/KayitOl")
    Call<RegisterPojo> kayitol(@Field("kad") String ad, @Field("sifre") String sifre);

    @FormUrlEncoded
    @POST("/api/Mail/Dogrulama")
    Call<DogrulamaPojo> dogrulama(@Field("kadi") String ad, @Field("kod") String kod);

    @FormUrlEncoded
    @POST("/api/Ilan/IlanVer")
    Call<IlanSonucPojo> ilanVer(@Field("uye_id") int uye_id, @Field("sehir") String sehir
            , @Field("ilce") String ilce, @Field("mahalle") String mahalle
            , @Field("marka") String marka, @Field("seri") String seri
            , @Field("model") String model, @Field("yil") String yil
            , @Field("ilantipi") String ilantipi, @Field("kimden") String kimden
            , @Field("baslik") String baslik, @Field("aciklama") String aciklama
            , @Field("motortipi") String motortipi, @Field("motorhacmi") String motorhacmi
            , @Field("surat") String surat, @Field("yakittipi") String yakittipi
            , @Field("ortalamayakit") String ortalamayakit, @Field("depohacmi") String depohacmi
            , @Field("km") String km, @Field("ucret") String ucret);

    @FormUrlEncoded
    @POST("/api/Ilan/IlanResim")
    Call<ResimEklePojo> resimYukle(@Field("uyeid") int uyeid, @Field("ilanid") int ilanid, @Field("resim") String base64StringResim);


    @GET("/api/Ilan/Ilanlarim")
    Call<List<IlanlarimPojo>> ilanlarim(@Query("uyeid") int uyeid);

    @GET("/api/Ilan/IlanSil")
    Call<IlanlarimSilPojo> ilanSil(@Query("ilanid") int ilanid);

    @GET("/api/Ilan/Ilanlar")
    Call<List<IlanlarPojo>> ilanlar();

    @GET("/api/Ilan/IlanDetay")
    Call<IlanDetayPojo> ilanDetay(@Query("ilanid") int ilanid);

    @GET("/api/Ilan/IlanResimleri")
    Call<List<SliderPojo>> ilanresimleri(@Query("ilanid") int ilanid);

    @GET("/api/Ilan/FavoriIlanlarim")
    Call<FavoriKontrol> getButtonText(@Query("ilanid") int ilanid, @Query("uyeid") int uyeid);

    @GET("/api/Ilan/FavoriIslem")
    Call<FavoriIslem> favoriIslem(@Query("ilanid") int ilanid, @Query("uyeid") int uyeid);

    @GET("/api/Ilan/FavoriIlanSlider")
    Call<List<FavoriSliderPojo>> setSlider(@Query("uyeid") int uyeid);

    @GET("/api/Ilan/FavoriIlanlarimiGetir")
    Call<List<IlanlarPojo>> getFavoriIlanlarim(@Query("uyeid") int uyeid);

    @GET("/api/Uye/UyeBilgiGetir")
    Call<UserPojo> getUserNameById(@Query("uyeid")int uyeid);
}
