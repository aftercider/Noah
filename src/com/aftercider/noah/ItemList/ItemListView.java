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
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.TextView;

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
				if(activity.addItemCount(position, 1)){
					int[] textPosition= {0,0};
					// ListItemのViewを取得
					FrameLayout item = getViewAtPosition(position);
					// 見つからなかったときは何もしない
					if(item==null) return;
					// 個数のTextViewを取得
					TextView textCount = ((ViewHolder)item.getTag()).textCount;
					// TextViewの位置を取得
					textCount.getLocationInWindow(textPosition); 
					activity.addAnimationBaloon(textPosition[0], textPosition[1]);
				}
				break;
			case BUTTON_COUNTDOWN:
				Log.i(getClass().getName(), "clickDOWN");
				if(activity.addItemCount(position, -1)){
					int[] textPosition= {0,0};
					// ListItemのViewを取得
					FrameLayout item = getViewAtPosition(position);
					// 見つからなかったときは何もしない
					if(item==null) return;
					// 個数のTextViewを取得
					TextView textCount = ((ViewHolder)item.getTag()).textCount;
					// TextViewの位置を取得
					textCount.getLocationInWindow(textPosition); 
					activity.addAnimationBaloon(textPosition[0], textPosition[1]);
				}
				break;
			default:
				break;	
		}
	}
	
	private FrameLayout getViewAtPosition(int position){
		FrameLayout frame;
		for (int i = 0; i < getChildCount(); i++) {
			frame = (FrameLayout)getChildAt(i);
			if( position == ((ViewHolder)frame.getTag()).position) return frame;
		}
		return null;
	}
}
