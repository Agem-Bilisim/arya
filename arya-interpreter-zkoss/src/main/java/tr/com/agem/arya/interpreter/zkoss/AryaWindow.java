package tr.com.agem.arya.interpreter.zkoss;

import java.io.IOException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.zkoss.zk.ui.Component;

import tr.com.agem.core.gateway.model.AryaRequest;
import tr.com.agem.core.gateway.model.RequestTypes;
import tr.com.agem.core.interpreter.IAryaInterpreter;

@SuppressWarnings("serial")
public class AryaWindow extends BaseController {
	IAryaInterpreter interpreter;

	@Override
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		init();
	}

	private void init() throws IOException {
		@SuppressWarnings("resource")
		ApplicationContext appContext = new ClassPathXmlApplicationContext(
				"arya-interpreter-zkoss.xml");

		interpreter = (IAryaInterpreter) appContext.getBean("aryaInterpreter");

		AryaRequest request = new AryaRequest();
		request.setAction("master");
		request.setRequestType(RequestTypes.VIEW_ONLY);
		String masterWindow = AryaInterpreterHelper.callUrl(
				"http://localhost:8080/arya/rest/hello", request);
		interpreter.interpretAryaResponse(masterWindow, getIcerik(), this);
	}

	public IAryaInterpreter getInterpreter() {
		return interpreter;
	}

	public void setInterpreter(IAryaInterpreter interpreter) {
		this.interpreter = interpreter;
	}

}
