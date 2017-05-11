



/**<P>文件注释：ListHelper.java</p>
 *<p>———————————————————————————————————————————</p> 
 * <p>作者：何明辉</p>
 * <p>时间：2013-8-20-下午4:29:58</p>
 * <p>类型：文件</p>
 * <p>用途：该文件用于</p>
 * <p>备注：***</p> 
 * <p>抛出异常：[]</p>
 * <p>触发事件：[]</p>
 * <p>示例：</p>
 *<p>———————————————————————————————————————————</p>
 *   
 *<p>———————————————————————————————————————————</p>
 */
package HHH.HUTIL;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;










/**<P>类型注释：</p>
 *<p>———————————————————————————————————————————</p> 
 * <p>作者：何明辉</p>
 * <p>时间：2013-8-20-下午4:29:58</p>
 * <p>类型：ListHelper</p>
 * <p>用途：该类型用于</p>
 * <p>备注：该类型涉及到[]张数据表</p>
 * <p>涉及到的数据表：</p>
 * <p>———————————————————————————————————————————</p>
 * 
 * 
 * <p>———————————————————————————————————————————</p>
 * <p>备注：***</p> 
 * <p>抛出异常：[]</p>
 * <p>触发事件：[]</p>
 * <p>示例：</p>
 *<p>———————————————————————————————————————————</p>
 *   
 *<p>———————————————————————————————————————————</p>
 */
public class ListHelper {
	
	public static List<Map<String,Object>>  find(List<Map<String,Object>> list,String field,String value){
		List<Map<String,Object>>  result=new ArrayList<Map<String,Object>>();
		if(list==null){return result;}
		for(Map<String,Object> m:list){
			if(m.get(field)!=null&&m.get(field).equals(value)){result.add(m);}
		}
		return result;
	}
	
	
	
	/**
	 * <P>函数注释：find()</p>
	 *<p>———————————————————————————————————————————</p> 
	 * <p>作者：何明辉</p>
	 * <p>时间：2013-8-20-下午5:09:26</p>
	 * <p>类型：方法</p>
	 * <p>用途：该方法用于提取List中指定属性的map</p>
	 * <p>备注：***</p> 
	 * <p>抛出异常：[]</p>
	 * <p>触发事件：[]</p>
	 * <p>示例：</p>
	 *<p>———————————————————————————————————————————</p>
	 *   
	 *<p>———————————————————————————————————————————</p>
	 *@param list
	 *@param field
	 *@param values
	 *@return
	 */
	public static List<Map<String,String>>  findIn(List<Map<String,String>> list,String field,Collection<? extends Object>values){
		List<Map<String,String>>  result=new ArrayList<Map<String,String>>();
		if(list==null){return result;}
		for(Map<String,String> m:list){
			if(m==null){continue;}
			for (Object o:values) {
				if(o==null){continue;}
				if(o.equals(m.get(field))&&!result.contains(m)){result.add(m);}
			}
		}
		return result;
	}
	
	
	
	public static List<Object> retrive(List<Map<String,Object>> list,Object field){
		List<Object> data=new ArrayList<Object>();
		if(list==null){return data;}
		for(Map<String,? extends Object> m:list){
			if(m.get(field)!=null){data.add(m.get(field));}
		}
		return data;
	}
	
	public static List<String> retrive(List<Map<String,String>> list,String field){
		List<String> data=new ArrayList<String>();
		if(list==null){return data;}
		for(Map<String,? extends Object> m:list){
			if(m.get(field)!=null){data.add(m.get(field).toString());}
		}
		return data;
	}
	
	public static List<String> unique(List<String> list){
		List<String> hh=new ArrayList<String>();
		for(String s:list){if(!hh.contains(s)){hh.add(s);}}
		return hh;
	}
	
	
	public static List<Object> uniques(List<Object> list){
		List<Object> hh=new ArrayList<Object>();
		for(Object s:list){if(!hh.contains(s)){hh.add(s);}}
		return hh;
	}



	public static List<String> retrive(Set<Map<String, Object>> list, String field) {
		List<String> data=new ArrayList<String>();
		if(list==null){return data;}
		for(Map<String,? extends Object> m:list){
			if(m.get(field)!=null){data.add(m.get(field).toString());}
		}
		return data;
	}
	
}
