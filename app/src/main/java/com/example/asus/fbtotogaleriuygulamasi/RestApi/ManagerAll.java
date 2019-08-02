package com.example.asus.fbtotogaleriuygulamasi.RestApi;

import android.util.Log;

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

public class ManagerAll extends BaseManager {
    private static ManagerAll ourGetInstanse = new ManagerAll();

    public static synchronized ManagerAll getGetInstanse() {
        return ourGetInstanse;
    }

    public Call<LoginPojo> login(String username, String password) {
        Call<LoginPojo> x = getRestApiClient().control(username, password);
        return x;
    }

    public Call<RegisterPojo> register(String username, String password) {
        Call<RegisterPojo> x = getRestApiClient().kayitol(username, password);
        return x;
    }

    public Call<DogrulamaPojo> dogrula(String username, String code) {
        Call<DogrulamaPojo> x = getRestApiClient().dogrulama(username, code);
        return x;
    }

    public Call<IlanSonucPojo> ilanver(int uye_id, String sehir, String ilce, String mahalle,
                                       String marka, String seri, String model, String yil, String ilantipi,
                                       String kimden, String baslik, String aciklama, String motortipi,
                                       String motorhacmi, String surat, String yakittipi, String ortalamayakit, String depohacmi, String km, String ucret) {
        Call<IlanSonucPojo> x = getRestApiClient().ilanVer(uye_id, sehir, ilce, mahalle, marka, seri, model, yil, ilantipi, kimden, baslik, aciklama, motortipi, motorhacmi, surat, yakittipi, ortalamayakit, depohacmi, km, ucret);
        return x;
    }

    public Call<ResimEklePojo> resimEkle(int uye_id, int ilan_id, String image) {
        Call<ResimEklePojo> x = getRestApiClient().resimYukle(uye_id, ilan_id, image);
        return x;
    }

    public Call<List<IlanlarimPojo>> Ilanlarim(int uyeid) {
        Call<List<IlanlarimPojo>> x = getRestApiClient().ilanlarim(uyeid);
        return x;
    }

    public Call<IlanlarimSilPojo> IlanlarimSil(int ilanid) {
        Call<IlanlarimSilPojo> x = getRestApiClient().ilanSil(ilanid);
        return x;
    }

    public Call<List<IlanlarPojo>> IlanlariGetir() {
        Call<List<IlanlarPojo>> x = getRestApiClient().ilanlar();
        return x;
    }

    public Call<IlanDetayPojo> IlaninDetayi(int ilanid) {
        Call<IlanDetayPojo> x = getRestApiClient().ilanDetay(ilanid);
        return x;
    }

    public Call<List<SliderPojo>> IlaninResimleri(int ilanid) {
        Call<List<SliderPojo>> x = getRestApiClient().ilanresimleri(ilanid);
        return x;
    }

    public Call<FavoriKontrol> getButtonName(int ilanid, int uyeid) {
        Call<FavoriKontrol> x = getRestApiClient().getButtonText(ilanid, uyeid);
        return x;
    }

    public Call<FavoriIslem> favoriAlCikart(int ilanid, int uyeid) {
        Call<FavoriIslem> x = getRestApiClient().favoriIslem(ilanid, uyeid);
        return x;
    }

    public Call<List<FavoriSliderPojo>> getSliderFavori(int uyeid) {
        Call<List<FavoriSliderPojo>> x = getRestApiClient().setSlider(uyeid);
        return x;
    }

    public Call<List<IlanlarPojo>> FavoriIlanlarimiGetir(int uyeid) {
        Call<List<IlanlarPojo>> x = getRestApiClient().getFavoriIlanlarim(uyeid);
        return x;
    }

    public Call<UserPojo> getUserById(int uyeid) {
        Call<UserPojo> x = getRestApiClient().getUserNameById(uyeid);
        return x;
    }

}
