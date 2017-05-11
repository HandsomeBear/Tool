package HHH.HINI;


import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.Date;
import java.util.Map;








/**<P>文件注释：INIFile.java</p>
 *█<p>———————————————————————————————————————————</p> 
 *█<p>作者：何明辉</p>
 *█<p>时间：2013-5-6-下午5:26:10</p>
 *█<p>类型：文件</p>
 *█<p>用途：该文件用于INI文件的处理</p>
 *█<p>备注：***</p> 
 *█<p>抛出异常：[]</p>
 *█<p>触发事件：[]</p>
 *█<p>示例：</p>
 *█<p>———————————————————————————————————————————</p>
 *█  
 *█<p>———————————————————————————————————————————</p>
 */
/**<P>类型注释：</p>
 *█<p>———————————————————————————————————————————</p> 
 *█<p>作者：何明辉</p>
 *█<p>时间：2013-5-6-下午5:26:10</p>
 *█<p>类型：INIFile</p>
 *█<p>用途：该类型用于</p>
 *█<p>备注：该类型涉及到[0]张数据表</p>
 *█<p>涉及到的数据表：</p>
 *█<p>———————————————————————————————————————————</p>
 *█ 
 *█ 
 *█<p>———————————————————————————————————————————</p>
 *█<p>备注：***</p> 
 *█<p>抛出异常：[]</p>
 *█<p>触发事件：[]</p>
 *█<p>示例：</p>
 *█<p>———————————————————————————————————————————</p>
 *█   
 *█<p>———————————————————————————————————————————</p>
 */
public class INIFile implements INIFileInterface{

	private INIImpl util=null;




	/**<P>函数注释：INIFile()</p>
	 *█<p>———————————————————————————————————————————</p> 
	 *█<p>作者：何明辉</p>
	 *█<p>时间：2013-5-6-下午5:26:50</p>
	 *█<p>类型：构造函数</p>
	 *█<p>用途：该构造函数用于</p>
	 *█<p>备注：***</p> 
	 *█<p>抛出异常：[]</p>
	 *█<p>触发事件：[]</p>
	 *█<p>示例：</p>
	 *█<p>———————————————————————————————————————————</p>
	 *█   
	 *█<p>———————————————————————————————————————————</p>
	 * @throws Exception 
	 */
	public INIFile(String file){
		util=INIUtil.getInstance(file);
	}

	public INIFile(String file,String charset){
		util=INIUtil.getInstance(file,charset);
	}

	public INIFile(InputStream in){
		util=new INIImpl();
		byte data[]=new byte[5242880];
		int length;
		try {
			length=in.read(data);
			Boolean isUtf8=false;
			if(data[0] == (byte) 0xEF && data[1] == (byte) 0xBB && data[2] == (byte) 0xBF) {
				isUtf8=true;
			}
			util.initial(new String(data,0,length,isUtf8?"UTF-8":"gbk"));
		}
		catch (IOException exception) {
//			TextLogger.getLogger().log(TextLogger.WARNING,exception.getMessage());
		}
	}


	/**<P>函数注释：addSection()</p>
	 *█<p>———————————————————————————————————————————</p> 
	 *█<p>作者：何明辉</p>
	 *█<p>时间：2013-5-6-下午5:28:11</p>
	 *█<p>类型：方法</p>
	 *█<p>用途：该方法代理了父类方法</p>
	 *█<p>备注：***</p> 
	 *█<p>抛出异常：[]</p>
	 *█<p>触发事件：[]</p>
	 *█<p>示例：</p>
	 *█<p>———————————————————————————————————————————</p>
	 *█   
	 *█<p>———————————————————————————————————————————</p>
	 *@see INIUtil#addSection(java.lang.String, java.lang.String)
	 */
	public void addSection(String sectionName, String comment) {
		this.util.addSection(sectionName, comment);
	}





	/**<P>函数注释：addSection()</p>
	 *█<p>———————————————————————————————————————————</p> 
	 *█<p>作者：何明辉</p>
	 *█<p>时间：2013-5-6-下午5:28:11</p>
	 *█<p>类型：方法</p>
	 *█<p>用途：该方法代理了父类方法</p>
	 *█<p>备注：***</p> 
	 *█<p>抛出异常：[]</p>
	 *█<p>触发事件：[]</p>
	 *█<p>示例：</p>
	 *█<p>———————————————————————————————————————————</p>
	 *█   
	 *█<p>———————————————————————————————————————————</p>
	 *@see INIUtil#addSection(INISection)
	 */
	public void addSection(INISection section) {
		this.util.addSection(section);
	}





	/**<P>函数注释：getSectionComment()</p>
	 *█<p>———————————————————————————————————————————</p> 
	 *█<p>作者：何明辉</p>
	 *█<p>时间：2013-5-6-下午5:28:11</p>
	 *█<p>类型：方法</p>
	 *█<p>用途：该方法代理了父类方法</p>
	 *█<p>备注：***</p> 
	 *█<p>抛出异常：[]</p>
	 *█<p>触发事件：[]</p>
	 *█<p>示例：</p>
	 *█<p>———————————————————————————————————————————</p>
	 *█   
	 *█<p>———————————————————————————————————————————</p>
	 *@see INIUtil#getSectionComment(java.lang.String)
	 */
	public String getSectionComment(String section) {
		return this.util.getSectionComment(section);
	}





	/**<P>函数注释：getSection()</p>
	 *█<p>———————————————————————————————————————————</p> 
	 *█<p>作者：何明辉</p>
	 *█<p>时间：2013-5-6-下午5:28:11</p>
	 *█<p>类型：方法</p>
	 *█<p>用途：该方法代理了父类方法</p>
	 *█<p>备注：***</p> 
	 *█<p>抛出异常：[]</p>
	 *█<p>触发事件：[]</p>
	 *█<p>示例：</p>
	 *█<p>———————————————————————————————————————————</p>
	 *█   
	 *█<p>———————————————————————————————————————————</p>
	 *@see INIUtil#getSection(java.lang.String)
	 */
	public INISection getSection(String name) {
		return this.util.getSection(name);
	}





	/**<P>函数注释：addProperty()</p>
	 *█<p>———————————————————————————————————————————</p> 
	 *█<p>作者：何明辉</p>
	 *█<p>时间：2013-5-6-下午5:28:11</p>
	 *█<p>类型：方法</p>
	 *█<p>用途：该方法代理了父类方法</p>
	 *█<p>备注：***</p> 
	 *█<p>抛出异常：[]</p>
	 *█<p>触发事件：[]</p>
	 *█<p>示例：</p>
	 *█<p>———————————————————————————————————————————</p>
	 *█   
	 *█<p>———————————————————————————————————————————</p>
	 *@see INIUtil#addProperty(java.lang.String, INIProperty)
	 */
	public void addProperty(String section, INIProperty property) {
		this.util.addProperty(section, property);
	}





	/**<P>函数注释：addProperty()</p>
	 *█<p>———————————————————————————————————————————</p> 
	 *█<p>作者：何明辉</p>
	 *█<p>时间：2013-5-6-下午5:28:11</p>
	 *█<p>类型：方法</p>
	 *█<p>用途：该方法代理了父类方法</p>
	 *█<p>备注：***</p> 
	 *█<p>抛出异常：[]</p>
	 *█<p>触发事件：[]</p>
	 *█<p>示例：</p>
	 *█<p>———————————————————————————————————————————</p>
	 *█   
	 *█<p>———————————————————————————————————————————</p>
	 *@see INIUtil#addProperty(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	public void addProperty(String section, String property, String value, String comment) {
		this.util.addProperty(section, property, value, comment);
	}





	/**<P>函数注释：equals()</p>
	 *█<p>———————————————————————————————————————————</p> 
	 *█<p>作者：何明辉</p>
	 *█<p>时间：2013-5-6-下午5:28:11</p>
	 *█<p>类型：方法</p>
	 *█<p>用途：该方法代理了父类方法</p>
	 *█<p>备注：***</p> 
	 *█<p>抛出异常：[]</p>
	 *█<p>触发事件：[]</p>
	 *█<p>示例：</p>
	 *█<p>———————————————————————————————————————————</p>
	 *█   
	 *█<p>———————————————————————————————————————————</p>
	 *@see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object arg0) {
		return this.util.equals(arg0);
	}





	/**<P>函数注释：getPropertyComment()</p>
	 *█<p>———————————————————————————————————————————</p> 
	 *█<p>作者：何明辉</p>
	 *█<p>时间：2013-5-6-下午5:28:11</p>
	 *█<p>类型：方法</p>
	 *█<p>用途：该方法代理了父类方法</p>
	 *█<p>备注：***</p> 
	 *█<p>抛出异常：[]</p>
	 *█<p>触发事件：[]</p>
	 *█<p>示例：</p>
	 *█<p>———————————————————————————————————————————</p>
	 *█   
	 *█<p>———————————————————————————————————————————</p>
	 *@see INIUtil#getPropertyComment(java.lang.String, java.lang.String)
	 */
	public String getPropertyComment(String section, String property) {
		return this.util.getPropertyComment(section, property);
	}





	/**<P>函数注释：getBooleanProperty()</p>
	 *█<p>———————————————————————————————————————————</p> 
	 *█<p>作者：何明辉</p>
	 *█<p>时间：2013-5-6-下午5:28:11</p>
	 *█<p>类型：方法</p>
	 *█<p>用途：该方法代理了父类方法</p>
	 *█<p>备注：***</p> 
	 *█<p>抛出异常：[]</p>
	 *█<p>触发事件：[]</p>
	 *█<p>示例：</p>
	 *█<p>———————————————————————————————————————————</p>
	 *█   
	 *█<p>———————————————————————————————————————————</p>
	 *@see INIUtil#getBooleanProperty(java.lang.String, java.lang.String)
	 */
	public Boolean getBooleanProperty(String section, String property) {
		return this.util.getBooleanProperty(section, property);
	}





	/**<P>函数注释：getDateProperty()</p>
	 *█<p>———————————————————————————————————————————</p> 
	 *█<p>作者：何明辉</p>
	 *█<p>时间：2013-5-6-下午5:28:11</p>
	 *█<p>类型：方法</p>
	 *█<p>用途：该方法代理了父类方法</p>
	 *█<p>备注：***</p> 
	 *█<p>抛出异常：[]</p>
	 *█<p>触发事件：[]</p>
	 *█<p>示例：</p>
	 *█<p>———————————————————————————————————————————</p>
	 *█   
	 *█<p>———————————————————————————————————————————</p>
	 *@see INIUtil#getDateProperty(java.lang.String, java.lang.String)
	 */
	public Date getDateProperty(String section, String property) throws ParseException {
		return this.util.getDateProperty(section, property);
	}





	/**<P>函数注释：getDoubleProperty()</p>
	 *█<p>———————————————————————————————————————————</p> 
	 *█<p>作者：何明辉</p>
	 *█<p>时间：2013-5-6-下午5:28:11</p>
	 *█<p>类型：方法</p>
	 *█<p>用途：该方法代理了父类方法</p>
	 *█<p>备注：***</p> 
	 *█<p>抛出异常：[]</p>
	 *█<p>触发事件：[]</p>
	 *█<p>示例：</p>
	 *█<p>———————————————————————————————————————————</p>
	 *█   
	 *█<p>———————————————————————————————————————————</p>
	 *@see INIUtil#getDoubleProperty(java.lang.String, java.lang.String)
	 */
	public Double getDoubleProperty(String section, String property) {
		return this.util.getDoubleProperty(section, property);
	}





	/**<P>函数注释：getIntegerProperty()</p>
	 *█<p>———————————————————————————————————————————</p> 
	 *█<p>作者：何明辉</p>
	 *█<p>时间：2013-5-6-下午5:28:11</p>
	 *█<p>类型：方法</p>
	 *█<p>用途：该方法代理了父类方法</p>
	 *█<p>备注：***</p> 
	 *█<p>抛出异常：[]</p>
	 *█<p>触发事件：[]</p>
	 *█<p>示例：</p>
	 *█<p>———————————————————————————————————————————</p>
	 *█   
	 *█<p>———————————————————————————————————————————</p>
	 *@see INIUtil#getIntegerProperty(java.lang.String, java.lang.String)
	 */
	public Integer getIntegerProperty(String section, String property) {
		return this.util.getIntegerProperty(section, property);
	}





	/**<P>函数注释：getLongProperty()</p>
	 *█<p>———————————————————————————————————————————</p> 
	 *█<p>作者：何明辉</p>
	 *█<p>时间：2013-5-6-下午5:28:11</p>
	 *█<p>类型：方法</p>
	 *█<p>用途：该方法代理了父类方法</p>
	 *█<p>备注：***</p> 
	 *█<p>抛出异常：[]</p>
	 *█<p>触发事件：[]</p>
	 *█<p>示例：</p>
	 *█<p>———————————————————————————————————————————</p>
	 *█   
	 *█<p>———————————————————————————————————————————</p>
	 *@see INIUtil#getLongProperty(java.lang.String, java.lang.String)
	 */
	public Long getLongProperty(String section, String property) {
		return this.util.getLongProperty(section, property);
	}





	/**<P>函数注释：getStringProperty()</p>
	 *█<p>———————————————————————————————————————————</p> 
	 *█<p>作者：何明辉</p>
	 *█<p>时间：2013-5-6-下午5:28:11</p>
	 *█<p>类型：方法</p>
	 *█<p>用途：该方法代理了父类方法</p>
	 *█<p>备注：***</p> 
	 *█<p>抛出异常：[]</p>
	 *█<p>触发事件：[]</p>
	 *█<p>示例：</p>
	 *█<p>———————————————————————————————————————————</p>
	 *█   
	 *█<p>———————————————————————————————————————————</p>
	 *@see INIUtil#getStringProperty(java.lang.String, java.lang.String)
	 */
	public String getStringProperty(String section, String property) {
		return this.util.getStringProperty(section, property);
	}





	/**<P>函数注释：getTimestampProperty()</p>
	 *█<p>———————————————————————————————————————————</p> 
	 *█<p>作者：何明辉</p>
	 *█<p>时间：2013-5-6-下午5:28:11</p>
	 *█<p>类型：方法</p>
	 *█<p>用途：该方法代理了父类方法</p>
	 *█<p>备注：***</p> 
	 *█<p>抛出异常：[]</p>
	 *█<p>触发事件：[]</p>
	 *█<p>示例：</p>
	 *█<p>———————————————————————————————————————————</p>
	 *█   
	 *█<p>———————————————————————————————————————————</p>
	 *@see INIUtil#getTimestampProperty(java.lang.String, java.lang.String)
	 */
	public Date getTimestampProperty(String section, String property) {
		return this.util.getTimestampProperty(section, property);
	}





	/**<P>函数注释：getProperties()</p>
	 *█<p>———————————————————————————————————————————</p> 
	 *█<p>作者：何明辉</p>
	 *█<p>时间：2013-5-6-下午5:28:11</p>
	 *█<p>类型：方法</p>
	 *█<p>用途：该方法代理了父类方法</p>
	 *█<p>备注：***</p> 
	 *█<p>抛出异常：[]</p>
	 *█<p>触发事件：[]</p>
	 *█<p>示例：</p>
	 *█<p>———————————————————————————————————————————</p>
	 *█   
	 *█<p>———————————————————————————————————————————</p>
	 *@see INIUtil#getProperties(java.lang.String)
	 */
	public Map getProperties(String section) {
		return this.util.getProperties(section);
	}





	/**<P>函数注释：getAllSectionNames()</p>
	 *█<p>———————————————————————————————————————————</p> 
	 *█<p>作者：何明辉</p>
	 *█<p>时间：2013-5-6-下午5:28:11</p>
	 *█<p>类型：方法</p>
	 *█<p>用途：该方法代理了父类方法</p>
	 *█<p>备注：***</p> 
	 *█<p>抛出异常：[]</p>
	 *█<p>触发事件：[]</p>
	 *█<p>示例：</p>
	 *█<p>———————————————————————————————————————————</p>
	 *█   
	 *█<p>———————————————————————————————————————————</p>
	 *@see INIUtil#getAllSectionNames()
	 */
	public String[] getAllSectionNames() {
		return this.util.getAllSectionNames();
	}





	/**<P>函数注释：getTotalSections()</p>
	 *█<p>———————————————————————————————————————————</p> 
	 *█<p>作者：何明辉</p>
	 *█<p>时间：2013-5-6-下午5:28:11</p>
	 *█<p>类型：方法</p>
	 *█<p>用途：该方法代理了父类方法</p>
	 *█<p>备注：***</p> 
	 *█<p>抛出异常：[]</p>
	 *█<p>触发事件：[]</p>
	 *█<p>示例：</p>
	 *█<p>———————————————————————————————————————————</p>
	 *█   
	 *█<p>———————————————————————————————————————————</p>
	 *@see INIUtil#getTotalSections()
	 */
	public int getTotalSections() {
		return this.util.getTotalSections();
	}





	/**<P>函数注释：getFileName()</p>
	 *█<p>———————————————————————————————————————————</p> 
	 *█<p>作者：何明辉</p>
	 *█<p>时间：2013-5-6-下午5:28:11</p>
	 *█<p>类型：方法</p>
	 *█<p>用途：该方法代理了父类方法</p>
	 *█<p>备注：***</p> 
	 *█<p>抛出异常：[]</p>
	 *█<p>触发事件：[]</p>
	 *█<p>示例：</p>
	 *█<p>———————————————————————————————————————————</p>
	 *█   
	 *█<p>———————————————————————————————————————————</p>
	 *@see INIUtil#getFileName()
	 */
	public String getFileName() {
		return this.util.getFileName();
	}





	/**<P>函数注释：getPropertyNames()</p>
	 *█<p>———————————————————————————————————————————</p> 
	 *█<p>作者：何明辉</p>
	 *█<p>时间：2013-5-6-下午5:28:11</p>
	 *█<p>类型：方法</p>
	 *█<p>用途：该方法代理了父类方法</p>
	 *█<p>备注：***</p> 
	 *█<p>抛出异常：[]</p>
	 *█<p>触发事件：[]</p>
	 *█<p>示例：</p>
	 *█<p>———————————————————————————————————————————</p>
	 *█   
	 *█<p>———————————————————————————————————————————</p>
	 *@see INIUtil#getPropertyNames(java.lang.String)
	 */
	public String[] getPropertyNames(String section) {
		return this.util.getPropertyNames(section);
	}





	/**<P>函数注释：getStringComment()</p>
	 *█<p>———————————————————————————————————————————</p> 
	 *█<p>作者：何明辉</p>
	 *█<p>时间：2013-5-6-下午5:28:11</p>
	 *█<p>类型：方法</p>
	 *█<p>用途：该方法代理了父类方法</p>
	 *█<p>备注：***</p> 
	 *█<p>抛出异常：[]</p>
	 *█<p>触发事件：[]</p>
	 *█<p>示例：</p>
	 *█<p>———————————————————————————————————————————</p>
	 *█   
	 *█<p>———————————————————————————————————————————</p>
	 *@see INIUtil#getStringComment(java.lang.String)
	 */
	public String getStringComment(String section) {
		return this.util.getStringComment(section);
	}





	/**<P>函数注释：getStringComment()</p>
	 *█<p>———————————————————————————————————————————</p> 
	 *█<p>作者：何明辉</p>
	 *█<p>时间：2013-5-6-下午5:28:11</p>
	 *█<p>类型：方法</p>
	 *█<p>用途：该方法代理了父类方法</p>
	 *█<p>备注：***</p> 
	 *█<p>抛出异常：[]</p>
	 *█<p>触发事件：[]</p>
	 *█<p>示例：</p>
	 *█<p>———————————————————————————————————————————</p>
	 *█   
	 *█<p>———————————————————————————————————————————</p>
	 *@see INIUtil#getStringComment(java.lang.String, java.lang.String)
	 */
	public String getStringComment(String section, String property) {
		return this.util.getStringComment(section, property);
	}





	/**<P>函数注释：hashCode()</p>
	 *█<p>———————————————————————————————————————————</p> 
	 *█<p>作者：何明辉</p>
	 *█<p>时间：2013-5-6-下午5:28:11</p>
	 *█<p>类型：方法</p>
	 *█<p>用途：该方法代理了父类方法</p>
	 *█<p>备注：***</p> 
	 *█<p>抛出异常：[]</p>
	 *█<p>触发事件：[]</p>
	 *█<p>示例：</p>
	 *█<p>———————————————————————————————————————————</p>
	 *█   
	 *█<p>———————————————————————————————————————————</p>
	 *@see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return this.util.hashCode();
	}





	/**<P>函数注释：initial()</p>
	 *█<p>———————————————————————————————————————————</p> 
	 *█<p>作者：何明辉</p>
	 *█<p>时间：2013-5-6-下午5:28:11</p>
	 *█<p>类型：方法</p>
	 *█<p>用途：该方法代理了父类方法</p>
	 *█<p>备注：***</p> 
	 *█<p>抛出异常：[]</p>
	 *█<p>触发事件：[]</p>
	 *█<p>示例：</p>
	 *█<p>———————————————————————————————————————————</p>
	 *█   
	 *█<p>———————————————————————————————————————————</p>
	 *@see INIUtil#initial()
	 */
	public void initial() throws IOException {
		this.util.initial();
	}





	/**<P>函数注释：save()</p>
	 *█<p>———————————————————————————————————————————</p> 
	 *█<p>作者：何明辉</p>
	 *█<p>时间：2013-5-6-下午5:28:11</p>
	 *█<p>类型：方法</p>
	 *█<p>用途：该方法代理了父类方法</p>
	 *█<p>备注：***</p> 
	 *█<p>抛出异常：[]</p>
	 *█<p>触发事件：[]</p>
	 *█<p>示例：</p>
	 *█<p>———————————————————————————————————————————</p>
	 *█   
	 *█<p>———————————————————————————————————————————</p>
	 *@see INIUtil#save()
	 */
	public boolean save()  {
		return this.util.save();
	}





	/**<P>函数注释：toString()</p>
	 *█<p>———————————————————————————————————————————</p> 
	 *█<p>作者：何明辉</p>
	 *█<p>时间：2013-5-6-下午5:28:11</p>
	 *█<p>类型：方法</p>
	 *█<p>用途：该方法代理了父类方法</p>
	 *█<p>备注：***</p> 
	 *█<p>抛出异常：[]</p>
	 *█<p>触发事件：[]</p>
	 *█<p>示例：</p>
	 *█<p>———————————————————————————————————————————</p>
	 *█   
	 *█<p>———————————————————————————————————————————</p>
	 *@see INIUtil#toString()
	 */
	public String toString() {
		return this.util.toString();
	}





	/**<P>函数注释：removeSection()</p>
	 *█<p>———————————————————————————————————————————</p> 
	 *█<p>作者：何明辉</p>
	 *█<p>时间：2013-5-6-下午5:28:11</p>
	 *█<p>类型：方法</p>
	 *█<p>用途：该方法代理了父类方法</p>
	 *█<p>备注：***</p> 
	 *█<p>抛出异常：[]</p>
	 *█<p>触发事件：[]</p>
	 *█<p>示例：</p>
	 *█<p>———————————————————————————————————————————</p>
	 *█   
	 *█<p>———————————————————————————————————————————</p>
	 *@see INIUtil#removeSection(java.lang.String)
	 */
	public void removeSection(String sectionName) {
		this.util.removeSection(sectionName);
	}





	/**<P>函数注释：removeSection()</p>
	 *█<p>———————————————————————————————————————————</p> 
	 *█<p>作者：何明辉</p>
	 *█<p>时间：2013-5-6-下午5:28:11</p>
	 *█<p>类型：方法</p>
	 *█<p>用途：该方法代理了父类方法</p>
	 *█<p>备注：***</p> 
	 *█<p>抛出异常：[]</p>
	 *█<p>触发事件：[]</p>
	 *█<p>示例：</p>
	 *█<p>———————————————————————————————————————————</p>
	 *█   
	 *█<p>———————————————————————————————————————————</p>
	 *@see INIUtil#removeSection(INISection)
	 */
	public void removeSection(INISection section) {
		this.util.removeSection(section);
	}





	/**<P>函数注释：insertSection()</p>
	 *█<p>———————————————————————————————————————————</p> 
	 *█<p>作者：何明辉</p>
	 *█<p>时间：2013-5-6-下午5:28:11</p>
	 *█<p>类型：方法</p>
	 *█<p>用途：该方法代理了父类方法</p>
	 *█<p>备注：***</p> 
	 *█<p>抛出异常：[]</p>
	 *█<p>触发事件：[]</p>
	 *█<p>示例：</p>
	 *█<p>———————————————————————————————————————————</p>
	 *█   
	 *█<p>———————————————————————————————————————————</p>
	 *@see INIUtil#insertSection(int, java.lang.String, java.lang.String)
	 */
	public int insertSection(int index, String sectionName, String comment) {
		return this.util.insertSection(index, sectionName, comment);
	}





	/**<P>函数注释：insertSection()</p>
	 *█<p>———————————————————————————————————————————</p> 
	 *█<p>作者：何明辉</p>
	 *█<p>时间：2013-5-6-下午5:28:11</p>
	 *█<p>类型：方法</p>
	 *█<p>用途：该方法代理了父类方法</p>
	 *█<p>备注：***</p> 
	 *█<p>抛出异常：[]</p>
	 *█<p>触发事件：[]</p>
	 *█<p>示例：</p>
	 *█<p>———————————————————————————————————————————</p>
	 *█   
	 *█<p>———————————————————————————————————————————</p>
	 *@see INIUtil#insertSection(int, INISection)
	 */
	public int insertSection(int index, INISection section) {
		return this.util.insertSection(index, section);
	}





	/**<P>函数注释：setSectionComment()</p>
	 *█<p>———————————————————————————————————————————</p> 
	 *█<p>作者：何明辉</p>
	 *█<p>时间：2013-5-6-下午5:28:11</p>
	 *█<p>类型：方法</p>
	 *█<p>用途：该方法代理了父类方法</p>
	 *█<p>备注：***</p> 
	 *█<p>抛出异常：[]</p>
	 *█<p>触发事件：[]</p>
	 *█<p>示例：</p>
	 *█<p>———————————————————————————————————————————</p>
	 *█   
	 *█<p>———————————————————————————————————————————</p>
	 *@see INIUtil#setSectionComment(java.lang.String, java.lang.String)
	 */
	public void setSectionComment(String section, String comment) {
		this.util.setSectionComment(section, comment);
	}





	/**<P>函数注释：insertProperty()</p>
	 *█<p>———————————————————————————————————————————</p> 
	 *█<p>作者：何明辉</p>
	 *█<p>时间：2013-5-6-下午5:28:11</p>
	 *█<p>类型：方法</p>
	 *█<p>用途：该方法代理了父类方法</p>
	 *█<p>备注：***</p> 
	 *█<p>抛出异常：[]</p>
	 *█<p>触发事件：[]</p>
	 *█<p>示例：</p>
	 *█<p>———————————————————————————————————————————</p>
	 *█   
	 *█<p>———————————————————————————————————————————</p>
	 *@see INIUtil#insertProperty(java.lang.String, int, INIProperty)
	 */
	public void insertProperty(String section, int index, INIProperty property) {
		this.util.insertProperty(section, index, property);
	}





	/**<P>函数注释：insertProperty()</p>
	 *█<p>———————————————————————————————————————————</p> 
	 *█<p>作者：何明辉</p>
	 *█<p>时间：2013-5-6-下午5:28:11</p>
	 *█<p>类型：方法</p>
	 *█<p>用途：该方法代理了父类方法</p>
	 *█<p>备注：***</p> 
	 *█<p>抛出异常：[]</p>
	 *█<p>触发事件：[]</p>
	 *█<p>示例：</p>
	 *█<p>———————————————————————————————————————————</p>
	 *█   
	 *█<p>———————————————————————————————————————————</p>
	 *@see INIUtil#insertProperty(java.lang.String, int, java.lang.String, java.lang.String, java.lang.String)
	 */
	public void insertProperty(String section, int index, String property, String value, String comment) {
		this.util.insertProperty(section, index, property, value, comment);
	}





	/**<P>函数注释：removeProperty()</p>
	 *█<p>———————————————————————————————————————————</p> 
	 *█<p>作者：何明辉</p>
	 *█<p>时间：2013-5-6-下午5:28:11</p>
	 *█<p>类型：方法</p>
	 *█<p>用途：该方法代理了父类方法</p>
	 *█<p>备注：***</p> 
	 *█<p>抛出异常：[]</p>
	 *█<p>触发事件：[]</p>
	 *█<p>示例：</p>
	 *█<p>———————————————————————————————————————————</p>
	 *█   
	 *█<p>———————————————————————————————————————————</p>
	 *@see INIUtil#removeProperty(java.lang.String, INIProperty)
	 */
	public void removeProperty(String section, INIProperty property) {
		this.util.removeProperty(section, property);
	}





	/**<P>函数注释：removeProperty()</p>
	 *█<p>———————————————————————————————————————————</p> 
	 *█<p>作者：何明辉</p>
	 *█<p>时间：2013-5-6-下午5:28:11</p>
	 *█<p>类型：方法</p>
	 *█<p>用途：该方法代理了父类方法</p>
	 *█<p>备注：***</p> 
	 *█<p>抛出异常：[]</p>
	 *█<p>触发事件：[]</p>
	 *█<p>示例：</p>
	 *█<p>———————————————————————————————————————————</p>
	 *█   
	 *█<p>———————————————————————————————————————————</p>
	 *@see INIUtil#removeProperty(java.lang.String, java.lang.String)
	 */
	public void removeProperty(String sectionName, String property) {
		this.util.removeProperty(sectionName, property);
	}





	/**<P>函数注释：setPropertyComment()</p>
	 *█<p>———————————————————————————————————————————</p> 
	 *█<p>作者：何明辉</p>
	 *█<p>时间：2013-5-6-下午5:28:11</p>
	 *█<p>类型：方法</p>
	 *█<p>用途：该方法代理了父类方法</p>
	 *█<p>备注：***</p> 
	 *█<p>抛出异常：[]</p>
	 *█<p>触发事件：[]</p>
	 *█<p>示例：</p>
	 *█<p>———————————————————————————————————————————</p>
	 *█   
	 *█<p>———————————————————————————————————————————</p>
	 *@see INIUtil#setPropertyComment(java.lang.String, java.lang.String, java.lang.String)
	 */
	public void setPropertyComment(String section, String property, String comment) {
		this.util.setPropertyComment(section, property, comment);
	}





	/**<P>函数注释：setBooleanProperty()</p>
	 *█<p>———————————————————————————————————————————</p> 
	 *█<p>作者：何明辉</p>
	 *█<p>时间：2013-5-6-下午5:28:11</p>
	 *█<p>类型：方法</p>
	 *█<p>用途：该方法代理了父类方法</p>
	 *█<p>备注：***</p> 
	 *█<p>抛出异常：[]</p>
	 *█<p>触发事件：[]</p>
	 *█<p>示例：</p>
	 *█<p>———————————————————————————————————————————</p>
	 *█   
	 *█<p>———————————————————————————————————————————</p>
	 *@see INIUtil#setBooleanProperty(java.lang.String, java.lang.String, java.lang.Boolean, java.lang.String)
	 */
	public void setBooleanProperty(String sectionName, String property, Boolean value, String comment) {
		this.util.setBooleanProperty(sectionName, property, value, comment);
	}





	/**<P>函数注释：setDateFormat()</p>
	 *█<p>———————————————————————————————————————————</p> 
	 *█<p>作者：何明辉</p>
	 *█<p>时间：2013-5-6-下午5:28:11</p>
	 *█<p>类型：方法</p>
	 *█<p>用途：该方法代理了父类方法</p>
	 *█<p>备注：***</p> 
	 *█<p>抛出异常：[]</p>
	 *█<p>触发事件：[]</p>
	 *█<p>示例：</p>
	 *█<p>———————————————————————————————————————————</p>
	 *█   
	 *█<p>———————————————————————————————————————————</p>
	 *@see INIUtil#setDateFormat(java.lang.String)
	 */
	public void setDateFormat(String format) {
		this.util.setDateFormat(format);
	}





	/**<P>函数注释：setDateProperty()</p>
	 *█<p>———————————————————————————————————————————</p> 
	 *█<p>作者：何明辉</p>
	 *█<p>时间：2013-5-6-下午5:28:11</p>
	 *█<p>类型：方法</p>
	 *█<p>用途：该方法代理了父类方法</p>
	 *█<p>备注：***</p> 
	 *█<p>抛出异常：[]</p>
	 *█<p>触发事件：[]</p>
	 *█<p>示例：</p>
	 *█<p>———————————————————————————————————————————</p>
	 *█   
	 *█<p>———————————————————————————————————————————</p>
	 *@see INIUtil#setDateProperty(java.lang.String, java.lang.String, java.util.Date, java.lang.String)
	 */
	public void setDateProperty(String sectionName, String property, Date value, String comment) {
		this.util.setDateProperty(sectionName, property, value, comment);
	}





	/**<P>函数注释：setDoubleProperty()</p>
	 *█<p>———————————————————————————————————————————</p> 
	 *█<p>作者：何明辉</p>
	 *█<p>时间：2013-5-6-下午5:28:11</p>
	 *█<p>类型：方法</p>
	 *█<p>用途：该方法代理了父类方法</p>
	 *█<p>备注：***</p> 
	 *█<p>抛出异常：[]</p>
	 *█<p>触发事件：[]</p>
	 *█<p>示例：</p>
	 *█<p>———————————————————————————————————————————</p>
	 *█   
	 *█<p>———————————————————————————————————————————</p>
	 *@see INIUtil#setDoubleProperty(java.lang.String, java.lang.String, java.lang.Double, java.lang.String)
	 */
	public void setDoubleProperty(String sectionName, String property, Double value, String comment) {
		this.util.setDoubleProperty(sectionName, property, value, comment);
	}





	/**<P>函数注释：setIntegerProperty()</p>
	 *█<p>———————————————————————————————————————————</p> 
	 *█<p>作者：何明辉</p>
	 *█<p>时间：2013-5-6-下午5:28:11</p>
	 *█<p>类型：方法</p>
	 *█<p>用途：该方法代理了父类方法</p>
	 *█<p>备注：***</p> 
	 *█<p>抛出异常：[]</p>
	 *█<p>触发事件：[]</p>
	 *█<p>示例：</p>
	 *█<p>———————————————————————————————————————————</p>
	 *█   
	 *█<p>———————————————————————————————————————————</p>
	 *@see INIUtil#setIntegerProperty(java.lang.String, java.lang.String, java.lang.Integer, java.lang.String)
	 */
	public void setIntegerProperty(String sectionName, String property, Integer value, String comment) {
		this.util.setIntegerProperty(sectionName, property, value, comment);
	}





	/**<P>函数注释：setLongProperty()</p>
	 *█<p>———————————————————————————————————————————</p> 
	 *█<p>作者：何明辉</p>
	 *█<p>时间：2013-5-6-下午5:28:11</p>
	 *█<p>类型：方法</p>
	 *█<p>用途：该方法代理了父类方法</p>
	 *█<p>备注：***</p> 
	 *█<p>抛出异常：[]</p>
	 *█<p>触发事件：[]</p>
	 *█<p>示例：</p>
	 *█<p>———————————————————————————————————————————</p>
	 *█   
	 *█<p>———————————————————————————————————————————</p>
	 *@see INIUtil#setLongProperty(java.lang.String, java.lang.String, java.lang.Long, java.lang.String)
	 */
	public void setLongProperty(String sectionName, String property, Long value, String comment) {
		this.util.setLongProperty(sectionName, property, value, comment);
	}





	/**<P>函数注释：setStringProperty()</p>
	 *█<p>———————————————————————————————————————————</p> 
	 *█<p>作者：何明辉</p>
	 *█<p>时间：2013-5-6-下午5:28:11</p>
	 *█<p>类型：方法</p>
	 *█<p>用途：该方法代理了父类方法</p>
	 *█<p>备注：***</p> 
	 *█<p>抛出异常：[]</p>
	 *█<p>触发事件：[]</p>
	 *█<p>示例：</p>
	 *█<p>———————————————————————————————————————————</p>
	 *█   
	 *█<p>———————————————————————————————————————————</p>
	 *@see INIUtil#setStringProperty(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	public void setStringProperty(String sectionName, String property, String value, String comment) {
		this.util.setStringProperty(sectionName, property, value, comment);
	}





	/**<P>函数注释：setTimestampFormat()</p>
	 *█<p>———————————————————————————————————————————</p> 
	 *█<p>作者：何明辉</p>
	 *█<p>时间：2013-5-6-下午5:28:11</p>
	 *█<p>类型：方法</p>
	 *█<p>用途：该方法代理了父类方法</p>
	 *█<p>备注：***</p> 
	 *█<p>抛出异常：[]</p>
	 *█<p>触发事件：[]</p>
	 *█<p>示例：</p>
	 *█<p>———————————————————————————————————————————</p>
	 *█   
	 *█<p>———————————————————————————————————————————</p>
	 *@see INIUtil#setTimestampFormat(java.lang.String)
	 */
	public void setTimestampFormat(String format) {
		this.util.setTimestampFormat(format);
	}





	/**<P>函数注释：setTimestampProperty()</p>
	 *█<p>———————————————————————————————————————————</p> 
	 *█<p>作者：何明辉</p>
	 *█<p>时间：2013-5-6-下午5:28:11</p>
	 *█<p>类型：方法</p>
	 *█<p>用途：该方法代理了父类方法</p>
	 *█<p>备注：***</p> 
	 *█<p>抛出异常：[]</p>
	 *█<p>触发事件：[]</p>
	 *█<p>示例：</p>
	 *█<p>———————————————————————————————————————————</p>
	 *█   
	 *█<p>———————————————————————————————————————————</p>
	 *@see INIUtil#setTimestampProperty(java.lang.String, java.lang.String, java.sql.Timestamp, java.lang.String)
	 */
	public void setTimestampProperty(String sectionName, String property, Timestamp value, String comment) {
		this.util.setTimestampProperty(sectionName, property, value, comment);
	}





}
