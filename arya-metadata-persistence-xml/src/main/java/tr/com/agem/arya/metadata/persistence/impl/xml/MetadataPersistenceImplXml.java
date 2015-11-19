package tr.com.agem.arya.metadata.persistence.impl.xml;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;

import tr.com.agem.core.metadata.model.IMetadata;
import tr.com.agem.core.metadata.model.MetadataTypes;
import tr.com.agem.core.metadata.persistence.IMetadataPersistence;
import tr.com.agem.core.property.reader.PropertyReader;
import tr.com.agem.core.utils.AryaUtils;

public class MetadataPersistenceImplXml implements IMetadataPersistence {

	private static final Logger logger = Logger.getLogger(MetadataPersistenceImplXml.class.getName());

	@Override
	public void saveMetadata(IMetadata metadata) {
		// TODO Auto-generated method stub
	}

	@Override
	public IMetadata findMetadata(Long metadataId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateMetadata(IMetadata metadata) {
		// TODO Auto-generated method stub
	}

	@Override
	public void deleteMetadata(Long metadataId) {
		// TODO Auto-generated method stub
	}

	private IMetadata findWithName(String appName, String viewName) {
		
		String xmlFilePath = findXMLFilePath(appName, viewName);

		System.out.println("file path-----"+xmlFilePath);
		
		logger.log(Level.INFO, "XML File Path: {0}", xmlFilePath);

		InputStream xmlStream = getClass().getClassLoader().getResourceAsStream(xmlFilePath);

		try {

			BufferedReader reader = new BufferedReader(new InputStreamReader(xmlStream, StandardCharsets.UTF_8));
			String line = null;
			StringBuilder metadataStr = new StringBuilder();
			String ls = System.getProperty("line.separator");

			while ((line = reader.readLine()) != null) {
				metadataStr.append(line);
				metadataStr.append(ls);
			}

			MetadataXml metadata = new MetadataXml();
			metadata.setApplicationName(appName);
			metadata.setFormName(viewName);
			metadata.setMetadataType(MetadataTypes.XML);
			metadata.setMetadata(metadataStr.toString());

			reader.close();
			
			logger.log(Level.FINE, "Metadata: {0}", metadataStr.toString());
			
			return metadata;

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}
	
	static final Map<String, String> ACTION_META = new HashMap<String, String>();
	static {
		ACTION_META.put("empty", "edit");
		ACTION_META.put("select", "edit");
		ACTION_META.put("delete", "edit");
		ACTION_META.put("add", "edit");
	}

	private String findXMLFilePath(String appName, String viewName) {
		String tmp = (viewName == null || viewName.isEmpty()) ? PropertyReader.property("master.metadata.file") : viewName;
		for (String key: ACTION_META.keySet()) {
			tmp = tmp.replaceAll(key, ACTION_META.get(key));
		}
		String d[] = tmp.split("\\.");
		String path = AryaUtils.join(File.separator, d);
		return appName + File.separator + path + "." + PropertyReader.property("metadata.file.extension");
	}

	@Override
	public IMetadata findWithNameAsXML(String appName, String viewName) {
		return findWithName(appName, viewName);
	}

	@Override
	public IMetadata findWithNameAsJSON(String appName, String viewName) {
		MetadataXml metadata = (MetadataXml) findWithName(appName, viewName);
		metadata.setMetadataType(MetadataTypes.JSON);
		try {
			metadata.setMetadata(new ObjectMapper().writeValueAsString(metadata.getMetadata()));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return metadata;
	}

}
