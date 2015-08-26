package tr.com.agem.arya.metadata.cursor;

import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import org.apache.commons.io.FilenameUtils;

import tr.com.agem.arya.metadata.builder.MetadataBuilder;
import tr.com.agem.arya.metadata.util.MetadataGeneratorUtil;
import tr.com.agem.arya.metadata.util.PropertyReader;

public class TraverseDirectories {

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
					&& FilenameUtils.isExtension(jarEntry.getName(), extensions)) {
				
				System.out.println("Entry: " + jarEntry.getName());
				
				new FileCursor(new MetadataBuilder()).execute(jarFile, jarEntry);

			}

		}

	}

}