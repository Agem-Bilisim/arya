package tr.com.agem.core.metadata;

import tr.com.agem.core.metadata.model.IMetaData;

public interface IMetaDataEngine 
{
	
	IMetaData findMetaData(String applicationName, Long metaDataId);
	
	void saveMetaData(IMetaData metaData);
	
	IMetaData findWithNameAsXML(String applicationName, String moduleName, String formName);

	IMetaData findWithNameAsJSON(String applicationName, String moduleName, String formName);

	IMetaData findWithNameAsXML(String applicationName, String viewName);

	IMetaData findWithNameAsJSON(String applicationName, String viewName);

}
