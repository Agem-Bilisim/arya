package tr.com.agem.java.adaptor;

import tr.com.agem.core.adaptor.IAryaAppAdaptor;
import tr.com.agem.core.gateway.model.IAryaActionRequest;

public class AryaSampleAdaptor implements IAryaAppAdaptor{

	public String processRequest(IAryaActionRequest request) {
		String returnVal = "Makine soguk";
		String username = "";
		try {
			username = (String) request.getParams().get("username");
			returnVal = "Merhaba " + username;
		} catch (Exception e) {
		}
		return returnVal;
	}

}
