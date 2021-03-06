package HHH.HSchedual.observers;

import java.text.SimpleDateFormat;
import java.util.Calendar;
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
//task.setAttribute("executetime", "09:07:12");
//task.setAttribute("executeunit", "6");
//-----------------------------------------------
@HSchedualObserver(executeType.EVERY_MONTH)
public class EVERY_MONTH extends HSchedualObserverer{

	public HSchedual.executeType executeType=HSchedual.executeType.EVERY_MONTH;
	
	@Override
	public void doObserve(HSchedualTask task){
		try {
			String poolName=task.getTargetPool();
			ExecutorService  pool=HSchedualTaskPoolFactory.getPool(StringUtils.isBlank(poolName)?"common":poolName);
			SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String units=StringUtils.defaultString(task.getAttribute("executeunit").toString(),"0");
			String targetTime=format.format(new Date()).split(" ")[0]+" "+task.getAttribute("executetime");
			Calendar ca=Calendar.getInstance();
			long run=0;
			long predict=0;
			try {
				ca.setTime(format.parse(targetTime));
				ca.set(Calendar.DAY_OF_MONTH, Integer.parseInt(units));
				run=ca.getTimeInMillis();
				long nowtime=System.currentTimeMillis();
				if(run<nowtime) {
					ca.add(Calendar.MONTH, 1);
					run=ca.getTimeInMillis();
				}
				predict=run-nowtime;//预计还有多久下一次运行
			}catch(Exception e){
				e.printStackTrace();
			}
			Date d=new Date(run);
			Date now=new Date();
			Calendar c=Calendar.getInstance(); 
			c.setTime(d);
			Calendar nd=Calendar.getInstance();
			nd.setTime(now);
			if(units.equals(nd.get(Calendar.DAY_OF_MONTH)+"")) {
				c.set(Calendar.DAY_OF_MONTH, nd.get(Calendar.DAY_OF_MONTH));
				c.set(Calendar.MONTH, nd.get(Calendar.MONTH));
				c.set(Calendar.YEAR, nd.get(Calendar.YEAR));
				d=c.getTime();
			
				if(d.before(now)) {
					long minus=d.getTime()-now.getTime();
					if(minus/1000<=10) {
						System.out.println("任务满足执行条件[每月"+units+"号于"+task.getAttribute("executetime")+"执行]，即将执行...");
						pool.submit(task);
					}
					else if(minus/1000>10&&minus/1000<=300) {
						System.out.println("任务将于"+(minus/1000-10)+"秒内运行");
						onTaskComingClose(task,predict);
					}
					return;
				}
			}
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}finally {
//			task.setEnable(false);
		}
	}
	
	
	@Override
	public void onTaskComingClose(HSchedualTask task,long predict){
		System.out.println("任务下次运行时间"+predict/1000+"秒");
	}
	
	
	@Override
	public void onTaskSubmited(HSchedualTask task){}
}
