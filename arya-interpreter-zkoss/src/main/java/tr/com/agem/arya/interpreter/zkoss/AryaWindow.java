package tr.com.agem.arya.interpreter.zkoss;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.zkoss.zk.ui.Component;

import tr.com.agem.core.gateway.model.AryaRequest;
import tr.com.agem.core.gateway.model.AryaResponse;
import tr.com.agem.core.gateway.model.RequestTypes;
import tr.com.agem.core.interpreter.IAryaComponent;
import tr.com.agem.core.property.reader.PropertyReader;

public class AryaWindow extends BaseController {

	private static final long serialVersionUID = 8576119465618112119L;

	List<IAryaComponent> components;

	@SuppressWarnings("unchecked")
	@Override
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		init();
	}

	private void init() throws IOException {

		components = new ArrayList<IAryaComponent>();

		// Prepare initial request
		AryaRequest request = new AryaRequest();
		request.setAction("master");
		request.setRequestType(RequestTypes.VIEW_ONLY);
	

		String responseStr = AryaInterpreterHelper.callUrl(PropertyReader.property("gateway.base.url"), request);
		AryaResponse response = new AryaResponse();
		response.fromXMLString(responseStr);

		AryaInterpreterHelper.interpretResponse(response, this);
	}

	public List<IAryaComponent> getComponents() {
		return components;
	}

	public void setComponents(List<IAryaComponent> components) {
		this.components = components;
	}

}
