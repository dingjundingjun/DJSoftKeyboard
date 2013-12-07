package com.dingj.djsoftkeyboard;

import android.content.Context;
import android.content.res.Configuration;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputConnection;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;

import com.dingj.djsoftkeyboard.views.EnglishKeyboardViewfor26;
import com.dingj.djsoftkeyboard.views.KeyBoardInputView;

/**
 * KeyboardControl
 * 用来管理各个键盘之间的切换、候选栏的控制
 * @author dingj
 *
 */
public class KeyBoardControl 
{
	private Context mContext;
	/**输入通道*/
	private InputConnection mInputConnection;
	/**输入界面*/
	private KeyBoardInputView mKeyBoardInputView; 
	public KeyBoardControl(Context c,InputConnection ic) 
	{
		mContext = c;
		mInputConnection = ic;
		mKeyBoardInputView = new EnglishKeyboardViewfor26(mContext);    //第一次初始化默认加载英文26键盘
	}
	
	/**
	 * 获取输入界面
	 * @return 
	 */
	public View getInputView()
	{
		if(mKeyBoardInputView != null)
		{
			return mKeyBoardInputView;
		}
		return null;
	}
}
