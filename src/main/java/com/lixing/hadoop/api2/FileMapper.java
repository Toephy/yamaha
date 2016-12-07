package com.lixing.hadoop.api2;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;

public class FileMapper extends org.apache.hadoop.mapreduce.Mapper<LongWritable, Text, Text, IntWritable> {

	private static final String PUSH_SUCCESS 			= "推送成功";
	private static final String ANDROID_PUSH_SUCCESS 	= "android小说更新推送成功";
	private static final String IOS_PUSH_SUCCESS 		= "IOS小说更新推送成功";
	
	private final static IntWritable one = new IntWritable(1);
	private Text word = new Text();
	
	@Override
	protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
		String line = value.toString();
		if (line.contains(PUSH_SUCCESS)) {
			word.set("PUSH_SUCCESS");
			context.write(word, one);
		} else if (line.contains(ANDROID_PUSH_SUCCESS)) {
			word.set("ANDROID_PUSH_SUCCESS");
			context.write(word, one);
		} else if (line.contains(IOS_PUSH_SUCCESS)) {
			word.set("IOS_PUSH_SUCCESS");
			context.write(word, one);
		}
	}

}
