package HHH.HSchedual.common;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import HHH.HTASK.HTaskPool;

public class HSchedualTaskPoolFactory {

	
	private static Map<String,ExecutorService> pools=new HashMap<String,ExecutorService>(); 
	
	
	
	public synchronized static ExecutorService getPool(String name){
		ExecutorService pool=pools.get(name);
		if(pool==null){
			pool=Executors.newCachedThreadPool();
			pools.put(name, pool);
		}
		return pool;
	}
	
}
