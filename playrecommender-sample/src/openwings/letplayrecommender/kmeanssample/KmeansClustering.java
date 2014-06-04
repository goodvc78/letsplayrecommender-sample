package openwings.letplayrecommender.kmeanssample;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import openwings.letplayrecommender.distance.DistanceMeasure;

public class KmeansClustering {

	final static int MAX_ITERATION = 20;
	
	public List<Integer>[] clusterKmeans(List<Vector<Object>> dataset, int cnt) {
		
		Vector<Object>[] centroids = firstCentroid( dataset, cnt );
		List<Integer>[] clusteredDataSet = nearestIds( dataset,centroids   );
		Vector<Object>[] lastcentroids;
		for( int i=0 ; i<MAX_ITERATION ; i++ ) {
			lastcentroids = centroids;
			centroids = newCentroid( clusteredDataSet, dataset   );
			
			if( compareCentroids(lastcentroids, centroids) ) {
				System.out.println("Don't move the centroid ");
				printClusteredTitle(clusteredDataSet, dataset );
				break;
			}
			System.out.println("\r\n"+(i+1)+"th clustered==========================");
			clusteredDataSet = nearestIds( dataset,centroids   );
			
		}
		return null; 
	}
	
	boolean compareCentroids( Vector<Object>[] centroid1, Vector<Object>[] centroid2 ) {
		
		for( Vector<Object> id1 : centroid1 ) {
			boolean matched=false;
			for(   Vector<Object> id2 : centroid2  ) {
				if( true == id2.equals(id1) ) {
					matched=true;
					break;
				}
			}
			if( !matched )
				return false;
		}
		return true;
	}
	
	
	private Vector<Object>[] newCentroid(List<Integer>[] clustered, List<Vector<Object>> data) {
		int size=clustered.length;
		Vector<Object>[] centroid = new  Vector[clustered.length];
		
		int idx=0;
		for( List<Integer> list : clustered )
		{
			Vector<Object> sum= new Vector<Object>();
			for( Integer index : list )
			{
				sumVectorEachElement(sum, data.get(index));
			}
			
			divideVectorEachElement(sum, list.size());
			centroid[idx++]=sum;
		}
		return centroid;
	}
	
	public static void sumVectorEachElement(Vector<Object> added,  Vector<Object> val)
	{
		int size=val.size();
		if(added.size()<size)
			added.setSize(size);
		
		for(int i=0 ; i<(size-1) ; i++ )
		{
			Double sum=(Double)added.get(i);
			if(null == sum) 	sum = new Double(0); 
			Double v=(Double)val.get(i);
			if(null == v) 	v = new Double(0); 
			added.set(i, v+sum);
		}
		added.set(size-1, new String("clustered"));
	}
	
	public static void divideVectorEachElement(Vector<Object> divided, double val)
	{
		int size=divided.size();
		for( int i=0 ; i<size ; i++ )
		{
			if( !(divided.get(i) instanceof Number) )
				continue;
			
			Double v=(Double)divided.get(i);
			divided.set(i, v/val);
		}
	}
	
	private void printClusteredTitle(List<Integer>[] clustered, List<Vector<Object>> dataset ) {
		
		for( List<Integer> list: clustered) {
			System.out.println("\r\n========clustered " + list.size() + " =================== ");
			for( Integer idx: list ) {
				System.out.print(dataset.get(idx)+" \n ");
			}
		}
		
	}

	private List<Integer>[] nearestIds(List<Vector<Object>> dataset,
			Vector<Object>[] centroids) {
		int clustered = centroids.length;
		List<Integer>[] clusteredDataSet = new ArrayList[clustered];
		for( int i=0 ; i<clustered ; i++ ){
			clusteredDataSet[i] =  new ArrayList<Integer> ();
		}
		
		for( int i=0 ; i<dataset.size() ; i++ ) {
			int nearClusterId = nearestCluster( dataset.get(i), centroids );
			clusteredDataSet[nearClusterId].add(i);
		}
		
		return clusteredDataSet;
	}
	
	int nearestCluster(Vector<Object> item, Vector<Object>[]  centroids ) {
		double distance=0, tmp=0;
		int index=0, pos=0;
		for(Vector<Object> center: centroids )		
		{
			tmp=distance(item, center);
			if(tmp>distance) {
				distance=tmp;
				pos=index;
			}
			index++;
		}
		return pos;
	}

	private double distance(Vector<Object> data, Vector<Object> center) {
		double distance = DistanceMeasure.measureCosine(data, center);
		return distance;
	}
	
	Vector<Object>[] firstCentroid(List<Vector<Object>> ds, int cnt) {
		Vector<Object>[] cenriods = new Vector[cnt];
		int n = (int) Math.floor(ds.size() / (cnt+2));
		int index=0;
		for( int i=n ; i<ds.size() ; i+=n ) {
			if( index>=cnt )	break;
			cenriods[index] = ds.get(i);
			index++;
		}
		return cenriods;
	}
}
