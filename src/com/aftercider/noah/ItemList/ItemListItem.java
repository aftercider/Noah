/*
 * Copyright (C) 2012 Aftercider
 *
 * All rights reserved by Aftercider
 * Contact: @aftercider (Twitter)
 *
 */

package com.aftercider.noah.ItemList;

public class ItemListItem {
	private String mItemName;
	private int    mPrice;
	private int    mCount;
	
	public ItemListItem() {
		mItemName = "UNION ☆ マグボトル A \n(BLUE 真空断熱二重構造\nワンタッチオープンタイプ)";
		mPrice    = 3500;
		mCount    = 2;
	}
	
	public void setItemName(String itemName) {
		mItemName = itemName;
	}

	public void setPrice(int price) {
		mPrice = price;
	}

	public void setCount(int count) {
		mCount = count;
	}

	public String getItemName(){
		return mItemName;
		// TODO なんかうまくいかないから文字列返させる。
	}
	
	public int getPrice(){
		return mPrice;
	}
	
	public int getCount(){
		return mCount;
	}

}
