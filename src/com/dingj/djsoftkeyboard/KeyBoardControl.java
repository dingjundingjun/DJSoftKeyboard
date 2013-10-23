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

import com.jding.softinput.activity.eebbkDragViews.OnViewChangeListener;

public class KeyBoardControl implements eebbkKeyBoardView.OnKeyboardActionListener 
{
	private Context mContext;
	private InputConnection mInputConnection;
	/**键盘逻辑处理1*/
	private eebbkKeyboard mKeyBoard_first;
	/**键盘逻辑处理1*/
	private eebbkKeyboard mKeyBoard_second;
	/**键盘逻辑处理1*/
	private eebbkKeyboard mKeyBoard_third;
//	/**键盘逻辑处理1*/
//	private eebbkKeyboard mKeyBoard_forth;
	
	/**上排键盘逻辑处理*/
	private eebbkKeyboard mTopKeyBoard_first;
	/**上排键盘逻辑处理*/
	private eebbkKeyboard mTopKeyBoard_second;
	/**上排键盘逻辑处理*/
	private eebbkKeyboard mTopKeyBoard_third;
	/**上排键盘逻辑处理*/
//	private JdingKeyboard mTopKeyBoard_third;
	/**上排键盘逻辑处理*/
//	private JdingKeyboard mTopKeyBoard_forth;
	
	/**键盘1*/
	private eebbkKeyBoardView mKeyBoardView_first;
	/**键盘2*/
	private eebbkKeyBoardView mKeyBoardView_second;
	/**键盘3*/
	private eebbkKeyBoardView mKeyBoardView_third;
//	/**键盘4*/
//	private eebbkKeyBoardView mKeyBoardView_forth;
	/**上排符号按键布局1*/
	private eebbkKeyBoardView mTopKeyBoardView_first;
	/**上排符号按键布局2*/
	private eebbkKeyBoardView mTopKeyBoardView_second;
	/**上排符号按键布局2*/
	private eebbkKeyBoardView mTopKeyBoardView_third;
	/**上排符号按键布局3*/
//	private JdingKeyBoardView mTopKeyBoardView_third;
	/**上排符号按键布局4*/
//	private JdingKeyBoardView mTopKeyBoardView_forth;
	/**键盘主界面*/
	private LinearLayout mMainView;
	/**上排符号VIEW GROUP*/
	private eebbkDragViews mTopKeyBoardViewGroup;
	/**键盘VIEW GROUP*/
	private eebbkDragViews mKeyBoardViewGroup;
	/**上排符号上一页*/
	private Button mTopLastPage;
	/**上排符号下一页*/
	private Button mTopNextPage;
	/**键盘上一页*/
	private Button mKeyBoardLastPage;
	/**键盘下一页*/
	private Button mKeyBoardNextpage;
	/**隐藏键盘*/
	private Button mHideBtn;
	/**键盘界面*/
	private View mKeyBoardView;
	/**一共多少页*/
	private final int TOP_VIEW_COUNT = 3;
	private final int KEY_VIEW_COUNT = 3;
	/**判断是否显示*/
	public KeyBoardControl(Context mContext,
			InputConnection mInputConnection)
	{
		super();
		this.mContext = mContext;
		this.mInputConnection = mInputConnection;
		onInitializeInterface();
		init();
	}
	
	public void setInputConnection(InputConnection ic)
	{
		mInputConnection = ic;
	}
	
	public void initPortLayout()
	{
		mMainView = (LinearLayout)LinearLayout.inflate(mContext,
                R.layout.input, null);
		mKeyBoardView = mMainView.findViewById(R.id.keyboardview);
		mTopLastPage = (Button)mMainView.findViewById(R.id.character_left);
		mTopNextPage = (Button)mMainView.findViewById(R.id.character_right);
		mKeyBoardLastPage = (Button)mMainView.findViewById(R.id.last_page);
		mKeyBoardNextpage = (Button)mMainView.findViewById(R.id.next_page);
		mHideBtn = (Button)mMainView.findViewById(R.id.btn_hide);
		
		mTopKeyBoardViewGroup = (eebbkDragViews) mMainView.findViewById(R.id.top_keyboard_group);
		mKeyBoardViewGroup = (eebbkDragViews) mMainView.findViewById(R.id.keyboard_group);
//		LayoutParams lp = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
//		lp.topMargin = 20;
//		mKeyBoardViewGroup.setLayoutParams(lp);
		//初始化键盘
		mKeyBoardView_first = (eebbkKeyBoardView) mMainView.findViewById(R.id.keyboard_first);
		mKeyBoardView_second = (eebbkKeyBoardView) mMainView.findViewById(R.id.keyboard_second);
		mKeyBoardView_third = (eebbkKeyBoardView) mMainView.findViewById(R.id.keyboard_third);
		//			mKeyBoardView_forth = (eebbkKeyBoardView) mMainView.findViewById(R.id.keyboard_forth);
		
		mTopKeyBoardView_first = (eebbkKeyBoardView) mMainView.findViewById(R.id.top_keyboard_first);
		mTopKeyBoardView_second = (eebbkKeyBoardView) mMainView.findViewById(R.id.top_keyboard_second);
		mTopKeyBoardView_third = (eebbkKeyBoardView)mMainView.findViewById(R.id.top_keyboard_third);
		
		mKeyBoardView_first.setKeyboard(mKeyBoard_first);
		mKeyBoardView_second.setKeyboard(mKeyBoard_second);
		mKeyBoardView_third.setKeyboard(mKeyBoard_third);
//		mKeyBoardView_forth.setKeyboard(mKeyBoard_forth);
		
		mKeyBoardView_first.setOnKeyboardActionListener(this);
		mKeyBoardView_second.setOnKeyboardActionListener(this);
		mKeyBoardView_third.setOnKeyboardActionListener(this);
//		mKeyBoardView_forth.setOnKeyboardActionListener(this);
		
		mTopKeyBoardView_first.setKeyboard(mTopKeyBoard_first);
		mTopKeyBoardView_second.setKeyboard(mTopKeyBoard_second);
		mTopKeyBoardView_third.setKeyboard(mTopKeyBoard_third);
		
		mTopKeyBoardView_first.setOnKeyboardActionListener(this);
		mTopKeyBoardView_second.setOnKeyboardActionListener(this);
		mTopKeyBoardView_third.setOnKeyboardActionListener(this);
	}
	
	public void initLandLayout()
	{
		mMainView = (LinearLayout)LinearLayout.inflate(mContext,
                R.layout.input_land, null);
		mKeyBoardView = mMainView.findViewById(R.id.keyboardview);
		mTopLastPage = (Button)mMainView.findViewById(R.id.character_left);
		mTopNextPage = (Button)mMainView.findViewById(R.id.character_right);
		mKeyBoardLastPage = (Button)mMainView.findViewById(R.id.last_page);
		mKeyBoardNextpage = (Button)mMainView.findViewById(R.id.next_page);
		mHideBtn = (Button)mMainView.findViewById(R.id.btn_hide);
		
		mTopKeyBoardViewGroup = (eebbkDragViews) mMainView.findViewById(R.id.top_keyboard_group);
		mKeyBoardViewGroup = (eebbkDragViews) mMainView.findViewById(R.id.keyboard_group);
//		LayoutParams lp = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
//		lp.topMargin = 20;
//		mKeyBoardViewGroup.setLayoutParams(lp);
		//初始化键盘
		mKeyBoardView_first = (eebbkKeyBoardView) mMainView.findViewById(R.id.keyboard_first);
		mKeyBoardView_second = (eebbkKeyBoardView) mMainView.findViewById(R.id.keyboard_second);
		mKeyBoardView_third = (eebbkKeyBoardView) mMainView.findViewById(R.id.keyboard_third);
		//			mKeyBoardView_forth = (eebbkKeyBoardView) mMainView.findViewById(R.id.keyboard_forth);
		
		mTopKeyBoardView_first = (eebbkKeyBoardView) mMainView.findViewById(R.id.top_keyboard_first);
		mTopKeyBoardView_second = (eebbkKeyBoardView) mMainView.findViewById(R.id.top_keyboard_second);
		mTopKeyBoardView_third = (eebbkKeyBoardView)mMainView.findViewById(R.id.top_keyboard_third);
		
		mKeyBoardView_first.setKeyboard(mKeyBoard_first);
		mKeyBoardView_second.setKeyboard(mKeyBoard_second);
		mKeyBoardView_third.setKeyboard(mKeyBoard_third);
//		mKeyBoardView_forth.setKeyboard(mKeyBoard_forth);
		
		mKeyBoardView_first.setOnKeyboardActionListener(this);
		mKeyBoardView_second.setOnKeyboardActionListener(this);
		mKeyBoardView_third.setOnKeyboardActionListener(this);
//		mKeyBoardView_forth.setOnKeyboardActionListener(this);
		
		mTopKeyBoardView_first.setKeyboard(mTopKeyBoard_first);
		mTopKeyBoardView_second.setKeyboard(mTopKeyBoard_second);
		mTopKeyBoardView_third.setKeyboard(mTopKeyBoard_third);
		
		mTopKeyBoardView_first.setOnKeyboardActionListener(this);
		mTopKeyBoardView_second.setOnKeyboardActionListener(this);
		mTopKeyBoardView_third.setOnKeyboardActionListener(this);
	}
	
	public void initListener()
	{
		mTopLastPage.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				mTopKeyBoardViewGroup.snapToLastScreen();
				updateGroupTop();
			}
		});
		
		mTopNextPage.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				mTopKeyBoardViewGroup.snapToNextScreen();
				updateGroupTop();
				
			}
		});
		
		mKeyBoardLastPage.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				mKeyBoardViewGroup.snapToLastScreen();
				updateGroupKeyBoard();
			}
		});
		
		mKeyBoardNextpage.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				mKeyBoardViewGroup.snapToNextScreen();
				updateGroupKeyBoard();
			}
		});
		
		mHideBtn.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				hideKeyBoard(mKeyBoardView.isShown());
			}
		});
		
		
		
		mKeyBoardViewGroup.SetOnViewChangeListener(new OnViewChangeListener()
		{
			@Override
			public void OnViewChange(int view)
			{
				updateGroupKeyBoard();
			}
		});
		
		mTopKeyBoardViewGroup.SetOnViewChangeListener(new OnViewChangeListener()
		{
			@Override
			public void OnViewChange(int view)
			{
				updateGroupTop();
			}
		});
	}
	
	public void init()
	{
			if(isLandScape())
			{
				initLandLayout();
			}
			else
			{
				initPortLayout();
			}
			initListener();
			
		}

	/**
	* Description:隐藏键盘
	*/
	protected void hideKeyBoard(boolean bIsHide)
	{
		mKeyBoardView.isShown();
		if(bIsHide)
		{
//			Animation animation;
//			animation = AnimationUtils.loadAnimation(mContext, R.anim.keyboard_close );
//			animation.setDuration(1000);
//			mKeyBoardView.startAnimation( animation );
			mKeyBoardView.setVisibility(View.GONE);
			mHideBtn.setBackgroundResource(R.drawable.btn_show);
			if(isLandScape())
			{
				mHideBtn.setBackgroundResource(R.drawable.btn_show_land);
			}
		}
		else
		{
			mKeyBoardView.setVisibility(View.VISIBLE);
			mHideBtn.setBackgroundResource(R.drawable.btn_hide);
			if(isLandScape())
			{
				mHideBtn.setBackgroundResource(R.drawable.btn_hide_land);
			}
		}
	}

	public void onInitializeInterface()
	{
		if (!isLandScape())
		{
			mKeyBoard_first = new eebbkKeyboard(mContext, R.xml.character_first);
			mKeyBoard_second = new eebbkKeyboard(mContext,
					R.xml.character_second);
			mKeyBoard_third = new eebbkKeyboard(mContext, R.xml.character_third);

			mTopKeyBoard_first = new eebbkKeyboard(mContext,
					R.xml.top_character_first);
			mTopKeyBoard_second = new eebbkKeyboard(mContext,
					R.xml.top_character_second);
			mTopKeyBoard_third = new eebbkKeyboard(mContext,
					R.xml.top_character_third);
			// mTopKeyBoard_third = new
			// JdingKeyboard(this,R.xml.top_character_first);
			// mTopKeyBoard_forth = new
			// JdingKeyboard(this,R.xml.top_character_first);
		}
		else
		{
			mKeyBoard_first = new eebbkKeyboard(mContext,
					R.xml.character_first_land);
			mKeyBoard_second = new eebbkKeyboard(mContext,
					R.xml.character_second_land);
			mKeyBoard_third = new eebbkKeyboard(mContext,
					R.xml.character_third_land);
			// mKeyBoard_forth = new
			// eebbkKeyboard(mContext,com.android.internal.R.xml.character_forth);^M
			mTopKeyBoard_first = new eebbkKeyboard(mContext,
					R.xml.top_character_first_land);
			mTopKeyBoard_second = new eebbkKeyboard(mContext,
					R.xml.top_character_second_land);
			mTopKeyBoard_third = new eebbkKeyboard(mContext,
					R.xml.top_character_third_land);
		}
	}
	
	public View getKeyBoardView()
	{
		return mMainView;
	}
	
	@Override
	public void onPress(int primaryCode)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onRelease(int primaryCode)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onKey(int primaryCode, int[] keyCodes)
	{
		if(mInputConnection != null)
		{
			System.out.println("primaryCode:" + primaryCode);
		}
		if(primaryCode < 65535)
		{
			handleCharacter(primaryCode, keyCodes);
		}
		else
		{
			handleBBkcharacter(primaryCode,keyCodes);
		}
	}

	@Override
	public void onText(CharSequence text)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void swipeLeft()
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void swipeRight()
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void swipeDown()
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void swipeUp()
	{
		// TODO Auto-generated method stub
		
	}
	
	 private void handleCharacter(int primaryCode, int[] keyCodes)
	 {
	           mInputConnection.commitText(
	                    String.valueOf((char) primaryCode), 1);
	 }	
	 
	 /**
		* Description:自造字
		@param primaryCode
		@param keyCodes
		*/
		private void handleBBkcharacter(int primaryCode, int[] keyCodes)
		{
			try
			{
				mInputConnection.commitText(
						eebbkKeyBoardParse.getCharacterString(primaryCode),1);
			}
			catch (Exception e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	private void updateGroupTop()
	{
		if(mTopKeyBoardViewGroup.getViewIndex() == 0)
		{
			mTopLastPage.setEnabled(false);
		}
		else if(mTopKeyBoardViewGroup.getViewIndex() == (TOP_VIEW_COUNT - 1))
		{
			mTopNextPage.setEnabled(false);
		}
		else
		{
			mTopLastPage.setEnabled(true);
			mTopNextPage.setEnabled(true);
			
		}
	}
	
	private void updateGroupKeyBoard()
	{
		if(mKeyBoardViewGroup.getViewIndex() == 0)
		{
			mKeyBoardLastPage.setEnabled(false);
		}
		else if(mKeyBoardViewGroup.getViewIndex() == (KEY_VIEW_COUNT - 1))
		{
			mKeyBoardNextpage.setEnabled(false);
		}
		else
		{
			mKeyBoardLastPage.setEnabled(true);
			mKeyBoardNextpage.setEnabled(true);
			
		}
	}
	
	public boolean isLandScape()
	 {
	     if(mContext == null)
	         return false;
	     Configuration config = mContext.getResources().getConfiguration();
	     if (config.orientation != Configuration.ORIENTATION_LANDSCAPE) {
	         return false;
	     }
	     return true;
	 }
}
