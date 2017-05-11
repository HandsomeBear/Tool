package HHH.HREST;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.core.JsonProcessingException;
import HHH.HJSON.HJSON;


public class HMessage {

	private boolean success=true;
	
	private Object data="";
	
	private long requestTime=0l;
	
	private long responseTime=0l;
	
	private long processTime=0l;
	
	private String msg="";
	
	
	public void success(boolean success){
		this.success=success;
	}
	
	
	public void data(Object data){
		this.data=data;
	}
	
	
	public void start(){
		this.requestTime=System.currentTimeMillis();
	}
	
	
	public void end(){
		this.responseTime=System.currentTimeMillis();
		this.processTime=(this.responseTime-this.requestTime)/1000;
	}
	
	
	public void msg(String msg){this.msg=msg;}


	public boolean isSuccess() {
		return success;
	}


	public Object getData() {
		return data;
	}


	public long getRequestTime() {
		return requestTime;
	}


	public long getResponseTime() {
		return responseTime;
	}


	public long getProcessTime() {
		return processTime;
	}


	public String getMsg() {
		return msg;
	}
	
	
	@Override
	public String toString() {
		Map<String,Object> o=new HashMap<String,Object>();
		o.put("success", success);
		o.put("data", data);
		o.put("msg", msg);
		SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		o.put("requestTime", format.format(requestTime));
		o.put("responseTime", format.format(responseTime));
		o.put("processCosts", processTime+" seconds");
		try {
			return HJSON.toJSON(o);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return "";
	}
	
}
