package HHH.HZIP;

import HHH.HZIP.REF.BZip2CompressorInputStream;
import HHH.HZIP.REF.BZip2CompressorOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;



public class HZIP {
	
	
	public static void bzip2(File inFile,File outFile) throws IOException {
		FileInputStream in=new FileInputStream(inFile);
		FileOutputStream out=new FileOutputStream(outFile);
		BZip2CompressorOutputStream bzip=new BZip2CompressorOutputStream(out,9);
		byte temp[]=new byte[1024*1024*50];
		int cc=0;
		while((cc=in.read(temp))>0) {
			bzip.write(temp, 0, cc);
		}
		bzip.flush();
		bzip.close();
		out.flush();
		out.close();
		in.close();
	}
	
	
	public static void unbzip2(File inFile,File outFile) throws IOException {
		FileInputStream in=new FileInputStream(inFile);
		FileOutputStream out=new FileOutputStream(outFile);
		BZip2CompressorInputStream bzip=new BZip2CompressorInputStream(in);
		byte temp[]=new byte[1024*1024*50];
		int cc=0;
		while((cc=bzip.read(temp))>0) {
			out.write(temp, 0, cc);
		}
		bzip.close();
		out.flush();
		out.close();
		in.close();
	}
	
	public static void bzip2(String inFile,String outFile) throws IOException{
		bzip2(new File(inFile),new File(outFile));
	}
	
	public static void unbzip2(String inFile,String outFile) throws IOException{
		unbzip2(new File(inFile),new File(outFile));
	}
	
	public static byte[] bzip2(byte data[]) throws IOException{
		ByteArrayOutputStream out=new ByteArrayOutputStream();
		BZip2CompressorOutputStream bzip=new BZip2CompressorOutputStream(out,9);
		bzip.write(data);
		bzip.flush();
		bzip.close();
		out.flush();
		byte d[]=out.toByteArray();
		out.close();
		return d;
	}
	
	
	public static byte[] unbzip2(byte data[]) throws IOException{
		ByteArrayInputStream in=new ByteArrayInputStream(data);
		ByteArrayOutputStream out=new ByteArrayOutputStream();
		BZip2CompressorInputStream bzip=new BZip2CompressorInputStream(in);
		byte temp[]=new byte[1024*1024*50];
		int cc=0;
		while((cc=bzip.read(temp))>0) {
			out.write(temp, 0, cc);
		}
		out.flush();
		byte d[]=out.toByteArray();
		out.close();
		bzip.close();
		in.close();
		return d;
		

	}
	

	
	public static byte[] gzip(byte[] data) {
		byte[] b=null;
		try {
			ByteArrayOutputStream bos=new ByteArrayOutputStream();
			GZIPOutputStream gzip=new GZIPOutputStream(bos, 9);
			gzip.write(data);
			gzip.finish();
			gzip.close();
			b=bos.toByteArray();
			bos.close();
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
		return b;
	}
	
	

	public static byte[] ungzip(byte[] data) {
		byte[] b=null;
		try {
			ByteArrayInputStream bis=new ByteArrayInputStream(data);
			GZIPInputStream gzip=new GZIPInputStream(bis);
			byte[] buf=new byte[1024*1024*50];
			int num=-1;
			ByteArrayOutputStream baos=new ByteArrayOutputStream();
			while ((num=gzip.read(buf)) != -1) {
				baos.write(buf, 0, num);
			}
			b=baos.toByteArray();
			baos.flush();
			baos.close();
			gzip.close();
			bis.close();
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
		return b;
	}
	
	
	public static byte[] limitZIP(byte []data) throws IOException {
		return bzip2(gzip(data));
	}
	
	
	
	
	public static byte[] unlimitZIP(byte []data) throws IOException {
		return ungzip(unbzip2(data));
	}
	
	
	
	public static String limitZIP(String data) throws IOException {
		byte temp[]=HZIP.limitZIP(data.getBytes("UTF-8"));
		return new String(HZIP.gzip(temp),"ISO-8859-1");
	}
	
	
	
	public static String unlimitZIP( String data) throws IOException {
		byte []temp=HZIP.ungzip(data.getBytes("ISO-8859-1"));
		return new String(HZIP.unlimitZIP(temp),"UTF-8");
	}
	
	
	public static void ZIP(File in,File out) throws IOException {
		if(in.isDirectory()){ZipDirectory(in,out);}
		else {
			ZipFile(in,out);
		}
	}
	
	
	 private static void ZipFile(File in,File out) {
	    	try {
	            File file =in;
	            File zipFile = out;
	            InputStream input = new FileInputStream(file);
	            ZipOutputStream zipOut = new ZipOutputStream(new FileOutputStream(zipFile));
	            zipOut.putNextEntry(new ZipEntry(file.getName()));
	            int temp = 0;byte data[]=new byte[1024*1024*50];
	            while((temp = input.read(data)) != -1){
	                zipOut.write(data,0,temp);
	            }
	            input.close();
	            zipOut.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	
	
	private static void ZipDirectory(File in,File out) {
		try {
	        File file = in;// 要被压缩的文件夹
	        File zipFile =out;
	        InputStream input = null;
	        ZipOutputStream zipOut = new ZipOutputStream(new FileOutputStream(zipFile));
	        if(file.isDirectory()){
	            File[] files = file.listFiles();
	            for(int i = 0; i < files.length; ++i){
	                input = new FileInputStream(files[i]);
	                zipOut.putNextEntry(new ZipEntry(file.getName() + File.separator + files[i].getName()));
	                int temp = 0;byte data[]=new byte[50*1024*1024];
	                while((temp = input.read(data)) != -1){
	                    zipOut.write(data,0,temp);
	                }
	                input.close();
	            }
	        }
	        zipOut.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	public static void unZIP(File in,File out){
		try {
            File file =in;
            String outx=out.getAbsolutePath();
            File outFile = null;
            java.util.zip.ZipFile zipFile = new java.util.zip.ZipFile(file);
            ZipInputStream zipInput = new ZipInputStream(new FileInputStream(file));
            ZipEntry entry = null;
            InputStream input = null;
            OutputStream output = null;
            while((entry = zipInput.getNextEntry()) != null){
                outFile = new File(outx + File.separator + entry.getName());
                if(!outFile.getParentFile().exists()){
                    outFile.getParentFile().mkdirs();
                }
                if(!outFile.exists()){
                    outFile.createNewFile();
                }
                input = zipFile.getInputStream(entry);
                output = new FileOutputStream(outFile);
                int temp = 0;byte data[]=new byte[1024*1024*50];
                while((temp = input.read(data)) != -1){
                    output.write(data,0,temp);
                }
                input.close();
                output.close();
            }
            zipInput.close();
            zipFile.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

}
