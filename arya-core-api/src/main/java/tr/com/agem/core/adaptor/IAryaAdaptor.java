package tr.com.agem.core.adaptor;

import tr.com.agem.core.gateway.model.IAryaRequest;

public interface IAryaAdaptor {
	public IAryaMapper getMapper();
	public IAryaConverter getConverter();
	public IAryaAdaptorResponse processRequest(IAryaRequest request);
}
