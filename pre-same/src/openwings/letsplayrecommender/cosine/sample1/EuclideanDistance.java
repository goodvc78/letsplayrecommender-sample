package openwings.letsplayrecommender.cosine.sample1;

import java.util.Vector;

public class EuclideanDistance {

	private double distance(Vector<Double> v1, Vector<Double> v2) {
		double sum = 0.0;
        for (int i=0 ; i<v1.size() ; i++) {
        	sum = sum + Math.pow((v1.get(i)-v2.get(i)),2.0);
        }
        return Math.sqrt(sum);
	}
}
