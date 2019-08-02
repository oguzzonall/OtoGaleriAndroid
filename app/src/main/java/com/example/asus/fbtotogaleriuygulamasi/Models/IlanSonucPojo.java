package com.example.asus.fbtotogaleriuygulamasi.Models;

public class IlanSonucPojo{
	private boolean tf;
	private int uyeid;
	private int ilanid;

	public void setTf(boolean tf){
		this.tf = tf;
	}

	public boolean isTf(){
		return tf;
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

	@Override
 	public String toString(){
		return 
			"IlanSonucPojo{" + 
			"tf = '" + tf + '\'' + 
			",uyeid = '" + uyeid + '\'' + 
			",ilanid = '" + ilanid + '\'' + 
			"}";
		}
}
