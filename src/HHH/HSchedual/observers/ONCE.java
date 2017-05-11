package HHH.HSchedual.observers;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;

import HHH.HLog.HLog;
import HHH.HSchedual.HSchedual;
import HHH.HSchedual.HSchedual.executeType;
import HHH.HSchedual.Annotation.HSchedualObserver;
import HHH.HSchedual.common.HSchedualTask;
import HHH.HSchedual.common.HSchedualTaskPoolFactory;
import HHH.HTASK.HTaskPool;
import HHH.HUTIL.StringUtils;

//-----------------------------------------------
//task.setAttribute("executetime", "2016-07-05 15:58:12");
//-----------------------------------------------
@HSchedualObserver(executeType.ONCE)
public class ONCE extends HSchedualObserverer{

	public HSchedual.executeType executeType=HSchedual.executeType.ONCE;			
	@Override
	public void doObserve(HSchedualTask task){
		try {
			String poolName=task.getTargetPool();
			ExecutorService  pool=HSchedualTaskPoolFactory.getPool(StringUtils.isBlank(poolName)?"common":poolName);
			SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String time=task.getAttribute("executetime").toString();
			Date now=new Date();
			Date d=format.parse(time);//需设置执行时间
			long dtime=d.getTime();
			if(dtime<System.currentTimeMillis()) {return;}
			if(d.before(now)) {return;}
			long minus=d.getTime()-now.getTime();
			if(minus/1000<=10) {
				pool.submit(task);
				task.setEnable(false);
			}
			else if(minus/1000>10&&minus/1000<=300) {
				onTaskComingClose(task,minus/1000-10);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			//task.setEnable(false);
		}
	}
	
	
	@Override
	public void onTaskComingClose(HSchedualTask task,long executetime){
		System.out.println("ready");
	}
	
	
	@Override
	public void onTaskSubmited(HSchedualTask task){}
}
