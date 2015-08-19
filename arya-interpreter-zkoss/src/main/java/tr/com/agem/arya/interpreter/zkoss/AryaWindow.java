package tr.com.agem.arya.interpreter.zkoss;

import java.io.IOException;
import java.io.StringReader;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.zkoss.zk.ui.Component;

import tr.com.agem.arya.metadata.arya.impl.AryaType;
import tr.com.agem.arya.metadata.arya.impl.WindowType;
import tr.com.agem.core.gateway.model.AryaRequest;
import tr.com.agem.core.gateway.model.AryaResponse;
import tr.com.agem.core.gateway.model.RequestTypes;
import tr.com.agem.core.interpreter.IAryaInterpreter;

@SuppressWarnings("serial")
public class AryaWindow extends BaseController {

	@Override
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		init();
	}

	@SuppressWarnings({ "unchecked", "resource" })
	private void init() throws IOException {
		ApplicationContext appContext = new ClassPathXmlApplicationContext(
				"arya-interpreter-zkoss.xml");

		IAryaInterpreter interpreter = (IAryaInterpreter) appContext
				.getBean("aryaInterpreter");

		AryaRequest request = new AryaRequest();
		request.setAction("master");
		request.setRequestType(RequestTypes.VIEW_ONLY);
		String masterWindow= AryaInterpreterHelper.callUrl("http://localhost:8080/arya/rest/hello", request);
		
		AryaResponse response = new AryaResponse();
		
		response.fromXMLString(masterWindow);
		
		try {
			JAXBContext jaxbContext = (JAXBContext) appContext
					.getBean("jaxbContext");

			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

			StringReader reader = new StringReader(response.getView());

			AryaType arya = ((JAXBElement<AryaType>) ((JAXBElement<AryaType>) jaxbUnmarshaller
					.unmarshal(reader))).getValue();

			List<Object> comp = arya.getContent();

			for (Object o : comp) {
				if (o instanceof JAXBElement) {
					if (((JAXBElement<?>) o).getName().getLocalPart()
							.equalsIgnoreCase("window")) {
						WindowType window = (WindowType) ((JAXBElement<?>) o)
								.getValue();

						interpreter.createComponents(getIcerik(),
								window.getContent());
					}
				}

			}
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}

}
