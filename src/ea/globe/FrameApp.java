package ea.globe;

import android.app.Application;

/**
 * @Description: app
 * @author: ethanchiu@126.com
 * @date: 2014年7月16日
 */
public class FrameApp{
	
	private static Application application;
	
	public static Application getApp(){
		return application;
	}
	
	public static void setApp(Application app){
		application =  app;
	}
	
}
