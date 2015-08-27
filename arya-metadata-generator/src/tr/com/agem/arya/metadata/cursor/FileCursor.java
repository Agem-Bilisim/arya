package tr.com.agem.arya.metadata.cursor;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import tr.com.agem.arya.metadata.builder.MetadataBuilder;
import tr.com.agem.arya.metadata.util.MetadataGeneratorUtil;

public class FileCursor {

	private static final Logger logger = Logger.getLogger(FileCursor.class.getName());

	private static final Pattern formPattern = Pattern.compile("<agem:form(.*)(/>|</agem:form>)",
			Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
	private static final Pattern scriptPattern = Pattern.compile(
			"<script(?:[^>]*src=['\"]([^'\"]*)['\"][^>]*>|[^>]*>([^<]*)</script>)",
			Pattern.CASE_INSENSITIVE | Pattern.DOTALL);

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
			logger.log(Level.FINE, "Source file has been read successfully!");

			if (!MetadataGeneratorUtil.getInstance().isEmpty(jsp)) {
				
				// Find <agem:form>, iterate over its properties and generate metadata XML
				Matcher formMatcher = formPattern.matcher(jsp);
				while (formMatcher.find()) {

					String agemFormTag = formMatcher.group(0);
					String[] properties = MetadataGeneratorUtil.getInstance().getFormProperties(agemFormTag);
					String className = MetadataGeneratorUtil.getInstance().getFormClassName(agemFormTag);
					String actionName = MetadataGeneratorUtil.getInstance().getFormActionName(agemFormTag);

					if (properties.length > 0 && !MetadataGeneratorUtil.getInstance().isEmpty(className)
							&& !MetadataGeneratorUtil.getInstance().isEmpty(actionName)) {
						logger.log(Level.FINE, "Found agem:form element!");
						metadata.append(builder.start(Class.forName(className), properties, actionName, isFirst));
						isFirst = false;
					}
				}
				
				// Find <script> tags and append to metadata XML
				Matcher scriptMatcher = scriptPattern.matcher(jsp);
				while (scriptMatcher.find()) {
					
					String src = scriptMatcher.group(0);
					String script = scriptMatcher.group(1);
					
					// TODO append src as include attr, else append script as text to metadata
					
				}

				if (metadata.length() > 0) {

					metadata.append(builder.end());

					// Generate file and its parent directories if not exists,
					// then write to it.
					File result = MetadataGeneratorUtil.getInstance().generateFileDir(jarEntry.getName());
					logger.log(Level.INFO, "Target File: {0}", result.getName());
					FileWriter fileWriter = new FileWriter(result);
					BufferedWriter bufferWriter = new BufferedWriter(fileWriter);
					bufferWriter.write(metadata.toString());
					bufferWriter.flush();
					bufferWriter.close();
				}

			}

		} catch (IOException e) {
			logger.log(Level.SEVERE, e.toString(), e);
		} catch (ClassNotFoundException e) {
			logger.log(Level.SEVERE, e.toString(), e);
		}

		return false;
	}

}
