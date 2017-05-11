package HHH.HTASK;

public interface HTaskListener {

	
	public void beforeExecute(HTask task);
	
	public void afterExecute(HTask task);
	
	public void error(HTask task,Exception e);
	
	public void interrupted(HTask task,InterruptedException e);

}
