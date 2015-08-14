package tr.com.agem.metadata.persistence.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import tr.com.agem.core.metadata.model.IMetadata;
import tr.com.agem.core.metadata.model.MetadataTypes;

@Entity
@Table(name = "METADATA")
public class MetadataImpl implements IMetadata {

	@Id
	@GeneratedValue
	private Long id;

	private String applicationName;

	private String moduleName;

	private String formName;

	private MetadataTypes metadataType;
	
	private String metadata;

	public MetadataImpl(IMetadata metadata) {
		this.id = metadata.getId();
		this.metadata = metadata.getMetadata();
	}

	public MetadataImpl() {
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

	public MetadataTypes getMetadataType() {
		return metadataType;
	}

	public void setMetadataType(MetadataTypes metadataType) {
		this.metadataType = metadataType;
	}

	public String getMetadata() {
		return metadata;
	}

	public void setMetadata(String metadata) {
		this.metadata = metadata;
	}

}
