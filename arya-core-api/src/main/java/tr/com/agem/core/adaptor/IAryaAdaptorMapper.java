package tr.com.agem.core.adaptor;

import java.util.Map;

import tr.com.agem.core.gateway.model.IAryaRequest;

public interface IAryaAdaptorMapper 
{
	Object map(IAryaRequest request);
	
	Map<String, Object> getActionMap();
	Map<String, String> getActionViewMap();
}
