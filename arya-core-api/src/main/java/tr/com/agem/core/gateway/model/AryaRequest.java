package tr.com.agem.core.gateway.model;

import java.io.Serializable;
import java.util.Map;

import org.codehaus.jackson.map.ObjectMapper;

public class AryaRequest implements IAryaRequest, Serializable {
	
	private static final long serialVersionUID = 722327548252141833L;

	private Map<String,Object> params;
	private String action;
	private RequestTypes requestType;

	public RequestTypes getRequestType() {
		return requestType;
	}

	public void setRequestType(RequestTypes requestType) {
		this.requestType = requestType;
	}

	@Override
	public Map<String, Object> getParams() {
		return params;
	}

	public void setParams(Map<String,Object> params) {
		this.params = params;
	}

	@Override
	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}
	
	public String toJSON() {
		ObjectMapper mapper = new ObjectMapper();
		try {
			return mapper.writeValueAsString(this);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

}
