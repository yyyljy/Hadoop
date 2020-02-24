package mapred.exam.airline.multiple;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.MultipleOutputs;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;

public class AirlineMultipleDriver {
	public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
		Configuration conf = new Configuration();
		Job job = new Job(conf, "airline");
		job.setMapperClass(AirlineMultipleMapper.class);
		job.setReducerClass(AirlineMultipleReducer.class);
		job.setJarByClass(AirlineMultipleDriver.class);
		job.setInputFormatClass(TextInputFormat.class);
		job.setOutputFormatClass(TextOutputFormat.class);
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(IntWritable.class);
		FileInputFormat.addInputPath(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));
		MultipleOutputs.addNamedOutput(job, "arrDelay", TextOutputFormat.class, Text.class , IntWritable.class);
		MultipleOutputs.addNamedOutput(job, "depDelay", TextOutputFormat.class, Text.class , IntWritable.class);
		MultipleOutputs.addNamedOutput(job, "DepNA", TextOutputFormat.class, Text.class , IntWritable.class);
		MultipleOutputs.addNamedOutput(job, "ArrNA", TextOutputFormat.class, Text.class , IntWritable.class);
		job.waitForCompletion(true);
	}
}
