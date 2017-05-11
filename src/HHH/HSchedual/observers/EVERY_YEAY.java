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
//task.setAttribute("executetime", "11:57:12");
//task.setAttribute("executeunit", "01-21");
//-----------------------------------------------
@HSchedualObserver(executeType.EVERY_YEAY)
public class EVERY_YEAY extends HSchedualObserverer{

	public HSchedual.executeType executeType=HSchedual.executeType.EVERY_YEAY;
	
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
				String sp[]=units.split("-");
				ca.set(Calendar.MONTH, Integer.parseInt(sp[0].trim())-1);
				ca.set(Calendar.DAY_OF_MONTH, Integer.parseInt(sp[1].trim()));
				run=ca.getTimeInMillis();
				long now=System.currentTimeMillis();
				if(run<now) {
					ca.add(Calendar.YEAR, 1);
					run=ca.getTimeInMillis();
				}
				predict = run-now;
			}
			catch (ParseException exception) {
				System.out.println("【错误|异常】类型：ParseException");
				exception.printStackTrace();
			}
			Date d=new Date(run);
			Date now=new Date();
			Calendar c=Calendar.getInstance();c.setTime(d);
			Calendar nd=Calendar.getInstance();nd.setTime(now);
			
			c.set(Calendar.YEAR, nd.get(Calendar.YEAR));
			d=c.getTime();
		
			if(d.before(now)) {
				long minus=d.getTime()-now.getTime();
				if(minus/1000<=10) {
					pool.submit(task);
				}
				else if(minus/1000>10&&minus/1000<=300) {
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
	public void onTaskComingClose(HSchedualTask task,long predict){
		System.out.println("任务将准备运行");
	}
	
	
	@Override
	public void onTaskSubmited(HSchedualTask task){}
}
