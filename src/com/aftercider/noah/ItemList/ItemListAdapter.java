/*
 * Copyright (C) 2012 Aftercider
 *
 * All rights reserved by Aftercider
 * Contact: @aftercider (Twitter)
 *
 */

package com.aftercider.noah.ItemList;

import java.util.List;

import com.aftercider.noah.R;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

class ViewHolder{
	TextView textView;
	ImageView imageView;
	Button buttonCountUp;
	Button buttonCountDown;
	FrameLayout frame;
}

public class ItemListAdapter extends ArrayAdapter<ItemListItem> {

	private LayoutInflater mInflater;
	
	public ItemListAdapter(Context context, List<ItemListItem> items) {
		super(context, 0, items);
		mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}
	
	@Override
	public View getView(final int position, View convertView, final ViewGroup parent){
		ViewHolder holder;
		if(convertView == null){
			convertView            = mInflater.inflate(R.layout.listitem_planning, parent, false);
			holder                 = new ViewHolder();
			holder.textView        = (TextView) convertView.findViewById(R.id.list_planning_name);
			holder.buttonCountUp   = (Button)convertView.findViewById(R.id.list_planning_button_countup);
			holder.buttonCountDown = (Button)convertView.findViewById(R.id.list_planning_button_countdown);
			holder.frame           = (FrameLayout)convertView.findViewById(R.id.frame1);
			convertView.setTag(holder);
		}
		else{
			holder = (ViewHolder) convertView.getTag();
		}
		
		ItemListItem item = getItem(position);
		holder.textView.setText(item.getItemName());	// 商品名を更新
		holder.frame.setVisibility(View.INVISIBLE);		// 個数変更ボタンを隠す
		
		// 個数変更ボタンの反応
		holder.buttonCountUp.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO 個数を変える
			}
		});

		return convertView;
	}
}
