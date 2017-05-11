package HHH.HUTIL;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

public class StringUtils{
	
	
	public static String join(Collection con,String seperator){
		if(con==null||con.isEmpty()){return "";}
		if(con.size()==1){return con.toArray()[0].toString();}
		StringBuilder bu=new StringBuilder();
		seperator=(seperator==null)?",":seperator;
		Iterator it=con.iterator();
		while(it.hasNext()){
			bu.append(it.next().toString());
			bu.append(seperator);
		}
		bu.setLength(bu.length()-seperator.length());
		return bu.toString();
	}
	
	
	public static String join(String[] strings,String seperator){
		return join(Arrays.asList(strings), seperator);
	}
	
	
	
	public static boolean isBlank(Object o) {
		return (o==null||"".equals(o));
	}
	
	public static boolean isNotBlank(Object o) {
		return !(o==null||"".equals(o));
	}
	
	
	public static String defaultString(String a,String b){
		if(isBlank(a)){return b;}
		return a;
	}
}