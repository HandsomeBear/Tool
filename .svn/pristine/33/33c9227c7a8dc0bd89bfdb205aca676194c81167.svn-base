package HHH.HBEAN;



//□----------------------□--------------------□--------------------□---------------------□---------------------□---------------------
//□ 包引入
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.LinkedHashMap;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;
import HHH.HJSON.HJSON;
//□----------------------□--------------------□--------------------□---------------------□---------------------□---------------------
import HHH.HZIP.HZIP;



public class DMP {
	BASE64Encoder encoder=new BASE64Encoder();
	BASE64Decoder decoder=new BASE64Decoder();
	//□----------------------□--------------------□--------------------□---------------------□---------------------□---------------------
	//□ 类变量-属性区
	
	private Map<String,Object> attributes=new LinkedHashMap<String,Object>();
	
	private Map<String,Object> data=new LinkedHashMap<String,Object>();
	
	private byte[] datastring="".getBytes();
	//□----------------------□--------------------□--------------------□---------------------□---------------------□---------------------
	
	public synchronized void compress() throws UnsupportedEncodingException, IOException{
		byte[] s=HZIP.limitZIP(HJSON.toJSON(data).getBytes("UTF-8"));
		this.datastring=s;
		this.data.clear();
	}
	
	public synchronized void unCompress() throws Exception{
		Map<String,Object> tm=HJSON.toMAP(new String(HZIP.unlimitZIP(datastring),"UTF-8"));
		this.data.clear();
		this.data.putAll(tm);
		this.datastring=null;
	}
	
	@Override
	public synchronized DMP clone() {
		DMP p=new DMP();
		p.attributes.putAll(attributes);
		p.data.putAll(data);
		p.datastring=datastring;
		return p;
	}
	
	
	public int dataLength() throws JsonProcessingException{
		return HJSON.toJSON(data).length();
	}
	
	
	public int textLength(){
		return datastring.length;
	}
	

	public Map<String, Object> getAttributes() {
		return attributes;
	}

	public void setAttributes(Map<String, Object> attributes) {
		this.attributes = attributes;
	}

	public synchronized Map<String, Object> getData() {
		return data;
	}

	public synchronized void setData(Map<String, Object> data) {
		this.data = data;
	}
	
	
	
	
}
