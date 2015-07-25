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
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.Button;
import org.zkoss.zul.Label;
import org.zkoss.zul.Textbox;

import tr.com.agem.arya.metadata.zul.impl.ButtonType;
import tr.com.agem.arya.metadata.zul.impl.LabelType;
import tr.com.agem.arya.metadata.zul.impl.TextboxType;
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

						for (Object oo : window.getContent()) {
							if (oo instanceof JAXBElement<?>) {
								JAXBElement<?> j = (JAXBElement<?>) oo;
								System.out.println(j.getDeclaredType());
								if (((JAXBElement<?>) j).getName()
										.getLocalPart()
										.equalsIgnoreCase("label")) {
									LabelType objType = (LabelType) j
											.getValue();
									Label obj = new Label();
									obj.setValue(objType.getValue());
									obj.setParent(getIcerik());
								} else if (((JAXBElement<?>) j).getName()
										.getLocalPart()
										.equalsIgnoreCase("textbox")) {
									TextboxType objType = (TextboxType) j
											.getValue();
									Textbox obj = new Textbox();
									obj.setValue(objType.getValue());
									obj.setParent(getIcerik());
								} else if (((JAXBElement<?>) j).getName()
										.getLocalPart()
										.equalsIgnoreCase("button")) {
									ButtonType objType = (ButtonType) j
											.getValue();
									Button obj = new Button();
									obj.setLabel(objType.getLabel());
									obj.setParent(getIcerik());
									if (!objType.getOnClick().isEmpty()) {
										obj.addEventListener(Events.ON_CLICK, new OnClickEventListener(getIcerik(), objType.getOnClick()));
									}
								}
							} else
								System.out.println("---> " + oo);
						}
					}
				}

			}
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
