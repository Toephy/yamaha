package com.lixing.myJunit.httpclient;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;

public class FileUtil {
	
	/**
	 * 将文件读取为二进制数组
	 * @param fileLocation
	 * 			文件存储的路径
	 * @return byte[]
	 * 			返回文件的二进制数组
	 */
	public static byte[] fileToByte(String fileLocation) {
		ByteArrayOutputStream out = null;
		InputStream is = null;
		try {
			is = new FileInputStream(fileLocation);
			out = new ByteArrayOutputStream();
			byte[] b = new byte[1024];
			int n;
			while ((n = is.read(b)) != -1) {
				out.write(b, 0, n);
			}
			return out.toByteArray();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {}	
			}
			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {}
			}
		}
		return null;
	}
	
	/**
	 * 向文件中写内容
	 * @param fileName
	 * 			被写的文件路径
	 * @param content
	 * 			待写入的内容
	 * @throws IOException 
	 */
	public static void writeFile(String fileLocation, String content) {
		File file = new File(fileLocation);
		FileWriter fw = null;
		try {
			if (!file.exists()) {
				createdirAndFile(file);
			}
			fw = new FileWriter(file, false);
			fw.write(content);
			fw.flush();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (fw != null) {
				try {
					fw.close();
				} catch (IOException e) {
				}
			}
		}
	}
	
	public static void createdirAndFile(File file) throws IOException {
		if (!file.getParentFile().exists()) {
			file.getParentFile().mkdirs();
		}
		file.createNewFile();
	}
	
	/**
	 * 删除指定文件
	 * @param fileLocation
	 */
	public static void removeFile(String fileLocation) {
		try {
			File file = new File(fileLocation);
			if (file.exists())
				file.delete();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static String getClassPath() {
		String path = FileUtil.class.getClassLoader().getResource("/log4j.xml").getPath();
		//webapps/siling-web/WEB-INF/classes/log4j.xml
		return path.substring(0, path.lastIndexOf("/"));
	}
	
	public static String getClassPath(String url) {
		return FileUtil.class.getClassLoader().getResource(url).getPath();
	}
	
	public static void main(String[] args) {
		// writeFile("E:\\test\\ll.txt", "t");
		// removeFile("E:\\log\\resin-books\\words\\access.log");
	}
}
