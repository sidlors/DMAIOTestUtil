package mx.gob.sre.nec.util.main.test;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import mx.gob.sre.nec.util.main.App;


public class AppTest {
	
	private App app;
	private File filepath;

	@Before
	public void setUp() throws Exception {
		app=new App();
		filepath=new File("C:\\Img\\2016\\01\\19\\189\\12734496");
	}

	@After
	public void tearDown() throws Exception {
	}
	

	@Test
	public void testFilter() {
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
