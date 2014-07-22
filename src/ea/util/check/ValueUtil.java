package ea.util.check;

import java.util.List;

/**
 * @Description: 值工具类
 * @author: ethanchiu@126.com
 * @date: 2013-7-26
 */
public class ValueUtil {

	/**
	 * @Description: 判断List不为为空
	 * @author: ethanchiu@126.com
	 * @date: Dec 23, 2013
	 * @param noteList
	 * @return
	 */
	public static boolean isListNotEmpty(List<?> noteList) {
		return null != noteList && noteList.size() > 0;
	}
	
	/**
	 * @Description: 判断List为空
	 * @author: ethanchiu@126.com
	 * @date: Dec 23, 2013
	 * @param noteList
	 * @return
	 */
	public static boolean isListEmpty(List<?> noteList) {
		return null == noteList || noteList.size() == 0;
	}
	
	/**
	 * @Description: 判断字符串不为空
	 * @author: ethanchiu@126.com
	 * @date: Dec 23, 2013
	 * @param str
	 * @return
	 */
	public static boolean isStrNotEmpty(String str) {
        if (str == null || str.length() == 0)
            return false;
        else
            return true;
	}
	
	public static boolean isStrEmpty(String value) {
		return !isStrNotEmpty(value);
	}
	
}