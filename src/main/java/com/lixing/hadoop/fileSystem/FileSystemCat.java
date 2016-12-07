package com.lixing.hadoop.fileSystem;

import java.net.URI;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;

public class FileSystemCat {

	public static final String HDFS_URL = "hdfs://120.197.138.47:9000/test/hadooptest2.log";
	
	public static void main(String[] args) throws Exception {
		Configuration conf = new Configuration();
		
		FileSystem fs = FileSystem.get(URI.create(HDFS_URL), conf);
		
		FSDataInputStream in = null;
		try {
			in = fs.open(new Path(HDFS_URL));
			IOUtils.copyBytes(in, System.out, 4096, false);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			IOUtils.closeStream(in);
		}
	}

}
