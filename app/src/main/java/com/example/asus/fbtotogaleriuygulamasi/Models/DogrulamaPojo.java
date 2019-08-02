package com.example.asus.fbtotogaleriuygulamasi.Models;

public class DogrulamaPojo{
	private String result;
	private boolean tf;
	private Object kadi;
	private int id;

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

	public void setKadi(Object kadi){
		this.kadi = kadi;
	}

	public Object getKadi(){
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
			"DogrulamaPojo{" + 
			"result = '" + result + '\'' + 
			",tf = '" + tf + '\'' + 
			",kadi = '" + kadi + '\'' + 
			",id = '" + id + '\'' + 
			"}";
		}
}
