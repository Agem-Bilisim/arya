package tr.com.agem.arya.metadata.generator;

import java.util.jar.JarFile;

import tr.com.agem.arya.metadata.cursor.TraverseDirectories;
import tr.com.agem.arya.metadata.util.MetadataGeneratorUtil;

public class Main {

	public static void main(String[] args) {
		new Main();
	}

	public Main() {

		JarFile jarFile = null;

		try {

			jarFile = MetadataGeneratorUtil.getInstance().findJarFile();
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
