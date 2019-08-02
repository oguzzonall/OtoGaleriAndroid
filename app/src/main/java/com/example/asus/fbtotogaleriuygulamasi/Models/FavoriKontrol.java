package com.example.asus.fbtotogaleriuygulamasi.Models;

public class FavoriKontrol{
	private String durum;
	private boolean tf;

	public void setDurum(String durum){
		this.durum = durum;
	}

	public String getDurum(){
		return durum;
	}

	public void setTf(boolean tf){
		this.tf = tf;
	}

	public boolean isTf(){
		return tf;
	}

	@Override
 	public String toString(){
		return 
			"FavoriKontrol{" + 
			"durum = '" + durum + '\'' + 
			",tf = '" + tf + '\'' + 
			"}";
		}
}
