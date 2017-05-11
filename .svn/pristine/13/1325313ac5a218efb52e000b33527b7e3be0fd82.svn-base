package HHH.HREST;
import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.dom4j.Document;
import org.dom4j.Element;
import HHH.HAnnotations.HAnnotationUtil;
import HHH.HAnnotations.HWeb.HRepository;
import HHH.HSQL.HSQL;
import HHH.HUTIL.StringUtils;
import HHH.HXML.HXML;

@HRepository
public class HDAO {
	
	public Object getConnection(){return null;}
	
	private static Map<String,String> sqls=new HashMap<String,String>();
	private static Map<String,Map<String,String>> sqlsmapping=new HashMap<String,Map<String,String>>();
	
	public String getSQL(String id){return sqls.get(id);}
	public Map<String,String> getMapping(String id){return sqlsmapping.get(id);}
	
	
	public List<Map<String, Object>> query4Maps(String id,Map<String,Object> params) throws SQLException{
		List<Map<String, Object>> list=new ArrayList<Map<String, Object>>();
		String sql=processSQL(id,params);
		params.remove("pageSize");
		params.remove("START_INDEX");
		
		String countsql=processSQL(id+"_count",params);
		Object conn=getConnection();
		System.out.println("[HDAO]query4Maps:"+id+"-------------------------");
		System.out.println("[HDAO]SQL:"+sql);
		System.out.println("[HDAO]COUNT-SQL:"+countsql);
		try {
			if(conn instanceof HSQL){return ((HSQL) conn).selects(sql);}
		} catch (Exception e) {
			System.out.println("[HDAO]SQL Execute Failed::"+sql);
		}
		System.out.println("[HDAO]-------------------------");
		return list;
	}
	
	
	public void insert(String id,Map<String,Object> params) throws SQLException{
		String sql=processSQL(id,params);
		Object conn=getConnection();
		System.out.println("[HDAO]insert:"+id+"-------------------------");
		System.out.println("[HDAO]SQL:"+sql);
		try {
			if(conn instanceof HSQL){ ((HSQL) conn).Insert(sql);}
		}catch (Exception e) {
			System.out.println("[HDAO]SQL Execute Failed::"+sql);
		}
		System.out.println("[HDAO]-------------------------");
	}
	
	
	
	public void update(String id,Map<String,Object> params) throws SQLException{
		String sql=processSQL(id,params);
		Object conn=getConnection();
		System.out.println("[HDAO]update:"+id+"-------------------------");
		System.out.println("[HDAO]SQL:"+sql);
		try {
			if(conn instanceof HSQL){ ((HSQL) conn).update(sql);}
			
		}catch (Exception e) {
			System.out.println("[HDAO]SQL Execute Failed::"+sql);
		}
		System.out.println("[HDAO]-------------------------");
	}
	
	
	
	public void delete(String id,Map<String,Object> params) throws SQLException{
		String sql=processSQL(id,params);
		Object conn=getConnection();
		System.out.println("[HDAO]delete:"+id+"-------------------------");
		System.out.println("[HDAO]SQL:"+sql);
		try {
			if(conn instanceof HSQL){ ((HSQL) conn).Delete(sql);}
		}catch (Exception e) {
			System.out.println("[HDAO]SQL Execute Failed::"+sql);
		}
		System.out.println("[HDAO]-------------------------");
	}
	
	
	public void execute(String id,Map<String,Object> params) throws SQLException{
		String sql=processSQL(id,params);
		Object conn=getConnection();
		System.out.println("[HDAO]execute:"+id+"-------------------------");
		System.out.println("[HDAO]SQL:"+sql);
		try {
			if(conn instanceof HSQL){ ((HSQL) conn).Excute(sql);}
		}catch (Exception e) {
			System.out.println("[HDAO]SQL Execute Failed::"+sql);
		}
		System.out.println("[HDAO]-------------------------");
	}
	
	
	
	public String processSQL(String id,Map<String,Object> params){
		String xmlsql=getSQL(id);
		if(HHH.HUTIL.StringUtils.isBlank(xmlsql)){return "";}
		String sql=xmlsql.replace("\t", " ");
		Iterator it=params.keySet().iterator();
		Map<String,String> mapping=getMapping(id);
		while(it.hasNext()){
			String key=it.next().toString();
			List<Object> values=new ArrayList<Object>();
			if(params.get(key.toString()) instanceof Collection){
				values.addAll((Collection<? extends Object>) params.get(key.toString()));
			}else if(params.get(key.toString()).getClass().isArray()){
				values.addAll(Arrays.asList(params.get(key.toString())));
			}else{
				String value=params.get(key.toString()).toString();
				values.add(value);
			}
			String item="";
			if("timestamp".equals(mapping.get(key))||
					"varchar".equals(mapping.get(key))||
					"string".equals(mapping.get(key))||
					mapping.get(key)==null){item="'"+HHH.HUTIL.StringUtils.join(values, "','")+"'";}
			if("int".equals(mapping.get(key))||"fuzzy".equals(mapping.get(key))){
				item=HHH.HUTIL.StringUtils.join(values, ",");
			}
			sql=sql.replace("#"+key+"#",item).replace("%"+key+"%",item);
		}
		String texts[]=sql.split("\n");
		for(int i=0;i<texts.length;i++){
			texts[i]=texts[i].trim();
			if(texts[i].startsWith("%")&&texts[i].endsWith("%")){texts[i]="";}
			if(!(texts[i].startsWith("<<")&&texts[i].endsWith(">>"))){continue;}
			if(!texts[i].contains("#")){texts[i]=texts[i].substring(2, texts[i].length()-2);}
			else{texts[i]="";}
		}
		String text=StringUtils.join(texts," ").trim();
		Object START_INDEX=params.get("START_INDEX");
		Object pageSize=params.get("pageSize");
		if(pageSize!=null&&!"".equals(pageSize)){
			text+=" limit "+pageSize;
		}
		if(START_INDEX!=null&&!"".equals(START_INDEX)){
			text+=" ,offset "+START_INDEX;
		}
		if(params.get("PART_LIMIT")!=null){
			text+=(" "+params.get("PART_LIMIT").toString());
		}
		return text;
	}
	
	
	
	public static void scanXML(){
		try {
			List<Class> HRepositorys=HAnnotationUtil.AnnotationType(HRepository.class);
			for (Class classz : HRepositorys) {
				File file=new File(classz.getClassLoader().getResource("").toURI().toString().replace("file:\\", "").replace("file:/", "")+classz.getName().replace(".", "\\")+".xml");
				if(!file.exists()){continue;}
				Document doc=HXML.toDocument(file);
				List<Element> elemtns=doc.getRootElement().elements();
				for (Element element : elemtns) {
					String name=element.getName();
					String id=element.attributeValue("id");
					String sql=((Element)element.elements().get(0)).getText();
					sqls.put(id, sql);
					if(element.elements().size()>1){
						Map<String,String> temp=new HashMap<String,String>();
						List<Element> params=((Element)element.elements().get(1)).elements();
						for (Element element2 : params) {
							String pname=element2.attributeValue("name");
							String ptype=element2.attributeValue("type");
							temp.put(pname, ptype);
						}
						sqlsmapping.put(id, temp);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
