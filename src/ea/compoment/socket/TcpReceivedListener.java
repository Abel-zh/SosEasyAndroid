package ea.compoment.socket;
/**
 * @Description: Declare the interface. The method messageReceived(String message) will must be implemented in the MyActivity class at on asynckTask doInBackground
 * @author: ethanchiu@126.com
 * @date: Apr 2, 2014
 */
public interface TcpReceivedListener {
	void messageReceived(String message);
}
