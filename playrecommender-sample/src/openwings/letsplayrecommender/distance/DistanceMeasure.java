package openwings.letsplayrecommender.distance;

import java.util.HashSet;
import java.util.Set;
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
	
	double measureJaccard(Set<String> item1, Set<String> item2 ) {
		
		Set<String> kyojibhab = intersection( item1, item2 );
		Set<String> habjibhab = union( item1, item2 );
		
		return (double) kyojibhab.size() / (double) habjibhab.size() ;
	}
	
	Set<String> splitLetter (String word) {
	
		int len  = word.length();
		Set<String> letters  = new HashSet<String> (); 
		for( int i=0 ; i<(len-1) ; i++ ) {
			letters.add(word.substring(i, i+2));
		}
		return letters;
	}
	
	private <T> Set<T> union(Set<T> setA, Set<T> setB) {
		Set<T> tmp = new HashSet<T>(setA);
		tmp.addAll(setB);
	    return tmp;
	}
	
	private <T> Set<T> intersection(Set<T> setA, Set<T> setB) {
		Set<T> tmp = new HashSet<T>();
		for (T x : setA)
			if (setB.contains(x))
				tmp.add(x);
	    return tmp;
	}
}
