package tr.com.agem.java.mapper;

import tr.com.agem.core.adaptor.IAryaMappedRequest;

public class AryaRestMappedRequest implements IAryaMappedRequest {
	
	private String actionURL;
	
	private String viewName;

	public String getActionURL() {
		return actionURL;
	}

	public void setActionURL(String actionURL) {
		this.actionURL = actionURL;
	}

	public String getViewName() {
		return viewName;
	}

	public void setViewName(String viewName) {
		this.viewName = viewName;
	}
	
}
