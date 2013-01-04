package com.aftercider.noah;

import com.aftercider.noah.ItemList.ItemListView;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Log;

public class PlanningItemManager {
	
	private SharedPreferences 	mPref         = null;
	private String 			mPrefName 	  = "com.aftercider.noah.pref";
	private String				mPrefCountKey = "itemcount";
	private int[]		 		mItemCounts;
	private PlanningActivity	mActivity;
	
	public PlanningItemManager(PlanningActivity parentActivity, int itemCount){
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
		if(mItemCounts[position] < 0) return false;
		return saveItemCount();
	}
	
	public int getItemCount(int position){
		if(position < 0 || position > mItemCounts.length - 1) return -1;
		Log.i(getClass().getName(), "getCount at:"+position+"val:"+mItemCounts[position]);
		return mItemCounts[position];
	}
	
	private SharedPreferences getPref(){
		return mActivity.getSharedPreferences(mPrefName, Context.MODE_WORLD_READABLE|Context.MODE_WORLD_WRITEABLE);
	}
	
	private String getPreferenceKey(int position){
		return mPrefCountKey + position;
	}
}
