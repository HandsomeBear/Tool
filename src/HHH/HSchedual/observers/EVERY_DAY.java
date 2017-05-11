package HHH.HSchedual.observers;

import java.text.ParseException;
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
//task.setAttribute("executetime", "09:00:12");//设置查询时间
//-----------------------------------------------
@HSchedualObserver(executeType.EVERY_DAY)
public class EVERY_DAY extends HSchedualObserverer{

	public HSchedual.executeType executeType=HSchedual.executeType.EVERY_DAY;
	SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	@Override
	public void doObserve(HSchedualTask task){
		try {
			String poolName=task.getTargetPool();
			ExecutorService  pool=HSchedualTaskPoolFactory.getPool(StringUtils.isBlank(poolName)?"common":poolName);
			//设置每天查询时预测下次时间----------------------
			long runtime=0;//运行时间
			long nowtime=0;//现在时间
			long predict=0;//预测时间
			try {
				String targetTime=format.format(new Date()).split(" ")[0]+" "+task.getAttribute("executetime");
				runtime=format.parse(targetTime).getTime();
				nowtime=System.currentTimeMillis();
				predict=runtime<nowtime?(runtime-nowtime+24*60*60*1000):runtime-nowtime;
			}
			catch (ParseException exception) {
				exception.printStackTrace();
			}
			Date now=new Date();
			Date d=new Date(runtime);;//需设置执行时间
			
			Calendar c=Calendar.getInstance();c.setTime(d);
			Calendar nd=Calendar.getInstance();nd.setTime(now);
			c.set(Calendar.YEAR, nd.get(Calendar.YEAR));
			c.set(Calendar.MONTH, nd.get(Calendar.MONTH));
			c.set(Calendar.DAY_OF_MONTH, nd.get(Calendar.DAY_OF_MONTH));
			d=c.getTime();
			
			if(!d.before(now)) {
			long minus=d.getTime()-now.getTime();
			if(minus/1000<=10) {
				System.out.println("任务满足执行条件[每天于"+task.getAttribute("executetime")+"执行]，即将执行...");
				pool.submit(task);
			}
			else if(minus/1000>10&&minus/1000<=300) {
				System.out.println("任务将于"+(minus/1000-10)+"秒内运行");
				onTaskComingClose(task,predict);
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
	public void onTaskComingClose(HSchedualTask task,long executetime){
		System.out.println("任务将准备运行");
	}
	
	
	@Override
	public void onTaskSubmited(HSchedualTask task){}
	
}
