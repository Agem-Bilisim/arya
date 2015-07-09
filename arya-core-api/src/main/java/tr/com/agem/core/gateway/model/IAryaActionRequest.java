package tr.com.agem.core.gateway.model;

import java.util.Map;

public interface IAryaActionRequest {
	
	Map<String, Object> getParams();
	String getWindow();
	String getActionClass();
	String getActionMethod();

}
