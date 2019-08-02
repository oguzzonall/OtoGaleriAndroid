package com.example.asus.fbtotogaleriuygulamasi.Models;

public class RegisterPojo{
	private String result;
	private boolean tf;
	private String dogrulamaKodu;

	public void setResult(String result){
		this.result = result;
	}

	public String getResult(){
		return result;
	}

	public void setTf(boolean tf){
		this.tf = tf;
	}

	public boolean isTf(){
		return tf;
	}

	public void setDogrulamaKodu(String dogrulamaKodu){
		this.dogrulamaKodu = dogrulamaKodu;
	}

	public String getDogrulamaKodu(){
		return dogrulamaKodu;
	}

	@Override
 	public String toString(){
		return 
			"RegisterPojo{" + 
			"result = '" + result + '\'' + 
			",tf = '" + tf + '\'' + 
			",dogrulamaKodu = '" + dogrulamaKodu + '\'' + 
			"}";
		}
}
