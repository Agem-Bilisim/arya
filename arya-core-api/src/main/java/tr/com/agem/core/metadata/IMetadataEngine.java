package tr.com.agem.core.metadata;

import java.util.Collection;
import java.util.List;

import tr.com.agem.core.metadata.model.IMetadata;

public interface IMetadataEngine {
	
	IMetadata findMetadata(String applicationName, Long metaDataId);
	
	void saveMetadata(IMetadata metaData); // TODO ??

	IMetadata findWithNameAsXML(String applicationName, String viewName);

	IMetadata findWithNameAsJSON(String applicationName, String viewName);

	List<String> getAttributes(IMetadata metaData);
	
	void setAttributes(IMetadata metadata, List<Collection<?>> attributes);
}
