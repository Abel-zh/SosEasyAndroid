package ea.compoment.socket;
/**
 * @Description: 对于sokcet的处理类
 * @author: ethanchiu@126.com
 * @date: Apr 23, 2014
 */
public class MySocketException extends Exception
{
	private static final long serialVersionUID = 2383252677896671750L;

	public MySocketException(){
		super();
	}
	
	public MySocketException(String detailMessage){
		super(detailMessage);
	}
}
