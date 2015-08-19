package tr.com.agem.arya;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import junit.framework.TestCase;
import tr.com.agem.arya.metadata.arya.impl.AryaType;
import tr.com.agem.arya.metadata.arya.impl.ObjectFactory;
import tr.com.agem.arya.metadata.arya.impl.WindowType;

public class TestAryaXMLParse extends TestCase {
	// @Test
	public void testAdd1Plus1() throws IOException {

		try {

			File file = new File(this.getClass().getClassLoader().getResource("file.xml").getPath());
			System.out.println(file.getAbsolutePath());
			JAXBContext jaxbContext = JAXBContext.newInstance(WindowType.class, ObjectFactory.class);

			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			@SuppressWarnings("unchecked")
			JAXBElement<AryaType> cc = (JAXBElement<AryaType>) jaxbUnmarshaller.unmarshal(file);

			System.out.println(cc);
			AryaType arya = cc.getValue();

			List<Object> comp = arya.getContent();

			for (Object o : comp) {
				if (o instanceof JAXBElement) {
					JAXBElement<?> x = (JAXBElement<?>) o;
					System.out.println(x.getDeclaredType());
					if (x.getName().getLocalPart().equalsIgnoreCase("window")) {
						WindowType window = (WindowType) x.getValue();
						for (Object oo : window.getContent()) {
							if (oo instanceof JAXBElement<?>) {
								JAXBElement<?> xx = (JAXBElement<?>) oo;
								System.out.println(xx.getDeclaredType());
							}
						}
					}
				}
			}

		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}
}
