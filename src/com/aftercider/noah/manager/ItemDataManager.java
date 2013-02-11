package com.aftercider.noah.manager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;

public class ItemDataManager {
	final String DATABASE_NAME = "DBtable.json";
	
	Activity mParentActivity;
	ItemData[] mItemDataArray;
	
	
	public ItemDataManager(Activity parentActivity){
		mParentActivity = parentActivity;
	}
	
	public boolean loadData(){
		try {
			InputStream inputStream = mParentActivity.getAssets().open(DATABASE_NAME);
			String jsonStr = inputStreemToString(inputStream);
			JSONArray json = new JSONArray(jsonStr);
			mItemDataArray = new ItemData[json.length()];
			for (int i = 0; i < json.length(); i++) {
				JSONObject jsonObject = json.getJSONObject(i);
				ItemData itemData = new ItemData();
				itemData.setItemName(jsonObject.getString("itemname"));
				itemData.setPrice(jsonObject.getInt("price"));
				itemData.setShortName(jsonObject.getString("shortname"));
				mItemDataArray[i] = itemData;
			}
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		} catch (JSONException e) {
			e.printStackTrace();
		}		
		return true;
	}
	
	public ItemData getItem(int position){
		if(position < 0 || position > mItemDataArray.length - 1){
			return null;
		}
		return mItemDataArray[position];
	}
	
	public int getSize(){
		return mItemDataArray.length;
	}
	
	private static String inputStreemToString(InputStream in) throws IOException{
        
	    BufferedReader reader = 
	        new BufferedReader(new InputStreamReader(in, "UTF-8"));
	    StringBuffer buf = new StringBuffer();
	    String str;
	    while ((str = reader.readLine()) != null) {
	            buf.append(str);
	            buf.append("\n");
	    }
	    return buf.toString();
	}
}
