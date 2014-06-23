package openwings.letsplayrecommender.sample.similarfiles;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.io.FileUtils;


public class FindSimilarFilesMain {

	public static void main(String[] args) throws IOException  {
		
		new FindSimilarFilesMain().mainLoog();
		
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
		File dir = new File("D:\\download");
		Collection<File> list = FileUtils.listFiles(dir, null, true);
		for( File f : list ) {
			System.out.println(f.getName() + ", "+ f.length()/1024 + "K");
			
		}
	}
}
