package com.example.asus.fbtotogaleriuygulamasi.Models;

public class UserPojo{
	private String sifre;
	private String kadi;

	public void setSifre(String sifre){
		this.sifre = sifre;
	}

	public String getSifre(){
		return sifre;
	}

	public void setKadi(String kadi){
		this.kadi = kadi;
	}

	public String getKadi(){
		return kadi;
	}

	@Override
 	public String toString(){
		return 
			"UserPojo{" + 
			"sifre = '" + sifre + '\'' + 
			",kadi = '" + kadi + '\'' + 
			"}";
		}
}
