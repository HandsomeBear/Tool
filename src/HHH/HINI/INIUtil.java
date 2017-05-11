package HHH.HINI;


import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**<P>文件注释：INIUtil.java</p>
 *█<p>———————————————————————————————————————————</p> 
 *█<p>作者：何明辉</p>
 *█<p>时间：2013-5-4-下午3:49:10</p>
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

public class INIUtil {

	private static Map<String,INIImpl> INIImpl=new HashMap<String,INIImpl>();//用户缓存INI实例



	/**<P>函数注释：INIUtil()</p>
	 *█<p>———————————————————————————————————————————</p> 
	 *█<p>作者：何明辉</p>
	 *█<p>时间：2013-5-4-下午4:00:11</p>
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
	private INIUtil() {}


	public static INIImpl getInstance(String file) {
		if(!(new File(file).exists())) {
			INIImpl il= new INIImpl();
			il.file=file;
			return il;
		}
		if(INIImpl.get(file)==null) {INIImpl.put(file, new INIImpl());}
		INIImpl impl=INIImpl.get(file);
		impl.file=file;
		impl.charset="";
		impl.initial();
		return impl;
	}

	public static INIImpl getInstance(String file,String charset){
		if(!(new File(file).exists())) {
			INIImpl il= new INIImpl();
			il.file=file;
			return il;
		}
		if(INIImpl.get(file)==null) {INIImpl.put(file, new INIImpl());}
		INIImpl impl=INIImpl.get(file);
		impl.file=file;
		impl.charset=charset;
		impl.initial();
		return impl;
	}



}
