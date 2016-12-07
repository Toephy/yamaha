package com.lixing.hadoop.fileSystem;

import java.net.URI;
import java.util.Date;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.LocatedFileStatus;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.fs.RemoteIterator;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.util.Progressable;

public class FileSystemCopy {

	public static final String HDFS_URL = "hdfs://120.197.138.47:9000";
	
	public static void main(String[] args) throws Exception {
		Configuration conf = new Configuration();
		FileSystem fs = FileSystem.get(URI.create(HDFS_URL), conf);
		Path path = new Path(HDFS_URL + "/test/mkfile.txt");
		if (!fs.exists(path)) {
			fs.create(new Path(HDFS_URL + "/test/mkfile.txt"));
		}
		
		FSDataOutputStream out = fs.create(path, new Progressable() {
			@Override
			public void progress() {
				System.out.println(new Date());
			}
		});
		String content = "abc";
		out.write(content.getBytes("UTF-8"));
		IOUtils.closeStream(out);
		
		FileStatus fileStatus = fs.getFileStatus(path);
		System.out.println(fileStatus);
		
		RemoteIterator<LocatedFileStatus> list = fs.listFiles(new Path(HDFS_URL + "/"), false);
		while (list.hasNext()) {
			System.out.println(list.next());
		}
	}

}
