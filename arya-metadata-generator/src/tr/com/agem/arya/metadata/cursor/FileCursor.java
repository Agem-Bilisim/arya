package tr.com.agem.arya.metadata.cursor;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import tr.com.agem.arya.metadata.builder.MetadataBuilder;
import tr.com.agem.arya.metadata.util.MetadataGeneratorUtil;

public class FileCursor {

	MetadataBuilder builder = null;

	public FileCursor(MetadataBuilder builder) {
		this.builder = builder;
	}

	public boolean execute(JarFile jarFile, JarEntry jarEntry) {

		boolean isFirst = true;
		StringBuilder metadata = new StringBuilder();

		try {

			// Read jsp file as String
			String jsp = MetadataGeneratorUtil.getInstance().readFile(jarFile, jarEntry);

			if (!MetadataGeneratorUtil.getInstance().isEmpty(jsp)) {

				Pattern pattern = Pattern.compile("<agem:form(.*)(/>|</agem:form>)",
						Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
				Matcher matcher = pattern.matcher(jsp);

				// Get agem:form and display:table elements
				// Iterate over properties and generate metadata XML!
				while (matcher.find()) {

					String agemFormTag = matcher.group(0);
					String[] properties = MetadataGeneratorUtil.getInstance().getFormProperties(agemFormTag);
					String className = MetadataGeneratorUtil.getInstance().getFormClassName(agemFormTag);
					String actionName = MetadataGeneratorUtil.getInstance().getFormActionName(agemFormTag);

					if (properties.length > 0 && !MetadataGeneratorUtil.getInstance().isEmpty(className)
							&& !MetadataGeneratorUtil.getInstance().isEmpty(actionName)) {
						metadata.append(builder.start(Class.forName(className), properties, actionName, isFirst));
						isFirst = false;
					}
				}

				if (metadata.length() > 0) {
					// End metadata XML
					metadata.append(builder.end());

					// Generate file and its parent directories if not exists,
					// then write to it.
					File result = MetadataGeneratorUtil.getInstance().generateFileDir(jarEntry.getName());
					FileWriter fileWriter = new FileWriter(result);
					BufferedWriter bufferWriter = new BufferedWriter(fileWriter);
					bufferWriter.write(metadata.toString());
					bufferWriter.flush();
					bufferWriter.close();
				}

			}

		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		return false;
	}

}
