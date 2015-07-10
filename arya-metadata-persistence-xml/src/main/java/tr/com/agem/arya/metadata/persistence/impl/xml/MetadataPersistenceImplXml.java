package tr.com.agem.arya.metadata.persistence.impl.xml;

import java.io.File;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import tr.com.agem.arya.metadata.zul.impl.ObjectFactory;
import tr.com.agem.arya.metadata.zul.impl.WindowType;
import tr.com.agem.arya.metadata.zul.impl.ZkType;
import tr.com.agem.core.metadata.model.IMetaData;
import tr.com.agem.core.metadata.persistence.IMetaDataPersistence;

import com.google.gson.Gson;

public class MetadataPersistenceImplXml implements IMetaDataPersistence {

	private static final String MASTERMODULE = "master";
	private static final String MASTERFORM = "master";
	private static final String XMLPATH = null;

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

	@SuppressWarnings("unchecked")
	@Override
	public IMetaData findWithName(String appName, String moduleName,
			String formName) {
		String xmlFileName;
		if (moduleName != null && formName != null)
			xmlFileName = appName + "/" + moduleName + "/" + formName
					+ ".xml";
		else if (formName != null)
			xmlFileName =  appName + "/" + MASTERMODULE + "/" + formName
					+ ".xml";
		else 
			xmlFileName =  appName + "/" + MASTERMODULE + "/" + MASTERFORM
					+ ".xml";
		
		xmlFileName = XMLPATH + xmlFileName;
		
		File file = new File(xmlFileName);
		
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(WindowType.class,
					ObjectFactory.class);

			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

			ZkType zk = ((JAXBElement<ZkType>) ((JAXBElement<ZkType>) jaxbUnmarshaller
					.unmarshal(file))).getValue();

			List<Object> comp = zk.getContent();

			for (Object o : comp) {
				if (o instanceof JAXBElement) {
					if (((JAXBElement<?>) o).getName().getLocalPart()
							.equalsIgnoreCase("window")) {
						Gson gson = new Gson();

						String json = gson.toJson(((JAXBElement<?>) o)
								.getValue());
						
						MetaDataXml metaData = new MetaDataXml();
						metaData.setApplicationName(appName);
						metaData.setModuleName(moduleName);
						metaData.setFormName(formName);
						metaData.setMetaData(json);
						metaData.setId(Long.MIN_VALUE);
						
						return metaData;
					}
				}
			}

		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
