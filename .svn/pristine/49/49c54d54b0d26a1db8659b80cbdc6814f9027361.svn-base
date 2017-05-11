



/**<P>文件注释：HAnnotationUtil.java</p>
 *█<p>———————————————————————————————————————————</p> 
 *█<p>作者：何明辉</p>
 *█<p>时间：2015年11月21日-下午12:30:22</p>
 *█<p>类型：文件</p>
 *█<p>用途：该文件用于</p>
 *█<p>备注：***</p> 
 *█<p>抛出异常：[]</p>
 *█<p>触发事件：[]</p>
 *█<p>示例：</p>
 *█<p>———————————————————————————————————————————</p>
 *█  
 *█<p>———————————————————————————————————————————</p>
 */
package HHH.HAnnotations;

import HHH.HUTIL.ListHelper;

import java.io.File;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;


/**<P>类型注释：</p>
 *█<p>———————————————————————————————————————————</p> 
 *█<p>作者：何明辉</p>
 *█<p>时间：2015年11月21日-下午12:30:22</p>
 *█<p>类型：HAnnotationUtil</p>
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
 *█   
 *█<p>———————————————————————————————————————————</p>
 */
public class HAnnotationUtil {
	
	
	public static Set<Class> ALLClassz=new HashSet<Class>();
	
	
	public static void init() throws ClassNotFoundException, IOException {
		List<String> cls=getClasses();
		for (String string : cls) {
				if(string.startsWith("java")||string.startsWith("sun")||string.startsWith("org")) {continue;}
				ALLClassz.add(Class.forName(string.replace("/", ".").replace("\\", ".")));
		}
	}
	
	
	public static void init(String packagex) throws ClassNotFoundException, IOException {
		List<String> cls=getClasses(packagex);
		for (String string : cls) {
				if(string.startsWith("java")||string.startsWith("sun")||string.startsWith("org")) {continue;}
				ALLClassz.add(Class.forName(string.replace("/", ".").replace("\\", ".")));
		}
	}
	
	
	
	public static List<String> getPacks(){
		List<String> classz=new ArrayList<String>();
		Package pacs[]=Package.getPackages();
		for (Package p : pacs) {
			String name=p.getName();
			if(name.startsWith("java")||name.startsWith("sun")||name.startsWith("org")) {continue;}
			classz.add(name);
		}
		return classz;
	}
	
	public static List<String> getClasses() throws IOException{
		List<String> pack=getPacks();
		List<String> classz=new ArrayList<String>();
		for (String p : pack) {
			classz.addAll(getClasses(p));
		}
		classz.addAll(selfDetection());
		classz=ListHelper.unique(classz);
		return classz;
	}
	
	
	public static List<String> getClasses(String packagex) throws IOException{
		Enumeration<URL> urls=ClassLoader.getSystemResources(packagex);
		List<String> root=new ArrayList<String>();
		List<String> classz=new ArrayList<String>();
		//———————————————————————————————————————————
		for (;urls.hasMoreElements();) {
			URL url = urls.nextElement();  
            String protocol = url.getProtocol();  
            if ("file".equals(protocol)) {  
                String filePath = URLDecoder.decode(url.getFile(), "UTF-8");
                root.add(filePath);
            }
            else if("jar".equals(protocol)) {
            	classz.addAll(getClassNameByJar(url.getPath(),true));
            }
		}
		//———————————————————————————————————————————
		for (String subroot : root) {
			File subrootFile=new File(subroot);
			String subrootFilePath=subrootFile.getCanonicalPath();
			List<File> list=traverseFolder(subrootFilePath);
			for (File file : list) {
				if(file.isDirectory()) {continue;}
				if(!file.getName().endsWith(".class")) {continue;}
				String s=file.getCanonicalPath().toString().replace(subrootFilePath, "").replace("\\", ".").replace(".class", "");
				if(s.contains("$")) {continue;}
				if(s.startsWith(".")) {s=s.substring(1);}
				if(s.startsWith("java")||s.startsWith("sun")||s.startsWith("org")) {continue;}
				classz.add(packagex+"."+s);
			}
		}
		classz.addAll(selfDetection(packagex));
		classz=ListHelper.unique(classz);
		Collections.sort(classz, new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				return o1.compareTo(o2);
			}});
		return classz;
	}
	
		//用于Eclipse中自省
	public static List<String> selfDetection() throws IOException{
		List<String> classz=new ArrayList<String>();
		File subrootFile=new File("./bin");
		String subrootFilePath=subrootFile.getCanonicalPath();
		List<File> list=traverseFolder(subrootFilePath);
		for (File file : list) {
			if(file.isDirectory()) {continue;}
			if(!file.getName().endsWith(".class")) {continue;}
			String s=file.getCanonicalPath().toString().replace(subrootFilePath, "").replace("/", ".").replace("\\", ".").replace(".class", "");
			if(s.contains("$")) {continue;}
			if(s.startsWith(".")) {s=s.substring(1);}
			if(s.startsWith("java")||s.startsWith("sun")||s.startsWith("org")) {continue;}
			classz.add(s);
		}
		
		try {
			File f=new File(HAnnotationUtil.class.getClassLoader().getResource("").toURI());
			subrootFilePath=f.getCanonicalPath();
			list.clear();
			list.addAll(traverseFolder(f.getCanonicalPath()));
			for (File file : list) {
				if(file.isDirectory()) {continue;}
				if(!file.getName().endsWith(".class")) {continue;}
				String s=file.getCanonicalPath().toString().replace(subrootFilePath, "").replace("/", ".").replace("\\", ".").replace(".class", "");
				if(s.contains("$")) {continue;}
				if(s.startsWith(".")) {s=s.substring(1);}
				if(s.startsWith("java")||s.startsWith("sun")||s.startsWith("org")) {continue;}
				classz.add(s);
			}
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		return classz;
	}
	
	
	//用于Eclipse中自省
	public static List<String> selfDetection(String pack) throws IOException{
		List<String> classz=new ArrayList<String>();
		File subrootFile=new File("./bin");
		String subrootFilePath=subrootFile.getCanonicalPath();
		List<File> list=traverseFolder(subrootFilePath);
		for (File file : list) {
			if(file.isDirectory()) {continue;}
			if(!file.getName().endsWith(".class")) {continue;}
			String s=file.getCanonicalPath().toString().replace(subrootFilePath, "").replace("/", ".").replace("\\", ".").replace(".class", "");
			if(s.contains("$")) {continue;}
			if(s.startsWith(".")) {s=s.substring(1);}
			if(s.startsWith("java")||s.startsWith("sun")||s.startsWith("org")) {continue;}
			classz.add(s);
		}
		
		try {
			File f=new File(HAnnotationUtil.class.getClassLoader().getResource("").toURI());
			f=new File(f.getAbsolutePath()+"/"+pack.replace(".", "/"));
			subrootFilePath=f.getCanonicalPath();
			list.clear();
			list.addAll(traverseFolder(f.getCanonicalPath()));
			for (File file : list) {
				if(file.isDirectory()) {continue;}
				if(!file.getName().endsWith(".class")) {continue;}
				String s=pack+file.getCanonicalPath().toString().replace(subrootFilePath, "").replace("/", ".").replace("\\", ".").replace(".class", "");
				if(s.contains("$")) {continue;}
				if(s.startsWith(".")) {s=s.substring(1);}
				if(s.startsWith("java")||s.startsWith("sun")||s.startsWith("org")) {continue;}
				classz.add(s);
			}
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		return classz;
	}
	
	
	
	
	
	public static  List<File> traverseFolder(String path) {
        File file = new File(path);
        List<File> result=new ArrayList<File>();
        if (file.exists()) {
            LinkedList<File> list = new LinkedList<File>();
            File[] files = file.listFiles();
            for (File file2 : files) {
                if (file2.isDirectory()) {
                	list.add(file2);
                }else {
                	result.add(file2);
                }
            }
            File temp_file;
            while (!list.isEmpty()) {
                temp_file = list.removeFirst();
                files = temp_file.listFiles();
                for (File file2 : files) {
                    if (file2.isDirectory()) {
                        list.add(file2);
                    } else {
                    	result.add(file2);
                    }
                }
            }
        }
        return result;
    }
	
	
	
	public static List<String> getClassNameByJar(String jarPath, boolean childPackage) {  
        List<String> myClassName = new ArrayList<String>();  
        String[] jarInfo = jarPath.split("!");  
        String jarFilePath = jarInfo[0];
        if(jarFilePath.contains("/"))jarFilePath=jarFilePath.substring(jarInfo[0].indexOf("/"));  
        if(jarInfo.length>1&&jarInfo[1].length()>1)jarInfo[1].substring(1);  
        JarFile jarFile=null;
        try {  
            jarFile = new JarFile(jarFilePath); 
            Enumeration<JarEntry> entrys = jarFile.entries();  
            while (entrys.hasMoreElements()) {  
                JarEntry jarEntry = entrys.nextElement();  
                if(jarEntry.isDirectory()) {continue;}
                String entryName = jarEntry.getName();  
                if(!entryName.endsWith(".class")) {continue;}
                entryName = entryName.replace("/", ".").replace(".class", "");
                if(!entryName.contains("$"))myClassName.add(entryName);  
            }  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  finally {
        	 if(jarFile!=null) try {
				jarFile.close();
			}
			catch (IOException exception) {
			}
		}
        return myClassName;  
    }  
	
	
	
	
	
	
	public static List<Class> AnnotationType(Class annotationType) throws IOException, ClassNotFoundException{
		if(ALLClassz.isEmpty()) {init();}
		List<Class> d=new ArrayList<Class>();
		for (Class clazz : ALLClassz) {
			Annotation as[]=clazz.getAnnotations();
			for (Annotation annotation : as) {
				if(annotation.annotationType()==annotationType) {d.add(clazz);break;}
			}
		}
		return d;
	}
	
	
	
	
	
	public static List<Class> getHType(String type) throws ClassNotFoundException, IOException {
		if(ALLClassz.isEmpty()) {init();}
		List<Class> d=new ArrayList<Class>();
		for (Class clazz : ALLClassz) {
			Annotation as[]=clazz.getAnnotations();
			for (Annotation annotation : as) {
				if(annotation.annotationType()==HType.class) {
					if(type.equals(((HType)annotation).value())) {
						d.add(clazz);break;
					}
				}
			}
		}
		return d;
	}
}
