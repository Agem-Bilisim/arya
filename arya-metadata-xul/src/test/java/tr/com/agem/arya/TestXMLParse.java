package tr.com.agem.arya;

import java.io.File;
import java.io.IOException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import junit.framework.TestCase;
import tr.com.agem.metadata.xul.impl.WindowElementType;

public class TestXMLParse extends TestCase {
	// @Test
	public void testAdd1Plus1() throws IOException {

		try {

			File file = new File("file.xml");
			System.out.println(file.getAbsolutePath());
			JAXBContext jaxbContext = JAXBContext
					.newInstance(WindowElementType.class);

			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			WindowElementType window = (WindowElementType) jaxbUnmarshaller
					.unmarshal(file);
			System.out.println(window);

		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}
}
