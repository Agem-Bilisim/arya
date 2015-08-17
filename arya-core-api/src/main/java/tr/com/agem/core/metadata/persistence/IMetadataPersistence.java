package tr.com.agem.core.metadata.persistence;

import tr.com.agem.core.metadata.model.IMetadata;

public interface IMetadataPersistence {

	void saveMetadata(IMetadata metadata);
	
	IMetadata findMetadata(Long metadataId);
	
	void updateMetadata(IMetadata metadata);
	
	void deleteMetadata(Long metadataId);
	
	IMetadata findWithNameAsXML(String appName, String viewName);

	IMetadata findWithNameAsJSON(String appName, String viewName);
	
}
