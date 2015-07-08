package tr.com.agem.arya;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import junit.framework.TestCase;
import tr.com.agem.arya.metadata.xul.impl.ButtonElementType;
import tr.com.agem.arya.metadata.xul.impl.ObjectFactory;
import tr.com.agem.arya.metadata.xul.impl.TextboxElementType;
import tr.com.agem.arya.metadata.xul.impl.WindowElementType;

public class TestXMLParse extends TestCase {
	// @Test
	public void testAdd1Plus1() throws IOException {

		try {

			File file = new File("file.xml");
			System.out.println(file.getAbsolutePath());
			JAXBContext jaxbContext = JAXBContext.newInstance(
					WindowElementType.class, ObjectFactory.class);

			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			@SuppressWarnings("unchecked")
			JAXBElement<WindowElementType> cc = (JAXBElement<WindowElementType>) jaxbUnmarshaller
					.unmarshal(file);

			WindowElementType window = cc.getValue();

			List<Object> comp = window.getAnyOrTextboxOrStatusbar();

			for (Object o : comp) {
				if (o instanceof JAXBElement) {
					JAXBElement<?> x = (JAXBElement<?>) o;
					System.out.println(x.getName());
					if (x.getName().getLocalPart()
							.equalsIgnoreCase("statusbar")) {

					} else if (x.getName().getLocalPart()
							.equalsIgnoreCase("textbox")) {
						TextboxElementType et = (TextboxElementType) x
								.getValue();
						System.out.println(et.getId());
					} else if (x.getName().getLocalPart()
							.equalsIgnoreCase("button")) {
						ButtonElementType et = (ButtonElementType) x.getValue();
						System.out.println(et.getId());
					}
				}

				System.out.println(window.getId());
			}

		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}
}
