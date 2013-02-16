/*
 * Copyright (C) 2012 Aftercider
 *
 * All rights reserved by Aftercider
 * Contact: @aftercider (Twitter)
 *
 */

package com.aftercider.noah;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class HomeActivity extends Activity {

	Button buttonPlanning;
	Button buttonStaff;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initializeButton();
    }
    
    // ボタンの初期化を行う
    private void initializeButton(){
    	buttonPlanning = (Button)findViewById(R.id.button_planning);
        buttonPlanning.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClassName("com.aftercider.noah","com.aftercider.noah.PlanningActivity");
				startActivity(intent);
			}
		});
        buttonStaff = (Button)findViewById(R.id.button_staff);
        buttonStaff.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClassName("com.aftercider.noah","com.aftercider.noah.StaffActivity");
				startActivity(intent);
			}
		});
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
    	getMenuInflater().inflate(R.menu.menu_home, menu);
    	return true;
    }
}
