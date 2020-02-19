package hdfs.exam;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

public class HDFSTest02 {
	public static void main(String[] args) {
		Configuration conf = new Configuration();
		FileSystem hdfs = null;
		FSDataInputStream hdfsin = null;
		try {
			hdfs = FileSystem.get(conf);
			Path path = new Path(args[0]);
			hdfsin = hdfs.open(path);
			System.out.println(hdfsin.readUTF());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
