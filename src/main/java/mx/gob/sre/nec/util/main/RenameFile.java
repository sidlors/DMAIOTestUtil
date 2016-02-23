package mx.gob.sre.nec.util.main;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RenameFile {

	private static final Map<String, String> myMap;

	static {
		myMap = new HashMap<String, String>();
		myMap.put("DerPulgar_1_1", "1");
		myMap.put("id_1_2", "2");
		myMap.put("DerMedio_1_3", "3");
		myMap.put("DerAnular_1_4", "4");
		myMap.put("DerMenique_1_5", "5");

		myMap.put("IzqPulgar_1_6", "6");
		myMap.put("ii_1_7", "7");
		myMap.put("IzqMedio_1_8", "8");
		myMap.put("IzqAnular_1_9", "9");
		myMap.put("IzqMenique_1_10", "10");

	}

	public void renameFiles(List<File> files) {

		// for(int i=0;i<files.size();i++){
		// //if()
		//
		// }
		for (File file : files) {
			for (String key : myMap.keySet()) {
				if (file.getName().contains(key)) {
					//
					if (file.renameTo(new File(file.getParentFile()+"/"+myMap.get(key)+".wsq"))) {
						System.out.println("rename " + file.getName() + " to " + myMap.get(key));	
					} else {
						System.err.println("Fail to rename " + file.getName() + " to " + myMap.get(key));
					}
				}
			}

			file.getName();
		}
	}

}
