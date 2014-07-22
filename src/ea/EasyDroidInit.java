package ea;

import android.app.Application;
import ea.globe.FrameApp;

/**
 * @Description: 进入程序时配置
 * @author: ethanchiu@126.com
 * @date: 2014年7月17日
 */
public class EasyDroidInit {
	
	/**
	 * @Description: 为框架提供项目的Application
	 * @author: ethanchiu@126.com
	 * @date: 2014年7月18日
	 * @param app
	 */
	public static void init(Application app){
		FrameApp.setApp(app);
	}
	
}
