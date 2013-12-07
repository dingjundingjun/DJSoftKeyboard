package com.dingj.djsoftkeyboard.views;

import com.dingj.djsoftkeyboard.util.Util;

import android.content.Context;
import android.util.AttributeSet;

/**
 * 26键英文输入法
 * @author dingj
 *
 */
public class EnglishKeyboardViewfor26 extends KeyBoardInputView
{
	
	public EnglishKeyboardViewfor26(Context context, AttributeSet attrs) 
	{
		super(context, attrs);
	}

	public EnglishKeyboardViewfor26(Context context)
	{
		super(context);
		initKeyBoardView(Util.KEYBOARD_TYPE_ENGLISH26);
		createKeyBoardView();
	}

	@Override
	public void createKeyBoardView()
	{
		super.createKeyBoardView();
		
	}
	
	
}
