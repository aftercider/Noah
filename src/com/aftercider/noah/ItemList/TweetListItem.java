/*
 * Copyright (C) 2012 Aftercider
 *
 * All rights reserved by Aftercider
 * Contact: @aftercider (Twitter)
 *
 */

package com.aftercider.noah.ItemList;

import com.aftercider.noah.manager.ItemData;

public class TweetListItem extends ItemData {
	private String mImageUrl = "";
	private String mText = "";
	
	public TweetListItem() {
		super();
	}

	public String getImageUrl() {
		return mImageUrl;
	}

	public void setImageUrl(String string) {
		this.mImageUrl = string;
	}

	public String getText() {
		return mText;
	}

	public void setText(String mText) {
		this.mText = mText;
	}
}
