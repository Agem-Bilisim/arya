package tr.com.agem.metadata.persistence.impl;

import java.io.IOException;

import org.codehaus.jackson.map.ObjectMapper;

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

	private IMetaData findWithName(String appName, String moduleName,
			String formName) {
		return metaDataDao.findWithName(appName, moduleName, formName);
	}

	@Override
	public IMetaData findWithNameAsXML(String appName, String moduleName,
			String formName) {
		return findWithName(appName, moduleName, formName);
	}

	@Override
	public IMetaData findWithNameAsJSON(String appName, String moduleName,
			String formName) {
		MetaDataImpl metadata = (MetaDataImpl) findWithName(appName,
				moduleName, formName);
		metadata.setMetaDataType("JSON");
		try {
			metadata.setMetaData(new ObjectMapper().writeValueAsString(metadata
					.getMetaData()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return metadata;
	}
}
