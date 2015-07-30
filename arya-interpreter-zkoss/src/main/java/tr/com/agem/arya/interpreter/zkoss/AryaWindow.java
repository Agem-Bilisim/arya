package tr.com.agem.arya.interpreter.zkoss;

import java.io.IOException;
import java.io.StringReader;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.restlet.representation.Representation;
import org.restlet.resource.ClientResource;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.zkoss.zk.ui.Component;

import tr.com.agem.arya.metadata.zul.impl.WindowType;
import tr.com.agem.arya.metadata.zul.impl.ZkType;

@SuppressWarnings("serial")
public class AryaWindow extends BaseController {

	@Override
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		init();
	}

	@SuppressWarnings({ "unchecked", "resource" })
	private void init() throws IOException {
		AryaInterpreterZkoss interpreter = new AryaInterpreterZkoss();

		ClientResource resource = new ClientResource(
				"http://localhost:8080/arya/rest/hello");

		Representation clientText = resource.get();

		String masterWindowJSON = clientText.getText();

		try {

			// JAXBContext jaxbContext = JAXBContext.newInstance(ZkType.class,
			// tr.com.agem.arya.metadata.zul.impl.ObjectFactory.class);

			ApplicationContext appContext = new ClassPathXmlApplicationContext(
					"arya-interpreter-zkoss.xml");

			JAXBContext jaxbContext = (JAXBContext) appContext
					.getBean("jaxbContext");

			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

			StringReader reader = new StringReader(masterWindowJSON);

			ZkType zk = ((JAXBElement<ZkType>) ((JAXBElement<ZkType>) jaxbUnmarshaller
					.unmarshal(reader))).getValue();

			List<Object> comp = zk.getContent();

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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
