package tr.com.agem.arya.metadata.generator;

import java.util.jar.JarFile;
import java.util.logging.Level;
import java.util.logging.Logger;

import tr.com.agem.arya.metadata.cursor.TraverseDirectories;
import tr.com.agem.arya.metadata.util.MetadataGeneratorUtil;

public class Main {
	
	private static final Logger logger = Logger.getLogger(Main.class.getName());

	public static void main(String[] args) {
		new Main();
	}

	public Main() {

		JarFile jarFile = null;

		try {

			jarFile = MetadataGeneratorUtil.getInstance().findJarFile();
			logger.log(Level.INFO, "Jar File: {0}", jarFile.getName());
			
			new TraverseDirectories(jarFile).traverse();

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				jarFile.close();
			} catch (Exception e) {
			}
		}

	}

}
