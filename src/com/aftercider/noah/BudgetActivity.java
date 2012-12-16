/*
 * Copyright (C) 2012 Aftercider
 *
 * All rights reserved by Aftercider
 * Contact: @aftercider (Twitter)
 *
 */

package com.aftercider.noah;

import com.example.widget.NumberPicker;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class BudgetActivity extends Activity {

	private final String PREFERENCE_NAME = "com.aftercider.noah";
	private final String PREFERENCE_KEY  = "budget";
	
	// 予算のNumberPicker
	private NumberPicker numberPicker_ten_thousand;
	private NumberPicker numberPicker_thousand;
	private NumberPicker numberPicker_hundred;
	
	private Button buttonPlanning; 
	
	// 予算のストレージ
	private SharedPreferences budgetPref;
	private int budgetValue;
	
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_budget);
        initializeButton();
        initializeNumberPicker();
    }
    
    // ボタンの初期化を行う
    private void initializeButton(){
    	buttonPlanning = (Button)findViewById(R.id.button_planning);
    	buttonPlanning.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				saveBudget( getPickerValue() );
				Intent intent = new Intent();
				intent.setClassName("com.aftercider.noah","com.aftercider.noah.PlanningActivity");
				startActivity(intent);
			}
		});
    }
    
    // 予算をストレージに保存
    private void saveBudget(int budget){
    	if(budget < 0){
    		Log.e("Budget exceeded.", String.valueOf(budget));
    	}
    	Editor e = budgetPref.edit(); 
    	e.putInt(PREFERENCE_KEY, budget);
    	e.commit();
    }
    
    // NumberPickerの初期化を行う
    private void initializeNumberPicker(){
    	numberPicker_ten_thousand = (NumberPicker)findViewById(R.id.budget_picker_ten_thousand);
    	numberPicker_thousand     = (NumberPicker)findViewById(R.id.budget_picker_thousand);
    	numberPicker_hundred      = (NumberPicker)findViewById(R.id.budget_picker_hundred);
    	
    	numberPicker_ten_thousand.setRange(0, 9);
    	numberPicker_thousand.setRange(0, 9);
    	numberPicker_hundred.setRange(0, 9);
    	
    	budgetPref  = getSharedPreferences(PREFERENCE_NAME, MODE_PRIVATE);
    	budgetValue = budgetPref.getInt(PREFERENCE_KEY, 10000);
    	
    	setPickerValue(budgetValue);   	
    }

    // NumberPickerの値を取得
    private int getPickerValue(){
    	return numberPicker_ten_thousand.getCurrent() * 10000
    			+ numberPicker_thousand.getCurrent() * 1000
    			+ numberPicker_hundred.getCurrent() * 100;
    }
    
    // NumberPickerに値を入力
    private void setPickerValue(int value){
    	if(value < 0){
    		Log.e("Budget exceeded.", String.valueOf(value));
    	}
    	numberPicker_ten_thousand.setCurrent((int)(Math.floor(value / 10000)) % 10);
    	numberPicker_thousand.setCurrent((int)(Math.floor(value / 1000)) % 10);
    	numberPicker_hundred.setCurrent((int)(Math.floor(value / 100)) % 10);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_budget, menu);
        return true;
    }
}
