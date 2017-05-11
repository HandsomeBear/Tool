package HHH.HLog;

public interface ILog {
	
	
	
	
	public void debug(String msg);
	
	
	/**
	 *函数注释：info()<br>
	 *作者：何明辉<br>
	 *时间：2014-5-20-上午10:19:12<br>
	 *类型：方法<br>
	 *用途：该方法用于打印普通消息<br>
	 *备注：***<br> 
	 *@param msg
	 */
	public void info(String msg);
	
	
	
	/**
	 *函数注释：warnning()<br>
	 *作者：何明辉<br>
	 *时间：2014-5-20-上午10:19:42<br>
	 *类型：方法<br>
	 *用途：该方法用于打印警告信息<br>
	 *备注：***<br> 
	 *@param msg
	 */
	public void warnning(String msg);
	
	
	
	/**
	 *函数注释：error()<br>
	 *作者：何明辉<br>
	 *时间：2014-5-20-上午10:19:58<br>
	 *类型：方法<br>
	 *用途：该方法用于打印错误信息<br>
	 *备注：***<br> 
	 *@param msg
	 */
	public void error(String msg);
}
