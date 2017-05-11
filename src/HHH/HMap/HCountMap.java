package HHH.HMap;
import java.util.Collection;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;



public class HCountMap {
	
	public ConcurrentHashMap<String,Long> map=new ConcurrentHashMap<String,Long>();
	
	public  void put(String key,Long num) {
		if(map.containsKey(key)) {map.put(key, map.get(key)+num);}
		else {map.put(key, num);}
	}
	
	
	public Long get(String key) {
		if(!map.containsKey(key)) {return 0l;}
		return map.get(key);
	}
	
	public void clear() {map.clear();}


	public boolean containsKey(Object arg0) {
		return this.map.containsKey(arg0);
	}


	public boolean containsValue(Object arg0) {
		return this.map.containsValue(arg0);
	}


	public boolean isEmpty() {
		return this.map.isEmpty();
	}


	public Set<String> keySet() {
		return this.map.keySet();
	}


	public Long remove(Object arg0) {
		return this.map.remove(arg0);
	}


	public int size() {
		return this.map.size();
	}


	public Collection<Long> values() {
		return this.map.values();
	}
	
	
	
}
