package HHH.HSQL.retriver;
import java.util.List;
import java.util.Map;








/**文件注释：ISQLRetriver.java<br>
 *作者：何明辉<br>
 *时间：2015-1-31-下午7:22:21<br>
 *类型：文件<br>
 *用途：该文件用于<br>
 *备注：***<br> 
 */
/**类型注释：<br>
 *作者：何明辉<br>
 *时间：2015-1-31-下午7:22:21<br>
 *类型：ISQLRetriver<br>
 *用途：该类型用于<br>
 *备注：<br>
 */
public interface ISQLRetriver {
	public void start();
	
	public boolean aftersplit(List<Map<String,Object>> data) throws Exception;
	
	public boolean beforecompress(byte data[]);
	
	public boolean aftercompress(byte data[]);
	
	public void finish(List<byte[]> data);
	
}
