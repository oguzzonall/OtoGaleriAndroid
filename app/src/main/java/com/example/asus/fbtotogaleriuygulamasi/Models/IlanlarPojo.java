package com.example.asus.fbtotogaleriuygulamasi.Models;

public class IlanlarPojo{
	private String resim;
	private boolean tf;
	private String aciklama;
	private String il;
	private String ilce;
	private int uyeid;
	private int ilanid;
	private String fiyat;
	private String mahalle;
	private String baslik;

	public void setResim(String resim){
		this.resim = resim;
	}

	public String getResim(){
		return resim;
	}

	public void setTf(boolean tf){
		this.tf = tf;
	}

	public boolean isTf(){
		return tf;
	}

	public void setAciklama(String aciklama){
		this.aciklama = aciklama;
	}

	public String getAciklama(){
		return aciklama;
	}

	public void setIl(String il){
		this.il = il;
	}

	public String getIl(){
		return il;
	}

	public void setIlce(String ilce){
		this.ilce = ilce;
	}

	public String getIlce(){
		return ilce;
	}

	public void setUyeid(int uyeid){
		this.uyeid = uyeid;
	}

	public int getUyeid(){
		return uyeid;
	}

	public void setIlanid(int ilanid){
		this.ilanid = ilanid;
	}

	public int getIlanid(){
		return ilanid;
	}

	public void setFiyat(String fiyat){
		this.fiyat = fiyat;
	}

	public String getFiyat(){
		return fiyat;
	}

	public void setMahalle(String mahalle){
		this.mahalle = mahalle;
	}

	public String getMahalle(){
		return mahalle;
	}

	public void setBaslik(String baslik){
		this.baslik = baslik;
	}

	public String getBaslik(){
		return baslik;
	}

	@Override
 	public String toString(){
		return 
			"IlanlarPojo{" + 
			"resim = '" + resim + '\'' + 
			",tf = '" + tf + '\'' + 
			",aciklama = '" + aciklama + '\'' + 
			",il = '" + il + '\'' + 
			",ilce = '" + ilce + '\'' + 
			",uyeid = '" + uyeid + '\'' + 
			",ilanid = '" + ilanid + '\'' + 
			",fiyat = '" + fiyat + '\'' + 
			",mahalle = '" + mahalle + '\'' + 
			",baslik = '" + baslik + '\'' + 
			"}";
		}
}
