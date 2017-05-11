import HHH.HTASK.HTask;
import HHH.HTASK.HTaskPool;


public class test {

	public static void main(String[] args) throws Exception {
//		HLog.setBaselogPath("Log");
//		HLog.setPolicy(new HLogPolicy());
//		HLog.getPolicy().setBakDIR("LogBak");
//		HLog.init();
//		HLog log=HLog.getInstance("127.0.0.1","test");
//		String s=HString.randomString(2000);
//		while(true) {
//			log.info(s);
//		}
		
		
		
		
		HTaskPool pool=new HTaskPool();
		pool.setMaxSize(5);
		
		pool.setOvertime(2);
		
		for (int i = 0; i < 100; i++) {
			pool.submit(new HTask() {
				@Override
				public void doAction() throws InterruptedException {
					System.out.println(System.currentTimeMillis());
				}
			});
		}

//		pool.setMaxSize(20);
		
		pool.shutdown();
		
		System.out.println("shutdown");
	}

}
