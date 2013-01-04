/*
 * Copyright (C) 2012 Aftercider
 *
 * All rights reserved by Aftercider
 * Contact: @aftercider (Twitter)
 *
 */

package com.aftercider.noah;

import java.util.ArrayList;

import com.aftercider.noah.ItemList.ItemListAdapter;
import com.aftercider.noah.ItemList.ItemListItem;
import com.aftercider.noah.ItemList.ItemListView;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class PlanningActivity extends Activity {

	Button mButtonSummary;	// SummaryActivityへのボタン
	ItemListView mItemListView;
	ArrayList<ItemListItem> mItemArrayList;
	ItemListAdapter mItemAdapter;
	
	PlanningItemManager mItemManager;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_planning);
        initializeAdapter();
        initializeButton();
        initializeListView();
        initializeItemManager();
    }
    
    // ボタンの初期化を行う
    private void initializeButton(){
    	mButtonSummary = (Button)findViewById(R.id.button_summary);
    	mButtonSummary.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClassName("com.aftercider.noah","com.aftercider.noah.SummaryActivity");
				startActivity(intent);
			}
		});
    }
    
    // リストビューの初期化を行う
    private void initializeListView(){
    	mItemListView = (ItemListView)findViewById(R.id.listview_planning);
    	mItemListView.setAdapter(mItemAdapter);
    }
    
    // ItemManagerの初期化
    private void initializeItemManager(){
    	mItemManager = new PlanningItemManager(this, mItemArrayList.size());
    	mItemManager.loadItemCount();
    }

    // Adapterを読み込む
    private void initializeAdapter(){
    	mItemArrayList = new ArrayList<ItemListItem>();
    	for(int i = 0; i < 30; i++){
    		ItemListItem item = new ItemListItem();
    		item.setCount(0);
    		item.setItemName("ほげ");
    		item.setPrice(4980);
    		mItemArrayList.add(item);
    	}
    	mItemAdapter = new ItemListAdapter(this, mItemArrayList);
    }
    
    // アイテムの個数を変更
    public void addItemCount(int position, int addValue){
    	// アイテムの個数を更新して保存
    	if( !mItemManager.addItemCount(position, addValue) ) return;
    	// Adapterを更新
    	ItemListItem item = mItemAdapter.getItem(position);
    	int count = mItemManager.getItemCount(position);
    	item.setCount(count);
    	mItemAdapter.notifyDataSetChanged();
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
    	getMenuInflater().inflate(R.menu.menu_planning, menu);
        return true;
    }
}
