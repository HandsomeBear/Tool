package HHH.HSchedual.observers; import java.text.ParseException;
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
import HHH.HUTIL.StringUtils; //-----------------------------------------------
//task.setAttribute("executetime", "09:11:12");
//task.setAttribute("executeunit", "3");
//-----------------------------------------------
@HSchedualObserver(executeType.EVERY_WEEK)
public class EVERY_WEEK extends HSchedualObserverer{  
 SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
 
 @Override
 public void doObserve(HSchedualTask task){
	 	try {
 			String poolName=task.getTargetPool();
 			ExecutorService  pool=HSchedualTaskPoolFactory.getPool(StringUtils.isBlank(poolName)?"common":poolName);
 			long run=0;
 			long predict=0;
 			String executeunit=("".equals(task.getAttribute("executeunit"))||task.getAttribute("executeunit")==null)?"1":task.getAttribute("executeunit").toString();
 			//周时间处理
 			long executeunitLong=Long.parseLong(executeunit);
 			executeunitLong+=1;
 			if(executeunitLong==8){executeunitLong=1;}
 			try {
 				String targetTime=format.format(new Date()).split(" ")[0]+" "+task.getAttribute("executetime");
 				Calendar ca=Calendar.getInstance();
 				int unit=ca.get(Calendar.DAY_OF_WEEK);
 				run=format.parse(targetTime).getTime();
 				long now=System.currentTimeMillis();
 				predict=run-now;
 				while(unit!=executeunitLong) {
 					ca.add(Calendar.DAY_OF_WEEK, 1);
 					unit=ca.get(Calendar.DAY_OF_WEEK);
 					predict+=24*60*60*1000;
 				}
 				if(predict<0) {predict=7*24*60*60*1000+predict;}
 			}
 			catch (ParseException exception) {
 				exception.printStackTrace();
 			}
 			Date now=new Date();
 			Calendar c=Calendar.getInstance();c.setTimeInMillis(run);
 			Calendar nd=Calendar.getInstance();nd.setTime(now);
 			if((nd.get(Calendar.DAY_OF_WEEK))==executeunitLong) {
 				c.set(Calendar.YEAR, nd.get(Calendar.YEAR));
 				c.set(Calendar.MONTH, nd.get(Calendar.MONTH));
 				c.set(Calendar.DAY_OF_MONTH, nd.get(Calendar.DAY_OF_MONTH));
 				Date d=c.getTime(); 
 				if(d.before(now)){return ;}
 				long minus=d.getTime()-now.getTime();
 				if(minus/1000<=10) {
 					pool.submit(task);//提交
 				}
 				else if(minus/1000>10&&minus/1000<=300) {
 					onTaskComingClose(task,predict);
 				}
	 			}
	 	} catch (Exception e) {
	 		e.printStackTrace();
	 	}finally {
	 		//   task.setEnable(false);
	 	}
 	}
 
 
 @Override
 public void onTaskComingClose(HSchedualTask task,long executetime){
 }
 
 
 @Override
 public void onTaskSubmited(HSchedualTask task){}
 
 
 
}