package tr.com.agem.core.context;

public enum AttributeScope {

	page("P"),
	request("R"),
	session("S"),
	application("A");
	
	private final String scope;
	
	AttributeScope(String scope) {
		this.scope = scope;
	}
	
	public String getScope() {
		return this.scope;
	}

}
