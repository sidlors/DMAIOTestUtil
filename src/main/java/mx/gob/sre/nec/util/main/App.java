package mx.gob.sre.nec.util.main;

import java.io.File;
import java.io.OutputStream;
import java.util.List;

import org.apache.commons.cli.BasicParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.OptionGroup;
import org.apache.commons.cli.Options;

public class App {

	private FilterFile filter;
	private UnzipFile unziper;
	private RenameFile renamer;

	public static void main(String[] args) throws Exception {
		// String endPoint="http://localhost:7101/IndraSid/ws/Afiswww?WSDL";
		if (args == null || args.length < 1) {
			System.err.println("Argumento no recibido");
			System.exit(-1);
		}
        final int DEF_ACTOR=1;
        int               actor    = 0;  
        String            path   = null;     
        OutputStream      output  = null;  
        CommandLineParser parser  = null;  
        CommandLine       cmdLine = null;         
  
      ///////////////////////////////////////////////////////////////////////  
      // Fase 1: Configuramos las opciones de validación de entrada.  
      ///////////////////////////////////////////////////////////////////////  
              
        Options options = new Options();  
        options.addOption("path",     true,  "Directorio Origen");  
        options.addOption("actor",   true,  "Actor 1:Solicitante 2:Padre 3:Madre");  
        options.addOption("h",      "help", false, "Imprime el mensaje de ayuda");   
          
        // No pueden aparecen las dos opciones simultáneamente.  
        OptionGroup group =  new OptionGroup();  
        group.addOption(new Option("err",     "Salida estándar de errores"));  
        group.addOption(new Option("console", "Salida estándar"));  
        options.addOptionGroup(group);  
          
          
        try {  
              
          ///////////////////////////////////////////////////////////////////////  
          // Fase 2: Parseamos la entrada con la configuración establecida  
          ///////////////////////////////////////////////////////////////////////  
        
            parser  = new BasicParser();  
            cmdLine = parser.parse(options, args);  
              
          ///////////////////////////////////////////////////////////////////////  
          // Fase 3: Analizamos los resultados y realizamos las tareas pertinentes  
          ///////////////////////////////////////////////////////////////////////  
              
            // Si está la opcion de ayuda, la imprimimos y salimos.  
            if (cmdLine.hasOption("h")){    // No hace falta preguntar por el parámetro "help". Ambos son sinónimos  
                new HelpFormatter().printHelp(App.class.getCanonicalName(), options );  
                return;  
            }  
              
            // Si el usuario ha especificado el puerto lo leemos          
            if (cmdLine.hasOption("actor")){  
                actor = Integer.parseInt( cmdLine.getOptionValue("actor") );    
            } else {  
                actor = DEF_ACTOR;  
            }  
              
            // Leemos la dirección IP. Sino existe generamos un error pues es un parámetro requerido.  
            path =  cmdLine.getOptionValue("path");  
            if (path == null){  
                throw new org.apache.commons.cli.ParseException("La direccion path es requerida");  
            }  
              
            if (cmdLine.hasOption("console")){  
                output = System.out;  
            } else if (cmdLine.hasOption("err")){  
                output = System.err;  
            } else {  
                output = null;  
            }  
              

    		//String path = args[0];
    		final File folder = new File(path);
    		App app = new App();
    		app.run(folder);
    		//
    		// TODO Servicio que Desencripte zip y los deje en el mismo directorio
    		//
            System.out.println("OK");  
              
        } catch (org.apache.commons.cli.ParseException ex){  
            System.out.println(ex.getMessage());  
              
            new HelpFormatter().printHelp(App.class.getCanonicalName(), options );    // Error, imprimimos la ayuda  
        } catch (java.lang.NumberFormatException ex){  
            new HelpFormatter().printHelp(App.class.getCanonicalName(), options );    // Error, imprimimos la ayuda  
        }  
	}

	public void run(File folder) throws Exception {
		if (!folder.isDirectory()) {
			throw new Exception("El path no es un folder");
		}
		filter = new FilterFile();
		unziper = new UnzipFile();
		renamer=new RenameFile();
		System.out.println("Extrayendo archivos...");
		List<File> zipFiles = filter.listFilesForFolder(folder,".zip");
		unziper.unzipFiles(zipFiles);
		System.out.println("Renombrando archivos...");
		renamer.renameFiles(filter.listFilesForFolder(folder, ".wsq"));
		
	}

}
