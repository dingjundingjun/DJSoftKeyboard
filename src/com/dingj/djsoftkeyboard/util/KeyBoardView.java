package com.dingj.djsoftkeyboard.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import jding.debug.JDingDebug;

import android.content.Context;
import android.widget.RelativeLayout;

/**
 * 根据类型去生成对应的键盘布局
 * @author dingj
 *
 */
public class KeyBoardView extends RelativeLayout
{
	private Context mContext;
	/**键盘类型*/
	private int mType;
	/**ini 解析类*/
	private IniEditor mIniEditor;
	private static String TAG = "KeyBoardView";
	private boolean bDebug = true;
	/**========================父布局相关===============================*/
	private float mPaddingLeft = 0;
	private float mPaddingRight = 0;
	private float mPaddingTop = 0;
	private float mPaddingBottom = 0;
	/**======================和子布局相关===============================*/
	/**键盘大小，位置，按键背景*/
	private final String SECTION_KeyBoard = "keyboard";
	
	private final String OPTION_PaddingTop = "PaddingTop";
	private final String OPTION_PaddingLeft = "PaddingLeft";
	private final String OPTION_PaddingRight = "PaddingRight";
	private final String OPTION_PaddingBottom = "PaddingBottom";
	
	public KeyBoardView(Context context,int type)
	{
		super(context);
		mContext = context;
		mType = type;
		createKeyBoardView(mType);
	}
	
	private void createKeyBoardView(int type)
	{
		mIniEditor = new IniEditor();
		InputStream is = null;
		try
		{
			is = mContext.getResources().getAssets().open(Util.KeyBoardIniFiles[type]);
		} catch (IOException e)
		{
			e.printStackTrace();
		}
		if(is != null)
		{
			try
			{
				mIniEditor.load(is);
			} catch (IOException e)
			{
				e.printStackTrace();
			}
		}
		createChildViews();
	}
	
	/**
	 * 生成子views
	 */
	private void createChildViews()
	{
		List<String> sectionList = mIniEditor.sectionNames();
		int sectionLen = sectionList.size();
		for(int i = 0;i < sectionLen;i++)
		{
			String tempSection = sectionList.get(i);
			List<String> optionList = mIniEditor.optionNames(tempSection);
			int optionLen = optionList.size();
			String tempOption = "";
			for(int j = 0;j < optionLen;j++)
			{
				tempOption = optionList.get(j);
				if(tempSection.equals(SECTION_KeyBoard))
				{
					parseSectionKeyBoard(tempOption);
				}
				if(bDebug)
				{
					JDingDebug.printfD(TAG, "section=>" + tempSection + " option=>" + tempOption + " key=>" + mIniEditor.get(tempSection, tempOption));
				}
			}
		}
	}
	
	/**
	 * 解析"KeyBoard"
	 * @param option
	 */
	private void parseSectionKeyBoard(String option)
	{
		if(option.equals(OPTION_PaddingTop))
		{
			mPaddingTop = Float.parseFloat(mIniEditor.get(SECTION_KeyBoard, option));
		}
		else if(option.equals(OPTION_PaddingLeft))
		{
			mPaddingLeft = Float.parseFloat(mIniEditor.get(SECTION_KeyBoard, option));
		}
		else if(option.equals(OPTION_PaddingRight))
		{
			mPaddingRight = Float.parseFloat(mIniEditor.get(SECTION_KeyBoard, option));
		}
		else if(option.equals(OPTION_PaddingBottom))
		{
			mPaddingBottom = Float.parseFloat(mIniEditor.get(SECTION_KeyBoard, option));
		}
	}
}
