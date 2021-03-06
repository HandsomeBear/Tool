package HHH.HLog;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import HHH.HZIP.HZIP;

public class HLogPolicy {
	
	public List<ILog> logs=new ArrayList<ILog>();
	
	private String bakdir="LogBak";
	private SimpleDateFormat format=new SimpleDateFormat("yyyyMMddHHmmss");
	private int maxFileCount = 50;
	
	public long getMaxSize(){
		return 20*1024*1024;
	}
	
	
	public void overMaxSize(File logFile){
		File target=new File(bakdir+"/"+logFile.getParentFile().getName()+"/"+logFile.getName()+"_"+format.format(new Date())+".bzip2");
		target.getParentFile().mkdirs();
		try {
			HZIP.bzip2(logFile, target);
		} catch (Exception e) {
			logFile.delete();
		}
		finally {
			logFile.delete();
		}
		//清理多余文件------------------------------------------------------------
		File dirs[]=new File(bakdir).listFiles();
		for(File dir:dirs){
			cleanDir(dir);
		}
		cleanDir(new File(bakdir));
	}
	
	
	
	
	public void setBakDIR(String path){
		this.bakdir=path;
	}
	
	
	
	private void cleanDir(File dir){
		File files[]=dir.listFiles();
		List<File> subfile=new ArrayList<File>();
		for (File file : files) {
			if(!file.canRead()){continue;}
			if(file.isFile()){subfile.add(file);}
		}
		Collections.sort(subfile, new Comparator<File>() {
			@Override
			public int compare(File o1, File o2) {
				return (o2.lastModified()-o1.lastModified())>0?1:-1;
			}
		});
		for(int i=subfile.size()-1;i>=getMaxFileCount();i--){
			subfile.get(i).delete();
		}
		subfile.clear();
	}
	
	
	
	
	public void appendLog(ILog log){
		logs.add(log);
	}
	
	public void setMaxFileCount(int maxFileCount){
		this.maxFileCount = maxFileCount;
	}
	
	public int getMaxFileCount(){
		return maxFileCount;
	}
}
