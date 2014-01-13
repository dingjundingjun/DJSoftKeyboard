package com.dingj.djsoftkeyboard;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import jding.debug.JDingDebug;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Debug;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputConnection;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;

import com.dingj.djsoftkeyboard.util.IniEditor;
import com.dingj.djsoftkeyboard.util.Util;
import com.dingj.djsoftkeyboard.views.DragViews;
import com.dingj.djsoftkeyboard.views.EnglishKeyboardViewfor26;
import com.dingj.djsoftkeyboard.views.KeyBoardView;

/**
 * KeyboardControl
 * 用来管理各个键盘之间的切换、候选栏的控制
 * @author dingj
 *
 */
public class KeyBoardControl 
{
	private boolean DEBUG = true;
	private String TAG = "KeyBoardControl";
	private Context mContext;
	/**输入通道*/
	private InputConnection mInputConnection;
	/**输入界面*/
	private KeyBoardView mKeyBoardView;
	/**输入法外层布局*/
	private RelativeLayout mMainFrame;
	/**拖动组件*/
	private DragViews mDragViews;
	/**ini接口*/
	private IniEditor mIniEditor;
	/**宽*/
	private int mLayoutW;
	/**高*/
	private int mLayoutH;
	/**背景图片*/
	private String mBGName;
	/*****************常量****************************/
	/**竖屏布局*/
	private final String LAYOUT_PORT = "layout_port";
	/**宽*/
	private final String LAYOUT_W = "w";
	/**高*/
	private final String LAYOUT_H = "h";
	/**背景*/
	private final String BG_IMAGE = "bg_image";
	public KeyBoardControl(Context c,InputConnection ic) 
	{
		mContext = c;
		mInputConnection = ic;
		init();
	}
	
	private void init()
	{
		mMainFrame = (RelativeLayout) RelativeLayout.inflate(mContext, R.layout.main, null);
		mDragViews = (DragViews) mMainFrame.findViewById(R.id.dragview);
		initTheme();
		initKeyBoard();
	}
	
	/**
	 * 初始化竖屏布局
	 */
	private void initKeyBoard()
	{
		mKeyBoardView = new EnglishKeyboardViewfor26(mContext,Util.KEYBOARD_TYPE_ENGLISH26);    //第一次初始化默认加载英文26键盘
		RelativeLayout.LayoutParams param = new LayoutParams(mKeyBoardView.getViewWidth(),mKeyBoardView.getViewHeight());
		param.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
		mDragViews.setLayoutParams(new LayoutParams(mLayoutW, mLayoutH));
		mDragViews.addView(mKeyBoardView, param);
	}
	/**
	 * 获取输入界面
	 * @return 
	 */
	public View getInputView()
	{
		if(mKeyBoardView != null)
		{
			return mMainFrame;
		}
		return null;
	}
	
	/**
	 * 初始化主题
	 */
	private void initTheme()
	{
		if(!Util.isLandScape(mContext))
		{
			mIniEditor = Util.openAssetPortInit(mContext, Util.THEME);
		}
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
				if(tempSection.equals(LAYOUT_PORT))
				{
					if(tempOption.equals(LAYOUT_W))
					{
						mLayoutW = (int) (Float.parseFloat(mIniEditor.get(LAYOUT_PORT, tempOption)) * Util.DISPLAY_WIDTH);
					}
					else if(tempOption.equals(LAYOUT_H))
					{
						mLayoutH = (int) (Float.parseFloat(mIniEditor.get(LAYOUT_PORT, tempOption)) * Util.DISPLAY_HEIGHT);
					}
					else if(tempOption.equals(BG_IMAGE))
					{
						mBGName = mIniEditor.get(LAYOUT_PORT, tempOption);
					}
				}
			}
		}
		if(DEBUG)
		{
			JDingDebug.printfD(TAG, "mLayoutW:" + mLayoutW + " mLayoutH:" + mLayoutH + " mBGName:" + mBGName);
		}
		setAssetBGImage(mBGName);
	}
	
	/**
	 * 设置主框背景
	 * @param image
	 */
	private void setAssetBGImage(String image)
	{
		BitmapDrawable drawable = Util.openAssetImage(mContext, image);
		mMainFrame.setBackground(drawable);
	}
}
