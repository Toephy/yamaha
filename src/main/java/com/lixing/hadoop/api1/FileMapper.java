package com.lixing.hadoop.api1;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.Mapper;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reporter;

public class FileMapper extends MapReduceBase implements Mapper<LongWritable, Text, Text, IntWritable> {

	private static final String PUSH_SUCCESS 			= "推送成功";
	private static final String ANDROID_PUSH_SUCCESS 	= "android小说更新推送成功";
	private static final String IOS_PUSH_SUCCESS 		= "IOS小说更新推送成功";
	
	@Override
	public void map(LongWritable key, Text value,
			OutputCollector<Text, IntWritable> output, Reporter reporter)
			throws IOException {
		String line = value.toString();
		System.out.println("Beafore map: [key = " + key + "value = " + value + "]");
		if (line.contains(PUSH_SUCCESS)) {
			output.collect(new Text("PUSH_SUCCESS"), new IntWritable(1));
			System.out.println("After map: [key = " + new Text("PUSH_SUCCESS") + "value = " + new IntWritable(1) + "]");
		} else if (line.contains(ANDROID_PUSH_SUCCESS)) {
			output.collect(new Text("ANDROID_PUSH_SUCCESS"), new IntWritable(1));
			System.out.println("After map: [key = " + new Text("ANDROID_PUSH_SUCCESS") + "value = " + new IntWritable(1) + "]");
		} else if (line.contains(IOS_PUSH_SUCCESS)) {
			output.collect(new Text("IOS_PUSH_SUCCESS"), new IntWritable(1));
			System.out.println("After map: [key = " + new Text("IOS_PUSH_SUCCESS") + "value = " + new IntWritable(1) + "]");
		}
	}

}
