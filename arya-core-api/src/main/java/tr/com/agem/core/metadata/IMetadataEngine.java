package tr.com.agem.core.metadata;

import tr.com.agem.core.metadata.model.IMetadata;

public interface IMetadataEngine 
{
	
	IMetadata findMetadata(String applicationName, Long metaDataId);
	
	void saveMetadata(IMetadata metaData); // TODO ??

	IMetadata findWithNameAsXML(String applicationName, String viewName);

	IMetadata findWithNameAsJSON(String applicationName, String viewName);

}
