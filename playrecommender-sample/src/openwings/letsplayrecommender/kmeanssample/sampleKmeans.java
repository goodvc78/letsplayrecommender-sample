package openwings.letsplayrecommender.kmeanssample;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import openwings.letsplayrecommender.distance.DistanceMeasure;

public class sampleKmeans {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		if( args.length > 1)
			System.out.println(" at least 1 argument!!");
		
		new sampleKmeans().mainLoop(args[0]);
	}

	static SimpleDateFormat dateFormatter = new SimpleDateFormat ( "dd-MMM-yyyy" );
	
	private void mainLoop(String path) throws IOException {
	
		List<Vector<Object>> dataset = makeDataSet(path);
		
		List<Integer>[] clustered = clusterKmeans(dataset, 10);
		
	}
	
	private List<Integer>[] clusterKmeans(List<Vector<Object>> dataset, int cnt) {
		
		Vector<Object>[] centroids = firstCentroid( dataset, cnt );
		List<Integer>[] clusteredDataSet = nearestIds( dataset,centroids   );
		printClusteredTitle(clusteredDataSet, dataset );
				
		for( int i=0 ; i<20 ; i++ ) {
			System.out.println("\r\n\r\n"+(i+1)+"th clustering==========================");
			centroids = newCentroid( clusteredDataSet, dataset   );
			clusteredDataSet = nearestIds( dataset,centroids   );
			printClusteredTitle(clusteredDataSet, dataset );
		}
		return null; 
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
				System.out.print(dataset.get(idx).get(20)+" | ");
			}
		}
		
	}

	private List<Integer>[] nearestIds(List<Vector<Object>> dataset,
			Vector<Object>[] centroids) {

		/// 
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

	private double consineDistance(Vector<Object> data, Vector<Object> center) {
		List<Object> subA = data.subList(0, data.size()-1);
		List<Object> subB = data.subList(0, center.size()-1);
		
		return 0;
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
	
	Vector<Object> toVector(String line) {
		Vector<Object> v  = new Vector<Object>();
		String[] fields = line.split("[|]");
		if(fields.length != 24 )
			return null;
		try {
			String year = fields[2].substring(fields[2].length()-4, fields[2].length());
			year = "0";
			 v.add( new Double(Double.parseDouble(year)) );
			 for( int i=5 ; i<=23 ; i++ ) 
				 v.add(Double.parseDouble(fields[i]));
			v.add( new String(fields[1]) );
		} catch (Exception e) {
			return null;
		}
		if( v.size() != 21 )
			return null;
		return v;
	}
	
	List<Vector<Object>> makeDataSet(String path) throws IOException {

		List<Vector<Object>> dataset = new ArrayList<Vector<Object>> ();
		
		BufferedReader br = new BufferedReader(new FileReader(path));
        String line;
        while((line = br.readLine()) != null) {
        	Vector data = toVector(line);
        	if( null==data || data.size() != 21 )
        		continue;
        	dataset.add(data);
        	System.out.println(data.toString());
        }
        
        br.close();
        
        return dataset;
	}
	
}
