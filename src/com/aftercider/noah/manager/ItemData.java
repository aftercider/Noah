package com.aftercider.noah.manager;

public class ItemData {
	private String mItemName;
	private int    mPrice;
	private String mShortName;
	
	public ItemData() {
		mItemName = "はやてちゃん";
		mPrice    = 7777;
	}
	
	public void setItemName(String itemName) {
		mItemName = itemName;
	}

	public void setPrice(int price) {
		mPrice = price;
	}
	
	public void setShortName(String shortName){
		mShortName = shortName;
	}

	public String getItemName(){
		return mItemName;
	}
	
	public int getPrice(){
		return mPrice;
	}
	
	public String getShortName() {
		return mShortName;
	}
}
