package HHH.HSchedual;

import java.lang.annotation.Annotation;
import java.util.List;
import java.util.Vector;

import HHH.HAnnotations.HAnnotationUtil;
import HHH.HSchedual.Annotation.HSchedualObserver;
import HHH.HSchedual.Annotation.HSchedualTaskPool;
import HHH.HSchedual.Annotation.HSchedualTaskType;
import HHH.HSchedual.common.HSchedualTask;
import HHH.HSchedual.common.HSchedualTaskManager;
import HHH.HSchedual.observers.HSchedualObserverer;
import HHH.HTASK.HTask;

public class HSchedual {
	
	public static enum executeType{ONCE,EVERY_DAY,EVERY_WEEK,EVERY_MONTH,EVERY_YEAY,PER_SECOND,PER_MINUTE,PER_HOUR,PER_DAY,PER_WEEK,PER_MONTH,PER_YEAR}
	

	public static void initObservers() throws Exception{
		List<Class> observerscls=HAnnotationUtil.AnnotationType(HSchedualObserver.class);
		for (Class classz : observerscls) {
			HSchedualObserver anno=(HSchedualObserver) classz.getAnnotation(HSchedualObserver.class);
			HSchedualObserverer.HSchedualObserverers.put(anno.value().toString(), (HSchedualObserverer) classz.newInstance());
		}
	}
	
	
	
	public static void scanInnerTasks() throws Exception{
		List<Class> observerscls=HAnnotationUtil.AnnotationType(HSchedualTaskType.class);
		for (Class classz : observerscls) {
			HSchedualTask task=(HSchedualTask) classz.newInstance();
			task.setExecuteType(((HSchedualTaskType)classz.getAnnotation(HSchedualTaskType.class)).value());
			Annotation tp=classz.getAnnotation(HSchedualTaskPool.class);
			if(tp!=null){
				task.setTargetPool(((HSchedualTaskPool)tp).value());
			}
			else{
				task.setTargetPool(task.getExecuteType().toString());
			}
			HSchedualTaskManager.addTask(task);
		}
	}
	
	
	
	public static void observTask(){
		HTask observeTask=new HTask(){
		@Override
		public void doAction() throws Exception {
			Vector<HSchedualTask> tasks=HSchedualTaskManager.tasks;
			for (HSchedualTask task : tasks) {
				HSchedualObserverer observer=HSchedualObserverer.HSchedualObserverers.get(task.getExecuteType().toString());
				if(observer==null){continue;}
				observer.observe(task);
			}
		}};
		observeTask.setInterval(10000);
		observeTask.setName("任务观察者");
		observeTask.setIgnoreError(true);
		observeTask.start();
	}
	
	
	
	public static void start() throws Exception{
		initObservers();
		scanInnerTasks();
		observTask();
	}
	
	
	public static void main(String[] args) throws Exception {
		HSchedual.start();
	}
}
