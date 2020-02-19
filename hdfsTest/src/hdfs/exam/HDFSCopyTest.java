package hdfs.exam;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

public class HDFSCopyTest {
	public static void main(String[] args) {
		Configuration conf = new Configuration();
		FileSystem hdfs = null;
		FSDataInputStream hdfsIn = null;
		FSDataOutputStream hdfsOut = null;
		
		try {
			hdfs = FileSystem.get(conf);
			Path inPath = new Path(args[0]);
			Path outPath = new Path(args[1]);
			hdfsIn = hdfs.open(inPath);
			String content = hdfsIn.readUTF();
			hdfsOut = hdfs.create(outPath);
			hdfsOut.writeUTF(content);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
