package tr.com.agem.arya.interpreter.zkoss;

import java.io.IOException;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.zkoss.zk.ui.Component;

import tr.com.agem.arya.interpreter.component.IAryaComponentProperty;
import tr.com.agem.core.gateway.model.AryaRequest;
import tr.com.agem.core.gateway.model.RequestTypes;
import tr.com.agem.core.interpreter.IAryaInterpreter;
import tr.com.agem.core.property.reader.PropertyReader;

public class AryaWindow extends BaseController {

	private static final long serialVersionUID = 8576119465618112119L;

	IAryaInterpreter interpreter;
	List<IAryaComponentProperty> components;

	@Override
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		init();
	}

	private void init() throws IOException {
		@SuppressWarnings("resource")
		ApplicationContext appContext = new ClassPathXmlApplicationContext("arya-interpreter-zkoss.xml");

		interpreter = (IAryaInterpreter) appContext.getBean("aryaInterpreter");

		AryaRequest request = new AryaRequest();
		request.setAction("master");
		request.setRequestType(RequestTypes.VIEW_ONLY);
		String masterWindow = AryaInterpreterHelper.callUrl(PropertyReader.property("gateway.base.url"), request);
		interpreter.interpretAryaResponse(masterWindow, getIcerik(), this);
	}

	public IAryaInterpreter getInterpreter() {
		return interpreter;
	}

	public void setInterpreter(IAryaInterpreter interpreter) {
		this.interpreter = interpreter;
	}

	public void add(List<IAryaComponentProperty> components) {
		this.components = components;
	}

	public List<IAryaComponentProperty> getComponents() {
		return components;
	}

	public void setComponents(List<IAryaComponentProperty> components) {
		this.components = components;
	}

}
