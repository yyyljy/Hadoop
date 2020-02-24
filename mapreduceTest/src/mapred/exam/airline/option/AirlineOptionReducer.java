package mapred.exam.airline.option;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.output.MultipleOutputs;

public class AirlineOptionReducer extends Reducer<Text, IntWritable, Text, IntWritable>{
	IntWritable resultVal = new IntWritable();
	
	@Override
	protected void reduce(Text key, Iterable<IntWritable> values,
			Reducer<Text, IntWritable, Text, IntWritable>.Context context) throws IOException, InterruptedException {
		int sum = 0;
		for(IntWritable value:values) {
			sum += value.get();
		}
		resultVal.set(sum);
		context.write(key, resultVal);
	}

}
