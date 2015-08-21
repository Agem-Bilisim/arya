package tr.com.agem.arya.interpreter.zkoss;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listitem;

import tr.com.agem.core.gateway.model.AryaRequest;
import tr.com.agem.core.gateway.model.AryaResponse;
import tr.com.agem.core.gateway.model.RequestTypes;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

@SuppressWarnings("rawtypes")
public class OnClickEventListener implements EventListener {

	Component parent;
	String onClick;
	AryaWindow aryaWindow;

	public OnClickEventListener(Component comp, String onClick, AryaWindow aryaWindow) {
		super();
		this.parent = comp;
		this.onClick = onClick;
		this.aryaWindow = aryaWindow;
	}

	@Override
	public void onEvent(Event event) throws Exception {
		/*
		 * Request
		 */
		AryaRequest request = new AryaRequest();
		request.setAction("ilceList"); // TODO onClick stringi çözümlenerek
										// action ve parametreler
										// ayrıştırılmalı...
		request.setRequestType(RequestTypes.ALL);

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("username", "alio");
		params.put("ilKodu", "35");
		request.setParams(params);

		/*
		 * Call Action
		 */
		String res = AryaInterpreterHelper.callUrl(
				"http://localhost:8080/arya/rest/arya", request);

		/*
		 * Response
		 */
		AryaResponse response = new AryaResponse();
		response.fromXMLString(res);
		ObjectMapper mapper = new ObjectMapper().configure(
				DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		@SuppressWarnings("unchecked")
		List<Map<String, Object>> dataList = mapper.readValue(
				response.getData(), List.class);

		aryaWindow.getInterpreter().interpretAryaResponse(response, parent, aryaWindow);
		
		/*
		 * TODO Java Script ????
		 */
		Clients.evalJavaScript("getComponentById('deneme2').setValue('Hedeeee');");

		if (dataList != null && dataList.size() > 0) {
			Listbox lb = new Listbox();
			lb.setHeight("300px");
			lb.setMold("paging");
			lb.setPageSize(10);
			lb.setParent(this.parent);
			for (Map<String, Object> o : dataList) {
				Listitem li = new Listitem();
				li.setLabel((String) o.get("ilceAdi"));
				li.setValue(o.get("ilceKodu"));
				li.setParent(lb);
			}
		}
	}
}