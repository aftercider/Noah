/*
 * Copyright (C) 2012 Aftercider
 *
 * All rights reserved by Aftercider
 * Contact: @aftercider (Twitter)
 *
 */

package com.aftercider.noah.ItemList;

import com.aftercider.noah.R;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.Toast;

public class ItemListView extends ListView{

	private boolean mScrolling = false;

	public ItemListView(Context context, AttributeSet attrs) {
		super(context, attrs);
		
		// アイテムタップ時の処理
		this.setOnItemClickListener(new OnItemClickListener() {
    		@Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
    			if(mScrolling){
    				return;
    			}
    			Toast.makeText(view.getContext(), (position + 1) + "番目がチェックされました。", Toast.LENGTH_SHORT).show();

    			// TODO Adapterに持って行った方がいい？
    			// 個数変更ボタンを表示する
    			FrameLayout frame = (FrameLayout)view.findViewById(R.id.frame1);
    			frame.setVisibility(View.VISIBLE);
            }
    	});
		// スクロール時にスナッピング
		this.setOnScrollListener(new OnScrollListener() {
			@Override
			public void onScrollStateChanged(AbsListView view, int scrollState) {
				switch (scrollState) {
				case OnScrollListener.SCROLL_STATE_IDLE:
					if (mScrolling) {
						// 一番上のアイテムが画面上に揃うようにする。
						// TODO いらないかも
						FrameLayout item = (FrameLayout) view.getChildAt(0);
						int top = Math.abs(item.getTop()); // top is a negative value
						int botton = Math.abs(item.getBottom());
						if (top >= botton) {
							((ListView) view).setSelectionFromTop(
									view.getFirstVisiblePosition() + 1, 0);
						} else {
							((ListView) view).setSelectionFromTop(
									view.getFirstVisiblePosition(), 0);
						}
					}
					mScrolling = false;
					break;
				case OnScrollListener.SCROLL_STATE_TOUCH_SCROLL:
				case OnScrollListener.SCROLL_STATE_FLING:
					// スクロールが始まったらListItemを更新してボタンを隠す。
					invalidateViews();
					mScrolling = true;
					break;
				}
			}
			
			@Override
			public void onScroll(AbsListView view, int firstVisibleItem,
					int visibleItemCount, int totalItemCount) {
				// TODO Auto-generated method stub
			}
		});
	}	
}
