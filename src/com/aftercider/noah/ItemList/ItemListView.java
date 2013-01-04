/*
 * Copyright (C) 2012 Aftercider
 *
 * All rights reserved by Aftercider
 * Contact: @aftercider (Twitter)
 *
 */

package com.aftercider.noah.ItemList;

import com.aftercider.noah.PlanningActivity;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ListView;

public class ItemListView extends ListView{
	public static final int BUTTON_COUNTUP   = 0;
	public static final int BUTTON_COUNTDOWN = 1;
	
	public ItemListView(Context context, AttributeSet attrs) {
		super(context, attrs);	
	}
	
	public void onClick(int position, int key){
		PlanningActivity activity = (PlanningActivity)getContext();
		switch(key){
			case BUTTON_COUNTUP:
				Log.i(getClass().getName(), "clickUP");
				activity.addItemCount(position, 1);
				break;
			case BUTTON_COUNTDOWN:
				Log.i(getClass().getName(), "clickDOWN");
				activity.addItemCount(position, -1);
				break;
			default:
				break;	
		}
	}
}
