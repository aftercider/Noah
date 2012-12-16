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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

class ViewHolder{
	TextView textView;
	ImageView imageView;
}

public class ItemListAdapter extends ArrayAdapter<ItemListItem> {

	private LayoutInflater inflater;
	
	public ItemListAdapter(Context context, List<ItemListItem> items) {
		super(context, 0, items);
		this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent){
		ViewHolder holder;
		if(convertView == null){
			convertView = inflater.inflate(R.layout.listitem_planning, parent, false);
			holder = new ViewHolder();
			holder.textView = (TextView) convertView.findViewById(R.id.list_planning_name);
			convertView.setTag(holder);
		}
		else{
			holder = (ViewHolder) convertView.getTag();
		}
		
		ItemListItem item = getItem(position);
		holder.textView.setText(item.getItemName());
		
		return convertView;
	}
}
