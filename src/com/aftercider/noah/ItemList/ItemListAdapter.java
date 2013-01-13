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
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

class ViewHolder{
	int position;
	TextView textName;
	TextView textCount;
	TextView textPrice;
	ImageView imageView;
	ImageButton buttonCountUp;
	ImageButton buttonCountDown;
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
			holder.textName        = (TextView)   convertView.findViewById(R.id.list_planning_name);
			holder.textCount       = (TextView)   convertView.findViewById(R.id.list_planning_count);
			holder.textPrice       = (TextView)   convertView.findViewById(R.id.list_planning_price);
			holder.buttonCountUp   = (ImageButton)convertView.findViewById(R.id.list_planning_button_countup);
			holder.buttonCountDown = (ImageButton)convertView.findViewById(R.id.list_planning_button_countdown);
			convertView.setTag(holder);
		}
		else{
			holder = (ViewHolder) convertView.getTag();
		}
		
		ItemListItem item = getItem(position);
		holder.position	= position;
		holder.textName.setText(item.getItemName());	// 商品名を更新
		holder.textCount.setText(item.getCount() + "個");		// 個数を更新
		holder.textPrice.setText(item.getPrice() + "円");		// 価格を更新
		
		// 個数変更ボタンの反応
		holder.buttonCountUp.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// 個数を変えて記録する
				Log.i("up button","pushed");
				((ItemListView)parent).onClick(position, ItemListView.BUTTON_COUNTUP);
			}
		});
		
		holder.buttonCountDown.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// 個数を変えて記録する
				Log.i("down button","pushed");
				((ItemListView)parent).onClick(position, ItemListView.BUTTON_COUNTDOWN);
			}
		});


		return convertView;
	}
}
