package HHH.HSQL;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import HHH.HUTIL.StringUtils;



public interface HSQL {

	public String getDataBasePort();

	public Connection getConnection();

	public String getDataBaseName();

	public String getDataBaseTag();

	public String getDataBaseUrl();

	public Statement getStmt();

	public String getUsername();

	public String getPassword();

	public ResultSet getRs();

	public void setConnection(Connection con);
	
	public void setStmt(Statement st);

	public void setDataBaseName(String dataBaseName);

	public void setDataBasePort(String dataBasePort);

	public void setDataBaseTag(String dataBaseTag);

	public void setDataBaseUrl(String dataBaseUrl);

	public void setPassword(String password);

	public void setUsername(String username);

	public void connect();

	public void close();

	public void batchImport(String[] sql) throws SQLException, Exception ;

	public void batchImport(Collection sqls) throws SQLException, Exception;
	
	public void batchInsert(String table,List<Map<String,Object>> data) throws SQLException;

	public int Delete(String sql) throws SQLException ;

	public int Excute(String com) throws SQLException;

	public List<String> getDatabaseNames() throws SQLException ;

	public long getRecordsCount() throws SQLException ;
	
	public long getRecordsCount(String tableName) throws SQLException ;

	public List<String> getTableNames() throws SQLException;

	public List<String> getTableNames(String dabaseName) throws SQLException;

	public List<Map<String, Object>> getTableStructures() throws SQLException ;

	public List<Map<String, Object>> getTableStructures(String tableName)throws SQLException;
	
	public void reloadTableStructure() throws SQLException;

	public String Insert(String sql) throws SQLException ;

	public ResultSet select(String sql) throws SQLException;

	public List<Map<String, Object>> selects(String sql) throws SQLException;

	public int update(String sql) throws SQLException ;

	public void clear(String table) throws SQLException;

	public int copyTableStructure(String sourceTable, String targetTable)throws SQLException;

	public void copy(String sourceTable, String targetTable)throws SQLException ;

	public void move(String sourceTable, String targetTable)throws SQLException ;

	public long getRecordsCount(String tableName, String condition)throws SQLException ;
	
	public List<String> createDeleteSQL(String tableName,List<Map<String,Object>> data) throws SQLException;
	
	public List<String> createInsertSQL(String tableName,List<Map<String,Object>> data) throws SQLException;
	
	public List<String> createUpdateSQL(String tableName,List<Map<String,Object>> data) throws SQLException;
	
	public List<String> createUpdateSQL(String tableName,List<Map<String,Object>> data,List<Map<String,Object>> target) throws SQLException;
	
	public List<String> createInsertOrUpdateSQL(String tableName,List<Map<String,Object>> data,List<Map<String,Object>> target) throws SQLException;
	
	public String createWhereCondition(List<Map<String,Object>> data,Collection key);
	
	public List<Object> getPK(String table) throws SQLException;
	
	public boolean hasIndex(String name) throws SQLException;	
	
	public void createIndex(String tableName,String field,String indexName) throws SQLException;
	
	public void createIndex(String tableName,String field) throws SQLException;
	
	public Statement getStmt_FORWARD_ONLY() throws SQLException;
	
	public Statement getStmt_SCROLL_INSENSITIVE() throws SQLException;
	
	public HSQL clone();

}
