
package HHH.HREST;


import javax.servlet.MultipartConfigElement;

import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.SessionManager;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.nio.SelectChannelConnector;
import org.eclipse.jetty.server.session.HashSessionManager;
import org.eclipse.jetty.server.session.SessionHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;



/**
 * <P>类型注释：</p>
 *█<p>———————————————————————————————————————————</p> 
 *█<p>作者：何明辉</p>
 *█<p>时间：2016年3月13日-下午2:01:43</p>
 *█<p>类型：HServer</p>
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
 *█<p>  HServer server=new HServer(81);</p>
 *█<p>  server.start();</p>
 *█<p>———————————————————————————————————————————</p>
 */
public class HServer {
	
	private int port=8080;
	private Server server;
	
	
	public HServer(int port) {
		this.port=port;
		server = new Server(this.port);
		HandlerList handlers = new HandlerList();
		server.setHandler(handlers);
		
		SessionManager sm = new HashSessionManager();
		SessionHandler sh=new SessionHandler(sm);
		ServletContextHandler hand = new ServletContextHandler();
		hand.setSessionHandler(sh);
		sm.setMaxInactiveInterval(3600);
		
		ServletHolder holder=new ServletHolder(new HServlet());
		holder.getRegistration().setMultipartConfig(new MultipartConfigElement("tempx/", 1024*1024*1000*10, 1024*1024*1000*10, 1024*1024*1000));
		hand.addServlet(holder,"/*");
		handlers.addHandler(hand);
		
		SelectChannelConnector connector = new SelectChannelConnector();
        connector.setPort(this.port);
        connector.setAcceptQueueSize(1000);
        connector.setName("main");
        connector.setMaxIdleTime(60000);
        server.setConnectors(new Connector[] {connector});
        
        
//        ResourceHandler resource_handler = new ResourceHandler();
//        resource_handler.setDirectoriesListed(true);
//        resource_handler.setWelcomeFiles(new String[]{ "index.html" });
//        resource_handler.setResourceBase(".");
	}
	
	
	
	public void start() throws Exception {
		HServlet.scan();
		server.start();
		HDAO.scanXML();
	}
	
	
	public void stop() throws Exception {
		server.stop();
	}
	
	
	
	public void destroy() {
		server.destroy();
	}
}
