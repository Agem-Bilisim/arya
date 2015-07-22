package tr.com.agem.arya.metadata.persistence.impl.xml;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.codehaus.jackson.map.ObjectMapper;

import tr.com.agem.core.metadata.model.IMetaData;
import tr.com.agem.core.metadata.persistence.IMetaDataPersistence;

public class MetadataPersistenceImplXml implements IMetaDataPersistence {

	private static final String MASTERMODULE = "master";
	private static final String MASTERFORM = "master";
//	private static final String XMLPATH = null;

	@Override
	public void saveMetaData(IMetaData metaData) {
		// TODO Auto-generated method stub

	}

	@Override
	public IMetaData findMetaData(Long metaDataId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateMetaData(IMetaData metaData) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteMetaData(Long metaDataId) {
		// TODO Auto-generated method stub

	}

	private IMetaData findWithName(String appName, String moduleName,
			String formName) {
		String xmlFileName;
		if (moduleName != null && formName != null)
			xmlFileName = appName + "/" + moduleName + "/" + formName + ".xml";
		else if (formName != null)
			xmlFileName = appName + "/" + MASTERMODULE + "/" + formName
					+ ".xml";
		else
			xmlFileName = appName + "/" + MASTERMODULE + "/" + MASTERFORM
					+ ".xml";

		File file = new File(xmlFileName);

		try {
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String line = null;
			StringBuilder stringBuilder = new StringBuilder();
			String ls = System.getProperty("line.separator");

			while ((line = reader.readLine()) != null) {
				stringBuilder.append(line);
				stringBuilder.append(ls);
			}

			MetaDataXml metaData = new MetaDataXml();
			metaData.setApplicationName(appName);
			metaData.setModuleName(moduleName);
			metaData.setFormName(formName);
			metaData.setMetaDataType("XML");
			metaData.setMetaData(stringBuilder.toString());

			reader.close();

			return metaData;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	
	@Override
	public IMetaData findWithNameAsXML(String appName, String moduleName,
			String formName) {
		return findWithName(appName, moduleName, formName);
	}

	@Override
	public IMetaData findWithNameAsJSON(String appName, String moduleName,
			String formName) {
		MetaDataXml metadata = (MetaDataXml) findWithName(appName, moduleName, formName);
		metadata.setMetaDataType("JSON");
		try {
			metadata.setMetaData(new ObjectMapper().writeValueAsString(metadata.getMetaData()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return metadata;
	}
}
