package tr.com.agem.metadata.persistence.impl;

import tr.com.agem.core.metadata.model.IMetaData;
import tr.com.agem.core.metadata.persistence.IMetaDataPersistence;
import tr.com.agem.metadata.persistence.dao.MetaDataDao;
import tr.com.agem.metadata.persistence.model.MetaDataImpl;

public class MetaDataPersistenceImpl implements IMetaDataPersistence {

	private MetaDataDao metaDataDao;
	
	public void setMetaDataDao(MetaDataDao metaDataDao) {
		this.metaDataDao = metaDataDao;
	}

	
	
	public void saveMetaData(IMetaData metaData) {
		metaDataDao.save(new MetaDataImpl(metaData));
	}

	public IMetaData findMetaData(Long metaDataId) {
		return metaDataDao.find(metaDataId);
	}

	public void updateMetaData(IMetaData metaData) {
		metaDataDao.update(new MetaDataImpl(metaData));
	}

	public void deleteMetaData(Long metaDataId) {
		metaDataDao.delete(metaDataId);
	}
}
