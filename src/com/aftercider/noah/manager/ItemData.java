package com.aftercider.noah.manager;


import com.aftercider.noah.R;

public class ItemData {
	private String mItemName;
	private int    mPrice;
	private String mShortName;
	private int mResourceID; 
	
	public ItemData() {
		mItemName   = "商品名";
		mPrice      = 7777;
		mShortName  = "短縮名";
		mResourceID = 0;
	}
	
	public void setItemName(String itemName) {
		mItemName = itemName;
	}

	public void setPrice(int price) {
		mPrice = price;
	}
	
	public void setShortName(String shortName){
		mShortName = shortName;
	}
	
	public void setResourceId(int resId){
		mResourceID = resId;
	}
	
	public void setResourceId(String imageName, String color){
		if(imageName.equals("book")){
			if(color.equals("pink")){
				mResourceID = R.drawable.icon_pink01;
			}
			if(color.equals("blue")){
				mResourceID = R.drawable.icon_blue01;
			}
		}
		if(imageName.equals("poster")){
			if(color.equals("pink")){
				mResourceID = R.drawable.icon_pink02;
			}
			if(color.equals("blue")){
				mResourceID = R.drawable.icon_blue02;
			}
		}
		if(imageName.equals("card")){
			if(color.equals("pink")){
				mResourceID = R.drawable.icon_pink03;
			}
			if(color.equals("blue")){
				mResourceID = R.drawable.icon_blue03;
			}
		}
		if(imageName.equals("towel")){
			if(color.equals("pink")){
				mResourceID = R.drawable.icon_pink04;
			}
			if(color.equals("blue")){
				mResourceID = R.drawable.icon_blue04;
			}
		}
		if(imageName.equals("shirt")){
			if(color.equals("pink")){
				mResourceID = R.drawable.icon_pink05;
			}
			if(color.equals("blue")){
				mResourceID = R.drawable.icon_blue05;
			}
		}
		if(imageName.equals("key")){
			if(color.equals("pink")){
				mResourceID = R.drawable.icon_pink06;
			}
			if(color.equals("blue")){
				mResourceID = R.drawable.icon_blue06;
			}
		}
		if(imageName.equals("bag")){
			if(color.equals("pink")){
				mResourceID = R.drawable.icon_pink07;
			}
			if(color.equals("blue")){
				mResourceID = R.drawable.icon_blue07;
			}
		}
		if(imageName.equals("penlight")){
			if(color.equals("pink")){
				mResourceID = R.drawable.icon_pink08;
			}
			if(color.equals("blue")){
				mResourceID = R.drawable.icon_blue08;
			}
		}
		if(imageName.equals("cd")){
			if(color.equals("pink")){
				mResourceID = R.drawable.icon_pink09;
			}
			if(color.equals("blue")){
				mResourceID = R.drawable.icon_blue09;
			}
		}
		if(imageName.equals("etc")){
			if(color.equals("pink")){
				mResourceID = R.drawable.icon_pink10;
			}
			if(color.equals("blue")){
				mResourceID = R.drawable.icon_blue10;
			}
		}
		if(imageName.equals("gacha")){
			if(color.equals("pink")){
				mResourceID = R.drawable.icon_pink11;
			}
			if(color.equals("blue")){
				mResourceID = R.drawable.icon_blue11;
			}
		}
	}

	
	
	public String getItemName(){
		return mItemName;
	}
	
	public int getPrice(){
		return mPrice;
	}
	
	public String getShortName() {
		return mShortName;
	}
	
	public int getResourceId(){
		return mResourceID;
	}
}
