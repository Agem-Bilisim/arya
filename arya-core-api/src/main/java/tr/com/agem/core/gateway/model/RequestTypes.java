package tr.com.agem.core.gateway.model;

public enum RequestTypes {

	VIEW_ONLY("V"),
	DATA_ONLY("D"),
	ALL("A"),
	LOGIN("L"),
	NAVBAR("N");
	
	private final String type;
	RequestTypes(String type) {
		this.type = type;
	}
	
	public String getType() {
		return this.type;
	}

}
