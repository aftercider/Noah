/*
 * Copyright (C) 2012 Aftercider
 *
 * All rights reserved by Aftercider
 * Contact: @aftercider (Twitter)
 *
 */

package com.aftercider.noah.ItemList;

public class ItemListItem {
	private String itemName;
	private int price;
	private int count;
	
	public ItemListItem() {
		this.itemName = "UNION ☆ マグボトル A \n(BLUE 真空断熱二重構造\nワンタッチオープン直飲みタイプ)";
		this.price    = 3500;
		this.count    = 2;
	}
	
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getItemName(){
		return "UNION ☆ マグボトル A \n(BLUE 真空断熱二重構造\nワンタッチオープン直飲みタイプ)";
		// TODO なんかうまくいかないから文字列返させる。
	}
	
	public int getPrice(){
		return this.price;
	}
	
	public int getCount(){
		return this.count;
	}

}
