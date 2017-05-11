package HHH.HINI;


import java.util.ArrayList;
import java.util.List;

/**<P>文件注释：INIProperty.java</p>
 *█<p>———————————————————————————————————————————</p> 
 *█<p>作者：何明辉</p>
 *█<p>时间：2013-5-4-下午2:36:34</p>
 *█<p>类型：文件</p>
 *█<p>用途：该文件用于INI的配置属性</p>
 *█<p>备注：***</p> 
 *█<p>抛出异常：[]</p>
 *█<p>触发事件：[]</p>
 *█<p>示例：</p>
 *█<p>———————————————————————————————————————————</p>
 *█  
 *█<p>———————————————————————————————————————————</p>
 */

public class INIProperty {

	private List<String> comments=null;
	private String key="";
	private String value="";




	/**<P>函数注释：INIProperty()</p>
	 *█<p>———————————————————————————————————————————</p> 
	 *█<p>作者：何明辉</p>
	 *█<p>时间：2013-5-4-下午2:41:00</p>
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
	public INIProperty() {
		this.comments=new ArrayList<String>();
	}



	/**
	 * <P>函数注释：INIProperty()</p>
	 *█<p>———————————————————————————————————————————</p> 
	 *█<p>作者：何明辉</p>
	 *█<p>时间：2013-5-4-下午2:41:39</p>
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
	public INIProperty(String content) {
		this();
		String spliter=System.getProperty("line.separator");
		String data[]=content.split(spliter);
		for(String s:data) {
			if(s==null) {continue;}
			if(s.trim().startsWith("#")||s.trim().startsWith(";")||s.trim().startsWith("//")||s.equals("")) {comments.add(s);}
			else if(s.contains("=")){
				int index=s.indexOf("=");
				this.setKey(s.substring(0,index).trim());
				this.setValue(s.substring(index+1).trim());
			}
		}
	}




	/**<P>函数注释：getComments()</p>
	 *█<p>———————————————————————————————————————————</p> 
	 *█<p>作者：何明辉</p>
	 *█<p>时间：2013-5-4-下午2:40:25</p>
	 *█<p>类型：get方法</p>
	 *█<p>用途：该方法用于返回comments</p>
	 *█<p>备注：***</p> 
	 *█<p>抛出异常：[]</p>
	 *█<p>触发事件：[]</p>
	 *█<p>示例：</p>
	 *█<p>———————————————————————————————————————————</p>
	 *█   
	 *█<p>———————————————————————————————————————————</p>
	 *█<p>return comments</p>
	 */
	public List<String> getComments() {
		return this.comments;
	}



	/**<P>函数注释：setComments()</p>
	 *█<p>———————————————————————————————————————————</p> 
	 *█<p>作者：何明辉</p>
	 *█<p>时间：2013-5-4-下午2:40:25</p>
	 *█<p>类型：set方法</p>
	 *█<p>用途：该方法用于设置comments</p>
	 *█<p>备注：***</p> 
	 *█<p>抛出异常：[]</p>
	 *█<p>触发事件：[]</p>
	 *█<p>示例：</p>
	 *█<p>———————————————————————————————————————————</p>
	 *█   
	 *█<p>———————————————————————————————————————————</p>
	 *█<p>comments  comments</p>
	 */
	public void setComments(List<String> comments) {
		this.comments=comments;
		for(String s:this.comments) {
			if (!(s.trim().startsWith("#") || s.trim().startsWith(";") || s.trim().startsWith("//"))) {
				this.comments.set(this.comments.indexOf(s), "#"+s);
			}
		}
	}



	/**<P>函数注释：getKey()</p>
	 *█<p>———————————————————————————————————————————</p> 
	 *█<p>作者：何明辉</p>
	 *█<p>时间：2013-5-4-下午2:40:25</p>
	 *█<p>类型：get方法</p>
	 *█<p>用途：该方法用于返回key</p>
	 *█<p>备注：***</p> 
	 *█<p>抛出异常：[]</p>
	 *█<p>触发事件：[]</p>
	 *█<p>示例：</p>
	 *█<p>———————————————————————————————————————————</p>
	 *█   
	 *█<p>———————————————————————————————————————————</p>
	 *█<p>return key</p>
	 */
	public String getKey() {
		return this.key;
	}



	/**<P>函数注释：setKey()</p>
	 *█<p>———————————————————————————————————————————</p> 
	 *█<p>作者：何明辉</p>
	 *█<p>时间：2013-5-4-下午2:40:25</p>
	 *█<p>类型：set方法</p>
	 *█<p>用途：该方法用于设置key</p>
	 *█<p>备注：***</p> 
	 *█<p>抛出异常：[]</p>
	 *█<p>触发事件：[]</p>
	 *█<p>示例：</p>
	 *█<p>———————————————————————————————————————————</p>
	 *█   
	 *█<p>———————————————————————————————————————————</p>
	 *█<p>key  key</p>
	 */
	public void setKey(String key) {
		this.key=key;
	}



	/**<P>函数注释：getValue()</p>
	 *█<p>———————————————————————————————————————————</p> 
	 *█<p>作者：何明辉</p>
	 *█<p>时间：2013-5-4-下午2:40:25</p>
	 *█<p>类型：get方法</p>
	 *█<p>用途：该方法用于返回value</p>
	 *█<p>备注：***</p> 
	 *█<p>抛出异常：[]</p>
	 *█<p>触发事件：[]</p>
	 *█<p>示例：</p>
	 *█<p>———————————————————————————————————————————</p>
	 *█   
	 *█<p>———————————————————————————————————————————</p>
	 *█<p>return value</p>
	 */
	public String getValue() {
		return this.value;
	}



	/**<P>函数注释：setValue()</p>
	 *█<p>———————————————————————————————————————————</p> 
	 *█<p>作者：何明辉</p>
	 *█<p>时间：2013-5-4-下午2:40:25</p>
	 *█<p>类型：set方法</p>
	 *█<p>用途：该方法用于设置value</p>
	 *█<p>备注：***</p> 
	 *█<p>抛出异常：[]</p>
	 *█<p>触发事件：[]</p>
	 *█<p>示例：</p>
	 *█<p>———————————————————————————————————————————</p>
	 *█   
	 *█<p>———————————————————————————————————————————</p>
	 *█<p>value  value</p>
	 */
	public void setValue(String value) {
		this.value=value;
	}


	public String toString() {
		String spliter=System.getProperty("line.separator");
		StringBuilder bu=new StringBuilder();
		for (String s : this.comments) {
			if(!(s.trim().startsWith("#")||s.trim().startsWith(";")||s.trim().startsWith("//"))) {s="#"+s;}
			if(s.equals("#")||s.equals("//")||s.equals(";")) {continue;};
			bu.append(s).append(spliter);
		}
		if(this.getKey()==null||"".equals(this.getKey())) {bu.append(spliter);}
		else bu.append(this.getKey()+"="+this.getValue());
		return bu.toString().trim();
	}

}
