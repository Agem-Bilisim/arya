package tr.com.agem.java.adaptor;

import tr.com.agem.core.adaptor.AryaApplicationAdaptor;
import tr.com.agem.core.adaptor.IAryaAdaptorResponse;
import tr.com.agem.core.gateway.model.IAryaRequest;

public class AryaSampleAdaptor extends AryaApplicationAdaptor{

	public IAryaAdaptorResponse processRequest(IAryaRequest request) 
	{
		AryaAdaptorResponse returnVal = (AryaAdaptorResponse) getMapper().map(request);
		return returnVal;
	}

}
