package HHH.HLog;
import HHH.HMap.HSortedMap;
import HHH.HTASK.HTask;
import HHH.HUTIL.StringUtils;

//□----------------------□--------------------□--------------------□---------------------□---------------------□---------------------
//□ 包引入
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

//□----------------------□--------------------□--------------------□---------------------□---------------------□---------------------




/**
 * 类型注释：<br>
 *作者：何明辉<br>
 *时间：2014-5-20-上午9:51:08<br>
 *类型：Log<br>
 *用途：该类型用于日志记录和处理<br>
 *备注：<br>
 *HLog.setBaselogPath("Log");<br>
 *HLog.getPolicy().setBakDIR("LogBak");<br>
 *HLog.init();<br>
 *HLog log=HLog.getInstance("127.0.0.1","test");<br>
 *String s=HString.randomString(2000);<br>
 *while(true) {<br>
 *	log.info(s);<br>
 *}<br>
 */


public class HLog implements ILog{
	
	//□----------------------□--------------------□--------------------□---------------------□---------------------□---------------------
	//□ 类变量区域
	public static int Level_DEBUG=0;//详细日志输出级别，所有信息都打印。
	public static int Level_INFO=1;//一般性日志输出级别，除了明细信息不打以外，都打。
	public static int Level_WARNNING=2;//警告信息，具有不确定性或有一定风险的信息
	public static int Level_ERROR=3;//错误信息，严重
	private int logLevel=1;//当前日志级别
	private String folder;//本日志所针对的对象IP
	private String target;//对象IP的行为类型，即这是做什么事的日志
	private String logPath;//日志路径，指定日志往哪里输出
	public boolean enable=true;//当前日志是否有效
	public static String[] levelstr= new String[]{"DEBUG","INFO","WARNNING","ERROR"};
	private static String baselogPath="Log";//日志路径
	private static  Map<String,HLog> logs=new ConcurrentHashMap<String,HLog>();//缓存，有了某个任务的日志后就不再生成对象
	private SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//日志使用同一的时间格式
	private static String loc="HLog.HLogs.loc";
	public static HSortedMap<String> cache=new HSortedMap<String>();
	private static HLogPolicy policy=new HLogPolicy();
	private static long fileMaxSize=policy.getMaxSize();
	//□----------------------□--------------------□--------------------□---------------------□---------------------□---------------------
	
	
	
	
	//□----------------------□--------------------□--------------------□---------------------□---------------------□---------------------
	//□ 构造函数区域
	/**
	 *函数注释：Log()<br>
	 *作者：何明辉<br>
	 *时间：2014-5-20-上午10:05:19<br>
	 *类型：构造函数<br>
	 *用途：该构造函数用于构造一个空的日志对象<br>
	 *备注：私有，不对外开放，单点单实例<br>
	 */
	private HLog() {}
	
	

	
	
	/**
	 *函数注释：getInstance()<br>
	 *作者：何明辉<br>
	 *时间：2014-5-20-上午10:13:06<br>
	 *类型：方法<br>
	 *用途：该方法用于获取指定对象节点的日志<br>
	 *备注：一个节点一个日志，分开打印，方便排查<br> 
	 *@param strip  下级或者节点的IP
	 *@param actiontype  节点的行为类型
	 *@return
	 */
	public static HLog getInstance(String folder,String target) {
		if(StringUtils.isBlank(folder)) {folder="";}
		if(StringUtils.isBlank(target)) {target="HHH";}
		//如果缓存中有就直接返回—————————————————————————————————
		HLog y=logs.get(folder+target);
		if(y!=null) {return y;}
		//不存在就新增一个并进行缓存————————————————————————————————
		HLog log=new HLog();
		log.logPath=(StringUtils.isBlank(baselogPath)?"":(baselogPath+File.separator))+folder+File.separator+target+".log";
		log.target = target;
		log.folder=folder;
		//确保日志路径存在—————————————————————————————————————
		try {
			File logfile= new File(log.logPath);
			File parent = logfile.getParentFile();
			parent.mkdirs(); 
//			if(!logfile.exists()) {	logfile.createNewFile();}
		}catch (Exception exception) {
			exception.printStackTrace();
		}
		//缓存并返回—————————————————————————————————————
		logs.put(folder+target, log);
		return log;
	}
	//□----------------------□--------------------□--------------------□---------------------□---------------------□---------------------

	
	//□----------------------□--------------------□--------------------□---------------------□---------------------□---------------------
	//□ 方法体区域
	
	/**
	 *函数注释：close()<br>
	 *作者：何明辉<br>
	 *时间：2014-5-20-上午10:17:40<br>
	 *类型：方法<br>
	 *用途：该方法用于关闭日志和输出流<br>
	 *备注：关闭后日志才能被归档和删除<br> 
	 *@throws IOException
	 */
	public void close() throws IOException {
		logs.remove(this.getFolder()+this.getTarget());
	}
	
	
	
	
	
	/**
	 *函数注释：debug()<br>
	 *作者：何明辉<br>
	 *时间：2014-5-20-上午10:19:12<br>
	 *类型：方法<br>
	 *用途：该方法用于打印详细消息<br>
	 *备注：***<br> 
	 *@param msg
	 */
	public void debug(String msg)  {
		if(!enable) {return;}
		writeLog(msg, Level_DEBUG);
		for (ILog log : policy.logs) {
			log.debug(msg);
		}
	}
	
	
	/**
	 *函数注释：info()<br>
	 *作者：何明辉<br>
	 *时间：2014-5-20-上午10:19:12<br>
	 *类型：方法<br>
	 *用途：该方法用于打印普通消息<br>
	 *备注：***<br> 
	 *@param msg
	 */
	public void info(String msg)  {
		if(!enable) {return;}
		writeLog(msg, Level_INFO);
		for (ILog log : policy.logs) {
			log.info(msg);
		}
	}
	
	
	
	/**
	 *函数注释：warnning()<br>
	 *作者：何明辉<br>
	 *时间：2014-5-20-上午10:19:42<br>
	 *类型：方法<br>
	 *用途：该方法用于打印警告信息<br>
	 *备注：***<br> 
	 *@param msg
	 */
	public void warnning(String msg) {
		if(!enable) {return;}
		writeLog(msg, Level_WARNNING);
		for (ILog log : policy.logs) {
			log.warnning(msg);
		}
	}
	
	
	
	/**
	 *函数注释：error()<br>
	 *作者：何明辉<br>
	 *时间：2014-5-20-上午10:19:58<br>
	 *类型：方法<br>
	 *用途：该方法用于打印错误信息<br>
	 *备注：***<br> 
	 *@param msg
	 */
	public void error(String msg)  {
		if(!enable) {return;}
		writeLog(msg, Level_ERROR);
		for (ILog log : policy.logs) {
			log.error(msg);
		}
	}
	
	
	/**
	 *函数注释：writeLog()<br>
	 *作者：何明辉<br>
	 *时间：2014-5-20-上午10:20:10<br>
	 *类型：方法<br>
	 *用途：该方法用于共享支持几种级别的日志输出<br>
	 *备注：***<br> 
	 *@param msg
	 *@param level
	 */
	private  void writeLog(String msg, int level) {
		if(this.logLevel>level){return;}
		if(cache.get(getLogPath())!=null&&cache.get(getLogPath()).size()>10000){
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		msg="["+format.format(new Date())+"]["+this.folder+"]["+this.target+"]["+levelstr[level]+"]["+msg+"]\r\n";
		synchronized (loc) {
		   cache.put(getLogPath(),msg);
		}
	}
	

	/**
	 *函数注释：getLogPath()<br>
	 *作者：何明辉<br>
	 *时间：2014-5-20-上午10:21:25<br>
	 *类型：方法<br>
	 *用途：该方法用于返回日志路径<br>
	 *备注：***<br> 
	 *@return
	 */
	public String getLogPath() {
		return this.logPath;
	}

	
	/**
	 *函数注释：setLogPath()<br>
	 *作者：何明辉<br>
	 *时间：2014-5-20-上午10:21:43<br>
	 *类型：方法<br>
	 *用途：该方法用于设置日志路径<br>
	 *备注：***<br> 
	 *@param logPath
	 */
	public void setLogPath(String logPath) {
		this.logPath=logPath;
	}

	
	
	/**
	 *函数注释：getLogLevel()<br>
	 *作者：何明辉<br>
	 *时间：2014-5-20-上午10:21:58<br>
	 *类型：方法<br>
	 *用途：该方法用于获取日志级别<br>
	 *备注：DEBUG,INFO,WARNNING,ERROR<br> 
	 *@return
	 */
	public int getLogLevel() {
		return this.logLevel;
	}

	
	
	
	/**
	 *函数注释：setLogLevel()<br>
	 *作者：何明辉<br>
	 *时间：2014-5-20-上午10:22:32<br>
	 *类型：方法<br>
	 *用途：该方法用于设置日志级别<br>
	 *备注：***<br> 
	 *@param logLevel
	 */
	public void setLogLevel(int logLevel) {
		this.logLevel=logLevel;
	}

	
	



	/**
	 *函数注释：getStrip()<br>
	 *作者：何明辉<br>
	 *时间：2014-5-20-上午10:22:56<br>
	 *类型：方法<br>
	 *用途：该方法用于获取日志对象的IP<br>
	 *备注：***<br> 
	 *@return
	 */
	public String getFolder() {
		return folder;
	}



	/**
	 *函数注释：setStrip()<br>
	 *作者：何明辉<br>
	 *时间：2014-5-20-上午10:23:13<br>
	 *类型：方法<br>
	 *用途：该方法用于设置日志对象的IP<br>
	 *备注：***<br> 
	 *@param strip
	 */
	public void setFolder(String folder) {
		this.folder = folder;
	}



	/**
	 *函数注释：getActionType()<br>
	 *作者：何明辉<br>
	 *时间：2014-5-20-上午10:23:26<br>
	 *类型：方法<br>
	 *用途：该方法用于获取日志对象的行为类型<br>
	 *备注：***<br> 
	 *@return
	 */
	public String getTarget() {
		return target;
	}



	/**
	 *函数注释：setActionType()<br>
	 *作者：何明辉<br>
	 *时间：2014-5-20-上午10:23:46<br>
	 *类型：方法<br>
	 *用途：该方法用于设置日志对象的行为类型<br>
	 *备注：***<br> 
	 *@param actionType
	 */
	public void setTarget(String target) {
		this.target = target;
	}



	/**
	 *函数注释：isEnable()<br>
	 *作者：何明辉<br>
	 *时间：2014-5-20-上午10:24:09<br>
	 *类型：方法<br>
	 *用途：该方法用于返回日志是否启用<br>
	 *备注：***<br> 
	 *@return
	 */
	public boolean isEnable() {
		return enable;
	}



	/**
	 *函数注释：setEnable()<br>
	 *作者：何明辉<br>
	 *时间：2014-5-20-上午10:24:26<br>
	 *类型：方法<br>
	 *用途：该方法用于设置日志是否启用<br>
	 *备注：true=启用   false=禁用<br> 
	 *@param enable
	 */
	public void setEnable(boolean enable) {
		this.enable = enable;
	}



	/**
	 *函数注释：getLogs()<br>
	 *作者：何明辉<br>
	 *时间：2014-5-20-上午10:24:58<br>
	 *类型：方法<br>
	 *用途：该方法用于返回所有日志对象的集合<br>
	 *备注：***<br> 
	 *@return
	 */
	public static Map<String, HLog> getLogs() {
		return logs;
	}

	
	/**
	 *函数注释：enable()<br>
	 *作者：何明辉<br>
	 *时间：2014-5-20-上午10:26:01<br>
	 *类型：方法<br>
	 *用途：该方法用于启用日志<br>
	 *备注：***<br>
	 */
	public void enable() {
		enable=true;
	}
	
	
	
	/**
	 * 函数注释：disable()<br>
	 * 作者：何明辉<br>
	 * 时间：2014-5-20-上午10:26:15<br>
	 * 类型：方法<br>
	 * 用途：该方法用于禁用日志<br>
	 * 备注：***<br>
	 */
	public void disable() {
		enable=false;
	}
	
	
	
	
	
	public static void setBaselogPath(String baselogPath) {
		HLog.baselogPath=baselogPath;
	}




	public static HLogPolicy getPolicy() {
		return policy;
	}





	public static void setPolicy(HLogPolicy policy) {
		HLog.policy = policy;
		HLog.fileMaxSize=policy.getMaxSize();
	}


	
	
	//□----------------------□--------------------□--------------------□---------------------□---------------------□---------------------	
	



	public static  void init() {
		
		
		HTask task=new HTask() {
			@Override
			public void doAction() throws Exception {
				HSortedMap<String> temp=null;
				synchronized (loc) {
					temp=cache.clone();
					cache.clear();
				}
				if(temp==null||temp.isEmpty()) {return;}
				Object keys[]=temp.getKeys();
				for(Object key:keys) {
				   if(key==null||StringUtils.isBlank(key.toString())) {continue;}
				   String file=key.toString();
				   ArrayList<String> texts=temp.get(file);
				   if(texts==null||texts.isEmpty()) {continue;}
				   File logFile=new File(file);
				   logFile.getParentFile().mkdirs();
				   OutputStream os = new FileOutputStream(file, true);  
				   try {
					 if(!logFile.exists()) {logFile.createNewFile();}
					 os.write(StringUtils.join(texts, "\r\n").getBytes("UTF-8"));
					 os.write("\r\n".getBytes("UTF-8"));
					 os.flush();
					 os.close();
				   }catch(Exception e) {
					 e.printStackTrace();
				   }finally {
					 os.close();
				   }
				   if(logFile.length()>=fileMaxSize){policy.overMaxSize(logFile);}
				}
				if(temp!=null)temp.clear();
			}
		};
		task.setName("HLogs.定时输出日志");
		task.setInterval(5000);
		task.setIgnoreError(true);
		task.start();
	}
}
