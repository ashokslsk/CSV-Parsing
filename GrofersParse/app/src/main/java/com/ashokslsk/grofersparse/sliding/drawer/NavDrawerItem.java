package com.ashokslsk.grofersparse.sliding.drawer;

public class NavDrawerItem {

	String ItemName;
	int imgResID;
	
	public NavDrawerItem(String itemName, int imgResID) {
		super();
		ItemName = itemName;
		this.imgResID = imgResID;
	}
	
	public String getItemName() {
		return ItemName;
	}
	public void setItemName(String itemName) {
		ItemName = itemName;
	}
	public int getImgResID() {
		return imgResID;
	}
	public void setImgResID(int imgResID) {
		this.imgResID = imgResID;
	}
	
	
	
}
