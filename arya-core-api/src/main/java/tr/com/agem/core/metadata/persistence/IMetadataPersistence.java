package tr.com.agem.core.metadata.persistence;

import tr.com.agem.core.metadata.model.IMetadata;

public interface IMetadataPersistence {

	void saveMetadata(String appName, IMetadata metadata);
	
	IMetadata findMetadata(String appName, Long metadataId);

	IMetadata findMetadata(String appName, String viewName);
	
	void updateMetadata(String appName, IMetadata metadata);
	
	void deleteMetadata(String appName, Long metadataId);	
}
