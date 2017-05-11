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
//task.setAttribute("executeunit", "21");
//-----------------------------------------------
@HSchedualObserver(executeType.PER_SECOND)
public class PER_SECOND extends HSchedualObserverer{

	public HSchedual.executeType executeType=HSchedual.executeType.PER_SECOND;
	
	@Override
	public void doObserve(HSchedualTask task){
		try {
			String poolName=task.getTargetPool();
			ExecutorService  pool=HSchedualTaskPoolFactory.getPool(StringUtils.isBlank(poolName)?"common":poolName);
			String units=StringUtils.defaultString(task.getAttribute("executeunit").toString(),"0");
			long unit=Integer.parseInt(units);
			SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			if(unit<10) {return ;}
			if(task.getStartTime()==0||task.getStartTime()>System.currentTimeMillis()){
				task.setStartTime(System.currentTimeMillis()-(unit*1000));//设置原生的starttime
				}
			long minus=System.currentTimeMillis()-task.getStartTime();
			if(minus/1000>=unit) {
				task.setStartTime( System.currentTimeMillis());
				pool.submit(task);
				onTaskSubmited(task);
			}
			long next=(unit-minus/1000);
			String per=next>300?"分":"秒";
			if(next>300) {next=next/60;}
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}finally {
//			task.setEnable(false);
		}
	}
	
	
	@Override
	public void onTaskComingClose(HSchedualTask task,long time){}
	
	
	@Override
	public void onTaskSubmited(HSchedualTask task){}
}
