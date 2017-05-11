package HHH.HSQL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.fasterxml.jackson.core.JsonProcessingException;

import HHH.HJSON.HJSON;
import HHH.HUTIL.ListHelper;
import HHH.HUTIL.StringUtils;





public class HSqlserver implements HSQL {

	private  String dataBaseTag="jdbc:sqlserver://";
	private  String dataBaseUrl = "192.168.2.29";
	private  String dataBasePort="1433";
	private  String dataBaseName="XKDB";
	private  String username = "sa";
	private  String password = "1987413";
	private  Connection con;
	private  Statement stmt;
	private  Statement stmt_FORWARD_ONLY;
	private  Statement stmt_SCROLL_INSENSITIVE;
	private  ResultSet rs;
	public static Set<Map<String,Object>> tableStructure=new HashSet<Map<String,Object>>();
	public static  String tableStructureLoc="tableStructure";
	public Map<String,String> preparedSQL=new HashMap<String, String>();
	public Map<String,PreparedStatement> preparedStatement=new HashMap<String, PreparedStatement>();
	
	public HSqlserver(String dataBaseUrl, String dataBasePort, String dataBaseName, String username, String password) {
		super();
		this.dataBaseUrl=dataBaseUrl;
		this.dataBasePort=dataBasePort;
		this.dataBaseName=dataBaseName;
		this.username=username;
		this.password=password;
	}

	@Override
	public String getDataBasePort() {
		return this.dataBasePort;
	}

	@Override
	public Connection getConnection() {
		return this.con;
	}

	@Override
	public String getDataBaseName() {
		return this.dataBaseName;
	}

	@Override
	public String getDataBaseTag() {
		return this.dataBaseTag;
	}

	@Override
	public String getDataBaseUrl() {
		return this.dataBaseUrl;
	}

	@Override
	public Statement getStmt() {
		return this.stmt;
	}

	@Override
	public String getUsername() {
		return this.username;
	}

	@Override
	public String getPassword() {
		return this.password;
	}

	@Override
	public ResultSet getRs() {
		return this.rs;
	}

	@Override
	public void setConnection(Connection con) {
		this.con = con;
		try {
			if(stmt==null){
				stmt = con.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void setStmt(Statement stmt) {
		this.stmt = stmt;
	}
	
	@Override
	public void setDataBaseName(String dataBaseName) {
		this.dataBaseName = dataBaseName;
	}

	@Override
	public void setDataBasePort(String dataBasePort) {
		this.dataBasePort = dataBasePort;
	}

	@Override
	public void setDataBaseTag(String dataBaseTag) {
		this.dataBaseTag = dataBaseTag;
	}

	@Override
	public void setDataBaseUrl(String dataBaseUrl) {
		this.dataBaseUrl = dataBaseUrl;
	}

	@Override
	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public void connect() {
			try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		// ��������
		try {
			con = DriverManager.getConnection(dataBaseTag+dataBaseUrl+":"+dataBasePort+";DatabaseName="+dataBaseName,username,password);
			stmt = con.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
			getTableStructures();
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void close() {
		try {
			try {
				if(stmt!=null)stmt.close();
				if(stmt_FORWARD_ONLY!=null)stmt_FORWARD_ONLY.close();
				if(stmt_SCROLL_INSENSITIVE!=null)stmt_SCROLL_INSENSITIVE.close();
				Iterator<PreparedStatement> it=preparedStatement.values().iterator();
				while(it.hasNext()){
					PreparedStatement st=it.next();
					if(st!=null){st.close();}
				}
			} catch (Exception e) {
			}
			preparedStatement.clear();
			if (con != null && !con.isClosed())con.close();
			this.con = null;
			preparedSQL.clear();
		} catch (SQLException e) {
			System.out.println("�����׳�������|�쳣��:���������close()������");
			System.out.println("������|�쳣�����ͣ�SQLException");
			e.printStackTrace();
		}
	}

	
	@Override
	public Statement getStmt_FORWARD_ONLY() throws SQLException {
		if(stmt_FORWARD_ONLY==null) {
//			stmt_FORWARD_ONLY = con.createStatement(SQLServerResultSet.TYPE_SS_SERVER_CURSOR_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
			stmt_FORWARD_ONLY = con.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
			stmt.setFetchSize(10000);
		}
		return stmt_FORWARD_ONLY;
	}
	
	@Override
	public Statement getStmt_SCROLL_INSENSITIVE() throws SQLException {
		if(stmt_SCROLL_INSENSITIVE==null)stmt_SCROLL_INSENSITIVE = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		return stmt_SCROLL_INSENSITIVE;
	}
	
	@Override
	public void batchImport(String[] sql) throws Exception {
		boolean isAotu = con.getAutoCommit();
		Exception err=null;
		try {
			int count = (sql.length / 10000) + (sql.length % 10000 == 0 ? 0 : 1);
			con.setAutoCommit(false);
			for (int i = 0; i < count - 1; i++) {
				int width = i * 10000;
				for (int k = 0; k < 10000; k++) {
					stmt.addBatch(sql[width + k]);
				}
				int al[] = stmt.executeBatch();
				stmt.clearBatch();
			}
			if (count != 0) {
				int tempCount = (count - 1) * 10000;
				int tempLength = sql.length;
				for (int k = tempCount; k < tempLength; k++) {
					stmt.addBatch(sql[k]);
				}
				int al[] = stmt.executeBatch();
			}
			con.commit();
			stmt.clearBatch();
		} catch (Exception e) {
			err=e;
		}finally{
			con.rollback();
			con.commit();
			con.setAutoCommit(isAotu);
		}
		if(err!=null){throw err;}
	}

	@Override
	public void batchImport(Collection sqls) throws Exception {
		String sql[] = new String[sqls.size()];
		sqls.toArray(sql);
		batchImport(sql);
	}

	
	@Override
	public void batchInsert(String table,List<Map<String,Object>> data) throws SQLException {
		String sql=preparedSQL.get(table+"_insert");
		List<Map<String, Object>>  structure=getTableStructures(table);
		List<Object> fields=ListHelper.retrive(structure, "field");
		fields.removeAll(ListHelper.retrive(ListHelper.find(structure, "identity", "1"), "field"));
		if(sql==null||"".equals(sql)){
			List<String> wen=new ArrayList<String>(fields.size());
			for (Object string : fields) {wen.add("?");}
			sql="insert into "+table+" ("+StringUtils.join(fields, ",")+") values("+StringUtils.join(wen, ",")+")";
			preparedSQL.put(table+"_insert", sql);
		}
		PreparedStatement pstmt=preparedStatement.get(table+"_insert");
		if(pstmt==null){
			pstmt=con.prepareStatement(sql);
			preparedStatement.put(table+"_insert", pstmt);
		}
		boolean isAotu = con.getAutoCommit();
		if(isAotu)con.setAutoCommit(false);
		for (Map<String, Object> map : data) {
			for(int i=0;i<fields.size();i++){
				pstmt.setObject((i+1), map.get(fields.get(i)));
			}
			pstmt.addBatch();
		}
		pstmt.executeBatch();
		con.commit();
		pstmt.clearBatch();
		if(isAotu)con.setAutoCommit(isAotu);
	}
	
	
	@Override
	public int Delete(String sql) throws SQLException {
		return this.Excute(sql);
	}

	@Override
	public int Excute(String com) throws SQLException {
		return this.stmt.executeUpdate(com);
	}

	@Override
	public List<String> getDatabaseNames() throws SQLException {
		String sql="select name from sysdatabases;";
		List<Map<String,Object>> data=this.selects(sql);
		List<String> dbs=new ArrayList<String>();
		for(Map<String,Object> m:data){dbs.add(m.get("name").toString());}
		return dbs;
	}

	@Override
	public long getRecordsCount() throws SQLException {
		List<String> dbs = this.getTableNames();
		long count = 0;
		for (String s : dbs) {
			count += this.getRecordsCount(s);
		}
		return count;
	}

	@Override
	public long getRecordsCount(String tableName) throws SQLException {
		String sql = "select count(1)  \"count\" from " + tableName + ";";
		List<Map<String, Object>> data = this.selects(sql);
		if (data.isEmpty()) {
			return 0;
		}
		return Long.parseLong(data.get(0).get("count").toString());
	}

	@Override
	public synchronized List<String> getTableNames() throws SQLException {
		String sql="select name from sysobjects where xtype='U'";
		List<Map<String,Object>> data=this.selects(sql);
		List<String> tables=new ArrayList<String>();
		if(!data.isEmpty()){
			String key=data.get(0).keySet().toArray()[0].toString();
			for(Map<String,Object> m:data){
				tables.add(m.get(key).toString().toLowerCase());
			}
		}
		return tables;
	}

	@Override
	public synchronized List<String> getTableNames(String dabaseName) throws SQLException {
		HSQL sql = new HSqlserver(this.dataBaseUrl, this.dataBasePort,	dabaseName, this.username, this.password);
		sql.connect();
		List<String> data = sql.getTableNames();
		sql.close();
		return data;
	}

	@Override
	public synchronized List<Map<String, Object>> getTableStructures() throws SQLException {
		synchronized (tableStructureLoc) {
			if(tableStructure==null||tableStructure.isEmpty()){
				if(tableStructure==null){tableStructure=new HashSet<Map<String,Object>>();}
				String s="SELECT d.name N'tablename', a.colorder N'fieldindex', a.name N'field', ( CASE WHEN COLUMNPROPERTY(a.id, a.name, 'IsIdentity') = 1 THEN '1' ELSE '0' END ) N'identity', ( CASE WHEN ( SELECT COUNT (*) FROM sysobjects WHERE ( name IN ( SELECT name FROM sysindexes WHERE (id = a.id) AND ( indid IN ( SELECT indid FROM sysindexkeys WHERE (id = a.id) AND ( colid IN ( SELECT colid FROM syscolumns WHERE (id = a.id) AND (name = a.name))))))) AND (xtype = 'PK')) > 0 THEN '1' ELSE '0' END ) N'pk', b.name N'type', COLUMNPROPERTY(a.id, a.name, 'PRECISION') AS N'length', ( CASE WHEN a.isnullable = 1 THEN '1' ELSE '0' END ) N'nullable', isnull(e. TEXT, '') N'default' FROM syscolumns a LEFT JOIN systypes b ON a.xtype = b.xusertype INNER JOIN sysobjects d ON a.id = d.id AND d.xtype = 'U' AND d.name <> 'dtproperties' LEFT JOIN syscomments e ON a.cdefault = e.id  ORDER BY object_name(a.id), a.colorder";
				List<Map<String, Object>> temp= HSqlserver.convertResultSetToList(this.select(s));
				for (Map<String, Object> map : temp) {
					Map<String,Object> m=new HashMap<String, Object>();
					Object keys[]=map.keySet().toArray();
					for (Object object : keys) {
						Object value=map.get(object);
						m.put(object.toString().toLowerCase(),value==null?"":value.toString().toLowerCase());
					}
					tableStructure.add(m);
				}
			}
			List<Map<String, Object>> list=new ArrayList<Map<String, Object>>();
			list.addAll(tableStructure);
			return list;
		}
	}

	@Override
	public synchronized List<Map<String, Object>> getTableStructures(String tableName)throws SQLException {
		synchronized (tableStructureLoc) {
			if(tableStructure==null||tableStructure.isEmpty()||!tableStructure.contains(tableName)){
				getTableStructures();
			}
			List<Map<String, Object>> list= ListHelper.find(getTableStructures(), "tablename", tableName.toLowerCase());
			return list;
		}
	}

	
	public synchronized void reloadTableStructure() throws SQLException{
		synchronized (tableStructureLoc) {
			tableStructure=null;
			getTableStructures();
		}
	}
	
	
	@Override
	public String Insert(String sql) throws SQLException {
		this.stmt.executeUpdate(sql);
		return sql;
	}

	@Override
	public ResultSet select(String sql) throws SQLException {
		return this.stmt.executeQuery(sql);
	}

	@Override
	public List<Map<String, Object>> selects(String sql) throws SQLException {
		return HSqlserver.convertResultSetToList(this.select(sql));
	}

	@Override
	public int update(String sql) throws SQLException {
		return this.stmt.executeUpdate(sql);
	}

	@Override
	public void clear(String table) throws SQLException {
		String sql = "delete from " + table ;
		this.stmt.executeUpdate(sql);
	}

	@Override
	public int copyTableStructure(String sourceTable, String targetTable)throws SQLException {
		String sql = "select * into " + targetTable + " from " + sourceTable+ " limit 0";
		return this.Excute(sql);
	}

	@Override
	public void copy(String sourceTable, String targetTable)throws SQLException {
		String sql = "select * into " + targetTable + " from " + sourceTable;
		this.Excute(sql);
	}

	@Override
	public void move(String sourceTable, String targetTable)throws SQLException {
		this.copy(sourceTable, targetTable);
		this.clear(sourceTable);
	}

	public static List<Map<String, Object>> convertResultSetToList(ResultSet set) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.sss");
		try {
			ResultSetMetaData rmtemp = set.getMetaData();
			int columnCount = rmtemp.getColumnCount();
			while (set.next()) {
				Map<String, Object> map = new HashMap<String, Object>();
				for (int i = 1; i <= columnCount; i++) {
					Object o = set.getObject(i);
					if (o != null && o instanceof Timestamp) {
						o = format.format(o);
					}
					map.put(rmtemp.getColumnLabel(i).toLowerCase(), o);
				}
				list.add(map);
			}
			set.close();
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public long getRecordsCount(String tableName, String condition)throws SQLException {
		if (null == tableName || null == condition) {
			return 0;
		}
		boolean bool = condition.trim().toLowerCase().startsWith("where");
		String sql = "select count(1)  \"count\" from " + tableName + " "
				+ (bool ? condition : (" where " + condition)) + ";";
		List<Map<String, Object>> data = this.selects(sql);
		return Long.parseLong(data.get(0).get("count").toString());
	}

	
	
	

	//����ɾ�����
	@Override
	public List<String> createDeleteSQL(String tableName,List<Map<String,Object>> data) throws SQLException{
		List<Map<String,Object>> structure=this.getTableStructures(tableName);
		List<Map<String,Object>> pkl=ListHelper.find(structure, "pk", "1");
		List<Object> pk=new ArrayList<Object>();
		if(!pkl.isEmpty()){pk=ListHelper.retrive(pkl, "field");}
		else{
			List<Object> identity=ListHelper.retrive(ListHelper.find(structure, "identity", "1"), "field");
			pk=ListHelper.retrive(structure, "field");
			pk.removeAll(identity);
		}
		//----------------------------------------------------------------------
		List<String> list=new ArrayList<String>();
		Object fields[]=pk.toArray();
		String temp[]=new String[fields.length];
		for (int i=0;i<fields.length;i++) {
			temp[i]=fields[i].toString().toString()+" #"+fields[i].toString().toString()+"#";
		}
		String sql="delete from "+tableName+" where "+StringUtils.join(temp," and ");
		for (Map<String,Object> m : data) {
			if(m.isEmpty()){continue;}
			String tsql=sql;
			for (Object string : fields) {
				Object d=m.get(string.toString().toLowerCase().trim());
				if(d==null){tsql=tsql.replace("#"+string+"#", " is null ");}
				else{tsql=tsql.replace("#"+string+"#","='"+d.toString().replace("'", "''")+"'");}
			}
			list.add(tsql);
		}
		return list;
	}
	
	//�����������
	@Override
	public List<String> createInsertSQL(String tableName,List<Map<String,Object>> data) throws SQLException{
		List<String> list=new ArrayList<String>();
		if(data==null||data.isEmpty()) {return list;}
		// ��������������������������������������������������������������������������������������
		List<Map<String,Object>> structure=this.getTableStructures(tableName);
		List<Object> fields=new ArrayList<Object>();
		List<Object> identity=ListHelper.retrive(ListHelper.find(structure, "identity", "1"), "field");
		fields=ListHelper.retrive(structure, "field");
		fields.removeAll(identity);
		// ��������������������������������������������������������������������������������������
		List<String> key=new ArrayList<String>();
		List<String> value=new ArrayList<String>();
		for (Map<String,Object>  m : data) {
			if(m.isEmpty()){continue;}
			key.clear();
			value.clear();
			for (Object string : fields) {
				if(m.get(string.toString().toLowerCase())==null) {continue;}
				key.add(string.toString().toLowerCase());
				value.add(m.get(string.toString().toLowerCase()).toString().replace("'", "''"));
			}
			list.add("insert into "+tableName+" ("+StringUtils.join(key,",")+") values('"+StringUtils.join(value,"','")+"') ");
		}
		return list;
	}
	
	//�����������
	@Override
	public List<String> createUpdateSQL(String tableName,List<Map<String,Object>> data) throws SQLException{
		List<String> list=new ArrayList<String>();
		List<Map<String,Object>> structure=this.getTableStructures(tableName);
		List<Map<String,Object>> pkl=ListHelper.find(structure, "pk", "1");
		List<Object> identity=ListHelper.retrive(ListHelper.find(structure, "identity", "1"), "field");
		List<Object> pk=new ArrayList<Object>();
		if(!pkl.isEmpty()){pk=ListHelper.retrive(pkl, "field");}
		else{
			pk=ListHelper.retrive(structure, "field");
			pk.removeAll(identity);
		}
		List<Object> fields=ListHelper.retrive(structure, "field");
		fields.removeAll(identity);
		fields.removeAll(pk);
		// ��������������������������������������������������������������������������������������
		List<String> set=new ArrayList<String>();
		List<String> condition=new ArrayList<String>();
		for (Map<String,Object>  m : data) {
			if(m.isEmpty()){continue;}
			set.clear();
			condition.clear();
			for (Object string : fields) {
				if(m.get(string.toString().toLowerCase())==null) {set.add(string.toString().toLowerCase()+" = null ");}
				else set.add(string.toString().toLowerCase()+" ='"+m.get(string.toString().toLowerCase()).toString().replace("'", "''")+"'");
			}
			for (Object string : pk) {
				if(m.get(string.toString().toLowerCase())==null) {condition.add(string.toString().toLowerCase()+" is null ");}
				else condition.add(string.toString().toLowerCase()+" ='"+m.get(string.toString().toLowerCase()).toString().replace("'", "''")+"'");
			}
			if(!set.isEmpty())list.add("update "+tableName+" set "+StringUtils.join(set,",")+" where "+StringUtils.join(condition," and "));
		}
		return list;
	}
	
	
	//�����������
	@Override
	public List<String> createUpdateSQL(String tableName,List<Map<String,Object>> data,List<Map<String,Object>> target) throws SQLException{
		List<String> list=new ArrayList<String>();
		if(data==null||data.isEmpty()){return list;}
		//-----------------------------------------
		List<Map<String,Object>> structure=this.getTableStructures(tableName);
		List<Map<String,Object>> pkl=ListHelper.find(structure, "pk", "1");
		List<Object> identity=ListHelper.retrive(ListHelper.find(structure, "identity", "1"), "field");
		List<Object> pk=new ArrayList<Object>();
		if(!pkl.isEmpty()){pk=ListHelper.retrive(pkl, "field");}
		else{
			pk=ListHelper.retrive(structure, "field");
			pk.removeAll(identity);
		}
		//-----------------------------------------
		LinkedHashMap<String,Map<String,Object>> srcmap=new LinkedHashMap<String, Map<String,Object>>(); 
		LinkedHashMap<String,Map<String,Object>> targetmap=new LinkedHashMap<String, Map<String,Object>>();
		StringBuilder bu=new StringBuilder();
		for (Map<String, Object> map:data) {
			bu.setLength(0);
			for (Object object : pk) {
				bu.append(map.get(object.toString()));
			}
			srcmap.put(bu.toString(), map);
		}
		for (Map<String, Object> map:target) {
			bu.setLength(0);
			for (Object object : pk) {
				bu.append(map.get(object.toString()));
			}
			targetmap.put(bu.toString(), map);
		}
		bu.setLength(0);
		Object keys[]=data.get(0).keySet().toArray();
		Object ids[]=srcmap.keySet().toArray();
		for (Object id : ids) {
			Map<String,Object> sm=srcmap.get(id);
			Map<String,Object> tm=targetmap.get(id);
			if(sm==null){continue;}
			if(sm!=null&&sm.equals(tm)){data.remove(sm);}
			for (Object key : keys) {
				if(pk.contains(key)){continue;}
				if(sm.get(key)==null){
					if(tm.get(key)==null){sm.remove(key);}
					continue;
				}
				if(tm.get(key)==null&&sm.get(key)!=null){continue;}
				if(sm.get(key)!=null&&tm.get(key)!=null&&!sm.get(key).toString().equals(tm.get(key).toString())){continue;}
				sm.remove(key);
			}
		}
		//------------------------------------------------------------------
		List<Object> fields=ListHelper.retrive(structure, "field");
		fields.removeAll(identity);
		fields.removeAll(pk);
		// ��������������������������������������������������������������������������������������
		List<String> set=new ArrayList<String>();
		List<String> condition=new ArrayList<String>();
		for (Map<String,Object>  m : data) {
			if(m.isEmpty()){continue;}
			set.clear();
			condition.clear();
			for (Object string : fields) {
				if(!m.containsKey(string)){continue;}
				if(m.get(string.toString().toLowerCase())==null) {set.add(string.toString().toLowerCase()+" = null ");}
				else set.add(string.toString().toLowerCase()+" ='"+m.get(string.toString().toLowerCase()).toString().replace("'", "''")+"'");
			}
			for (Object string : pk) {
				if(m.get(string.toString().toLowerCase())==null) {condition.add(string.toString().toLowerCase()+" is null ");}
				else condition.add(string.toString().toLowerCase()+" ='"+m.get(string.toString().toLowerCase()).toString().replace("'", "''")+"'");
			}
			if(!set.isEmpty())list.add("update "+tableName+" set "+StringUtils.join(set,",")+" where "+StringUtils.join(condition," and "));
		}
		return list;
	}
	
	
	//�����������
	@Override
	public List<String> createInsertOrUpdateSQL(String tableName,List<Map<String,Object>> data,List<Map<String,Object>> target) throws SQLException{
		List<String> list=new ArrayList<String>();
		if(data==null||data.isEmpty()){return list;}
		List<Map<String,Object>> inserts=new ArrayList<Map<String,Object>>();
		//-----------------------------------------
		List<Map<String,Object>> structure=this.getTableStructures(tableName);
		List<Map<String,Object>> pkl=ListHelper.find(structure, "pk", "1");
		List<Object> identity=ListHelper.retrive(ListHelper.find(structure, "identity", "1"), "field");
		List<Object> pk=new ArrayList<Object>();
		if(!pkl.isEmpty()){pk=ListHelper.retrive(pkl, "field");}
		else{
			pk=ListHelper.retrive(structure, "field");
			pk.removeAll(identity);
		}
		//-----------------------------------------
		LinkedHashMap<String,Map<String,Object>> srcmap=new LinkedHashMap<String, Map<String,Object>>(); 
		LinkedHashMap<String,Map<String,Object>> targetmap=new LinkedHashMap<String, Map<String,Object>>();
		StringBuilder bu=new StringBuilder();
		for (Map<String, Object> map:data) {
			bu.setLength(0);
			for (Object object : pk) {
				bu.append(map.get(object.toString()));
			}
			srcmap.put(bu.toString(), map);
		}
		for (Map<String, Object> map:target) {
			bu.setLength(0);
			for (Object object : pk) {
				bu.append(map.get(object.toString()));
			}
			targetmap.put(bu.toString(), map);
		}
		bu.setLength(0);
		Object keys[]=data.get(0).keySet().toArray();
		Object ids[]=srcmap.keySet().toArray();
		for (Object id : ids) {
			Map<String,Object> sm=srcmap.get(id);
			Map<String,Object> tm=targetmap.get(id);
			if(sm==null){continue;}
			if(sm!=null&&tm==null){inserts.add(sm);data.remove(sm);continue;}
			if(sm!=null&&sm.equals(tm)){data.remove(sm);}
			for (Object key : keys) {
				if(pk.contains(key)){continue;}
				if(sm.get(key)==null){
					if(tm.get(key)==null){sm.remove(key);}
					continue;
				}
				if(tm.get(key)==null&&sm.get(key)!=null){continue;}
				if(sm.get(key)!=null&&tm.get(key)!=null&&!sm.get(key).toString().equals(tm.get(key).toString())){continue;}
				sm.remove(key);
			}
		}
		//------------------------------------------------------------------
		List<Object> fields=ListHelper.retrive(structure, "field");
		fields.removeAll(identity);
		fields.removeAll(pk);
		// ��������������������������������������������������������������������������������������
		if(!inserts.isEmpty()){list.addAll(createInsertSQL(tableName, inserts));}
		List<String> set=new ArrayList<String>();
		List<String> condition=new ArrayList<String>();
		for (Map<String,Object>  m : data) {
			if(m.isEmpty()){continue;}
			set.clear();
			condition.clear();
			for (Object string : fields) {
				if(!m.containsKey(string)){continue;}
				if(m.get(string.toString().toLowerCase())==null) {set.add(string.toString().toLowerCase()+" = null ");}
				else set.add(string.toString().toLowerCase()+" ='"+m.get(string.toString().toLowerCase()).toString().replace("'", "''")+"'");
			}
			for (Object string : pk) {
				if(m.get(string.toString().toLowerCase())==null) {condition.add(string.toString().toLowerCase()+" is null ");}
				else condition.add(string.toString().toLowerCase()+" ='"+m.get(string.toString().toLowerCase()).toString().replace("'", "''")+"'");
			}
			if(!set.isEmpty())list.add("update "+tableName+" set "+StringUtils.join(set,",")+" where "+StringUtils.join(condition," and "));
		}
		return list;
	}	
	
	@Override
	public String createWhereCondition(List<Map<String,Object>> data,Collection key){
		if(key==null||key.isEmpty()){return "";}
		if(key!=null&&key.size()==1){
			String pk=key.iterator().next().toString();
			List<Object> ids=ListHelper.uniques(ListHelper.retrive(data, pk));
			return pk+" in('"+StringUtils.join(ids, "','")+"')";
		}
		List<String> result=new ArrayList<String>();
		List<String> condition=new ArrayList<String>();
		for (Map<String,Object>  m : data) {
			if(m.isEmpty()){continue;}
			condition.clear();
			for (Object string : key) {
				if(m.get(string.toString().toLowerCase())==null) {condition.add(string.toString().toLowerCase()+" is null ");}
				else condition.add(string.toString().toLowerCase()+" ='"+m.get(string.toString().toLowerCase()).toString().replace("'", "''")+"'");
			}
			result.add("("+StringUtils.join(condition, " and ")+")");
		}
		return StringUtils.join(result, " or ");
	}
	
	
	@Override
	public List<Object> getPK(String table) throws SQLException{
		List<Map<String,Object>> structure=this.getTableStructures(table);
		List<Map<String,Object>> pkl=ListHelper.find(structure, "pk", "1");
		List<Object> identity=ListHelper.retrive(ListHelper.find(structure, "identity", "1"), "field");
		List<Object> pk=new ArrayList<Object>();
		pk=ListHelper.retrive(pkl, "field");
		return pk;
	}
	
	
	
	
	public boolean hasIndex(String name) throws SQLException{
		if(StringUtils.isBlank(name)){return false;}
		return this.selects("select * from sys.indexes where name='"+name.toLowerCase()+"'").isEmpty()?false:true;
	}
	
	
	public void createIndex(String tableName,String field,String indexName) throws SQLException{
		this.Excute("CREATE INDEX "+indexName.toLowerCase()+" ON "+tableName+" ("+field+");");
	}
	
	public void createIndex(String tableName,String field) throws SQLException{
		this.Excute("CREATE INDEX "+("index_"+tableName+"_"+field).toLowerCase()+" ON "+tableName+" ("+field+");");
	}
	
	
	@Override
	public HSqlserver clone() {
		HSqlserver sql = new HSqlserver(dataBaseUrl, dataBasePort,
				dataBaseName, username, password);
		return sql;

	}

	@Override
	public boolean equals(Object obj) {
		HSqlserver sql = (HSqlserver) obj;
		if (sql.getDataBaseTag() == null && this.getDataBaseTag() != null) {
			return false;
		}
		if (!sql.getDataBaseTag().equals(dataBaseTag)) {
			return false;
		}

		if (sql.getDataBaseUrl() == null && this.getDataBaseUrl() != null) {
			return false;
		}
		if (!sql.getDataBaseUrl().equals(dataBaseUrl)) {
			return false;
		}

		if (sql.getDataBasePort() == null && this.getDataBasePort() != null) {
			return false;
		}
		if (!sql.getDataBasePort().equals(dataBasePort)) {
			return false;
		}

		if (sql.getDataBaseName() == null && this.getDataBaseName() != null) {
			return false;
		}
		if (!sql.getDataBaseName().equals(dataBaseName)) {
			return false;
		}

		if (sql.getUsername() == null && this.getUsername() != null) {
			return false;
		}
		if (!sql.getUsername().equals(username)) {
			return false;
		}

		if (sql.getPassword() == null && this.getPassword() != null) {
			return false;
		}
		if (!sql.getPassword().equals(password)) {
			return false;
		}
		return true;
	}
	
}
