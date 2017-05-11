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
@HSchedualObserver(executeType.PER_HOUR)
public class PER_HOUR extends HSchedualObserverer{

	public HSchedual.executeType executeType=HSchedual.executeType.PER_HOUR;
	
	@Override
	public void doObserve(HSchedualTask task){
		try {
			String poolName=task.getTargetPool();
			ExecutorService  pool=HSchedualTaskPoolFactory.getPool(StringUtils.isBlank(poolName)?"common":poolName);
			SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String units=StringUtils.defaultString(task.getAttribute("executeunit").toString(),"0");
			long unit=3600*Integer.parseInt(units);//获取单位
			if(unit<3600) {return;}
			if(task.getStartTime()==0||task.getStartTime()>System.currentTimeMillis()){
				task.setStartTime(System.currentTimeMillis()-(unit*1000));//设置原生的starttime
				}
			long minus=System.currentTimeMillis()-task.getStartTime();
			if(minus/1000>=unit) {
				task.setStartTime(System.currentTimeMillis());
				pool.submit(task);
				onTaskSubmited(task);
			}
			long next=((unit-minus/1000)/60);
			String per=next>120?"小时":"分";
			if(next>120) {next=next/60;}
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
