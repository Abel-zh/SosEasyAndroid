package ea.util.system;

import android.util.DisplayMetrics;
import ea.compoment.log.LogUtil;
import ea.globe.FrameApp;

/**
 * @Description: 测试手机屏幕
 * @author: ethanchiu@126.com
 * @date: 2013-8-27
 */
public class DisplayUtil {
	
	public static DisplayMetrics getMetrics(){
		
//第一种方式	
		DisplayMetrics metric = FrameApp.getApp().getResources().getDisplayMetrics();
//第二种方式
//		DisplayMetrics metric = new DisplayMetrics();
//		ac.getWindowManager().getDefaultDisplay().getMetrics(metric);
		return metric;
	}
	
	/**
	 * @Description: 显示手机屏幕信息
	 * @author: ethanchiu@126.com
	 * @date: Oct 8, 2013
	 * @param ac
	 */
	public static void calcDPI(){
		DisplayMetrics metric = getMetrics();
		
		int width = metric.widthPixels;  // 屏幕宽度（像素）
		int height = metric.heightPixels;  // 屏幕高度（像素）
		float density = metric.density;  // 屏幕密度（0.75 / 1.0 / 1.5）
		int densityDpi = metric.densityDpi;  // 屏幕密度DPI（120 / 160 / 240）
		
		LogUtil.d(DisplayUtil.class, 
				"屏幕宽度（像素）-->" + width +
				"\n 屏幕高度（像素）-->" + height + 
				"\n 屏幕宽度（dp）-->" + px2dip(width) + 
				"\n 屏幕高度（dp）-->" + px2dip(height) + 
				"\n 屏幕密度（0.75 / 1.0 / 1.5）-->" + density +
				"\n 屏幕密度DPI（120 / 160 / 240） -->" + densityDpi
				);
	}
	
	/** 
	 * 将px值转换为dp值，保证尺寸大小不变 
	 *  
	 * @param dipValue 
	 * @param scale 
	 *            （DisplayMetrics类中属性density） 
	 * @return 
	 */  
	public static int px2dip(float pxValue) {  
		final float scale = getMetrics().density;  
		int dip = (int) (pxValue / scale + 0.5f);  
		LogUtil.d(DisplayUtil.class, "px2dip-->" + dip);
		return dip;
	}  

	/** 
	 * 将dip或dp值转换为px值，保证尺寸大小不变 
	 *  
	 * @param dipValue 
	 * @param scale 
	 *            （DisplayMetrics类中属性density） 
	 * @return 
	 */  
	public static int dip2px(float dipValue) {  
		final float scale = getMetrics().density;  
		int px = (int) (dipValue * scale + 0.5f);  
		return px;
	}  

	/** 
	 * 将px值转换为sp值，保证文字大小不变 
	 *  
	 * @param pxValue 
	 * @param fontScale 
	 *            （DisplayMetrics类中属性scaledDensity） 
	 * @return 
	 */  
	public static int px2sp(float pxValue) {  
		final float fontScale = getMetrics().scaledDensity;  
		int sp = (int) (pxValue / fontScale + 0.5f);
		LogUtil.d(DisplayUtil.class, "px2sp-->" + sp);
		return sp;
	}  

	/** 
	 * 将sp值转换为px值，保证文字大小不变 
	 *  
	 * @param spValue 
	 * @param fontScale 
	 *            （DisplayMetrics类中属性scaledDensity） 
	 * @return 
	 */  
	public static int sp2px(float spValue) {  
		final float fontScale = getMetrics().scaledDensity;  
		int px = (int) (spValue * fontScale + 0.5f);
		LogUtil.d(DisplayUtil.class, "sp2px-->" + px);
		return px;
	}  
	
}
