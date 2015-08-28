package tr.com.agem.arya.metadata.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Locale;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import tr.com.agem.core.utils.AryaUtils;

public class MetadataGeneratorUtil {
	
	private static final Logger logger = Logger.getLogger(MetadataGeneratorUtil.class.getName());
	
	private static MetadataGeneratorUtil instance = null;
	
	public static MetadataGeneratorUtil getInstance() {
		if (instance == null) {
			instance = new MetadataGeneratorUtil();
		}
		return instance;
	}
	
	private MetadataGeneratorUtil() {
	}

	public String readFile(JarFile jarFile, JarEntry jarEntry) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(jarFile.getInputStream(jarEntry), StandardCharsets.UTF_8));
		StringBuilder fileStr = new StringBuilder();
		String line;
		while ((line = reader.readLine()) != null) {
			fileStr.append(line);
		}
		reader.close();
		return fileStr.toString();
	}

	public String[] getFormProperties(String source) {
		Pattern pattern = Pattern.compile("properties=\"(.*?)\"", Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
		Matcher matcher = pattern.matcher(source);
		return matcher.find() ? AryaUtils.split(matcher.group(1), ", \n\r\t") : null;
	}

	public String getFormClassName(String source) {
		Pattern pattern = Pattern.compile("clazz=\"(.*?)\"");
		Matcher matcher = pattern.matcher(source);
		return matcher.find() ? matcher.group(1) : null;
	}

	public String getFormActionName(String source) {
		Pattern pattern = Pattern.compile("action=\"[a-zA-Z0-9/]*/([a-zA-Z]*).do\"");
		Matcher matcher = pattern.matcher(source);
		// genel/isci/add.do etc.
		return matcher.find() ? matcher.group(1) : null;
	}
	
	public File generateFileDir(String path) throws IOException {
		String resultFileDir = PropertyReader.property("xml.files.directory");
		String resultFileName = "";
		File resultFile = null;

		Pattern pattern = Pattern.compile("WEB-INF/([a-zA-Z0-9 /]*/)([a-zA-Z0-9]*.jsp)");
		Matcher matcher = pattern.matcher(path);

		if (matcher.find()) {
			if (!resultFileDir.endsWith("/"))
				resultFileDir += "/";
			resultFileDir += matcher.group(1);
			resultFileName = matcher.group(2).replace("jsp", "arya");
		}

		File resultDir = new File(resultFileDir);
		if (!resultDir.exists() && !resultDir.mkdirs()) {
			throw new RuntimeException("Cannot create file: " + resultFileDir);
		}

		if (AryaUtils.isNotEmpty(resultFileName)) {
			resultFile = new File(resultDir, resultFileName);
			if (!resultFile.exists()) {
				boolean isCreated = resultFile.createNewFile();
				logger.log(Level.FINE, "{0} created: {1}", new Object[]{ resultFileName, isCreated });
			}
		}

		return resultFile;
	}

	public Object generateWindowId(String actionName, String className) {
		String[] packages = className.split("\\.");
		return appendCamelCase(actionName, packages[packages.length-2]) + "Window";
	}

	public String appendCamelCase(String s1, String s2) {
		return s1.toLowerCase(Locale.ENGLISH) + s2.substring(0, 1).toUpperCase(Locale.ENGLISH) + s2.substring(1).toLowerCase(Locale.ENGLISH);
	}
	
	public boolean entryStartsWith(String entry, String[] paths) {
		for (String path : paths) {
			if (entry.startsWith(path)) {
				return true;
			}
		}
		return false;
	}
	
	// TODO find another way to find absolute path of a jar file easily?
	public JarFile findJarFile() throws IOException {
		String parent = new File(this.getClass().getClassLoader().getResource(PropertyReader.property("jar.file.prefix") + "/web.xml").getPath())
				.getParent();
		parent = parent.replaceAll("(!|file:|/" + PropertyReader.property("jar.file.prefix") + ")", "");
		return new JarFile(parent);
	}

}
