package openwings.letsplayrecommender.cosine.sample1;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

public class CosineDistance {

	double _baseNumerator=0;
	double _baseDenomiator=10;
	
	
	public CosineDistance() {
		super();
		this._baseNumerator = 0;
		this._baseDenomiator = 10;
	}
	
	
	public CosineDistance(double baseNumerator, double baseDenomiator) {
		super();
		this._baseNumerator = baseNumerator;
		this._baseDenomiator = baseDenomiator;
	}

	private double calculate( double sclar, double norm1, double norm2 ) {
	
		double distance = ( sclar + _baseNumerator) / ( Math.sqrt( norm1 * norm2 ) + _baseDenomiator );
		return distance ;
	}
	
	private double distance(Vector<Double> v1, Vector<Double> v2) {
		double sclar = 0, norm1 = 0, norm2 = 0;
        for (int i=0 ; i<v1.size() ; i++) {
        	sclar += v1.get(i) * v2.get(i);
        	norm1 += Math.pow(v1.get(i), 2);
        	norm2 += Math.pow(v2.get(i), 2);
        }
        return calculate( sclar , norm1, norm2);
	}

	private double distance(Set<String> s1, Set<String> s2) {
		Set<String> both =new HashSet<String>(s1); 
        both.retainAll(s2);
        double sclar = 0, norm1 = 0, norm2 = 0;
        
        for (String k : both) sclar += (s1.contains(k)?1:0) * (s2.contains(k)?1:0);
        for (String k : s1) norm1 += (s1.contains(k)?1:0) * (s1.contains(k)?1:0);
        for (String k : s2) norm2 += (s2.contains(k)?1:0) * (s2.contains(k)?1:0);
        return calculate( sclar , norm1, norm2);
	}

	private double distance(Map<String, Double> m1, Map<String, Double> m2) {
		Set<String> both =new HashSet<String>(m1.keySet()); 
        both.retainAll(m2.keySet());
        double sclar = 0, norm1 = 0, norm2 = 0;
        for (String k : both) sclar += m1.get(k) * m2.get(k);
        for (String k : m1.keySet()) norm1 += Math.pow(m1.get(k), 2);
        for (String k : m2.keySet()) norm2 += Math.pow(m2.get(k), 2);
        return calculate( sclar , norm1, norm2);
	}



}
