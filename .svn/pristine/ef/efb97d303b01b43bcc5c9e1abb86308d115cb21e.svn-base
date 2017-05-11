package HHH.HSQL.Synchor;

import java.sql.ResultSet;
import java.util.List;
import java.util.Map;

import HHH.HBEAN.DMP;
import HHH.HSQL.HSQL;
import HHH.HSQL.retriver.HSQLRetriver;
import HHH.HTASK.HLinkedTask;
import HHH.HTASK.HLinkedTaskQueue;
import HHH.HUTIL.ListHelper;
import HHH.HUTIL.StringUtils;

public class HTableSynchor {
	private HSQL srcHSQL=null;
	private HSQL targetHSQL=null;
	private String tablename="";
	private boolean exactlySynchor=false;
	private long totalProcessed=0;
	private long start=0;
	private long end=0;
	
	private HLinkedTask query=new HLinkedTask() {
		@Override
		public void doAction() throws Exception {
			ResultSet set=srcHSQL.select("select * from "+tablename);
			HSQLRetriver retriver=new HSQLRetriver(set) {
				@Override
				public boolean aftersplit(List<Map<String, Object>> data) throws Exception {
					DMP dmp=new DMP();
					dmp.getData().put("data", data);
					dmp.compress();
					push(dmp);
					if(sizeOfDMPs()>10)pass();
					return false;
				}
				@Override
				public void finish(java.util.List<byte[]> dmp) {
					try {
						pass();
					} catch (InterruptedException e) {
					  destroy();
					}
				};
			};
			retriver.retrive();
			this.destroy();
		}
	};
	
	HLinkedTask compare=new HLinkedTask() {
		@Override
		public void doAction() throws Exception {
			while(true){
				try{
					DMP dmp=this.take();
					if(dmp==null){break;}
					dmp.unCompress();
					List<Map<String, Object>> data=(List<Map<String, Object>>) dmp.getData().get("data");
					long start=System.currentTimeMillis();
					System.out.println("��Դ"+tablename+"��ȡ��"+data.size()+"������������");
					System.out.println("���ڻ�ȡĿ��"+tablename+"����");
					List<Object> pks=srcHSQL.getPK(tablename);
					String where =srcHSQL.createWhereCondition(data, pks);
					List<Map<String, Object>> target=targetHSQL.selects("select * from "+tablename+" where "+where );
					System.out.println("Ŀ��"+tablename+"������ȡ�ã����ڱȶ�����");
					List<String> SQLS=srcHSQL.createInsertOrUpdateSQL(tablename, data, target);
					System.out.println("�Աȷ�������Ҫ��"+SQLS.size()+"�����ݽ��д���");
					targetHSQL.Excute("ALTER  TABLE  "+tablename+"  DISABLE  TRIGGER  all  ");
					targetHSQL.batchImport(SQLS);
					targetHSQL.Excute("ALTER  TABLE  "+tablename+"  ENABLE  TRIGGER  all  ");
					long end=System.currentTimeMillis();
					System.out.println(SQLS.size()+"����������Ŀ��"+tablename+"�д������,��ʱ"+(end-start)/1000+"��");
					totalProcessed+=SQLS.size();
					System.out.println(tablename+"�ܼƴ���"+totalProcessed+"����¼");
					System.out.println("----------------------------------");
					dmp.getData().clear();
					data.clear();
					pks.clear();
					target.clear();
				}catch(Exception e){
					e.printStackTrace();
				}
			}
			
		}
	};
	
	
	
	
	private HLinkedTask QueryTreeTable=new HLinkedTask() {
		@Override
		public void doAction() throws Exception {
			DMP dmp=this.take();
			final String uid=dmp.getAttributes().get("uid").toString();
			String uidparentid=dmp.getAttributes().get("uidparentid").toString();
			String root=dmp.getAttributes().get("root")==null?"":dmp.getAttributes().get("root").toString();
			try {
				srcHSQL.Excute("drop table HTableSynchor");
			} catch (Exception e) {}
			srcHSQL.Excute("SELECT *,-1 \"depth\"  into HTableSynchor FROM "+tablename);
			srcHSQL.update("update HTableSynchor set depth='0' where "+uidparentid+" not in(select "+uid+" from HTableSynchor) and "+uidparentid+" is not null and "+uidparentid+"!=''");
			if(root==null){srcHSQL.update("update HTableSynchor set depth='0' where "+uidparentid+" is null");}
			else{srcHSQL.update("update HTableSynchor set depth='0' where "+uid+"='"+root+"'");}
			int count=1;
			for(int i=0;i<100;i++){
				srcHSQL.update("update HTableSynchor set depth='"+count+"' where "+uidparentid+" in(select "+uid+" from HTableSynchor where depth='"+(count-1)+"')");
				count++;
				if(srcHSQL.getRecordsCount("HTableSynchor", "depth=-1")<=0){break;}
			}
			ResultSet set=srcHSQL.select("select * from HTableSynchor where depth>=0 order by depth asc");
			HSQLRetriver retriver=new HSQLRetriver(set) {
				@Override
				public boolean aftersplit(List<Map<String, Object>> data) throws Exception {
					DMP dmp=new DMP();
					for (Map<String, Object> map : data) {
						map.remove("depth");
					}
					List<Map<String,Object>> dest=targetHSQL.selects("select * from "+tablename+ " where "+uid+" in ('"+StringUtils.join(ListHelper.retrive(data, uid), "','")+"')");
					targetHSQL.Excute("ALTER  TABLE  "+tablename+"  DISABLE  TRIGGER  all  ");
					targetHSQL.batchImport(targetHSQL.createInsertOrUpdateSQL(tablename, data,dest));
					targetHSQL.Excute("ALTER  TABLE  "+tablename+"  ENABLE  TRIGGER  all  ");
					return false;
				}
			};
			retriver.retrive();
			srcHSQL.Excute("drop table HTableSynchor");
		}
	};
	
	
	private HLinkedTaskQueue queue=new HLinkedTaskQueue(){
		@Override
		public void finish() {
			super.finish();
			end=System.currentTimeMillis();
			System.out.println(tablename+"�������,��������������ܹ���ʱ��"+(end-start)/1000+"��");
			System.gc();
		}};
	
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	public HTableSynchor(HSQL src,HSQL target,String tablename) {
		this.srcHSQL=src;
		this.targetHSQL=target;
		this.tablename=tablename;
		queue.clear();
		if(srcHSQL.getConnection()==null){srcHSQL.connect();}
		if(targetHSQL.getConnection()==null){targetHSQL.connect();}
		queue.addTask(query);
		queue.addTask(compare);
		compare.setMaxCatche(10);
	}

	public HTableSynchor(HSQL target,String tablename,List<DMP> data){
		this.srcHSQL=target;
		this.targetHSQL=target;
		this.tablename=tablename;
		queue.clear();
		if(srcHSQL.getConnection()==null){srcHSQL.connect();}
		if(targetHSQL.getConnection()==null){targetHSQL.connect();}
		queue.addTask(compare);
		compare.setMaxCatche(-1);
		for (DMP dmp : data) {
			compare.push(dmp);
		}
		compare.setExitAfterEmpty(true);
	}
	
	
	public HTableSynchor(HSQL src,HSQL target,String tablename,String uid,String uidparentid,String root) {
		this.srcHSQL=src;
		this.targetHSQL=target;
		this.tablename=tablename;
		queue.clear();
		if(srcHSQL.getConnection()==null){srcHSQL.connect();}
		if(targetHSQL.getConnection()==null){targetHSQL.connect();}
		queue.addTask(QueryTreeTable);
		DMP dmp=new DMP();
		dmp.getAttributes().put("uid", uid);
		dmp.getAttributes().put("uidparentid", uidparentid);
		dmp.getAttributes().put("root", root);
		QueryTreeTable.push(dmp);
	}
	
	
	public void start(){
		totalProcessed=0;
		start=System.currentTimeMillis();
		queue.start();
	}
	
	
	public void stop(){
		queue.stop();
	}
	
	public HSQL getSrcHSQL() {
		return srcHSQL;
	}

	public void setSrcHSQL(HSQL src) {
		this.srcHSQL = src;
	}

	public HSQL getTargetHSQL() {
		return targetHSQL;
	}

	public void setTargetHSQL(HSQL target) {
		this.targetHSQL = target;
	}

	public boolean isExactlySynchor() {
		return exactlySynchor;
	}

	public void setExactlySynchor(boolean exactlySynchor) {
		this.exactlySynchor = exactlySynchor;
	}
	
	
	public void addTask(HLinkedTask task){
		this.queue.addTask(task);
	}
	
	public void setTask(int index,HLinkedTask task){
		this.queue.setTask(index, task);
	}
	
	public void removeTask(HLinkedTask task){
		this.queue.removeTask(task);
	}
	
	public HLinkedTask getTask(int index){
		return queue.getTask(index);
	}
	
}
