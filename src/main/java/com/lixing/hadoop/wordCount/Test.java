package com.lixing.hadoop.wordCount;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper.Context;
import org.mockito.Mockito;


public class Test {

	public static void main(String[] args) throws Exception {
		FileMapper mapper = new FileMapper();
		Text value = new Text("abc abc hello world!");
		Context context = Mockito.mock(Context.class);
		mapper.map(null, value, context);
		Mockito.verify(context, Mockito.never()).write(new Text("abc"), new IntWritable(1));
		
		
	}

}
