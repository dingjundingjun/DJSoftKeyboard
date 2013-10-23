package com.dingj.djsoftkeyboard;

import android.app.KeyguardManager;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Bitmap;
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

public class SoftInputService extends InputMethodService
{
	private KeyBoardControl mKeyBoardControl;
	private final static String TAG = "SoftInputService";
	@Override
	public void onCreate()
	{
		Log.d(TAG, "onCreate");
		super.onCreate();
		Button btn = new Button(this);
		btn.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				// TODO Auto-generated method stub
				
			}
		});
	}

	/**
	 * 加载键盘数据
	 * */
	@Override
	public void onInitializeInterface()
	{
		Log.d(TAG, "onInitializeInterface");
		super.onInitializeInterface();
		mJdingKeyBoardControl = new KeyBoardControl(this, getCurrentInputConnection());
	}
	
	@Override
	public void onStartInput(EditorInfo attribute, boolean restarting)
	{
		Log.d(TAG, "onStartInput");
		// TODO Auto-generated method stub
		super.onStartInput(attribute, restarting);
	}
	
	@Override
	public View onCreateInputView()
	{
		Log.d(TAG, "onCreateInputView");
//		if(info.privateImeOptions != null && info.privateImeOptions.equals("eebbk.softinput"))
		{
			return mJdingKeyBoardControl.getKeyBoardView();
		}
	}
	
	@Override
	public View onCreateCandidatesView()
	{
		Log.d(TAG, "onCreateCandidatesView");
		return super.onCreateCandidatesView();
	}
	
	@Override
	public void onStartInputView(EditorInfo info, boolean restarting)
	{
		Log.d(TAG, "onStartInputView");
		// TODO Auto-generated method stub
		super.onStartInputView(info, restarting);
		if(info.privateImeOptions != null && info.privateImeOptions.equals("eebbk.softinput"))
		{ 
			mJdingKeyBoardControl.setInputConnection(getCurrentInputConnection());
			Log.e("jding id", info.fieldName + " : " + info.packageName + " " + info.privateImeOptions);
		}
//		mJdingKeyBoardControl.setInputConnection(getCurrentInputConnection());
		Log.e("jding id", info.fieldName + " : " + info.packageName + " " + info.privateImeOptions);
	}
	
	

	


	@Override
	public void onConfigurationChanged(Configuration newConfig)
	{
		// TODO Auto-generated method stub
		super.onConfigurationChanged(newConfig);
	}

	 public boolean isLandScape()
	      {
	          Configuration config = getResources().getConfiguration();
	          if (config.orientation != Configuration.ORIENTATION_LANDSCAPE) {
	              return false;
	           }
	          return true;
	      }
	
	

	

}