package mapred.exam.air;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class AirlineMapper extends Mapper<LongWritable, Text, Text, IntWritable>{
	static final IntWritable outputVal = new IntWritable(1);
	Text outputKey = new Text();
	
	@Override
	protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Text, IntWritable>.Context context)
			throws IOException, InterruptedException {
		if(key.get()>0) {
			String[] line = value.toString().split(",");
			//if(line[4].equals("NA") || line[5].equals("NA")) {
			if(line[15].equals("NA")) {
				
			}else {
				/*
				5	DepTime		actual departure time (local, hhmm)
				6	CRSDepTime	scheduled departure time (local, hhmm)
				*/
				//int DepTime = Integer.parseInt(line[4]);
				//int CRSDepTime = Integer.parseInt(line[5]);
				String month = line[1];
				//if((DepTime-CRSDepTime)>0) {
				if(Integer.parseInt(line[15])>0) {
					outputKey.set(line[0]+"년 "+month+"월");
					context.write(outputKey, outputVal);
				}
			}
		}
	}
}
