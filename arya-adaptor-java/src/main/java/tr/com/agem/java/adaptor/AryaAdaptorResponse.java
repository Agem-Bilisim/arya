package tr.com.agem.java.adaptor;

import tr.com.agem.core.adaptor.IAryaAdaptorResponse;

public class AryaAdaptorResponse implements IAryaAdaptorResponse {

	private String viewName;

	private String data;
	
	private String attributes;

	@Override
	public String getViewName() {
		return viewName;
	}

	public void setViewName(String viewName) {
		this.viewName = viewName;
	}

	@Override
	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}
	
	public void setAttributes(String attributes) {
		this.attributes = attributes;
	}

	@Override
	public String getAttributes() {
		return attributes;
	}

}
