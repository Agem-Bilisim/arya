package tr.com.agem.arya.interpreter.utils;

import java.io.IOException;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Div;

import tr.com.agem.arya.interpreter.components.base.AryaMain;
import tr.com.agem.arya.interpreter.script.ElementFunctions;
import tr.com.agem.core.gateway.model.AryaRequest;
import tr.com.agem.core.gateway.model.AryaResponse;
import tr.com.agem.core.gateway.model.RequestTypes;
import tr.com.agem.core.property.reader.PropertyReader;

@SuppressWarnings("rawtypes")
public class BaseController extends GenericForwardComposer {

	private static final long serialVersionUID = 8866650311533378984L;
	private Div componentContainer; // works as a parent component

	public BaseController() {
		super();
	}

	@SuppressWarnings("unchecked")
	@Override
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		init();
	}

	private void init() throws IOException {
		
		// Prepare initial request
		AryaRequest request = new AryaRequest();
		
		if(ElementFunctions.getLastPage() != null) {
			
			request.setAction(ElementFunctions.getLastPage());
			request.setRequestType(RequestTypes.valueOf(ElementFunctions.getReqType()));
		} 
		else {
			
			request.setAction("login");
			request.setRequestType(RequestTypes.VIEW_ONLY);
		} 

		String responseStr=null;
		try {
			responseStr = AryaInterpreterHelper.callUrl(PropertyReader.property("gateway.base.url"), request);
		} catch (Exception e) {
			e.printStackTrace();
		} 

		
		AryaResponse response = new AryaResponse();
		response.fromXMLString(responseStr);
		
		if(componentContainer==null)
			componentContainer=new Div();
		
		AryaMain main = new AryaMain(componentContainer);
		
		AryaInterpreterHelper.interpretResponse(response, main);
	}

	public Div getComponentContainer() {
		return componentContainer;
	}

	public void setComponentContainer(Div componentContainer) {
		this.componentContainer = componentContainer;
	}

}
