package tr.com.agem.metadata.persistence.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import tr.com.agem.core.metadata.model.IMetaData;

@Entity
@Table(name="METADATA")
public class MetaDataImpl implements IMetaData{
	
	@Id @GeneratedValue
	private Long id;
	
	private String applicationName;
	
	private String metaDataXml;
	
	public MetaDataImpl(IMetaData metaData) {
		this.id = metaData.getId();
		this.metaDataXml = metaData.getMetaDataXml();
	}
	
	public MetaDataImpl() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMetaDataXml() {
		return metaDataXml;
	}

	public void setMetaDataXml(String metaDataXml) {
		this.metaDataXml = metaDataXml;
	}

	public String getApplicationName() {
		return applicationName;
	}

	public void setApplicationName(String applicationName) {
		this.applicationName = applicationName;
	}

}
