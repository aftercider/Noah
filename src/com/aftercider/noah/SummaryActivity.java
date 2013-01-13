/*
 * Copyright (C) 2012 Aftercider
 *
 * All rights reserved by Aftercider
 * Contact: @aftercider (Twitter)
 *
 */

package com.aftercider.noah;

import com.aftercider.noah.manager.ItemCountManager;
import com.aftercider.noah.manager.ItemData;
import com.aftercider.noah.manager.ItemDataManager;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.ToggleButton;

public class SummaryActivity extends Activity {

	// 商品のテーブル
	TableLayout mTableLayout;
	
	// 購入予定の合計
	TextView mTextViewPlannedCount;
	TextView mTextViewPlannedPrice;
	
	// チェック済み商品の合計
	TextView mTextViewCheckedCount;
	TextView mTextViewCheckedPrice;
	
	// ボタン
	Button mButtonTweet;
	Button mButtonHome;
	
	// 商品情報
	ItemCountManager mItemCountManager;
	ItemDataManager  mItemDataManager;
	
	boolean[] mCheckedArray;
	
	LayoutInflater mInflater;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);
        initializeManager();
        initializeButton();
        initializeTable();
        mInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        
        createTable();
    }
    
    // ボタンの初期化を行う
    private void initializeButton(){
    	mButtonTweet = (Button)findViewById(R.id.button_tweet);
    	mButtonHome  = (Button)findViewById(R.id.button_home);
    	
    	mButtonTweet.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClassName("com.aftercider.noah","com.aftercider.noah.HomeActivity");
				startActivity(intent);
			}
		});
    	
    	mButtonHome.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClassName("com.aftercider.noah","com.aftercider.noah.HomeActivity");
				startActivity(intent);
			}
		});
    }
    
   // テーブルの初期化を行う
    private void initializeTable(){
    	// 購入予定アイテムの表を初期化する
    	mTableLayout = (TableLayout)findViewById(R.id.tablelayout_summary);
    	
    	// 合計個数の表を初期化
    	mTextViewPlannedCount = (TextView)findViewById(R.id.textview_summary_planned_count);
    	mTextViewPlannedPrice = (TextView)findViewById(R.id.textview_summary_planned_price);
    	mTextViewPlannedCount.setText(calcSumCount()+"個");
    	mTextViewPlannedPrice.setText(calcSumPrice()+"円");

    	// チェック済み表を初期化
    	mTextViewCheckedCount = (TextView)findViewById(R.id.textview_summary_checked_count);
    	mTextViewCheckedPrice = (TextView)findViewById(R.id.textview_summary_checked_price);
    }
    
    // マネージャの初期化を行う
    private void initializeManager(){
    	mItemDataManager = new ItemDataManager(this);
    	mItemDataManager.loadData();
    	
    	mItemCountManager = new ItemCountManager(this, mItemDataManager.getSize());
    	mItemCountManager.loadItemCount();
    	
    	mCheckedArray = new boolean[mItemDataManager.getSize()];	// 初期値falseが入る
    }
    
    // テーブルを作る
    private void createTable(){
    	TableRow tableRow;
    	
    	// 購入予定のアイテムのインデックスを取得
    	int[] buyItemIndexArray = mItemCountManager.getBuyItemIndexArray();
    	
    	for (int i = 0; i < buyItemIndexArray.length; i++) {
    		Log.i("item", i+":"+buyItemIndexArray[i]);
    		int position = buyItemIndexArray[i];
    		tableRow = createTableRow(position);    	// TableRowを作成
    		setTableRowClickEvent(tableRow);	// TableRowのクリックイベントを作成
        	addTable(tableRow);					// TableRowをリストに追加
		}
    	return;
    }
    
	// TableRowを作成
    private TableRow createTableRow(int position){
    	TableRow tableRow = (TableRow) mInflater.inflate(R.layout.tablerow_summary, null, false);
    	// ToggleButton toggleButtonChecked;
    	TextView textViewName  = (TextView) (tableRow.findViewById(R.id.textview_summary_item_name));
    	TextView textViewCount = (TextView) (tableRow.findViewById(R.id.textview_summary_item_count));
    	TextView textViewPrice = (TextView) (tableRow.findViewById(R.id.textview_summary_item_price));
    	
    	// TableRowにインデックスを付加
    	tableRow.setTag(position);
    	
    	// 表示内容の取得
    	ItemData itemData = mItemDataManager.getItem(position);
    	
    	// 表示内容をセット
    	textViewName.setText(itemData.getShortName());
    	textViewCount.setText(mItemCountManager.getItemCount(position)+"個");
    	textViewPrice.setText(itemData.getPrice()+"円");
    	
    	return tableRow;
    }
    
	// TableRowのクリックイベントを作成
    private void setTableRowClickEvent(final TableRow tableRow){
    	//tableRow.setOnClickListener(null);
    	
    	// toggleButtonをタッチすると合計が変わるようにする
    	ToggleButton toggleButton = (ToggleButton) (tableRow.findViewById(R.id.togglebutton_summary_checked));
    	toggleButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// チェック済み個数の更新
				int position = Integer.valueOf(tableRow.getTag().toString());
				int sumCount = 0;
				int sumPrice = 0;
				
				mCheckedArray[position] = ((ToggleButton)v).isChecked();
				for (int i = 0; i < mCheckedArray.length; i++) {
					if(!mCheckedArray[i]) continue;
					int count = mItemCountManager.getItemCount(i);
					sumCount += count;
					sumPrice += mItemDataManager.getItem(i).getPrice() * count;
				}
				mTextViewCheckedCount.setText(sumCount+"個");
				mTextViewCheckedPrice.setText(sumPrice+"円");
			}
		});
    	return;
    }
    
	// TableRowをリストに追加
    private void addTable(TableRow tableRow){
    	mTableLayout.addView(tableRow);
    	return;
    }
    
    // TODO Managerに分離する
    private int calcSumCount(){
    	int price = 0;
    	for (int i = 0; i < mItemDataManager.getSize(); i++) {
			price += mItemCountManager.getItemCount(i);
		}
    	return price;
    }

    // TODO Managerに分離する
    private int calcSumPrice(){
    	int price = 0;
    	for (int i = 0; i < mItemDataManager.getSize(); i++) {
			price += mItemDataManager.getItem(i).getPrice() * mItemCountManager.getItemCount(i);
		}
    	return price;
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }
}