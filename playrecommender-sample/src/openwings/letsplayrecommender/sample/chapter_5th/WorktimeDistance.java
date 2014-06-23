package openwings.letsplayrecommender.sample.chapter_5th;

import java.io.IOException;
import java.util.List;
import java.util.Vector;

import openwings.letsplayrecommender.distance.DistanceMeasure;
import openwings.letsplayrecommender.util.Dataset;
import openwings.letsplayrecommender.util.MathUtil;

public class WorktimeDistance {
	
	public static void main(String[] args) throws IOException {
		
		List<Vector<Object>> dataset = Dataset.toDataset("data/pc-usetime-mixed-part.txt", "\t", 8, 0);
		
		System.out.println("Cosine=========================");
		for( Vector<Object> v1 : dataset ) {
			for( Vector<Object> v2 : dataset ) {
				double similarity = DistanceMeasure.measureCosine(v1,v2);
				System.out.print( MathUtil.round(similarity,4) + "\t");
			}
			System.out.println();
		}
		
		System.out.println("Euclidean=========================");
		for( Vector<Object> v1 : dataset ) {
			for( Vector<Object> v2 : dataset ) {
				double similarity = DistanceMeasure.measureEucliean(v1,v2);
				System.out.print( MathUtil.round(similarity,4) + "\t");
			}
			System.out.println();
		}
		
		
	}
	
}
