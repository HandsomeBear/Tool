package HHH.HUTIL;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.rmi.server.UID;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;
import java.util.StringTokenizer;

public class HString  {

	
	private static String identifySet="0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
	public static boolean isChinese(char c) {
		Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
		if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
				|| ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
				|| ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
				|| ub == Character.UnicodeBlock.GENERAL_PUNCTUATION
				|| ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
				|| ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS) {
			return true;
		}
		return false;
	}
	
	
	public static boolean isAllChinese(String strName) {
		boolean isAllChinese = true;
		char[] ch = strName.toCharArray();
		for (char c : ch) {
			if (isChinese(c) != true) {
				isAllChinese = false;				
			}
		}
		return isAllChinese;
	}
	
	
	public static boolean containsChinese(String strName) {
		boolean containsChinese = false;
		char[] ch = strName.toCharArray();
		for (char c : ch) {
			if (isChinese(c) == true) {
				containsChinese = true;				
			}
		}
		return containsChinese;
	}
	
	
	public static int countChinese(String strName) {
		int countChinese = 0;
		char[] ch = strName.toCharArray();
		for (char c : ch) {
			if (isChinese(c) == true) {
				countChinese +=1;				
			}
		}
		return countChinese;
	}
	
	
	public static boolean isDigit(char c) {
		return Character.isDigit(c);
	}

	
	public static boolean isAllDigit(String str){
		char data[]=str.toCharArray();
		boolean isAllDigit=true;
		for(int i=0;i<data.length;i++){
			if(isDigit(data[i])!=true){isAllDigit=false;}
		}
		return isAllDigit;
	}
	
	 
	public static boolean containsDigit(String str){
		char data[]=str.toCharArray();
		boolean containsDigit=false;
		for(int i=0;i<data.length;i++){
			if(isDigit(data[i])==true){containsDigit=true;}
		}
		return containsDigit;
	}
	 
	public static int countDigit(String str){
		char data[]=str.toCharArray();
		int countDigit=0;
		for(int i=0;i<data.length;i++){
			if(isDigit(data[i])==true){countDigit+=1;}
		}
		return countDigit;
	}
	 
   
	public static boolean isNumeric(String str) {
		int begin = 0;
		boolean once = true;
		if (str == null || str.trim().equals("")) {
			return false;
		}
		str = str.trim();
		if (str.startsWith("+") || str.startsWith("-")) {
			if (str.length() == 1) {
				// "+" "-"
				return false;
			}
			begin = 1;
		}
		for (int i = begin; i < str.length(); i++) {
			if (!Character.isDigit(str.charAt(i))) {
				if (str.charAt(i) == '.' && once) {
					// '.' can only once
					once = false;
				} else {
					return false;
				}
			}
		}
		if (str.length() == (begin + 1) && !once) {
			// "." "+." "-."
			return false;
		}
		return true;
	}
   
    
	public static boolean isInteger(String str) {
		int begin = 0;
		if (str == null || str.trim().equals("")) {
			return false;
		}
		str = str.trim();
		if (str.startsWith("+") || str.startsWith("-")) {
			if (str.length() == 1) {
				// "+" "-"
				return false;
			}
			begin = 1;
		}
		for (int i = begin; i < str.length(); i++) {
			if (!Character.isDigit(str.charAt(i))) {
				return false;
			}
		}
		return true;
	}

   
	public static boolean isEnglish(char c) {
		boolean isEnglish=true;		
			if (!(c>= 'A' && c<= 'Z')
					&& !(c>= 'a' && c<= 'z')) {
				isEnglish= false;
			}
		
		return isEnglish;
	}
    
	
	public static boolean isAllEnglish(String s) {
		boolean isAllEnglish=true;
		for (int i = 0; i < s.length(); i++) {
			if (!(s.charAt(i) >= 'A' && s.charAt(i) <= 'Z')
					&& !(s.charAt(i) >= 'a' && s.charAt(i) <= 'z')) {
				isAllEnglish= false;
			}
		}
		return isAllEnglish;
	}


	public static boolean containsEnglish(String s) {
		boolean containsEnglish=false;
		char data[]=s.toCharArray();
		for (int i = 0; i < s.length(); i++) {
			if (isEnglish(data[i])) {
				containsEnglish= true;
			}
		}
		return containsEnglish;
	}


	public static int countEnglish(String s) {
		int countEnglish=0;
		char data[]=s.toCharArray();
		for (int i = 0; i <data.length; i++) {
			if (isEnglish(data[i])) {
				countEnglish+=1;
			}
		}
		return countEnglish;
	}
	


	public static boolean isSpace(char c){
		return (c==' ')?true:false;
	}


	public static boolean isAllSpace(String str){
		boolean isAllSpace=true;
		char data[]=str.toCharArray();
		for(char a:data){if(isSpace(a)==false){isAllSpace=false;}}
		return isAllSpace;
	}


	public static boolean containsSpace(String str){
		boolean containsSpace=false;
		char data[]=str.toCharArray();
		for(char a:data){if(isSpace(a)==true){containsSpace=true;}}
		return containsSpace;		
	}


	public static int countSpace(String s) {
		int countSpace=0;
		char data[]=s.toCharArray();
		for (int i = 0; i <data.length; i++) {
			if (isEnglish(data[i])) {
				countSpace+=1;
			}
		}
		return countSpace;
	}


	public static boolean isAllChar(char c,String str){
		boolean isAllChar=true;
		char data[]=str.toCharArray();
		for(char a:data){if(a!=c){isAllChar=false;}}
		return isAllChar;		
	}
	


	public static int countChar(char c,String str){
		int countChar=0;
		char data[]=str.toCharArray();
		for(char a:data){if(a==c){countChar+=1;}}
		return countChar;	
	}


	public static String trim(String str){
		return str.replaceAll(" ", "");
	}
	


	public static int countTokens(String str){
		return new StringTokenizer(str).countTokens();
	}
	


	public static String replaceChineseWith(String src,String replace){
		char data[]=src.toCharArray();
		for(char a:data){if(isChinese(a)==true){src=src.replaceAll(a+"", replace);}}
		return src;
	}


	public static String replaceEnglishWith(String src,String replace){
		char data[]=src.toCharArray();
		for(char a:data){if(isEnglish(a)==true){src=src.replaceAll(a+"", replace);}}
		return src;
	}
	


	public  static char randomChar(){
		return identifySet.charAt(new Random().nextInt(62));
	}


	public  static String randomString(){
		return randomString(new Random().nextInt(100));
	}


	public  static String randomString(int Length){
		StringBuilder builder=new StringBuilder();
		Random random=new Random();
		for(int i=0;i<Length;i++){builder.append(identifySet.charAt(random.nextInt(62)));}
		return builder.toString();
	}
	


	public static char randomChar(String source){
		return source.charAt(new Random().nextInt(source.length()));
	}


	public  static String randomString(char field[]){
		StringBuilder builder=new StringBuilder();
		Random random=new Random();
		int temp=random.nextInt(field.length);
		for(int i=0;i<temp;i++){builder.append(field[random.nextInt(field.length)]);}
		return builder.toString();
	}


	public  static String randomString(char field[],int length){
		StringBuilder builder=new StringBuilder();
		Random random=new Random();		
		for(int i=0;i<length;i++){builder.append(field[random.nextInt(field.length)]);}
		return builder.toString();
	}


	public  static String randomSubString(String source){
		return source.substring(new Random().nextInt(source.length()-1));
	}


	public  static String randomString(String source[]){
		return source[new Random().nextInt(source.length-1)];
	}
	



	public  static String randomToken(String source){
		String temp[]=source.split(" ");
		return temp[new Random().nextInt(temp.length)];
	}


	public static String rightShift(String source){
		String first=source.substring(0,source.length()-1);
		String last=source.substring(source.length()-1);
		return last.concat(first);
	}


	public static String rightShift(String source,int step){
		String temp=source;
		for(int i=0;i<step;i++){temp=rightShift(temp);}
		return temp;
	}
	


	public static String leftShift(String source){
		String first=source.substring(0,1);
		String last=source.substring(1);
		return last.concat(first);
	}
	


	public static String leftShift(String source,int step){
		String temp=source;
		for(int i=0;i<step;i++){temp=leftShift(temp);}
		return temp;
	}
	


	public static String toString(Object object,int length,char addtion,boolean addToHead){		
		String temp=object.toString();		
		if(temp.length()>length){return temp.substring(0,length);}
		else{
			char data[]=new char[length];
			if(addToHead==true){
				for(int i=0;i<length-temp.length();i++){data[i]=addtion;}
				for(int i=0;i<temp.length();i++){data[i+length-temp.length()]=temp.charAt(i);}
			}
			else{
				for(int i=0;i<temp.length();i++){data[i]=temp.charAt(i);}
				for(int i=0;i<length-temp.length();i++){data[i+temp.length()]=addtion;}
			}			
			return new String(data);
		}
		
	}
	
	
	
	
	


 public  static String getMD5String(String s) throws NoSuchAlgorithmException { 
    

			char hexDigits[] ={ '0', '1', '2', '3', '4', '5', '6', '7','8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };  	     
	        byte[] strTemp = s.getBytes();      
	        MessageDigest mdTemp= MessageDigest.getInstance("MD5");			 
	        mdTemp.update(strTemp);    
	        byte[] md = mdTemp.digest();    
	        int j = md.length;    
	        char str[] = new char[j * 2];    
	        int k = 0;    
	        for (int i = 0; i < j; i++) {    
	            byte b = md[i];       
	            str[k++] = hexDigits[b >> 4 & 0xf];    
	            str[k++] = hexDigits[b & 0xf];    
	        }  
        return new String(str);
	 
  }   
 
 
 
 
 
 
 
	 public static String createUID() {
		    String uid = new UID().toString();
		    StringTokenizer st = new StringTokenizer(uid, ":");
		    uid = "";
		    while (st.hasMoreElements()) {
		      uid = uid + st.nextToken();
		    }
	
		    char[] ch = uid.toCharArray();
		    char[] tmp = new char[ch.length];
		    int ind = ch.length - 1;
		    for (int i = 0; i < ch.length; i++) {
		      tmp[(ind--)] = ch[i];
		    }
		    uid = String.valueOf(tmp);
		    return uid;
		  }
	
	 
	 
	 
	 
	 
	 public static String getEncoding(String str) {      
	       String encode = "GB2312";      
	      try {      
	          if (str.equals(new String(str.getBytes(encode), encode))) {      
	               String s = encode;      
	              return s;      
	           }      
	       } catch (Exception exception) {      
	       }      
	       encode = "ISO-8859-1";      
	      try {      
	          if (str.equals(new String(str.getBytes(encode), encode))) {      
	               String s1 = encode;      
	              return s1;      
	           }      
	       } catch (Exception exception1) {      
	       }      
	       encode = "UTF-8";      
	      try {      
	          if (str.equals(new String(str.getBytes(encode), encode))) {      
	               String s2 = encode;      
	              return s2;      
	           }      
	       } catch (Exception exception2) {      
	       }      
	       encode = "GBK";      
	      try {      
	          if (str.equals(new String(str.getBytes(encode), encode))) {      
	               String s3 = encode;      
	              return s3;      
	           }      
	       } catch (Exception exception3) {      
	       }      
	      return "";      
	   }  
	 
	 
	 

		public static String getHtmlSource(String theURL, int connTimeout, int requTimeout,String ...charset) throws UnsupportedEncodingException {
			if(!theURL.startsWith("http")) {theURL="http://"+theURL;}
			String encode=(charset==null||charset.length==0)?getHtmlEncodeing(theURL):charset[0];
			if(encode.toLowerCase().equals("gzip")){return "";}
			String sTotalString="";
			URL l_url=null;
			HttpURLConnection l_connection=null;
			java.io.InputStream l_urlStream=null;
			BufferedReader l_reader=null;
			try {
				l_url=new URL(theURL);
				l_connection=(HttpURLConnection) l_url.openConnection();
				l_connection.setConnectTimeout(connTimeout);
				// ����ȡ���ݳ�ʱ����
				l_connection.setReadTimeout(requTimeout);
				try{l_connection.connect();}catch(Exception e) {return "";}
				if(l_connection.getResponseCode()==403) {return "";}
				l_urlStream=l_connection.getInputStream();
				l_reader=new BufferedReader(new InputStreamReader(l_urlStream,encode));
				int buffer_size=1024;
				char[] buffer=new char[buffer_size];
				StringBuffer sb=new StringBuffer();
				int readcount=0;
				while ((readcount=l_reader.read(buffer, 0, buffer_size)) > 0) {
					sb.append(buffer, 0, readcount);
				}
				sTotalString=sb.toString();
				l_reader.close();
				l_urlStream.close();
				l_connection.disconnect();
			}
			catch (Exception e) {
				e.printStackTrace();
			}
			finally {
				if (l_reader != null) {
					try {
						l_reader.close();
					}
					catch (Exception e) {}
				}
				if (l_urlStream != null) {
					try {
						l_urlStream.close();
					}
					catch (Exception e) {}
				}
				if (l_connection != null) {
					try {
						l_connection.disconnect();
					}
					catch (Exception e) {}
				}
			}
			return sTotalString;
		}
		
		
		public static String getHtmlEncodeing(String theURL){
			String sTotalString="";
			URL l_url=null;
			HttpURLConnection l_connection=null;
			java.io.InputStream l_urlStream=null;
			BufferedReader l_reader=null;
			try {
				l_url=new URL(theURL);
				l_connection=(HttpURLConnection) l_url.openConnection();
				String encode=l_connection.getContentEncoding();
				if(encode!=null)return encode;
				l_connection.setConnectTimeout(60000);
				l_connection.setReadTimeout(60000);
				try{l_connection.connect();}catch(Exception e) {return "";}
				if(l_connection.getResponseCode()==403) {return "";}
				l_urlStream=l_connection.getInputStream();
				l_reader=new BufferedReader(new InputStreamReader(l_urlStream,"UTF-8"));
				int buffer_size=1024;
				char[] buffer=new char[buffer_size];
				StringBuffer sb=new StringBuffer();
				int readcount=0;
				while ((readcount=l_reader.read(buffer, 0, buffer_size)) > 0) {
					sb.append(buffer, 0, readcount);
				}
				sTotalString=sb.toString();
				l_reader.close();
				l_urlStream.close();
				l_connection.disconnect();
			}
			catch (Exception e) {
				e.printStackTrace();
			}
			finally {
				if (l_reader != null) {
					try {
						l_reader.close();
					}
					catch (Exception e) {}
				}
				if (l_urlStream != null) {
					try {
						l_urlStream.close();
					}
					catch (Exception e) {}
				}
				if (l_connection != null) {
					try {
						l_connection.disconnect();
					}
					catch (Exception e) {}
				}
			}
			String temp=sTotalString;
			if(temp.contains("<body>")){temp=temp.substring(0,temp.indexOf("<body>"));}
			temp=HString.trim(temp).replace("\"", "");
			if(temp.contains("charset=")){
				int start=temp.indexOf("charset=")+8;
				int end=start;
				for (int i=start; i < temp.length(); i++) {
					char c=temp.charAt(i);
					if (c=='"'||c==' '||c=='>'||c=='/') {end=i;break;}
				}
				String type= temp.substring(start,end);
				if(type.toLowerCase().contains("gb2312")){return "gb2312";}
				else if(type.toLowerCase().contains("gbk")){return "gbk";}
				else if(type.toLowerCase().contains("utf8")){return "utf8";}
				else if(type.toLowerCase().contains("iso-8859-1")){return "iso-8859-1";}
				else if(type.toLowerCase().contains("utf-8")){return "utf-8";}
			}
			
			return getEncoding(sTotalString);
		}
}
