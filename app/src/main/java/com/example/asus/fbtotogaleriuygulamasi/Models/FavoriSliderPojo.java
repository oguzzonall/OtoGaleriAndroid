package com.example.asus.fbtotogaleriuygulamasi.Models;

public class FavoriSliderPojo{
	private String resimyolu;
	private boolean tf;
	private int ilanid;

	public void setResimyolu(String resimyolu){
		this.resimyolu = resimyolu;
	}

	public String getResimyolu(){
		return resimyolu;
	}

	public void setTf(boolean tf){
		this.tf = tf;
	}

	public boolean isTf(){
		return tf;
	}

	public void setIlanid(int ilanid){
		this.ilanid = ilanid;
	}

	public int getIlanid(){
		return ilanid;
	}

	@Override
 	public String toString(){
		return 
			"FavoriSliderPojo{" + 
			"resimyolu = '" + resimyolu + '\'' + 
			",tf = '" + tf + '\'' + 
			",ilanid = '" + ilanid + '\'' + 
			"}";
		}
}
