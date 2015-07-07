package tr.com.agem.core.metadata.persistence;

import tr.com.agem.core.metadata.model.IMetaData;

public interface IMetaDataPersistence {

	void saveMetaData(IMetaData metaData);
	
	IMetaData findMetaData(Long metaDataId);
	
	void updateMetaData(IMetaData metaData);
	
	void deleteMetaData(Long metaDataId);
	
}