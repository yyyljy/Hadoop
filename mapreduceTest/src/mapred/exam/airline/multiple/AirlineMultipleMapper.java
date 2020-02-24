package mapred.exam.airline.multiple;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class AirlineMultipleMapper extends Mapper<LongWritable, Text, Text, IntWritable>{
	static final IntWritable outputVal = new IntWritable(1);
	Text outputKey = new Text();
	
	@Override
	protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Text, IntWritable>.Context context)
			throws IOException, InterruptedException {
		if(key.get()>0) {
			String[] line = value.toString().split(",");
			if(value.toString().substring(0, 4).equals("1987")) {
				String month = line[1];
				if(line[15].equals("NA")){//출발 NA
					outputKey.set("DepNA,"+month);
					context.write(outputKey, outputVal);
				}else if(line[14].equals("NA")) {//NA
					outputKey.set("ArrNA,"+month);
					context.write(outputKey, outputVal);
				}else if(Integer.parseInt(line[15])>0) {//출발
					outputKey.set("depDelay,"+month);
					context.write(outputKey, outputVal);
				}else if(Integer.parseInt(line[14])>0) {//도착
					outputKey.set("arrDelay,"+month);
					context.write(outputKey, outputVal);
				}
			}
		}
	}
}
