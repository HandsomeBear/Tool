package HHH.HSQL.retriver;







import HHH.HJSON.HJSON;
import HHH.HZIP.HZIP;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;






public abstract class HSQLRetriver implements ISQLRetriver{
	
	private int  splitSize=10000;
	private  Statement stmt;
	private ResultSet set;
	private List<Map<String,Object>> data=new ArrayList<Map<String,Object>>();
	private List<byte[]> dmp=new ArrayList<byte[]>();
	private SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.sss");
	
	public HSQLRetriver(ResultSet set) {
		this.set=set;
	}
	
	public HSQLRetriver(Connection con,String sql) throws SQLException {
		this.stmt=con.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
		stmt.setFetchSize(10000);
		
		this.set=stmt.executeQuery(sql);
	}
	
	public HSQLRetriver(ResultSet set,int  splitSize) {
		this.set=set;
		this.splitSize=splitSize;
	}
	
	public HSQLRetriver(Connection con,String sql,int  splitSize) throws SQLException {
		this(con,sql);
		this.splitSize=splitSize;
	}
	public void retrive() throws SQLException, IOException,Exception{
		if(!set.next()) {return;}
		start();
		ResultSetMetaData meta=set.getMetaData();
		List<String> title=new ArrayList<String>();
		for (int i=1; i <= meta.getColumnCount(); i++) {
			title.add(meta.getColumnName(i).toLowerCase());
		}
		int count=title.size();
		int index=1;
		Map<String,Object> first=new HashMap<String,Object>();
		for(int i=index;i<=count;i++) {
			Object o=set.getObject(i);
			if(o!=null&&o instanceof Timestamp) {
				o=format.format(o);
			}
			first.put(title.get(i-1),o);
		}
		data.add(first);
		while(set.next()) {
			index=1;
			Map<String,Object> map=new HashMap<String,Object>();
			for(int i=index;i<=count;i++) {
				Object o=set.getObject(i);
				if(o!=null&&o instanceof Timestamp) {
					o=format.format(o);
				}
				map.put(title.get(i-1),o);
			}
			data.add(map);
			if(data.size()>=splitSize) {
				split(data);
			}
		}
		set.close();
		if(stmt!=null) {stmt.close();stmt=null;}
		if(!data.isEmpty()) {split(data);}
		finish(dmp);
		data.clear();
		dmp.clear();
	}
		
	
	
	private void split(List<Map<String,Object>> dd) throws Exception {
		final List<Map<String,Object>> temp=new ArrayList<Map<String,Object>>();
		temp.addAll(dd);
		dd.clear();
		boolean b=aftersplit(temp);
		System.gc();
		if(b)comporess(temp);
	}
	
	private void comporess(List<Map<String,Object>> dd) throws Exception {
		String s=HJSON.toJSON(dd);
		byte compress[]=s.getBytes("ISO-8859-1");
		boolean a=beforecompress(compress);
		if(!a) {return;}
		compress=HZIP.bzip2(compress);
		boolean b=aftercompress(compress);
		if(!b) {return;}
		dmp.add(compress);
		dd.clear();
		dd=null;
		System.gc();
	}
	
	
	@Override
	public void start() {
		return ;
	}
	
	@Override
	public boolean aftersplit(List<Map<String,Object>> data) throws Exception {
		return true;
	}
	
	
	@Override
	public boolean beforecompress(byte[] data) {
		return true;
	}

	
	@Override
	public boolean aftercompress(byte[] data) {
		return true;
	}
	
	
	@Override
	public void finish(List<byte[]> dmp) {
		return ;
	}
	

}
