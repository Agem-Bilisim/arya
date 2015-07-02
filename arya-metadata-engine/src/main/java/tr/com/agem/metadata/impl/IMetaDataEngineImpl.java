package tr.com.agem.metadata.impl;

import tr.com.agem.core.metadata.IMetaDataEngine;
import tr.com.agem.core.metadata.model.IMetaData;
import tr.com.agem.core.metadata.persistence.IMetaDataPersistence;

public class IMetaDataEngineImpl implements IMetaDataEngine{

	private IMetaDataPersistence persistence;

	public void setPersistence(IMetaDataPersistence persistence) {
		this.persistence = persistence;
	}

	public IMetaData findMetaData(Long metaDataId) {
		return persistence.findMetaData(metaDataId);
	}


}
