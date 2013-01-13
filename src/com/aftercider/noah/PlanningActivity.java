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
import com.aftercider.noah.manager.ItemCountManager;
import com.aftercider.noah.manager.ItemData;
import com.aftercider.noah.manager.ItemDataManager;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class PlanningActivity extends Activity {

	Button mButtonSummary;	// SummaryActivityへのボタン
	ItemListView mItemListView;
	TextView mTextViewSumPrice;
	
	ArrayList<ItemListItem> mItemArrayList;
	ItemListAdapter mItemAdapter;
	
	ItemCountManager mItemCountManager;
	ItemDataManager mItemDataManager;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_planning);
        initializeItemManager();
        initializeAdapter();
        initializeButton();
        initializeListView();
        initializeSumView();
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
    	mItemDataManager = new ItemDataManager(this);
    	mItemDataManager.loadData();
    	
    	mItemCountManager = new ItemCountManager(this, mItemDataManager.getSize());
    	mItemCountManager.loadItemCount();
    }

    // Adapterを読み込む
    private void initializeAdapter(){
    	mItemArrayList = new ArrayList<ItemListItem>();
    	for(int i = 0; i < mItemDataManager.getSize(); i++){
    		ItemListItem item = new ItemListItem();
    		ItemData itemData = mItemDataManager.getItem(i);
    		
    		item.setItemName(itemData.getItemName());
    		item.setCount(mItemCountManager.getItemCount(i));
    		item.setPrice(itemData.getPrice());
    		
    		mItemArrayList.add(item);
    	}
    	mItemAdapter = new ItemListAdapter(this, mItemArrayList);
    }
    
    // 合計額を入れるTextViewを初期化
    private void initializeSumView(){
    	mTextViewSumPrice = (TextView)findViewById(R.id.textview_price);
    	// 合計情報を更新する
    	// TODO 分離する
    	mTextViewSumPrice.setText("計:"+calcSumPrice()+"円");
    }
    
    // アイテムの個数を変更
    public boolean addItemCount(int position, int addValue){
    	// アイテムの個数を更新して保存
    	if( !mItemCountManager.addItemCount(position, addValue) ) return false;
    	// Adapterを更新
    	ItemListItem item = mItemAdapter.getItem(position);
    	int count = mItemCountManager.getItemCount(position);
    	item.setCount(count);
    	
    	// 合計情報を更新する
    	// TODO 分離する
    	mTextViewSumPrice.setText("計:"+calcSumPrice()+"円");
    	
    	// TODO 描画系は分離する
    	mItemAdapter.notifyDataSetChanged();	
    	return true;
    }
    
    // TODO Managerに分離する
    private int calcSumPrice(){
    	int price = 0;
    	for (int i = 0; i < mItemDataManager.getSize(); i++) {
			price += mItemDataManager.getItem(i).getPrice() * mItemCountManager.getItemCount(i);
		}
    	return price;
    }
    
    // TODO ちゃんと動かす
    public void addAnimationBaloon(int x, int y){
    	ImageView v = (ImageView)findViewById(R.id.animation_countup);
    	v.setVisibility(View.VISIBLE);
    	v.layout(x, y - v.getHeight(), x + v.getWidth(), y);
    	Log.i("posiitino", x+":"+(y - v.getHeight())+":"+(x + v.getWidth())+":"+y+":");
    	Log.i("left:top", v.getLeft() +":"+ v.getTop());
    	
    	// リソースからアニメーションを読み込み、ビューに設定
        v.setBackgroundResource( R.drawable.animation_baloon_up );
 
        // ビューからアニメーションを取り出し
        AnimationDrawable anim = (AnimationDrawable)v.getBackground();
 
        // アニメーション開始
        anim.setVisible(true, true);
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
    	getMenuInflater().inflate(R.menu.menu_planning, menu);
        return true;
    }
}
