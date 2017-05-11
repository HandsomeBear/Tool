

package HHH.HREST;

import HHH.HAnnotations.HAnnotationUtil;
import HHH.HAnnotations.HWeb.HAutowired;
import HHH.HAnnotations.HWeb.HController;
import HHH.HAnnotations.HWeb.HMethod;
import HHH.HAnnotations.HWeb.HRepository;
import HHH.HUTIL.StringUtils;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@MultipartConfig(location="tempx",maxFileSize=1024*1024*1000*10)
public class HServlet extends HttpServlet{
	
	public static Map<String,Object> HControllers=new HashMap<String,Object>();
	public static Map<String,Object> HRepositorys=new HashMap<String,Object>();
	public static Map<String,Map<String,Method>> mapping=new HashMap<String,Map<String,Method>>();
	

	
	
	public static void scan() throws Exception{
		List<Class> Repositorys = HAnnotationUtil.AnnotationType(HRepository.class);
		for (Class classz : Repositorys) {
			HRepositorys.put(classz.getName(), classz.newInstance());
		}
		//-------------------------------------------------------------------------
		List<Class> classz=HAnnotationUtil.AnnotationType(HController.class);
		for (Class class1 : classz) {
			if(Modifier.isAbstract(class1.getModifiers())){continue;}
			Annotation a=class1.getAnnotation(HController.class);
			String controllerMapping=((HController)a).value();
			if(StringUtils.isNotBlank(controllerMapping)&&controllerMapping.startsWith("/")){controllerMapping=controllerMapping.substring(1);}
			Object instance=class1.newInstance();
			HControllers.put(controllerMapping,instance );
			//-------------------------------------------
			Method[] methods=class1.getDeclaredMethods();
			Map<String,Method> mm=new HashMap<String,Method>();
			for (Method method : methods) {
				HMethod m=method.getAnnotation(HMethod.class);
				if(m==null) {continue;}
				mm.put(m.value(), method);
			}
			mapping.put(controllerMapping, mm);
			//-------------------------------------------
			Field fields[]=class1.getDeclaredFields();
			for (Field field : fields) {
				HAutowired auto=field.getAnnotation(HAutowired.class);
				if(auto==null){continue;}
				field.setAccessible(true);
				Class injecttype=field.getType();
				if(!HRepositorys.containsKey(injecttype.getName())){
					throw new Exception(class1.getName()+"中的自动注入属性"+field.getName()+"不存在");
				}
				field.set(instance,HRepositorys.get(injecttype.getName()));
			}
		}
	}
	
	
	
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException,IOException {
		handle(req,resp);
	}
	
	
	
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException,IOException {
		handle(req,resp);
	}
	
	
	
	private void handle(HttpServletRequest req, HttpServletResponse resp){
		try {
			resp.setHeader("Content-type", "text/html;charset=UTF-8");  
			resp.setCharacterEncoding("UTF-8");  
			HMessage message=new HMessage();
			message.start();
			String addr=req.getRequestURI();
			if(StringUtils.isNotBlank(addr)&&addr.startsWith("/")){addr=addr.substring(1);}
			addr=addr.trim();
			Object instance=HControllers.get(addr);
			if(instance!=null) {
				String act=req.getParameter("act");
				if(act!=null&&!"".equals(act)) {
					Method method=mapping.get(addr).get(act);
					if(method!=null&&!"".equals(method)) {
						try {
							Object returns=method.invoke(instance, req,resp);
							message.success(true);
							if(returns instanceof HMessage){
								message.data(((HMessage) returns).getData());
								message.msg(((HMessage) returns).getMsg());
								message.success(((HMessage) returns).isSuccess());
								((HMessage) returns).data("");
								((HMessage) returns).msg("");
								returns=null;
							}
							else if(returns!=null&&!(returns instanceof Void)){
								message.data(returns);
							}
						}
						catch (Exception exception) {
							exception.printStackTrace();
							message.success(false);
						}
					}else{
						message.msg("sorry,您请求的方法不存在");
						message.success(false);
					}
				}
			}
			else{
				message.msg("sorry,您请求的地址不存在");
				message.success(false);
			}
			message.end();
			if(!"".equals(message.getData())){resp.getWriter().write(message.toString());}
			if(!"".equals(message.getMsg())){resp.getWriter().write(message.toString());}
			message.data("");
			message.msg("");
			message=null;
		} catch (Throwable e) {
			e.printStackTrace();
		}
		
	}
}
