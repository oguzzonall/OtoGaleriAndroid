package com.example.asus.fbtotogaleriuygulamasi.Models;

public class IlanlarimPojo{
	private String result;
	private String resim;
	private boolean tf;
	private String aciklama;
	private int uyeid;
	private int ilanid;
	private String fiyat;
	private String baslik;

	public void setResult(String result){
		this.result = result;
	}

	public String getResult(){
		return result;
	}

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

	public void setBaslik(String baslik){
		this.baslik = baslik;
	}

	public String getBaslik(){
		return baslik;
	}

	@Override
 	public String toString(){
		return 
			"IlanlarimPojo{" + 
			"result = '" + result + '\'' + 
			",resim = '" + resim + '\'' + 
			",tf = '" + tf + '\'' + 
			",aciklama = '" + aciklama + '\'' + 
			",uyeid = '" + uyeid + '\'' + 
			",ilanid = '" + ilanid + '\'' + 
			",fiyat = '" + fiyat + '\'' + 
			",baslik = '" + baslik + '\'' + 
			"}";
		}
}
