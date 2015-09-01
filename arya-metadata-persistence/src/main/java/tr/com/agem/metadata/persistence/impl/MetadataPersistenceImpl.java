package tr.com.agem.metadata.persistence.impl;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

import tr.com.agem.core.metadata.model.IMetadata;
import tr.com.agem.core.metadata.model.MetadataTypes;
import tr.com.agem.core.metadata.persistence.IMetadataPersistence;
import tr.com.agem.metadata.persistence.dao.MetadataDao;
import tr.com.agem.metadata.persistence.model.MetadataImpl;

public class MetadataPersistenceImpl implements IMetadataPersistence {

	private MetadataDao metadataDao;

	public void setMetadataDao(MetadataDao metadataDao) {
		this.metadataDao = metadataDao;
	}

	public void saveMetadata(IMetadata metadata) {
		metadataDao.save(new MetadataImpl(metadata));
	}

	public IMetadata findMetadata(Long metadataId) {
		return metadataDao.find(metadataId);
	}

	public void updateMetadata(IMetadata metadata) {
		metadataDao.update(new MetadataImpl(metadata));
	}

	public void deleteMetadata(Long metadataId) {
		metadataDao.delete(metadataId);
	}

	private IMetadata findWithName(String appName, String viewName) {
		return metadataDao.findWithName(appName, viewName);
	}

	@Override
	public IMetadata findWithNameAsXML(String appName, String viewName) {
		return findWithName(appName, viewName);
	}

	@Override
	public IMetadata findWithNameAsJSON(String appName, String viewName) {
		MetadataImpl metadata = (MetadataImpl) findWithName(appName, viewName);
		metadata.setMetadataType(MetadataTypes.JSON);
		try {
			metadata.setMetadata(new ObjectMapper().writeValueAsString(metadata
					.getMetadata()));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return metadata;
	}
	
}
