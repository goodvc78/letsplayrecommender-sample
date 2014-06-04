package openwings.letsplayrecommender.sample.chapter_5th;

import java.io.IOException;
import java.util.List;
import java.util.Vector;

import openwings.letplayrecommender.kmeanssample.KmeansClustering;
import openwings.letplayrecommender.util.Dataset;

public class CusteringWorktimeMain {

	final static int VECTOR_DIMENTION = 5; 
	final static int TITLE_INDEX = 0;
	
	
	public static void main(String[] args) throws IOException {

//		List<Vector<Object>> dataset = Dataset.toDataset ("data/pc-usetime-mixed-all.txt", "\t", VECTOR_DIMENTION, TITLE_INDEX );
		List<Vector<Object>> dataset = Dataset.toDataset ("data/pc-usetime-office.txt", "\t", VECTOR_DIMENTION, TITLE_INDEX );
		new KmeansClustering().clusterKmeans(dataset, 4);

	}

}
