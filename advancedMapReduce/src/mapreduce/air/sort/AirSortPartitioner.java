package mapreduce.air.sort;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.Partitioner;

//Partitioner클래스에는 Mapper의 key와 value타입을 generic으로 명시
//year를 기준으로 해시코드를 구해서 같은 year를 갖고 있는 데이터를
//같은 reducer에서 작업할 수 있도록
public class AirSortPartitioner extends Partitioner<CustomKey, IntWritable>{
	//numPartitions는 파티션의 갯수 = 리듀스테스크의 갯수 
	@Override
	public int getPartition(CustomKey key, IntWritable value, int numPartitions) {
		return key.getYear().hashCode() % numPartitions;
	}
}
