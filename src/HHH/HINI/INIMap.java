package HHH.HINI;




import java.util.ArrayList;
import java.util.HashMap;





/**
 * <p>作者：何明辉</p>
 * <p>时间：2010-5-14-下午01:29:08</p>
 * <p>类型：HSortedMap</p>
 * <p>用途：该类型用于存储分类数据，方便统计</p>
 * <p>备注：和普通Map不同，该Map的put(String name,Object value)方法将向name里面添加value的元素，而不是替换</p>
 */
public class INIMap extends HashMap<String,Object> {



	/**
	 * <p>作者：何明辉</p>
	 * <p>时间：2010-5-14-下午12:54:21</p>
	 * <p>类型：方法</p>
	 * <p>用途：该方法代理了父类方法</p>
	 * <p>备注：***</p>
	 * <p>@see java.util.AbstractMap#equals(java.lang.Object)</p>
	 */

	public boolean equals(Object arg0) {
		return super.equals(arg0);
	}



	/**
	 * <p>作者：何明辉</p>
	 * <p>时间：2010-5-14-下午12:54:21</p>
	 * <p>类型：方法</p>
	 * <p>用途：该方法代理了父类方法</p>
	 * <p>备注：***</p>
	 * <p>@see java.util.HashMap#get(java.lang.Object)</p>
	 */
	@SuppressWarnings("unchecked")
	public ArrayList<Object> get(String name) {
		return (ArrayList<Object>) super.get(name);
	}



	/**
	 * <p>作者：何明辉</p>
	 * <p>时间：2010-5-14-下午12:54:21</p>
	 * <p>类型：方法</p>
	 * <p>用途：该方法代理了父类方法</p>
	 * <p>备注：***</p>
	 * <p>@see java.util.AbstractMap#hashCode()</p>
	 */

	public int hashCode() {
		return super.hashCode();
	}



	/**
	 * <p>作者：何明辉</p>
	 * <p>时间：2010-5-14-下午12:54:21</p>
	 * <p>类型：方法</p>
	 * <p>用途：该方法代理了父类方法</p>
	 * <p>备注：***</p>
	 * <p>@see java.util.HashMap#isEmpty()</p>
	 */

	public boolean isEmpty() {
		return super.isEmpty();
	}



	/**
	 * <p>作者：何明辉</p>
	 * <p>时间：2010-5-14-下午12:54:21</p>
	 * <p>类型：方法</p>
	 * <p>用途：该方法代理了父类方法</p>
	 * <p>备注：***</p>
	 * <p>@see java.util.HashMap#put(java.lang.Object, java.lang.Object)</p>
	 */
	public ArrayList<?> put(String name, ArrayList<?> value) {
		return (ArrayList<?>) super.put(name, value);
	}



	/**
	 * <p>作者：何明辉</p>
	 * <p>时间：2010-5-14-下午01:21:23</p>
	 * <p>类型：方法</p>
	 * <p>用途：该方法用于向指定name的list里面添加数据</p>
	 * <p>备注：此方法向相同name的value里面添加元素，而不是替换name的value</p>
	 * @param name
	 * @param value
	 * @return 
	 */
	public Object put(String name, Object value) {
		ArrayList<Object> list=this.get(name);
		if (list == null) {
			list=new ArrayList<Object>();
			super.put(name, list);
		}
		list.add(value);
		return list;
	}


}
