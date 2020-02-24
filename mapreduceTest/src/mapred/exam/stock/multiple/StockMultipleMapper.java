package mapred.exam.stock.multiple;

import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class StockMultipleMapper extends Mapper<LongWritable, Text, Text, IntWritable> {
	static final IntWritable outputVal = new IntWritable(1);
	Text outputKey = new Text();
	
	@Override
	protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Text, IntWritable>.Context context)
			throws IOException, InterruptedException {
		if(key.get()>0) {
			String[] line = value.toString().split(",");
			double closeprice = Double.parseDouble(line[6]);
			double openprice = Double.parseDouble(line[3]);
			double result = closeprice-openprice;
			String year = line[2].substring(0, 4);
			if((result)>0) {//상승 마감
				outputKey.set("up,"+year);
				context.write(outputKey, outputVal);
			}else if((result)<0) {//하락 마감
				outputKey.set("down,"+year);
				context.write(outputKey, outputVal);
			}else{//보합 마감
				outputKey.set("equal,"+year);
				context.write(outputKey, outputVal);
			}
		}
	}
}
