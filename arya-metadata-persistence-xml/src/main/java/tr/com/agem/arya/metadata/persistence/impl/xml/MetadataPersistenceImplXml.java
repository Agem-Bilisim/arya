package tr.com.agem.arya.metadata.persistence.impl.xml;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import tr.com.agem.arya.metadata.zul.impl.ObjectFactory;
import tr.com.agem.arya.metadata.zul.impl.WindowType;
import tr.com.agem.arya.metadata.zul.impl.ZkType;
import tr.com.agem.core.metadata.model.IMetaData;
import tr.com.agem.core.metadata.persistence.IMetaDataPersistence;

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
		
//		xmlFileName = XMLPATH + xmlFileName;
		
//		ClassLoader classLoader = getClass().getClassLoader();
//		File file = new File(classLoader.getResource(xmlFileName).getFile());
		File file = new File(xmlFileName);
		
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(ZkType.class,
					ObjectFactory.class);

			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

			ZkType zk = ((JAXBElement<ZkType>) ((JAXBElement<ZkType>) jaxbUnmarshaller
					.unmarshal(file))).getValue();

			List<Object> comp = zk.getContent();
			
			for (Object o : comp) {
				if (o instanceof JAXBElement) {
					if (((JAXBElement<?>) o).getName().getLocalPart().equalsIgnoreCase("window")) {
						WindowType window = (WindowType) ((JAXBElement<?>) o).getValue();
						List<Object> l = new ArrayList<Object>();
						for (Object oo : window.getContent()) {
							if (oo instanceof JAXBElement<?>) {
								//if (((JAXBElement<?>) oo).getName().getLocalPart().equalsIgnoreCase("label")) {
									l.add(((JAXBElement<?>) oo).getValue());
								//}
							}
						}
						//window.getContent().clear();
						//window.getContent().addAll(l);

						MetaDataXml metaData = new MetaDataXml();
						metaData.setApplicationName(appName);
						metaData.setModuleName(moduleName);
						metaData.setFormName(formName);
						try {
							metaData.setMetaData(new ObjectMapper().writeValueAsString(window));
						} catch (JsonGenerationException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (JsonMappingException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						metaData.setId(Long.MIN_VALUE);
						
						return metaData;
					}
				}
			}
			

//			for (Object o : comp) {
//				if (o instanceof JAXBElement) {
//					if (((JAXBElement<?>) o).getName().getLocalPart()
//							.equalsIgnoreCase("window")) {
//						Gson gson = new Gson();
//						
//						String json = gson.toJson(((JAXBElement<WindowType>) o).getValue());
//						
//						MetaDataXml metaData = new MetaDataXml();
//						metaData.setApplicationName(appName);
//						metaData.setModuleName(moduleName);
//						metaData.setFormName(formName);
//						metaData.setMetaData(json);
//						metaData.setId(Long.MIN_VALUE);
//						
//						return metaData;
//					}
//				}
//			}

		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
