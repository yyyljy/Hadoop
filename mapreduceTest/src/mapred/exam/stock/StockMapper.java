package mapred.exam.stock;

import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class StockMapper extends Mapper<LongWritable, Text, Text, IntWritable> {
	static final IntWritable outputVal = new IntWritable(1);
	Text outputKey = new Text();
	
	@Override
	protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Text, IntWritable>.Context context)
			throws IOException, InterruptedException {
		/*StringTokenizer st = new StringTokenizer(str, delim);*/
		/*StringTokenizer st = new StringTokenizer(value.toString());*/
		if(key.get()>0) {
			String[] line = value.toString().split(",");
			double closeprice = Double.parseDouble(line[6]);
			double openprice = Double.parseDouble(line[3]);
			String date = line[2].substring(0, 4);
			if((closeprice-openprice)>0) {
				outputKey.set(date);
				context.write(outputKey, outputVal);
			}
		}
	}
}
