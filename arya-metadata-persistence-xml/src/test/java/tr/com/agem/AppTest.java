package tr.com.agem;

import java.io.IOException;
import java.io.StringReader;
import java.util.List;

import javax.naming.spi.ObjectFactory;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;

import tr.com.agem.arya.metadata.persistence.impl.xml.MetaDataXml;
import tr.com.agem.arya.metadata.persistence.impl.xml.MetadataPersistenceImplXml;
import tr.com.agem.arya.metadata.zul.impl.WindowType;
import tr.com.agem.arya.metadata.zul.impl.ZkType;

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
	@SuppressWarnings("unchecked")
	public void testApp() {

		MetadataPersistenceImplXml xml = new MetadataPersistenceImplXml();

		MetaDataXml mdxml = (MetaDataXml) xml.findWithName("arya", null, null);

		System.out.println(mdxml.getMetaData());

		try {

			JAXBContext jaxbContext = JAXBContext.newInstance(ZkType.class,
					ObjectFactory.class);

			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

			StringReader reader = new StringReader(mdxml.getMetaData());

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
								System.out.println(j.getValue());
							} else
								System.out.println("---> " + oo);
						}
					}
				}

			}
			// try {
			// window = new ObjectMapper().readValue(mdxml.getMetaData(),
			// WindowType.class);
			//
			// // Map<String, Object> properties = new HashMap<String,
			// Object>(2);
			// // //properties.put(UnmarshallerProperties.MEDIA_TYPE,
			// MediaType.APPLICATION_JSON);
			// // //properties.put(JAXBContextProperties.JSON_INCLUDE_ROOT,
			// true);
			// // JAXBContext jc = JAXBContext.newInstance(new Class[]
			// {WindowType.class,
			// tr.com.agem.arya.metadata.zul.impl.ObjectFactory.class});// ,
			// properties);
			// //
			// // Unmarshaller unmarshaller = jc.createUnmarshaller();
			// // StreamSource json = new StreamSource(new
			// java.io.StringReader(mdxml.getMetaData()));
			// // window = unmarshaller.unmarshal(json,
			// WindowType.class).getValue();
			//
			// for (Object oo : window.getContent()) {
			// if (oo instanceof JAXBElement<?>) {
			// JAXBElement<?> j = (JAXBElement<?>) oo;
			// System.out.println(j.getValue());
			// } else
			// System.out.println("---> " + oo);
			// }
			//
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		assertTrue(true);
	}
}
