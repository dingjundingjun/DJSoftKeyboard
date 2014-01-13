package com.dingj.djsoftkeyboard;

import com.dingj.djsoftkeyboard.test.TestIni;
import com.dingj.djsoftkeyboard.util.Util;
import com.dingj.djsoftkeyboard.views.DragViews;

import jding.debug.JDingDebug;
import android.app.KeyguardManager;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.inputmethodservice.InputMethodService;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;

public class DJManagerService extends InputMethodService
{
	/**控制类*/
	private KeyBoardControl mKeyBoardControl;
	private final static String TAG = "SoftInputService";
	private final static boolean DEBUG = true;
	@Override
	public void onCreate()
	{
		super.onCreate();
		Util.getDisplaySize(this);
		mKeyBoardControl = new KeyBoardControl(this, getCurrentInputConnection());
	}

	/**
	 * 加载键盘数据
	 * */
	@Override
	public void onInitializeInterface()
	{
		if(DEBUG)
		{
			JDingDebug.printfD(TAG, "onInitializeInterface");
		}
		super.onInitializeInterface();
	}
	
	@Override
	public void onStartInput(EditorInfo attribute, boolean restarting)
	{
		if(DEBUG)
		{
			JDingDebug.printfD(TAG, "onStartInput");
		}
		super.onStartInput(attribute, restarting);
	}
	
	@Override
	public View onCreateInputView()
	{
		if(DEBUG)
		{
			JDingDebug.printfD(TAG, "onCreateInputView");
		}
		return mKeyBoardControl.getInputView();
//		DragViews dragViews = new DragViews(this);
//		RelativeLayout.LayoutParams param = new LayoutParams(768, 700);
//		param.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
//		dragViews.addView(mKeyBoardControl.getInputView(),param);
//		return dragViews;
	}
	
	@Override
	public View onCreateCandidatesView()
	{
		if(DEBUG)
		{
			JDingDebug.printfD(TAG, "onCreateCandidatesView");
		}
		return super.onCreateCandidatesView();
	}
	
	@Override
	public void onStartInputView(EditorInfo info, boolean restarting)
	{
		if(DEBUG)
		{
			JDingDebug.printfD(TAG, "onStartInputView");
		}
/*		TestIni testIni = new TestIni(this);
		testIni.init();*/
		super.onStartInputView(info, restarting);
	}
	
	@Override
	public void onConfigurationChanged(Configuration newConfig)
	{
		// TODO Auto-generated method stub
		super.onConfigurationChanged(newConfig);
	}
	
	
}