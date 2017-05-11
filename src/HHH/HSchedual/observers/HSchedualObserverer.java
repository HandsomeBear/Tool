package HHH.HSchedual.observers;

import java.util.HashMap;
import java.util.Map;
import HHH.HSchedual.common.HSchedualTask;


public class HSchedualObserverer {

	
	public static Map<String,HSchedualObserverer> HSchedualObserverers=new HashMap<String,HSchedualObserverer>();
	
	
	public void observe(HSchedualTask task){
		onObserve(task);
		if(!task.isEnable()){onTaskDisabled(task);return;}
		doObserve(task);
	}
	
	public void onObserve(HSchedualTask task){}
	
	public void doObserve(HSchedualTask task){}
	
	public void onTaskComingClose(HSchedualTask task,long executetime){}//即将运行
	
	public void onTaskDisabled(HSchedualTask task){}
	
	public void onTaskSubmited(HSchedualTask task){}
	
}
