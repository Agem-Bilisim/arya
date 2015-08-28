package tr.com.agem.arya.metadata.cursor;

import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.logging.Level;
import java.util.logging.Logger;

import tr.com.agem.arya.metadata.builder.MetadataBuilder;
import tr.com.agem.arya.metadata.util.MetadataGeneratorUtil;
import tr.com.agem.arya.metadata.util.PropertyReader;
import tr.com.agem.core.utils.AryaUtils;

public class TraverseDirectories {
	
	private static final Logger logger = Logger.getLogger(TraverseDirectories.class.getName());

	private JarFile jarFile;

	public TraverseDirectories(JarFile jarFile) {
		this.jarFile = jarFile;
	}

	public void traverse() {

		String[] paths = PropertyReader.property("traverse.directories").split(",");
		String[] extensions = PropertyReader.property("traverse.files.by.extensions").split(",");

		Enumeration<JarEntry> entries = jarFile.entries();
		while (entries.hasMoreElements()) {

			JarEntry jarEntry = entries.nextElement();

			if (MetadataGeneratorUtil.getInstance().entryStartsWith(jarEntry.getName(), paths)
					&& AryaUtils.isExtension(jarEntry.getName(), extensions)) {
				
				logger.log(Level.INFO, "\nSource File: {0}", jarEntry.getName());
				
				new FileCursor(new MetadataBuilder()).execute(jarFile, jarEntry);

			}

		}

	}

}
