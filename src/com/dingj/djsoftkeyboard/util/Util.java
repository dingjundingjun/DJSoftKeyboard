package com.dingj.djsoftkeyboard.util;

import android.content.Context;
import android.content.res.Configuration;

/**
 * 工具类
 * @author dingj
 *
 */
public class Util 
{
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
}
