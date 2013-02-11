/*
 * Copyright (C) 2012 Aftercider
 *
 * All rights reserved by Aftercider
 * Contact: @aftercider (Twitter)
 *
 */

package com.aftercider.noah.ItemList;

import java.util.List;

import jp.sharakova.android.urlimageview.UrlImageView;

import com.aftercider.noah.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ArrayAdapter;
import android.widget.TextView;

class ViewHolder{
	int position;
	UrlImageView imageView;
	TextView textView;
}

public class TweetListAdapter extends ArrayAdapter<TweetListItem> {

	private LayoutInflater mInflater;
	
	public TweetListAdapter(Context context, List<TweetListItem> items) {
		super(context, 0, items);
		mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}
	
	@Override
	public View getView(final int position, View convertView, final ViewGroup parent){
		ViewHolder holder;
		if(convertView == null){
			convertView            = mInflater.inflate(R.layout.listitem_tweet, parent, false);
			holder                 = new ViewHolder();
			holder.imageView = (UrlImageView) convertView.findViewById(R.id.list_twitter_icon);
			holder.textView  = (TextView)  convertView.findViewById(R.id.list_twitter_tweet);
			convertView.setTag(holder);
		}
		else{
			holder = (ViewHolder) convertView.getTag();
		}
		
		TweetListItem item = getItem(position);
		holder.position	= position;
		if(item.getImageUrl() != ""){	// URLが入力されていたら画像を更新する
			holder.imageView.setImageUrl(item.getImageUrl());
		}
		holder.textView.setText(item.getText());
		return convertView;
	}
}
