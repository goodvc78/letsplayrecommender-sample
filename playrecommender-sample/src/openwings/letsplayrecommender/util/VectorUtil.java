package openwings.letsplayrecommender.util;


import java.util.Vector;

public class VectorUtil {
    
	public static void sumVectorEachElement(Vector<Double> added,  Vector<Double> val)
	{
		int size=val.size();
		if(added.size()<size)
			added.setSize(size);
		
		for(int i=0 ; i<size ; i++ )
		{
			Double sum=added.get(i);
			if(null == sum) 	sum = new Double(0); 
			Double v=val.get(i);
			if(null == v) 	v = new Double(0); 
			added.set(i, v+sum);
		}
	}
	
	public static void divideVectorEachElement(Vector<Double> divided, double val)
	{
		int size=divided.size();
		for( int i=0 ; i<size ; i++ )
		{
			Double v=divided.get(i);
			divided.set(i, v/val);
		}
	}
	
	
}
