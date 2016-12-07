package com.lixing.hadoop.api2;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;



public class FileReducer extends Reducer<Text, IntWritable, Text, IntWritable>  {

	@Override
	protected void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
		int total = 0;
		while(values.iterator().hasNext()) {
			total += values.iterator().next().get();
		}
		context.write(key, new IntWritable(total));
	}
	
}
