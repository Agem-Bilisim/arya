package tr.com.agem.core;

import tr.com.agem.core.gateway.model.IAryaActionRequest;

public interface IAryaAppAdaptor {
	
	String processRequest(IAryaActionRequest request);

}
