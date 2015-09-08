package tr.com.agem.java.mapper;

import tr.com.agem.core.adaptor.IAryaMappedRequest;

public class AryaRestMappedRequest implements IAryaMappedRequest {
	
	private String actionURL;

	public String getActionURL() {
		return actionURL;
	}

	public void setActionURL(String actionURL) {
		this.actionURL = actionURL;
	}

}
