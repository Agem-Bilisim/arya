package tr.com.agem.metadata.persistence.impl;

import tr.com.agem.core.metadata.model.IMetadata;
import tr.com.agem.core.metadata.persistence.IMetadataPersistence;
import tr.com.agem.metadata.persistence.dao.MetadataDao;
import tr.com.agem.metadata.persistence.model.MetadataImpl;

public class MetadataPersistenceImpl implements IMetadataPersistence {

	private MetadataDao metadataDao;

	public void setMetadataDao(MetadataDao metadataDao) {
		this.metadataDao = metadataDao;
	}

	public void saveMetadata(String appName, IMetadata metadata) {
		metadataDao.save(appName, new MetadataImpl(metadata));
	}

	public IMetadata findMetadata(String appName, Long metadataId) {
		return metadataDao.find(appName, metadataId);
	}

	public void updateMetadata(String appName, IMetadata metadata) {
		metadataDao.update(appName, new MetadataImpl(metadata));
	}

	public void deleteMetadata(String appName, Long metadataId) {
		metadataDao.delete(appName, metadataId);
	}

	public IMetadata findMetadata(String appName, String viewName) {
		return metadataDao.find(appName, viewName);
	}
}
