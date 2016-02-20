package mx.gob.sre.nec.util.main;

import java.io.File;
import java.util.List;

public class App {

	private FilterFile filter;
	private UnzipFile unziper;

	public static void main(String[] args) throws Exception {
		// String endPoint="http://localhost:7101/IndraSid/ws/Afiswww?WSDL";
		if (args == null || args.length < 1) {
			System.err.println("Argumento no recibido");
			System.exit(-1);
		}

		String path = args[0];
		final File folder = new File(path);
		App app = new App();
		app.run(folder);
		//
		// TODO Servicio que Desencripte zip y los deje en el mismo directorio
		//

	}

	public void run(File folder) throws Exception {
		if (!folder.isDirectory()) {
			throw new Exception("El path no es un folder");
		}
		filter = new FilterFile();
		unziper = new UnzipFile();
		List<File> zipFiles = filter.listFilesForFolder(folder,".zip");
		for (File file : zipFiles) {
			System.out.println(file.getName());
		}
		
		unziper.unzipFiles(zipFiles);
		
		System.out.println("\n\nTotal de archivos: "+zipFiles.size());
	}

}
