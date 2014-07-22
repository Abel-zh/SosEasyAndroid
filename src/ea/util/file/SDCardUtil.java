package ea.util.file;

import java.io.File;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;

import android.os.Environment;

/**
 * @Description: SD卡工具类
 * @author: ethanchiu@126.com
 * @date: 2013-7-26
 */
public class SDCardUtil
{
    public static boolean isSdcardExist() {
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) { return true; }
        return false;
    }
    
    /**
     * 得到外部存储介质的路径，一般为/mnt/sdcard，如果不存在，则返回null[实际上重复检查，但是又必要]， 所以一般先调用
     * sdcardExist()方法判断如果存在sdcard，再调用该方法。
     */
    public static String getSdcardPath() {
        if (isSdcardExist()) { return Environment.getExternalStorageDirectory().getAbsolutePath(); }
        return null;
    }

    public static boolean checkSdcardAndMkdirs() {
        if (!isSdcardExist()) return false;
        String[] hikerDirs = new String[] {
                // HIKER_DB, HIKER_RESOURCE, HIKER_APK_STORE, HIKER_DCIM_PATH
                };

        File hikerDir = null;
        if (isEmpty(hikerDirs)) {
        	return false;
        }
        for (String dir : hikerDirs) {
            hikerDir = new File(dir);
            if (!hikerDir.exists() || !hikerDir.isDirectory()) {
                hikerDir.mkdirs();
            }
        }
        return true;
    }

    /**
     * 使用当前时间戳拼接一个唯一的文件名
     * 
     * @param format
     * @return
     */
    public static String generateFileNameByCurrentDate() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss_SS");
        String fileName = format.format(new Timestamp(System.currentTimeMillis()));
        return fileName;
    }
    
    /**
     * list非空检测
     * @param list
     * @return
     */
    public static boolean isEmpty(List<?> list){
    	return list == null || list.size() == 0;
    }
    
    /***
     * 数组非空检测
     * @param objs
     * @return
     */
    public static boolean isEmpty(Object[] objs){
    	return objs == null || objs.length ==  0;
    }
}
