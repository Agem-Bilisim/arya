package tr.com.agem.java.adaptor;

import tr.com.agem.core.adaptor.AryaApplicationAdaptor;
import tr.com.agem.core.adaptor.IAryaAdaptorResponse;
import tr.com.agem.core.gateway.model.IAryaRequest;

public class AryaSampleAdaptor extends AryaApplicationAdaptor{

	public IAryaAdaptorResponse processRequest(IAryaRequest request) 
	{
		AryaAdaptorResponse returnVal = new AryaAdaptorResponse();
		String username = "";
		try {
			username = (String) request.getParams().get("username");
			returnVal.setData("{message:'Merhaba " + username + "'}");
			returnVal.setViewName("helloView");
		} catch (Exception e) {
		}
		return returnVal;
	}

}
