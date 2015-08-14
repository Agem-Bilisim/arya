package tr.com.agem.arya.metadata.persistence.impl.xml;

import tr.com.agem.core.metadata.model.IMetadata;
import tr.com.agem.core.metadata.model.MetadataTypes;

public class MetadataXml implements IMetadata {

	private Long id;

	private String applicationName;

	private String formName;

	private MetadataTypes metadataType;
	
	private String metadata;

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
