package tr.com.agem.java.adaptor;

import tr.com.agem.core.adaptor.AryaApplicationAdaptor;
import tr.com.agem.core.adaptor.IAryaAdaptorResponse;
import tr.com.agem.core.gateway.model.IAryaRequest;

public class AryaSampleAdaptor extends AryaApplicationAdaptor {

	@Override
	public IAryaAdaptorResponse processRequest(IAryaRequest request) {
		AryaAdaptorResponse returnVal = (AryaAdaptorResponse) getMapper().map(
				request, getDataSource(), getConverter());
		return returnVal;
	}

}
