package ea.util;

import android.os.Handler;
import android.os.Message;

/**
 * @Description: 异步工作
 * @author: ethanchiu@126.com
 * @date: Mar 28, 2014
 */
public class AsyncWork<Result> {
	
	public interface SyncTask<Result> {
		Result work();
		void onComplete(Result result);
	}
	
	private class EventHandler extends Handler {
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case ID_COMPLETE:
				if (!mCancel) {
					@SuppressWarnings("unchecked")
					Result r = (Result) msg.obj;
					
					mTask.onComplete(r);
				}
				break;
			}
		}
	}
	
	private static final int ID_COMPLETE = 0x01;
	private EventHandler mEventHandler = new EventHandler();
	private SyncTask<Result> mTask = null;
	private boolean mCancel = false;
	
	public AsyncWork(SyncTask<Result> task) {
		mTask = task;
		if (null == task) {
			throw new IllegalArgumentException("");
		}
	}
	
	/**
	 * 开始工作
	 */
	public void start() {
		mCancel = false;
		new Thread(new Runnable() {
			@Override
			public void run() {
				Result r = mTask.work();
				Message msg = new Message();
				msg.what = ID_COMPLETE;
				msg.obj = r;
				mEventHandler.sendMessage(msg);
			}
		}).start();
	}
	
	/**
	 * 取消
	 */
	public void cancel() {
		mCancel = true;
	}
}
