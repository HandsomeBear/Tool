



/**<P>文件注释：HControllerTest.java</p>
 *█<p>———————————————————————————————————————————</p> 
 *█<p>作者：何明辉</p>
 *█<p>时间：2016年3月13日-下午11:39:26</p>
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
package HHH.HREST;

import HHH.HAnnotations.HWeb.HController;
import HHH.HAnnotations.HWeb.HMethod;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**<P>类型注释：</p>
 *█<p>———————————————————————————————————————————</p> 
 *█<p>作者：何明辉</p>
 *█<p>时间：2016年3月13日-下午11:39:26</p>
 *█<p>类型：HControllerTest</p>
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

@HController("test")
public class HControllerTest {
	
	
	
	@HMethod("a")
	public void a(HttpServletRequest request,HttpServletResponse  response) throws IOException {
		response.getWriter().write("a");
	}
	
	@HMethod("b")
	public void b(HttpServletRequest request,HttpServletResponse  response) throws IOException {
		response.getWriter().write("b");
	}
	
	@HMethod("c")
	public HMessage c(HttpServletRequest request,HttpServletResponse  response) throws IOException {
		HMessage m=new HMessage();
		m.data("heminghui");
		return m;
	}
	
	
	@HMethod("d")
	public Object d(HttpServletRequest request,HttpServletResponse  response) throws IOException {
		return "你成功啦";
	}
	
	
	
	
	
}
