package openwings.letsplayrecommender.jaccard.sample1;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;


public class JaccardDistanceSimple {

	public static void main(String[] args) throws IOException  {
		
		new JaccardDistanceSimple().mainLoog();
		
	}

	static <K, V extends Comparable<? super V>> List<Entry<K, V>> entriesSortedByValues(
			Map<K, V> map) {

		List<Entry<K, V>> sortedEntries = new ArrayList<Entry<K, V>>(
				map.entrySet());

		Collections.sort(sortedEntries, new Comparator<Entry<K, V>>() {
			@Override
			public int compare(Entry<K, V> e1, Entry<K, V> e2) {
				return e2.getValue().compareTo(e1.getValue());
			}
		});

		return sortedEntries;
	}
	
	void mainLoog() throws IOException {
		
		String input = "NBC 히어로즈 시즌1 EP07.mp4";
		
		System.out.println("input = " + input);
		/// 글자를 2 letter splite
		Set<String> inputs = splitLetter(input);
		
		Map<String, Double> similaries = new HashMap<String, Double> (); 
				
		BufferedReader br = new BufferedReader(new FileReader("resource\\drama-list.txt"));
        String line;
        while((line = br.readLine()) != null) {
        	
        	/// 글자를 2 letter
        	Set<String> items = splitLetter(line);

        	/// 유사도 측정 
        	double similarity = measureJaccard(inputs, items);
        	
        	similaries.put(line, similarity);
//	        	System.out.println(line + " =  " +  similarity);
        }
	    
        List<Entry<String, Double>> result = entriesSortedByValues(similaries);
        
        for( Entry<String, Double> e :   result ) {
        	System.out.println( e.toString());
        }
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
