package HHH.HTASK;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import HHH.HBEAN.DMP;

public abstract class HLinkedTask extends HTask implements ILinkedTask{

	public List<DMP> dmppk=new ArrayList<DMP>();
	public HLinkedTask neibghour=null;
	public int maxCatche=10;
	private String loc="";
	private boolean ExitAfterEmpty=false;
	
	public HLinkedTask(){
		loc=this.getID()+"loc";
	}
	
	
	@Override
	public void pass(DMP...dmps) throws InterruptedException{
		List<DMP> passdmp=(dmps!=null&&dmps.length>0)?Arrays.asList(dmps):dmppk;
		if(neibghour!=null)neibghour.receive(passdmp);
		synchronized (loc) {
			dmppk.removeAll(passdmp);
		}
		
	}
	

	@Override
	public void receive(List<DMP> dmp) throws InterruptedException{
		if(maxCatche>0){
			while(this.dmppk.size()>=maxCatche){
				Thread.sleep(1000);
			}
		}
		synchronized (loc) {
			this.dmppk.addAll(dmp);
		}
	}
	
	@Override
	public DMP take() throws InterruptedException{
		if(dmppk.isEmpty()){
			if(this.isStop()){return null;}
			if(ExitAfterEmpty){this.setStop(true);return null;}
			Thread.sleep(1000);
			return take();
		}
		synchronized (loc) {
			return dmppk.remove(0);
		}
	}


	public void  push(DMP dmp){
		synchronized (loc) {
			dmppk.add(dmp);
		}
	}


	public int sizeOfDMPs(){
		synchronized (loc) {
			return dmppk.size();
		}
	}
	
	
	public HLinkedTask getNeibghour() {
		return neibghour;
	}


	public void setNeibghour(HLinkedTask neibghour) {
		this.neibghour = neibghour;
	}


	public int getMaxCatche() {
		return maxCatche;
	}


	public void setMaxCatche(int maxCatche) {
		this.maxCatche = maxCatche;
	}
	


	public boolean isExitAfterEmpty() {
		return ExitAfterEmpty;
	}


	public void setExitAfterEmpty(boolean exitAfterEmpty) {
		ExitAfterEmpty = exitAfterEmpty;
	}
	
	
	
}
