package com.dingj.djsoftkeyboard;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;

import com.dingj.djsoftkeyboard.util.IniEditor;
import com.dingj.djsoftkeyboard.util.Util;

/**
 * 解析键盘
 * @author dingj
 *
 */
public class KeyBoard
{
	private boolean DEBUG = true;
	private final String TAG = "KeyBoard";
	/**ini解析*/
	public IniEditor mIniEditor;
	public Context mContext;
	/**按键宽度*/
	public int mKeyWidth;
	/**按键高度*/
	public int mKeyheight;
	/**外部布局高度*/
	public int mFrameHeight;
	/**外部布局宽度*/
	public int mFrameWidth;
	/**按键背景*/
	public String mKeyImage;
	/**有多岁行*/
	public int mRowNum;
	/**键盘间隔*/
	public int mHGap;
	public List<Key> mKeyList = new ArrayList<Key>();
	/***************************常量******************************/
	public KeyBoard(IniEditor mIniEditor, Context mContext,int frameWidth,int frameHeight,int rowNum)
	{
		super();
		this.mIniEditor = mIniEditor;
		this.mContext = mContext;
		mFrameHeight = frameHeight;
		mFrameWidth = frameWidth;
		mRowNum = rowNum;
	}

	/**
	 * 负责解析
	 */
	public void parse()
	{
		//首先解析按钮的参数
		mKeyWidth = (int) (Float.parseFloat(mIniEditor.get(mContext.getString(R.string.key), mContext.getString(R.string.width))) * mFrameWidth) ;
		mKeyheight = (int) (Float.parseFloat(mIniEditor.get(mContext.getString(R.string.key), mContext.getString(R.string.height))) * mFrameHeight) ;
		mKeyImage = mIniEditor.get(mContext.getString(R.string.key), mContext.getString(R.string.key_bg_image));
		mHGap = (int) (Float.parseFloat(mIniEditor.get(mContext.getString(R.string.key), mContext.getString(R.string.h_gap_qwerty))) * mFrameWidth) ;
		//解析每一行
		for(int i = 0;i < mRowNum;i++)
		{
			int tempI = i + 1;
			String rowSelection = "Row" + tempI;
			String keys[] = Util.getSplit(mIniEditor.get(rowSelection, mContext.getString(R.string.row_keys)),",");
			int lenght = keys.length;
			String vOffsetStr = mIniEditor.get(rowSelection, mContext.getString(R.string.v_offset));
			String hOffsetStr = mIniEditor.get(rowSelection, mContext.getString(R.string.h_offset));
			int vOffset = 0;
			int hOffset = 0;
			if(vOffsetStr != null)
			{
				vOffset = (int) (Float.parseFloat(mIniEditor.get(rowSelection, mContext.getString(R.string.v_offset))) * mFrameHeight);
			}
			if(hOffsetStr != null)
			{
				hOffset = (int) (Float.parseFloat(mIniEditor.get(rowSelection, mContext.getString(R.string.h_offset))) * mFrameWidth);
			}
			for(int j = 0;j < lenght;j++)    //计算单个键盘
			{
				String keySelection = keys[j];
				String s_key = mIniEditor.get(keySelection, mContext.getString(R.string.s_keys));
				String keyWidth = mIniEditor.get(keySelection, mContext.getString(R.string.width));
				Key tempKey = new Key(mContext);
				if(keyWidth == null)
				{
					tempKey.setmWidth(mKeyWidth);
				}
				else
				{
					tempKey.setmWidth((int) (Float.parseFloat(keyWidth) * mFrameWidth));
				}
				tempKey.setmHeight(mKeyheight);
				tempKey.setmSKey(s_key);
				tempKey.setmBgImage(mKeyImage);
				tempKey.setRow(i);
				tempKey.caculate(mHGap,vOffset,hOffset,j);
				mKeyList.add(tempKey);
			}
		}
	}
	
	public List<Key> getKeyList()
	{
		return mKeyList;
	}
}
