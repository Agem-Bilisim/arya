package tr.com.agem.metadata.persistence.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import tr.com.agem.core.metadata.model.IMetaData;

@Entity
@Table(name = "METADATA")
public class MetaDataImpl implements IMetaData {

	@Id
	@GeneratedValue
	private Long id;

	private String applicationName;

	private String moduleName;

	private String formName;

	private String metaDataType;
	
	private String metaData;

	public MetaDataImpl(IMetaData metaData) {
		this.id = metaData.getId();
		this.metaData = metaData.getMetaData();
	}

	public MetaDataImpl() {
	}

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
