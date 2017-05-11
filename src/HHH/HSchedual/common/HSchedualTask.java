package HHH.HSchedual.common;

import HHH.HTASK.HTask;

public class HSchedualTask extends HTask {

	
	private boolean enable=true;
	
	private HHH.HSchedual.HSchedual.executeType executeType=HHH.HSchedual.HSchedual.executeType.ONCE;
	
	private String targetPool="common";
	
	@Override
	public void doAction() throws Exception {
		// TODO 自动生成的方法存根

	}




	public boolean isEnable() {
		return enable;
	}




	public void setEnable(boolean enable) {
		this.enable = enable;
	}




	public HHH.HSchedual.HSchedual.executeType getExecuteType() {
		return executeType;
	}




	public void setExecuteType(HHH.HSchedual.HSchedual.executeType executeType) {
		this.executeType = executeType;
	}




	public String getTargetPool() {
		return targetPool;
	}




	public void setTargetPool(String targetPool) {
		this.targetPool = targetPool;
	}
	
	
	
	
	

}
