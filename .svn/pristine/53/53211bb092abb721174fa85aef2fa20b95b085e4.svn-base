package HHH.HTASK;

import java.util.List;

public class HTaskContainer extends HTask{

	private List<HTask> queue=null;
	private List<HTaskContainer> container=null;
	private HTaskPool pool;
	private boolean isWaiting=true;
	private HTask task=null;
	
	public HTaskContainer(List<HTask> queue,List<HTaskContainer> container,HTaskPool pool) {
		 this.queue=queue;
		 this.container=container;
		 this.pool=pool;
		 this.addListener(new HTaskListener() {
			@Override
			public void interrupted(HTask task, InterruptedException e) {
			}
			@Override
			public void error(HTask task, Exception e) {
			}
			@Override
			public void beforeExecute(HTask task) {
			}
			@Override
			public void afterExecute(HTask task) {
				remove();
			}
		});
	}
	
	
	@Override
	public void doAction() throws Exception {
		
		int times=0;
		
		while(!this.isStop()){
			this.setStartTime(System.currentTimeMillis());
			try {
				synchronized ("HTaskPoolHTaskContainer") {
					if(queue==null){return;}
					if(!queue.isEmpty())task=queue.remove(0);
					else{task=null;}
				}
				if(task!=null){
					isWaiting=false;
					times=0;
					task.run();
				}
				else{
					times++;
					isWaiting=true;
					this.setEndTime(System.currentTimeMillis());
					if(times>=60){return;}
					Thread.sleep(1000);
				}
			} catch (InterruptedException e) {
				this.setEndTime(System.currentTimeMillis());
				this.remove();
				this.destroy();
			} catch (Exception e) {
			}
			this.setEndTime(System.currentTimeMillis());
		}
	}
	
	
	public void remove(){
		synchronized ("HTaskPoolHTaskContainer_at") {
			container.remove(this);
		}
		
	}
	
	

	public boolean isWating(){return this.isWaiting;}
	
	
	
	public HTask getTask(){return this.task;}
	
}
