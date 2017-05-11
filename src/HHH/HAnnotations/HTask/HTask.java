



/**<P>文件注释：HTask.java</p>
 *█<p>———————————————————————————————————————————</p> 
 *█<p>作者：何明辉</p>
 *█<p>时间：2015年11月25日-下午11:31:02</p>
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
package HHH.HAnnotations.HTask;

import java.lang.annotation.Documented;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**<P>类型注释：</p>
 *█<p>———————————————————————————————————————————</p> 
 *█<p>作者：何明辉</p>
 *█<p>时间：2015年11月25日-下午11:31:02</p>
 *█<p>类型：HTask</p>
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
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface HTask {
	
	
	public String id() ;
	public String name() ;
}
