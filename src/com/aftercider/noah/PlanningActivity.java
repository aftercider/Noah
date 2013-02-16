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
import android.view.Menu;
import android.view.MenuItem;
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
    		item.setResourceId(itemData.getResourceId());
    		
    		mItemArrayList.add(item);
    	}
    	mItemAdapter = new ItemListAdapter(this, mItemArrayList);
    }
    
    // 合計額を入れるTextViewを初期化
    private void initializeSumView(){
    	mTextViewSumPrice = (TextView)findViewById(R.id.textview_price);
    	
    	// 合計情報を更新する
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
    	mTextViewSumPrice.setText("計:"+calcSumPrice()+"円");
    	
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
    
    public void addAnimationBalloon(int x, int y){
    	ImageView imageView = (ImageView)findViewById(R.id.animation_countup);
    	// リソースからアニメーションを読み込み、ビューに設定
    	imageView.setVisibility(View.VISIBLE);
    	int[] pos = {0,0};
    	mItemListView.getLocationInWindow(pos);
    	y -= pos[1];
    	
        // ビューからアニメーションを取り出し
        AnimationDrawable anim = (AnimationDrawable)imageView.getDrawable();
 
        // アニメーション開始
        anim.setVisible(true, true);
        imageView.layout(x - imageView.getWidth()/2, y - imageView.getHeight(), x + imageView.getWidth()/2, y);
    }
    public void minusAnimationBalloon(int x, int y){
    	ImageView imageView = (ImageView)findViewById(R.id.animation_countdown);
    	// リソースからアニメーションを読み込み、ビューに設定
    	imageView.setVisibility(View.VISIBLE);
    	int[] pos = {0,0};
    	mItemListView.getLocationInWindow(pos);
    	y -= pos[1];
    	
        // ビューからアニメーションを取り出し
        AnimationDrawable anim = (AnimationDrawable)imageView.getDrawable();
 
        // アニメーション開始
        anim.setVisible(true, true);
        imageView.layout(x - imageView.getWidth()/2, y - imageView.getHeight(), x + imageView.getWidth()/2, y);
    }
 
    
    public boolean onOptionsItemSelected(MenuItem menu) {
    	// アイテムの個数を消去する
    	mItemCountManager.resetItemCount();
    	refreshItemList();	
    	
    	return super.onOptionsItemSelected(menu);
    }
    
    private void refreshItemList(){
    	// Adapterを更新
    	for (int i = 0; i < mItemDataManager.getSize(); i++) {
    		ItemListItem item = mItemAdapter.getItem(i);
        	int count = mItemCountManager.getItemCount(i);
        	item.setCount(count);
		}
    	
    	// 合計情報を更新する
    	mTextViewSumPrice.setText("計:"+calcSumPrice()+"円");
    	mItemAdapter.notifyDataSetChanged();
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
    	menu.add(0 , Menu.FIRST, Menu.NONE, "個数を初期化する");
        return true;
    }
}
