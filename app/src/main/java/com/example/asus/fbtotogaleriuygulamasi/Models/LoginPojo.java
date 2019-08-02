package com.example.asus.fbtotogaleriuygulamasi.Models;

public class LoginPojo{
	private String kadi;
	private int id;

	public void setKadi(String kadi){
		this.kadi = kadi;
	}

	public String getKadi(){
		return kadi;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	@Override
 	public String toString(){
		return 
			"LoginPojo{" + 
			"kadi = '" + kadi + '\'' + 
			",id = '" + id + '\'' + 
			"}";
		}
}
