package tr.com.agem.java.adaptor;

import java.util.Map;

import tr.com.agem.core.adaptor.IAryaAdaptorMapper;
import tr.com.agem.core.gateway.model.IAryaRequest;

public class AryaSampleMapper implements IAryaAdaptorMapper {
	private Map<String, Object> actionMap;
	private Map<String, String> actionViewMap;

	@Override
	public Object map(IAryaRequest request) {
		Object action = getActionMap().get(request.getAction());
		String actionView = getActionViewMap().get(request.getAction());

		AryaAdaptorResponse returnVal = new AryaAdaptorResponse();

		// TODO: Call action
		returnVal.setData("{message:'Merhaba " + action + "'}");

		returnVal.setViewName(actionView);

		return returnVal;
	}

	@Override
	public Map<String, Object> getActionMap() {
		return actionMap;
	}

	public void setActionMap(Map<String, Object> actionMap) {
		this.actionMap = actionMap;
	}

	@Override
	public Map<String, String> getActionViewMap() {
		return actionViewMap;
	}

	public void setActionViewMap(Map<String, String> actionViewMap) {
		this.actionViewMap = actionViewMap;
	}

}
