package HHH.HTASK;

import java.util.Vector;

public class HTaskPool {

	private Vector<HTaskContainer> container=new Vector<HTaskContainer>();
	private Vector<HTask> queue=new Vector<HTask>();
	private int maxSize=5;
	private long overtime=0;//超时时间 秒
	
	private HTask overtimetask=null;
	
	
	
	public void submit(HTask task){
		synchronized ("HTaskPoolHTaskContainer") {
			queue.add(task);
		}
		synchronized ("HTaskPoolHTaskContainer_at"){
			for(HTaskContainer c:container){
				if(c.isWating()){return;}
			}
			if(container.size()>=maxSize){return;}
		}
		addThread();
	}
	
	
	public void setMaxSize(int size){
		this.maxSize=size;
		while(container.size()<maxSize&&queue.size()>0){
			addThread();
		}
	}
	
	
	
	public int getMaxSize(){
		return this.maxSize;
	}
	
	
	
	public long getOvertime() {
		return overtime;
	}


	public void setOvertime(long overtime) {
		this.overtime = overtime;
		if(this.overtimetask!=null){return;}
		overtimetask=new HTask() {
			@Override
			public void doAction() throws Exception {
				this.setName("超时检测线程");
				for(int i=container.size()-1;i>=0;i--){
					if(getOvertime()==0){break;}
					HTaskContainer t=container.get(i);
					if(t.getStartTime()==0)continue;
					if(System.currentTimeMillis()-t.getStartTime()>getOvertime()*1000){
						container.remove(i);
						t.destroy();
					}
				}
				setMaxSize(getMaxSize());
			}
			
		};
		overtimetask.start();
	}


	public int getActiveCount(){
		synchronized ("HTaskPoolHTaskContainer_at") {return container.size();}
	}
	
	public int getWaitingCont(){
		synchronized ("HTaskPoolHTaskContainer") {
			return queue.size();
		}
	}
	
	
	private void addThread(){
		HTaskContainer c=new HTaskContainer(queue,container,this);
		synchronized ("HTaskPoolHTaskContainer_at") {
			container.add(c);
		}
		c.start();
	}
	
	
	
	public void stop(String taskid){
		synchronized ("HTaskPoolHTaskContainer") {
			for (HTask r : queue) {
				if(!taskid.equals(r.getID())){continue;}
				r.setStop(true);
				r.destroy();
			}
		}
	}
	
	
	public void shutdown(){
		new Thread(){@Override
		public void run() {
			while(queue.size()>0){
				try {
					Thread.sleep(1000);
					continue;
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			synchronized ("HTaskPoolHTaskContainer_at") {
				for (HTaskContainer r : container) {
					r.setStop(true);
				}
			}
			while(getActiveCount()>0){
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			HTaskPool.this.destroy();
			
		};}.start();
	}
	
	
	
	public void destroy(){
		overtimetask.destroy();
		this.queue.clear();
		for (HTask task : container) {
			task.destroy();
		}
		this.container.clear();
		this.queue=null;
		this.container=null;
	}
	
	
	
}
