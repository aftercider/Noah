/*
 * Copyright (C) 2012 Aftercider
 *
 * All rights reserved by Aftercider
 * Contact: @aftercider (Twitter)
 *
 */

package com.aftercider.noah;

import java.util.ArrayList;

import com.aftercider.noah.ItemList.TweetListAdapter;
import com.aftercider.noah.ItemList.TweetListItem;
import com.aftercider.noah.manager.TwitterAccountManager;
import com.aftercider.tool.TwitterOAuth;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class TwitterAuthActivity extends Activity {

	public static final String TWEET_MESSAGE = "tweetmessage";
	
	private Button mTweetButton;	// ツイートする
	private Button mAccountButton;	// アカウント切替
	private ListView mTweetListView;	// ツイート内容のリスト
	private TweetListAdapter mTweetListAdapter;	// ツイート内容のAdapter
	private TwitterAccountManager mTwitterAccountManager;
	private String[] mTweetMessage;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_twitterauth);
        
        // ツイッター認証されてなかったら認証させる
        mTwitterAccountManager = new TwitterAccountManager(this);
        if(mTwitterAccountManager.getStatus() == TwitterAccountManager.STATUS_NONE){
        	Toast t = Toast.makeText(getApplicationContext(), "アカウントの認証をしてください", Toast.LENGTH_SHORT);
            t.show();
        	Intent intent = new Intent(getApplicationContext(), TwitterOAuth.class);
			startActivityForResult(intent, TwitterOAuth.REQ_CODE_TWITTER);
        }
        
        // インテントの取得
        Intent intent = getIntent();
        mTweetMessage = intent.getStringArrayExtra(TWEET_MESSAGE);
        initializeTweetAdapter(mTweetMessage);
        initializeTweetListview();
        initializeButton();
    }

    @Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == TwitterOAuth.REQ_CODE_TWITTER) {
			if (resultCode == RESULT_OK) {
				mTwitterAccountManager.setAccount(data);
				setIconUrl(mTwitterAccountManager.getImageUrl());
				initializeTweetListview();
				mTweetButton.setEnabled(true);
				Toast t = Toast.makeText(getApplicationContext(), "認証に成功しました", Toast.LENGTH_SHORT);
	            t.show();
			}else{
				if(mTwitterAccountManager.getStatus() == TwitterAccountManager.STATUS_NONE){
					mTweetButton.setEnabled(false);
				}
			}
		}
	}
    
    private void setIconUrl(String iconUrl){
    	TweetListItem item;
    	ArrayList<TweetListItem> mItemArrayList = new ArrayList<TweetListItem>();
    	for (int i = 0; i < mTweetListAdapter.getCount(); i++) {
    		item = mTweetListAdapter.getItem(i);
    		item.setImageUrl(iconUrl);
    		mItemArrayList.add(item);
		}
    	mTweetListAdapter = new TweetListAdapter(this, mItemArrayList);
    }
    
    // ツイート用Adapterを初期化する
    private void initializeTweetAdapter(String[] tweetMessage){
    	if(tweetMessage == null) return;
    	ArrayList<TweetListItem> mItemArrayList = new ArrayList<TweetListItem>();
    	for(int i = 0; i < tweetMessage.length; i++){
    		TweetListItem item = new TweetListItem();
    		if(mTwitterAccountManager.getStatus() == TwitterAccountManager.STATUS_AVAILABLE){
    			item.setImageUrl(mTwitterAccountManager.getImageUrl());
    		}
    		item.setText(tweetMessage[i]);
    		mItemArrayList.add(item);
    	}
    	mTweetListAdapter = new TweetListAdapter(this, mItemArrayList);
    }
    
    // リストビューの初期化を行う
    private void initializeTweetListview(){
    	mTweetListView = (ListView)findViewById(R.id.listview_twitter);
    	mTweetListView.setAdapter(mTweetListAdapter);
    }
    
    // ボタンの初期化を行う
    private void initializeButton(){
    	mAccountButton = (Button)findViewById(R.id.button_twitter_account);
    	mAccountButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(v.getContext(), TwitterOAuth.class);
				startActivityForResult(intent, TwitterOAuth.REQ_CODE_TWITTER);
			}
		});
    	
    	mTweetButton = (Button)findViewById(R.id.button_twitter_tweet);
    	mTweetButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Toast t = Toast.makeText(getApplicationContext(), "ツイート中です…", Toast.LENGTH_SHORT);
	            t.show();
				TweetTask tw = new TweetTask();
				tw.execute(mTweetMessage);
			}
		});
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }
    
    /**
     * バックグラウンド処理を行うクラス
     */
    class TweetTask extends AsyncTask<String[], Void, Boolean> {
    	@Override
		protected Boolean doInBackground(String[]... messages) {
    		return mTwitterAccountManager.tweet(messages[0]);
		}

        @Override
        public void onPostExecute(Boolean param) {
        	String toastText;
        	if(param){
        		toastText = "ツイートしました";
        	}else{
        		toastText = "ツイートに一部失敗しました";
        	}
            Toast t = Toast.makeText(getApplicationContext(), toastText, Toast.LENGTH_SHORT);
            t.show();
            finish();
        }
    }
}