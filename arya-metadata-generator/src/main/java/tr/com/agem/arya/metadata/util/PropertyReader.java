package tr.com.agem.arya.metadata.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import tr.com.agem.core.utils.AryaUtils;

public class PropertyReader {

	private static HashMap<String, String> PROPERTIES = null;
	private static Logger logger = Logger.getLogger(PropertyReader.class.getName());

	public static void readProperties() {
		PROPERTIES = new HashMap<String, String>();
		InputStream inputStream = null;
		try {
			Properties properties = new Properties();
			inputStream = PropertyReader.class.getResourceAsStream("/config.properties");
			properties.load(inputStream);
			if (inputStream == null) {
				logger.warning("Properties dosyası bulunamadı. Dosya : config.properties");
				return;
			}
			properties.load(inputStream);
			Enumeration<Object> keySet = properties.keys();
			while (keySet.hasMoreElements()) {
				Object keyObject = keySet.nextElement();
				PROPERTIES.put(keyObject.toString(), properties.getProperty(keyObject.toString()));
			}

			for (Object key : properties.keySet()) {
				logger.info(key + "=" + PROPERTIES.get(key.toString()));
			}
		} catch (IOException e) {
			logger.log(Level.SEVERE, "Properties dosyası yüklenemedi.", e);
			AryaUtils.logException(null,e);
		} finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					AryaUtils.logException(null,e);
				}
			}
		}
	}

	public static String property(String key) {
		if (PROPERTIES == null)
			readProperties();
		return PROPERTIES.get(key);
	}

}
