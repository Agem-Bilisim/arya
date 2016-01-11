package tr.com.agem.core.gateway.model;

public interface IAryaResponse {
	void fromXMLString(String xmlString);
	String toString();
	String getView();
	String getData();
	Object getAttributes();
}
