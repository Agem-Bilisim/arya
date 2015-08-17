package tr.com.agem.core.metadata.model;

public enum MetadataTypes {
	
	JSON("J"),
	XML("X");
	
	private final String type;
	MetadataTypes(String type) {
		this.type = type;
	}
	
	public String getType() {
		return this.type;
	}

}
