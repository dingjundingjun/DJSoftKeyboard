package com.dingj.djsoftkeyboard.test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import jding.debug.JDingDebug;

import android.content.Context;

import com.dingj.djsoftkeyboard.util.IniEditor;

public class TestIni
{
	private IniEditor mIniEditor;
	private Context mContext;
	private static String TAG = "TestIni";
	public TestIni(Context c)
	{
		mContext = c;
	}
	
	public void init()
	{
		mIniEditor = new IniEditor();
		InputStream is = null;
		try
		{
			is = mContext.getResources().getAssets().open("en_26.ini");
		} catch (IOException e)
		{
			e.printStackTrace();
		}
		if(is != null)
		{
			initFile(is);
		}
		showData();
	}
	
	private void initFile(InputStream is)
	{
		try
		{
			mIniEditor.load(is);
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	private void showData()
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
				JDingDebug.printfD(TAG, "section=>" + tempSection + " option=>" + tempOption + " key=>" + mIniEditor.get(tempSection, tempOption));
			}
			
		}
	}
}
