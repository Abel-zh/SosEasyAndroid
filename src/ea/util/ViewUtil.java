package ea.util;

import android.app.Activity;
import android.app.Application;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Gallery;
import android.widget.ListView;
import android.widget.TextView;
import ea.globe.FrameApp;
import ea.util.system.DisplayUtil;

/**
 * @Description: view工具类
 * @author: ethan.qiu@sosino.com
 * @date: 2013-7-31
 */
public class ViewUtil {

	//<<<<<<<<<<<<<<<<<<<<<<<<<< view 构建相关操作 begin <<<<<<<<<<<<<<

	private static LayoutInflater inflater;

	/**
	 * 设置view的可见状态
	 * 
	 * @version 创建时间：2013-3-20 上午10:24:14
	 * @param view
	 * @param viewVisibleState
	 */
	public static void setViewVisible(View view, int viewVisibleState) {
		if (null == view) {
			return;
		}
		if (view.getVisibility() != viewVisibleState) {
			view.setVisibility(viewVisibleState);
		}
	}
	
	public static View buildView(int layout) {
		return buildView(layout, null);
	}
	
	public static View buildView(int resource, ViewGroup root) {
		return buildView(resource, root, root != null);
	}
	
	/**
	 * 
	 * @Description: 创建视图
	 * @author: ethan.qiu@sosino.com
	 * @date: 2013-8-7
	 * @param resource ID for an XML layout resource to load (e.g., R.layout.main_page)
	 * @param root
	 * @param attachToRoot
	 * @return
	 */
	public static View buildView(int resource, ViewGroup root, boolean attachToRoot) {
		return getInflater().inflate(resource, root, attachToRoot);
	}


	/**
	 * 创建一个listview里的view,并且可以设置高度
	 * 
	 * @author carlos E-mail:carloscyy@gmail.com
	 * @version 创建时间：2011-11-17 上午11:07:06
	 * @param layoutId
	 * @param heightDip
	 * @return
	 */
	public static View buildListViewChild(int layoutId, int heightDip) {
		View view = buildView(layoutId);
		if (heightDip > 0) {
			int height = DisplayUtil.dip2px(heightDip);
			ListView.LayoutParams lp = new ListView.LayoutParams(
					ListView.LayoutParams.FILL_PARENT, height);
			view.setLayoutParams(lp);
		}
		return view;
	}

	/**
	 * 创建一个listview里的view,并且可以设置高度
	 * 
	 * @author carlos E-mail:carloscyy@gmail.com
	 * @version 创建时间：2011-11-17 上午11:07:06
	 * @param layoutId
	 * @param heightDip
	 * @return
	 */
	public static View buildListViewChildByPxHeight(int layoutId, int heightPx) {
		View view = buildView(layoutId);
		ListView.LayoutParams lp = new ListView.LayoutParams(
				ListView.LayoutParams.FILL_PARENT, heightPx);
		view.setLayoutParams(lp);
		return view;
	}

	/**
	 * 
	 * @version 创建时间：2011-10-8 下午4:40:36
	 * @return
	 */
	private static LayoutInflater getInflater() {
		if (null == inflater) {
			inflater = LayoutInflater.from(getApp());
		}
		return inflater;
	}



	/**
	 * 根据demin的resId来设置尺度
	 * 
	 * 
	 * @version 创建时间：2012-8-7 下午5:35:13
	 * @param bookrackItem
	 * @param dimenResId
	 * @return
	 */
	public static View buildGalleryChildByDimen(int layoutId, int dimenResId) {
		if (dimenResId > 0) {
			int px = (int) getApp().getResources()
					.getDimension(dimenResId);
			return buildGalleryChildByPxHeight(layoutId, px);

		} else {
			return buildView(layoutId);
		}
	}

	/**
	 * 
	 */
	private static View buildGalleryChildByPxHeight(int layoutId, int heightPx) {
		View view = buildView(layoutId);
		Gallery.LayoutParams lp = new Gallery.LayoutParams(
				Gallery.LayoutParams.FILL_PARENT, heightPx);
		view.setLayoutParams(lp);
		return view;
	}

	/**
	 * 根据demin的resId来设置尺度
	 * 
	 */
	public static View buildListViewChildByDimen(int layoutId, int dimenResId) {
		int px = (int) getApp().getResources().getDimension(dimenResId);
		return buildListViewChildByPxHeight(layoutId, px);
	}


	//>>>>>>>>>>>>>>>>>>>>>>>  view 构建相关操作 end >>>>>>>>>>>>>>>>>
	//<<<<<<<<<<<<<<<<<<<<<<<<<< view 值相关操作 begin <<<<<<<<<<<<<<
		/**
		 * 判断在多个EditText或者TextView的内容中有一个为空就返回true
		 * 
		 * @param ets
		 * @return
		 */
		public static boolean isHasEmptyView(View... views) {
			for (View v : views) {
				if (!v.isShown()) {// 不可见的不做判断
					continue;
				}
				if (v instanceof EditText) {
					EditText et = (EditText) v;
					if (TextUtils.isEmpty(et.getText().toString().trim())) {
						return true;
					}
				} else if (v instanceof TextView) {
					TextView tv = (TextView) v;
					if (TextUtils.isEmpty(tv.getText().toString().trim())) {
						return true;
					}
				}
			}
			return false;
		}

		/**
		 * 获得EditText 或者 TextView的内容
		 * 
		 * @param et
		 * @return
		 */
		public static String getViewText(View v) {
			String str = "";
			if (v != null) {
				if (v instanceof EditText) {
					String tempStr = ((EditText) v).getText().toString().trim();
					str = TextUtils.isEmpty(tempStr) ? "" : tempStr;
				} else if (v instanceof TextView) {
					String tempStr = ((TextView) v).getText().toString().trim();
					str = TextUtils.isEmpty(tempStr) ? "" : tempStr;
				}
			}
			return str;
		}

		/**
		 * 给textView赋值
		 * 
		 */
		public static void setText(Activity activity, int resId, String content) {
			View tvText = activity.findViewById(resId);
			if (null != tvText && (tvText instanceof TextView)) {
				((TextView) tvText).setText(content);
			}
		}
		
		public static Application getApp(){
			return FrameApp.getApp();
		}

		//>>>>>>>>>>>>>>>>>>>>>>>  view 值相关操作 end>>>>>>>>>>>>>>>>>
}