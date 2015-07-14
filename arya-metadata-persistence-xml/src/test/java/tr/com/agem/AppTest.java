package tr.com.agem;

import java.io.IOException;

import javax.xml.bind.JAXBElement;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import tr.com.agem.arya.metadata.persistence.impl.xml.MetaDataXml;
import tr.com.agem.arya.metadata.persistence.impl.xml.MetadataPersistenceImplXml;
import tr.com.agem.arya.metadata.zul.impl.WindowType;

/**
 * Unit test for simple App.
 */
public class AppTest extends TestCase {
	/**
	 * Create the test case
	 *
	 * @param testName
	 *            name of the test case
	 */
	public AppTest(String testName) {
		super(testName);
	}

	/**
	 * @return the suite of tests being tested
	 */
	public static Test suite() {
		return new TestSuite(AppTest.class);
	}

	/**
	 * Rigourous Test :-)
	 */
	public void testApp() {

		MetadataPersistenceImplXml xml = new MetadataPersistenceImplXml();

		MetaDataXml mdxml = (MetaDataXml) xml.findWithName("arya", null, null);

		System.out.println(mdxml.getMetaData());

		WindowType window;
		try {
			window = new ObjectMapper().readValue(mdxml.getMetaData(),
					WindowType.class);

			for (Object oo : window.getContent()) {
				if (oo instanceof JAXBElement<?>) {
					JAXBElement<?> j = (JAXBElement<?>) oo;
					System.out.println(j.getValue());
				} else
					System.out.println("---> " + oo);
			}

		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		assertTrue(true);
	}
}
