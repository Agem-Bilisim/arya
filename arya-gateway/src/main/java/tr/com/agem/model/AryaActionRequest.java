package tr.com.agem.model;

import java.io.Serializable;
import java.util.Map;

import tr.com.agem.core.gateway.model.IAryaActionRequest;

public class AryaActionRequest implements IAryaActionRequest,Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Map<String,Object> params;
	private String window;
	private String action;
	

	@Override
	public Map<String, Object> getParams() {
		return params;
	}

	@Override
	public String getWindow() {
		return window;
	}

	public void setParams(Map<String,Object> params) {
		this.params = params;
	}

	public void setWindow(String window) {
		this.window = window;
	}

	@Override
	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}


}
