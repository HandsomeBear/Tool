package HHH.HUTIL;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import HHH.HJSON.HJSON;
import HHH.HZIP.HZIP;

public class HCache {

	private List<byte[]> cache=new ArrayList<byte[]>();
	private Map  map=new HashMap();
	private long total=0;
	
	public  void put(String key,Object value) throws Exception{
		if(key==null){throw new Exception("key can not be null for "+HJSON.toJSON(value));}
		if(value==null){throw new Exception("value can not be null for "+HJSON.toJSON(key));}
		map.put(key, value);
		if(map.size()>=100000){
		   cache.add(HZIP.limitZIP(HJSON.toJSON(map).getBytes("UTF-8")));
		   map.clear();
		}
		total++;
	}
	
	
	public  Object get(String key) throws  Exception{
		if(key==null){throw new Exception("key can not be null for "+HJSON.toJSON(key));}
		Object returns=null;
		for (byte[] bs : cache) {
			Map m=HJSON.toMAP(new String(HZIP.unlimitZIP(bs),"UTF-8"));
			Object o=m.get(key);
			if(o!=null){returns=o;m.clear();return returns;}
			m.clear();
			m=null;
		}
		return map.get(key);
	}
	
	
	
	
	public  List<Object> get(List<String> keys) throws  Exception{
		List<Object> data=new ArrayList<Object>();
		if(keys==null||keys.isEmpty()){return data;}
		for (byte[] bs : cache) {
			Map m=HJSON.toMAP(new String(HZIP.unlimitZIP(bs),"UTF-8"));
			for (String key : keys) {
				Object o=m.get(key);
				if(o!=null){data.add(o);}
			}
			m.clear();
			m=null;
		}
		for (String key : keys) {
			Object o=map.get(key);
			if(o!=null){data.add(o);}
		}
		return data;
	}

	
	
	public  void clear(){
		cache.clear();
		map.clear();
		total=0;
	}
	
	
	public long size() throws Exception{
		long size=0;
		for (byte[] bs : cache) {
			size+=bs.length;
		}
		size+=HJSON.toJSON(map).length();
		return size;
	}
	
	
	
	public long capacity(){
		return this.total;
	}
	

	
	public List<Object> keys() throws Exception{
		List<Object> keys=new ArrayList<Object>();
		
		for (byte[] bs : cache) {
			Map m=HJSON.toMAP(new String(HZIP.unlimitZIP(bs),"UTF-8"));
			keys.addAll(m.keySet());
			m.clear();
			m=null;
		}
		keys.addAll(map.keySet());
		
		return keys;
	}
}
