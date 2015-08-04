package tr.com.agem.metadata.impl;

import tr.com.agem.core.metadata.IMetaDataEngine;
import tr.com.agem.core.metadata.model.IMetaData;
import tr.com.agem.core.metadata.persistence.IMetaDataPersistence;

public class MetaDataEngineImpl implements IMetaDataEngine{

	private IMetaDataPersistence persistence;

	public void setPersistence(IMetaDataPersistence persistence) {
		this.persistence = persistence;
	}

	public void saveMetaData(IMetaData metaData) {
		persistence.saveMetaData(metaData);;
	}

	public IMetaData findWithNameAsXML(String appName, String moduleName, String formName) {
		return persistence.findWithNameAsXML(appName, moduleName, formName);
	}

	public IMetaData findWithNameAsJSON(String appName, String moduleName, String formName) {
		return persistence.findWithNameAsJSON(appName, moduleName, formName);
	}

	@Override
	public IMetaData findMetaData(String applicationName, Long metaDataId) {
		return persistence.findMetaData(metaDataId);
	}

	@Override
	public IMetaData findWithNameAsXML(String applicationName, String viewName) {
		return null;
	}

	@Override
	public IMetaData findWithNameAsJSON(String applicationName, String viewName) {
		return null;
	}

}
