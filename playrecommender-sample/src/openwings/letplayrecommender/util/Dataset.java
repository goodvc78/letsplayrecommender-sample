package openwings.letplayrecommender.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class Dataset {
	public static Vector<Object> toVector(String line, String delimitor, int dimention, int titleIndex) {
		Vector<Object> v  = new Vector<Object>();
		String[] fields = line.split("["+delimitor+"]");

		try {
			 for( int i=0 ; i<dimention ; i++ ) {
				 if( i == titleIndex)
					 continue;
				 if( fields[i].isEmpty() )
					 v.add( new Double(0));
				 else 
					 v.add( new Double( Math.round((Double.parseDouble(fields[i])/60))) );
				 
			 }
			v.add( fields[titleIndex] );
		} catch (Exception e) {
			return null;
		}
		if( v.size() != dimention )
			return null;
		return v;
	}
	
	public static List<Vector<Object>> toDataset(String path, String delimitor, int dimention, int titleIndex) throws IOException {

		List<Vector<Object>> dataset = new ArrayList<Vector<Object>> ();
		BufferedReader br = new BufferedReader(new FileReader(path));
        String line;
        while((line = br.readLine()) != null) {
        	Vector data = toVector(line, delimitor, dimention, titleIndex );
        	if( null==data )
        		continue;
        	dataset.add(data);
        	System.out.println(data.toString());
        }
        br.close();
        return dataset;
	}
}
