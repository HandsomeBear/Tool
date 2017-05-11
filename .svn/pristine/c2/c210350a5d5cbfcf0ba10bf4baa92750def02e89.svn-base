package HHH.HTASK;

import java.util.ArrayList;
import java.util.List;

public class HLinkedTaskQueue {

	List<HLinkedTask> queue=new ArrayList<HLinkedTask>();
	
	public synchronized void addTask(HLinkedTask task){
		queue.add(task);
	}
	
	public HLinkedTask getTask(int index){
		return queue.get(index);
	}
	
	public synchronized void removeTask(HLinkedTask task){
		queue.remove(task);
	}
	
	public int size(){
		return queue.size();
	}
	
	public void clear(){
		this.queue.clear();
	}
	
	public void setTask(int index,HLinkedTask task){
		this.queue.set(index, task);
	}
	
	public void start(){
		if(queue.size()<=0){return;}
		for(int i=0;i<queue.size()-1;i++){
			queue.get(i).setNeibghour(queue.get(i+1));
		}
		for(int i=0;i<queue.size();i++){
			queue.get(i).start();
		}
		for(HLinkedTask task:queue){
			while(task.isAlive()){
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					break;
				}
			}
			HLinkedTask neibour=task.getNeibghour();
			task.destroy();
			if(neibour!=null){neibour.setStop(true);}
		}
		finish();
	}
	
	public void stop(){
		for(HLinkedTask task:queue){
			task.destroy();
		}
	}
	
	
	public void finish(){}
}
