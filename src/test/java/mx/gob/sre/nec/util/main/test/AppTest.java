package mx.gob.sre.nec.util.main.test;

import static org.junit.Assert.*;

import java.io.File;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import mx.gob.sre.nec.util.main.App;
import mx.gob.sre.nec.util.main.FilterFile;
import mx.gob.sre.nec.util.main.RenameFile;


public class AppTest {
	
	private App app;
	private File filepath;
	private FilterFile filter;
	private RenameFile renamerFile;

	@Before
	public void setUp() throws Exception {
		app=new App();
		filepath=new File("/Users/sidlors/Documents/12734397");
		filter=new FilterFile();
		renamerFile=new RenameFile();
	}

	@After
	public void tearDown() throws Exception {
	}
	

	@Test
	public void testFilter() {
		
		List<File> filesWSQ=null;
		try {
			filesWSQ=filter.listFilesForFolder(filepath, ".wsq");
			renamerFile.renameFiles(filesWSQ);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testRenamerFile() {
		
		try {
			app.run(filepath);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

	@Ignore
	@Test(expected=java.lang.Exception.class)
	public void testFilterWrongPath() {
		filepath =new File("c:\\casdasfasdfasdfasdfa");
		try {
			app.run(filepath);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Ignore
	public void testUnziper() {
		try {
			app.run(filepath);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
