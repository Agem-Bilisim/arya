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

	public void saveMetadata(IMetadata metadata) {
		persistence.saveMetadata(metadata);
	}

	public IMetadata findWithNameAsXML(String appName, String viewName) {
		return persistence.findWithNameAsXML(appName, viewName);
	}

	public IMetadata findWithNameAsJSON(String appName, String viewName) {
		return persistence.findWithNameAsJSON(appName, viewName);
	}

	@Override
	public IMetadata findMetadata(String applicationName, Long metadataId) {
		return persistence.findMetadata(metadataId);
	}

	@Override
	public List<String> getAttributes(IMetadata metaData) {
		return null;
	}

	@Override
	public void setAttributes(IMetadata metadata, List<Collection<?>> attributes) {
	}

}
