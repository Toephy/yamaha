package com.lixing.hadoop.api1;

import java.io.IOException;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.FileInputFormat;
import org.apache.hadoop.mapred.FileOutputFormat;
import org.apache.hadoop.mapred.JobClient;
import org.apache.hadoop.mapred.JobConf;


public class FileJob {

	public static void main(String[] args) {
		if (args.length != 2) {
			System.out.println("please input <file path> and <output path>");
			System.exit(-1);
		}
		
		JobConf conf = new JobConf(FileJob.class);
		
		conf.setJobName("FileJob Test");
		
		FileInputFormat.addInputPath(conf, new Path(args[0]));
		FileOutputFormat.setOutputPath(conf, new Path(args[1]));
		
		conf.setMapperClass(FileMapper.class);
		conf.setReducerClass(FileReducer.class);
		
		conf.setOutputKeyClass(Text.class);
		conf.setOutputValueClass(IntWritable.class);
		
		try {
			JobClient.runJob(conf);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
