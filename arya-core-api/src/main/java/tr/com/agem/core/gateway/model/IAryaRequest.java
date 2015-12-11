package tr.com.agem.core.gateway.model;

import java.util.Map;

public interface IAryaRequest {
	Map<String, Object> getParams();
	String getAttributeName();
	String getAttributeValue();
	RequestTypes getRequestType();
	String getAction();
	String toJSON();
}
