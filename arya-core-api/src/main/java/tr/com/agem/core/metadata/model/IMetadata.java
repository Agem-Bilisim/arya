package tr.com.agem.core.metadata.model;

public interface IMetadata {
	Long getId();
	String getApplicationName();
	String getViewName();
	String getMetadata();
	MetadataTypes getMetadataType();
}
