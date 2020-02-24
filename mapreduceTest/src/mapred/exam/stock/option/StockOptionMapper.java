package mapred.exam.stock.option;

import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class StockOptionMapper extends Mapper<LongWritable, Text, Text, IntWritable> {
	static final IntWritable outputVal = new IntWritable(1);
	Text outputKey = new Text();
	String jobType;
	
	@Override
	public void setup(Mapper<LongWritable, Text, Text, IntWritable>.Context context)
			throws IOException, InterruptedException {
		jobType = context.getConfiguration().get("jobType");
	}

	@Override
	protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Text, IntWritable>.Context context)
			throws IOException, InterruptedException {
		if(key.get()>0) {
			String[] line = value.toString().split(",");
			if(line!=null && line.length>0) {
				double closeprice = Double.parseDouble(line[6]);
				double openprice = Double.parseDouble(line[3]);
				String date = line[2].substring(0, 4);
				if(jobType.equals("LT")){
					if((closeprice-openprice)>0) {
						outputKey.set(date);
						context.write(outputKey, outputVal);
					}
				}else if(jobType.equals("ST")) {
					if((closeprice-openprice)<0) {
						outputKey.set(date);
						context.write(outputKey, outputVal);
					}
				}else if(jobType.equals("EQ")) {
					if((closeprice-openprice)==0) {
						outputKey.set(date);
						context.write(outputKey, outputVal);
					}
				}
			}
		}
	}
}
