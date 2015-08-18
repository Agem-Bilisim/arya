package tr.com.agem.arya.interpreter.zkoss;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Textbox;

import tr.com.agem.core.gateway.model.AryaRequest;
import tr.com.agem.core.gateway.model.AryaResponse;
import tr.com.agem.core.gateway.model.RequestTypes;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

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
		request.setAction("action2");
		request.setRequestType(RequestTypes.DATA_ONLY);

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("username", "alio");

		request.setParams(params);

		String res = AryaInterpreterHelper.callUrl(
				"http://localhost:8080/arya/rest/arya", request);

		AryaResponse response = new AryaResponse();

		response.fromXMLString(res);

		if (response.getData().isEmpty()) {
			Textbox tb = new Textbox();
			tb.setValue("Boş");
			tb.setParent(this.parent);
		} else {
			Listbox lb = new Listbox();
			lb.setHeight("300px");
			lb.setMold("paging");
			lb.setPageSize(10);
			lb.setParent(this.parent);
			ObjectMapper mapper = new ObjectMapper().configure(
					DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

			@SuppressWarnings("unchecked")
			List<Map<String,Object>> list = mapper.readValue(response.getData(), List.class);
			for (Map<String,Object> o : list) {
				Listitem li = new Listitem();
				li.setLabel(o.get("ilAdi") +" - " + o.get("ilKodu"));
				li.setValue(o.get("ilKodu"));
				li.setParent(lb);
			}
		}
	}
}
