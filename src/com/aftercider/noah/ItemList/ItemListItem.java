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
		itemName = "";
		price    = 0;
		count    = 0;
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
		return this.itemName;
	}
	
	public int getPrice(){
		return this.price;
	}
	
	public int getCount(){
		return this.count;
	}

}
