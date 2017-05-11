package HHH.HINI;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**<P>文件注释：INISection.java</p>
 *█<p>———————————————————————————————————————————</p> 
 *█<p>作者：何明辉</p>
 *█<p>时间：2013-5-4-下午3:03:31</p>
 *█<p>类型：文件</p>
 *█<p>用途：该文件用于INI文件的板块</p>
 *█<p>备注：***</p> 
 *█<p>抛出异常：[]</p>
 *█<p>触发事件：[]</p>
 *█<p>示例：</p>
 *█<p>———————————————————————————————————————————</p>
 *█  
 *█<p>———————————————————————————————————————————</p>
 */

public class INISection {

	private String name="";
	private List<INIProperty> properties=null;
	private List<String> headerComments=null;//板块上方的注释
	private List<String> tailComments=null;//板块末尾的注释




	/**<P>函数注释：INISection()</p>
	 *█<p>———————————————————————————————————————————</p> 
	 *█<p>作者：何明辉</p>
	 *█<p>时间：2013-5-4-下午3:07:47</p>
	 *█<p>类型：构造函数</p>
	 *█<p>用途：该构造函数用于</p>
	 *█<p>备注：***</p> 
	 *█<p>抛出异常：[]</p>
	 *█<p>触发事件：[]</p>
	 *█<p>示例：</p>
	 *█<p>———————————————————————————————————————————</p>
	 *█   
	 *█<p>———————————————————————————————————————————</p>
	 */
	public INISection() {
		this.properties=new ArrayList<INIProperty>();
		this.headerComments=new ArrayList<String>();
		this.tailComments=new ArrayList<String>();
	}

	/**
	 * <P>函数注释：INISection()</p>
	 *█<p>———————————————————————————————————————————</p> 
	 *█<p>作者：何明辉</p>
	 *█<p>时间：2013-5-4-下午3:08:10</p>
	 *█<p>类型：构造函数</p>
	 *█<p>用途：该构造函数用于</p>
	 *█<p>备注：***</p> 
	 *█<p>抛出异常：[]</p>
	 *█<p>触发事件：[]</p>
	 *█<p>示例：</p>
	 *█<p>———————————————————————————————————————————</p>
	 *█   
	 *█<p>———————————————————————————————————————————</p>
	 *@param content
	 */
	public INISection(String content) {
		this();
		String spliter=System.getProperty("line.separator");
		List<String> data=Arrays.asList(content.split(spliter));
		int start=0,end=0;
		//处理头注释——————————————————————————————————————
		for(String s:data) {
			if(s==null||"".equals(s.trim())) {continue;}
			if(s.trim().startsWith("#")||s.trim().startsWith(";")||s.trim().startsWith("//")) {this.headerComments.add(s);}
			else if (s.trim().startsWith("[")) {
				start=data.indexOf(s) + 1;
				this.setName(s.substring(s.indexOf("[")+1,s.indexOf("]")).trim());
				break;
			}
			else {break;}
		}
		//处理尾注释——————————————————————————————————————
		for(int i=data.size()-1;i>=0;i--) {
			String s=data.get(i);
			if(s==null||"".equals(s.trim())) {continue;}
			if(s.trim().startsWith("#")||s.trim().startsWith(";")||s.trim().startsWith("//")) {this.tailComments.add(s.trim());}
			else {end=data.indexOf(s);break;}
		}
		Collections.reverse(tailComments);//将顺序还原回来
		//处理属性——————————————————————————————————————
		List<String> attri=data.subList(start, end+1);
		StringBuilder bu=new StringBuilder();
		for(String s:attri) {
			if(s==null||"".equals(s.trim())) {continue;}
			if(s.trim().startsWith("#")||s.trim().startsWith(";")||s.trim().startsWith("//")) {bu.append(s).append(spliter);}
			else {bu.append(s.trim());this.properties.add(new INIProperty(bu.toString().trim()));bu.setLength(0);}
		}
		//———————————————————————————————————————————
	}




	/**<P>函数注释：getProperties()</p>
	 *█<p>———————————————————————————————————————————</p> 
	 *█<p>作者：何明辉</p>
	 *█<p>时间：2013-5-4-下午3:09:52</p>
	 *█<p>类型：get方法</p>
	 *█<p>用途：该方法用于返回properties</p>
	 *█<p>备注：***</p> 
	 *█<p>抛出异常：[]</p>
	 *█<p>触发事件：[]</p>
	 *█<p>示例：</p>
	 *█<p>———————————————————————————————————————————</p>
	 *█   
	 *█<p>———————————————————————————————————————————</p>
	 *█<p>return properties</p>
	 */
	public List<INIProperty> getProperties() {
		return this.properties;
	}




	/**<P>函数注释：setProperties()</p>
	 *█<p>———————————————————————————————————————————</p> 
	 *█<p>作者：何明辉</p>
	 *█<p>时间：2013-5-4-下午3:09:52</p>
	 *█<p>类型：set方法</p>
	 *█<p>用途：该方法用于设置properties</p>
	 *█<p>备注：***</p> 
	 *█<p>抛出异常：[]</p>
	 *█<p>触发事件：[]</p>
	 *█<p>示例：</p>
	 *█<p>———————————————————————————————————————————</p>
	 *█   
	 *█<p>———————————————————————————————————————————</p>
	 *█<p>properties  properties</p>
	 */
	public void setProperties(List<INIProperty> properties) {
		this.properties=properties;
	}




	/**<P>函数注释：getHeaderComments()</p>
	 *█<p>———————————————————————————————————————————</p> 
	 *█<p>作者：何明辉</p>
	 *█<p>时间：2013-5-4-下午3:09:52</p>
	 *█<p>类型：get方法</p>
	 *█<p>用途：该方法用于返回headerComments</p>
	 *█<p>备注：***</p> 
	 *█<p>抛出异常：[]</p>
	 *█<p>触发事件：[]</p>
	 *█<p>示例：</p>
	 *█<p>———————————————————————————————————————————</p>
	 *█   
	 *█<p>———————————————————————————————————————————</p>
	 *█<p>return headerComments</p>
	 */
	public List<String> getHeaderComments() {
		return this.headerComments;
	}




	/**<P>函数注释：setHeaderComments()</p>
	 *█<p>———————————————————————————————————————————</p> 
	 *█<p>作者：何明辉</p>
	 *█<p>时间：2013-5-4-下午3:09:52</p>
	 *█<p>类型：set方法</p>
	 *█<p>用途：该方法用于设置headerComments</p>
	 *█<p>备注：***</p> 
	 *█<p>抛出异常：[]</p>
	 *█<p>触发事件：[]</p>
	 *█<p>示例：</p>
	 *█<p>———————————————————————————————————————————</p>
	 *█   
	 *█<p>———————————————————————————————————————————</p>
	 *█<p>headerComments  headerComments</p>
	 */
	public void setHeaderComments(List<String> headerComments) {
		this.headerComments=headerComments;
		for(String s:this.headerComments) {
			if (!(s.trim().startsWith("#") || s.trim().startsWith(";") || s.trim().startsWith("//"))) {
				this.headerComments.set(this.headerComments.indexOf(s), "#"+s);
			}
		}
	}




	/**<P>函数注释：getTailComments()</p>
	 *█<p>———————————————————————————————————————————</p> 
	 *█<p>作者：何明辉</p>
	 *█<p>时间：2013-5-4-下午3:09:52</p>
	 *█<p>类型：get方法</p>
	 *█<p>用途：该方法用于返回tailComments</p>
	 *█<p>备注：***</p> 
	 *█<p>抛出异常：[]</p>
	 *█<p>触发事件：[]</p>
	 *█<p>示例：</p>
	 *█<p>———————————————————————————————————————————</p>
	 *█   
	 *█<p>———————————————————————————————————————————</p>
	 *█<p>return tailComments</p>
	 */
	public List<String> getTailComments() {
		return this.tailComments;
	}




	/**<P>函数注释：setTailComments()</p>
	 *█<p>———————————————————————————————————————————</p> 
	 *█<p>作者：何明辉</p>
	 *█<p>时间：2013-5-4-下午3:09:52</p>
	 *█<p>类型：set方法</p>
	 *█<p>用途：该方法用于设置tailComments</p>
	 *█<p>备注：***</p> 
	 *█<p>抛出异常：[]</p>
	 *█<p>触发事件：[]</p>
	 *█<p>示例：</p>
	 *█<p>———————————————————————————————————————————</p>
	 *█   
	 *█<p>———————————————————————————————————————————</p>
	 *█<p>tailComments  tailComments</p>
	 */
	public void setTailComments(List<String> tailComments) {
		this.tailComments=tailComments;
		for(String s:this.tailComments) {
			if (!(s.trim().startsWith("#") || s.trim().startsWith(";") || s.trim().startsWith("//"))) {
				this.tailComments.set(this.tailComments.indexOf(s), "#"+s);
			}
		}
	}






	/**<P>函数注释：getName()</p>
	 *█<p>———————————————————————————————————————————</p> 
	 *█<p>作者：何明辉</p>
	 *█<p>时间：2013-5-4-下午3:17:42</p>
	 *█<p>类型：get方法</p>
	 *█<p>用途：该方法用于返回name</p>
	 *█<p>备注：***</p> 
	 *█<p>抛出异常：[]</p>
	 *█<p>触发事件：[]</p>
	 *█<p>示例：</p>
	 *█<p>———————————————————————————————————————————</p>
	 *█   
	 *█<p>———————————————————————————————————————————</p>
	 *█<p>return name</p>
	 */
	public String getName() {
		return this.name;
	}




	/**<P>函数注释：setName()</p>
	 *█<p>———————————————————————————————————————————</p> 
	 *█<p>作者：何明辉</p>
	 *█<p>时间：2013-5-4-下午3:17:42</p>
	 *█<p>类型：set方法</p>
	 *█<p>用途：该方法用于设置name</p>
	 *█<p>备注：***</p> 
	 *█<p>抛出异常：[]</p>
	 *█<p>触发事件：[]</p>
	 *█<p>示例：</p>
	 *█<p>———————————————————————————————————————————</p>
	 *█   
	 *█<p>———————————————————————————————————————————</p>
	 *█<p>name  name</p>
	 */
	public void setName(String name) {
		this.name=name;
	}



	public void addProperty(String key,String value,String comment) {
		if(key==null||value==null||"".equals(key)) {return;}
		if(comment==null||"".equals(comment)) {comment="";}
		INIProperty p=new INIProperty();
		String spliter=System.getProperty("line.separator");
		p.setKey(key);
		p.setValue(value);
		p.setComments(Arrays.asList(comment.split(spliter)));
		this.properties.add(p);
	}

	public void addProperty(INIProperty p) {
		this.properties.add(p);
	}

	public INIProperty getProperty(String key) {
		if(key==null) {return null;}
		for(INIProperty p:this.properties) {
			if(key.trim().equals(p.getKey().trim())) {return p;}
		}
		return null;
	}

	public void removeProperty(String key) {
		if(key==null) {return;}
		for(INIProperty p:this.properties) {
			if(key.trim().equals(p.getKey().trim())) {this.properties.remove(p);break;}
		}
	}


	public void removeProperty(INIProperty p) {
		this.properties.remove(p);
	}


	public String toString() {
		String spliter=System.getProperty("line.separator");
		StringBuilder bu=new StringBuilder();
		for (String s : this.headerComments) {
			if(s.equals("#")||s.equals("//")||s.equals(";")) {continue;};
			bu.append(s).append(spliter);
		}
		bu.append("[").append(this.getName()).append("]").append(spliter);
		for(INIProperty s:this.properties) {bu.append(s.toString()).append(spliter);}
		for (String s : this.tailComments) {
			if(s.equals("#")||s.equals("//")||s.equals(";")) {continue;};
			bu.append(s).append(spliter);
		}
		bu.append(spliter);
		return bu.toString()+(spliter);
	}


}
