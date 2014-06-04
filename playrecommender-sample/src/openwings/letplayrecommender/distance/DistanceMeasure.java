package openwings.letplayrecommender.distance;

import java.util.Vector;

public class DistanceMeasure {


	private static double measureCosine(Integer[] A, Integer[] B) {
		
		int size = A.length;
		
		long normA = 0, normB=0, scla=0;
		for( int i=0 ; i<size ; i++ ) {
			normA += ( A[i]*A[i] );
			normB += ( B[i]*B[i] );
			scla += (  A[i]*B[i] );
		}
		
		double similarity = scla / ( Math.sqrt(normA) * Math.sqrt(normB) );
		return similarity;
	}
	
	public static double measureCosine(Vector<Object> A, Vector<Object> B) {
		
		if( A.size() != B.size() )
			return 0;
		
		int size = A.size();
		long normA = 0, normB=0, scla=0;
		for( int i=0 ; i<size ; i++ ) {
			
			if( !( A.get(i) instanceof Number) )
				continue;
			Double a = (Double) A.get(i);
			Double b = (Double) B.get(i);
			normA += ( a * a );
			normB += ( b * b );
			scla += (  a * b );
		}
		
		double similarity = scla / ( Math.sqrt(normA) * Math.sqrt(normB) );
		return similarity;
	}	
	
	
	public static double measureEucliean(Vector<Object> A, Vector<Object> B) {
		
		if( A.size() != B.size() )
			return 0;
		double sum = 0.0;
        for (int i=0 ; i<A.size() ; i++) {
			if( !( A.get(i) instanceof Number) )
				continue;
			Double a = (Double) A.get(i);
			Double b = (Double) B.get(i);
			sum = sum + Math.pow((a-b),2.0);
        }
        return Math.sqrt(sum);
	}
}
