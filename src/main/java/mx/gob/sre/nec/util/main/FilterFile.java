package mx.gob.sre.nec.util.main;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.List;

public class FilterFile implements FilenameFilter {

	void filenameFilter() {
	}

	public List<File> listFilesForFolder(final File folder) {
		
		List<File> archivos=new ArrayList<File>();
		
		for (final File fileEntry : folder.listFiles()) {
			if (fileEntry.isDirectory()) {
				listFilesForFolder(fileEntry);
			} else {
				archivos.add(fileEntry);
			}
		}
		return archivos;
	}
	
	
	public List<File> listFilesForFolder(final File folder,String extension) {
		
		List<File> archivos=new ArrayList<File>();
		
		for (final File fileEntry : folder.listFiles()) {
			if (fileEntry.isDirectory()) {
				listFilesForFolder(fileEntry);
			} else {
				if(accept(fileEntry,extension)){
					archivos.add(fileEntry);
				}
			}
		}
		return archivos;
	}

	public boolean accept(File dir, String name) {
		if (dir.getAbsolutePath().lastIndexOf('.') > 0) {
			int lastIndex = dir.getName().lastIndexOf('.');
			String str = dir.getName().substring(lastIndex);
			if (str.equals(name)) {
				return true;
			}
		}
		return false;
	}

}
