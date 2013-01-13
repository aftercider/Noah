/*
 * Copyright (C) 2012 Aftercider
 *
 * All rights reserved by Aftercider
 * Contact: @aftercider (Twitter)
 *
 */

package com.aftercider.noah.ItemList;

import com.aftercider.noah.manager.ItemData;

public class ItemListItem extends ItemData {
	private int mCount;
	
	public ItemListItem() {
		super();
		mCount = 0;
	}
	public void setCount(int count) {
		mCount = count;
	}

	public int getCount(){
		return mCount;
	}

}
