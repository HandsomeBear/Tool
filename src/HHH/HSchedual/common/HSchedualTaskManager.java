package HHH.HSchedual.common;

import java.util.Vector;

public class HSchedualTaskManager {

	public static Vector<HSchedualTask> tasks=new Vector<HSchedualTask>();
	
	
	public static void addTask(HSchedualTask t){tasks.add(t);}
	
	
	public static HSchedualTask getTask(int index){return tasks.get(Math.min(index, tasks.size()-1));}
	
	
	public static void removeTask(int index){
		if(index>=tasks.size()){return;}
		tasks.remove(index);
	}
	
	
	public static int size(){
		return tasks.size();
	}
	
}
