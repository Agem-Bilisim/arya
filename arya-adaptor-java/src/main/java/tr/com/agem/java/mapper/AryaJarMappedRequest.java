package tr.com.agem.java.mapper;

import tr.com.agem.core.adaptor.IAryaMappedRequest;

public class AryaJarMappedRequest implements IAryaMappedRequest {
	
	private String action;
	
	private String actionMethodName;
	
	private String serviceName;
	
	private String formName;
	
	private String path;

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getActionMethodName() {
		return actionMethodName;
	}

	public void setActionMethodName(String actionMethodName) {
		this.actionMethodName = actionMethodName;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public String getFormName() {
		return formName;
	}

	public void setFormName(String formName) {
		this.formName = formName;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

}
