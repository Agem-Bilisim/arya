package tr.com.agem.arya.metadata.impl;

import java.util.Collection;
import java.util.List;

import tr.com.agem.core.metadata.IMetadataEngine;
import tr.com.agem.core.metadata.model.IMetadata;
import tr.com.agem.core.metadata.persistence.IMetadataPersistence;

public class MetadataEngineImpl implements IMetadataEngine {

	private IMetadataPersistence persistence;

	public void setPersistence(IMetadataPersistence persistence) {
		this.persistence = persistence;
	}

	public void saveMetadata(String applicationName, IMetadata metadata) {
		persistence.saveMetadata(applicationName, metadata);
	}

	public IMetadata findMetadata(String applicationName, String viewName) {
		return persistence.findMetadata(applicationName, viewName);
	}

	@Override
	public IMetadata findMetadata(String applicationName, Long metadataId) {
		return persistence.findMetadata(applicationName, metadataId);
	}

	@Override
	public List<String> getAttributes(IMetadata metaData) {
		return null;
	}

	@Override
	public void setAttributes(IMetadata metadata, List<Collection<?>> attributes) {
	}

}
