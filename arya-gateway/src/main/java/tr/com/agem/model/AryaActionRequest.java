package tr.com.agem.model;

import java.io.Serializable;
import java.util.Map;

import tr.com.agem.core.gateway.model.IAryaActionRequest;

public class AryaActionRequest implements IAryaActionRequest,Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Map<String,Object> params;
	private String window;
	private String actionClass;
	private String actionMethod;
	

	@Override
	public Map<String, Object> getParams() {
		return params;
	}

	@Override
	public String getWindow() {
		return window;
	}

	@Override
	public String getActionClass() {
		return actionClass;
	}

	@Override
	public String getActionMethod() {
		return actionMethod;
	}

	public void setParams(Map<String,Object> params) {
		this.params = params;
	}

	public void setWindow(String window) {
		this.window = window;
	}

	public void setActionClass(String actionClass) {
		this.actionClass = actionClass;
	}

	public void setActionMethod(String actionMethod) {
		this.actionMethod = actionMethod;
	}

}
