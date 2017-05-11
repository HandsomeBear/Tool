package HHH.HTASK;
import java.rmi.server.UID;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.concurrent.ConcurrentHashMap;


public abstract class HTask extends Thread implements ITASK{
	
	public static enum executeType{ONCE,EVERY_DAY,EVERY_WEEK,EVERY_MONTH,EVERY_YEAY,PER_SECOND,PER_MINUTE,PER_HOUR,PER_DAY,PER_WEEK,PER_MONTH,PER_YEAR}
	
	private Map attributes=new ConcurrentHashMap();
	
	private String ID=createUID();

	private String status=STATUS_READY;
	
	private long interval=0;
	
	private boolean stop=false;
	
	private boolean ignoreError=false;
	
	private long startTime=0;
	
	private long endTime=0;
	
	
	public static String STATUS_READY="ready";
	public static String STATUS_RUNNING="running";
	public static String STATUS_ERROR="error";
	public static String STATUS_FINISH="finish";
	
	private List<HTaskListener> listeners=new ArrayList<HTaskListener>();
	
	@Override
	public final void run() {
		this.stop=false;
		status=STATUS_RUNNING;
		if(interval==0){
			startTime=System.currentTimeMillis();
			//-----------------------------------
			for(HTaskListener l:listeners){l.beforeExecute(this);}
			//-----------------------------------
			try {
				this.doAction();
				status=STATUS_FINISH;
			} 
			catch(InterruptedException e){
				//-----------------------------------
				for(HTaskListener l:listeners){l.interrupted(this,e);}
				//-----------------------------------
				stop=true;
				status=STATUS_ERROR;
				endTime=System.currentTimeMillis();
			}
			catch (Exception e) {
				//-----------------------------------
				for(HTaskListener l:listeners){l.error(this,e);}
				//-----------------------------------
				stop=true;
				status=STATUS_ERROR;
				endTime=System.currentTimeMillis();
			}
			//-----------------------------------
			for(HTaskListener l:listeners){l.afterExecute(this);}
			//-----------------------------------
			endTime=System.currentTimeMillis();
		}
		else if(interval>0){
			while(!Thread.currentThread().isInterrupted()&&interval>0&&!stop){
				startTime=System.currentTimeMillis();
				//-----------------------------------
				for(HTaskListener l:listeners){l.beforeExecute(this);}
				//-----------------------------------
				try {
					status=STATUS_RUNNING;
					this.doAction();
					status=STATUS_FINISH;
					Thread.sleep(interval);
				} catch (InterruptedException e) {
					//-----------------------------------
					for(HTaskListener l:listeners){l.interrupted(this,e);}
					//-----------------------------------
					stop=true;
					status=STATUS_ERROR;
					endTime=System.currentTimeMillis();
					break;
				} catch (Exception e) {
					//-----------------------------------
					for(HTaskListener l:listeners){l.error(this,e);}
					//-----------------------------------
					endTime=System.currentTimeMillis();
					if(!ignoreError){
						stop=true;
						status=STATUS_ERROR;
						endTime=System.currentTimeMillis();
						break;
					}
					try {
						Thread.sleep(interval);
					}
					catch (InterruptedException exception) {
						//-----------------------------------
						for(HTaskListener l:listeners){l.interrupted(this,exception);}
						//-----------------------------------
						stop=true;
						status=STATUS_ERROR;
						endTime=System.currentTimeMillis();
						break;
					}
				}
				//-----------------------------------
				for(HTaskListener l:listeners){l.afterExecute(this);}
				//-----------------------------------
				endTime=System.currentTimeMillis();
			}
		}
		destroy();
	}
	

	
	@Override
	public final void destroy(){
		this.stop=true;
		this.listeners.clear();
		this.interrupt();
	}
	
	
    private String createUID() {
	    String uid = new UID().toString();
	    StringTokenizer st = new StringTokenizer(uid, ":");
	    uid = "";
	    while (st.hasMoreElements()) {
	      uid = uid + st.nextToken();
	    }
	
	    char[] ch = uid.toCharArray();
	    char[] tmp = new char[ch.length];
	    int ind = ch.length - 1;
	    for (int i = 0; i < ch.length; i++) {
	      tmp[(ind--)] = ch[i];
	    }
	    uid = String.valueOf(tmp);
	    return uid;
    }


	@Override
	public final long getInterval() {
		return interval;
	}

	
	@Override
	public final void setInterval(long interval){
		this.interval=interval;
	}
	
	@Override
	public final void clearInterval(){
		this.interval=0;
	}

	@Override
	public final boolean isStop() {
		return stop;
	}


	@Override
	public final void setStop(boolean stop) {
		this.stop = stop;
	}


	@Override
	public final boolean isIgnoreError() {
		return ignoreError;
	}


	@Override
	public final void setIgnoreError(boolean ignoreError) {
		this.ignoreError = ignoreError;
	}


	@Override
	public final String getID() {
		return ID;
	}

	
	@Override
	@Deprecated
	public long getId() {
		return super.getId();
	}

	
	@Override
	public final String getStatus() {
		return status;
	}
    
	
	
    public long getStartTime() {
		return startTime;
	}



	public void setStartTime(long startTime) {
		this.startTime = startTime;
	}



	public long getEndTime() {
		return endTime;
	}



	public void setEndTime(long endTime) {
		this.endTime = endTime;
	}



	public void addListener(HTaskListener listener){
    	this.listeners.add(listener);
    }
    
    
    public void removeListener(HTaskListener listener){
    	this.listeners.remove(listener);
    }
    
    
    public List<HTaskListener> getListeners(){
    	return this.listeners;
    }
    
    
    public void setAttribute(String key,Object value){
    	attributes.put(key, value);
    }
    
    
    public Object getAttribute(String key){
    	return attributes.get(key);
    }
    
    
    public Map getAttributes(){
    	return attributes;
    }
    
    
    
}
