package com.aftercider.noah;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class AccountActivity extends Activity {

	Button buttonPlanning;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_budget);
        initializeButton();
    }
    
    // ボタンの初期化を行う
    private void initializeButton(){
    	buttonPlanning = (Button)findViewById(R.id.button_budget);
    	buttonPlanning.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClassName("com.aftercider.noah","com.aftercider.noah.PlanningActivity");
				startActivity(intent);
			}
		});
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_budget, menu);
        return true;
    }
}
