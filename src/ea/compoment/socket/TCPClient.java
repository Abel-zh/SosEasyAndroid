package ea.compoment.socket;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

import android.util.Log;

/**
 * @Description: Handle the TCPClient with Socket Server.
 * @author: ethanchiu@126.com
 * @date: Apr 2, 2014
 */
public class TCPClient {

	public static final String TAG_SOCKET = "tag_socket";
	public static final String TAG = TCPClient.class.getSimpleName();

	private String serverMessage;

	public static String SERVERIP;
	public static int SERVERPORT;
	private TcpReceivedListener mMessageListener = null;
	private boolean mRun = false;

	private DataOutputStream out = null;
	private DataInputStream in = null;

	public static void setServerip(String serverIp) {
		SERVERIP = serverIp;
	}

	public static void setServerport(int serverPort) {
		SERVERPORT = serverPort;
	}

	/**
	 * Constructor of the class. OnMessagedReceived listens for the messages
	 * received from server
	 */
	public TCPClient(final TcpReceivedListener listener) {
		mMessageListener = listener;
		Log.d(TAG, "TCPClient create");
	}

	private InvokeListener mInvokeListener;

	public void setInvokeListener(InvokeListener invokeListener) {
		this.mInvokeListener = invokeListener;
	}

	/**
	 * Sends the message entered by client to the server
	 * 
	 * @param message
	 *            text entered by client
	 */
	public synchronized void sendMessage(String message) {

		Log.d(TAG_SOCKET, "request -->" + message);

		if (out != null) {
			try {
				out.writeInt(message.getBytes("UTF-8").length);
				out.write(message.getBytes("UTF-8"));
				out.flush();

				// 放置节奏太快导致TCP连接关闭
				Thread.sleep(500);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * @Description: TODO
	 * @author: ethanchiu@126.com
	 * @date: Apr 2, 2014
	 */
	public void stopTcp() {
		isRestart = false;
		mRun = false;
	}

	private Socket socket;

	boolean isRestart = false;// 是否重新启动socket

	public boolean isClosed() {
		if (socket != null) {
			return socket.isClosed();
		} else {
			return true;
		}

	}

	/**
	 * @Description: 发送
	 * @author: ethanchiu@126.com
	 * @date: Apr 2, 2014
	 */
	public void startTcp() {
		mRun = true;

		try {
			InetAddress serverAddr = InetAddress.getByName(SERVERIP);

			Log.e(TAG_SOCKET, "=======socket.start");

			socket = new Socket(serverAddr, SERVERPORT);

			try {
				socket.setKeepAlive(true);
				out = new DataOutputStream(socket.getOutputStream());
				in = new DataInputStream(socket.getInputStream());

				while (mRun) {

					serverMessage = getInputString();

					Log.d(TAG_SOCKET, "response -->" + serverMessage);

					if (serverMessage != null && mMessageListener != null) {
						mMessageListener.messageReceived(serverMessage);
					}
					serverMessage = null;
				}
			} catch (Exception e) {
				isRestart = true;
				e.printStackTrace();
			} finally {
				Log.e(TAG_SOCKET, "=======socket.close");

				socket.close();

				if (isRestart) {

					// FIXME 保证注册成功的暂时方法
					new Thread(new Runnable() {
						@Override
						public void run() {
							try {
								Thread.sleep(1000);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
							if (mInvokeListener != null) {
								mInvokeListener.onRestart();
							}
						}
					}).start();

					startTcp();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @Description: 获得结果
	 * @author: ethanchiu@126.com
	 * @date: Apr 4, 2014
	 * @return
	 * @throws MySocketException
	 * @throws IOException 
	 */
	private String getInputString() throws MySocketException, IOException {

		String serverMessage = null;

		int length = in.readInt();

		if (length == -1) {
			throw new MySocketException("no readInt!");
		}

		byte[] array = new byte[length];
		int len = in.read(array);

		if (len == -1) {
			throw new MySocketException("no read!");
		}


		serverMessage = new String(array);

//		LogUtil.d(TAG_SOCKET, "response-->" + serverMessage);

		return serverMessage;



		//		InputStream in = null;
		//		
		//		int size = 0;
		//		byte[] buffer = new byte[1024];
		//		try {
		//			size = in.read(buffer);
		//
		//			if (size == -1) {
		//				throw new MySocketException();
		//			}
		//
		//			LogUtil.e(TAG_SOCKET, "read size-->" + size);
		//
		//			serverMessage = new String(buffer, 0, size, "UTF-8");
		//			return serverMessage;
		//
		//		} catch (UnsupportedEncodingException e) {
		//			e.printStackTrace();
		//		} catch (IOException e) {
		//			e.printStackTrace();
		//		}
		//		return null;
	}

}
