package com.dingj.djsoftkeyboard.views;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import com.dingj.djsoftkeyboard.util.IniEditor;
import com.dingj.djsoftkeyboard.util.Util;

import jding.debug.JDingDebug;

import android.content.Context;
import android.graphics.Color;
import android.widget.RelativeLayout;

/**
 * 根据类型去生成对应的键盘布局
 * @author dingj
 *
 */
public class KeyBoardView extends RelativeLayout
{
	private Context mContext;
	private boolean DEBUG = true;
	/**键盘类型*/
	private int mType;
	/**ini 解析类*/
	private IniEditor mIniEditor;
	private static String TAG = "KeyBoardView";
	private boolean bDebug = true;
	/**========================父布局相关===============================*/
	/**左边距*/
	private float mPaddingLeft = 0;
	/**右边距*/
	private float mPaddingRight = 0;
	/**上边距*/
	private float mPaddingTop = 0;
	/**底边距*/
	private float mPaddingBottom = 0;
	/**宽度*/
	private float mWidth = 0;
	/**高度*/
	private float mHeight = 0;
	/**背景图片*/
	private String mBackgroundPic;
	/**包含多少行键盘*/
	private String mRow[];
	/**界面属性*/
	private LayoutParams mParams;
	/**======================和子布局相关===============================*/
	
	/**======================常量===================================*/
	/**键盘大小，位置，按键背景*/
	private final String SECTION_KeyBoard = "keyboard";
	private final String OPTION_PaddingTop = "paddingTop";
	private final String OPTION_PaddingLeft = "paddingLeft";
	private final String OPTION_PaddingRight = "paddingRight";
	private final String OPTION_PaddingBottom = "paddingBottom";
	private final String OPTION_W = "w";
	private final String OPTION_H = "h";
/*	BG_IMAGE=bg.png 
			ROWS=ROW1,ROW2,ROW3,ROW4*/
	private final String OPTION_BG_IMAGE = "bg_image";
	private final String OPTION_ROWS = "rows";
	
	
	public KeyBoardView(Context context)
	{
		super(context);
	}

	public KeyBoardView(Context context,int type)
	{
		super(context);
		mContext = context;
		mType = type;
		mParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		createKeyBoardView(mType);
	}
	
	private void createKeyBoardView(int type)
	{
		if(!Util.isLandScape(mContext))
		{
			mIniEditor = Util.openAssetPortInit(mContext, Util.KeyBoardIniFiles[type]);
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
		initMainView();
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
		else if(option.equals(OPTION_W))
		{
			mWidth =  Float.parseFloat(mIniEditor.get(SECTION_KeyBoard, option));
		}
		else if(option.equals(OPTION_H))
		{
			mHeight = Float.parseFloat(mIniEditor.get(SECTION_KeyBoard, option));
		}
		else if(option.equals(OPTION_BG_IMAGE))
		{
			mBackgroundPic = mIniEditor.get(SECTION_KeyBoard, option);
		}
		else if(option.equals(OPTION_ROWS))
		{
			mRow = Util.getSplit(mIniEditor.get(SECTION_KeyBoard, option), ",");
		}
	}
	
	private void initMainView()
	{
		if(DEBUG)
		{
			JDingDebug.printfD(TAG, "mWidth=" + mWidth + " height=" + mHeight);
		}
		/**先做转换*/
		mWidth = Util.DISPLAY_WIDTH * mWidth;
		mHeight = Util.DISPLAY_HEIGHT * mHeight;
		mPaddingTop *= mHeight;
		mPaddingBottom *= mHeight;
		mPaddingLeft *= mWidth;
		mPaddingRight *= mWidth;
		/**转换完成以后设置属性*/
		if(DEBUG)
		{
			JDingDebug.printfD(TAG, "mWidth=" + mWidth + " height=" + mHeight);
		}
		mParams.width = (int) mWidth;
		mParams.height = (int) mHeight;
		this.setBackgroundColor(Color.RED);
		mParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
		this.setLayoutParams(mParams);
	}
	
	/**
	 * 获取键盘高度
	 * @return
	 */
	public int getViewHeight()
	{
		return (int) mHeight;
	}
	
	/**
	 * 获取键盘宽度
	 * @return
	 */
	public int getViewWidth()
	{
		return (int) mWidth;
	}
	
	
}
