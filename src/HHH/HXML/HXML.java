package HHH.HXML;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

public class HXML {

	public static Document toDocument(String text) throws DocumentException{
		return DocumentHelper.parseText(text);
	}
	
	public static Document toDocument(File file) throws DocumentException, IOException{
		SAXReader reader = new SAXReader();
        Document document = reader.read(file);
		return document;
	}
	
	
	public static String toString(Document doc){
		return doc.asXML();
	}
	
	
	public static void toFile(Document doc,File target) throws IOException{
		OutputFormat format = OutputFormat.createPrettyPrint();
		XMLWriter writer = new XMLWriter(new OutputStreamWriter(new FileOutputStream(target), "UTF-8"),format);
        writer.write( doc );
        writer.close();
	}
	
	
	public static Element getChild(Document doc){
		Element it=doc.getRootElement();
		return it;
	}
	
	
	
	public static List<Element> getChildren(Element doc){
		List<Element> it=doc.elements();
		return it;
	}
	
	
	public static List<Element> find(Document doc,String name,String value){
		List<Element> result=new ArrayList<Element>();
		Element root=doc.getRootElement();
		if(value==null){return null;}
		if(value.equals(root.attribute(name))){result.add(root);}
		Iterator it=root.elementIterator(name);
		while(it.hasNext()){
			Element e=(Element) it.next();
			if(value.equals(e.attributeValue(name))){result.add(e);}
		}
		return result;
	}
	
}
