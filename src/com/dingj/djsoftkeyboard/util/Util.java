package com.dingj.djsoftkeyboard.util;

import java.io.IOException;
import java.io.InputStream;

import jding.debug.JDingDebug;
import android.content.Context;
import android.content.res.Configuration;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

/**
 * 工具类
 * @author dingj
 *
 */
public class Util 
{
	/**屏幕宽度*/
	public static int DISPLAY_WIDTH;
	/**屏幕高度*/
	public static int DISPLAY_HEIGHT;
	/**主题文件名称*/
	public static String THEME = "themes.ini";
	/**
	 * 判断是横屏还是竖屏
	 * @param context
	 * @return true:横屏 false:竖屏
	 */
	public static boolean isLandScape(Context context) 
	{
		Configuration config = context.getResources().getConfiguration();
		if (config.orientation != Configuration.ORIENTATION_LANDSCAPE) 
		{
			return false;
		}
		return true;
	}
	
	/**
	 * 获取屏幕分辨率
	 * @param context
	 */
	public static void getDisplaySize(Context context)
	{
		Display display = ((WindowManager)context.getApplicationContext().getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
		DISPLAY_WIDTH = display.getWidth();
		DISPLAY_HEIGHT = display.getHeight();
		JDingDebug.printfD("Util", "w=" + DISPLAY_WIDTH + " h=" + DISPLAY_HEIGHT);
	}
	
	/**
	 * 分割出字符串
	 * @return
	 */
	public static String[] getSplit(String str,String sp)
	{
		String[] rString = str.split(sp);
		return rString;
	}
	
	public static IniEditor openAssetPortInit(Context context,String name)
	{
		IniEditor iniEditor = new IniEditor();
		InputStream is = null;
		try
		{
			is = context.getResources().getAssets().open("port/" + name);
		} catch (IOException e)
		{
			e.printStackTrace();
		}
		if(is != null)
		{
			try
			{
				iniEditor.load(is);
			} catch (IOException e)
			{
				e.printStackTrace();
			}
		}
		return iniEditor;
	}
	
	/**各个键盘对应的路径*/
	public static String KeyBoardIniFiles[] = {"en_26.ini"};
	/**英文26键盘index*/
	public static int KEYBOARD_TYPE_ENGLISH26 = 0;    
}
