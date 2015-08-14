package tr.com.agem.arya.interpreter.zkoss;

import java.util.HashMap;
import java.util.Map;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zul.Textbox;

import tr.com.agem.core.gateway.model.AryaRequest;
import tr.com.agem.core.gateway.model.AryaResponse;
import tr.com.agem.core.gateway.model.RequestTypes;

@SuppressWarnings("rawtypes")
public class OnClickEventListener implements EventListener {

	Component parent;
	String onClick;

	public OnClickEventListener(Component comp, String onClick) {
		super();
		this.parent = comp;
		this.onClick = onClick;
	}

	@Override
	public void onEvent(Event event) throws Exception {
		AryaRequest request = new AryaRequest();
		request.setAction("myAction");
		request.setRequestType(RequestTypes.DATA_ONLY);

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("username", "alio");

		request.setParams(params);

		String res = AryaInterpreterHelper.callUrl(
				"http://localhost:8080/arya/rest/arya", request);

		AryaResponse response = new AryaResponse();

		response.fromXMLString(res);

		try {
			Textbox tb = new Textbox();
			tb.setValue(response.getData());
			tb.setParent(this.parent);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
