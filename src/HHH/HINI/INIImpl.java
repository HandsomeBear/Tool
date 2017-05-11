package HHH.HINI;





/**<P>文件注释：INIImpl.java</p>
 *█<p>———————————————————————————————————————————</p> 
 *█<p>作者：何明辉</p>
 *█<p>时间：2013-5-8-上午11:09:04</p>
 *█<p>类型：文件</p>
 *█<p>用途：该文件用于</p>
 *█<p>备注：***</p> 
 *█<p>抛出异常：[]</p>
 *█<p>触发事件：[]</p>
 *█<p>示例：</p>
 *█<p>———————————————————————————————————————————</p>
 *█  
 *█<p>———————————————————————————————————————————</p>
 */


import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;








/**<P>类型注释：</p>
 *█<p>———————————————————————————————————————————</p> 
 *█<p>作者：何明辉</p>
 *█<p>时间：2013-5-8-上午11:09:04</p>
 *█<p>类型：INIImpl</p>
 *█<p>用途：该类型用于</p>
 *█<p>备注：该类型涉及到[]张数据表</p>
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
public class INIImpl implements INIFileInterface {

	List<INISection> sections=new ArrayList<INISection>();//存放区块
	String charset="";
	private String dateFormat = "yyyy-MM-dd";
	private String mstrTimeStampFmt = "yyyy-MM-dd HH:mm:ss";
	String file="";
	private Map<String,String> lastModifyTime=new HashMap<String,String>();//存放上次修改时间的映射



	/**<P>函数注释：addSection()</p>
	 *█<p>———————————————————————————————————————————</p> 
	 *█<p>作者：何明辉</p>
	 *█<p>时间：2013-5-6-上午10:29:50</p>
	 *█<p>类型：方法</p>
	 *█<p>用途：该方法（覆盖/实现）了父类方法</p>
	 *█<p>备注：***</p> 
	 *█<p>抛出异常：[]</p>
	 *█<p>触发事件：[]</p>
	 *█<p>示例：</p>
	 *█<p>———————————————————————————————————————————</p>
	 *█   
	 *█<p>———————————————————————————————————————————</p>
	 * @see INIFileInterface#addSection(java.lang.String, java.lang.String)
	 */

	public void addSection(String sectionName, String comment) {
		List<INISection> list=sections;
		String spliter=System.getProperty("line.separator");
		INISection section=new INISection();
		section.setName(sectionName);
		section.setHeaderComments(Arrays.asList(comment.split(spliter)));
		list.add(section);
	}






	/**<P>函数注释：removeSection()</p>
	 *█<p>———————————————————————————————————————————</p> 
	 *█<p>作者：何明辉</p>
	 *█<p>时间：2013-5-6-上午10:29:50</p>
	 *█<p>类型：方法</p>
	 *█<p>用途：该方法（覆盖/实现）了父类方法</p>
	 *█<p>备注：***</p> 
	 *█<p>抛出异常：[]</p>
	 *█<p>触发事件：[]</p>
	 *█<p>示例：</p>
	 *█<p>———————————————————————————————————————————</p>
	 *█   
	 *█<p>———————————————————————————————————————————</p>
	 * @see INIFileInterface#removeSection(java.lang.String)
	 */

	public void removeSection(String sectionName) {
		List<INISection> list=sections;
		for(Object section:list) {
			if(sectionName.equals(((INISection)section).getName())) {
				list.remove(section);
				break;
			}
		}
	}






	/**<P>函数注释：addSection()</p>
	 *█<p>———————————————————————————————————————————</p> 
	 *█<p>作者：何明辉</p>
	 *█<p>时间：2013-5-6-上午10:29:50</p>
	 *█<p>类型：方法</p>
	 *█<p>用途：该方法（覆盖/实现）了父类方法</p>
	 *█<p>备注：***</p> 
	 *█<p>抛出异常：[]</p>
	 *█<p>触发事件：[]</p>
	 *█<p>示例：</p>
	 *█<p>———————————————————————————————————————————</p>
	 *█   
	 *█<p>———————————————————————————————————————————</p>
	 * @see INIFileInterface#addSection(INISection)
	 */

	public void addSection(INISection section) {
		List<INISection> list=sections;
		list.add(section);
	}






	/**<P>函数注释：removeSection()</p>
	 *█<p>———————————————————————————————————————————</p> 
	 *█<p>作者：何明辉</p>
	 *█<p>时间：2013-5-6-上午10:29:50</p>
	 *█<p>类型：方法</p>
	 *█<p>用途：该方法（覆盖/实现）了父类方法</p>
	 *█<p>备注：***</p> 
	 *█<p>抛出异常：[]</p>
	 *█<p>触发事件：[]</p>
	 *█<p>示例：</p>
	 *█<p>———————————————————————————————————————————</p>
	 *█   
	 *█<p>———————————————————————————————————————————</p>
	 * @see INIFileInterface#removeSection(INISection)
	 */

	public void removeSection(INISection section) {
		List<INISection> list=sections;
		list.remove(section);
	}






	/**<P>函数注释：insertSection()</p>
	 *█<p>———————————————————————————————————————————</p> 
	 *█<p>作者：何明辉</p>
	 *█<p>时间：2013-5-6-上午10:29:50</p>
	 *█<p>类型：方法</p>
	 *█<p>用途：该方法（覆盖/实现）了父类方法</p>
	 *█<p>备注：***</p> 
	 *█<p>抛出异常：[]</p>
	 *█<p>触发事件：[]</p>
	 *█<p>示例：</p>
	 *█<p>———————————————————————————————————————————</p>
	 *█   
	 *█<p>———————————————————————————————————————————</p>
	 * @see INIFileInterface#insertSection(int, java.lang.String, java.lang.String)
	 */

	public int insertSection(int index, String sectionName, String comment) {
		List<INISection> list=sections;
		String spliter=System.getProperty("line.separator");
		INISection section=new INISection();
		section.setName(sectionName);
		section.setHeaderComments(Arrays.asList(comment.split(spliter)));
		list.add(index,section);
		return index;
	}






	/**<P>函数注释：insertSection()</p>
	 *█<p>———————————————————————————————————————————</p> 
	 *█<p>作者：何明辉</p>
	 *█<p>时间：2013-5-6-上午10:29:50</p>
	 *█<p>类型：方法</p>
	 *█<p>用途：该方法（覆盖/实现）了父类方法</p>
	 *█<p>备注：***</p> 
	 *█<p>抛出异常：[]</p>
	 *█<p>触发事件：[]</p>
	 *█<p>示例：</p>
	 *█<p>———————————————————————————————————————————</p>
	 *█   
	 *█<p>———————————————————————————————————————————</p>
	 * @see INIFileInterface#insertSection(int, INISection)
	 */

	public int insertSection(int index, INISection section) {
		List<INISection> list=sections;
		list.add(index,section);
		return index;
	}






	/**<P>函数注释：setSectionComment()</p>
	 *█<p>———————————————————————————————————————————</p> 
	 *█<p>作者：何明辉</p>
	 *█<p>时间：2013-5-6-上午10:29:50</p>
	 *█<p>类型：方法</p>
	 *█<p>用途：该方法（覆盖/实现）了父类方法</p>
	 *█<p>备注：***</p> 
	 *█<p>抛出异常：[]</p>
	 *█<p>触发事件：[]</p>
	 *█<p>示例：</p>
	 *█<p>———————————————————————————————————————————</p>
	 *█   
	 *█<p>———————————————————————————————————————————</p>
	 * @see INIFileInterface#setSectionComment(java.lang.String, java.lang.String)
	 */

	public void setSectionComment(String section, String comment) {
		INISection IS=this.getSection(section);
		if(IS==null) {return;}
		String spliter=System.getProperty("line.separator");
		IS.setHeaderComments(Arrays.asList(comment.split(spliter)));
	}






	/**<P>函数注释：getSectionComment()</p>
	 *█<p>———————————————————————————————————————————</p> 
	 *█<p>作者：何明辉</p>
	 *█<p>时间：2013-5-6-上午10:29:50</p>
	 *█<p>类型：方法</p>
	 *█<p>用途：该方法（覆盖/实现）了父类方法</p>
	 *█<p>备注：***</p> 
	 *█<p>抛出异常：[]</p>
	 *█<p>触发事件：[]</p>
	 *█<p>示例：</p>
	 *█<p>———————————————————————————————————————————</p>
	 *█   
	 *█<p>———————————————————————————————————————————</p>
	 * @see INIFileInterface#getSectionComment(java.lang.String)
	 */

	public String getSectionComment(String section) {
		INISection IS=this.getSection(section);
		if(IS==null) {return null;}
		List<String> data= IS.getHeaderComments();
		StringBuilder bu=new StringBuilder();
		String spliter=System.getProperty("line.separator");
		for(String s:data) {bu.append(s).append(spliter);}
		return bu.toString().trim();
	}


	public INISection getSection(String name){
		List<INISection> list=sections;
		for(Object sectionx:list) {
			INISection IS=(INISection)sectionx;
			if(name.equals(IS.getName())) {
				return IS;
			}
		}
		return null;
	}



	/**<P>函数注释：addProperty()</p>
	 *█<p>———————————————————————————————————————————</p> 
	 *█<p>作者：何明辉</p>
	 *█<p>时间：2013-5-6-上午10:29:50</p>
	 *█<p>类型：方法</p>
	 *█<p>用途：该方法（覆盖/实现）了父类方法</p>
	 *█<p>备注：***</p> 
	 *█<p>抛出异常：[]</p>
	 *█<p>触发事件：[]</p>
	 *█<p>示例：</p>
	 *█<p>———————————————————————————————————————————</p>
	 *█   
	 *█<p>———————————————————————————————————————————</p>
	 * @see INIFileInterface#addProperty(java.lang.String, INIProperty)
	 */

	public void addProperty(String section, INIProperty property) {
		INISection s=this.getSection(section);
		if(s==null) {
			INISection sectionx=new INISection();
			sectionx.setName(section);
			this.addSection(sectionx);
			s=sectionx;
		}
		s.addProperty(property);
	}






	/**<P>函数注释：addProperty()</p>
	 *█<p>———————————————————————————————————————————</p> 
	 *█<p>作者：何明辉</p>
	 *█<p>时间：2013-5-6-上午10:29:50</p>
	 *█<p>类型：方法</p>
	 *█<p>用途：该方法（覆盖/实现）了父类方法</p>
	 *█<p>备注：***</p> 
	 *█<p>抛出异常：[]</p>
	 *█<p>触发事件：[]</p>
	 *█<p>示例：</p>
	 *█<p>———————————————————————————————————————————</p>
	 *█   
	 *█<p>———————————————————————————————————————————</p>
	 * @see INIFileInterface#addProperty(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */

	public void addProperty(String section, String property, String value, String comment) {
		INISection s=this.getSection(section);
		if(s==null) {
			INISection sectionx=new INISection();
			sectionx.setName(section);
			this.addSection(sectionx);
			s=sectionx;
		}
		INIProperty p=new INIProperty();
		p.setKey(property);
		p.setValue(value);
		String spliter=System.getProperty("line.separator");
		p.setComments(Arrays.asList(comment.split(spliter)));
		s.addProperty(p);
	}






	/**<P>函数注释：insertProperty()</p>
	 *█<p>———————————————————————————————————————————</p> 
	 *█<p>作者：何明辉</p>
	 *█<p>时间：2013-5-6-上午10:29:50</p>
	 *█<p>类型：方法</p>
	 *█<p>用途：该方法（覆盖/实现）了父类方法</p>
	 *█<p>备注：***</p> 
	 *█<p>抛出异常：[]</p>
	 *█<p>触发事件：[]</p>
	 *█<p>示例：</p>
	 *█<p>———————————————————————————————————————————</p>
	 *█   
	 *█<p>———————————————————————————————————————————</p>
	 * @see INIFileInterface#insertProperty(java.lang.String, int, INIProperty)
	 */

	public void insertProperty(String section, int index, INIProperty property) {
		INISection s=this.getSection(section);
		if(s==null) {
			INISection sectionx=new INISection();
			sectionx.setName(section);
			this.addSection(sectionx);
			s=sectionx;
		}
		s.getProperties().add(index,property);
	}






	/**<P>函数注释：insertProperty()</p>
	 *█<p>———————————————————————————————————————————</p> 
	 *█<p>作者：何明辉</p>
	 *█<p>时间：2013-5-6-上午10:29:50</p>
	 *█<p>类型：方法</p>
	 *█<p>用途：该方法（覆盖/实现）了父类方法</p>
	 *█<p>备注：***</p> 
	 *█<p>抛出异常：[]</p>
	 *█<p>触发事件：[]</p>
	 *█<p>示例：</p>
	 *█<p>———————————————————————————————————————————</p>
	 *█   
	 *█<p>———————————————————————————————————————————</p>
	 * @see INIFileInterface#insertProperty(java.lang.String, int, java.lang.String, java.lang.String, java.lang.String)
	 */

	public void insertProperty(String section, int index, String property, String value, String comment) {
		INISection s=this.getSection(section);
		if(s==null) {
			INISection sectionx=new INISection();
			sectionx.setName(section);
			this.addSection(sectionx);
			s=sectionx;
		}
		INIProperty p=new INIProperty();
		p.setKey(property);
		p.setValue(value);
		String spliter=System.getProperty("line.separator");
		p.setComments(Arrays.asList(comment.split(spliter)));
		s.getProperties().add(index,p);
	}






	/**<P>函数注释：removeProperty()</p>
	 *█<p>———————————————————————————————————————————</p> 
	 *█<p>作者：何明辉</p>
	 *█<p>时间：2013-5-6-上午10:29:50</p>
	 *█<p>类型：方法</p>
	 *█<p>用途：该方法（覆盖/实现）了父类方法</p>
	 *█<p>备注：***</p> 
	 *█<p>抛出异常：[]</p>
	 *█<p>触发事件：[]</p>
	 *█<p>示例：</p>
	 *█<p>———————————————————————————————————————————</p>
	 *█   
	 *█<p>———————————————————————————————————————————</p>
	 * @see INIFileInterface#removeProperty(java.lang.String, INIProperty)
	 */

	public void removeProperty(String section, INIProperty property) {
		INISection s=this.getSection(section);
		if(s==null) {return;}
		s.removeProperty(property);
	}






	/**<P>函数注释：removeProperty()</p>
	 *█<p>———————————————————————————————————————————</p> 
	 *█<p>作者：何明辉</p>
	 *█<p>时间：2013-5-6-上午10:29:50</p>
	 *█<p>类型：方法</p>
	 *█<p>用途：该方法（覆盖/实现）了父类方法</p>
	 *█<p>备注：***</p> 
	 *█<p>抛出异常：[]</p>
	 *█<p>触发事件：[]</p>
	 *█<p>示例：</p>
	 *█<p>———————————————————————————————————————————</p>
	 *█   
	 *█<p>———————————————————————————————————————————</p>
	 * @see INIFileInterface#removeProperty(java.lang.String, java.lang.String)
	 */

	public void removeProperty(String sectionName, String property) {
		INISection s=this.getSection(sectionName);
		if(s==null) {return;}
		s.removeProperty(property);
	}






	/**<P>函数注释：setPropertyComment()</p>
	 *█<p>———————————————————————————————————————————</p> 
	 *█<p>作者：何明辉</p>
	 *█<p>时间：2013-5-6-上午10:29:50</p>
	 *█<p>类型：方法</p>
	 *█<p>用途：该方法（覆盖/实现）了父类方法</p>
	 *█<p>备注：***</p> 
	 *█<p>抛出异常：[]</p>
	 *█<p>触发事件：[]</p>
	 *█<p>示例：</p>
	 *█<p>———————————————————————————————————————————</p>
	 *█   
	 *█<p>———————————————————————————————————————————</p>
	 * @see INIFileInterface#setPropertyComment(java.lang.String, java.lang.String, java.lang.String)
	 */

	public void setPropertyComment(String section, String property, String comment) {
		INISection s=this.getSection(section);
		if(s==null) {return;}
		INIProperty  p=s.getProperty(property);
		if(p==null) {return;}
		String spliter=System.getProperty("line.separator");
		p.setComments(Arrays.asList(comment.split(spliter)));
	}






	/**<P>函数注释：getPropertyComment()</p>
	 *█<p>———————————————————————————————————————————</p> 
	 *█<p>作者：何明辉</p>
	 *█<p>时间：2013-5-6-上午10:29:50</p>
	 *█<p>类型：方法</p>
	 *█<p>用途：该方法（覆盖/实现）了父类方法</p>
	 *█<p>备注：***</p> 
	 *█<p>抛出异常：[]</p>
	 *█<p>触发事件：[]</p>
	 *█<p>示例：</p>
	 *█<p>———————————————————————————————————————————</p>
	 *█   
	 *█<p>———————————————————————————————————————————</p>
	 * @see INIFileInterface#getPropertyComment(java.lang.String, java.lang.String)
	 */

	public String getPropertyComment(String section, String property) {
		INISection s=this.getSection(section);
		if(s==null) {return null;}
		INIProperty  p=s.getProperty(property);
		if(p==null) {return null;}
		List<String> data= p.getComments();
		StringBuilder bu=new StringBuilder();
		String spliter=System.getProperty("line.separator");
		for(String ss:data) {bu.append(ss).append(spliter);}
		return bu.toString().trim();
	}






	/**<P>函数注释：getBooleanProperty()</p>
	 *█<p>———————————————————————————————————————————</p> 
	 *█<p>作者：何明辉</p>
	 *█<p>时间：2013-5-6-上午10:29:50</p>
	 *█<p>类型：方法</p>
	 *█<p>用途：该方法（覆盖/实现）了父类方法</p>
	 *█<p>备注：***</p> 
	 *█<p>抛出异常：[]</p>
	 *█<p>触发事件：[]</p>
	 *█<p>示例：</p>
	 *█<p>———————————————————————————————————————————</p>
	 *█   
	 *█<p>———————————————————————————————————————————</p>
	 * @see INIFileInterface#getBooleanProperty(java.lang.String, java.lang.String)
	 */

	public Boolean getBooleanProperty(String section, String property) {
		INISection s=this.getSection(section);
		if(s==null) {return null;}
		INIProperty  p=s.getProperty(property);
		if(p==null) {return null;}
		return Boolean.valueOf(p.getValue());
	}






	/**<P>函数注释：getDateProperty()</p>
	 *█<p>———————————————————————————————————————————</p> 
	 *█<p>作者：何明辉</p>
	 *█<p>时间：2013-5-6-上午10:29:50</p>
	 *█<p>类型：方法</p>
	 *█<p>用途：该方法（覆盖/实现）了父类方法</p>
	 *█<p>备注：***</p> 
	 *█<p>抛出异常：[]</p>
	 *█<p>触发事件：[]</p>
	 *█<p>示例：</p>
	 *█<p>———————————————————————————————————————————</p>
	 *█   
	 *█<p>———————————————————————————————————————————</p>
	 * @throws ParseException 
	 * @see INIFileInterface#getDateProperty(java.lang.String, java.lang.String)
	 */

	public Date getDateProperty(String section, String property) throws ParseException {
		INISection s=this.getSection(section);
		if(s==null) {return null;}
		INIProperty  p=s.getProperty(property);
		if(p==null) {return null;}
		return new SimpleDateFormat(this.dateFormat).parse(p.getValue());
	}






	/**<P>函数注释：getDoubleProperty()</p>
	 *█<p>———————————————————————————————————————————</p> 
	 *█<p>作者：何明辉</p>
	 *█<p>时间：2013-5-6-上午10:29:50</p>
	 *█<p>类型：方法</p>
	 *█<p>用途：该方法（覆盖/实现）了父类方法</p>
	 *█<p>备注：***</p> 
	 *█<p>抛出异常：[]</p>
	 *█<p>触发事件：[]</p>
	 *█<p>示例：</p>
	 *█<p>———————————————————————————————————————————</p>
	 *█   
	 *█<p>———————————————————————————————————————————</p>
	 * @see INIFileInterface#getDoubleProperty(java.lang.String, java.lang.String)
	 */

	public Double getDoubleProperty(String section, String property) {
		INISection s=this.getSection(section);
		if(s==null) {return null;}
		INIProperty  p=s.getProperty(property);
		if(p==null) {return null;}
		return Double.valueOf(p.getValue());
	}






	/**<P>函数注释：getIntegerProperty()</p>
	 *█<p>———————————————————————————————————————————</p> 
	 *█<p>作者：何明辉</p>
	 *█<p>时间：2013-5-6-上午10:29:50</p>
	 *█<p>类型：方法</p>
	 *█<p>用途：该方法（覆盖/实现）了父类方法</p>
	 *█<p>备注：***</p> 
	 *█<p>抛出异常：[]</p>
	 *█<p>触发事件：[]</p>
	 *█<p>示例：</p>
	 *█<p>———————————————————————————————————————————</p>
	 *█   
	 *█<p>———————————————————————————————————————————</p>
	 * @see INIFileInterface#getIntegerProperty(java.lang.String, java.lang.String)
	 */

	public Integer getIntegerProperty(String section, String property) {
		INISection s=this.getSection(section);
		if(s==null) {return null;}
		INIProperty  p=s.getProperty(property);
		if(p==null) {return null;}
		return Integer.valueOf(p.getValue());
	}






	/**<P>函数注释：getLongProperty()</p>
	 *█<p>———————————————————————————————————————————</p> 
	 *█<p>作者：何明辉</p>
	 *█<p>时间：2013-5-6-上午10:29:50</p>
	 *█<p>类型：方法</p>
	 *█<p>用途：该方法（覆盖/实现）了父类方法</p>
	 *█<p>备注：***</p> 
	 *█<p>抛出异常：[]</p>
	 *█<p>触发事件：[]</p>
	 *█<p>示例：</p>
	 *█<p>———————————————————————————————————————————</p>
	 *█   
	 *█<p>———————————————————————————————————————————</p>
	 * @see INIFileInterface#getLongProperty(java.lang.String, java.lang.String)
	 */

	public Long getLongProperty(String section, String property) {
		INISection s=this.getSection(section);
		if(s==null) {return null;}
		INIProperty  p=s.getProperty(property);
		if(p==null) {return null;}
		return Long.valueOf(p.getValue());
	}






	/**<P>函数注释：getStringProperty()</p>
	 *█<p>———————————————————————————————————————————</p> 
	 *█<p>作者：何明辉</p>
	 *█<p>时间：2013-5-6-上午10:29:50</p>
	 *█<p>类型：方法</p>
	 *█<p>用途：该方法（覆盖/实现）了父类方法</p>
	 *█<p>备注：***</p> 
	 *█<p>抛出异常：[]</p>
	 *█<p>触发事件：[]</p>
	 *█<p>示例：</p>
	 *█<p>———————————————————————————————————————————</p>
	 *█   
	 *█<p>———————————————————————————————————————————</p>
	 * @see INIFileInterface#getStringProperty(java.lang.String, java.lang.String)
	 */

	public String getStringProperty(String section, String property) {
		INISection s=this.getSection(section);
		if(s==null) {return null;}
		INIProperty  p=s.getProperty(property);
		if(p==null) {return null;}
		return p.getValue();
	}






	/**<P>函数注释：getTimestampProperty()</p>
	 *█<p>———————————————————————————————————————————</p> 
	 *█<p>作者：何明辉</p>
	 *█<p>时间：2013-5-6-上午10:29:50</p>
	 *█<p>类型：方法</p>
	 *█<p>用途：该方法（覆盖/实现）了父类方法</p>
	 *█<p>备注：***</p> 
	 *█<p>抛出异常：[]</p>
	 *█<p>触发事件：[]</p>
	 *█<p>示例：</p>
	 *█<p>———————————————————————————————————————————</p>
	 *█   
	 *█<p>———————————————————————————————————————————</p>
	 * @see INIFileInterface#getTimestampProperty(java.lang.String, java.lang.String)
	 */

	public Timestamp  getTimestampProperty(String section, String property) {
		INISection s=this.getSection(section);
		if(s==null) {return null;}
		INIProperty  p=s.getProperty(property);
		if(p==null) {return null;}
		return Timestamp.valueOf(p.getValue());
	}






	/**<P>函数注释：getProperties()</p>
	 *█<p>———————————————————————————————————————————</p> 
	 *█<p>作者：何明辉</p>
	 *█<p>时间：2013-5-6-上午10:29:50</p>
	 *█<p>类型：方法</p>
	 *█<p>用途：该方法（覆盖/实现）了父类方法</p>
	 *█<p>备注：***</p> 
	 *█<p>抛出异常：[]</p>
	 *█<p>触发事件：[]</p>
	 *█<p>示例：</p>
	 *█<p>———————————————————————————————————————————</p>
	 *█   
	 *█<p>———————————————————————————————————————————</p>
	 * @see INIFileInterface#getProperties(java.lang.String)
	 */

	public Map getProperties(String section) {
		INISection s=this.getSection(section);
		if(s==null) {return null;}
		Map<String,String> data=new LinkedHashMap<String,String>();
		for(INIProperty p:s.getProperties()) {
			data.put(p.getKey(), p.getValue());
		}
		return data;
	}






	/**<P>函数注释：setBooleanProperty()</p>
	 *█<p>———————————————————————————————————————————</p> 
	 *█<p>作者：何明辉</p>
	 *█<p>时间：2013-5-6-上午10:29:50</p>
	 *█<p>类型：方法</p>
	 *█<p>用途：该方法（覆盖/实现）了父类方法</p>
	 *█<p>备注：***</p> 
	 *█<p>抛出异常：[]</p>
	 *█<p>触发事件：[]</p>
	 *█<p>示例：</p>
	 *█<p>———————————————————————————————————————————</p>
	 *█   
	 *█<p>———————————————————————————————————————————</p>
	 * @see INIFileInterface#setBooleanProperty(java.lang.String, java.lang.String, java.lang.Boolean, java.lang.String)
	 */

	public void setBooleanProperty(String sectionName, String property, Boolean value, String comment) {
		setStringProperty(sectionName,property,value.toString(),comment);
	}






	/**<P>函数注释：setDateFormat()</p>
	 *█<p>———————————————————————————————————————————</p> 
	 *█<p>作者：何明辉</p>
	 *█<p>时间：2013-5-6-上午10:29:50</p>
	 *█<p>类型：方法</p>
	 *█<p>用途：该方法（覆盖/实现）了父类方法</p>
	 *█<p>备注：***</p> 
	 *█<p>抛出异常：[]</p>
	 *█<p>触发事件：[]</p>
	 *█<p>示例：</p>
	 *█<p>———————————————————————————————————————————</p>
	 *█   
	 *█<p>———————————————————————————————————————————</p>
	 * @see INIFileInterface#setDateFormat(java.lang.String)
	 */

	public void setDateFormat(String format) {
		this.dateFormat=format;
	}






	/**<P>函数注释：setDateProperty()</p>
	 *█<p>———————————————————————————————————————————</p> 
	 *█<p>作者：何明辉</p>
	 *█<p>时间：2013-5-6-上午10:29:50</p>
	 *█<p>类型：方法</p>
	 *█<p>用途：该方法（覆盖/实现）了父类方法</p>
	 *█<p>备注：***</p> 
	 *█<p>抛出异常：[]</p>
	 *█<p>触发事件：[]</p>
	 *█<p>示例：</p>
	 *█<p>———————————————————————————————————————————</p>
	 *█   
	 *█<p>———————————————————————————————————————————</p>
	 * @see INIFileInterface#setDateProperty(java.lang.String, java.lang.String, java.util.Date, java.lang.String)
	 */

	public void setDateProperty(String sectionName, String property, Date value, String comment) {
		setStringProperty(sectionName,property,new SimpleDateFormat(this.dateFormat).format(value).toString(),comment);
	}






	/**<P>函数注释：setDoubleProperty()</p>
	 *█<p>———————————————————————————————————————————</p> 
	 *█<p>作者：何明辉</p>
	 *█<p>时间：2013-5-6-上午10:29:50</p>
	 *█<p>类型：方法</p>
	 *█<p>用途：该方法（覆盖/实现）了父类方法</p>
	 *█<p>备注：***</p> 
	 *█<p>抛出异常：[]</p>
	 *█<p>触发事件：[]</p>
	 *█<p>示例：</p>
	 *█<p>———————————————————————————————————————————</p>
	 *█   
	 *█<p>———————————————————————————————————————————</p>
	 * @see INIFileInterface#setDoubleProperty(java.lang.String, java.lang.String, java.lang.Double, java.lang.String)
	 */

	public void setDoubleProperty(String sectionName, String property, Double value, String comment) {
		setStringProperty(sectionName,property,value.toString(),comment);
	}






	/**<P>函数注释：setIntegerProperty()</p>
	 *█<p>———————————————————————————————————————————</p> 
	 *█<p>作者：何明辉</p>
	 *█<p>时间：2013-5-6-上午10:29:50</p>
	 *█<p>类型：方法</p>
	 *█<p>用途：该方法（覆盖/实现）了父类方法</p>
	 *█<p>备注：***</p> 
	 *█<p>抛出异常：[]</p>
	 *█<p>触发事件：[]</p>
	 *█<p>示例：</p>
	 *█<p>———————————————————————————————————————————</p>
	 *█   
	 *█<p>———————————————————————————————————————————</p>
	 * @see INIFileInterface#setIntegerProperty(java.lang.String, java.lang.String, java.lang.Integer, java.lang.String)
	 */

	public void setIntegerProperty(String sectionName, String property, Integer value, String comment) {
		setStringProperty(sectionName,property,value.toString(),comment);
	}






	/**<P>函数注释：setLongProperty()</p>
	 *█<p>———————————————————————————————————————————</p> 
	 *█<p>作者：何明辉</p>
	 *█<p>时间：2013-5-6-上午10:29:50</p>
	 *█<p>类型：方法</p>
	 *█<p>用途：该方法（覆盖/实现）了父类方法</p>
	 *█<p>备注：***</p> 
	 *█<p>抛出异常：[]</p>
	 *█<p>触发事件：[]</p>
	 *█<p>示例：</p>
	 *█<p>———————————————————————————————————————————</p>
	 *█   
	 *█<p>———————————————————————————————————————————</p>
	 * @see INIFileInterface#setLongProperty(java.lang.String, java.lang.String, java.lang.Long, java.lang.String)
	 */

	public void setLongProperty(String sectionName, String property, Long value, String comment) {
		setStringProperty(sectionName,property,value.toString(),comment);
	}






	/**<P>函数注释：setStringProperty()</p>
	 *█<p>———————————————————————————————————————————</p> 
	 *█<p>作者：何明辉</p>
	 *█<p>时间：2013-5-6-上午10:29:50</p>
	 *█<p>类型：方法</p>
	 *█<p>用途：该方法（覆盖/实现）了父类方法</p>
	 *█<p>备注：***</p> 
	 *█<p>抛出异常：[]</p>
	 *█<p>触发事件：[]</p>
	 *█<p>示例：</p>
	 *█<p>———————————————————————————————————————————</p>
	 *█   
	 *█<p>———————————————————————————————————————————</p>
	 * @see INIFileInterface#setStringProperty(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */

	public void setStringProperty(String sectionName, String property, String value, String comment) {
		INISection s=this.getSection(sectionName);
		if(s==null) {
			INISection sectionx=new INISection();
			sectionx.setName(sectionName);
			this.addSection(sectionx);
			s=sectionx;
		}
		INIProperty p=s.getProperty(property);
		if(p==null) {p=new INIProperty();s.addProperty(p);}
		p.setKey(property);
		p.setValue(value.toString());
		String spliter=System.getProperty("line.separator");
		if(comment==null) {comment="";}
		p.setComments(Arrays.asList(comment.split(spliter)));
	}






	/**<P>函数注释：setTimestampFormat()</p>
	 *█<p>———————————————————————————————————————————</p> 
	 *█<p>作者：何明辉</p>
	 *█<p>时间：2013-5-6-上午10:29:50</p>
	 *█<p>类型：方法</p>
	 *█<p>用途：该方法（覆盖/实现）了父类方法</p>
	 *█<p>备注：***</p> 
	 *█<p>抛出异常：[]</p>
	 *█<p>触发事件：[]</p>
	 *█<p>示例：</p>
	 *█<p>———————————————————————————————————————————</p>
	 *█   
	 *█<p>———————————————————————————————————————————</p>
	 * @see INIFileInterface#setTimestampFormat(java.lang.String)
	 */

	public void setTimestampFormat(String format) {
		mstrTimeStampFmt=format;
	}






	/**<P>函数注释：setTimestampProperty()</p>
	 *█<p>———————————————————————————————————————————</p> 
	 *█<p>作者：何明辉</p>
	 *█<p>时间：2013-5-6-上午10:29:50</p>
	 *█<p>类型：方法</p>
	 *█<p>用途：该方法（覆盖/实现）了父类方法</p>
	 *█<p>备注：***</p> 
	 *█<p>抛出异常：[]</p>
	 *█<p>触发事件：[]</p>
	 *█<p>示例：</p>
	 *█<p>———————————————————————————————————————————</p>
	 *█   
	 *█<p>———————————————————————————————————————————</p>
	 * @see INIFileInterface#setTimestampProperty(java.lang.String, java.lang.String, java.sql.Timestamp, java.lang.String)
	 */

	public void setTimestampProperty(String sectionName, String property, Timestamp value, String comment) {
		this.setStringProperty(sectionName, property,new SimpleDateFormat(mstrTimeStampFmt).format(value), comment);
	}






	/**<P>函数注释：getAllSectionNames()</p>
	 *█<p>———————————————————————————————————————————</p> 
	 *█<p>作者：何明辉</p>
	 *█<p>时间：2013-5-6-上午10:29:50</p>
	 *█<p>类型：方法</p>
	 *█<p>用途：该方法（覆盖/实现）了父类方法</p>
	 *█<p>备注：***</p> 
	 *█<p>抛出异常：[]</p>
	 *█<p>触发事件：[]</p>
	 *█<p>示例：</p>
	 *█<p>———————————————————————————————————————————</p>
	 *█   
	 *█<p>———————————————————————————————————————————</p>
	 * @see INIFileInterface#getAllSectionNames()
	 */

	public String[] getAllSectionNames() {
		List<INISection> list=sections;
		String names[]=new String[list.size()];
		for(int i=0;i<list.size();i++) {
			INISection IS=list.get(i);
			names[i]=IS.getName();
		}
		return names;
	}






	/**<P>函数注释：getTotalSections()</p>
	 *█<p>———————————————————————————————————————————</p> 
	 *█<p>作者：何明辉</p>
	 *█<p>时间：2013-5-6-上午10:29:50</p>
	 *█<p>类型：方法</p>
	 *█<p>用途：该方法（覆盖/实现）了父类方法</p>
	 *█<p>备注：***</p> 
	 *█<p>抛出异常：[]</p>
	 *█<p>触发事件：[]</p>
	 *█<p>示例：</p>
	 *█<p>———————————————————————————————————————————</p>
	 *█   
	 *█<p>———————————————————————————————————————————</p>
	 * @see INIFileInterface#getTotalSections()
	 */

	public int getTotalSections() {
		return sections.size();
	}






	/**<P>函数注释：getFileName()</p>
	 *█<p>———————————————————————————————————————————</p> 
	 *█<p>作者：何明辉</p>
	 *█<p>时间：2013-5-6-上午10:29:50</p>
	 *█<p>类型：方法</p>
	 *█<p>用途：该方法（覆盖/实现）了父类方法</p>
	 *█<p>备注：***</p> 
	 *█<p>抛出异常：[]</p>
	 *█<p>触发事件：[]</p>
	 *█<p>示例：</p>
	 *█<p>———————————————————————————————————————————</p>
	 *█   
	 *█<p>———————————————————————————————————————————</p>
	 * @see INIFileInterface#getFileName()
	 */

	public String getFileName() {
		return file;
	}






	/**<P>函数注释：getPropertyNames()</p>
	 *█<p>———————————————————————————————————————————</p> 
	 *█<p>作者：何明辉</p>
	 *█<p>时间：2013-5-6-上午10:29:50</p>
	 *█<p>类型：方法</p>
	 *█<p>用途：该方法（覆盖/实现）了父类方法</p>
	 *█<p>备注：***</p> 
	 *█<p>抛出异常：[]</p>
	 *█<p>触发事件：[]</p>
	 *█<p>示例：</p>
	 *█<p>———————————————————————————————————————————</p>
	 *█   
	 *█<p>———————————————————————————————————————————</p>
	 * @see INIFileInterface#getPropertyNames(java.lang.String)
	 */

	public String[] getPropertyNames(String section) {
		INISection s=this.getSection(section);
		if(s==null) {return null;}
		List<INIProperty> properties=s.getProperties();
		String[] data=new String[properties.size()];
		for(int i=0;i<properties.size();i++) {
			data[i]=properties.get(i).getKey();
		}
		return data;
	}






	/**<P>函数注释：getStringComment()</p>
	 *█<p>———————————————————————————————————————————</p> 
	 *█<p>作者：何明辉</p>
	 *█<p>时间：2013-5-6-上午10:29:50</p>
	 *█<p>类型：方法</p>
	 *█<p>用途：该方法（覆盖/实现）了父类方法</p>
	 *█<p>备注：***</p> 
	 *█<p>抛出异常：[]</p>
	 *█<p>触发事件：[]</p>
	 *█<p>示例：</p>
	 *█<p>———————————————————————————————————————————</p>
	 *█   
	 *█<p>———————————————————————————————————————————</p>
	 * @see INIFileInterface#getStringComment(java.lang.String)
	 */

	public String getStringComment(String section) {
		INISection s=this.getSection(section);
		if(s==null) {return null;}
		List<String>cc= s.getHeaderComments();
		StringBuilder bu=new StringBuilder();
		String spliter=System.getProperty("line.separator");
		for(String sc:cc) {bu.append(sc).append(spliter);}
		return bu.toString().trim();
	}






	/**<P>函数注释：getStringComment()</p>
	 *█<p>———————————————————————————————————————————</p> 
	 *█<p>作者：何明辉</p>
	 *█<p>时间：2013-5-6-上午10:29:50</p>
	 *█<p>类型：方法</p>
	 *█<p>用途：该方法（覆盖/实现）了父类方法</p>
	 *█<p>备注：***</p> 
	 *█<p>抛出异常：[]</p>
	 *█<p>触发事件：[]</p>
	 *█<p>示例：</p>
	 *█<p>———————————————————————————————————————————</p>
	 *█   
	 *█<p>———————————————————————————————————————————</p>
	 * @see INIFileInterface#getStringComment(java.lang.String, java.lang.String)
	 */

	public String getStringComment(String section, String property) {
		INISection s=this.getSection(section);
		if(s==null) {return null;}
		INIProperty p=s.getProperty(property);
		if(p==null) {return null;}
		List<String>cc= p.getComments();
		StringBuilder bu=new StringBuilder();
		String spliter=System.getProperty("line.separator");
		for(String sc:cc) {bu.append(sc).append(spliter);}
		return bu.toString().trim();
	}




	/**
	 * <P>函数注释：initial()</p>
	 *█<p>———————————————————————————————————————————</p> 
	 *█<p>作者：何明辉</p>
	 *█<p>时间：2013-5-13-上午10:27:35</p>
	 *█<p>类型：方法</p>
	 *█<p>用途：该方法用于初始化数据</p>
	 *█<p>备注：如果发现文件没有改动就不再读文件了，直接返回，否则重新读文件</p> 
	 *█<p>抛出异常：[]</p>
	 *█<p>触发事件：[]</p>
	 *█<p>示例：</p>
	 *█<p>———————————————————————————————————————————</p>
	 *█   
	 *█<p>———————————————————————————————————————————</p>
	 */
	public void initial(){
		//如果文件并未修改则不用再读取————————————————————————————————
		if((new File(file).lastModified()+"").equals(lastModifyTime.get(file))) {return;}
		//如果文件未读取过或者已修改过则重新读取————————————————————————————
		this.sections.clear();
		String filedata=readFile();
		if ("UTF-8".equals(getCharset()) || "UTF8".equals(getCharset())) {
			byte[] bb=filedata.getBytes();
			if(bb[0] == (byte) 0xEF && bb[1] == (byte) 0xBB && bb[2] == (byte) 0xBF) {
				filedata=filedata.substring(1);
			}
		}
		filedata="abc\r\n"+filedata;
		String spliter=System.getProperty("line.separator");
		List<String> contents=Arrays.asList(filedata.split(spliter));

		int start=0,end=contents.size()-1;		
		for(int i=contents.size()-1;i>=0;i--) {
			String s=contents.get(i);
			if(s==null||"".equals(s.trim())) {continue;}
			if(s.trim().startsWith("[")&&start!=-1) {start=-1;continue;}
			else if(s.trim().startsWith("[")&&start==-1) {
				createSection(Arrays.asList(new String[] {s}));
			}
			if(!(s.trim().startsWith("#")||s.trim().startsWith(";")||s.trim().startsWith("//"))&&start==-1) {
				List<String> temp=contents.subList(i+1, end+1);
				createSection(temp);
				end=i;start=0;
			}

		}
		this.lastModifyTime.put(file, new File(file).lastModified()+"");
		Collections.reverse(this.sections);

	}


	public void initial(String content){
		this.sections.clear();
		String filedata=content;
		byte[] bb=filedata.getBytes();
		if(bb[0] == (byte) 0xEF && bb[1] == (byte) 0xBB && bb[2] == (byte) 0xBF) {
			filedata=filedata.substring(1);
		}
		filedata="abc\r\n"+filedata;
		String spliter=System.getProperty("line.separator");
		List<String> contents=Arrays.asList(filedata.split(spliter));

		int start=0,end=contents.size()-1;		
		for(int i=contents.size()-1;i>=0;i--) {
			String s=contents.get(i);
			if(s==null||"".equals(s.trim())) {continue;}
			if(s.trim().startsWith("[")&&start!=-1) {start=-1;continue;}
			else if(s.trim().startsWith("[")&&start==-1) {
				createSection(Arrays.asList(new String[] {s}));
			}
			if(!(s.trim().startsWith("#")||s.trim().startsWith(";")||s.trim().startsWith("//"))&&start==-1) {
				List<String> temp=contents.subList(i+1, end+1);
				createSection(temp);
				end=i;start=0;
			}

		}
		Collections.reverse(this.sections);
	}
	/**
	 * <P>函数注释：createSection()</p>
	 *█<p>———————————————————————————————————————————</p> 
	 *█<p>作者：何明辉</p>
	 *█<p>时间：2013-5-13-上午10:27:14</p>
	 *█<p>类型：方法</p>
	 *█<p>用途：该方法用于创建一个板块</p>
	 *█<p>备注：内部调用</p> 
	 *█<p>抛出异常：[]</p>
	 *█<p>触发事件：[]</p>
	 *█<p>示例：</p>
	 *█<p>———————————————————————————————————————————</p>
	 *█   
	 *█<p>———————————————————————————————————————————</p>
	 *@param data
	 */
	private void createSection(List<String> data) {
		StringBuilder bu=new StringBuilder();
		String spliter=System.getProperty("line.separator");
		for(String s:data) {
			bu.append(s).append(spliter);
		}
		this.sections.add(new INISection(bu.toString().trim()));
	}

	/**
	 * <P>函数注释：readFile()</p>
	 *█<p>———————————————————————————————————————————</p> 
	 *█<p>作者：何明辉</p>
	 *█<p>时间：2013-5-13-上午10:27:01</p>
	 *█<p>类型：方法</p>
	 *█<p>用途：该方法用于读文件</p>
	 *█<p>备注：***</p> 
	 *█<p>抛出异常：[]</p>
	 *█<p>触发事件：[]</p>
	 *█<p>示例：</p>
	 *█<p>———————————————————————————————————————————</p>
	 *█   
	 *█<p>———————————————————————————————————————————</p>
	 *@return
	 */
	private String readFile() {
		if(file.length()<=0) {return "";}
		if(new File(file).length()<=0) {return "";}
		String charset=getCharset();
		if(charset==null||"".equals(charset)) {charset="GBK";}
		InputStreamReader reader;
		try {
			reader=new InputStreamReader(new FileInputStream(file),charset);
			char data[]=new char[5242880];//5MB
			int length=reader.read(data);
			if(length<=0){return "";}
			if ("feff".equalsIgnoreCase(Integer.toHexString(data[0]))){
				return new String(data,1,length); 
			}
			return new String(data,0,length);
		}
		catch (UnsupportedEncodingException exception) {
//			TextLogger.getLogger().log(TextLogger.WARNING,exception.getMessage());
		}
		catch (FileNotFoundException exception) {
//			TextLogger.getLogger().log(TextLogger.WARNING,exception.getMessage());
		}
		catch (IOException exception) {
//			TextLogger.getLogger().log(TextLogger.WARNING,exception.getMessage());
		}
		return "";

	}

	/**
	 * <P>函数注释：writeFile()</p>
	 *█<p>———————————————————————————————————————————</p> 
	 *█<p>作者：何明辉</p>
	 *█<p>时间：2013-5-13-上午10:26:52</p>
	 *█<p>类型：方法</p>
	 *█<p>用途：该方法用于写文件</p>
	 *█<p>备注：***</p> 
	 *█<p>抛出异常：[]</p>
	 *█<p>触发事件：[]</p>
	 *█<p>示例：</p>
	 *█<p>———————————————————————————————————————————</p>
	 *█   
	 *█<p>———————————————————————————————————————————</p>
	 *@param context
	 *@throws IOException
	 */
	private void writeFile(String context) throws IOException {
		if(context==null||"".equals(context)){return;}
		String charset=getCharset();
		if(charset==null||"".equals(charset)) {charset="GBK";}
		OutputStreamWriter writer=new OutputStreamWriter(new FileOutputStream(file),charset);
		writer.write(context);
		writer.flush();
		writer.close();
	}


	/**
	 * <P>函数注释：save()</p>
	 *█<p>———————————————————————————————————————————</p> 
	 *█<p>作者：何明辉</p>
	 *█<p>时间：2013-5-13-上午10:26:31</p>
	 *█<p>类型：方法</p>
	 *█<p>用途：该方法用于将数据保存到文件</p>
	 *█<p>备注：***</p> 
	 *█<p>抛出异常：[]</p>
	 *█<p>触发事件：[]</p>
	 *█<p>示例：</p>
	 *█<p>———————————————————————————————————————————</p>
	 *█   
	 *█<p>———————————————————————————————————————————</p>
	 * @see com.cnunisoft.util.INIFileInterface#save()
	 */
	public synchronized boolean save() {
		List<INISection> list=sections;
		if (list == null) { return false; }
		try {
			for (Object o : list) {
				// 在保存之前先进行校验
				INISection s=(INISection) o;
				List<INIProperty> properties=s.getProperties();
				boolean flag=false;
				for (INIProperty p : properties) {
					if (p.getKey() == null || "".equals(p.getKey()) || "".equals(p.toString())) {
						continue;
					}
					flag=true;
				}
				if (!flag) {
//					TextLogger.getLogger().log(TextLogger.WARNING, "[" + s.getName() + "]" + "不能没有实际内容，必须有配置项");
					return false;
				}
			}
			String data=this.toString().trim();
			writeFile(data);
			return true;
		}
		catch (Exception exception) {
//			TextLogger.getLogger().log(TextLogger.WARNING,exception.getMessage());
		}
		return false;
	}


	/**
	 * <P>函数注释：toString()</p>
	 *█<p>———————————————————————————————————————————</p> 
	 *█<p>作者：何明辉</p>
	 *█<p>时间：2013-5-13-上午10:25:58</p>
	 *█<p>类型：方法</p>
	 *█<p>用途：该方法用于将信息字符串化</p>
	 *█<p>备注：***</p> 
	 *█<p>抛出异常：[]</p>
	 *█<p>触发事件：[]</p>
	 *█<p>示例：</p>
	 *█<p>———————————————————————————————————————————</p>
	 *█   
	 *█<p>———————————————————————————————————————————</p>
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		List<INISection> list=sections;
		if(list==null) {return null;}
		StringBuilder bu=new StringBuilder();
		String spliter=System.getProperty("line.separator");
		for (Object o : list) {
			bu.append(o.toString()).append(spliter);
		}
		return bu.toString();
	}






	/**
	 * <P>函数注释：getCharset()</p>
	 *█<p>———————————————————————————————————————————</p> 
	 *█<p>作者：何明辉</p>
	 *█<p>时间：2013-5-13-上午10:25:45</p>
	 *█<p>类型：方法</p>
	 *█<p>用途：该方法用于获取文件的编码</p>
	 *█<p>备注：***</p> 
	 *█<p>抛出异常：[]</p>
	 *█<p>触发事件：[]</p>
	 *█<p>示例：</p>
	 *█<p>———————————————————————————————————————————</p>
	 *█   
	 *█<p>———————————————————————————————————————————</p>
	 *@return
	 */
	private String getCharset() {
		if(!this.charset.equals("")) {return this.charset;}
		if(!new File(file).exists()){return "UTF-8";}
		String charset ="GBK";
		byte[] first3Bytes = new byte[3];
		BufferedInputStream bis = null;
		try {
			boolean checked = false;
			bis = new BufferedInputStream(new FileInputStream(file));
			bis.mark(0);
			int read = bis.read(first3Bytes, 0, 3);
			if (read == -1)
				return charset;
			if (first3Bytes[0] == (byte) 0xFF && first3Bytes[1] == (byte) 0xFE) {
				charset = "Unicode";//UTF-16LE
				checked = true;
			} else if (first3Bytes[0] == (byte) 0xFE && first3Bytes[1] == (byte) 0xFF) {
				charset = "Unicode";//UTF-16BE
				checked = true;
			} else if ((first3Bytes[0] == (byte) 0xEF && first3Bytes[1] == (byte) 0xBB && first3Bytes[2] == (byte) 0xBF) || ("feff".equalsIgnoreCase(Integer.toHexString(first3Bytes[0])))) {
				charset = "UTF8";
				checked = true;
			}
			bis.reset();
			if (!checked) {
				while ((read = bis.read()) != -1) {
					if (read >= 0xF0)
						break;
					if (0x80 <= read && read <= 0xBF) //单独出现BF以下的，也算是GBK
						break;
					if (0xC0 <= read && read <= 0xDF) {
						read = bis.read();
						if (0x80 <= read && read <= 0xBF) //双字节 (0xC0 - 0xDF) (0x80 - 0xBF),也可能在GB编码内
							continue;
						else
							break;
					} else if (0xE0 <= read && read <= 0xEF) { //也有可能出错，但是几率较小
						read = bis.read();
						if (0x80 <= read && read <= 0xBF) {
							read = bis.read();
							if (0x80 <= read && read <= 0xBF) {
								charset = "UTF-8";
								break;
							} else
								break;
						} else
							break;
					}
				}
				//TextLogger.getLogger().info(loc + " " + Integer.toHexString(read));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (bis != null) {
				try {
					bis.close();
				} catch (IOException ex) {
				}
			}
		}
		return charset;
	}



}
