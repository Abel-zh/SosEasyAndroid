package ea.util;

import android.content.res.Resources;
import ea.globe.FrameApp;

/**
 * @Description: 系统资源工具类
 * @author: ethanchiu@126.com
 * @date: 2013-7-31
 */
public class ResUtil {
	
	/**
	 * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
	 */
	public static int dip2px(float dpValue) {
		final float scale = getResources()
				.getDisplayMetrics().density;
		int result = (int) (dpValue * scale + 0.5f);
		return result;

	}
	
	/**
	 * @Description: 获得像素
	 * @author: ethanchiu@126.com
	 * @date: Dec 15, 2013
	 * @param resId
	 * @return
	 */
	public static float getDimension(int resId){
		return dip2px(getResources().getDimension(resId));
	}
	
	/**
	 * @Description: 获得像素
	 * @author: ethanchiu@126.com
	 * @date: Dec 15, 2013
	 * @param resId
	 * @return
	 */
	public static int getDimensionInt(int resId){
		return (int)dip2px(getResources().getDimension(resId));
	}
	
	public static String getString(int resId){
		return getResources().getString(resId);
	}
	
	public static int getColor(int resId){
		return getResources().getColor(resId);
	}
	
	/**
	 * @Description: 获得Resources
	 * @author: ethanchiu@126.com
	 * @date: Dec 15, 2013
	 * @return
	 */
	public static Resources getResources(){
		return FrameApp.getApp().getResources();
	}
	
}
