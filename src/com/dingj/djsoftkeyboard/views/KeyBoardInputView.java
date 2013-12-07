package com.dingj.djsoftkeyboard.views;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

/**
 * @author dingj
 * 该类需要通过解析ini来加载键盘
 */
public class KeyBoardInputView extends RelativeLayout
{
    private Context mContext = null;
    /**ini键盘布局名称*/
    private String mIniFileName = null;
    /**键盘类型*/
    public int mImeType = -1; 
	
    
    public KeyBoardInputView(Context context)
	{
		super(context);
	}

	public KeyBoardInputView(Context context, AttributeSet attrs)
	{
		super(context, attrs);
	}
	
	/**
	 * 设置ini配置文件
	 * @param fileName
	 */
	public void setInitFileName(String fileName)
	{
		this.mIniFileName = fileName;
	}
	
	/**
	 * 根据当前的ini配置，生成键盘
	 */
	public void updateImeLayout()
	{
		
	}

	/**
	 * 设置当前键盘的类型
	 * @param type
	 */
	public void initKeyBoardView(int type)
	{
		mImeType = type;
	}
	
	/**
	 * 根据类型去生成键盘
	 * @param type
	 */
	public void createKeyBoardView()
	{
		
	}
}
