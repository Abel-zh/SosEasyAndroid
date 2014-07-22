package ea.util.system;

import java.util.List;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ResolveInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import ea.compoment.log.LogUtil;
import ea.globe.FrameApp;
import ea.util.check.StringUtil;
import ea.util.check.ValueUtil;

/**
 * @Description: 
 * 	设备助手类
 *  方案说明： Wifi Mac地址+ Device ID (IMEI/MEID) + Android ID
 * @author: ethanchiu@126.com
 * @date: 2013-8-12
 */
public class DeviceUtil {

	private static String TAG = DeviceUtil.class.getSimpleName();

	/**
	 * @Description: 获得设备的唯一标示符
	 * @author: ethanchiu@126.com
	 * @date: Dec 23, 2013
	 * @return
	 */
	public static String getDeviceUniqueId(){
		String ret = StringUtil.mergeMutableArgs("|",
				"NAME:" + getDeviceModel(),
				"DEVICE_ID:" + getDeviceId(),
				"MAC:" + getMacAddress(),
				"ANDROID_ID:" + getAndroidId(),
				"IMSI:" + getIMSI(),
				"IMEI:" + getDeviceId());
		LogUtil.d(TAG, "getDeviceUniqueId(): " + ret);
		return ret;
	}

	/**
	 * @Description: 获取设备绑定唯一标识符
	 * @author: ethanchiu@126.com
	 * @date: Dec 23, 2013
	 * @return
	 */
	public static String getDeviceBindUniqueId() {
		return "SYS_TYPE:1|" + getDeviceUniqueId();
	}

	/**
	 * @Description: 获得设备的Wifi Mac地址(开启Wi-Fi热点时为null)
	 * @author: ethanchiu@126.com
	 * @date: Dec 23, 2013
	 * @return
	 */
	private static String getMacAddress() {
		WifiManager wifi = (WifiManager) getApp().getSystemService(Context.WIFI_SERVICE); 
		WifiInfo info = wifi.getConnectionInfo(); 

		String ret = info.getMacAddress();

		if (ret == null) {
			ret = "";
		}

		return ret;
	}

	/**
	 * @Description: GSM卡为IMEI, CDMA卡为MEID
	 * @author: ethanchiu@126.com
	 * @date: Dec 23, 2013
	 * @return
	 */
	public static  String getDeviceId(){
		String imei_meid = null;
		try {
			TelephonyManager mTelephonyMgr = (TelephonyManager) getApp().getSystemService(Context.TELEPHONY_SERVICE);
			imei_meid = mTelephonyMgr.getDeviceId();
		} catch (Exception e) {
			e.printStackTrace();
			imei_meid= "";
		}
		return imei_meid;
	}

	/**
	 * @Description: 获得Android ID
	 * @author: ethanchiu@126.com
	 * @date: Dec 23, 2013
	 * @return
	 */
	private static String getAndroidId() {
		String ANDROID_ID = Settings.System.getString( getApp().getContentResolver(), Settings.System.ANDROID_ID);
		return ANDROID_ID;
	}

	/**
	 * @Description: 获取IMSI地址，如果在飞行模式的时候是无法获取的。
	 * @author: ethanchiu@126.com
	 * @date: Dec 23, 2013
	 * @return
	 */
	private static  String getIMSI(){
		String imsi;
		try {
			TelephonyManager mTelephonyMgr = (TelephonyManager) getApp().getSystemService(Context.TELEPHONY_SERVICE);
			imsi = mTelephonyMgr.getSubscriberId();
		} catch (Exception e) {
			e.printStackTrace();
			imsi= "";
		}
		return imsi;
	}

	/**
	 * @Description: 获取设备的型号
	 * @author: ethanchiu@126.com
	 * @date: Dec 23, 2013
	 * @return
	 */
	public static String getDeviceModel() {
		String deviceModel = Build.MODEL;
		LogUtil.v(TAG, "Device model: " + deviceModel);
		return deviceModel;
	}

	/**
	 * @Description: 获取设备的厂商
	 * @author: ethanchiu@126.com
	 * @date: Dec 23, 2013
	 * @return
	 */
	public static String getDeviceBrand() {
		String deviceBrand = Build.BRAND;
		LogUtil.v(TAG, "Device model: " + deviceBrand);
		return deviceBrand;
	}

	/**
	 * @Description: getAppActivityInfo:获取指定类名的应用程序的安装信息.
	 * @author: ethanchiu@126.com
	 * @date: Dec 23, 2013
	 * @param ctx
	 * @param cls 完整的包名+类名
	 * @since JDK 1.6
	 * @return
	 */
	public static ActivityInfo getAppActivityInfo(Context ctx, String cls){
		if (ValueUtil.isStrEmpty(cls)) {
			return null;
		}
		List<ResolveInfo> apps = getAppListFromPackageManager(ctx);
		for (ResolveInfo info : apps) {
			if (info == null) {
				continue;
			}
			String clsName = info.activityInfo.name;
			if (cls.equals(clsName)) {
				return info.activityInfo;
			}
		}
		return null;
	}


	/**
	 * @Description: 获取手机应用包名 信息
	 * @author: ethanchiu@126.com
	 * @date: Dec 23, 2013
	 * @param context
	 * @return
	 */
	private static List<ResolveInfo> getAppListFromPackageManager(Context context) {
		final Intent mainIntent = new Intent(Intent.ACTION_MAIN, null);
		mainIntent.addCategory(Intent.CATEGORY_LAUNCHER);
		final PackageManager packageManager = context.getPackageManager();
		return packageManager.queryIntentActivities(mainIntent, 0);
	}
	
	/**
	 * add by Abel 2014-05-29 <br/>
	 * add getVersionCode() 2014-06-11
	 */
	/**
	 * 
	 * @Method: hideSoftInput
	 * @Description: 隐藏键盘
	 * @param view
	 */
	public static void hideSoftInput(View view) {
		InputMethodManager imm = (InputMethodManager) getApp().getSystemService(Context.INPUT_METHOD_SERVICE);
		imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
	}

	/**
	 * 
	 * @Method: getVersionCode
	 * @Description: 获取版本号
	 * @return
	 * @return int
	 */
	public static int getVersionCode() {
		int versionCode = 0;
		try {
			versionCode = getApp().getPackageManager().getPackageInfo(getApp().getPackageName(), 0).versionCode;
		} catch (NameNotFoundException e) {
			LogUtil.e("MainActivity", "获取应用程序版本号错误");
		}
		return versionCode;
	}
	
	public static Application getApp(){
		return FrameApp.getApp();
	}
	
	/**
	 * end
	 */
	
}
