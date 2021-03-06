package tr.com.agem.arya.metadata.cursor;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import tr.com.agem.arya.metadata.builder.MetadataBuilder;
import tr.com.agem.arya.metadata.util.MetadataGeneratorUtil;
import tr.com.agem.core.utils.AryaUtils;

public class FileCursor {

	private static final Logger logger = Logger.getLogger(FileCursor.class.getName());

	private static final Pattern formPattern = Pattern.compile("<agem:form(.*)(/>|</agem:form>)",
			Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
	private static final Pattern scriptBodyPattern = Pattern.compile("<script[^>]*>([\\w\\W]*?)</script>",
			Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
	private static final Pattern scriptSrcPattern = Pattern.compile("<script.*?src=\"(.*?)\"",
			Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
	private static final Pattern tablePattern = Pattern.compile("<display:column([^>]*)>([\\w\\W]*?)</display:column>",
			Pattern.CASE_INSENSITIVE);

	MetadataBuilder builder = null;

	public FileCursor(MetadataBuilder viewBuilder) {
		this.builder = viewBuilder;
	}

	public void execute(JarFile jarFile, JarEntry jarEntry) {

		boolean isFirst = true;

		try {

			// Read JSP file as String
			String jsp = MetadataGeneratorUtil.getInstance().readFile(jarFile, jarEntry);
			logger.log(Level.FINE, "Source file has been read successfully!");

			if (AryaUtils.isNotEmpty(jsp)) {

				// Begin metadata file.
				builder.beginFile();

				//
				// Find <agem:form> tags, append to view metadata.
				//
				Matcher formMatcher = formPattern.matcher(jsp);
				while (formMatcher.find()) {

					String formTag = formMatcher.group(0);
					String[] properties = MetadataGeneratorUtil.getInstance().getFormProperties(formTag);
					String className = MetadataGeneratorUtil.getInstance().getFormClassName(formTag);
					String actionName = MetadataGeneratorUtil.getInstance().getFormActionName(formTag);

					if (properties.length > 0 && AryaUtils.isNotEmpty(className) && AryaUtils.isNotEmpty(actionName)) {

						logger.log(Level.FINE, "Found agem:form element!");

						Class<?> cls = Class.forName(className);

						if (isFirst) {
							builder.beginViewMetadata(actionName, cls.getName());
						}
						builder.appendViewBody(cls, properties);

						isFirst = false;
					}

				}
				//
				// Find <display:column> tag, append to view metadata.
				//
				Matcher tableMatcher = tablePattern.matcher(jsp);
				while (tableMatcher.find()) {

					String tableTag = tableMatcher.group(0);
					HashMap<String, String> properties = MetadataGeneratorUtil.getInstance()
							.getColumnProperties(tableTag);
					String requestURI = MetadataGeneratorUtil.getInstance().getRequestURI(jsp);

					if (properties != null && !properties.isEmpty() && requestURI != null && !requestURI.isEmpty()) {

						logger.log(Level.FINE, "Found display:column element!");

						if (isFirst) {
							builder.beginViewMetadata("list", requestURI.replaceAll("/", "."));
						}
						builder.appendViewBody(properties);

						isFirst = false;
					}

				}

				if (builder.isViewTagOpened()) {
					builder.endViewMetadata();
				}
				isFirst = true;

				//
				// Find <script> tags, append to script metadata.
				//
				Matcher scriptBodyMatcher = scriptBodyPattern.matcher(jsp);
				while (scriptBodyMatcher.find()) {

					String scriptBody = scriptBodyMatcher.group(1);

					if (AryaUtils.isNotEmpty(scriptBody)) {

						if (isFirst) {
							builder.beginScriptMetadata();
						}
						builder.appendScriptBody(scriptBody);

						isFirst = false;
					}

				}

				Matcher scriptSrcMatcher = scriptSrcPattern.matcher(jsp);
				while (scriptSrcMatcher.find()) {

					String scriptSrc = scriptSrcMatcher.group(1);

					if (AryaUtils.isNotEmpty(scriptSrc)) {
						builder.appendScriptSrc(scriptSrc);
					}

				}

				if (builder.isScriptTagOpened()) {
					builder.endScriptMetadata();
				}
				builder.endFile();

				// Generate metadata file and its parent directories if not
				// exists,
				// then write to it.
				File result = MetadataGeneratorUtil.getInstance().generateFileDir(jarEntry.getName());
				logger.log(Level.INFO, "Target File: {0}", result.getName());
				FileOutputStream fileStream = new FileOutputStream(result);
				BufferedWriter bufferWriter = new BufferedWriter(
						new OutputStreamWriter(fileStream, StandardCharsets.UTF_8));
				bufferWriter.write(builder.getMetadata());
				bufferWriter.flush();
				bufferWriter.close();

			}

		} catch (IOException | ClassNotFoundException e) {
			logger.log(Level.SEVERE, e.toString(), e);
		}

	}

}
