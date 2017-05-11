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
//task.setAttribute("executeunit", "1");
//-----------------------------------------------
@HSchedualObserver(executeType.PER_WEEK)
public class PER_WEEK extends HSchedualObserverer{

	public HSchedual.executeType executeType=HSchedual.executeType.PER_WEEK;
	
	@Override
	public void doObserve(HSchedualTask task){
		try {
			String poolName=task.getTargetPool();
			ExecutorService  pool=HSchedualTaskPoolFactory.getPool(StringUtils.isBlank(poolName)?"common":poolName);
			String units=StringUtils.defaultString(task.getAttribute("executeunit").toString(),"0");

			long unit=7*86400*Integer.parseInt(units);
			SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			if(unit<(7*86400)) {return ;}
			if(task.getStartTime()==0||task.getStartTime()>System.currentTimeMillis()){
			task.setStartTime(System.currentTimeMillis()-(unit*1000));//设置原生的starttime
			}
			System.out.println("任务上次执行时间："+format.format(new Date(task.getStartTime())));
			long minus=System.currentTimeMillis()-task.getStartTime();
			if(minus/1000>=unit) {
				task.setStartTime(System.currentTimeMillis());
				pool.submit(task);
				onTaskSubmited(task);
			}
			long next=(unit-minus/1000);
			String per="秒";
			if(next/3600/24>1) {next=next/3600/24;per="天";}
			else if(next/3600>2) {next=next/3600;per="小时";}
			else if(next/60>5) {next=next/60;per="分钟";}
			else if(next>10) {;per="秒";}
			System.out.println("下次执行大约于"+next+per+"后");
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}finally {
//			task.setEnable(false);
		}
	}
	
	
	@Override
	public void onTaskComingClose(HSchedualTask task,long time){
	}
	
	
	@Override
	public void onTaskSubmited(HSchedualTask task){}
}
