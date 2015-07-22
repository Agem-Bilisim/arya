package tr.com.agem.arya.metadata.persistence.impl.xml;

import tr.com.agem.core.metadata.model.IMetaData;

public class MetaDataXml implements IMetaData {

	private Long id;

	private String applicationName;

	private String moduleName;

	private String formName;

	private String metaDataType;
	
	private String metaData;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getApplicationName() {
		return applicationName;
	}

	public void setApplicationName(String applicationName) {
		this.applicationName = applicationName;
	}

	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	public String getFormName() {
		return formName;
	}

	public void setFormName(String formName) {
		this.formName = formName;
	}

	public String getMetaData() {
		return metaData;
	}

	public void setMetaData(String metaData) {
		this.metaData = metaData;
	}

	public String getMetaDataType() {
		return metaDataType;
	}

	public void setMetaDataType(String metaDataType) {
		this.metaDataType = metaDataType;
	}
}
