package tr.com.agem.core.metadata;

import tr.com.agem.core.metadata.model.IMetaData;

public interface IMetaDataEngine {
	
	IMetaData findMetaData(Long metaDataId);
	
	void saveMetaData(IMetaData metaData);
	
	IMetaData findWithNameAsXML(String appName, String moduleName, String formName);

	IMetaData findWithNameAsJSON(String appName, String moduleName, String formName);

}
