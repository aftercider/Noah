package com.aftercider.noah.manager;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class ItemCountManager {
	
	private SharedPreferences 	mPref         = null;
	private String 			mPrefName 	  = "com.aftercider.noah.pref";
	private String				mPrefCountKey = "itemcount";
	private int[]		 		mItemCounts;
	private Activity	mActivity;
	
	public ItemCountManager(Activity parentActivity, int itemCount){
		mActivity   = parentActivity; 
		mItemCounts	= new int[itemCount];
		
		for (int i = 0; i < mItemCounts.length; i++){
			mItemCounts[i] = 0;
		}
	}
	
	public boolean loadItemCount(){
		if(mPref == null) mPref = getPref();
		
		// Preferenceの読み込み
		for (int i = 0; i < mItemCounts.length; i++) {
			mItemCounts[i] = mPref.getInt(getPreferenceKey(i), 0);
		}
		return true;
	}
	
	public boolean saveItemCount(){
		if(mPref == null) mPref = getPref();
		Editor edit = mPref.edit();
		
		// Preferenceに書き込み
		for (int i = 0; i < mItemCounts.length; i++) {
			edit.putInt(getPreferenceKey(i), mItemCounts[i]);
		}
		edit.commit();
		return true;
	}
	
	// ItemCountを更新して保存
	public boolean addItemCount(int position, int addValue){
		if(position < 0 || position > mItemCounts.length - 1) return false;
		mItemCounts[position] += addValue;
		if(mItemCounts[position] < 0) {
			mItemCounts[position] = 0;
			return false;
		}
		return saveItemCount();
	}
	
	public boolean resetItemCount(){
		for (int i = 0; i < mItemCounts.length; i++) {
			mItemCounts[i] = 0;
		}
		return saveItemCount();
	}
	
	public int getItemCount(int position){
		if(position < 0 || position > mItemCounts.length - 1) return -1;
		return mItemCounts[position];
	}
	
	public int getBuyItemCounts(){
		int itemCounts = 0;
		for (int i = 0; i < mItemCounts.length; i++) {
			if(mItemCounts[i] > 0) itemCounts++;
		}
		return itemCounts;
	}
	
	public int[] getBuyItemIndexArray(){
		int[] indexArray = new int[getBuyItemCounts()];
		int currentIndex = 0;
		for (int i = 0; i < mItemCounts.length; i++) {
			if(mItemCounts[i] > 0){
				indexArray[currentIndex] = i;
				currentIndex++;
			}
		}
		return indexArray;
	}
	
	private SharedPreferences getPref(){
		return mActivity.getSharedPreferences(mPrefName, Context.MODE_WORLD_READABLE|Context.MODE_WORLD_WRITEABLE);
	}
	
	private String getPreferenceKey(int position){
		return mPrefCountKey + position;
	}
}
