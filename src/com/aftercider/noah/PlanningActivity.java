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
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class PlanningActivity extends Activity {

	Button buttonSummary;	// SummaryActivityへのボタン
	ListView listView;
	ArrayList<ItemListItem> itemArrayList;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_planning);
        initializeButton();
        initializeListView();
    }
    
    // ボタンの初期化を行う
    private void initializeButton(){
    	buttonSummary = (Button)findViewById(R.id.button_summary);
    	buttonSummary.setOnClickListener(new OnClickListener() {
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
    	this.loadData();
    	listView = (ListView)findViewById(R.id.listview_planning);
    	listView.setAdapter(new ItemListAdapter(this, itemArrayList));
    	listView.setOnItemClickListener(new OnItemClickListener() {
    		@Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
    			Toast.makeText(getApplicationContext(),
                        (position + 1) + "番目がチェックされました。", Toast.LENGTH_SHORT).show();
            }
    		
    	});
    }

    // Adapterを読み込む
    private void loadData(){
    	itemArrayList = new ArrayList<ItemListItem>();
    	for(int i = 0; i < 30; i++){
    		ItemListItem item = new ItemListItem();
    		item.setCount(0);
    		item.setItemName("ほげ");
    		item.setPrice(4980);
    		itemArrayList.add(item);
    	}
    	
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
    	getMenuInflater().inflate(R.menu.menu_planning, menu);
        return true;
    }
}
