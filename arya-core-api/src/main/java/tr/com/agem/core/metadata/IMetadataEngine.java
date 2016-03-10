package tr.com.agem.core.metadata;

import java.util.Collection;
import java.util.List;

import tr.com.agem.core.metadata.model.IMetadata;

public interface IMetadataEngine {
	
	IMetadata findMetadata(String applicationName, Long metaDataId);
	
	IMetadata findMetadata(String applicationName, String viewName);

	void saveMetadata(String applicationName, IMetadata metaData); 

	List<String> getAttributes(IMetadata metaData);
	void setAttributes(IMetadata metadata, List<Collection<?>> attributes);
}
