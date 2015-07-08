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
import tr.com.agem.arya.metadata.xul.impl.TextboxElementType;
import tr.com.agem.arya.metadata.zul.impl.ObjectFactory;
import tr.com.agem.arya.metadata.zul.impl.WindowType;
import tr.com.agem.arya.metadata.zul.impl.ZkType;

public class TestZULXMLParse extends TestCase {
	// @Test
	public void testAdd1Plus1() throws IOException {

		try {

			File file = new File("file.xml");
			System.out.println(file.getAbsolutePath());
			JAXBContext jaxbContext = JAXBContext.newInstance(WindowType.class,
					ObjectFactory.class);

			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			@SuppressWarnings("unchecked")
			JAXBElement<ZkType> cc = (JAXBElement<ZkType>) jaxbUnmarshaller
					.unmarshal(file);

			System.out.println(cc);
			ZkType zk = cc.getValue();

			List<Object> comp = zk.getContent();

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
