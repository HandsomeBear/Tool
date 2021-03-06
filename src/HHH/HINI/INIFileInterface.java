package HHH.HINI;


import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.Date;
import java.util.Map;








/**<P>文件注释：IINIFile.java</p>
 *█<p>———————————————————————————————————————————</p> 
 *█<p>作者：何明辉</p>
 *█<p>时间：2013-5-6-上午9:31:13</p>
 *█<p>类型：文件</p>
 *█<p>用途：该文件用于INIFile操作类的接口定义</p>
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
 *█<p>时间：2013-5-6-上午9:31:13</p>
 *█<p>类型：IINIFile</p>
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
public interface INIFileInterface {
	/**
	 * <P>函数注释：addSection()</p>
	 *█<p>———————————————————————————————————————————</p> 
	 *█<p>作者：何明辉</p>
	 *█<p>时间：2013-5-13-上午10:08:10</p>
	 *█<p>类型：方法</p>
	 *█<p>用途：该方法用于增加板块</p>
	 *█<p>备注：***</p> 
	 *█<p>抛出异常：[]</p>
	 *█<p>触发事件：[]</p>
	 *█<p>示例：</p>
	 *█<p>———————————————————————————————————————————</p>
	 *█   
	 *█<p>———————————————————————————————————————————</p>
	 *@param sectionName
	 *@param comment
	 */
	public void addSection(String sectionName,String comment);
	/**
	 * <P>函数注释：removeSection()</p>
	 *█<p>———————————————————————————————————————————</p> 
	 *█<p>作者：何明辉</p>
	 *█<p>时间：2013-5-13-上午10:08:24</p>
	 *█<p>类型：方法</p>
	 *█<p>用途：该方法用于移除板块</p>
	 *█<p>备注：***</p> 
	 *█<p>抛出异常：[]</p>
	 *█<p>触发事件：[]</p>
	 *█<p>示例：</p>
	 *█<p>———————————————————————————————————————————</p>
	 *█   
	 *█<p>———————————————————————————————————————————</p>
	 *@param sectionName
	 */
	public void removeSection(String sectionName);
	/**
	 * <P>函数注释：addSection()</p>
	 *█<p>———————————————————————————————————————————</p> 
	 *█<p>作者：何明辉</p>
	 *█<p>时间：2013-5-13-上午10:08:38</p>
	 *█<p>类型：方法</p>
	 *█<p>用途：该方法用于增加板块</p>
	 *█<p>备注：***</p> 
	 *█<p>抛出异常：[]</p>
	 *█<p>触发事件：[]</p>
	 *█<p>示例：</p>
	 *█<p>———————————————————————————————————————————</p>
	 *█   
	 *█<p>———————————————————————————————————————————</p>
	 *@param section
	 */
	public void addSection(INISection section);
	/**
	 * <P>函数注释：removeSection()</p>
	 *█<p>———————————————————————————————————————————</p> 
	 *█<p>作者：何明辉</p>
	 *█<p>时间：2013-5-13-上午10:08:47</p>
	 *█<p>类型：方法</p>
	 *█<p>用途：该方法用于移除板块</p>
	 *█<p>备注：***</p> 
	 *█<p>抛出异常：[]</p>
	 *█<p>触发事件：[]</p>
	 *█<p>示例：</p>
	 *█<p>———————————————————————————————————————————</p>
	 *█   
	 *█<p>———————————————————————————————————————————</p>
	 *@param section
	 */
	public void removeSection(INISection section);
	/**
	 * <P>函数注释：insertSection()</p>
	 *█<p>———————————————————————————————————————————</p> 
	 *█<p>作者：何明辉</p>
	 *█<p>时间：2013-5-13-上午10:08:57</p>
	 *█<p>类型：方法</p>
	 *█<p>用途：该方法用于在指定位置插入板块</p>
	 *█<p>备注：***</p> 
	 *█<p>抛出异常：[]</p>
	 *█<p>触发事件：[]</p>
	 *█<p>示例：</p>
	 *█<p>———————————————————————————————————————————</p>
	 *█   
	 *█<p>———————————————————————————————————————————</p>
	 *@param index
	 *@param sectionName
	 *@param comment
	 *@return
	 */
	public int insertSection(int index,String sectionName,String comment);
	/**
	 * <P>函数注释：insertSection()</p>
	 *█<p>———————————————————————————————————————————</p> 
	 *█<p>作者：何明辉</p>
	 *█<p>时间：2013-5-13-上午10:08:57</p>
	 *█<p>类型：方法</p>
	 *█<p>用途：该方法用于在指定位置插入板块</p>
	 *█<p>备注：***</p> 
	 *█<p>抛出异常：[]</p>
	 *█<p>触发事件：[]</p>
	 *█<p>示例：</p>
	 *█<p>———————————————————————————————————————————</p>
	 *█   
	 *█<p>———————————————————————————————————————————</p>
	 *@param index
	 *@param sectionName
	 *@param comment
	 *@return
	 */
	public int insertSection(int index,INISection section);
	/**
	 * <P>函数注释：setSectionComment()</p>
	 *█<p>———————————————————————————————————————————</p> 
	 *█<p>作者：何明辉</p>
	 *█<p>时间：2013-5-13-上午10:09:22</p>
	 *█<p>类型：方法</p>
	 *█<p>用途：该方法用于设置板块的注释</p>
	 *█<p>备注：***</p> 
	 *█<p>抛出异常：[]</p>
	 *█<p>触发事件：[]</p>
	 *█<p>示例：</p>
	 *█<p>———————————————————————————————————————————</p>
	 *█   
	 *█<p>———————————————————————————————————————————</p>
	 *@param section
	 *@param comment
	 */
	public void setSectionComment(String section,String comment);
	/**
	 * <P>函数注释：getSectionComment()</p>
	 *█<p>———————————————————————————————————————————</p> 
	 *█<p>作者：何明辉</p>
	 *█<p>时间：2013-5-13-上午10:09:34</p>
	 *█<p>类型：方法</p>
	 *█<p>用途：该方法用于获取指定板块的注释</p>
	 *█<p>备注：***</p> 
	 *█<p>抛出异常：[]</p>
	 *█<p>触发事件：[]</p>
	 *█<p>示例：</p>
	 *█<p>———————————————————————————————————————————</p>
	 *█   
	 *█<p>———————————————————————————————————————————</p>
	 *@param section
	 *@return
	 */
	public String getSectionComment(String section);
	/**
	 * <P>函数注释：getSection()</p>
	 *█<p>———————————————————————————————————————————</p> 
	 *█<p>作者：何明辉</p>
	 *█<p>时间：2013-5-13-上午10:10:03</p>
	 *█<p>类型：方法</p>
	 *█<p>用途：该方法用于获取指定板块</p>
	 *█<p>备注：包括注释和设置</p> 
	 *█<p>抛出异常：[]</p>
	 *█<p>触发事件：[]</p>
	 *█<p>示例：</p>
	 *█<p>———————————————————————————————————————————</p>
	 *█   
	 *█<p>———————————————————————————————————————————</p>
	 *@param name
	 *@return
	 */
	public INISection getSection(String name);
	/**
	 * <P>函数注释：addProperty()</p>
	 *█<p>———————————————————————————————————————————</p> 
	 *█<p>作者：何明辉</p>
	 *█<p>时间：2013-5-13-上午10:10:26</p>
	 *█<p>类型：方法</p>
	 *█<p>用途：该方法用于增加属性</p>
	 *█<p>备注：***</p> 
	 *█<p>抛出异常：[]</p>
	 *█<p>触发事件：[]</p>
	 *█<p>示例：</p>
	 *█<p>———————————————————————————————————————————</p>
	 *█   
	 *█<p>———————————————————————————————————————————</p>
	 *@param section
	 *@param property
	 */
	public void addProperty(String section,INIProperty property);
	/**
	 * <P>函数注释：addProperty()</p>
	 *█<p>———————————————————————————————————————————</p> 
	 *█<p>作者：何明辉</p>
	 *█<p>时间：2013-5-13-上午10:10:37</p>
	 *█<p>类型：方法</p>
	 *█<p>用途：该方法用于增加属性</p>
	 *█<p>备注：***</p> 
	 *█<p>抛出异常：[]</p>
	 *█<p>触发事件：[]</p>
	 *█<p>示例：</p>
	 *█<p>———————————————————————————————————————————</p>
	 *█   
	 *█<p>———————————————————————————————————————————</p>
	 *@param section
	 *@param property
	 *@param value
	 *@param comment
	 */
	public void addProperty(String section,String property,String value,String comment);
	/**
	 * <P>函数注释：insertProperty()</p>
	 *█<p>———————————————————————————————————————————</p> 
	 *█<p>作者：何明辉</p>
	 *█<p>时间：2013-5-13-上午10:10:47</p>
	 *█<p>类型：方法</p>
	 *█<p>用途：该方法用于在指定位置插入属性</p>
	 *█<p>备注：***</p> 
	 *█<p>抛出异常：[]</p>
	 *█<p>触发事件：[]</p>
	 *█<p>示例：</p>
	 *█<p>———————————————————————————————————————————</p>
	 *█   
	 *█<p>———————————————————————————————————————————</p>
	 *@param section
	 *@param index
	 *@param property
	 */
	public void insertProperty(String section,int index,INIProperty property);
	/**
	 * <P>函数注释：insertProperty()</p>
	 *█<p>———————————————————————————————————————————</p> 
	 *█<p>作者：何明辉</p>
	 *█<p>时间：2013-5-13-上午10:11:05</p>
	 *█<p>类型：方法</p>
	 *█<p>用途：该方法用于在指定位置插入属性</p>
	 *█<p>备注：***</p> 
	 *█<p>抛出异常：[]</p>
	 *█<p>触发事件：[]</p>
	 *█<p>示例：</p>
	 *█<p>———————————————————————————————————————————</p>
	 *█   
	 *█<p>———————————————————————————————————————————</p>
	 *@param section
	 *@param index
	 *@param property
	 *@param value
	 *@param comment
	 */
	public void insertProperty(String section,int index,String property,String value,String comment);
	/**
	 * <P>函数注释：removeProperty()</p>
	 *█<p>———————————————————————————————————————————</p> 
	 *█<p>作者：何明辉</p>
	 *█<p>时间：2013-5-13-上午10:11:15</p>
	 *█<p>类型：方法</p>
	 *█<p>用途：该方法用于移除属性</p>
	 *█<p>备注：***</p> 
	 *█<p>抛出异常：[]</p>
	 *█<p>触发事件：[]</p>
	 *█<p>示例：</p>
	 *█<p>———————————————————————————————————————————</p>
	 *█   
	 *█<p>———————————————————————————————————————————</p>
	 *@param section
	 *@param property
	 */
	public void removeProperty(String section,INIProperty property);
	/**
	 * <P>函数注释：removeProperty()</p>
	 *█<p>———————————————————————————————————————————</p> 
	 *█<p>作者：何明辉</p>
	 *█<p>时间：2013-5-13-上午10:11:33</p>
	 *█<p>类型：方法</p>
	 *█<p>用途：该方法用于移除属性</p>
	 *█<p>备注：***</p> 
	 *█<p>抛出异常：[]</p>
	 *█<p>触发事件：[]</p>
	 *█<p>示例：</p>
	 *█<p>———————————————————————————————————————————</p>
	 *█   
	 *█<p>———————————————————————————————————————————</p>
	 *@param sectionName
	 *@param property
	 */
	public void removeProperty(String sectionName,String property);
	/**
	 * <P>函数注释：setPropertyComment()</p>
	 *█<p>———————————————————————————————————————————</p> 
	 *█<p>作者：何明辉</p>
	 *█<p>时间：2013-5-13-上午10:12:03</p>
	 *█<p>类型：方法</p>
	 *█<p>用途：该方法用于设置属性的注释</p>
	 *█<p>备注：***</p> 
	 *█<p>抛出异常：[]</p>
	 *█<p>触发事件：[]</p>
	 *█<p>示例：</p>
	 *█<p>———————————————————————————————————————————</p>
	 *█   
	 *█<p>———————————————————————————————————————————</p>
	 *@param section
	 *@param property
	 *@param comment
	 */
	public void setPropertyComment(String section,String property,String comment);
	/**
	 * <P>函数注释：getPropertyComment()</p>
	 *█<p>———————————————————————————————————————————</p> 
	 *█<p>作者：何明辉</p>
	 *█<p>时间：2013-5-13-上午10:12:13</p>
	 *█<p>类型：方法</p>
	 *█<p>用途：该方法用于获取属性的注释</p>
	 *█<p>备注：***</p> 
	 *█<p>抛出异常：[]</p>
	 *█<p>触发事件：[]</p>
	 *█<p>示例：</p>
	 *█<p>———————————————————————————————————————————</p>
	 *█   
	 *█<p>———————————————————————————————————————————</p>
	 *@param section
	 *@param property
	 *@return
	 */
	public String getPropertyComment(String section,String property);
	//———————————————————————————————————————————

	/**
	 * <P>函数注释：getBooleanProperty()</p>
	 *█<p>———————————————————————————————————————————</p> 
	 *█<p>作者：何明辉</p>
	 *█<p>时间：2013-5-13-上午10:12:28</p>
	 *█<p>类型：方法</p>
	 *█<p>用途：该方法用于获取bool属性值</p>
	 *█<p>备注：***</p> 
	 *█<p>抛出异常：[]</p>
	 *█<p>触发事件：[]</p>
	 *█<p>示例：</p>
	 *█<p>———————————————————————————————————————————</p>
	 *█   
	 *█<p>———————————————————————————————————————————</p>
	 *@param section
	 *@param property
	 *@return
	 */
	public Boolean getBooleanProperty(String section,String property);
	/**
	 * <P>函数注释：getDateProperty()</p>
	 *█<p>———————————————————————————————————————————</p> 
	 *█<p>作者：何明辉</p>
	 *█<p>时间：2013-5-13-上午10:12:40</p>
	 *█<p>类型：方法</p>
	 *█<p>用途：该方法用于获取日期属性值</p>
	 *█<p>备注：***</p> 
	 *█<p>抛出异常：[]</p>
	 *█<p>触发事件：[]</p>
	 *█<p>示例：</p>
	 *█<p>———————————————————————————————————————————</p>
	 *█   
	 *█<p>———————————————————————————————————————————</p>
	 *@param section
	 *@param property
	 *@return
	 *@throws ParseException
	 */
	public Date getDateProperty(String section,String property) throws ParseException;
	/**
	 * <P>函数注释：getDoubleProperty()</p>
	 *█<p>———————————————————————————————————————————</p> 
	 *█<p>作者：何明辉</p>
	 *█<p>时间：2013-5-13-上午10:12:52</p>
	 *█<p>类型：方法</p>
	 *█<p>用途：该方法用于获取double属性值</p>
	 *█<p>备注：***</p> 
	 *█<p>抛出异常：[]</p>
	 *█<p>触发事件：[]</p>
	 *█<p>示例：</p>
	 *█<p>———————————————————————————————————————————</p>
	 *█   
	 *█<p>———————————————————————————————————————————</p>
	 *@param section
	 *@param property
	 *@return
	 */
	public Double getDoubleProperty(String section,String property);
	/**
	 * <P>函数注释：getIntegerProperty()</p>
	 *█<p>———————————————————————————————————————————</p> 
	 *█<p>作者：何明辉</p>
	 *█<p>时间：2013-5-13-上午10:13:07</p>
	 *█<p>类型：方法</p>
	 *█<p>用途：该方法用于获取整数属性值</p>
	 *█<p>备注：***</p> 
	 *█<p>抛出异常：[]</p>
	 *█<p>触发事件：[]</p>
	 *█<p>示例：</p>
	 *█<p>———————————————————————————————————————————</p>
	 *█   
	 *█<p>———————————————————————————————————————————</p>
	 *@param section
	 *@param property
	 *@return
	 */
	public Integer getIntegerProperty(String section,String property);
	/**
	 * <P>函数注释：getLongProperty()</p>
	 *█<p>———————————————————————————————————————————</p> 
	 *█<p>作者：何明辉</p>
	 *█<p>时间：2013-5-13-上午10:13:26</p>
	 *█<p>类型：方法</p>
	 *█<p>用途：该方法用于获取long属性值</p>
	 *█<p>备注：***</p> 
	 *█<p>抛出异常：[]</p>
	 *█<p>触发事件：[]</p>
	 *█<p>示例：</p>
	 *█<p>———————————————————————————————————————————</p>
	 *█   
	 *█<p>———————————————————————————————————————————</p>
	 *@param section
	 *@param property
	 *@return
	 */
	public Long getLongProperty(String section,String property);
	/**
	 * <P>函数注释：getStringProperty()</p>
	 *█<p>———————————————————————————————————————————</p> 
	 *█<p>作者：何明辉</p>
	 *█<p>时间：2013-5-13-上午10:13:37</p>
	 *█<p>类型：方法</p>
	 *█<p>用途：该方法用于获取字符串型属性值</p>
	 *█<p>备注：系统中获取的属性都是string的</p> 
	 *█<p>抛出异常：[]</p>
	 *█<p>触发事件：[]</p>
	 *█<p>示例：</p>
	 *█<p>———————————————————————————————————————————</p>
	 *█   
	 *█<p>———————————————————————————————————————————</p>
	 *@param section
	 *@param property
	 *@return
	 */
	public String getStringProperty(String section,String property);
	/**
	 * <P>函数注释：getTimestampProperty()</p>
	 *█<p>———————————————————————————————————————————</p> 
	 *█<p>作者：何明辉</p>
	 *█<p>时间：2013-5-13-上午10:14:16</p>
	 *█<p>类型：方法</p>
	 *█<p>用途：该方法用于获取时间戳属性</p>
	 *█<p>备注：该方法已废弃</p> 
	 *█<p>抛出异常：[]</p>
	 *█<p>触发事件：[]</p>
	 *█<p>示例：</p>
	 *█<p>———————————————————————————————————————————</p>
	 *█   
	 *█<p>———————————————————————————————————————————</p>
	 *@param section
	 *@param property
	 *@return
	 */
	public Date getTimestampProperty(String section,String property);
	/**
	 * <P>函数注释：getProperties()</p>
	 *█<p>———————————————————————————————————————————</p> 
	 *█<p>作者：何明辉</p>
	 *█<p>时间：2013-5-13-上午10:14:49</p>
	 *█<p>类型：方法</p>
	 *█<p>用途：该方法用于获取板块下的所有属性</p>
	 *█<p>备注：***</p> 
	 *█<p>抛出异常：[]</p>
	 *█<p>触发事件：[]</p>
	 *█<p>示例：</p>
	 *█<p>———————————————————————————————————————————</p>
	 *█   
	 *█<p>———————————————————————————————————————————</p>
	 *@param section
	 *@return
	 */
	public Map getProperties(String section);

	/**
	 * <P>函数注释：setBooleanProperty()</p>
	 *█<p>———————————————————————————————————————————</p> 
	 *█<p>作者：何明辉</p>
	 *█<p>时间：2013-5-13-上午10:15:06</p>
	 *█<p>类型：方法</p>
	 *█<p>用途：该方法用于设置bool型的属性</p>
	 *█<p>备注：系统中所有的属性都是string型的</p> 
	 *█<p>抛出异常：[]</p>
	 *█<p>触发事件：[]</p>
	 *█<p>示例：</p>
	 *█<p>———————————————————————————————————————————</p>
	 *█   
	 *█<p>———————————————————————————————————————————</p>
	 *@param sectionName
	 *@param property
	 *@param value
	 *@param comment
	 */
	public void setBooleanProperty(String sectionName, String property, Boolean value, String comment);
	/**
	 * <P>函数注释：setDateFormat()</p>
	 *█<p>———————————————————————————————————————————</p> 
	 *█<p>作者：何明辉</p>
	 *█<p>时间：2013-5-13-上午10:15:39</p>
	 *█<p>类型：方法</p>
	 *█<p>用途：该方法用于设置日期格式模板</p>
	 *█<p>备注：***</p> 
	 *█<p>抛出异常：[]</p>
	 *█<p>触发事件：[]</p>
	 *█<p>示例：</p>
	 *█<p>———————————————————————————————————————————</p>
	 *█   
	 *█<p>———————————————————————————————————————————</p>
	 *@param format
	 */
	public void setDateFormat(String format);
	/**
	 * <P>函数注释：setDateProperty()</p>
	 *█<p>———————————————————————————————————————————</p> 
	 *█<p>作者：何明辉</p>
	 *█<p>时间：2013-5-13-上午10:15:50</p>
	 *█<p>类型：方法</p>
	 *█<p>用途：该方法用于设置日期属性</p>
	 *█<p>备注：***</p> 
	 *█<p>抛出异常：[]</p>
	 *█<p>触发事件：[]</p>
	 *█<p>示例：</p>
	 *█<p>———————————————————————————————————————————</p>
	 *█   
	 *█<p>———————————————————————————————————————————</p>
	 *@param sectionName
	 *@param property
	 *@param value
	 *@param comment
	 */
	public void setDateProperty(String sectionName,String property,Date value,String comment);
	/**
	 * <P>函数注释：setDoubleProperty()</p>
	 *█<p>———————————————————————————————————————————</p> 
	 *█<p>作者：何明辉</p>
	 *█<p>时间：2013-5-13-上午10:16:01</p>
	 *█<p>类型：方法</p>
	 *█<p>用途：该方法用于设置double属性</p>
	 *█<p>备注：***</p> 
	 *█<p>抛出异常：[]</p>
	 *█<p>触发事件：[]</p>
	 *█<p>示例：</p>
	 *█<p>———————————————————————————————————————————</p>
	 *█   
	 *█<p>———————————————————————————————————————————</p>
	 *@param sectionName
	 *@param property
	 *@param value
	 *@param comment
	 */
	public void setDoubleProperty(String sectionName,String property,Double value,String comment);
	/**
	 * <P>函数注释：setIntegerProperty()</p>
	 *█<p>———————————————————————————————————————————</p> 
	 *█<p>作者：何明辉</p>
	 *█<p>时间：2013-5-13-上午10:16:17</p>
	 *█<p>类型：方法</p>
	 *█<p>用途：该方法用于设置整形属性</p>
	 *█<p>备注：系统中所有的属性都是string型的</p> 
	 *█<p>抛出异常：[]</p>
	 *█<p>触发事件：[]</p>
	 *█<p>示例：</p>
	 *█<p>———————————————————————————————————————————</p>
	 *█   
	 *█<p>———————————————————————————————————————————</p>
	 *@param sectionName
	 *@param property
	 *@param value
	 *@param comment
	 */
	public void setIntegerProperty(String sectionName,String property,Integer value,String comment);
	/**
	 * <P>函数注释：setLongProperty()</p>
	 *█<p>———————————————————————————————————————————</p> 
	 *█<p>作者：何明辉</p>
	 *█<p>时间：2013-5-13-上午10:18:21</p>
	 *█<p>类型：方法</p>
	 *█<p>用途：该方法用于设置long型的属性</p>
	 *█<p>备注：系统中所有的属性都是string型的</p> 
	 *█<p>抛出异常：[]</p>
	 *█<p>触发事件：[]</p>
	 *█<p>示例：</p>
	 *█<p>———————————————————————————————————————————</p>
	 *█   
	 *█<p>———————————————————————————————————————————</p>
	 *@param sectionName
	 *@param property
	 *@param value
	 *@param comment
	 */
	public void setLongProperty(String sectionName,String property,Long value,String comment);
	/**
	 * 	<P>函数注释：setStringProperty()</p>
	 *█<p>———————————————————————————————————————————</p> 
	 *█<p>作者：何明辉</p>
	 *█<p>时间：2013-5-13-上午10:18:45</p>
	 *█<p>类型：方法</p>
	 *█<p>用途：该方法用于设置字符串型的属性</p>
	 *█<p>备注：系统中所有的属性都是string型的</p> 
	 *█<p>抛出异常：[]</p>
	 *█<p>触发事件：[]</p>
	 *█<p>示例：</p>
	 *█<p>———————————————————————————————————————————</p>
	 *█   
	 *█<p>———————————————————————————————————————————</p>
	 *@param sectionName
	 *@param property
	 *@param value
	 *@param comment
	 */
	public void setStringProperty(String sectionName,String property,String value,String comment);
	/**
	 * <P>函数注释：setTimestampFormat()</p>
	 *█<p>———————————————————————————————————————————</p> 
	 *█<p>作者：何明辉</p>
	 *█<p>时间：2013-5-13-上午10:19:03</p>
	 *█<p>类型：方法</p>
	 *█<p>用途：该方法用于设置日期格式模板</p>
	 *█<p>备注：***</p> 
	 *█<p>抛出异常：[]</p>
	 *█<p>触发事件：[]</p>
	 *█<p>示例：</p>
	 *█<p>———————————————————————————————————————————</p>
	 *█   
	 *█<p>———————————————————————————————————————————</p>
	 *@param format
	 */
	public void setTimestampFormat(String format);
	/**
	 * <P>函数注释：setTimestampProperty()</p>
	 *█<p>———————————————————————————————————————————</p> 
	 *█<p>作者：何明辉</p>
	 *█<p>时间：2013-5-13-上午10:19:12</p>
	 *█<p>类型：方法</p>
	 *█<p>用途：该方法用于设置时间戳属性</p>
	 *█<p>备注：该方法已废弃</p> 
	 *█<p>抛出异常：[]</p>
	 *█<p>触发事件：[]</p>
	 *█<p>示例：</p>
	 *█<p>———————————————————————————————————————————</p>
	 *█   
	 *█<p>———————————————————————————————————————————</p>
	 *@param sectionName
	 *@param property
	 *@param value
	 *@param comment
	 */
	public void setTimestampProperty(String sectionName,String property,Timestamp value,String comment);

	/**
	 * <P>函数注释：save()</p>
	 *█<p>———————————————————————————————————————————</p> 
	 *█<p>作者：何明辉</p>
	 *█<p>时间：2013-5-13-上午10:19:35</p>
	 *█<p>类型：方法</p>
	 *█<p>用途：该方法用于保存INI数据</p>
	 *█<p>备注：空白区块会自动添加一个属性</p> 
	 *█<p>抛出异常：[]</p>
	 *█<p>触发事件：[]</p>
	 *█<p>示例：</p>
	 *█<p>———————————————————————————————————————————</p>
	 *█   
	 *█<p>———————————————————————————————————————————</p>
	 *@return
	 *@throws IOException
	 *@throws Exception
	 */
	public boolean save();
	/**
	 * <P>函数注释：toString()</p>
	 *█<p>———————————————————————————————————————————</p> 
	 *█<p>作者：何明辉</p>
	 *█<p>时间：2013-5-13-上午10:21:17</p>
	 *█<p>类型：方法</p>
	 *█<p>用途：该方法用于将配置打印成文件内容</p>
	 *█<p>备注：***</p> 
	 *█<p>抛出异常：[]</p>
	 *█<p>触发事件：[]</p>
	 *█<p>示例：</p>
	 *█<p>———————————————————————————————————————————</p>
	 *█   
	 *█<p>———————————————————————————————————————————</p>
	 *@return
	 */
	public String toString();
	/**
	 * <P>函数注释：getAllSectionNames()</p>
	 *█<p>———————————————————————————————————————————</p> 
	 *█<p>作者：何明辉</p>
	 *█<p>时间：2013-5-13-上午10:21:38</p>
	 *█<p>类型：方法</p>
	 *█<p>用途：该方法用于获取所有的板块名</p>
	 *█<p>备注：***</p> 
	 *█<p>抛出异常：[]</p>
	 *█<p>触发事件：[]</p>
	 *█<p>示例：</p>
	 *█<p>———————————————————————————————————————————</p>
	 *█   
	 *█<p>———————————————————————————————————————————</p>
	 *@return
	 */
	public String[] getAllSectionNames();
	/**
	 * <P>函数注释：getTotalSections()</p>
	 *█<p>———————————————————————————————————————————</p> 
	 *█<p>作者：何明辉</p>
	 *█<p>时间：2013-5-13-上午10:21:53</p>
	 *█<p>类型：方法</p>
	 *█<p>用途：该方法用于获取板块数</p>
	 *█<p>备注：***</p> 
	 *█<p>抛出异常：[]</p>
	 *█<p>触发事件：[]</p>
	 *█<p>示例：</p>
	 *█<p>———————————————————————————————————————————</p>
	 *█   
	 *█<p>———————————————————————————————————————————</p>
	 *@return
	 */
	public int getTotalSections();
	/**
	 * <P>函数注释：getFileName()</p>
	 *█<p>———————————————————————————————————————————</p> 
	 *█<p>作者：何明辉</p>
	 *█<p>时间：2013-5-13-上午10:22:04</p>
	 *█<p>类型：方法</p>
	 *█<p>用途：该方法用于获取当前ini实例所处理的文件</p>
	 *█<p>备注：***</p> 
	 *█<p>抛出异常：[]</p>
	 *█<p>触发事件：[]</p>
	 *█<p>示例：</p>
	 *█<p>———————————————————————————————————————————</p>
	 *█   
	 *█<p>———————————————————————————————————————————</p>
	 *@return
	 */
	public String getFileName();
	/**
	 * <P>函数注释：getPropertyNames()</p>
	 *█<p>———————————————————————————————————————————</p> 
	 *█<p>作者：何明辉</p>
	 *█<p>时间：2013-5-13-上午10:22:32</p>
	 *█<p>类型：方法</p>
	 *█<p>用途：该方法用于获取板块所有属性名</p>
	 *█<p>备注：***</p> 
	 *█<p>抛出异常：[]</p>
	 *█<p>触发事件：[]</p>
	 *█<p>示例：</p>
	 *█<p>———————————————————————————————————————————</p>
	 *█   
	 *█<p>———————————————————————————————————————————</p>
	 *@param section
	 *@return
	 */
	public String[] getPropertyNames(String section);
	/**
	 * <P>函数注释：getStringComment()</p>
	 *█<p>———————————————————————————————————————————</p> 
	 *█<p>作者：何明辉</p>
	 *█<p>时间：2013-5-13-上午10:23:34</p>
	 *█<p>类型：方法</p>
	 *█<p>用途：该方法用于获取区块的注释</p>
	 *█<p>备注：***</p> 
	 *█<p>抛出异常：[]</p>
	 *█<p>触发事件：[]</p>
	 *█<p>示例：</p>
	 *█<p>———————————————————————————————————————————</p>
	 *█   
	 *█<p>———————————————————————————————————————————</p>
	 *@param section
	 *@return
	 */
	public String getStringComment(String section);
	/**
	 * <P>函数注释：getStringComment()</p>
	 *█<p>———————————————————————————————————————————</p> 
	 *█<p>作者：何明辉</p>
	 *█<p>时间：2013-5-13-上午10:23:46</p>
	 *█<p>类型：方法</p>
	 *█<p>用途：该方法用于获取属性的注释</p>
	 *█<p>备注：***</p> 
	 *█<p>抛出异常：[]</p>
	 *█<p>触发事件：[]</p>
	 *█<p>示例：</p>
	 *█<p>———————————————————————————————————————————</p>
	 *█   
	 *█<p>———————————————————————————————————————————</p>
	 *@param section
	 *@param property
	 *@return
	 */
	public String getStringComment(String section,String property);




}
