package com.dingj.djsoftkeyboard;

import android.content.Context;

public class Key
{
	private Context mContext;
	/**键值*/
	public String mSKey;
	/**高度*/
	public int mHeight;
	/**宽度*/
	public int mWidth;
	/**背景图片*/
	public String mBgImage;
	/**X坐标*/
	public int mX;
	/**Y坐标*/
	public int mY;
	/**属于哪一个row*/
	public int mRow;
	
	public Key(Context mContext)
	{
		super();
		this.mContext = mContext;
	}
	
	public String getmSKey()
	{
		return mSKey;
	}
	public void setmSKey(String mSKey)
	{
		this.mSKey = mSKey;
	}
	public int getmHeight()
	{
		return mHeight;
	}
	public void setmHeight(int mHeight)
	{
		this.mHeight = mHeight;
	}
	public int getmWidth()
	{
		return mWidth;
	}
	public void setmWidth(int mWidth)
	{
		this.mWidth = mWidth;
	}
	public String getmBgImage()
	{
		return mBgImage;
	}
	public void setmBgImage(String mBgImage)
	{
		this.mBgImage = mBgImage;
	}
	public int getmX()
	{
		return mX;
	}
	public void setmX(int mX)
	{
		this.mX = mX;
	}
	public int getmY()
	{
		return mY;
	}
	public void setmY(int mY)
	{
		this.mY = mY;
	}
	
	public void setRow(int row)
	{
		mRow = row;
	}
	
	public int getRow()
	{
		return mRow;
	}

	public void caculate(int mHGap, int vOffset, int hOffset,int index)
	{
	}
}
